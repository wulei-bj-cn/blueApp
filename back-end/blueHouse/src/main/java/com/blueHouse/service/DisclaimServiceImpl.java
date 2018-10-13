package com.blueHouse.service;

import com.blueHouse.mapper.DisclaimMapper;
import com.blueHouse.pojo.browse.T_Disclaim;
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
    public T_Disclaim findDisclaimById(String id) {
        return disclaimMapper.findDisclaimById(id);
    }

    @Override
    public List<T_Disclaim> findDisclaimByUserName(String name) {
        return disclaimMapper.findDisclaimByUserName(name);
    }

    @Override
    public List<T_Disclaim> findDisclaimByCrewrName(String name) {
        return disclaimMapper.findDisclaimByCrewrName(name);
    }

    @Override
    public void insertDisclaim(T_Disclaim disclaim) {
        disclaimMapper.insertDisclaim(disclaim);
    }

    @Override
    public void updateDisclaim(T_Disclaim disclaim) {
        disclaimMapper.updateDisclaim(disclaim);
    }

    @Override
    public void deleteDisclaim(T_Disclaim disclaim) { disclaimMapper.deleteDisclaim(disclaim); }
}
