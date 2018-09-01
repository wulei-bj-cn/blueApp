package com.blueHouse.service;

import com.blueHouse.pojo.Activity;

import java.util.List;

/**
 * Created by lihan on 2018/8/11.
 */
public interface ActivityService {
    Activity findActivityById(int id);
    List<Activity> findActivityByPartialId(int id);
    List<Activity> findAllActivity();
    void insertActivity(Activity act);
    void updateActivity(Activity act);
}
