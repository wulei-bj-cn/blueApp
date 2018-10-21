package com.blueHouse.service;

import com.blueHouse.mapper.FavoriteMapper;
import com.blueHouse.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class FavoriteServiceImpl implements FavoriteService {
    @Resource
    @Autowired
    private FavoriteMapper favoriteMapper;

    public List<Favorite> findCollectionByUserId(String user_id) { return favoriteMapper.findCollectionByUserId(user_id); }

    public List<Favorite> findCollectionByCategory(String category) { return favoriteMapper.findCollectionByCategory(category); }

    public List<Favorite> findAllCollections() { return favoriteMapper.findAllCollections(); }

    public void insertCollection(Favorite favorite) { favoriteMapper.insertCollection(favorite); }

    public void deleteCollection(Favorite favorite) { favoriteMapper.deleteCollection(favorite); }

    public void deleteAllCollectionByUser(Favorite favorite) { favoriteMapper.deleteAllCollectionByUser(favorite); }

}
