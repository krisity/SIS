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
 * Servlet class that is used to show all information for the currently logged student.
 * 
 * @author User
 */
public class StudentProfile extends HttpServlet {

    // Declare a string variable that will hold the path to the StudentProfile.jsp page.
    private static final String LIST_STUDENT_INFO = "/StudentProfile.jsp";
    
    // Declare a string variable that will hold the path to the StudentGrades.jsp page.
    private static final String LIST_STUDENT_GRADES = "/StudentGrades.jsp";
    
    // Declare a global string variable that will hold the faculty number of the student.
    private String facN;
    
    // Declare a List variable holding the disciplines for the student's specialty.
    private List<Discipline> showDisc;
    
    // Declare a List variable holding the grades of the student.
    private List<Grades> showGr;
    
    // Declare a UserDao variable.
    private final UserDao dao;
    
    // Default constructor for the servlet.
    public StudentProfile(){
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
       
       // Check if the action parameter is listStudentInfo.
        if (action.equalsIgnoreCase("listStudentInfo")) {

            // Change the empty forward string to be the path to the StudentProfile.jsp page.
            forward = LIST_STUDENT_INFO;
            
            // Get the faculty number parameter and store it in a string variable.
            String facultyNumber = request.getParameter("facultyNumber");

            // Get the faculty number parameter and store it in the previously declared global variable facN.
            facN = request.getParameter("facultyNumber");
            
            // Set an attribute holding info for the logged student. This attribute 
            // is sent back to the client-side (i.e. the jsp).
            request.setAttribute("user", dao.searchStudent(facultyNumber));
        }
        
        // Check if the action is listStudentGrades:
        if (action.equalsIgnoreCase("listStudentGrades")){

            // Change the empty forward string to be the path to the StudentGrades.jsp page.
            forward = LIST_STUDENT_GRADES;

            // Get the faculty number parameter and store it in a string variable.
            String facultyNumber = request.getParameter("facultyNumber");

            // Get the faculty number parameter and store it in the previously declared global variable facN.
            facN = request.getParameter("facultyNumber");

            // Search student by the faculty number and assign the results
            // to a variable from the RegisterUser class.
            RegisterUser user = dao.searchStudent(facultyNumber);
            
            // Set attribute with name "user" and all the parameters for the given student.
            request.setAttribute("user", user);
            
            // Get the student specialty and assign it to a string called "specialty"
            String specialty = user.getSpecialty();

            // Declare a list with disciplines that holds all disciplines for the current
            // student and his/hers specialty.
            List<Discipline> showDisciplines = dao.showDisciplines(specialty); 
           
            // Assign all disciplines for the current student and his/hers specialty 
            // to the previously created global variable showDisc. 
            showDisc = dao.showDisciplines(specialty);
            
            // Set the attribute "students" with all the disciplines which is passed back to the jsp page.
            request.setAttribute("students", showDisciplines);
            
            // Get all grades for the currently logged student and store them in 
            // a list.
            List<Grades> showGrades = dao.showGrades(facultyNumber);
            
            // Get all grades for the currently logged student and store them in 
            // the global list variable - showGr which was previously declared.
            showGr = dao.showGrades(facultyNumber);
            
            // Set the attribute "grades" with all  the grades which is then passed back to the jsp page.
            request.setAttribute("grades", showGrades);
     }
        // Get the request dispatcher.
        RequestDispatcher view = request.getRequestDispatcher(forward);

        // Forward the view to the correct jsp page.
        view.forward(request, response);
    }
       
 
//    /**
//     * Override of the post method.
//     * 
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException 
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        // Declare a new RegisterUSer variable.
////        RegisterUser student = new RegisterUser();
//// 
////        // Set all para
////        student.setFacultyNumber(request.getParameter("facultyNumber"));
////        student.setName(request.getParameter("name"));
////        student.setSurname(request.getParameter("surname"));
////        student.setpIdNumber(request.getParameter("pIdNumber"));
////        student.setPhone(request.getParameter("phone"));
////        student.setFaculty(request.getParameter("faculty"));
////        student.setSpecialty(request.getParameter("specialty"));
////        student.setDegree(request.getParameter("degree"));
////        student.setEmail(request.getParameter("email"));
////        
////      //  String facultyNumber = request.getParameter("facultyNumber");
////        
////        //dao.searchStudent(facultyNumber);
////        
////        RequestDispatcher view = request.getRequestDispatcher(LIST_STUDENT_INFO);
////
////        request.setAttribute("user", dao.searchStudent(request.getParameter("facultyNumber")));
////
////        view.forward(request, response);
//    }
    }

