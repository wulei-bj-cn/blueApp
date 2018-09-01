package com.blueHouse.service;

import com.blueHouse.mapper.ActivityMapper;
import com.blueHouse.pojo.Activity;

import java.util.List;

/**
 * Created by lihan on 2018/8/11.
 */
public class ActivityServiceImpl implements  ActivityService{
    private ActivityMapper activity;

    @Override
    public Activity findActivityById(int id) {
        return activity.findActivityById(id);
    }

    @Override
    public List<Activity> findActivityByPartialId(int id) {
        return activity.findActivityByPartialId(id);
    }

    @Override
    public List<Activity> findAllActivity() {
        return activity.findAllActivity();
    }

    @Override
    public void insertActivity(Activity act) {
        activity.insertActivity(act);
    }

    @Override
    public void updateActivity(Activity act) {
        activity.updateActivity(act);
    }
}
