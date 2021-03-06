package com.example.demo.Model.DAO;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="job_categories")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(min=1)
    private String name;

    //@OneToMany(mappedBy = "jobCategory", fetch = FetchType.LAZY)
    //private List<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
*/

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name;
    }
}

