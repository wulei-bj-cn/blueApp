package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lihan on 2018/10/1.
 */
@RequestMapping("/material")
@Controller
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public String getAllMaterial(ModelMap modelMap){
        List<T_Material> materials = materialService.findAllMaterials();
        modelMap.put("materials",materials);
        modelMap.put("materialsCount",materials.size());
        modelMap.put("isSearching",false);
        return "materials";
    }

    @RequestMapping(value="/searchMaterials",method = RequestMethod.GET)
    public String searchMaterials(HttpServletRequest req ,ModelMap modelMap){
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()){
            modelMap.put("isSearching", false);
            return "redirect: /material/getAll";
        }else {
            List<T_Material> materials = materialService.findMaterialByPartialName(searchKey);
            modelMap.put("materials", materials);
            modelMap.put("materialsCount", materials.size());
            modelMap.put("isSearching", true);
            modelMap.put("searchKey", searchKey);
            return "materials";
        }
    }

    @RequestMapping(value = "/updateMaterial", method = RequestMethod.GET)
    public String updateMaterial(HttpServletRequest req) {
        String brand = req.getParameter("brand");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String id = req.getParameter("materialId");

        long cate = 0L;
        double pric = 0.0;
        try{
            cate = Long.parseLong(category);
            pric = Double.parseDouble(price);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


        T_Material newMaterial= new T_Material();

        newMaterial.setId(id);
        newMaterial.setBrand(brand);
        newMaterial.setName(name);
        newMaterial.setUrl(url);
        newMaterial.setCategory(category);
        newMaterial.setPrice(pric);

        materialService.updateMaterial(newMaterial);

        return "redirect: /material/getAll";
    }

    @RequestMapping(value = "/insertiMaterial", method = RequestMethod.GET)
    public String insertMaterial(HttpServletRequest req) {
        String brand = req.getParameter("brand");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        String id = req.getParameter("materialId");

        long cate = 0L;
        double pric = 0.0;
        try{
            cate = Long.parseLong(category);
            pric = Double.parseDouble(price);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }


        T_Material newMaterial= new T_Material();

        newMaterial.setId(id);
        newMaterial.setBrand(brand);
        newMaterial.setName(name);
        newMaterial.setUrl(url);
        newMaterial.setCategory(category);
        newMaterial.setPrice(pric);

        materialService.insertMaterial(newMaterial);

        return "redirect: /material/getAll";
    }
}

