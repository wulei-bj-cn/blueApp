package com.blueHouse.mapper;

import com.blueHouse.pojo.orders.Material;

/**
 * Created by lihan on 2018/9/1.
 */
public interface MaterialMapper {
    Material finaMaterialById(String id);
    Material findMaterialByPartialName(String name);
    void insertMaterial(Material material);
    void updateMaterial(Material material);
}
