package com.example.demo.Service;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

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
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.findAllEmployees();
        int flag=0;
        for(Employee employee:employeeList){
            Department department = new Department();
            department=employee.getDepartment();
            if(department.getId()==id){
                flag=1;
            }
        }
        if(flag == 0){
            this.departmentRepository.deleteById(id);
        }
    }
}
