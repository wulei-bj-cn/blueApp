package com.blueHouse.service;

import com.blueHouse.mapper.ProjectMapper;
import com.blueHouse.pojo.browse.T_Project;
import com.blueHouse.pojo.orders.Project;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class ProjectServiceImpl implements ProjectService {
    @Resource
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public T_Project findProjectById(String id) {return projectMapper.findProjectById(id); }

    @Override
    public List<T_Project> findProjectByName(String name) {
        return projectMapper.findProjectByName(name);
    }

    @Override
    public List<T_Project> findAllProjects() {
        return projectMapper.findAllProjects();
    }

    @Override
    public void insertProject(T_Project project) {
        projectMapper.insertProject(project);
    }

    @Override
    public void updateProject(T_Project project) {
        projectMapper.updateProject(project);
    }

    @Override
    public void deleteProject(T_Project project) { projectMapper.deleteProject(project); }
}
