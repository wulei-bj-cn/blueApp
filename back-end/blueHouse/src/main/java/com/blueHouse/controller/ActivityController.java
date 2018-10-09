package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Activity;
import com.blueHouse.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllActivities(ModelMap modelMap){
        List<T_Activity> activities = activityService.findAllActivity();
        modelMap.put("activities", activities);
        modelMap.put("activitiesCount", activities.size());
        modelMap.put("isSearching", false);
        return "activities";
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
            tsStart = strToSqlDate(startTime,"yyyy-MM-dd HH:mm");
            tsEnd = strToSqlDate(endTime,"yyyy-MM-dd HH:mm");
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
            tsStart = strToSqlDate(startTime,"yyyy-MM-dd HH:mm");
            tsEnd = strToSqlDate(endTime,"yyyy-MM-dd HH:mm");
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

        activityService.insertActivity(newActivity);

        return "redirect: /activity/getAll";
    }
    /**
     * 将java.sql.Timestamp对象转化为String字符串
     * @param time
     *            要格式的java.sql.Timestamp对象
     * @param strFormat
     *            输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return 表示日期的字符串
     */
    public static String dateToStr(java.sql.Timestamp time, String strFormat) {
        DateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }

    /**
     * 将String字符串转换为java.sql.Timestamp格式日期,用于数据库保存
     * @param strDate
     *            表示日期的字符串
     * @param dateFormat
     *            传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.sql.Timestamp类型日期对象（如果转换失败则返回null）
     */
    public static java.sql.Timestamp strToSqlDate(String strDate, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        java.util.Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        return dateSQL;
    }
}
