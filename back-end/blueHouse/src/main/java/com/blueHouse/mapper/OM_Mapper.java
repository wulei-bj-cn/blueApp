package com.blueHouse.mapper;

/**
 * Created by wulei on 23/07/2018.
 */

import com.blueHouse.pojo.orders.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OM_Mapper {
    List<Order> findOrder(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Order> findOrderByUser(
            @Param("user_id")String user_id
    );

    List<Order> findAllOrders();

    List<Measure> findMeasure(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Contract> findContract(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Design> findDesign(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Disclaim> findDisclaim(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Project> findProject(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );

    List<Material> findMaterial(
            @Param("user_id")String user_id,
            @Param("order_id")String order_id
    );
}
