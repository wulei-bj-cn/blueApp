package com.blueHouse.service;


import com.blueHouse.pojo.browse.T_Project;
import com.blueHouse.pojo.orders.Project;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ProjectService {
    T_Project findProjectById(String id);
    List<T_Project> findProjectByName(String name );
    List<T_Project> findAllProjects();
    void insertProject(T_Project project);
    void updateProject(T_Project project);
    void deleteProject(T_Project project);
}
