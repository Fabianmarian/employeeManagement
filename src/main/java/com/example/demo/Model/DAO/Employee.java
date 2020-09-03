package com.example.demo.Model.DAO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee")
    private int id;

    @Column(name = "firstName")
    @NotNull
    @Size(min=1)
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @Size(min=1)
    private String lastName;


    @ManyToOne
    @JoinColumn(name = "jobCategoryId")
   // @NotNull
    private Job jobCategory;


    @ManyToOne()
    @JoinColumn(name = "departmentId")
    //@NotNull
    private Department department;

    @Column(name = "isManager")
    private boolean isManager;

    @Column(name="startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate startDate;

    @Column(name = "endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @Column(name = "isActive")
    private boolean isActive = true;

    @NotNull
    private String address;

    @Column(name = "CP")
    @NotNull
    private int postalCode;

    @NotNull
    private String telephone;

    private String email;

    @Column(name="birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate birthday;

    @PositiveOrZero
    private int noChildern;

    @NotNull
    private double salary;

    private String studies;

    private int socialSecurityNumber;

    private boolean hasDrivingLicense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Job getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(Job jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getNoChildern() {
        return noChildern;
    }

    public void setNoChildern(int noChildern) {
        this.noChildern = noChildern;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public boolean isHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(boolean hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobCategory=" + jobCategory +
                ", department=" + department +
                ", isManager=" + isManager +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", noChildern=" + noChildern +
                ", salary=" + salary +
                ", studies='" + studies + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", hasDrivingLicense=" + hasDrivingLicense +
                '}';
    }
}