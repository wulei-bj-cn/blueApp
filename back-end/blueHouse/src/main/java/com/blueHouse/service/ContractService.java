package com.blueHouse.service;

import com.blueHouse.pojo.orders.Contract;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ContractService {
    Contract findContractById(String id);
    List<Contract> findContractByPartialName(String name);
    List<Contract> findAllContract();
    void insertContract(Contract con);
    void updateContract(Contract con);
}
