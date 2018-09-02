package com.blueHouse.mapper;

import com.blueHouse.pojo.orders.Design;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface DesignMapper {
    Design findDesignById(String id);
    List<Design> findDesignByUseName(String name);
    List<Design> finaDesignByDesignerName(String name);
    void insertDesign(Design design);
    void updateDesign(Design design);
}