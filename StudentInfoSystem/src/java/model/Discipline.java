/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class that is used to show the relation of specialty and disciplines of a student.
 * 
 * @author User
 */
public class Discipline {
    
    // Declare 2 string variables - one for specialty and one for discipline.
    private String specialty, discipline;

    // Getter for the specialty.
    public String getSpecialty() {
        return specialty;
    }

    // Setter for the specialty.
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // Getter for the discipline.
    public String getDiscipline() {
        return discipline;
    }

    // Setter for the discipline.
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
