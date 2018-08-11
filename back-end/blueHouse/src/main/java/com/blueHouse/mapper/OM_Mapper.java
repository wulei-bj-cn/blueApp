package com.blueHouse.mapper;

/**
 * Created by wulei on 23/07/2018.
 */

import com.blueHouse.pojo.Measure;
import com.blueHouse.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OM_Mapper {
    List<Order> findOrder(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Measure> findMeasure(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );
}
