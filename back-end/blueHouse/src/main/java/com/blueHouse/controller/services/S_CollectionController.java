package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.Collection;
import com.blueHouse.service.CollectionService;
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
@RequestMapping("/collectionService")
public class S_CollectionController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/findCollectionByUserId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCollectionByUserId(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "user_id") String user_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        CollectionService collectionService = (CollectionService) applicationContext.getBean("collectionService");
        List<Collection> collections = collectionService.findCollectionByUserId(user_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", collections);

        return map;
    }

    @RequestMapping(value = "/findCollectionByCategory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCollectionByCategory(
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

        CollectionService collectionService = (CollectionService) applicationContext.getBean("collectionService");
        List<Collection> collections = collectionService.findCollectionByCategory(category);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", collections);

        return map;
    }

    @RequestMapping(value = "/deleteCollection", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCollectionByCategory(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "item_id") String item_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        CollectionService collectionService = (CollectionService) applicationContext.getBean("collectionService");
        Collection collection = new Collection();
        collection.setUser_id(user_id);
        collection.setItem_id(item_id);
        try {
            collectionService.deleteCollection(collection);
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
