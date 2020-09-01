package com.example.demo.Controller;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Model.DAO.Employee;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Model.DTO.EmployeeDto;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee = this.employeeService.findEmployeeById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Employee found");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @GetMapping("/findAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = this.employeeService.findAllEmployee();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "List created");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employeeList);
    }

    @GetMapping("/findEmployeeDtoById/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeDtoById(@PathVariable int id){
        EmployeeDto employeeDto = this.employeeService.findEmployeeDtoById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Employee DTO found");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employeeDto);
    }

    @GetMapping("/findAllEmployeesDto")
    public ResponseEntity<List<EmployeeDto>> findAllEmployeesDto(){
        List<EmployeeDto> employeeDtoList = this.employeeService.findAllEmployeeDto();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "List created");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employeeDtoList);
    }

    @PostMapping("/addEmployee/{jobCategory}/{department}")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee, @PathVariable int jobCategory, @PathVariable int department){
        Employee newEmployee = this.employeeService.saveEmployee(employee, jobCategory, department);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewEmployee");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(newEmployee);
    }

    @PutMapping("/updateEmployee/{id}/{jobId}/{departmentId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee, @PathVariable int jobId, @PathVariable int departmentId){
        Employee employeeUpdated = this.employeeService.updateEmployee(id, employee, jobId, departmentId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Employee updated");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employeeUpdated);
    }

    @PutMapping("/updateEmployeeFirstName/{id}/{firstName}")
    public ResponseEntity<Employee> updateEmployeeFirstName(@PathVariable int id, @Valid @PathVariable String firstName){
        Employee employee = this.employeeService.updateEmployeeFirstName(id, firstName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated first name");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeLastName/{id}/{lastName}")
    public ResponseEntity<Employee> updateEmployeeLastName(@PathVariable int id, @Valid @PathVariable String lastName){
        Employee employee = this.employeeService.updateEmployeeLastName(id, lastName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated last name");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeManager/{id}/{status}")
    public ResponseEntity<Employee> updateEmployeeManager(@PathVariable int id, @PathVariable boolean status){
        Employee employee = this.employeeService.updateEmployeeManager(id, status);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated manager status");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeJob/{id}/{jobId}")
    public ResponseEntity<Employee> updateEmployeeJob(@PathVariable int id, @Valid @PathVariable int jobId){
        Employee employee = this.employeeService.updateEmployeeJob(id, jobId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated job id");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeDepartment/{id}/{departmentId}")
    public ResponseEntity<Employee> updateEmployeeDepartment(@PathVariable int id, @Valid @PathVariable int departmentId){
        Employee employee = this.employeeService.updateEmployeeDepartment(id, departmentId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated department");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeStartDate/{id}/{startDate}")
    public ResponseEntity<Employee> updateEmployeeStartDate(@PathVariable int id, @Valid @PathVariable LocalDate startDate){
        Employee employee = this.employeeService.updateEmployeeStartDate(id, startDate);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated start date");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeEndDate/{id}/{endDate}")
    public ResponseEntity<Employee> updateEmployeeEndDate(@PathVariable int id, @PathVariable LocalDate endDate){
        Employee employee = this.employeeService.updateEmployeeEndDate(id, endDate);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated end date");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeActive/{id}/{status}")
    public ResponseEntity<Employee> updateEmployeeActive(@PathVariable int id, @Valid @PathVariable boolean status){
        Employee employee = this.employeeService.updateEmployeeActive(id, status);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated active status");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeAddress/{id}/{address}")
    public ResponseEntity<Employee> updateEmployeeAddress(@PathVariable int id, @Valid @PathVariable String address){
        Employee employee = this.employeeService.updateEmployeeAddress(id, address);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated address");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeePostalCode/{id}/{postalCode}")
    public ResponseEntity<Employee> updateEmployeePostalCode(@PathVariable int id, @Valid @PathVariable int postalCode){
        Employee employee = this.employeeService.updateEmployeePostalCode(id, postalCode);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated postal code");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeTelephone/{id}/{telephone}")
    public ResponseEntity<Employee> updateEmployeeTelephone(@PathVariable int id, @Valid @PathVariable String telephone){
        Employee employee = this.employeeService.updateEmployeeTelephone(id, telephone);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated telephone");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeEmail/{id}/{email}")
    public ResponseEntity<Employee> updateEmployeeEmail(@PathVariable int id, @Valid @PathVariable String email){
        Employee employee = this.employeeService.updateEmployeeEmail(id, email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated email");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeBirthday/{id}/{birthday}")
    public ResponseEntity<Employee> updateEmployeeBirthday(@PathVariable int id, @Valid @PathVariable LocalDate birthday){
        Employee employee = this.employeeService.updateEmployeeBirthday(id, birthday);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated birthday");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeNoChildren/{id}/{noChildren}")
    public ResponseEntity<Employee> updateEmployeeNoChildren(@PathVariable int id, @PathVariable int noChildren){
        Employee employee = this.employeeService.updateEmployeeNoChildren(id, noChildren);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated children number");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeSalary/{id}/{salary}")
    public ResponseEntity<Employee> updateEmployeeSalary(@PathVariable int id, @Valid @PathVariable double salary){
        Employee employee = this.employeeService.updateEmployeeSalary(id, salary);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated salary");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeStudies/{id}/{studies}")
    public ResponseEntity<Employee> updateEmployeeStudies(@PathVariable int id, @Valid @PathVariable String studies){
        Employee employee = this.employeeService.updateEmployeeStudies(id, studies);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated studies");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeSSN/{id}/{ssn}")
    public ResponseEntity<Employee> updateEmployeeSSN(@PathVariable int id, @Valid @PathVariable int ssn){
        Employee employee = this.employeeService.updateEmployeeSSN(id, ssn);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated ssn");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @PutMapping("/updateEmployeeDriving/{id}/{status}")
    public ResponseEntity<Employee> updateEmployeeDriving(@PathVariable int id, @PathVariable boolean status){
        Employee employee = this.employeeService.updateEmployeeDriving(id, status);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Updated driving license status");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable int id){
        this.employeeService.deleteEmployeeById(id);
    }

}
