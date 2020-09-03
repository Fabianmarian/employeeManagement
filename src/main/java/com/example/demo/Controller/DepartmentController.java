package com.example.demo.Controller;

import com.example.demo.Model.DAO.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable int id){
        Department department = this.departmentService.findDepartmentById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Department found");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(department);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = this.departmentService.findAllDepartments();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "List created");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(departments);
    }

    @PostMapping("/addDepartment")
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department){
        Department newDepartment = this.departmentService.addDepartment(department);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewDepartment");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHeaders).body(newDepartment);
    }

    @PutMapping("/updateDepartment/{id}/{name}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id, @PathVariable String name){
        Department department = this.departmentService.updateDepartment(id, name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Department updated");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHeaders).body(department);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public void deleteDepartment(@PathVariable int id){
        this.departmentService.deleteDepartmentById(id);
    }
}
