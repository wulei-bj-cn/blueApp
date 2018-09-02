package com.blueHouse.service;

import com.blueHouse.pojo.Material;

/**
 * Created by lihan on 2018/9/1.
 */
public interface MaterialService {
    Material finaMaterialById(String id);
    void insertMaterial(Material material);
    void updateMaterial(Material material);
}
