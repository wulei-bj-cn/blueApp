package com.blueHouse.test;

import com.blueHouse.pojo.browse.T_Activity;
import com.blueHouse.service.ActivityService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lihan on 2018/8/25.
 */
public class TestTActivityService {
    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findActivityById() throws Exception {
        ActivityService activityService= (ActivityService) applicationContext.getBean("activityService");
        T_Activity activity = activityService.findActivityById("1");
        System.out.println(activity.getId());
    }

    @Test
    public void findActivityByPartialName() throws Exception {
        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        List<T_Activity> activity = activityService.findActivityByPartialName("act");
        for (T_Activity act: activity) {
            System.out.println(act.getDes());
        }
    }

    @Test
    public void findAllActivity() throws Exception {
        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        List<T_Activity> activity = activityService.findAllActivity();
        for (T_Activity act: activity) {
            System.out.println(act.getEnd_time());
        }
    }
    @Test
    public void updateActivity() throws Exception{
        T_Activity act = new T_Activity();
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
