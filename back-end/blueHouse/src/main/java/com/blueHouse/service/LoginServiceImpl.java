package com.blueHouse.service;

import com.blueHouse.mapper.AdminMapper;
import com.blueHouse.mapper.LoginMapper;
import com.blueHouse.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lihan on 2018/11/17.
 */
public class LoginServiceImpl implements LoginService{
    @Resource
    @Autowired
    private AdminMapper adminMapper;

    @Resource
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String getPasswordByUser(String user) {
        Admin admin = adminMapper.findAdminByLoginUser(user);
        if(admin == null ){
            return null;
        }
        return admin.getPassword();
    }

    @Override
    public String getPrivilegeByUser(String user) {
        Admin admin = adminMapper.findAdminByLoginUser(user);
        if(admin == null ){
            return null;
        }
        return admin.getRole();
    }

    @Override
    public String getNameByUser(String user) {
        Admin admin = adminMapper.findAdminByLoginUser(user);
        if(admin == null ){
            return null;
        }
        return admin.getName();
    }

    @Override
    public String getStatusByUser(String user) {
        Admin admin = adminMapper.findAdminByLoginUser(user);
        if(admin == null ){
            return null;
        }
        return admin.getStatus();
    }

    @Override
    public String getPermissionsByUser(String user) {
        return loginMapper.getLoginPermissions(user);
    }

    @Override
    public boolean loginCheck(String user, String password) {
        if (this.getStatusByUser(user) == null ||  ! this.getStatusByUser(user).equals("active")){
            return false;
        }
        return this.getPasswordByUser(user).equals(password);
    }

    @Override
    public boolean permissionCheck(String user, int permission) {
        if (this.getStatusByUser(user) == null || ! this.getStatusByUser(user).equals("active")){
            return false;
        }
        String pers = this.getPermissionsByUser(user);
        if(pers == null ){
            return false;
        }
        String[] perArr = pers.split("|");
        List list = Arrays.asList(perArr);
        return list.contains(String.valueOf(permission));
    }
}
