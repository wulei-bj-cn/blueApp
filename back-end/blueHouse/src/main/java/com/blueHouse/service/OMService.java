package com.blueHouse.service;

import com.blueHouse.pojo.Measure;
import com.blueHouse.pojo.Order;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public interface OMService {
    List<Measure> findMeasure(String user_id, String order_id);
    List<Order> findOrder(String user_id, String order_id);
}
