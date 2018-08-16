package com.blueHouse.service;

import com.blueHouse.pojo.*;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public interface OMService {
    List<Measure> findMeasure(String user_id, String order_id);
    List<Order> findOrder(String user_id, String order_id);
    List<Order> findAllOrders();
    List<Contract> findContract(String user_id, String order_id);
    List<Design> findDesign(String user_id, String order_id);
    List<Disclaim> findDisclaim(String user_id, String order_id);
    List<Project> findProject(String user_id, String order_id);
}
