package com.blueHouse.service;

import com.blueHouse.mapper.CartMapper;
import com.blueHouse.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class CartServiceImpl implements CartService {
    @Resource
    @Autowired
    private CartMapper cartMapper;

    public List<Cart> findCartByUserId(String user_id) { return cartMapper.findCartByUserId(user_id); }

    public List<Cart> findAllCarts() { return cartMapper.findAllCarts(); }

    public void insertCart(Cart cart) { cartMapper.insertCart(cart); }

    public void deleteCart(Cart cart) { cartMapper.deleteCart(cart); }

    public void deleteAllCartByUser(Cart cart) { cartMapper.deleteAllCartByUser(cart); }

}
