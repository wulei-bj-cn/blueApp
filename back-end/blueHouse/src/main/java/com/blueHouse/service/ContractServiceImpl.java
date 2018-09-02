package com.blueHouse.service;

import com.blueHouse.mapper.ContractMapper;
import com.blueHouse.pojo.orders.Contract;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class ContractServiceImpl implements ContractService {
    @Resource
    @Autowired
    private ContractMapper contractMapper;


    @Override
    public Contract findContractById(String id) {
        return contractMapper.findContractById(id);
    }

    @Override
    public List<Contract> findContractByPartialName(String name) {
        return contractMapper.findContractByPartialName(name);
    }
    @Override
    public List<Contract> findAllContract() {
        return contractMapper.findAllContract();
    }

    @Override
    public void insertContract(Contract con) {
        contractMapper.insertContract(con);
    }

    @Override
    public void updateContract(Contract con) {
        contractMapper.updateContract(con);
    }
}
