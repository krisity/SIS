/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class that is used when registering a student - contains information about the 
 * student general information such as name, faculty number etc.
 * @author User
 */
public class RegisterUser {
    
    // The variables which appear on the student registration page.
    private String facultyNumber, name, surname, pIdNumber, phone, faculty,specialty, degree, email;  

    // Getter for the faculty number.
    public String getFacultyNumber() {
        return facultyNumber;
    }

    // Setter for the faculty number.
    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    // Getter for the name.
    public String getName() {
        return name;
    }

    // Setter for the name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the surname.
    public String getSurname() {
        return surname;
    }

    // Setter for the surname.
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter for the personal ID number.
    public String getpIdNumber() {
        return pIdNumber;
    }

    // Setter for the personal ID number.
    public void setpIdNumber(String pIdNumber) {
        this.pIdNumber = pIdNumber;
    }

    // Getter for the phone.
    public String getPhone() {
        return phone;
    }
    
    // Setter for the phone.
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for the faculty.
    public String getFaculty() {
        return faculty;
    }

    // Setter for the faculty.
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    // Getter for the specialty.
    public String getSpecialty() {
        return specialty;
    }

    // Setter for the specialty.
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // Getter for the degree.
    public String getDegree() {
        return degree;
    }

    // Setter for the degree.
    public void setDegree(String degree) {
        this.degree = degree;
    }

    // Getter for the email.
    public String getEmail() {
        return email;
    }

    // Setter for the email.
    public void setEmail(String email) {
        this.email = email;
    }
}
