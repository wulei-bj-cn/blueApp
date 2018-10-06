package com.blueHouse.service;


import com.blueHouse.pojo.Collection;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface CollectionService {

    Collection findCollectionByUserId(String user_id);
    List<Collection> findAllCollections();
    void insertCollection(Collection cart);
    void deleteCollection(Collection cart);
    void deleteAllCollectionByUser(Collection cart);
}
