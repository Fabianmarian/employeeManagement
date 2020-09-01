package com.example.demo.Service;


import com.example.demo.Exception.RestExceptionHandler;
import com.example.demo.Model.DAO.Job;
import com.example.demo.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job findJobById(int id){
        return this.jobRepository.findById(id).get();
    }
    public List<Job> findAllJobs(){
        return this.jobRepository.findAll();
    }
    public Job addJob(Job job){
            return this.jobRepository.save(job);
    }
    public Job updateJob(int id, String name){
        Job job = findJobById(id);
        job.setName(name);
        jobRepository.save(job);
        return job;
    }

    public void deleteJobById(int id){
        this.jobRepository.deleteById(id);
    }
}
