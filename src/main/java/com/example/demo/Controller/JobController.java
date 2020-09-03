package com.example.demo.Controller;

import com.example.demo.Model.DAO.Job;
import com.example.demo.Service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/jobcategories")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/getJobCategoryById/{id}")
    public ResponseEntity<Job> gejobById(@PathVariable int id){
        Job job = this.jobService.findJobById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Job found");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(job);
    }

    @GetMapping("/getAllJobCategories")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = this.jobService.findAllJobs();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "List created");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(jobs);
    }

    @PostMapping("/addJobCategory")
    public ResponseEntity<Job> addJob(@Valid @RequestBody Job job){
        Job newJob = this.jobService.addJob(job);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "addedNewJob");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHeaders).body(newJob);
    }

    @PutMapping("/updateJob/{id}/{name}")
    public ResponseEntity<Job> updateJob(@PathVariable int id, @PathVariable String name){
        Job job = this.jobService.updateJob(id, name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response", "Job updated");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHeaders).body(job);
    }

    @DeleteMapping("/deleteJobCategory/{id}")
    public void deleteJob(@PathVariable int id){
        this.jobService.deleteJobById(id);
    }

}
