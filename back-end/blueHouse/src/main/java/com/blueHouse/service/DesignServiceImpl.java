package com.blueHouse.service;

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
    private DesignService designService;

    @Override
    public Design findDesignById(String id) {
        return designService.findDesignById(id);
    }

    @Override
    public List<Design> findDesignByUseName(String name) {
        return designService.findDesignByUseName(name);
    }

    @Override
    public List<Design> finaDesignByDesignerName(String name) {
        return designService.finaDesignByDesignerName(name);
    }

    @Override
    public void insertDesign(Design design) {
        designService.insertDesign(design);
    }

    @Override
    public void updateDesign(Design design) {
        designService.updateDesign(design);
    }
}
