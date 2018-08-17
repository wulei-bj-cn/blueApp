package com.blueHouse.pojo;


import java.util.List;

/**
 * Created by wulei on 23/07/2018.
 */
public class OrderItems {

    private String user_id;
    private String order_id;
    private List<Measure> measures = null;
    private List<Contract> contracts = null;
    private List<Design> designs = null;
    private List<Disclaim> disclaims = null;
    private List<Project> projects = null;

    public OrderItems(
            String user_id,
            String order_id,
            List<Measure> measures,
            List<Contract> contracts,
            List<Design> designs,
            List<Disclaim> disclaims,
            List<Project> projects
            ) {
        this.user_id = user_id;
        this.order_id = order_id;
        this.measures = measures;
        this.contracts = contracts;
        this.designs = designs;
        this.disclaims = disclaims;
        this.projects = projects;
    }

    public String getUser_id() { return this.user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getOrder_id() { return this.order_id; }
    public void setOrder_id(String order_id) { this.order_id = order_id; }

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

