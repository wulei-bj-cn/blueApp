package com.blueHouse.service;

import com.blueHouse.mapper.MaterialMapper;
import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.pojo.orders.Material;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class MaterialServiceImpl implements MaterialService {
    @Resource
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public T_Material finaMaterialById(String id) {
        return materialMapper.finaMaterialById(id);
    }

    @Override
    public List<T_Material> findMaterialByPartialName(String name) {
        return materialMapper.findMaterialByPartialName(name);
    }

    @Override
    public List<T_Material> findMaterialByBrand(String brand) {
        return materialMapper.findMaterialByBrand(brand);
    }

    @Override
    public List<T_Material> findMaterialByCategory(String category) {
        return materialMapper.findMaterialByCategory(category);
    }

    @Override
    public List<T_Material> findAllMaterials() {
        return materialMapper.findAllMaterials();
    }

    @Override
    public void insertMaterial(T_Material material) {
        materialMapper.insertMaterial(material);
    }

    @Override
    public void updateMaterial(T_Material material) {
        materialMapper.updateMaterial(material);
    }

    @Override
    public void deleteMaterial(T_Material material) { materialMapper.deleteMaterial(material); }
}
