package com.blueHouse.service;


import com.blueHouse.pojo.orders.Disclaim;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface DisclaimService {
    Disclaim findDisclaimById(String id);
    List<Disclaim> findDisclaimByUserName(String name);
    List<Disclaim> findDisclaimByCrewrName(String name);
    void insertDisclaim(Disclaim disclaim);
    void updateDisclaim(Disclaim disclaim);
    void deleteDisclaim(Disclaim disclaim);
}
