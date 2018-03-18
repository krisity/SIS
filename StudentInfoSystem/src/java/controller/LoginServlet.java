/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.RegisterUser;
import model.User;

/**
 * Servlet class that is used to verify the login of a user into the system.
 * 
 * @author User
 */
public class LoginServlet extends HttpServlet {

    // Default empty constructor.
    public LoginServlet() {
        
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
        
        // Get the txtUsername parameter from the index.jsp and store it
        // as a string username.
        String username = request.getParameter("txtUsername");
        
        // Get the txtPassword parameter from the index.jsp and store it
        // as a string username.
        String password = request.getParameter("txtPassword");
        
        // Declare an instance of the User class.
        User user = new User();
        
        // Set the username of the above declared user. The username is the 
        // text entered by the person inside the username textfield.
        user.setUsername(username);
        
        // Set the password of the above declared user. The password is the 
        // text entered by the person inside the password textfield.
        user.setPassword(password);
        
        // Declare an instance of the USerDao class.
        UserDao loginDao = new UserDao();
        
        // Try to authenticate the user.
        try{
            
            // Call the authenticateUser method in order to validate the user credentials 
            // The method returns a string with the role of the user. 
            String userValidate = loginDao.authenticateUser(user);
            
            // Check if the role of the user is "admin"
            if (userValidate.equals("admin_role")) {

                // Get the request session and store it in a httpsession variable.
                HttpSession session = request.getSession();
                
                // Set the session attribute with the username.
                session.setAttribute("Admin", username);
                
                // Set the request attribute with the username.
                request.setAttribute("username", username);

                // Get the request dispatcher and forward it to AdminPage.jsp
                request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
                
              // Check if the role of the user is student_role.  
            } else if (userValidate.equals("student_role")) {
                
                // Get the request session and store it in a httpsession variable.
                HttpSession session = request.getSession();
                
                // Set the session attribute with the username.
                session.setAttribute("Student", username);
                
                // Set the request attribute with the username.
                request.setAttribute("username", username);
                
                // Search for a student by a username and set the attribute.
                request.setAttribute("student", loginDao.searchStudent(username));
                
                // Declare an instance of the RegisterUser class.
                RegisterUser student = new RegisterUser();
 
                // Set the faculty number of the student instance declared above
                // by getting the facultyNumber parameter.
                student.setFacultyNumber(request.getParameter("facultyNumber"));
                
                // Set the name of the student instance declared above by getting 
                // the name request parameter.
                student.setName(request.getParameter("name"));
                 
                // Set the request parameter and forward it to StudentPAge.jsp.
                request.getRequestDispatcher("StudentPage.jsp").forward(request, response);
            }
            
            // If the role of the user is neither student nor admin.
            else {
                
                //System.out.println("Error message = "+userValidate);
                
                // Set request attribute for the error message
                request.setAttribute("errMessage", userValidate);
                
                // Get the request dispatcher and forward the request to index.jsp
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        
         // Catch a possible exception and handle it.
        }catch(IOException | ServletException e1){
            
        }
    }
}