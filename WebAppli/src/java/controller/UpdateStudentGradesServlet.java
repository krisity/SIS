/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Grades;
import model.RegisterUser;

/**
 * Servlet class that is used when an administrator is updating students' grades.
 * @author User
 */
public class UpdateStudentGradesServlet extends HttpServlet {
    
    // Declare a UserDao class variable.
    private final UserDao dao;
    
    // Declare a string variable that will be used to hold the faculty number of the student.
    private String facN;
    
    // Declare a list variable that will hold the student grades.
    private List<Grades> showGr;
    
    // Default constructor for the servlet.
    public UpdateStudentGradesServlet(){
        
        // Initialize the UserDao class variable.
        dao = new UserDao();
    }
    
    /**
     * Override of the get method.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Declare an empty string that will be used for quick forwarding through the jsp pages.
        String forward="";

        // Declare a string that will hold the action parameter.
        String action = request.getParameter("action");
        
        // Check if the action is "updateGrade" i.e. if the user has clicked on "Update Grade".
        if (action.equalsIgnoreCase("updateGrade")){

            // When clicking on the "Update Grade" hyperlink assign the UpdateStudentGrades.jsp to the forward string
            forward = "/UpdateStudentGrades.jsp";
            
            // Get the "facultyNumber" parameter from the request (i.e. from the ListStuudentsGrades.jsp)
            // and assign it to a string value
            String facultyNumber = request.getParameter("facultyNumber");

            // Get the facultyNumber parameter and set it to global variable facN
            // that was declared earlier.
            facN = request.getParameter("facultyNumber");
            
            // Search student by the faculty number and assign the results
            // to a variable from the RegisterUser class.
            RegisterUser user = dao.searchStudent(facultyNumber);
            
            // Set attribute with name "user" and all the parameters for the given student
            request.setAttribute("user", user);
           
            // Get the student specialty and assign it to a string called "specialty"
            String specialty = user.getSpecialty();
            
            // Get the full name of the specialty and assign it to a string called fullNameSpecialty.
            String fullNameSpecialty = dao.listFullNameOfSpecialty(specialty);

            // Set attribute "specialty" that is actually the full name of the specialty of the selected student.
            request.setAttribute("specialty", fullNameSpecialty);
            
           // Get all grades for the selected student and store them in a list variable.
            List<Grades> showGrades = dao.showGrades(facultyNumber);
            
            // Get all grades for the selected student and assign them in the global list variable 
            // declared earlier.
            showGr = dao.showGrades(facultyNumber);
            
            // Set attribute "grades" with all grades for the selected student.
            request.setAttribute("grades", showGrades);
            
     }
        // Get the requested Dispatcher.
        RequestDispatcher view = request.getRequestDispatcher(forward);

        // Forward the request to the correct jsp.
        view.forward(request, response);
 
    }
    
    /**
     * Override of the post method.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        // Declare a String array to hold all grades which are entered in the UpdateStudentGrades.jsp 
        // by the "admin"
        String[] gradesList = request.getParameterValues("txtGrade");
         
        // Loop  through the String array 
        for(int i = 0; i < gradesList.length; i++) {

            // Get each of the grades and store them as a string called grade.
            String grade = gradesList[i];
            
            // Get the list with all grades for the selected student, get the first grade
            // and get the discipline for that grade.
            String disc = showGr.get(i).getDiscipline();
    
            // Declare a new variable grades fromt he Grades class.
            Grades grades = new Grades();

            // Set the faculty number of the student to the variable grades
            grades.setStudent_id(facN);
            
            // Set the discipline of the student to the variable grades
            grades.setDiscipline(disc);
            
            // Set the grade of the student to the variable grades
            grades.setGrade(grade);
            
            // Call to the updateStudentGrades method.
            dao.updateStudentGrades(grades);
        }

        // Get the getRequestDispatcher that forwards the request to the ListStudentsGrades.jsp page
        RequestDispatcher view = request.getRequestDispatcher("/ListStudentsGrades.jsp");
        
        // Set attribute "studnewts" that will hold info for all students.
        request.setAttribute("students", dao.getAllStudents());

        // Forward the request.
        view.forward(request, response);
    }
}