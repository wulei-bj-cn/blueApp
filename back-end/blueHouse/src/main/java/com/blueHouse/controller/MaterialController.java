package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.service.LoginService;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.MaterialService;
import com.blueHouse.utils.TimeStampUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by lihan on 2018/10/1.
 */
@RequestMapping("/material")
@Controller
public class MaterialController {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    @Resource
    private MaterialService materialService;

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 6;

    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public String getAllMaterial(HttpServletRequest req ,ModelMap modelMap){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<T_Material> materials = materialService.findAllMaterials();
            modelMap.put("materials",materials);
            modelMap.put("materialsCount",materials.size());
            modelMap.put("isSearching",false);
            return "materials";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value="/searchMaterials",method = RequestMethod.GET)
    public String searchMaterials(HttpServletRequest req ,ModelMap modelMap){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")) {
            if (loginService.permissionCheck(user, PAGEPERMISSIONCODE)) {
                modelMap.put("permissionCode", true);
            } else {
                modelMap.put("permissionCode", false);
            }
            String searchKey = req.getParameter("searchKey");
            if (searchKey.isEmpty()) {
                modelMap.put("isSearching", false);
                return "redirect: /material/getAll";
            } else {
                List<T_Material> materials = materialService.findMaterialByPartialName(searchKey);
                modelMap.put("materials", materials);
                modelMap.put("materialsCount", materials.size());
                modelMap.put("isSearching", true);
                modelMap.put("searchKey", searchKey);
                return "materials";
            }
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/updateMaterial", method = RequestMethod.GET)
    public String updateMaterial(HttpServletRequest request) {
        T_Material material = this.createMaterialFromRequest(request);
        materialService.updateMaterial(material);

        return "redirect: /material/getAll";
    }

    @RequestMapping(value = "/insertiMaterial", method = RequestMethod.GET)
    public String insertMaterial(HttpServletRequest request) {
        T_Material material = this.createMaterialFromRequest(request);
        materialService.insertMaterial(material);

        return "redirect: /material/getAll";
    }

    private T_Material createMaterialFromRequest(HttpServletRequest request){
        T_Material material = new T_Material();
        String brand = request.getParameter("brand");
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String id = request.getParameter("materialId");

        long cate = 0L;
        double pric = 0.0;
        try{
            cate = Long.parseLong(category);
            pric = Double.parseDouble(price);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        material.setId(id);
        material.setBrand(brand);
        material.setName(name);
        material.setUrl(url);
        material.setCategory(category);
        material.setPrice(pric);

        return material;
    }

    @RequestMapping(value = "/updateMaterialWithFile", method = RequestMethod.POST)
    public String updateMaterialFile(@RequestParam("material_file") MultipartFile materialFile, HttpServletRequest request) {
        String material_id = request.getParameter("material_id");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String category = request.getParameter("category");
        String prePrice = request.getParameter("prePrice");
        String newPrice = request.getParameter("newPrice");
        String details = request.getParameter("details");

        //long cate = 0L;
        double pric = 0.0;
        double prepric = 0.0;
        try{
            //cate = Long.parseLong(category);
            pric = Double.parseDouble(newPrice);
            prepric =  Double.parseDouble(newPrice);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img_address = prop.getProperty("img_address");

        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    //String material_id = request.getParameter("material_id");
                    String targetPath = material_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "materials/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Material ID:" + material_id);
                        T_Material t_material = materialService.finaMaterialById(material_id);
                        t_material.setUrl(targetPath);
                        t_material.setName(name);
                        t_material.setBrand(brand);
                        t_material.setCategory(category);
                        t_material.setDetails(details);
                        t_material.setNewPrice(pric);
                        t_material.setPrePrice(prepric);
                        materialService.updateMaterial(t_material);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "redirect: /material/getAll";
    }

    @RequestMapping(value = "/insertMaterialWithFile", method = RequestMethod.POST)
    public String insertMaterialFile(@RequestParam("material_file") MultipartFile materialFile, HttpServletRequest request) {
        //String material_id = request.getParameter("material_id");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String category = request.getParameter("category");
        String prePrice = request.getParameter("prePrice");
        String newPrice = request.getParameter("newPrice");
        String details = request.getParameter("details");

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String material_id = "mat" + md5Service.encodeByMD5(brand + name + ts);

        double pric = 0.0;
        double prepric = 0.0;
        try{
            //cate = Long.parseLong(category);
            pric = Double.parseDouble(newPrice);
            prepric =  Double.parseDouble(newPrice);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        T_Material t_material = new T_Material();
        t_material.setName(name);
        t_material.setBrand(brand);
        t_material.setCategory(category);
        t_material.setDetails(details);
        t_material.setNewPrice(pric);
        t_material.setPrePrice(prepric);
        t_material.setId(material_id);


        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img_address = prop.getProperty("img_address");

        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String targetPath = material_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "materials/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Material ID:" + material_id);
                        t_material.setUrl(targetPath);
                        materialService.insertMaterial(t_material);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "redirect: /material/getAll";
    }

}

