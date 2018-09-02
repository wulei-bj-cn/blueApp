package com.blueHouse.service;

import com.blueHouse.mapper.ProjectMapper;
import com.blueHouse.pojo.orders.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by lihan on 2018/9/1.
 */
public class ProjectServiceImpl implements ProjectService {
    @Resource
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Project findProjectById(String id) {return projectMapper.findProjectById(id); }

    @Override
    public void insertProject(Project project) {
        projectMapper.insertProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateProject(project);
    }
}
