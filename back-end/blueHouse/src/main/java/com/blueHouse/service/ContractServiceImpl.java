package com.blueHouse.service;

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
    private ContractService contractService;


    @Override
    public Contract findContractById(String id) {
        return contractService.findContractById(id);
    }

    @Override
    public List<Contract> findContractByPartialName(String name) {
        return contractService.findContractByPartialName(name);
    }

    @Override
    public List<Contract> findAllContract() {
        return contractService.findAllContract();
    }

    @Override
    public void insertContract(Contract con) {
        contractService.insertContract(con);
    }

    @Override
    public void updateContract(Contract con) {
        contractService.updateContract(con);
    }
}
