package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Activity;
import com.blueHouse.service.ActivityService;
import com.blueHouse.service.LoginService;
import com.blueHouse.utils.TimeStampUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lihan on 2018/9/8.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 3;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllActivities(HttpServletRequest req, ModelMap modelMap){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<T_Activity> activities = activityService.findAllActivity();
            modelMap.put("activities", activities);
            modelMap.put("activitiesCount", activities.size());
            modelMap.put("isSearching", false);
            return "activities";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchActivities" , method = RequestMethod.GET)
    public String searchActivities(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /activity/getAll";
        } else {
            List<T_Activity> activities = activityService.findActivityByPartialName(searchKey);
            modelMap.put("searchKey", searchKey);
            modelMap.put("activitiesCount", activities.size());
            modelMap.put("searchActivities", activities);
            modelMap.put("isSearching", true);
            return "activities";

        }
    }

    @RequestMapping(value = "/updateActivity", method = RequestMethod.GET)
    public String updateActivity(HttpServletRequest req) {
        String des = req.getParameter("des");
        String url = req.getParameter("url");
        String activityID = req.getParameter("activityID");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        Timestamp tsStart = null;
        Timestamp tsEnd = null;

        try{
            tsStart = TimeStampUtil.strToSqlDate(startTime,"yyyy-MM-dd HH:mm");
            tsEnd = TimeStampUtil.strToSqlDate(endTime,"yyyy-MM-dd HH:mm");
            //tsStart = Timestamp.valueOf(startTime);
            //tsEnd = Timestamp.valueOf(endTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        T_Activity newActivity = new T_Activity();

        newActivity.setId(activityID);
        newActivity.setDes(des);
        newActivity.setUrl(url);
        newActivity.setStart_time(tsStart);
        newActivity.setEnd_time(tsEnd);

        activityService.updateActivity(newActivity);

        return "redirect: /activity/getAll";
    }

    @RequestMapping(value = "/insertActivity", method = RequestMethod.GET)
    public String insertActivity(HttpServletRequest req) {
        String des = req.getParameter("des");
        String url = req.getParameter("url");
        String activityID = req.getParameter("activityID");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");

        Timestamp tsStart = null;
        Timestamp tsEnd = null;

        try{
            tsStart = TimeStampUtil.strToSqlDate(startTime,"yyyy-MM-dd HH:mm");
            tsEnd = TimeStampUtil.strToSqlDate(endTime,"yyyy-MM-dd HH:mm");
        }catch (Exception e){
            e.printStackTrace();
        }
        T_Activity newActivity = new T_Activity();

        newActivity.setId(activityID);
        newActivity.setDes(des);
        newActivity.setUrl(url);
        newActivity.setStart_time(tsStart);
        newActivity.setEnd_time(tsEnd);

        activityService.insertActivity(newActivity);

        return "redirect: /activity/getAll";
    }

}
