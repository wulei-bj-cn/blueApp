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

    public List<Favorite> findFavoriteByUserId(String user_id) { return favoriteMapper.findFavoriteByUserId(user_id); }

    public List<Favorite> findFavoriteByCategory(String category) { return favoriteMapper.findFavoriteByCategory(category); }

    public List<Favorite> findAllFavorites() { return favoriteMapper.findAllFavorites(); }

    public void insertFavorite(Favorite favorite) { favoriteMapper.insertFavorite(favorite); }

    public void deleteFavorite(Favorite favorite) { favoriteMapper.deleteFavorite(favorite); }

    public void deleteAllFavoriteByUser(Favorite favorite) { favoriteMapper.deleteAllFavoriteByUser(favorite); }

}
