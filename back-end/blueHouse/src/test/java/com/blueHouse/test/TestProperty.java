package com.blueHouse.test;

import com.blueHouse.mapper.UserMapper;
import com.blueHouse.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestProperty {

    @Test
    public void testProperty() throws Exception {
        Properties prop = null;

        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img_address = prop.getProperty("img_address");
        System.out.println(img_address);
    }

}
