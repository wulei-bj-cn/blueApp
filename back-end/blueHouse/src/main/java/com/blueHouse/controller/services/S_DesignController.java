package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Design;
import com.blueHouse.pojo.orders.OrderItem;
import com.blueHouse.service.DesignService;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.OrderItemService;
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
@RequestMapping("/designService")
public class S_DesignController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    DesignService designService = (DesignService) applicationContext.getBean("designService");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    @RequestMapping(value = "/findDesignById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findDesignById(
            @RequestParam(value = "design_id") String design_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        T_Design design = designService.findDesignById(design_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", design);

        return map;
    }

    @RequestMapping(value = "/insertDesign", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertDesign(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //生产新的Design对象，并对其赋值，尤其是ID
        T_Design t_design = new T_Design();
        String design_name = "for user " + user_id + " for order " + order_id;
        t_design.setName(design_name);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        t_design.setTs(ts);
        t_design.setStatus("进行中");
        //要保证有唯一的Design id，这里通过MD5结合固定字符串"mea"的方法来大概率保证id唯一。
        String design_id = "des" + md5Service.encodeByMD5(design_name + ts);
        t_design.setId(design_id);

        //生产新的订单项，就是将订单id，用户id，item的id插入到订单项表中。
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(design_id);
        orderItem.setItem_class("designs");
        orderItem.setStart_time(ts);
        try {
            //更新Design表，向Design表中插入相关记录。
            designService.insertDesign(t_design);
            //走到这一步的时候，不需要新建订单了，因为订单在预约测量环节中已经创建了。
            //根据订单id和design id，将该测量项加入order item表
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

}
