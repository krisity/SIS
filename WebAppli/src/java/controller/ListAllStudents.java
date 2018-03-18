
package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class that is used to get all the students from the database.
 * 
 * @author User
 */
public class ListAllStudents extends HttpServlet {
   
    // Declare a static string variable that will hold the path to the 
    // listStudent.jsp
    private static final String LIST_STUDENT = "/listStudent.jsp";
    
    // Declare a UserDao variable from the UserDao class.
    private final UserDao dao;
    
    /**
     * Constructor for the servlet that only declares a new UserDao variable.
     */
    public ListAllStudents(){
        
        // Create a new instance of UserDao class. 
        dao = new UserDao();
    }
    
    /**
     * Get method that handles the "list all students" request.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Declare a string variable that will initially be an empty character.
        String forward = "";

        // Get the "action" parameter from the request and store it as a 
        // string variable to use it later on.
        String action = request.getParameter("action");
        
        // Check whether the string "action" is actually "listStudent"
        if (action.equalsIgnoreCase("listStudent")){

            // Set the forward string to be equal to the LIST_STUDENT
            forward = LIST_STUDENT;

            // Set the attribute for the request by getting all the students.
            // The name of the attribute is "students".
            request.setAttribute("students", dao.getAllStudents());
            
     }
        // Get the request dispatcher
        RequestDispatcher view = request.getRequestDispatcher(forward);

        // Forward the request dispatcher
        view.forward(request, response);
     }
    
    /**
     * Override of the post method that handles the listStudent action.
     * 
     * @param request
     * @param response 
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        // Get the request dispatcher
        RequestDispatcher view = request.getRequestDispatcher(LIST_STUDENT);

        // Get all students and set the attribute of the request
        request.setAttribute("students", dao.getAllStudents());

        // Forward the request back.
        view.forward(request, response);
    }
}
