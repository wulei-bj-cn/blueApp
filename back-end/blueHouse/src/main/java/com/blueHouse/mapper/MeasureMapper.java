package com.blueHouse.mapper;

import com.blueHouse.pojo.Measure;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface MeasureMapper {
    Measure findMeasureById(String id);
    List<Measure> findMeasureByUserName(String name);
    List<Measure> findMeasureByCrewName(String name);
    void insertMeasure(Measure measure);
    void updateMeasure(Measure measure);
}
