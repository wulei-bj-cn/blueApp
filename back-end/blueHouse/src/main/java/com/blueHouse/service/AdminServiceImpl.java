package com.blueHouse.service;

import com.blueHouse.mapper.ActivityMapper;
import com.blueHouse.mapper.AdminMapper;
import com.blueHouse.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class AdminServiceImpl implements AdminService {

    @Resource
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminById(int id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public List<Admin> findAdminByPartialId(int id) {
        return adminMapper.findAdminByPartialId(id);
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminMapper.findAllAdmin();
    }

    @Override
    public List<Admin> findAdminByNameOrID(String name) {
        return adminMapper.findAdminByNameOrID(name);
    }

    @Override
    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);

    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }
}
