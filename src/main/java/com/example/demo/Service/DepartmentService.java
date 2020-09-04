package com.example.demo.Service;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    public Department findDepartmentById(int id){

        return this.departmentRepository.findById(id).get();
    }
    public List<Department> findAllDepartments(){
        return this.departmentRepository.findAll();
    }
    public Department addDepartment(Department department){
        return this.departmentRepository.save(department);
    }
    public Department updateDepartment(int id, String name){
        Department department = findDepartmentById(id);
        department.setName(name);
        departmentRepository.save(department);
        return department;
    }
    public void deleteDepartmentById(int id){
            this.departmentRepository.deleteById(id);
    }
}
