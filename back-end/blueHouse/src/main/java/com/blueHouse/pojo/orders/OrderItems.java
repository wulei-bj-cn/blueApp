package com.blueHouse.pojo.orders;


import com.blueHouse.pojo.User;

import java.util.List;

/**
 * Created by wulei on 23/07/2018.
 */
public class OrderItems {

    private User user;
    private Order order;
    private List<Measure> measures = null;
    private List<Contract> contracts = null;
    private List<Design> designs = null;
    private List<Disclaim> disclaims = null;
    private List<Project> projects = null;

    public OrderItems(
            User user,
            Order order,
            List<Measure> measures,
            List<Contract> contracts,
            List<Design> designs,
            List<Disclaim> disclaims,
            List<Project> projects
            ) {
        this.user = user;
        this.order = order;
        this.measures = measures;
        this.contracts = contracts;
        this.designs = designs;
        this.disclaims = disclaims;
        this.projects = projects;
    }

    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }

    public Order getOrder() { return this.order; }
    public void setOrder(Order order) { this.order = order; }

    public List<Measure> getMeasure() { return this.measures; }
    public void setMeasure(List<Measure> measures) { this.measures = measures; }

    public List<Contract> getContracts() { return this.contracts; }
    public void setContracts(List<Contract> contracts) { this.contracts = contracts; }

    public List<Design> getDesigns() { return this.designs; }
    public void setDesigns(List<Design> designs) { this.designs = designs; }

    public List<Disclaim> getDisclaim() { return this.disclaims; }
    public void setDisclaim(List<Disclaim> disclaims) { this.disclaims = disclaims; }

    public List<Project> getProjects() { return this.projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

}

