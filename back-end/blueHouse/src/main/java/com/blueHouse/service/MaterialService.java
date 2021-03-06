package com.blueHouse.service;

import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.pojo.orders.Material;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface MaterialService {
    T_Material finaMaterialById(String id);
    List<T_Material> findMaterialByPartialName(String name);
    List<T_Material> findMaterialByBrand(String name);
    List<T_Material> findMaterialByCategory(String name);
    List<T_Material> findAllMaterials();
    void insertMaterial(T_Material material);
    void updateMaterial(T_Material material);
    void deleteMaterial(T_Material material);
}
