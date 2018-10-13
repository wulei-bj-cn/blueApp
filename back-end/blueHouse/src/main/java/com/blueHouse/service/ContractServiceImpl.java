package com.blueHouse.service;

import com.blueHouse.mapper.ContractMapper;
import com.blueHouse.pojo.browse.T_Contract;
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
    public T_Contract findContractById(String id) {
        return contractMapper.findContractById(id);
    }

    @Override
    public List<T_Contract> findContractByPartialName(String name) {
        return contractMapper.findContractByPartialName(name);
    }
    @Override
    public List<T_Contract> findAllContract() {
        return contractMapper.findAllContract();
    }

    @Override
    public void insertContract(T_Contract con) {
        contractMapper.insertContract(con);
    }

    @Override
    public void updateContract(T_Contract con) {
        contractMapper.updateContract(con);
    }

    @Override
    public void deleteContract(T_Contract con) { contractMapper.deleteContract(con);}
}
