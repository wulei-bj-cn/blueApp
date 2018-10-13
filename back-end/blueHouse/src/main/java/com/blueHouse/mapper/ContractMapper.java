package com.blueHouse.mapper;

import com.blueHouse.pojo.browse.T_Contract;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface ContractMapper {
        T_Contract findContractById(String id);
        List<T_Contract> findContractByPartialName(String name);
        List<T_Contract> findAllContract();
        void insertContract(T_Contract con);
        void updateContract(T_Contract con);
        void deleteContract(T_Contract con);
}
