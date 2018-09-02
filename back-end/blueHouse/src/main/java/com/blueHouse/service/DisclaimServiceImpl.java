package com.blueHouse.service;

import com.blueHouse.mapper.DisclaimMapper;
import com.blueHouse.pojo.orders.Disclaim;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class DisclaimServiceImpl  implements  DisclaimService{
    @Resource
    @Autowired
    private DisclaimMapper disclaimMapper;

    @Override
    public Disclaim findDisclaimById(String id) {
        return disclaimMapper.findDisclaimById(id);
    }

    @Override
    public List<Disclaim> findDisclaimByUserName(String name) {
        return disclaimMapper.findDisclaimByUserName(name);
    }

    @Override
    public List<Disclaim> findDisclaimByCrewrName(String name) {
        return disclaimMapper.findDisclaimByCrewrName(name);
    }

    @Override
    public void insertDisclaim(Disclaim disclaim) {
        disclaimMapper.insertDisclaim(disclaim);
    }

    @Override
    public void updateDisclaim(Disclaim disclaim) {
        disclaimMapper.updateDisclaim(disclaim);
    }
}
