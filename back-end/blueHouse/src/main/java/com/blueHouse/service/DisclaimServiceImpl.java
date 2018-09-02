package com.blueHouse.service;

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
    private DisclaimService disclaimService;

    @Override
    public Disclaim findDisclaimById(String id) {
        return disclaimService.findDisclaimById(id);
    }

    @Override
    public List<Disclaim> findDisclaimByUserName(String name) {
        return disclaimService.findDisclaimByUserName(name);
    }

    @Override
    public List<Disclaim> findDisclaimByCrewrName(String name) {
        return disclaimService.findDisclaimByCrewrName(name);
    }

    @Override
    public void insertDisclaim(Disclaim disclaim) {
        disclaimService.insertDisclaim(disclaim);
    }

    @Override
    public void updateDisclaim(Disclaim disclaim) {
        disclaimService.updateDisclaim(disclaim);
    }
}
