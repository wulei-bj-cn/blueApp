package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.browse.T_Project;
import com.blueHouse.pojo.orders.Order;
import com.blueHouse.pojo.orders.OrderItem;
import com.blueHouse.service.OrderService;
import com.blueHouse.service.ProjectService;
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
@RequestMapping("/projectService")
public class S_ProjectController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    ProjectService projectService = (ProjectService) applicationContext.getBean("projectService");
    OrderService orderService = (OrderService) applicationContext.getBean("orderService");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    @RequestMapping(value = "/findProjectById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findProjectById(
            @RequestParam(value = "project_id") String project_id,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        T_Project project = projectService.findProjectById(project_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", project);

        return map;
    }

    @RequestMapping(value = "/insertProject", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> insertProject(
            @RequestParam(value = "user_id") String user_id,
            @RequestParam(value = "order_id") String order_id,
            @RequestParam(value = "category") String category,
            HttpServletRequest req
    ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        //生产新的Project对象，并对其赋值，尤其是ID
        T_Project t_project = new T_Project();
        String project_name = "项目名称：待命名";
        t_project.setName(project_name);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        t_project.setTs(ts);
        t_project.setCategory(category);
        t_project.setStatus("进行中");
        //要保证有唯一的Project id，这里通过MD5结合固定字符串"mea"的方法来大概率保证id唯一。
        String project_id = "pro" + md5Service.encodeByMD5(project_name + category + ts);
        t_project.setId(project_id);

        //生产新的订单项，就是将订单id，用户id，item的id插入到订单项表中。
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(project_id);
        orderItem.setItem_class("projects");
        orderItem.setStart_time(ts);
        try {
            //更新Project表，向Project表中插入相关记录。
            projectService.insertProject(t_project);
            //已经走到施工工程这一阶段，需要更新订单状态到7 。
            Order order = new Order();
            order.setId(order_id);
            order.setUser_id(user_id);
            order.setStatus("7");
            orderService.updateOrderStatus(order);
            //根据订单id和project id，将该测量项加入order item表
            orderItemService.insertOrderItem(orderItem);
        } catch (RuntimeException re) {
            System.out.println(re);
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
