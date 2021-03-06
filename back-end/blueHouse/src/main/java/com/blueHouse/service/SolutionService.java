package com.blueHouse.service;

import com.blueHouse.pojo.browse.T_Solution;

import java.util.List;

/**
 * Created by lihan on 2018/9/1.
 */
public interface SolutionService {

    List<T_Solution> findAllSolutions();
    T_Solution findSolutionById(String id);
    List<T_Solution> findSolutionByName(String name);
    List<T_Solution> findSolutionByDesignerName(String designer);
    List<T_Solution> findSolutionByCategory(String designer);
    void insertSolution(T_Solution t_solution);
    void updateSolution(T_Solution t_solution);
    void deleteSolution(T_Solution t_solution);

}
