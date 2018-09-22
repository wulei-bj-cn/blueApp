package com.blueHouse.service;

import com.blueHouse.pojo.Activity;

import java.util.List;

/**
 * Created by lihan on 2018/8/11.
 */
public interface ActivityService {
    Activity findActivityById(String id);
    List<Activity> findActivityByPartialName(String name);
    List<Activity> findAllActivity();
    void insertActivity(Activity act);
    void updateActivity(Activity act);
}
