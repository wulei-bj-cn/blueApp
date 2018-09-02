package com.blueHouse.service;

import com.blueHouse.pojo.OrderItems;
import com.blueHouse.pojo.Project;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ProjectService {
    Project findProjectById(String id);
    void insertProject(Project project);
    void updateProject(Project project);
}
