package com.blueHouse.service;

import com.blueHouse.mapper.DesignMapper;
import com.blueHouse.pojo.orders.Design;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class DesignServiceImpl implements DesignService{
    @Resource
    @Autowired
    private DesignMapper designMapper;

    @Override
    public Design findDesignById(String id) {
        return designMapper.findDesignById(id);
    }

    @Override
    public List<Design> findDesignByUseName(String name) {
        return designMapper.findDesignByUseName(name);
    }

    @Override
    public List<Design> finaDesignByDesignerName(String name) {
        return designMapper.finaDesignByDesignerName(name);
    }

    @Override
    public void insertDesign(Design design) {
        designMapper.insertDesign(design);
    }

    @Override
    public void updateDesign(Design design) {
        designMapper.updateDesign(design);
    }

    @Override
    public void deleteDesign(Design design) { designMapper.deleteDesign(design); }
}
