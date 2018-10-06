package com.blueHouse.service;

import com.blueHouse.mapper.BrandMapper;
import com.blueHouse.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class BrandServiceImpl implements BrandService {
    @Resource
    @Autowired
    private BrandMapper brandMapper;

    public Brand findBrandById(String id) { return brandMapper.findBrandById(id); }

    public Brand findBrandByBrand(String brand) { return brandMapper.findBrandByBrand(brand); }

    public List<Brand> findAllBrands() { return brandMapper.findAllBrands(); }

    public void insertBrand(Brand brand) { brandMapper.insertBrand(brand); }

    public void updateBrand(Brand brand) { brandMapper.updateBrand(brand); }

    public void deleteBrand(Brand brand) { brandMapper.deleteBrand(brand); }
}
