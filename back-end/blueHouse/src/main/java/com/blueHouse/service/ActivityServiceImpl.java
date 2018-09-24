package com.blueHouse.service;

import com.blueHouse.mapper.ActivityMapper;
import com.blueHouse.pojo.browse.T_Activity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/8/11.
 */
public class ActivityServiceImpl implements  ActivityService{
    @Resource
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public T_Activity findActivityById(String id) {
        return activityMapper.findActivityById(id);
    }

    @Override
    public List<T_Activity> findActivityByPartialName(String name) {
        return activityMapper.findActivityByPartialName(name);
    }

    @Override
    public List<T_Activity> findAllActivity() {
        return activityMapper.findAllActivity();
    }

    @Override
    public void insertActivity(T_Activity act) {
        activityMapper.insertActivity(act);
    }

    @Override
    public void updateActivity(T_Activity act) {
        activityMapper.updateActivity(act);
    }
}
