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
}

