package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Measure;
import com.blueHouse.pojo.orders.Order;
import com.blueHouse.pojo.orders.OrderItem;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.MeasureService;
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
@RequestMapping("/measureService")
public class S_MeasureController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    MeasureService measureService = (MeasureService) applicationContext.getBean("measureService");
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");


    @RequestMapping(value = "/insertMeasure", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMeasureByCategory(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "address") String address,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //生产新的Measure对象，并对其赋值，尤其是ID
        T_Measure t_measure = new T_Measure();
        t_measure.setAddress(address);
        t_measure.setName(address);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        t_measure.setTs(ts);
        //要保证有唯一的Measure id，这里通过MD5结合固定字符串"mea"的方法来大概率保证id唯一。
        String measure_id = "mea" + md5Service.encodeByMD5(address + ts);
        t_measure.setId(measure_id);

        //生产新的订单，主要是订单id
        Order order = new Order();
        order.setUser_id(user_id);
        order.setStart_time(ts);
        order.setStatus("预约测量中");
        String order_id = "ord" + md5Service.encodeByMD5(user_id + measure_id);
        order.setId(order_id);

        //生产新的订单项，就是将订单id，用户id，item的id插入到订单项表中。
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(measure_id);
        orderItem.setItem_class("measures");
        orderItem.setStart_time(ts);
        try {
            //更新Measure表，向Measure表中插入相关记录。
            measureService.insertMeasure(t_measure);
            //由于预约测量是整个订单流程的第一环节，所以需要创建订单
            orderService.insertOrder(order);
            //预约测量是订单的第一步，插入订单项后，需要更新订单的状态到0 。
            order.setStatus("0");
            orderService.updateOrderStatus(order);
            //根据订单id和测量id，将该测量项加入order item表
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
