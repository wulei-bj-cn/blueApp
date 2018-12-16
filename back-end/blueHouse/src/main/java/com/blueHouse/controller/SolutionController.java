package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Solution;
import com.blueHouse.service.MD5Service;
import com.blueHouse.service.SolutionService;
import com.blueHouse.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")) {
            if (loginService.permissionCheck(user, PAGEPERMISSIONCODE)) {
                modelMap.put("permissionCode", true);
            } else {
                modelMap.put("permissionCode", false);
            }
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
        }else{
            return "redirect: /login/logins";
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

    @RequestMapping(value = "/uploadSolutionCover", method = RequestMethod.POST)
    public String uploadSolutionCover(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img_address = prop.getProperty("img_address");

        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String solution_id = request.getParameter("solution_id");
                    String targetPath = solution_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "solutions/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Solution ID:" + solution_id);
                        T_Solution t_solution = solutionService.findSolutionById(solution_id);
                        assert t_solution != null;
                        t_solution.setCover(targetPath);
                        try {
                            solutionService.updateSolution(t_solution);
                        } catch (Exception e) {
                            System.out.println("Exception caught: " + e.toString());
                        }
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "solutions";
    }

    @RequestMapping(value = "/uploadSolutionUrl", method = RequestMethod.POST)
    public String uploadSolutionUrl(@RequestParam("solution_files") MultipartFile[] solution_files, HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("conf/blueHouse.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img_address = prop.getProperty("img_address");

        if(multipartResolver.isMultipart(request))
        {

            String solution_id = request.getParameter("solution_id");
            System.out.println("==========Solution ID:" + solution_id);
            T_Solution t_solution = solutionService.findSolutionById(solution_id);
            assert t_solution != null;
            String url = "";

            for (int i = 0; i < solution_files.length; i++) {

                if(!solution_files[i].isEmpty()){

                    MultipartFile file = solution_files[i];
                    System.out.println("=====Origin name:" + file.getOriginalFilename());

                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    String picId = "sol_pic" + md5Service.encodeByMD5(solution_id + ts + i);
                    String targetPath = picId + ".jpeg";
                    url += targetPath + ",";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "solutions/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

            t_solution.setUrl(url);
            try {
                solutionService.updateSolution(t_solution);
            } catch (Exception e) {
                System.out.println("Exception caught: " + e.toString());
            }

        }

        return "solutions";
    }
}
