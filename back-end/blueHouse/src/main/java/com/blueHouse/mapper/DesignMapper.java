package com.blueHouse.mapper;

import com.blueHouse.pojo.browse.T_Design;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface DesignMapper {
    T_Design findDesignById(String id);
    List<T_Design> findDesignByUseName(String name);
    List<T_Design> finaDesignByDesignerName(String name);
    void insertDesign(T_Design design);
    void updateDesign(T_Design design);
    void deleteDesign(T_Design design);
}
