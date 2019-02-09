package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.orders.*;
import com.blueHouse.pojo.services.OrderData;
import com.blueHouse.service.OMService;
import com.blueHouse.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderService")
public class S_OrderController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");

    @RequestMapping(value = "/getOrderByUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrderByUser(
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

        OMService omService = (OMService) applicationContext.getBean("omService");
        PageHelper.startPage(page, size);
        List<Order> orders = omService.findOrderByUser(user_id);
        List<OrderData> orderData = new ArrayList();
        for (Order order: orders) {
            String order_id = order.getId();
            String status = order.getStatus();
            orderData.add(
                    new OrderData(order_id, order_id, "", "", status)
            );
        }

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", orderData);

        return map;
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrderDetail(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Order> order = omService.findOrder(user_id, order_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", order);

        return map;
    }

    @RequestMapping(value = "/getDesignList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getDesignList(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        OMService omService = (OMService) applicationContext.getBean("omService");
        PageHelper.startPage(page, size);
        List<Design> designs = omService.findDesign(user_id, order_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", designs);

        return map;
    }

    @RequestMapping(value = "/getContractList", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getContractList(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Contract> contracts = omService.findContract(user_id, order_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", contracts);

        return map;
    }

    @RequestMapping(value = "/requestFinishOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> requestFinishOrder(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        Order order = orderService.findOrderById(order_id);
        order.setStatus("80");

        try {
            //用户提交尾款，请求完成订单。
            orderService.updateOrderStatus(order);
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

    @RequestMapping(value = "/finishOrder", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> finishOrder(
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //更新订单状态，标记测量已经完成，用户已经确认
        Order order = orderService.findOrderById(order_id);
        order.setStatus("82");

        try {
            //更新订单状态，标记测量已经完成，用户已经确认
            orderService.updateOrderStatus(order);
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
