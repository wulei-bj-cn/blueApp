package com.blueHouse.service;


import com.blueHouse.pojo.Favorite;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface FavoriteService {

    List<Favorite> findFavoriteByUserId(String user_id);
    List<Favorite> findFavoriteByCategory(String category);
    List<Favorite> findAllFavorites();
    void insertFavorite(Favorite cart);
    void deleteFavorite(Favorite cart);
    void deleteAllFavoriteByUser(Favorite cart);
}
