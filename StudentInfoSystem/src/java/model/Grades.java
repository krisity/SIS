/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class that is used to show the grades of students - each student has multiple 
 * disciplines with one grade per discipline.
 * @author User
 */
public class Grades {
    
    // Declare 3 strings - 1 for the student faculty number, 1 for the grade and 1 for the discipline.
    private String student_id, grade, discipline;

    // Getter for the student faculty number.
    public String getStudent_id() {
        return student_id;
    }

    // Setter for the student faculty number.
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    // Getter for the grade.
    public String getGrade() {
        return grade;
    }

    // Setter for the grade.
    public void setGrade(String grade) {
        this.grade = grade;
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
