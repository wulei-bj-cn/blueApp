package com.blueHouse.controller;

import com.blueHouse.pojo.browse.T_Project;
import com.blueHouse.service.LoginService;
import com.blueHouse.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by lihan on 2018/10/3.
 */

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 4;

    @RequestMapping(value = "/getAll"  , method = RequestMethod.GET)
    public String getAllProjects(HttpServletRequest req,ModelMap modelMap) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<T_Project> projects = projectService.findAllProjects();
            modelMap.put("projects", projects);
            modelMap.put("projectsCount", projects.size());
            modelMap.put("isSearching", false);

            return "projects";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchProjects" , method = RequestMethod.GET)
    public String searchProjects(HttpServletRequest request ,ModelMap modelMap){
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")) {
            if (loginService.permissionCheck(user, PAGEPERMISSIONCODE)) {
                modelMap.put("permissionCode", true);
            } else {
                modelMap.put("permissionCode", false);
            }
            String searchKey = request.getParameter("searchKey");
            if (searchKey.isEmpty()) {
                modelMap.put("isSearching", false);
                return "redirect: /project/getAll";
            } else {
                List<T_Project> projects = projectService.findProjectByName(searchKey);
                modelMap.put("projects", projects);
                modelMap.put("projectsCount", projects.size());
                modelMap.put("isSearching", true);
                modelMap.put("searchKey", searchKey);
                return "projects";
            }
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/updateProject" , method = RequestMethod.GET)
    public String updateProject(HttpServletRequest request ){
        T_Project project = this.createProjectFromRequest(request);
        projectService.updateProject(project);

        return "redirect: /project/getAll";
    }

    @RequestMapping(value = "/insertProject" , method = RequestMethod.GET)
    public String insertProject(HttpServletRequest request ){
        T_Project project = this.createProjectFromRequest(request);
        projectService.insertProject(project);
        return "redirect: /project/getAll";
    }


    private T_Project createProjectFromRequest(HttpServletRequest request){
        String id = request.getParameter("projectId");
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String enable = request.getParameter("enabled");
        String category = request.getParameter("category");

        int is_enable = 0;

        try{
            is_enable = Integer.parseInt(enable);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }

        T_Project project = new T_Project();
        project.setCategory(category);
        project.setEnabled(is_enable);
        project.setId(id);
        project.setName(name);
        project.setUrl(url);

        return project;
    }
}
