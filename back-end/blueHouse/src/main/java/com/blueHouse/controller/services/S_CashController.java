package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.orders.Order;
import com.blueHouse.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cashService")
public class S_CashController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    OrderService orderService = (OrderService) applicationContext.getBean("orderService");

    @RequestMapping(value = "/payCash", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertCash(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            @RequestParam(value = "cash_type") String cash_type,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //更新订单状态，标记正在申请提交定金
        Order order = orderService.findOrderById(order_id);
        if(cash_type.equals("DesignCash")) {
            order.setStatus("20");
        } else if (cash_type.equals("ProjectCash")) {
            order.setStatus("50");
        }

        try {
            //更新订单状态，标记正在申请提交定金
            orderService.updateOrderStatus(order);
        } catch (RuntimeException re) {
            System.out.println(re.toString());
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
