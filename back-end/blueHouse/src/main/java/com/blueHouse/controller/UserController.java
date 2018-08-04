package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */
import java.util.List;
import javax.annotation.Resource;

import com.blueHouse.pojo.User;
import com.blueHouse.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.findAllUsers();
        modelMap.put("users",users);
        modelMap.put("usersCount", users.size());
        return "users";
    }

}
