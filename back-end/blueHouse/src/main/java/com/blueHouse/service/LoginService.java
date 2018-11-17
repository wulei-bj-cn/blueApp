package com.blueHouse.service;

/**
 * Created by lihan on 2018/11/17.
 */
public interface LoginService {
    String getPasswordByUser(String user);
    String getPrivilegeByUser(String user);
    String getNameByUser(String user);
    String getStatusByUser(String user);
    String getPermissionsByUser(String user);
    boolean loginCheck(String user,String password);
    boolean permissionCheck(String user,int permission);
}
