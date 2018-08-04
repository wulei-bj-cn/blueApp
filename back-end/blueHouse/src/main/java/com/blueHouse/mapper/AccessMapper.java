package com.blueHouse.mapper;

/**
 * Created by wulei on 23/07/2018.
 */

import com.blueHouse.pojo.Access;

import java.util.List;

public interface AccessMapper {
    Access findAccessById(int id);
    List<Access> findAccessByPartialId(int id);
    List<Access> findAllAccesss();
    void insertAccess(Access user);
    void updateAccess(Access user);
}
