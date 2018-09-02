package com.blueHouse.test;

import com.blueHouse.pojo.browse.T_Material;
import com.blueHouse.service.BrowseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestBrowseService {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void browseAllMaterials() throws Exception {
        BrowseService browseService = (BrowseService) applicationContext.getBean("browseService");
        List<T_Material> materials = browseService.browseAllMaterials();
        for (T_Material material: materials) {
            System.out.println(
                    material.getId() + "\n" +
                            material.getId() + "\n" +
                            material.getName() + "\n" +
                            material.getCategory() + "\n" +
                            material.getClass_name() + "\n" +
                            material.getBrand() + "\n" +
                            material.getUrl() + "\n" +
                            material.getPrice()
            );
        }
    }

}
