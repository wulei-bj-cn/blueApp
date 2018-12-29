package com.blueHouse.service;

import com.blueHouse.mapper.OrderItemMapper;
import com.blueHouse.pojo.orders.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class OrderItemServiceImpl implements  OrderItemService{
    @Resource
    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderItem> findOrderItemByOrderId(String order_id) { return orderItemMapper.findOrderItemByOrderId(order_id); }

    public OrderItem findOrderItemBy2Id(OrderItem orderItem) { return orderItemMapper.findOrderItemBy2Id(orderItem); }

    public void insertOrderItem(OrderItem orderItem) { orderItemMapper.insertOrderItem(orderItem); }

    public void updateOrderItemStatus(OrderItem orderItem) { orderItemMapper.updateOrderItemStatus(orderItem); }

    public void updateOrderItemEndTime(OrderItem orderItem) { orderItemMapper.updateOrderItemEndTime(orderItem); }

    public void deleteOrderItem(OrderItem orderItem) { orderItemMapper.deleteOrderItem(orderItem); }

    public void deleteOrderItemsByOrder(OrderItem orderItem) {orderItemMapper.deleteOrderItemsByOrder(orderItem); }
}
