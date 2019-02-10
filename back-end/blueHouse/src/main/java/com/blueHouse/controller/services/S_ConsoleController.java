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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/home")
public class S_ConsoleController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
    SolutionService solutionService = (SolutionService) applicationContext.getBean("solutionService");
    ArticleService articleService = (ArticleService) applicationContext.getBean("articleService");

    private <T> List<T> limitedList(List<T> sourceList, int start_index, int end_index) {
        List<T> objects = null;
        if (sourceList != null && start_index < sourceList.size()) {
            if (end_index < sourceList.size()) {
                objects = sourceList.subList(start_index, end_index);
            } else {
                objects = sourceList.subList(start_index, sourceList.size());
            }
        }
        return objects;
    }

    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> homePage(
            HttpServletRequest req
    ) {

        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int home_page =  Integer.parseInt(prop.getProperty("home_page"));
        int home_size =  Integer.parseInt(prop.getProperty("home_size"));
        int start_index = home_page * home_size;
        int end_index = (home_page + 1) * home_size - 1;

        Map<String, Object> map = new HashMap<>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        JSONObject jsonObject = new JSONObject();
        JSONObject designsJson = new JSONObject();

        List<T_Activity> activities = activityService.findAllActivity();
        List<T_Activity> subActivities = limitedList(activities, start_index, end_index);

        List<T_Solution> solutions1 = solutionService.findSolutionByCategory("⽓派中式");
        List<T_Solution> subSol1 = limitedList(solutions1, start_index, end_index);
        List<T_Solution> solutions2 = solutionService.findSolutionByCategory("奢华欧式");
        List<T_Solution> subSol2 = limitedList(solutions2, start_index, end_index);
        List<T_Solution> solutions3 = solutionService.findSolutionByCategory("精美⽥园");
        List<T_Solution> subSol3 = limitedList(solutions3, start_index, end_index);
        List<T_Solution> solutions4 = solutionService.findSolutionByCategory("⼤⽓美式");
        List<T_Solution> subSol4 = limitedList(solutions4, start_index, end_index);

        List<Article> articles = articleService.findAllArticles();
        List<Article> subArticles = limitedList(articles, start_index, end_index);

        designsJson.put("⽓派中式", subSol1);
        designsJson.put("奢华欧式", subSol2);
        designsJson.put("精美⽥园", subSol3);
        designsJson.put("⼤⽓美式", subSol4);

        jsonObject.put("banner", subActivities);
        jsonObject.put("news", subArticles);
        jsonObject.put("design", designsJson);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", jsonObject);

        return map;
    }

}
