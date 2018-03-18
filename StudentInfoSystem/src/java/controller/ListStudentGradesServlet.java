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
import model.Discipline;
import model.Grades;
import model.RegisterUser;

/**
 * Servlet class that is used to get all student grades from the database.
 * 
 * @author User
 */
public class ListStudentGradesServlet extends HttpServlet {

    // Declare a static string variable that will hold the path to the 
    // listStudentGrades.jsp
    private static final String LIST_STUDENT_GRADES = "/ListStudentsGrades.jsp";
    
    // Declare a UserDao variable from the UserDao class.
    private final UserDao dao;
    
    // Declare a string variable that will hold the faculty number of the student.
    private String facN;
    
    // Declare a list that will hold the disciplines of a given specialty.
    private List<Discipline> showDisc;
    
    // Declare a list that will hold the grades for a student.
    private List<Grades> showGr;
    
    /**
     * Constructor for the servlet that only declares a new UserDao variable.
     */
    public ListStudentGradesServlet(){
        
        // Create a new instance of UserDao class. 
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

        // Declare an empty string. 
        String forward="";

        // Get the "action" request parameter and store it as a string.
        String action = request.getParameter("action");
        
        // Check if the action is of type "listStudent".
        if (action.equalsIgnoreCase("listStudent")){

            // Set the forward string to the listStudentGrades.jsp.
            forward = LIST_STUDENT_GRADES;

            // Set the attribute for the request by getting all the students.
            // The name of the attribute is "students".
            request.setAttribute("students", dao.getAllStudents());
            
            // Get the request dispatcher.
            RequestDispatcher view = request.getRequestDispatcher(forward);
            
            // Forward the request.
            view.forward(request, response);
     }
        // Check if the action is of type "addGrade".
        if (action.equalsIgnoreCase("addGrade")){

            // When clicking on the "Add Grade" hyperlink assign the addGrades.jsp to the forward string
            forward = "/addGrades.jsp";
            
            // Get the "facultyNumber" parameter from the request (i.e. from the ListStudentsGrades.jsp)
            // and assign it to a string value.
            String facultyNumber = request.getParameter("facultyNumber");

            // Get the "facultyNumber" request parameter and assign it to the facN string.
            facN = request.getParameter("facultyNumber");
            
            // Search student by the faculty number and assign the results
            // to a variable from the RegisterUser class.
            RegisterUser user = dao.searchStudent(facultyNumber);
            
            // Set attribute with name "user" and all the parameters for the given student
            request.setAttribute("user", user);
            
            // Get the student specialty and assign it to a string called "specialty"
            String specialty = user.getSpecialty();

            // Declare a list with disciplines that holds all disciplines for the current
            // student and his/hers specialty.
            List<Discipline> showDisciplines = dao.showDisciplines(specialty);
            
            // Show the disciplines for the given specialty of the student and assign it to the showDisc list.
            showDisc = dao.showDisciplines(specialty);
            
            // Set the attribute "students" with all the disciplines. The students
            // attribute is then passed back.
            request.setAttribute("students", showDisciplines);
            
            // Get the request dispatcher.
            RequestDispatcher view = request.getRequestDispatcher(forward);
            
            // Forward the request
            view.forward(request, response);
     
     }
        // Check if the action is of type "updateGrade"
        if (action.equalsIgnoreCase("updateGrade")){
            
            // When clicking on the "Update Grade" hyperlink assign the 
            // UpdateStudentGrades.jsp to the forward string
            forward = "/UpdateStudentGrades.jsp";
            
            // Get the "facultyNumber" parameter from the request (i.e. from the ListStudentsGrades.jsp)
            // and assign it to a string value
            String facultyNumber = request.getParameter("facultyNumber");

            // Get the "facultyNumber" request parameter and assign it to the facN string.
            facN = request.getParameter("facultyNumber");
            
            // Search student by the faculty number and assign the results
            // to a variable from the RegisterUser class.
            RegisterUser user = dao.searchStudent(facultyNumber);
            
            // Set attribute with name "user" and all the parameters for the given student.
            request.setAttribute("user", user);
           
            // Get the student specialty and assign it to a string called "specialty".
            String specialty = user.getSpecialty();

            // Declare a list with disciplines that holds all disciplines for the current
            // student and his/hers specialty.
            List<Discipline> showDisciplines = dao.showDisciplines(specialty);
            
            // Show the disciplines for the given specialty and assign them the 
            // showDisc list.
            showDisc = dao.showDisciplines(specialty);
             
            // Set the attribute "students" with all the disciplines.
            // The attribute is then passed back to the new jsp.
            request.setAttribute("students", showDisciplines);
            
            // Show the grades of the student depending on his/hers faculty number
            // and assign the grades to a list of grades.
            List<Grades> showGrades = dao.showGrades(facultyNumber);
            
            // Show the grades of the student depending on his/hers faculty number
            // and assign the grades to the showGr list declared earlier.
            showGr = dao.showGrades(facultyNumber);
            
            // Set the request attribute "grades" with all student grades.
            request.setAttribute("grades", showGrades);
          
            // Get the request dispatcher.
            RequestDispatcher view = request.getRequestDispatcher(forward);
            
            // Forward the request to the jsp.
            view.forward(request, response);
    }
}
    /**
     * Override of the post method
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        // Declare a String array to hold all grades which are entered in the addGrades.jsp 
        // by the "admin".
        String[] gradesList = request.getParameterValues("txtGrade");

        /**
         * The gradesList and the Disciplines list are the same size = >
         * there are always the same number of disciplines and the same number of grades
         * Because of that we iterate through lets say the grades list, we get the corresponding
         * discipline and then we save it through the addIdToGrades method that we have created in the 
         * dao package
         */
        // Start by iteration through the gradesList.
        for(int i = 0; i < gradesList.length; i++) {
            
            // Get the discipline and save it to a string.
            String disc = showDisc.get(i).getDiscipline();
            
            // Get the grade from the gradesList and save it to a string.
            String grade = gradesList[i];
            
            // Declare a new instance of the Grades class.
            Grades grades = new Grades();
            
            // Set the student faculty number.
            grades.setStudent_id(facN);
            
            // Set the discipline.
            grades.setDiscipline(disc);
            
            // Set the grade.
            grades.setGrade(grade);
            
            // Add the grades to the UserDao variable.
            dao.addGrades(grades);
        }

        // Get the request dispatcher.
        RequestDispatcher view = request.getRequestDispatcher("/ListStudentsGrades.jsp");
        
        // Declare a string that will hold the success message when a student has been graded.
        String msg = "Success! Student with faculty number: " + facN + " has been graded! " ;
        
        // Set the attribute with the success message.
        request.setAttribute("successMsg", msg);
        
        // Get all students and set the attribute. 
        request.setAttribute("students", dao.getAllStudents());

        // Forward the request.
        view.forward(request, response);
        
    }
}

