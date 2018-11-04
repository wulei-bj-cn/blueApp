package com.blueHouse.test;

import com.blueHouse.pojo.Admin;
import com.blueHouse.service.AdminService;
import com.blueHouse.utils.TimeStampUtil;
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
    @Test
    public void updateAdmin() throws Exception{
        Admin admin = new Admin();
        admin.setName("wulei");
        admin.setId("admin1");
        AdminService adminService= (AdminService) applicationContext.getBean("adminService");
        adminService.updateAdmin(admin);

    }
    @Test
    public void insertAdmin() throws Exception{
        Admin admin = new Admin();
        admin.setName("lihan3");
        admin.setId("adm5");
        admin.setLogin("lihan");
        admin.setPassword("123456");
        admin.setRole("admin");
        admin.setStatus("active");
        admin.setLast_log_on(TimeStampUtil.strToSqlDate("2018-01-01 18:10:00","yyyy-MM-dd HH:mm:ss"));
        AdminService adminService= (AdminService) applicationContext.getBean("adminService");
        adminService.insertAdmin(admin);

    }
}
