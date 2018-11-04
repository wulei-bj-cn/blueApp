package com.blueHouse.controller;

import com.blueHouse.pojo.Admin;
import com.blueHouse.service.AdminService;
import com.blueHouse.utils.TimeStampUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihan on 2018/11/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllAdmins(ModelMap modelMap){
        List<Admin> admins = adminService.findAllAdmin();
        modelMap.put("admins", admins);
        modelMap.put("adminsCount", admins.size());
        modelMap.put("isSearching", false);
        return "admins";
    }

    @RequestMapping(value = "/searchAdmins" , method = RequestMethod.GET)
    public String searchAdmins(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /admin/getAll";
        } else {
            List<Admin> admins = adminService.findAdminByNameOrID(searchKey);
            modelMap.put("searchKey", searchKey);
            modelMap.put("adminsCount", admins.size());
            modelMap.put("searchAdmins", admins);
            modelMap.put("isSearching", true);
            return "admins";

        }
    }

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
    public String updateAdmin(HttpServletRequest req) {
        String adminId = req.getParameter("adminId");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String status = req.getParameter("status");
        String last_log_on = req.getParameter("last_log_on");
        Timestamp llo = null;
        try{
            llo = TimeStampUtil.strToSqlDate(last_log_on,"yyyy-MM-dd HH:mm");
            //tsStart = Timestamp.valueOf(startTime);
            //tsEnd = Timestamp.valueOf(endTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        Admin newAdmin = new Admin();

        newAdmin.setId(adminId);
        newAdmin.setLogin(login);
        newAdmin.setName(name);
        //newDesign.setTs();
        newAdmin.setPassword(password);
        newAdmin.setStatus(status);
        newAdmin.setRole(role);
        newAdmin.setLast_log_on(llo);

        adminService.updateAdmin(newAdmin);

        return "redirect: /admin/getAll";
    }

    @RequestMapping(value = "/insertAdmin", method = RequestMethod.GET)
    public String insertAdmin(HttpServletRequest req) {
        String adminId = req.getParameter("adminId");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String status = req.getParameter("status");
        String last_log_on = req.getParameter("last_log_on");
        Timestamp llo = null;
        try{
            llo = TimeStampUtil.strToSqlDate(last_log_on,"yyyy-MM-dd HH:mm:ss");
            //tsStart = Timestamp.valueOf(startTime);
            //tsEnd = Timestamp.valueOf(endTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        Admin newAdmin = new Admin();

        newAdmin.setId(adminId);
        newAdmin.setLogin(login);
        newAdmin.setName(name);
        newAdmin.setPassword(password);
        newAdmin.setStatus(status);
        newAdmin.setRole(role);
        newAdmin.setLast_log_on(llo);

        adminService.insertAdmin(newAdmin);

        return "redirect: /admin/getAll";
    }
}
