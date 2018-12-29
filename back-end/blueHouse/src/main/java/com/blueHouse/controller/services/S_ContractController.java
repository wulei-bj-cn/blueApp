package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Contract;
import com.blueHouse.pojo.orders.Order;
import com.blueHouse.pojo.orders.OrderItem;
import com.blueHouse.service.ContractService;
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
@RequestMapping("/contractService")
public class S_ContractController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    ContractService contractService = (ContractService) applicationContext.getBean("contractService");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    @RequestMapping(value = "/findContractById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getContractByCategory(
            @RequestParam(value = "contract_id") String contract_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        T_Contract contract = contractService.findContractById(contract_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", contract);

        return map;
    }

    @RequestMapping(value = "/insertContract", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertContract(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            @RequestParam(value = "contract_type") String contract_type,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //生产新的Contract对象，并对其赋值，尤其是ID
        T_Contract t_contract = new T_Contract();
        String contract_name = contract_type + " for user " + user_id + " for order " + order_id;
        t_contract.setName(contract_name);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        t_contract.setTs(ts);
        t_contract.setType(contract_type);
        //要保证有唯一的Contract id，这里通过MD5结合固定字符串"mea"的方法来大概率保证id唯一。
        String contract_id = "con" + md5Service.encodeByMD5(contract_name + contract_type + ts);
        t_contract.setId(contract_id);

        //生产新的订单项，就是将订单id，用户id，item的id插入到订单项表中。
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(contract_id);
        orderItem.setItem_class("contracts");
        orderItem.setStart_time(ts);
        orderItem.setStatus("0");

        //更新订单状态，标记正在申请设计合同 - 也就是请求甲方出具设计方案
        Order order = orderService.findOrderById(order_id);
        order.setStatus("10");

        try {
            //更新Contract表，向Contract表中插入相关记录。
            contractService.insertContract(t_contract);
            //根据订单id和合同id，将该合同项加入order item表
            orderItemService.insertOrderItem(orderItem);
            //用户发起设计合同请求，并与线下签订合同，等待线上上传合同截图；
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

    @RequestMapping(value = "/confirmContract", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> confirmContract(
            @RequestParam(value = "order_id") String order_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //更新订单状态，标记合同已经完成，用户已经确认
        Order order = orderService.findOrderById(order_id);
        order.setStatus("12");

        try {
            //更新订单状态，标记合同已经完成，用户已经确认
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
