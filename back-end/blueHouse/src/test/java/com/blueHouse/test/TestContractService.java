package com.blueHouse.test;

import com.blueHouse.pojo.orders.Contract;
import com.blueHouse.service.ContractService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lihan on 2018/9/2.
 */
public class TestContractService {
    private ApplicationContext applicationContext;
    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findContractById() throws Exception {
        ContractService contractService= (ContractService) applicationContext.getBean("contractService");
        Contract contract = contractService.findContractById("con1");
        System.out.println(contract.getId());
    }
}
