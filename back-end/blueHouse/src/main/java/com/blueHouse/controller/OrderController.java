package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.orders.*;
import com.blueHouse.service.OMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OMService omService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllOrders(ModelMap modelMap) {
        System.out.println("======Hey, SOB, I'm in /order/getAll ======");
        List<Order> orders = omService.findAllOrders();
        List<OrderItems> orderItems = new ArrayList();
        if (orders != null) {
            for (Order order: orders) {
                String user_id = order.getUser_id();
                String order_id = order.getId();
                List<Measure> measures = omService.findMeasure(user_id, order_id);
                List<Contract> contracts = omService.findContract(user_id, order_id);
                List<Design> designs = omService.findDesign(user_id, order_id);
                List<Disclaim> disclaims = omService.findDisclaim(user_id, order_id);
                List<Project> projects = omService.findProject(user_id, order_id);
                orderItems.add(
                        new OrderItems(user_id, order_id, measures, contracts, designs, disclaims, projects));
            }
        }
        modelMap.put("orderItems", orderItems);
        modelMap.put("ordersCount", orders.size());
        modelMap.put("isSearching", false);
        return "orders";
    }

    @RequestMapping(value = "/uploadMeasure")
    public String uploadMeasureFile(@RequestParam("measure_file") MultipartFile measureFile, HttpServletRequest request) {
        System.out.println("======Hey, SOB, I'm in /order/uploadMeasure ======");
        String filename = "YPP-007.jpg";
        String targetPath = request.getContextPath() + "/resources/img/measures/" + filename;
        File file = new File("/Users/wulei/" + filename);
        System.out.println("=======Uploading MEASURE file=======");
        try {
            measureFile.transferTo(file);
        } catch (IOException ex) {
            System.out.println("IO exception detected when uploading Blue House MEASURE files!");
        }
        System.out.println("=======DONE Uploading MEASURE file=======");

        return "orders";
    }

}
