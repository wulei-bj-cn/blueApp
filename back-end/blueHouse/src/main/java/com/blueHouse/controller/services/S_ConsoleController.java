package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.alibaba.fastjson.JSONObject;
import com.blueHouse.pojo.Article;
import com.blueHouse.pojo.browse.T_Activity;
import com.blueHouse.pojo.browse.T_Solution;
import com.blueHouse.service.ActivityService;
import com.blueHouse.service.ArticleService;
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
@RequestMapping("/home")
public class S_ConsoleController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
    SolutionService solutionService = (SolutionService) applicationContext.getBean("solutionService");
    ArticleService articleService = (ArticleService) applicationContext.getBean("articleService");

    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertMeasure(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        JSONObject jsonObject = new JSONObject();
        JSONObject designsJson = new JSONObject();

        PageHelper.startPage(page, size);
        List<T_Activity> activities = activityService.findAllActivity();

        List<T_Solution> solutions1 = solutionService.findSolutionByCategory("⽓派中式");
        List<T_Solution> solutions2 = solutionService.findSolutionByCategory("奢华欧式");
        List<T_Solution> solutions3 = solutionService.findSolutionByCategory("精美⽥园");
        List<T_Solution> solutions4 = solutionService.findSolutionByCategory("⼤⽓美式");

        List<Article> articles = articleService.findAllArticles();

        designsJson.put("⽓派中式", solutions1);
        designsJson.put("奢华欧式", solutions2);
        designsJson.put("精美⽥园", solutions3);
        designsJson.put("⼤⽓美式", solutions4);

        jsonObject.put("banner", activities);
        jsonObject.put("news", articles);
        jsonObject.put("design", designsJson);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", jsonObject);

        return map;
    }

}
