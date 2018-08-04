package com.blueHouse.service;

import com.blueHouse.pojo.Access;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public interface AccessService {
    Access findAccessById(int id);
    List<Access> findAccessByPartialId(int id);
    List<Access> findAllAccesss();
    void insertAccess(Access user);
    void updateAccess(Access user);
}
