package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.Cart;
import com.blueHouse.service.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cartService")
public class S_CartController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/findCartByUserId", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCartByCategory(
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

        CartService cartService = (CartService) applicationContext.getBean("cartService");
        List<Cart> carts = cartService.findCartByUserId(user_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", carts);

        return map;
    }

    @RequestMapping(value = "/deleteCart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteCart(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "item_id") String item_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        CartService collectionService = (CartService) applicationContext.getBean("collectionService");
        Cart collection = new Cart();
        collection.setUser_id(user_id);
        collection.setItem_id(item_id);
        try {
            collectionService.deleteCart(collection);
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

    @RequestMapping(value = "/insertCart", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertCart(
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

        CartService collectionService = (CartService) applicationContext.getBean("collectionService");
        Cart collection = new Cart();
        collection.setUser_id(user_id);
        collection.setItem_id(item_id);
        collection.setItem_class(item_class);
        collection.setAdd_time((Timestamp) new Date());
        try {
            collectionService.insertCart(collection);
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
