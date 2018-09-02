package com.blueHouse.service;

import com.blueHouse.pojo.Measure;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class MeasureServiceImpl implements MeasureService {
    @Resource
    @Autowired
    private MeasureService measureService;

    @Override
    public Measure findMeasureById(String id) {
        return measureService.findMeasureById(id);
    }

    @Override
    public List<Measure> findMeasureByUserName(String name) {
        return measureService.findMeasureByUserName(name);
    }

    @Override
    public List<Measure> findMeasureByCrewName(String name) {
        return measureService.findMeasureByCrewName(name);
    }

    @Override
    public void insertMeasure(Measure measure) {
        measureService.insertMeasure(measure);
    }

    @Override
    public void updateMeasure(Measure measure) {
        measureService.insertMeasure(measure);
    }
}
