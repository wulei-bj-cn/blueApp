package com.blueHouse.service;

import com.blueHouse.mapper.CollectionMapper;
import com.blueHouse.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class CollectionServiceImpl implements CollectionService {
    @Resource
    @Autowired
    private CollectionMapper collectionMapper;

    public List<Collection> findCollectionByUserId(String user_id) { return collectionMapper.findCollectionByUserId(user_id); }

    public List<Collection> findCollectionByCategory(String category) { return collectionMapper.findCollectionByCategory(category); }

    public List<Collection> findAllCollections() { return collectionMapper.findAllCollections(); }

    public void insertCollection(Collection collection) { collectionMapper.insertCollection(collection); }

    public void deleteCollection(Collection collection) { collectionMapper.deleteCollection(collection); }

    public void deleteAllCollectionByUser(Collection collection) { collectionMapper.deleteAllCollectionByUser(collection); }

}
