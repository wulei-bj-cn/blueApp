package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.*;
import com.blueHouse.service.OMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OMService omService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        List<Order> orders = omService.findAllOrders();
        List<OrderItems> orderItems = null;
        if (orders != null) {
            for (Order order: orders) {
                List<Measure> measures = omService.findMeasure(order.getUser_id(), order.getId());
                List<Contract> contracts = omService.findContract(order.getUser_id(), order.getId());
                List<Design> designs = omService.findDesign(order.getUser_id(), order.getId());
                List<Disclaim> disclaims = omService.findDisclaim(order.getUser_id(), order.getId());
                List<Project> projects = omService.findProject(order.getUser_id(), order.getId());
                orderItems.add(new OrderItems(measures, contracts, designs, disclaims, projects));
            }
        }
        modelMap.put("orderItems", orderItems);
        modelMap.put("ordersCount", orders.size());
        modelMap.put("isSearching", false);
        return "orders";
    }

}
