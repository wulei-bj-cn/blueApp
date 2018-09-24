package com.blueHouse.mapper;

/**
 * Created by lihan on 11/08/2018.
 */

import com.blueHouse.pojo.browse.T_Activity;

import java.util.List;

public interface ActivityMapper {
    T_Activity findActivityById(String id);
    List<T_Activity> findActivityByPartialName(String name);
    List<T_Activity> findAllActivity();
    void insertActivity(T_Activity act);
    void updateActivity(T_Activity act);
}
