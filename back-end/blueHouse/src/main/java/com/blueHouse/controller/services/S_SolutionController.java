package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Solution;
import com.blueHouse.service.SolutionService;
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
@RequestMapping("/solutionService")
public class S_SolutionController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    SolutionService solutionService = (SolutionService) applicationContext.getBean("solutionService");

    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSolutionByCategory(
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

        PageHelper.startPage(page, size);
        List<T_Solution> solutions = solutionService.findSolutionByCategory(category);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", solutions);

        return map;
    }

    @RequestMapping(value = "/getByDesigner", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getSolutionByDesigner(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "designer") String designer,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        PageHelper.startPage(page, size);
        List<T_Solution> solutions = solutionService.findSolutionByDesignerName(designer);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", solutions);

        return map;
    }

}
