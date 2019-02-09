package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.service.MaterialService;
import com.github.pagehelper.PageHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/materialService")
public class S_MaterialController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/getMaterialByCategory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMaterialByCategory(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "category") String category,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        MaterialService materialService = (MaterialService) applicationContext.getBean("materialService");
        PageHelper.startPage(page, size);
        List<T_Material> materials = materialService.findMaterialByCategory(category);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", materials);

        return map;
    }

    @RequestMapping(value = "/getMaterialByBrand", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMaterialByBrand(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "brand") String brand,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        MaterialService materialService = (MaterialService) applicationContext.getBean("materialService");
        PageHelper.startPage(page, size);
        List<T_Material> materials = materialService.findMaterialByBrand(brand);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", materials);

        return map;
    }

    @RequestMapping(value = "/findMaterialByPartialName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findMaterialByPartialName(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "name") String name,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        MaterialService materialService = (MaterialService) applicationContext.getBean("materialService");
        PageHelper.startPage(page, size);
        List<T_Material> materials = materialService.findMaterialByPartialName(name);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", materials);

        return map;
    }

    @RequestMapping(value = "/findAllMaterials", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMaterialByBrand(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        MaterialService materialService = (MaterialService) applicationContext.getBean("materialService");
        PageHelper.startPage(page, size);
        List<T_Material> materials = materialService.findAllMaterials();

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", materials);

        return map;
    }

}
