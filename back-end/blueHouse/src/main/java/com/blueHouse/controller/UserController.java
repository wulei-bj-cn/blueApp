package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.blueHouse.pojo.Access;
import com.blueHouse.pojo.User;
import com.blueHouse.service.AccessService;
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

    @Resource
    private AccessService accessService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.findAllUsers();
        List<Access> accesses = accessService.findAllAccesss();
        modelMap.put("users", users);
        modelMap.put("access", accesses);
        modelMap.put("usersCount", users.size());
        modelMap.put("accessCount", accesses.size());
        modelMap.put("isSearching", false);
        return "users";
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public String searchUsers(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /user/getAll";
        } else {
            //List<User> usersByID = userService.findUserByPartialId((int)searchKey);
            List<User> usersByName = userService.findUserByName(searchKey);
            //modelMap.put("searchUsers", usersByID.addAll(usersByName));
            modelMap.put("searchKey", searchKey);
            modelMap.put("usersCount", usersByName.size());
            modelMap.put("searchUsers", usersByName);
            modelMap.put("isSearching", true);
            List<Access> accesses = accessService.findAllAccesss();
            modelMap.put("access", accesses);
            modelMap.put("accessCount", accesses.size());
            return "users";
        }
    }
}
