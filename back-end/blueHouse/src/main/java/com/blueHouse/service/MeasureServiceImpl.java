package com.blueHouse.service;

import com.blueHouse.mapper.MeasureMapper;
import com.blueHouse.pojo.browse.T_Measure;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class MeasureServiceImpl implements MeasureService {
    @Resource
    @Autowired
private MeasureMapper measureMapper;

    @Override
    public T_Measure findMeasureById(String id) {
        return measureMapper.findMeasureById(id);
    }

    @Override
    public List<T_Measure> findMeasureByUserName(String name) {
        return measureMapper.findMeasureByUserName(name);
    }

    @Override
    public List<T_Measure> findMeasureByCrewName(String name) {
        return measureMapper.findMeasureByCrewName(name);
    }

    @Override
    public void insertMeasure(T_Measure measure) {
        measureMapper.insertMeasure(measure);
    }

    @Override
    public void updateMeasure(T_Measure measure) {
        measureMapper.updateMeasure(measure);
    }

    @Override
    public void deleteMeasure(T_Measure measure) { measureMapper.deleteMeasure(measure); }
}
