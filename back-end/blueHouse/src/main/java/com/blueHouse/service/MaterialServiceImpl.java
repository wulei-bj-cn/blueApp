package com.blueHouse.service;

import com.blueHouse.pojo.Material;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by lihan on 2018/9/1.
 */
public class MaterialServiceImpl implements MaterialService {
    @Resource
    @Autowired
    private MaterialService materialService;

    @Override
    public Material finaMaterialById(String id) {
        return materialService.finaMaterialById(id);
    }

    @Override
    public void insertMaterial(Material material) {
        materialService.insertMaterial(material);
    }

    @Override
    public void updateMaterial(Material material) {
        materialService.updateMaterial(material);
    }
}
