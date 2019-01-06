package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.orders.*;
import com.blueHouse.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cash")
public class CashController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    OrderService orderService = (OrderService) applicationContext.getBean("orderService");

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 2;

    @RequestMapping(value = "/confirmCash", method = RequestMethod.GET)
    public String searchUsers(@RequestParam(value = "cash_user_id") String user_id,
                              @RequestParam(value = "cash_order_id") String order_id,
                              @RequestParam(value = "cash_type") String cash_type,
                              HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (loginStatus != null && loginStatus.equals("1")) {
            if (loginService.permissionCheck(user, PAGEPERMISSIONCODE)) {
                modelMap.put("permissionCode", true);
            } else {
                modelMap.put("permissionCode", false);
            }

            System.out.println("=====Cash type: " + cash_type);
            System.out.println("=====Order id: " + order_id);
            //更新订单状态，标记正在确认提交定金
            Order order = orderService.findOrderById(order_id);
            if(cash_type == "DesignCash") {
                order.setStatus("21");
            } else if (cash_type == "ProjectCash") {
                order.setStatus("51");
            }

            try {
                //更新订单状态，标记正在确认提交定金
                orderService.updateOrderStatus(order);
                System.out.println("===== Order update done: " + order_id);
            } catch (RuntimeException re) {
                System.out.println(re.toString());
            }
        } else {
            return "redirect: /login/logins";
        }
        return "orders";
    }


}
