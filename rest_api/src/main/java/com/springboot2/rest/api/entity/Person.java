package com.springboot2.rest.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Full_Name", length = 64, nullable = false)
    private String fullName;

    @Column(name = "Gender", length = 1, nullable = false)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth", nullable = false)
    private Date dateOfBirth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
