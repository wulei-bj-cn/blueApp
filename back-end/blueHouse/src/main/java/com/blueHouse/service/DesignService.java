package com.blueHouse.service;

import com.blueHouse.pojo.Design;
import com.sun.tools.javac.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface DesignService {
    Design findDesignById(String id);
    List<Design> findDesignByUseName(String name);
    List<Design> finaDesignByDesignerName(String name);
    void insertDesign(Design design);
    void updateDesign(Design design);
}
