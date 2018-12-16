package com.blueHouse.controller;

/**
 * Created by wulei on 27/07/2018.
 */

import com.blueHouse.pojo.User;
import com.blueHouse.pojo.browse.T_Design;
import com.blueHouse.pojo.browse.T_Measure;
import com.blueHouse.pojo.orders.*;
import com.blueHouse.service.*;
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
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/order")
public class OrderController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");

    MeasureService measureService = (MeasureService) applicationContext.getBean("measureService");
    DesignService designService = (DesignService) applicationContext.getBean("designService");
    MD5Service md5Service = (MD5Service) applicationContext.getBean("md5Service");
    OrderItemService orderItemService = (OrderItemService) applicationContext.getBean("orderItemService");
    UserService userService = (UserService) applicationContext.getBean("userService");

    @Resource
    private OMService omService;

    @Resource
    private LoginService loginService;

    private int PAGEPERMISSIONCODE = 2;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllOrders(HttpServletRequest req,ModelMap modelMap) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if(loginStatus != null && loginStatus.equals("1")){
            if(loginService.permissionCheck(user,PAGEPERMISSIONCODE)){
                modelMap.put("permissionCode", true);
            }else{
                modelMap.put("permissionCode", false);
            }
            List<Order> orders = omService.findAllOrders();
            List<OrderItems> orderItems = new ArrayList();
            if (orders != null) {
                for (Order order: orders) {
                    String user_id = order.getUser_id();
                    User pojoUser = userService.findUserById(user_id);
                    String order_id = order.getId();
                    List<Measure> measures = omService.findMeasure(user_id, order_id);
                    List<Contract> contracts = omService.findContract(user_id, order_id);
                    List<Design> designs = omService.findDesign(user_id, order_id);
                    List<Disclaim> disclaims = omService.findDisclaim(user_id, order_id);
                    List<Project> projects = omService.findProject(user_id, order_id);
                    orderItems.add(
                            new OrderItems(pojoUser, order, measures, contracts, designs, disclaims, projects));
                }
            }
            modelMap.put("orderItems", orderItems);
            modelMap.put("ordersCount", orders.size());
            modelMap.put("isSearching", false);
            return "orders";
        }else{
            return "redirect: /login/logins";
        }
    }

    @RequestMapping(value = "/searchOrders", method = RequestMethod.GET)
    public String searchUsers(HttpServletRequest req, ModelMap modelMap) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (loginStatus != null && loginStatus.equals("1")) {
            if (loginService.permissionCheck(user, PAGEPERMISSIONCODE)) {
                modelMap.put("permissionCode", true);
            } else {
                modelMap.put("permissionCode", false);
            }
            String searchKey = req.getParameter("searchKey");
            boolean searchFromUsersPage = searchKey.equals("users.jsp");
            boolean searchFromOrdersPage = !searchKey.equals("users.jsp");
            if (searchKey.isEmpty()) {
                modelMap.put("isSearching", false);
                return "redirect: /order/getAll";
            } else if (searchFromUsersPage) {
                String order_id = req.getParameter("order_id");
                String user_id = req.getParameter("user_id");
                List<Order> orders = omService.findOrder(user_id, order_id);
                List<OrderItems> orderItems = new ArrayList();
                if (orders != null && orders.size() != 0) {
                    for (Order order : orders) {
                        String userId = order.getUser_id();
                        String orderId = order.getId();
                        User pojoUser = userService.findUserById(userId);
                        List<Measure> measures = omService.findMeasure(userId, orderId);
                        List<Contract> contracts = omService.findContract(userId, orderId);
                        List<Design> designs = omService.findDesign(userId, orderId);
                        List<Disclaim> disclaims = omService.findDisclaim(userId, orderId);
                        List<Project> projects = omService.findProject(userId, orderId);
                        orderItems.add(
                                new OrderItems(pojoUser, order, measures, contracts, designs, disclaims, projects));
                    }
                }
                modelMap.put("orderItems", orderItems);
                modelMap.put("ordersCount", orders.size());
                modelMap.put("searchKey", searchKey);
                modelMap.put("isSearching", true);
                return "orders";
            } else if (searchFromOrdersPage) {
                List<User> searchUsers = userService.findUserByNameOrID(searchKey);
                System.out.println("===== Search key: " + searchKey);
                System.out.println("===== Found User name:" + searchUsers.get(0).getName());
                List<Order> orders = new ArrayList<>();
                for (User searchUser: searchUsers) {
                    List<Order> userOrders = omService.findOrderByUser(searchUser.getId());
                    if(userOrders != null) {
                        orders.addAll(userOrders);
                    }
                }
                List<OrderItems> orderItems = new ArrayList();
                if (orders.size() != 0 ) {
                    for (Order order : orders) {
                        String userId = order.getUser_id();
                        String orderId = order.getId();
                        User pojoUser = userService.findUserById(userId);
                        System.out.println("===== userId: " + userId);
                        System.out.println("===== user Object: " + pojoUser);
                        List<Measure> measures = omService.findMeasure(userId, orderId);
                        List<Contract> contracts = omService.findContract(userId, orderId);
                        List<Design> designs = omService.findDesign(userId, orderId);
                        List<Disclaim> disclaims = omService.findDisclaim(userId, orderId);
                        List<Project> projects = omService.findProject(userId, orderId);
                        orderItems.add(
                                new OrderItems(pojoUser, order, measures, contracts, designs, disclaims, projects));
                    }
                }
                System.out.println("===== Order counts: " + orders.size());
                modelMap.put("orderItems", orderItems);
                modelMap.put("ordersCount", orders.size());
                modelMap.put("searchKey", searchKey);
                modelMap.put("isSearching", true);
                return "orders";
            }
        } else {
            return "redirect: /login/logins";
        }
        return "orders";
    }


    @RequestMapping(value = "/uploadMeasure", method = RequestMethod.POST)
    public String uploadMeasureFile(@RequestParam("measure_file") MultipartFile measureFile, HttpServletRequest request) {
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
                    String measure_id = request.getParameter("measure_id");
                    String targetPath = measure_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "measures/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Measure ID:" + measure_id);
                        T_Measure t_measure = measureService.findMeasureById(measure_id);
                        t_measure.setUrl(targetPath);
                        measureService.updateMeasure(t_measure);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "orders";
    }

    @RequestMapping(value = "/insertDesign", method = RequestMethod.POST)
    public String insertDesignFile(@RequestParam("design_file") MultipartFile designFile, HttpServletRequest request) {
        String name = request.getParameter("design_name");
        String designer = request.getParameter("designer");
        String order_id = request.getParameter("design_order_id");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String design_id = "des" + md5Service.encodeByMD5(designer + name + ts);

        T_Design t_design = new T_Design();
        t_design.setId(design_id);
        t_design.setName(name);
        t_design.setStatus("进行中");
        t_design.setTs(ts);
        t_design.setDesigner(designer);
        //t_design.setUrl();


        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_id(order_id);
        orderItem.setItem_id(design_id);
        orderItem.setItem_class("designs");
        orderItem.setStart_time(ts);

        orderItemService.insertOrderItem(orderItem);


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
                    //String design_id = request.getParameter("design_id");
                    String targetPath = design_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "designs/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Design ID:" + design_id);
                        //T_Design t_design = designService.findDesignById(design_id);
                        t_design.setUrl(targetPath);
                        designService.insertDesign(t_design);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "orders";
    }

    @RequestMapping(value = "/uploadDesign", method = RequestMethod.POST)
    public String uploadDesignFile(@RequestParam("design_file") MultipartFile designFile, HttpServletRequest request) {
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
                    String design_id = request.getParameter("design_id");
                    String targetPath = design_id + ".jpeg";
                    System.out.println("==========Target file path:" + targetPath);
                    String writePath = img_address + "designs/" + targetPath;
                    File targetFile = new File(writePath);
                    //上传
                    try {
                        file.transferTo(targetFile);

                        System.out.println("==========Design ID:" + design_id);
                        T_Design t_design = designService.findDesignById(design_id);
                        t_design.setUrl(targetPath);
                        designService.updateDesign(t_design);
                    } catch (IOException ex) {
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.getMessage());
                        System.out.println("IO exception detected when uploading Blue House MEASURE files! ERROR: " + ex.toString());
                    }
                }

            }

        }

        return "orders";
    }

}
