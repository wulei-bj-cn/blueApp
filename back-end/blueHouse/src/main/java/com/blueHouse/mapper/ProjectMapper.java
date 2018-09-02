package com.blueHouse.mapper;

import com.blueHouse.pojo.Project;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ProjectMapper {
    Project  findProjectById(String id);
    void insertProject(Project project);
    void updateProject(Project project);
}
