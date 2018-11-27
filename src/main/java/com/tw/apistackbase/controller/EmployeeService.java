package com.tw.apistackbase.controller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employees> employees;
    public EmployeeService(){
        employees = new ArrayList();
        Employees employees1 = new Employees(0,"Xiaoming", 20, "Male");
        Employees employees2 = new Employees(1,"Xiaohong", 19, "Female");
        Employees employees3 = new Employees(2,"Xiaozhi", 15, "Male");
        Employees employees4 = new Employees(3,"Xiaogang", 16, "Male");
        Employees employees5 = new Employees(4,"Xiaoxia", 15, "Female");
        employees.add(employees1);
        employees.add(employees2);
        employees.add(employees3);
        employees.add(employees4);
        employees.add(employees5);
    }

    public List<Employees> getAll(){
        return this.employees;
    }

    public List<Employees> getAll(String gender){
        List<Employees> employeesWithTragetGender = new ArrayList<>();
        employeesWithTragetGender = this.employees.stream()
                                                .filter(e -> e.getGender().toLowerCase().equals(gender))
                                                .collect(Collectors.toList());
        return employeesWithTragetGender;
    }

    public Employees searchEmployee(int id){
        Employees employees = this.employees.get(id);
        return employees;
    }

    public List<Employees> initiateEmployee(List<Employees> employees){
        this.employees = employees;
        return this.employees;
    }

    public void updateEmployee(int targetID, Employees targetEmployee) {
        for(Employees employee : this.employees){
            if (employee.getId() == targetID){
                employee.setAge(targetEmployee.getAge());
                employee.setGender(targetEmployee.getGender());
                employee.setName(targetEmployee.getName());
            }
        }
    }

    public void deleteEmployee(int targetID) {
        for(Employees employee : this.employees){
            if (employee.getId() == targetID){
                this.employees.remove(employee);
                return;
            }
        }
    }
}
