package com.blueHouse.service;


import com.blueHouse.pojo.Cart;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface CartService {

    List<Cart> findCartByUserId(String user_id);
    List<Cart> findAllCarts();
    void insertCart(Cart cart);
    void deleteCart(Cart cart);
    void deleteAllCartByUser(Cart cart);
}
