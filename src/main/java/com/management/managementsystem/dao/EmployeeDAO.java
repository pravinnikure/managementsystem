package com.management.managementsystem.dao;

import com.management.managementsystem.Model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee,Integer>
{
    @Override
    List<Employee> findAll();
}

