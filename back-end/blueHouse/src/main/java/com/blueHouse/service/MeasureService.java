package com.blueHouse.service;


import com.blueHouse.pojo.browse.T_Measure;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface MeasureService {
    T_Measure findMeasureById(String id);
    List<T_Measure> findMeasureByUserName(String name);
    List<T_Measure> findMeasureByCrewName(String name);
    void insertMeasure(T_Measure measure);
    void updateMeasure(T_Measure measure);
    void deleteMeasure(T_Measure measure);

}
