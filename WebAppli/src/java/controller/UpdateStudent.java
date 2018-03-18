/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Faculty;
import model.RegisterUser;

/**
 * Servlet class that is used when an administrator is updating the information for a specific student.
 * @author User
 */
public class UpdateStudent extends HttpServlet {

    // Declare a string variable that will hold the path to updateStudent.jsp page.
    private static final String EDIT = "/updateStudent.jsp";
      
    // Declare a UserDao variable.
    private final UserDao dao;
    
    // Default constructor for the UpdateStudent servlet.
    public UpdateStudent(){
        
        // Initialize the UserDao variable.
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
        
        // Check if the action is "edit" i.e. if the user has clicked on "Update", not on "Delete".
        if (action.equalsIgnoreCase("edit")){

            // Change the empty forward string to be the path to the updateStudent.jsp page.
            forward = EDIT;

            // Get the facultyNumber parameter and store it in a string variable.
            String facultyNumber = request.getParameter("facultyNumber");

            // Load the info for the currently selected user by his/hers facultyNumber.
            RegisterUser user = dao.searchStudent(facultyNumber);

            // Set an attribute "user" holding info for the selected student. This attribute 
            // is sent back to the client-side (i.e. the updateStudent.jsp).
            request.setAttribute("user", user);
            
            // Get the faculty of the selected user and store it in a string.
            String studentFaculty = user.getFaculty();
            
            // Get all other faculties from the db and store them in a list.
            // The list holds REFERENCES to all faculties except for the faculty 
            // of the currently selected student.
            List<Faculty> allFaculties = dao.listFaculty(studentFaculty);

            // Declare a new ArrayList to hold the actual codes for all faculties.
            List<String> facultyArrayList = new ArrayList<>();
            
            // Loop through the allFaculties list and for each item from the list:
            allFaculties.forEach((faculty) -> {
               
                // get the faculty name and add it to the facultyArrayList list.
                // After this is done the facultyArrayList is populated with the 
                // codes for all faculties except for the faculty in which the 
                // selected student is in.
                facultyArrayList.add(faculty.getFaculty());
            });
            
            // Set an attribute "faculty" which holds the codes for all faculties 
            // except for the faculty in which the selected student is in.
            request.setAttribute("faculty", facultyArrayList);
            
            // Get the specialty of the selected student and store it in a string variable.
            String specialty = user.getSpecialty();
            
            // Get all other specialties from the db and store them in a list.
            // The list holds REFERENCES to all specialties except for the specialty 
            // of the currently selected student.
            List<Faculty> allSpecialties = dao.listSpecialty(specialty);
            
            // Declare a new ArrayList to hold the actual codes for all specialties.
            List<String> specialtiesArrayList = new ArrayList<>();
            
            // Loop through the allSpecialties list and for each item from the list:
            allSpecialties.forEach((specialty1) -> {
                
                // get the specialty name and add it to the specialtiesArrayList list.
                // After this is done the specialtiesArrayList is populated with the 
                // codes for all specialties except for the specialty in which the 
                // selected student is in.
                specialtiesArrayList.add(specialty1.getSpecialty());
            });
             
            // Set an attribute "specialty" which holds the codes for all specialties 
            // except for the specialty in which the selected student is in. 
            request.setAttribute("specialty", specialtiesArrayList);
            
            
            
            // Get the degree of the student and store it in a string.
            String degree = user.getDegree();
            
            // Check if the degree of the selected student is Master.
            if(degree.equals("Master")){
                // Declare a string that will hold "Bachelor".
                String bac = "Bachelor";
                
                // Set an attribute "degree" that holds the string bac.
                request.setAttribute("degree", bac);
                
            // Check if the degree of the selected student is Bachelor.
            }else if(degree.equals("Bachelor")){
                
                // Declare a string that will hold "Master".
                String mas = "Master";
                
                // Set an attribute "degree" that holds the string mas.
                request.setAttribute("degree", mas);
                
            }
            
     }
        // Check if the action is delete i.e. if the user has clicked on delete.
        else if (action.equalsIgnoreCase("delete")){

            // Get the student facultyNumber parameter and store it in a string. 
            String studentId = request.getParameter("facultyNumber");

            // Call to the deleteStudent method.
            dao.deleteStudent(studentId);

            // Change the empty forward string to be the path to the listStudent.jsp page.
            forward = "/listStudent.jsp";
            
            // Declare a string that will hold the success message that a student has been deleted.
            String deletemsg = "Success! Student with faculty number: " + studentId + " has been deleted! " ;
            
            // Set attribute "deletemsg" to pass it back to the listStudent.jsp.
            request.setAttribute("deletemsg", deletemsg);

            // Set attribute "students" that will hold all info for all students to 
            // pass it back to the listStudent.jsp.
            request.setAttribute("students", dao.getAllStudents()); 
        }
        
        // Get the RequestDispatcher.
        RequestDispatcher view = request.getRequestDispatcher(forward);

        // Forward the RequestDispatcher to the correct jsp page.
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

        // Declare a RegisterUser variable. 
        RegisterUser student = new RegisterUser();
 
        // Get all parameters from the updateStudent.jsp page and set them as parameters 
        // for the RegisterUser variable.  
        student.setName(request.getParameter("txtname"));
        student.setSurname(request.getParameter("txtsurname"));
        student.setpIdNumber(request.getParameter("txtpIdNumber"));
        student.setPhone(request.getParameter("txtphone"));
        student.setFaculty(request.getParameter("txtfaculty"));
        student.setSpecialty(request.getParameter("txtspecialty"));
        student.setDegree(request.getParameter("txtdegree"));
        student.setEmail(request.getParameter("txtemail"));
        
        // Get the faculty number of the student from the updateStudent.jsp pag
        // and store it in a string variable.
        String facultyNumber = request.getParameter("txtfacultyNumber");

        // Set the faculty number as parameter 
        // for the RegisterUser variable.  
        student.setFacultyNumber(facultyNumber);

        // Call to the updateStudent method.
        dao.updateStudent(student);

        // Declare a string that will hold the success message that the student has been updated.
        String msg = "Success! Student with faculty number: " + facultyNumber + " has been updated! " ;
       
        // Get the getRequestDispatcher.
        RequestDispatcher view = request.getRequestDispatcher("/listStudent.jsp");

        // Set attribute "students" that will hold all students from the db.
        request.setAttribute("students", dao.getAllStudents());

        // Set attribute "successMsg".
        request.setAttribute("successMsg", msg);
        
        // Forward the request to the correct jsp page.
        view.forward(request, response);
    }
}

