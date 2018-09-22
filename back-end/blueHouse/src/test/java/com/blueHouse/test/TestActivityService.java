package com.blueHouse.test;

import com.blueHouse.pojo.Activity;
import com.blueHouse.service.ActivityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by lihan on 2018/8/25.
 */
public class TestActivityService {
    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findActivityById() throws Exception {
        ActivityService activityService= (ActivityService) applicationContext.getBean("activityService");
        Activity activity = activityService.findActivityById("1");
        System.out.println(activity.getId());
    }

    @Test
    public void findActivityByPartialName() throws Exception {
        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        List<Activity> activity = activityService.findActivityByPartialName("1");
        for (Activity act: activity) {
            System.out.println(act.getStart_time());
        }
    }

    @Test
    public void findAllActivity() throws Exception {
        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        List<Activity> activity = activityService.findAllActivity();
        for (Activity act: activity) {
            System.out.println(act.getEnd_time());
        }
    }
    @Test
    public void updateActivity() throws Exception{
        Activity act = new Activity();
        act.setDes("hahhao");
        act.setId("act1");
        String startTime = "2018-09-09 00:00:00";
        String endTime = "2018-09-10 00:00:00";
        act.setStart_time(java.sql.Timestamp.valueOf(startTime));
        act.setEnd_time(java.sql.Timestamp.valueOf(endTime));
        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        activityService.insertActivity(act);
    }
}
