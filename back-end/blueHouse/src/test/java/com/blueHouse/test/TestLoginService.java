package com.blueHouse.test;

import com.blueHouse.pojo.Admin;
import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/11/17.
 */
public class TestLoginService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findMaterialById() throws Exception {
        LoginService loginService = (LoginService) applicationContext.getBean("loginService");
        String user  = "wulei";
        String password  = loginService.getPasswordByUser(user);
        System.out.println(password);
        String priv = loginService.getPrivilegeByUser(user);
        System.out.println(priv);
        String name = loginService.getNameByUser(user);
        System.out.println(name);
        String status = loginService.getStatusByUser(user);
        System.out.println(status);
        String permiss = loginService.getPermissionsByUser(user);
        System.out.println(permiss);
        boolean l = loginService.loginCheck(user,password);
        System.out.println(l);
        boolean p = loginService.permissionCheck(user,1);
        System.out.println(p);
        //  System.out.println(loginService.loginCheck("wulei",password));
    }

}
