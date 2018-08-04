package com.blueHouse.service;

import com.blueHouse.mapper.AccessMapper;
import com.blueHouse.pojo.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
@Service
public class AccessServiceImpl implements AccessService {

    @Resource
    @Autowired
    private AccessMapper accessMapper;

    public Access findAccessById(int id) {
        return accessMapper.findAccessById(id);
    }

    public List<Access> findAccessByPartialId(int id) {
        return accessMapper.findAccessByPartialId(id);
    }

    public List<Access> findAllAccesss() {
        return accessMapper.findAllAccesss();
    }

    public void insertAccess(Access access) { accessMapper.insertAccess(access); }

    public void updateAccess(Access access) { accessMapper.updateAccess(access);}
}
