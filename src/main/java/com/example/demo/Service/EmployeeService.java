package com.example.demo.Service;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Model.DTO.EmployeeDto;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee findEmployeeById(int id){
        return this.employeeRepository.findById(id).get();
    }

    public List<Employee> findAllEmployees(){
        return this.employeeRepository.findAll();
    }

    public EmployeeDto findEmployeeDtoById(int id){
        Employee employee = employeeRepository.findById(id).get();
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
        List<Employee> employeeList = findAllEmployees();
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

    public List<Employee> getAllEmployeesByDepartment(int idDepartment){
        List<Employee> employeeList = findAllEmployees();
        List<Employee> employeesListByDepartment = new ArrayList<>();
        Department department = departmentRepository.findById(idDepartment).get();
        for(Employee employee: employeeList){
            if(employee.getDepartment()==department){
                employeesListByDepartment.add(employee);
            }
        }
        return employeesListByDepartment;
    }

    public List<Employee> getAllEmployeesByJob(int idJob){
        List<Employee> employeeList = findAllEmployees();
        List<Employee> employeesListByJob = new ArrayList<>();
        Job job = jobRepository.findById(idJob).get();
        for(Employee employee: employeeList){
            if(employee.getJobCategory()==job){
                employeesListByJob.add(employee);
            }
        }
        return employeesListByJob;
    }

    public Employee saveEmployee(Employee employee){
        return this.employeeRepository.save(employee);
    }

    public Employee addEmployee2(Employee employee, int idDepartment, int idJobCategory){
        employee.setJobCategory(jobRepository.findById(idJobCategory).get());
        employee.setDepartment(departmentRepository.findById(idDepartment).get());
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee){
        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setJobCategory(employee.getJobCategory());
        employee1.setDepartment(employee.getDepartment());
        employee1.setManager(employee.isManager());
        employee1.setStartDate(employee.getStartDate());
        employee1.setEndDate(employee.getEndDate());
        employee1.setActive(employee.isActive());
        employee1.setAddress(employee.getAddress());
        employee1.setEmail(employee.getEmail());
        employee1.setTelephone(employee.getTelephone());
        employee1.setPostalCode(employee.getPostalCode());
        employee1.setBirthday(employee.getBirthday());
        employee1.setNoChildern(employee.getNoChildern());
        employee1.setStudies(employee.getStudies());
        employee1.setSalary(employee.getSalary());
        employee1.setSocialSecurityNumber(employee.getSocialSecurityNumber());
        employee1.setHasDrivingLicense(employee.isHasDrivingLicense());
        employeeRepository.save(employee1);
        return employee1;
    }

    public Employee updateEmployee2(int id, Employee employee, int jobId, int departmentId){
        Employee employee1 = findEmployeeById(id);
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setJobCategory(jobRepository.findById(jobId).get());
        employee1.setDepartment(departmentRepository.findById(departmentId).get());
        employee1.setManager(employee.isManager());
        employee1.setStartDate(employee.getStartDate());
        employee1.setEndDate(employee.getEndDate());
        employee1.setActive(employee.isActive());
        employee1.setAddress(employee.getAddress());
        employee1.setEmail(employee.getEmail());
        employee1.setTelephone(employee.getTelephone());
        employee1.setPostalCode(employee.getPostalCode());
        employee1.setBirthday(employee.getBirthday());
        employee1.setNoChildern(employee.getNoChildern());
        employee1.setStudies(employee.getStudies());
        employee1.setSalary(employee.getSalary());
        employee1.setSocialSecurityNumber(employee.getSocialSecurityNumber());
        employee1.setHasDrivingLicense(employee.isHasDrivingLicense());
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
