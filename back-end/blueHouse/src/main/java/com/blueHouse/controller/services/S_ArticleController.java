package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.Article;
import com.blueHouse.service.ArticleService;
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
@RequestMapping("/articleService")
public class S_ArticleController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/findAllArticles", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getArticleByCategory(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        ArticleService articleService = (ArticleService) applicationContext.getBean("articleService");
        List<Article> articles = articleService.findAllArticles();

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", articles);

        return map;
    }


}
