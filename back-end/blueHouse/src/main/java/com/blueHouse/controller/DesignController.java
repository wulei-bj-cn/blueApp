package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Design;
import com.blueHouse.service.DesignService;
import com.blueHouse.utils.TimeStampUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihan on 2018/10/26.
 */
@Controller
@RequestMapping("/design")
public class DesignController  {

    @Resource
    private DesignService designService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllDesigns(ModelMap modelMap){
        List<T_Design> designs = designService.findAllDesign();
        modelMap.put("designs", designs);
        modelMap.put("designsCount", designs.size());
        modelMap.put("isSearching", false);
        return "designs";
    }

    @RequestMapping(value = "/searchDesigns" , method = RequestMethod.GET)
    public String searchDesigns(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /design/getAll";
        } else {
            List<T_Design> designs = designService.findDesignByT_DesignerName(searchKey);
            modelMap.put("searchKey", searchKey);
            modelMap.put("designsCount", designs.size());
            modelMap.put("searchDesigns", designs);
            modelMap.put("isSearching", true);
            return "designs";

        }
    }

    @RequestMapping(value = "/updateDesign", method = RequestMethod.GET)
    public String updateDesign(HttpServletRequest req) {
        String designId = req.getParameter("designId");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String ts = req.getParameter("ts");
        String designer = req.getParameter("designer");
        String status = req.getParameter("status");

        Timestamp tstime = null;

        try{
            tstime = TimeStampUtil.strToSqlDate(ts,"yyyy-MM-dd HH:mm");
        }catch (Exception e){
            e.printStackTrace();
        }

        T_Design newDesign = new T_Design();

        newDesign.setId(designId);
        newDesign.setName(name);
        newDesign.setTs(tstime);
        newDesign.setUrl(url);
        newDesign.setStatus(status);
        newDesign.setDesigner(designer);

        designService.updateDesign(newDesign);

        return "redirect: /design/getAll";
    }

    @RequestMapping(value = "/insertDesign", method = RequestMethod.GET)
    public String insertDesign(HttpServletRequest req) {
        String designId = req.getParameter("designId");
        String name = req.getParameter("name");
        String url = req.getParameter("url");
        String ts = req.getParameter("ts");
        String designer = req.getParameter("designer");
        String status = req.getParameter("status");

        Timestamp tstime = null;

        try{
            tstime = TimeStampUtil.strToSqlDate(ts,"yyyy-MM-dd HH:mm");
        }catch (Exception e){
            e.printStackTrace();
        }

        T_Design newDesign = new T_Design();

        newDesign.setId(designId);
        newDesign.setName(name);
        newDesign.setTs(tstime);
        newDesign.setUrl(url);
        newDesign.setStatus(status);
        newDesign.setDesigner(designer);

        designService.insertDesign(newDesign);

        return "redirect: /design/getAll";
    }
}
