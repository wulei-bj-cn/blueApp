package com.blueHouse.service;

import com.blueHouse.mapper.MeasureMapper;
import com.blueHouse.pojo.orders.Measure;
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
    public Measure findMeasureById(String id) {
        return measureMapper.findMeasureById(id);
    }

    @Override
    public List<Measure> findMeasureByUserName(String name) {
        return measureMapper.findMeasureByUserName(name);
    }

    @Override
    public List<Measure> findMeasureByCrewName(String name) {
        return measureMapper.findMeasureByCrewName(name);
    }

    @Override
    public void insertMeasure(Measure measure) {
        measureMapper.insertMeasure(measure);
    }

    @Override
    public void updateMeasure(Measure measure) {
        measureMapper.insertMeasure(measure);
    }

    @Override
    public void deleteMeasure(Measure measure) { measureMapper.deleteMeasure(measure); }
}
