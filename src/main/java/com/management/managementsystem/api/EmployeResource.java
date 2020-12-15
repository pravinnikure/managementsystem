package com.management.managementsystem.api;

import com.management.managementsystem.Model.Employee;
import com.management.managementsystem.Service.EmployeService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeResource
{
    //Injecting EmployeeService class to EmployeeResource class
   // @Autowired annotation can be used to autowire bean on the setter method
    @Autowired
    private EmployeService employeService;

    //Handle post type request
    //@RequestBody makes Spring to map entire request to a model class and from
    // there you can retrieve or set values from its getter and setter methods

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return employeService.addEmployee(employee);
    }

    //Retriving Employee
    @GetMapping
    public List<Employee> getEmployees()
    {
        return employeService.getEmployees();
    }

    @GetMapping(value = "/{empId}")
    //@path variable annotation in order to receve and store it to empId
    public Employee getEmployee(@PathVariable("empId") int empId)
    {
        return employeService.getEmployee(empId);

    }
    //Update by using put mapping

    @PutMapping(value = "/{empId}")
    public Employee updateEmployee(@PathVariable("empId") int empId,@RequestBody Employee employee)
    {
      return employeService.updateEmployee(empId,employee);
    }

    @DeleteMapping(value = "/{empId}")
    public void deleteEmploye(@PathVariable("empId") int empId)
    {
        employeService.deleteEmployee(empId);
    }

}
