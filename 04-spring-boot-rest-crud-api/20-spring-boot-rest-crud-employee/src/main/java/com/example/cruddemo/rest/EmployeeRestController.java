package com.example.cruddemo.rest;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    //expose employees endpoint
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for getting a single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    //add mapping for creating a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //If an ID is passed in the JSON, set it to 0 so that we force a save of a new item instead of an update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        //this db employee will have the id from the DB
        return dbEmployee;
    }

    //add mapping for updating an existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for Deleteing employees
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee with id - " + employeeId;
    }
}
