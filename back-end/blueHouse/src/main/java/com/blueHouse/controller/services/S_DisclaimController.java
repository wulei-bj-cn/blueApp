package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Disclaim;
import com.blueHouse.pojo.orders.Order;
import com.blueHouse.pojo.orders.OrderItem;
import com.blueHouse.service.DisclaimService;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.OrderItemService;
import com.blueHouse.service.OrderService;
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
import java.util.Map;

@Controller
@RequestMapping("/disclaimService")
public class S_DisclaimController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    DisclaimService disclaimService = (DisclaimService) applicationContext.getBean("disclaimService");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    @RequestMapping(value = "/findDisclaimById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findDisclaimById(
            @RequestParam(value = "disclaim_id") String disclaim_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        T_Disclaim disclaim = disclaimService.findDisclaimById(disclaim_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", disclaim);

        return map;
    }

    @RequestMapping(value = "/insertDisclaim", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertDisclaim(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //生产新的Disclaim对象，并对其赋值，尤其是ID
        T_Disclaim t_disclaim = new T_Disclaim();
        String disclaim_name = "施工交底 for user " + user_id + " for order " + order_id;
        t_disclaim.setName(disclaim_name);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        t_disclaim.setTs(ts);
        //要保证有唯一的Disclaim id，这里通过MD5结合固定字符串"mea"的方法来大概率保证id唯一。
        String disclaim_id = "dis" + md5Service.encodeByMD5(disclaim_name + ts);
        t_disclaim.setId(disclaim_id);

        //生产新的订单项，就是将订单id，用户id，item的id插入到订单项表中。
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(disclaim_id);
        orderItem.setItem_class("disclaims");
        orderItem.setStart_time(ts);
        orderItem.setStatus("0");

        Order order = orderService.findOrderById(order_id);
        order.setId(order_id);
        order.setUser_id(user_id);
        order.setStatus("60");

        try {
            //更新Disclaim表，向Disclaim表中插入相关记录。
            disclaimService.insertDisclaim(t_disclaim);
            //施工交底已经完成，需要更新订单状态到6。
            orderService.updateOrderStatus(order);
            //根据订单id和disclaim id，将该测量项加入order item表
            orderItemService.insertOrderItem(orderItem);
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

    @RequestMapping(value = "/confirmDisclaim", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> confirmDisclaim(
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
        order.setStatus("62");

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
