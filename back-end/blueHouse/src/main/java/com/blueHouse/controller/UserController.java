package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.blueHouse.pojo.User;
import com.blueHouse.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        //ModelAndView mav = new ModelAndView("users");
        List<User> users = userService.findAllUsers();
        //List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(22);
        user1.setName("Yawei");
        users.add(user1);
        for(User user:users){
            System.out.println(user.getName());
        }
        modelMap.put("users",users);
        //mav.addObject("users", users);
        return "index";
    }

}
