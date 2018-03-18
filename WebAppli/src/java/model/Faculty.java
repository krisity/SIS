/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class that is used to show the relation of faculty and specialty of a student.
 * @author User
 */
public class Faculty {
    
    // Declare 2 strings - 1 for faculty and 1 for specialty.
    String faculty, specialty;

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
}
