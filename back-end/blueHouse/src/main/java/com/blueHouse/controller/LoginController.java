package com.blueHouse.controller;

import com.blueHouse.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihan on 2018/11/17.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    LoginService loginService;

    @RequestMapping(value = "/logins", method = RequestMethod.GET)
    public String login(HttpServletRequest req,ModelMap modelMap) {
        HttpSession session = req.getSession();
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus!= null && loginStatus.equals("1")){
            String user = (String) req.getAttribute("user");
            modelMap.put("loginStatus","1");
            modelMap.put("user",user);
        }else{
            modelMap.put("loginStatus","-1");
        }
        return "logins";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn(HttpServletRequest req, ModelMap modelMap) {
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if(! loginService.loginCheck(user,password)){
            modelMap.put("loginStatus","0");
            session.setAttribute("loginStatus","0");
            return "logins";
        }else{
            modelMap.put("user",user);
            modelMap.put("loginStatus","1");
            session.setAttribute("user",user);
            session.setAttribute("loginStatus","1");
            return "console";
        }
    }
}
