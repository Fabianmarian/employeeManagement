package com.example.demo.Service;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Model.DTO.EmployeeDto;
import com.example.demo.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findEmployeeById(int id){
        return this.employeeRepository.findById(id).get();
    }

    public List<Employee> findAllEmployee(){
        return this.employeeRepository.findAll();
    }

    public EmployeeDto findEmployeeDtoById(int id){
        Employee employee = findEmployeeById(id);
        ModelMapper modelMapper = new ModelMapper();
        Job job = employee.getJobCategory();
        Department department = employee.getDepartment();
        String employeeJob = job.getName();
        String employeeDepartment = department.getName();
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employeeDto.setDepartment(employeeDepartment);
        employeeDto.setJob(employeeJob);
        return employeeDto;
    }

    public List<EmployeeDto> findAllEmployeeDto(){
        List<Employee> employeeList = findAllEmployee();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Employee employee: employeeList){
            Job job = employee.getJobCategory();
            Department department = employee.getDepartment();
            String employeeJob = job.getName();
            String employeeDepartment = department.getName();
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            employeeDto.setJob(employeeJob);
            employeeDto.setDepartment(employeeDepartment);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    public Employee saveEmployee(Employee employee, int jobCategory, int department){
        DepartmentService departmentService = new DepartmentService();
        JobService jobService = new JobService();
        employee.setJobCategory(jobService.findJobById(jobCategory));
        employee.setDepartment(departmentService.findDepartmentById(department));
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee, int jobId, int departmentId){
        DepartmentService departmentService = new DepartmentService();
        JobService jobService = new JobService();
        Employee employee1 = findEmployeeById(id);
        employee1 = employee;
        employee1.setJobCategory(jobService.findJobById(jobId));
        employee1.setDepartment(departmentService.findDepartmentById(departmentId));
        employeeRepository.save(employee1);
        return employee1;
    }

    public Employee updateEmployeeFirstName(int id, String firstName){
        Employee employee = findEmployeeById(id);
        employee.setFirstName(firstName);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeLastName(int id, String lastName){
        Employee employee = findEmployeeById(id);
        employee.setLastName(lastName);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeManager(int id, boolean status){
        Employee employee = findEmployeeById(id);
        employee.setManager(status);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeJob(int id, int jobId){
        JobService jobService = new JobService();
        Employee employee = findEmployeeById(id);
        employee.setJobCategory(jobService.findJobById(jobId));
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeDepartment(int id, int departmentId){
        DepartmentService departmentService = new DepartmentService();
        Employee employee = findEmployeeById(id);
        employee.setDepartment(departmentService.findDepartmentById(id));
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeStartDate(int id, LocalDate startDate){
        Employee employee = findEmployeeById(id);
        employee.setStartDate(startDate);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeEndDate(int id, LocalDate endDate){
        Employee employee = findEmployeeById(id);
        employee.setEndDate(endDate);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeActive(int id, boolean status){
        Employee employee = findEmployeeById(id);
        employee.setActive(status);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeAddress(int id, String address){
        Employee employee = findEmployeeById(id);
        employee.setAddress(address);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeePostalCode(int id, int postalCode){
        Employee employee = findEmployeeById(id);
        employee.setPostalCode(postalCode);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeTelephone(int id, String telephone){
        Employee employee = findEmployeeById(id);
        employee.setTelephone(telephone);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeEmail(int id, String email){
        Employee employee = findEmployeeById(id);
        employee.setEmail(email);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeBirthday(int id, LocalDate birthday){
        Employee employee = findEmployeeById(id);
        employee.setBirthday(birthday);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeNoChildren(int id, int childrens){
        Employee employee = findEmployeeById(id);
        employee.setNoChildern(childrens);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeSalary(int id, double salary){
        Employee employee = findEmployeeById(id);
        employee.setSalary(salary);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeStudies(int id, String studies){
        Employee employee = findEmployeeById(id);
        employee.setStudies(studies);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeSSN(int id, int ssn){
        Employee employee = findEmployeeById(id);
        employee.setSocialSecurityNumber(ssn);
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeDriving(int id, boolean status){
        Employee employee = findEmployeeById(id);
        employee.setHasDrivingLicense(status);
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployeeById(int id){
        this.employeeRepository.deleteById(id);
    }

}
