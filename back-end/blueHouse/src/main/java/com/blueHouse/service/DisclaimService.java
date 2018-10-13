package com.blueHouse.service;


import com.blueHouse.pojo.browse.T_Disclaim;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface DisclaimService {
    T_Disclaim findDisclaimById(String id);
    List<T_Disclaim> findDisclaimByUserName(String name);
    List<T_Disclaim> findDisclaimByCrewrName(String name);
    void insertDisclaim(T_Disclaim disclaim);
    void updateDisclaim(T_Disclaim disclaim);
    void deleteDisclaim(T_Disclaim disclaim);
}
