package com.blueHouse.mapper;

import com.blueHouse.pojo.orders.OrderItem;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface OrderItemMapper {

    List<OrderItem> findOrderItemByOrderId(String order_id);
    OrderItem findOrderItemBy2Id(String order_id, String item_id);
    void insertOrderItem(OrderItem orderItem);
    void updateOrderItemStatus(OrderItem orderItem);
    void updateOrderItemEndTime(OrderItem orderItem);
    void deleteOrderItem(OrderItem orderItem);
    void deleteOrderItemsByOrder(OrderItem orderItem);
}
