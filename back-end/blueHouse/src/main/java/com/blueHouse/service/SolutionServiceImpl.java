package com.blueHouse.service;

import com.blueHouse.mapper.SolutionMapper;
import com.blueHouse.pojo.browse.T_Solution;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public class SolutionServiceImpl implements SolutionService {
    @Resource
    @Autowired
    private SolutionMapper solutionMapper;

    public List<T_Solution> findAllSolutions() { return solutionMapper.findAllSolutions(); }
    public T_Solution findSolutionById(String id) { return solutionMapper.findSolutionById(id); }
    public List<T_Solution> findSolutionByName(String name) { return solutionMapper.findSolutionByName(name); }
    public List<T_Solution> findSolutionByDesignerName(String designer) { return solutionMapper.findSolutionByDesignerName(designer); }
    public List<T_Solution> findSolutionByCategory(String category) { return solutionMapper.findSolutionByCategory(category); }
    public void insertSolution(T_Solution t_solution) { solutionMapper.insertSolution(t_solution); }
    public void updateSolution(T_Solution t_solution) { solutionMapper.updateSolution(t_solution); }
    public void deleteSolution(T_Solution t_solution) { solutionMapper.deleteSolution(t_solution);}
}
