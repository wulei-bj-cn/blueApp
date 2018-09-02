package com.blueHouse.test;

import com.blueHouse.pojo.Admin;
import com.blueHouse.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestAdminService {
    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findAdminById() throws Exception {
        AdminService adminService= (AdminService) applicationContext.getBean("adminService");
        Admin admin = adminService.findAdminById("adm1");
        System.out.println(admin.getId());
        System.out.println(admin.getLast_log_on());
    }

    @Test
    public void findAllAdmin() throws Exception{
        AdminService adminService= (AdminService) applicationContext.getBean("adminService");
        List<Admin> admins = adminService.findAllAdmin();
        for(Admin admin:admins){
            System.out.println(admin.getId());
            System.out.println(admin.getLogin());
        }
    }
}
