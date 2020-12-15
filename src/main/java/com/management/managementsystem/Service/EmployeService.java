package com.management.managementsystem.Service;

import com.management.managementsystem.Model.Employee;
import com.management.managementsystem.dao.EmployeeDAO;
import com.management.managementsystem.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class EmployeService
{
    @Autowired
    private EmployeeDAO employeeDAO;

    private int employeeIdCount = 1;
    private List<Employee> employeeList = new CopyOnWriteArrayList<>();//For concurrent operations

    public Employee addEmployee(Employee employee)
    {
        /*employee.setEmpId(employeeIdCount);
        employeeList.add(employee);
        employeeIdCount++;*/
       return employeeDAO.save(employee);

        /*return employee;*/

    }

    public List<Employee> getEmployees()
    {
        return employeeDAO.findAll();
           /* return employeeList;*/
    }

    @PutMapping("/{empId}")
    public Employee getEmployee(@RequestBody int empId)
    {
      /*  return  employeeList
                .stream()
                .filter(e->e.getEmpId()==empId)
                .findFirst()
                .get();*/
        Optional<Employee> optionalEmployee = employeeDAO.findById(empId);

        if(!optionalEmployee.isPresent())
        {
            throw new EmployeeNotFoundException("Employee details not available ");

        }

        return optionalEmployee.get();
    }
    public Employee updateEmployee(int empId,Employee employee)
    {
        //Updated employee
      /* employeeList
                .stream()
                .forEach(e->{
                    if(e.getEmpId()==empId){
                        e.setEmpFirstName(employee.getEmpFirstName());
                        e.setEmpLastName(employee.getEmpLastName());
                        e.setEmail(employee.getEmail());

                    }
                });

        //Returbed updated employee

        return
                employeeList
                        .stream()
                        .filter(e->e.getEmpId() == empId)
                        .findFirst()
                        .get();*/

        employee.setEmpId(empId);
       return employeeDAO.save(employee);

    }

    public void deleteEmployee(int empId)
    {
        /*employeeList
                .stream()
                .forEach(e->{
                    if(e.getEmpId()==empId)
                    {
                        employeeList.remove(e);
                    }
                });*/
        employeeDAO.deleteById(empId);
     }

}
