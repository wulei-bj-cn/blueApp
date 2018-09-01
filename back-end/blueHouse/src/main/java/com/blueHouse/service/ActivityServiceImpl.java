package com.blueHouse.service;

import com.blueHouse.mapper.ActivityMapper;
import com.blueHouse.pojo.Activity;
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
    public Activity findActivityById(int id) {
        return activityMapper.findActivityById(id);
    }

    @Override
    public List<Activity> findActivityByPartialId(int id) {
        return activityMapper.findActivityByPartialId(id);
    }

    @Override
    public List<Activity> findAllActivity() {
        return activityMapper.findAllActivity();
    }

    @Override
    public void insertActivity(Activity act) {
        activityMapper.insertActivity(act);
    }

    @Override
    public void updateActivity(Activity act) {
        activityMapper.updateActivity(act);
    }
}
