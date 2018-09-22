package com.blueHouse.mapper;

/**
 * Created by lihan on 11/08/2018.
 */

import com.blueHouse.pojo.Activity;

import java.util.List;

public interface ActivityMapper {
    Activity findActivityById(String id);
    List<Activity> findActivityByPartialName(String name);
    List<Activity> findAllActivity();
    void insertActivity(Activity act);
    void updateActivity(Activity act);
}
