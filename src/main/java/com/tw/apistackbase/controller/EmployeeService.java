package com.tw.apistackbase.controller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employees> employees;
    private int idIndex = 0;

    public EmployeeService(){
        employees = new ArrayList();
//        Employees employees1 = new Employees("Xiaoming", 20, "Male");
//        Employees employees2 = new Employees("Xiaohong", 19, "Female");
//        Employees employees3 = new Employees("Xiaozhi", 15, "Male");
//        Employees employees4 = new Employees("Xiaogang", 16, "Male");
//        Employees employees5 = new Employees("Xiaoxia", 15, "Female");
//        employees.add(employees1);
//        employees.add(employees2);
//        employees.add(employees3);
//        employees.add(employees4);
//        employees.add(employees5);

        save(new Employees("Xiaoming", 20, "Male"));
        save(new Employees("Xiaohong", 19, "Female"));
        save(new Employees("Xiaozhi", 15, "Male"));
        save(new Employees("Xiaogang", 16, "Male"));
        save(new Employees("Xiaoxia", 15, "Female"));
    }

    public int save(Employees employees){
        this.idIndex = this.idIndex + 1;
        employees.setId(idIndex);
        this.employees.add(employees);
        return idIndex;
    }

    public List<Employees> getAll(){
        return this.employees;
    }

    public List<Employees> getEmployeeByGender(String gender){
        List<Employees> employeesWithTargetGender = new ArrayList<>();
        employeesWithTargetGender = this.employees.stream()
                                                .filter(e -> e.getGender().toLowerCase().equals(gender))
                                                .collect(Collectors.toList());
        return employeesWithTargetGender;
    }

    public List<Employees> getPage(int page, int size){
        int startIdx = 0;
        if(page > 0){
            startIdx = page * size - 1;
        }
        List<Employees> listWithPage = new ArrayList<>();
        for(int i = startIdx; i < this.employees.size(); i++){
            if(i > startIdx + size){
                break;
            }
            listWithPage.add(employees.get(i));
        }
        return listWithPage;
    }


    public Employees searchEmployeeByID(int id){
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
