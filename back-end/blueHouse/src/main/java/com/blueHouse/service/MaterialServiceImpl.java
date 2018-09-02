package com.blueHouse.service;

import com.blueHouse.mapper.MaterialMapper;
import com.blueHouse.pojo.orders.Material;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by lihan on 2018/9/1.
 */
public class MaterialServiceImpl implements MaterialService {
    @Resource
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public Material finaMaterialById(String id) {
        return materialMapper.finaMaterialById(id);
    }

    @Override
    public void insertMaterial(Material material) {
        materialMapper.insertMaterial(material);
    }

    @Override
    public void updateMaterial(Material material) {
        materialMapper.updateMaterial(material);
    }
}
