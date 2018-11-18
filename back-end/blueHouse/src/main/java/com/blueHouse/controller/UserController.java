package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.blueHouse.pojo.Access;
import com.blueHouse.pojo.User;
import com.blueHouse.service.AccessService;
import com.blueHouse.service.LoginService;
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

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 1;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllUsers(HttpServletRequest req,ModelMap modelMap) {
        System.out.println("======Hey, SOB, I'm in /user/getAll ======");
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<User> users = userService.findAllUsers();
            List<Access> accesses = accessService.findAllAccesss();
            modelMap.put("users", users);
            modelMap.put("access", accesses);
            modelMap.put("usersCount", users.size());
            modelMap.put("accessCount", accesses.size());
            modelMap.put("isSearching", false);
            return "users";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public String searchUsers(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /user/getAll";
        } else {
            List<User> userByNameOrID = userService.findUserByNameOrID(searchKey);
            modelMap.put("searchKey", searchKey);
            modelMap.put("usersCount", userByNameOrID.size());
            modelMap.put("searchUsers", userByNameOrID);
            modelMap.put("isSearching", true);
            List<Access> accesses = accessService.findAllAccesss();
            modelMap.put("access", accesses);
            modelMap.put("accessCount", accesses.size());
            return "users";
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser(HttpServletRequest req) {
        String userName = req.getParameter("userName");
        int userAge = Integer.parseInt(req.getParameter("userAge"));
        String userPhone = req.getParameter("userPhone");
        String userAddress = req.getParameter("userAddress");
        String userID = req.getParameter("userID");

        User newUser = new User();
        newUser.setId(userID);
        newUser.setName(userName);
        newUser.setAge(userAge);
        newUser.setPhone(userPhone);
        newUser.setAddress(userAddress);

        userService.updateUser(newUser);

        return "redirect: /user/getAll";
    }
}
