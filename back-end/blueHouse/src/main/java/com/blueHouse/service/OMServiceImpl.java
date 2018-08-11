package com.blueHouse.service;

import com.blueHouse.mapper.OM_Mapper;
import com.blueHouse.pojo.Measure;
import com.blueHouse.pojo.Order;
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

}
