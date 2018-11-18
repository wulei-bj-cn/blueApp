package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.Favorite;
import com.blueHouse.service.FavoriteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/favoriteService")
public class S_FavoriteController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/findFavoriteByUserId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFavoriteByUserId(
            @RequestParam(value = "user_id") String user_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        FavoriteService favoriteService = (FavoriteService) applicationContext.getBean("favoriteService");
        List<Favorite> favorites = favoriteService.findFavoriteByUserId(user_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", favorites);

        return map;
    }

    @RequestMapping(value = "/findFavoriteByCategory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFavoriteByCategory(
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

        FavoriteService favoriteService = (FavoriteService) applicationContext.getBean("favoriteService");
        List<Favorite> favorites = favoriteService.findFavoriteByCategory(category);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", favorites);

        return map;
    }

    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteFavorite(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "item_id") String item_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        FavoriteService favoriteService = (FavoriteService) applicationContext.getBean("favoriteService");
        Favorite favorite = new Favorite();
        favorite.setUser_id(user_id);
        favorite.setItem_id(item_id);
        try {
            favoriteService.deleteFavorite(favorite);
        } catch (RuntimeException re) {
            returnStatus = false;
        }

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", "");

        return map;
    }

    @RequestMapping(value = "/insertFavorite", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertFavorite(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "item_id") String item_id,
            @RequestParam(value = "item_class") String item_class,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        FavoriteService favoriteService = (FavoriteService) applicationContext.getBean("favoriteService");
        Favorite favorite = new Favorite();
        favorite.setUser_id(user_id);
        favorite.setItem_id(item_id);
        favorite.setItem_class(item_class);
        favorite.setAdd_time(new Timestamp(System.currentTimeMillis()));
        try {
            favoriteService.insertFavorite(favorite);
        } catch (RuntimeException re) {
            returnStatus = false;
        }

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", "");

        return map;
    }

}
