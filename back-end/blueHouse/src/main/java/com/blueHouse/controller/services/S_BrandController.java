package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.Brand;
import com.blueHouse.service.BrandService;
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
@RequestMapping("/brandService")
public class S_BrandController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/findAllBrands", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBrandByCategory(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        BrandService brandService = (BrandService) applicationContext.getBean("brandService");
        List<Brand> brands = brandService.findAllBrands();

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", brands);

        return map;
    }


}
