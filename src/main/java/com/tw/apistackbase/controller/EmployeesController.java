package com.tw.apistackbase.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    EmployeeService employeeService = new EmployeeService();

    @Autowired
    public EmployeesController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(produces = {"application/json"})
    public List<Employees> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    public Employees searchEmployee(@PathVariable String id ) {
        return employeeService.searchEmployee(Integer.parseInt(id));
    }

    @PostMapping(produces = {"application/json"},consumes = {"application/json"})
    public List<Employees> initiateEmployees(@RequestBody List<Employees> employees){
    List<Employees> newEmployeesList = employeeService.initiateEmployee(employees);
    return newEmployeesList;
    }

    @PutMapping(path = "/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public List<Employees> updateEmployees(@RequestBody Employees employees, @PathVariable String id){
        int targetID = Integer.parseInt(id);
        this.employeeService.updateEmployee(targetID, employees);
        List<Employees> updatedEmployeesList = this.employeeService.getAll();
        return updatedEmployeesList;
    }

    @DeleteMapping(path = "/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public List<Employees> deleteEmployees(@PathVariable String id){
        int targetID = Integer.parseInt(id);
        this.employeeService.deleteEmployee(targetID);
        List<Employees> updatedEmployeesList = this.employeeService.getAll();
        return updatedEmployeesList;
    }

//    @RequestMapping(params = { "page", "pageSize" }, method = GET, produces = {"application/json"})
//    @ResponseBody
//    public List<Employees> getAllWithPagination(@RequestParam( "page" ) int page, @RequestParam( "pageSize" ) int size)
//    {
//        return employeeService.getAll(page, size);
//    }

    @RequestMapping(params = { "gender" }, method = GET, produces = {"application/json"})
    public List<Employees> getAllWithGender(@RequestParam( "gender" ) String gender)
    {
        return employeeService.getAll(gender);
        //return gender;
    }

}
