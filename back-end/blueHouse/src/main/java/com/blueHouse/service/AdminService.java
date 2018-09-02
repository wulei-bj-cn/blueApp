package com.blueHouse.service;

import com.blueHouse.pojo.Admin;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface AdminService {
    Admin findAdminById(String id);
    List<Admin> findAllAdmin();
    List<Admin> findAdminByNameOrID(String name);
    void insertAdmin(Admin admin);
    void updateAdmin(Admin admin);
}
