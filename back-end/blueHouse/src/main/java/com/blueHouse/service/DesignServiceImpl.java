package com.blueHouse.service;

import com.blueHouse.mapper.DesignMapper;
import com.blueHouse.pojo.browse.T_Design;
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
    public T_Design findDesignById(String id) {
        return designMapper.findDesignById(id);
    }

    @Override
    public List<T_Design> findDesignByUseName(String name) {
        return designMapper.findDesignByUseName(name);
    }

    @Override
    public List<T_Design> finaDesignByT_DesignerName(String name) {
        return designMapper.finaDesignByDesignerName(name);
    }

    @Override
    public void insertDesign(T_Design design) {
        designMapper.insertDesign(design);
    }

    @Override
    public void updateDesign(T_Design design) {
        designMapper.updateDesign(design);
    }

    @Override
    public void deleteDesign(T_Design design) { designMapper.deleteDesign(design); }
}
