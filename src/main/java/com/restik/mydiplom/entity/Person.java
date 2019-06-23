package com.restik.mydiplom.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Table(name="person_tbl")
@Inheritance(strategy= InheritanceType.JOINED)
public class Person {


    @Id
    @GeneratedValue
    @Column(name="personID",unique=true,nullable=false)
    private long personID;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="roleType")
    private String roleType;



    public Person(){

    }


    public long getPersonID() {
        return personID;
    }
    public void setPersonID(long personID) {
        this.personID = personID;
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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

}