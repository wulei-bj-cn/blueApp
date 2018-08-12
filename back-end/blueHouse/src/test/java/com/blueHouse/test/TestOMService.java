package com.blueHouse.test;

import com.blueHouse.pojo.*;
import com.blueHouse.service.OMService;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by wulei on 27/07/2018.
 */
public class TestOMService {

    private ApplicationContext applicationContext;

    @Before
    public void init() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    }

    @Test
    public void findMeasure() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Measure> measures = omService.findMeasure("usr8", "ord1");
        for (Measure measure: measures) {
            System.out.println(
                    measure.getId() + "\n" +
                            measure.getUser_id() + "\n" +
                            measure.getOrder_id() + "\n" +
                            measure.getName() + "\n" +
                            measure.getUrl() + "\n" +
                            measure.getTs() + "\n" +
                            measure.getStatus() + "\n" +
                            measure.getCrew()
            );
        }
    }

    @Test
    public void findContract() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Contract> contracts = omService.findContract("usr8", "ord1");
        for (Contract contract: contracts) {
            System.out.println(
                    contract.getId() + "\n" +
                            contract.getUser_id() + "\n" +
                            contract.getOrder_id() + "\n" +
                            contract.getName() + "\n" +
                            contract.getUrl() + "\n" +
                            contract.getTs() + "\n" +
                            contract.getStatus() + "\n" +
                            contract.getType()
            );
        }
    }

    @Test
    public void findDesign() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Design> designs = omService.findDesign("usr8", "ord1");
        for (Design design: designs) {
            System.out.println(
                    design.getId() + "\n" +
                            design.getUser_id() + "\n" +
                            design.getOrder_id() + "\n" +
                            design.getName() + "\n" +
                            design.getUrl() + "\n" +
                            design.getTs() + "\n" +
                            design.getStatus() + "\n" +
                            design.getDesigner()
            );
        }
    }

    @Test
    public void findDisclaim() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Disclaim> disclaims = omService.findDisclaim("usr8", "ord1");
        for (Disclaim disclaim: disclaims) {
            System.out.println(
                    disclaim.getId() + "\n" +
                            disclaim.getUser_id() + "\n" +
                            disclaim.getOrder_id() + "\n" +
                            disclaim.getName() + "\n" +
                            disclaim.getUrl() + "\n" +
                            disclaim.getStatus() + "\n" +
                            disclaim.getTs() + "\n"
            );
        }
    }

    @Test
    public void findProject() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Project> projects = omService.findProject("usr8", "ord1");
        for (Project project: projects) {
            System.out.println(
                    project.getId() + "\n" +
                            project.getUser_id() + "\n" +
                            project.getOrder_id() + "\n" +
                            project.getName() + "\n" +
                            project.getCategory() + "\n" +
                            project.getStatus() + "\n" +
                            project.getEnabled() + "\n"
            );
        }
    }

    @Test
    public void findOrder() throws Exception {
        OMService omService = (OMService) applicationContext.getBean("omService");
        List<Order> orders = omService.findOrder("usr8", "ord1");
        for (Order order: orders) {
            System.out.println(
                    order.getUser_id() + "\n" +
                            order.getId() + "\n" +
                            order.getStart_time() + "\n" +
                            order.getEnd_time() + "\n" +
                            order.getStatus()
            );
        }
    }
}
