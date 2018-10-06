package com.blueHouse.service;

import com.blueHouse.pojo.Brand;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface BrandService {
    Brand findBrandById(String id);
    Brand findBrandByBrand(String brand);
    List<Brand> findAllBrands();
    void insertBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(Brand brand);
}
