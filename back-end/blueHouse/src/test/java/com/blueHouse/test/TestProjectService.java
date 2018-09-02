package com.blueHouse.test;

import com.blueHouse.pojo.orders.Project;
import com.blueHouse.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestProjectService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findProjectById() throws Exception {
        ProjectService projectService= (ProjectService) applicationContext.getBean("projectService");
        Project project = projectService.findProjectById("pro1");
        System.out.println(project.getId());
    }
}
