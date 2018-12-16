package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Design;
import com.blueHouse.service.DesignService;
import com.blueHouse.service.LoginService;
import com.blueHouse.utils.TimeStampUtil;
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
 * Created by lihan on 2018/10/26.
 */
@Controller
@RequestMapping("/design")
public class DesignController  {

    @Resource
    private DesignService designService;

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 5;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllDesigns(HttpServletRequest req,ModelMap modelMap){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<T_Design> designs = designService.findAllDesign();
            modelMap.put("designs", designs);
            modelMap.put("designsCount", designs.size());
            modelMap.put("isSearching", false);
            return "designs";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchDesigns" , method = RequestMethod.GET)
    public String searchDesigns(HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            String searchKey = req.getParameter("searchKey");
            if (searchKey.isEmpty()) {
                modelMap.put("isSearching", false);
                return "redirect: /design/getAll";
            } else {
                List<T_Design> designs = designService.findDesignByT_DesignerName(searchKey);
                modelMap.put("searchKey", searchKey);
                modelMap.put("designsCount", designs.size());
                modelMap.put("searchDesigns", designs);
                modelMap.put("isSearching", true);
                return "designs";
            }
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/updateDesign", method = RequestMethod.GET)
    public String updateDesign(HttpServletRequest req) {
        String designId = req.getParameter("designId");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String ts = req.getParameter("ts");
        String designer = req.getParameter("designer");
        String status = req.getParameter("status");

        Timestamp tstime = null;

        try{
            tstime = TimeStampUtil.strToSqlDate(ts,"yyyy-MM-dd HH:mm");
        }catch (Exception e){
            e.printStackTrace();
        }

        T_Design newDesign = new T_Design();

        newDesign.setId(designId);
        newDesign.setName(name);
        newDesign.setTs(tstime);
        newDesign.setUrl(url);
        newDesign.setStatus(status);
        newDesign.setDesigner(designer);

        designService.updateDesign(newDesign);

        return "redirect: /design/getAll";
    }

    @RequestMapping(value = "/updateDesignWithFile", method = RequestMethod.POST)
    public String updateDesignFile(@RequestParam("design_file") MultipartFile designFile, HttpServletRequest request) {
        //String designId = request.getParameter("design_id");
        String name = request.getParameter("name");
        //String url = request.getParameter("url");
        String designer = request.getParameter("designer");
        //String status = request.getParameter("status");
        String details = request.getParameter("details");

        Timestamp tstime = TimeStampUtil.localTimToSqlDate();

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
                    String design_id = request.getParameter("design_id");
                    String targetPath = design_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "designs/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Design ID:" + design_id);
                        T_Design t_design = designService.findDesignById(design_id);
                        t_design.setUrl(targetPath);
                        t_design.setName(name);
                        t_design.setTs(tstime);
                        t_design.setDetails(details);
                        t_design.setDesigner(designer);
                        designService.updateDesign(t_design);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "redirect: /design/getAll";
    }

    @RequestMapping(value = "/insertDesign", method = RequestMethod.GET)
    public String insertDesign(HttpServletRequest req) {
        String designId = req.getParameter("designId");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String ts = req.getParameter("ts");
        String designer = req.getParameter("designer");
        String status = req.getParameter("status");

        Timestamp tstime = null;

        try{
            tstime = TimeStampUtil.strToSqlDate(ts,"yyyy-MM-dd HH:mm");
        }catch (Exception e){
            e.printStackTrace();
        }

        T_Design newDesign = new T_Design();

        newDesign.setId(designId);
        newDesign.setName(name);
        newDesign.setTs(tstime);
        newDesign.setUrl(url);
        newDesign.setStatus(status);
        newDesign.setDesigner(designer);

        designService.insertDesign(newDesign);

        return "redirect: /design/getAll";
    }
}
