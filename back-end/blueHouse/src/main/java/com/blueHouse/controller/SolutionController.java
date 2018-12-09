package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Solution;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.SolutionService;
import com.blueHouse.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lihan on 2018/10/26.
 */
@Controller
@RequestMapping("/solution")
public class SolutionController {

    @Resource
    private SolutionService solutionService;

    @Resource
    private LoginService loginService;

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");

    private int PAGEPERMISSIONCODE = 5;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllSolutions(HttpServletRequest req,ModelMap modelMap){
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<T_Solution> solutions = solutionService.findAllSolutions();
            modelMap.put("solutions", solutions);
            modelMap.put("solutionsCount", solutions.size());
            modelMap.put("isSearching", false);
            return "solutions";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchSolutions" , method = RequestMethod.GET)
    public String searchSolutions(HttpServletRequest req, ModelMap modelMap) {
        String searchKey = req.getParameter("searchKey");
        if (searchKey.isEmpty()) {
            modelMap.put("isSearching", false);
            return "redirect: /solution/getAll";
        } else {
            List<T_Solution> solutions = solutionService.findSolutionByName(searchKey);
            modelMap.put("searchKey", searchKey);
            modelMap.put("solutionsCount", solutions.size());
            modelMap.put("searchSolutions", solutions);
            modelMap.put("isSearching", true);
            return "solutions";

        }
    }

    @RequestMapping(value = "/updateSolution", method = RequestMethod.GET)
    public String updateSolution(HttpServletRequest req) {
        String solutionId = req.getParameter("solutionId");
        String name = req.getParameter("name");
        String cover = req.getParameter("cover");
        String url = req.getParameter("url");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String status = req.getParameter("status");

        T_Solution newSolution = new T_Solution();

        newSolution.setId(solutionId);
        newSolution.setName(name);
        newSolution.setCover(cover);
        newSolution.setTs(ts);
        newSolution.setUrl(url);
        newSolution.setStatus(status);

        solutionService.updateSolution(newSolution);

        return "redirect: /solution/getAll";
    }

    @RequestMapping(value = "/insertSolution", method = RequestMethod.GET)
    public String insertSolution(HttpServletRequest req) {

        String name = req.getParameter("name");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String solutionId = "sol" + md5Service.encodeByMD5(name + ts);
        String status = req.getParameter("status");

        T_Solution newSolution = new T_Solution();

        newSolution.setId(solutionId);
        newSolution.setName(name);
        newSolution.setTs(ts);
        newSolution.setStatus(status);

        solutionService.insertSolution(newSolution);

        return "redirect: /solution/getAll";
    }
}
