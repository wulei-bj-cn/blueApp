package com.blueHouse.service;

import com.blueHouse.pojo.browse.T_Activity;

import java.util.List;

/**
 * Created by lihan on 2018/8/11.
 */
public interface ActivityService {
    T_Activity findActivityById(String id);
    List<T_Activity> findActivityByPartialName(String name);
    List<T_Activity> findAllActivity();
    void insertActivity(T_Activity act);
    void updateActivity(T_Activity act);
}
