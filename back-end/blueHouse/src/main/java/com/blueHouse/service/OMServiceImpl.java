package com.blueHouse.service;

import com.blueHouse.mapper.OM_Mapper;
import com.blueHouse.pojo.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
@Service
public class OMServiceImpl implements OMService {

    @Resource
    @Autowired
    private OM_Mapper om_mapper;

    public List<Measure> findMeasure(String user_id, String order_id) {
        return om_mapper.findMeasure(user_id, order_id);
    }

    public List<Order> findOrder(String user_id, String order_id) {
        return om_mapper.findOrder(user_id, order_id);
    }

    public List<Order> findAllOrders() {
        return om_mapper.findAllOrders();
    }

    public List<Contract> findContract(String user_id, String order_id) {
        return om_mapper.findContract(user_id, order_id);
    }

    public List<Design> findDesign(String user_id, String order_id) {
        return om_mapper.findDesign(user_id, order_id);
    }

    public List<Disclaim> findDisclaim(String user_id, String order_id) {
        return om_mapper.findDisclaim(user_id, order_id);
    }

    public List<Project> findProject(String user_id, String order_id) {
        return om_mapper.findProject(user_id, order_id);
    }

    public List<Material> findMaterial(String user_id, String order_id) {
        return om_mapper.findMaterial(user_id, order_id);
    }
}
