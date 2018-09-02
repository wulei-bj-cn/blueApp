package com.blueHouse.service;

import com.blueHouse.pojo.orders.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by lihan on 2018/9/1.
 */
public class ProjectServiceImpl implements ProjectService {
    @Resource
    @Autowired
    private ProjectService projectService;

    @Override
    public Project findProjectById(String id) {
        return projectService.findProjectById(id);
    }

    @Override
    public void insertProject(Project project) {
        projectService.insertProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectService.updateProject(project);
    }
}
