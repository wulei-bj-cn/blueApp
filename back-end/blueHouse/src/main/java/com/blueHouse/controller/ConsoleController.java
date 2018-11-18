package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/console")
public class ConsoleController {

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllConsoles(HttpServletRequest req) {
        HttpSession session = req.getSession();
        //String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            return "console";
        }else{
            return "redirect: /login/logins";
        }
    }

}
