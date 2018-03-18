/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Discipline;
import model.RegisterUser;

/**
 * Servlet class that is used when the administrator registers a new student.
 * @author User
 */
public class RegisterNewStudentServlet extends HttpServlet {

    // Default empty constructor
   public RegisterNewStudentServlet() {
       
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
        
        // Get all parameters from the registerStudent.jsp page and store them in strings.
        String facultyNumber = request.getParameter("txtFacultyNumber");
        String name = request.getParameter("txtFirstName");
        String surname = request.getParameter("txtLastName");
        String pIdNumber = request.getParameter("txtPidNumber");
        String phone = request.getParameter("txtPhone");
        String faculty = request.getParameter("txtFaculty");
        String specialty = request.getParameter("txtSpecialty");
        String degree = request.getParameter("txtDegree");
        String email = request.getParameter("txtEmail");
        
        // Declare a new RegisterUser variable.
        RegisterUser user = new RegisterUser();
        
        // Set the parameters from the registerStudent.jsp page to the newly created RegisterUser from above.
        user.setFacultyNumber(facultyNumber);
        user.setName(name);
        user.setSurname(surname);
        user.setpIdNumber(pIdNumber);
        user.setPhone(phone);
        user.setFaculty(faculty);
        user.setSpecialty(specialty);
        user.setDegree(degree);
        user.setEmail(email);
        
        // Declare a new UserDao variable.
        UserDao registerDao = new UserDao();
   
        // Declare a string that will hold the success message.
        String smsg = "Successful registration! New student created with username: " + facultyNumber + " and password: " + pIdNumber;
        
        // Call to the addStudentCredentials method. The method adds login credentials.
        registerDao.addStudentCredentials(user);
        
        // Add the newly created student to the db.
        registerDao.addStudent(user);
        
        // By taking the student's specialty, list all disciplines for the specialty.
        List<Discipline> l = registerDao.listDisciplines(specialty);
        
        // Add initial grades for the cnewly created student.
        registerDao.addInitialGrades(facultyNumber, l);
        
        // Set the request attribute holding the success message.
        request.setAttribute("successMsg", smsg);
         
        // Forward the request to the RegisterNewStudent.jsp page.
        request.getRequestDispatcher("RegisterNewStudent.jsp").forward(request, response);
    }    
}

