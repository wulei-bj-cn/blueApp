package com.blueHouse.service;


import com.blueHouse.pojo.Favorite;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface FavoriteService {

    List<Favorite> findCollectionByUserId(String user_id);
    List<Favorite> findCollectionByCategory(String category);
    List<Favorite> findAllCollections();
    void insertCollection(Favorite cart);
    void deleteCollection(Favorite cart);
    void deleteAllCollectionByUser(Favorite cart);
}
