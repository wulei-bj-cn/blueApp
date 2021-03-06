package com.blueHouse.controller.services;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.User;
import com.blueHouse.service.UserService;
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
@RequestMapping("/user")
public class S_UserController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOrderListForUser(
            @RequestParam(value = "id") String user_id,
            HttpServletRequest req
            ) {

        Map<String, Object> map = new HashMap<String, Object>();
        boolean returnStatus = true;
        String message = "";
        int httpCode = 200;
        int error_type = 1;

        UserService userService = (UserService) applicationContext.getBean("userService");
        User user = userService.findUserById(user_id);

        map.put("status", returnStatus);
        map.put("message", message);
        map.put("code", httpCode);
        map.put("error_type", error_type);
        map.put("data", user);

        return map;
    }

}
