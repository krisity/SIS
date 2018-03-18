package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Discipline;
import model.Faculty;
import model.Grades;
import model.RegisterUser;
import model.User;
import util.DbUtil;

/**
 * Class that contains different methods related to the database extraction, update or deletion.
 * 
 * @author User
 * 
 */
public class UserDao {
    
    //First we declare a variable of type Connection
    private final Connection connection;
    
    //A method that uses the getConnection method from the DbUtil package (we need to import the DbUtil package as well)
    public UserDao() {

        // Assign the getConnection method to the created variable connection
        connection = DbUtil.getConnection();

    }
    
    /**
     * Method used to authenticate the user.
     * 
     * @param user A variable of type User.
     * @return A string with the user's role if the user has successfully authenticated or error string.
     */
    public String authenticateUser(User user){
        
        // Get the username of the user and save it in a string.
        String userName = user.getUsername();
        
        // Get the password of the user and save it in a string.
        String password = user.getPassword();
        
        // Initialize the connection variable, set it to null
        Connection conn = null;
        
        // Initialize the statement variable, set it to null
        Statement statement = null;
        
        // Initialize the resultset variable, set it to null
        ResultSet resultSet = null;
        
        // Declare a string variable for the username, set it to null.
        String userNameDB = "";
        
        // Declare a string variable for the password, set it to null.
        String passwordDB = "";
        
        // Declare a string variable for the role of the user, set it to null.
        String roleDB = "";
        
        // Try to authenticate the user
        try {
            
            // Establish a database connection.
            conn = DbUtil.getConnection();
            
            // Create a Statement object for sending SQL statements to the database
            // and store it in a statement variable.
            statement = conn.createStatement();
            
            // Executes the given SQL statement for selecting everything
            // from the users table. Returns a single ResultSet object.
            resultSet = statement.executeQuery("select username, password, role from users");
            
            // While there are results from the query:
            while(resultSet.next()){
                
               // Get the username attribute from the users table and set it to the userNameDB string. 
               userNameDB = resultSet.getString("username");
               
               // Get the password attribute from the users table and set it to the passwordDB string. 
               passwordDB = resultSet.getString("password");
               
               // Get the role attribute from the users table and set it to the roleDB string. 
               roleDB = resultSet.getString("role");
               
               // Check if the username entered by the user equals to one of the usernames from the db;
               // check if the password entered by the user equals to one of the password from the db. 
               // if both the username and the password match those from the db, check the role of the user. 
               // If the role of the user is admin:
               if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin"))
                   
                   // Return a string "admin_role"
                   return "admin_role";
               
               // Check again for username and password verifications. 
               // If the role of the user is student:
               else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("student"))
                   
                   // Return a string "student_role"
                   return "student_role";
            }
         }
        // Catch a possible exception.
         catch(SQLException e)
         {
            }
        
        // If the username or password don't match any of those in the db 
        // return a string with "Invalid user credentials".
        return "Invalid user credentials";
        
    }
    
    /**
     * Method that adds a newly created student to the db.
     * 
     * @param user A parameter of the RegisterUser class.
     */
    public void addStudent(RegisterUser user) {

        // Try to add the student to the db:
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            // i.e. SQL statement with known parameters.
            PreparedStatement preparedStatement = connection

                    .prepareStatement("insert into student_info(facultyNumber, name, surname, pIdNumber, phone, faculty,specialty, degree, email) "
                            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Parameters start with 1

            // Get the faculty number of the passed RegisterUser variable.
            // Set the faculty number to the preparedStatement value. 
            preparedStatement.setString(1, user.getFacultyNumber());

            // Get the name of the passed RegisterUser variable.
            // Set the name to the preparedStatement value.
            preparedStatement.setString(2, user.getName());
            
            // Get the surname of the passed RegisterUser variable.
            // Set the surname to the preparedStatement value.
            preparedStatement.setString(3, user.getSurname());

            // Get the personal id number of the passed RegisterUser variable.
            // Set the personal id number to the preparedStatement value.
            preparedStatement.setString(4, user.getpIdNumber());

            // Get the phone number of the passed RegisterUser variable.
            // Set the phone number to the preparedStatement value.
            preparedStatement.setString(5, user.getPhone());
            
            // Get the faculty  of the passed RegisterUser variable.
            // Set the faculty  to the preparedStatement value.
            preparedStatement.setString(6, user.getFaculty());
            
            // Get the specialty of the passed RegisterUser variable.
            // Set the specialty to the preparedStatement value.
            preparedStatement.setString(7, user.getSpecialty());
            
            // Get the degree of the passed RegisterUser variable.
            // Set the degree to the preparedStatement value.
            preparedStatement.setString(8, user.getDegree());
            
            // Get the email of the passed RegisterUser variable.
            // Set the email to the preparedStatement value.
            preparedStatement.setString(9, user.getEmail());
           
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();

            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
    }
    
    /**
     * Method that searches for a student by faculty number from the db.
     * 
     * @param facultyNumber - A string which is the faculty number of the student.
     * @return 
     */
    public RegisterUser searchStudent(String facultyNumber) {

        // Declare a variable of type RegisterUser.
        RegisterUser student = new RegisterUser();
        
        // Try to search for a student.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            // i.e. SQL statement with known parameters.
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select * from student_info where facultyNumber = ?");

            // Set the first parameter of the preparedStatement object to be the facultyNumber
            preparedStatement.setString(1, facultyNumber);
            
            // Execute the query and store it in a ResultSet object.
            ResultSet rs = preparedStatement.executeQuery();
            
            // If there are any results 
            if(rs.next()){
                
                // Get the facultyNumber from the db and set it to the "student" variable of type RegisterUser.
                student.setFacultyNumber(rs.getString("facultyNumber"));
                
                // Get the name from the db and set it to the "student" variable of type RegisterUser.
                student.setName(rs.getString("name"));
                
                // Get the surname from the db and set it to the "student" variable of type RegisterUser.
                student.setSurname(rs.getString("surname"));
                
                // Get the personal ID number from the db and set it to the "student" variable of type RegisterUser.
                student.setpIdNumber(rs.getString("pIdNumber"));
                
                // Get the phone from the db and set it to the "student" variable of type RegisterUser.
                student.setPhone(rs.getString("phone"));
                
                // Get the faculty from the db and set it to the "student" variable of type RegisterUser.
                student.setFaculty(rs.getString("faculty"));
                
                // Get the specialty from the db and set it to the "student" variable of type RegisterUser.
                student.setSpecialty(rs.getString("specialty"));
                
                // Get the degree from the db and set it to the "student" variable of type RegisterUser.
                student.setDegree(rs.getString("degree"));
                
                // Get the email from the db and set it to the "student" variable of type RegisterUser.
                student.setEmail(rs.getString("email"));
         
            }

            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }

        // Return the modified "student" variable of type RegisterUser.
        return student;
    }
    
    /**
     * Method that adds credentials to the newly created student.
     * 
     * @param user - Variable of type RegisterUser.
     */
    public void addStudentCredentials(RegisterUser user){
        
        // Try to add student credentials.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database.
            PreparedStatement preparedStatement = connection

                    .prepareStatement("insert into users(username, password, role) "
                            + " values (?, ?, 'student')");

            // Parameters start with 1

            // Get the facultyNumber variable from the passed RegisterUser variable.
            preparedStatement.setString(1, user.getFacultyNumber());

             // Get the personal Id Number variable from the passed RegisterUser variable.
            preparedStatement.setString(2, user.getpIdNumber());

            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();


       // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
    }
    
    /**
     * Method that lists all registered students.
     * 
     * @return 
     */
    public List<RegisterUser> getAllStudents() {

        // Declare a List variable holding a RegisterUser objects.
        List<RegisterUser> students = new ArrayList<>();

        // Try to list all students
        try {

            // Create a Statement object for sending SQL statements to the database.
            Statement statement = connection.createStatement();

            // Execute the SQL statement. This returns a single ResultSet object.
            ResultSet rs = statement.executeQuery("select * from student_info order by facultyNumber");

            // While there are results from the SQL query:
            while (rs.next()) {

                // Declare a new RegisterUser variable.
                RegisterUser user = new RegisterUser();

                // Get the facultyNumber from the db and set it to the "user" variable of type RegisterUser.
                user.setFacultyNumber(rs.getString("facultyNumber"));

                // Get the name from the db and set it to the "user" variable of type RegisterUser.
                user.setName(rs.getString("name"));

                // Get the surname from the db and set it to the "user" variable of type RegisterUser.
                user.setSurname(rs.getString("surname"));

                // Get the pIdNumber from the db and set it to the "user" variable of type RegisterUser.
                user.setpIdNumber(rs.getString("pIdNumber"));

                // Get the phone from the db and set it to the "user" variable of type RegisterUser.
                user.setPhone(rs.getString("phone"));
                
                // Get the faculty from the db and set it to the "user" variable of type RegisterUser.
                user.setFaculty(rs.getString("faculty"));
                
                // Get the specialty from the db and set it to the "user" variable of type RegisterUser.
                user.setSpecialty(rs.getString("specialty"));
                
                // Get the degree from the db and set it to the "user" variable of type RegisterUser.
                user.setDegree(rs.getString("degree"));
                
                // Get the email from the db and set it to the "user" variable of type RegisterUser.
                user.setEmail(rs.getString("email"));

                // Add the modified "user" variable to the list of students.
                // This whole process is repeated until we have looped through all rows of the table in the db
                // to get all students.
                students.add(user);
            }

        // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
        // Return the list of all students.
        return students;
    }

 /**
  * Method that gets all the users.
  * 
  * @return 
  */
// public List<User> getAllUsers() {
//
//        List<User> users = new ArrayList<User>();
//
//        try {
//
//            Statement statement = connection.createStatement();
//
//            ResultSet rs = statement.executeQuery("select * from users");
//
//            while (rs.next()) {
//
//                User user = new User();
//
//                user.setUsername(rs.getString("username"));
//
//                user.setPassword(rs.getString("password"));
//
//                users.add(user);
//                
//
//            }
//
//        // Catch a possible SQL Exception
//        } catch (SQLException e) {
//        }
//
//
//
//        return users;
//
//    }
 
    /**
     * Method that updates student in the db.
     * 
     * @param student - A RegisterUser variable.
     */
    public void updateStudent(RegisterUser student) {

        // Try to update the student.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("update student_info set name=?, surname=?, pIdNumber=?, phone=?,"
                            + " faculty=?, specialty=?, degree=?, email=? where facultyNumber=? "
                            );

            // Create another PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement1 = connection

                    .prepareStatement("update users set password=? where username=? ");
            
            // Parameters start with 1

            // Get the name of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(1, student.getName());
            
            // Get the surname of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(2, student.getSurname());

            // Get the perosnal ID number of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(3, student.getpIdNumber());

            // Get the phone of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(4, student.getPhone());
            
            // Get the faculty of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(5, student.getFaculty());
            
            // Get the specialty of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(6, student.getSpecialty());
            
            // Get the degree of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(7, student.getDegree());
            
            // Get the email of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(8, student.getEmail());
            
            // Get the facultyNumber of the passed RegisterUser variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(9, student.getFacultyNumber());
            
            
            // Get the perosnal ID number of the passed RegisterUser variable 
            // and set it as a parameter to the other prepared statement.
            preparedStatement1.setString(1, student.getpIdNumber());
            
            // Get the facultyNumber of the passed RegisterUser variable and 
            // set it as a parameter to the other prepared statement.
            preparedStatement1.setString(2, student.getFacultyNumber());
            
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();

            // Execute the SQL statement in the PreparedStatement object
            preparedStatement1.executeUpdate();

            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
    }
    
    /**
     * Method that deletes a student.
     * 
     * @param studentId - A string which is actually the student faculty number.
     */
    public void deleteStudent(String studentId){
        
        // Try to delete a student.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("delete from student_info where facultyNumber=?");
            
            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement1 = connection

                    .prepareStatement("delete from users where username=?");
            
            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement2 = connection

                    .prepareStatement("delete from student_grades where student_id=?");

            // Parameters start with 1

            // Set the first parameter of the prepared statement to be the studentID
            preparedStatement.setString(1, studentId);
            
            // Set the first parameter of the preparedstatement1 to be the studentID
            preparedStatement1.setString(1, studentId);
            
            // Set the first parameter of the preparedstatement2 to be the studentID
            preparedStatement2.setString(1, studentId);

            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();
            
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement1.executeUpdate();
            
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement2.executeUpdate();

         // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
    }
    
    /**
     * Method that shows all disciplines for the passed specialty.
     * 
     * @param specialtyId - String which is the specialty code.
     * @return 
     */
    public List<Discipline> showDisciplines(String specialtyId){
        
        // Declare a list with Discipline objects.
        List<Discipline> disciplines = new ArrayList<>();
        
           // Try to show all disciplines.
           try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select discipline from disciplines where specialty=?");


            // Set the 1st parameter of the prepared statement to be the passed specialty value.
            preparedStatement.setString(1, specialtyId);
            
            // Execute the query.
            ResultSet rs = preparedStatement.executeQuery();

            // While there are results.
            while(rs.next()){
                
                // Declare a new variable from the discipline class.
                Discipline dis = new Discipline();
                
                // Get the discipline value from the result set and set is as the discipline for the 
                // above created variable.
                dis.setDiscipline(rs.getString("discipline"));
                
                // Add the variable to the list of disciplines.
                disciplines.add(dis);
            }
            
        // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
        // Return the list with the disciplines for the given specialty.
        return disciplines;
}
    
    /**
     * Method that adds grades.
     * 
     * @param grades - Variable of type Grades.
     */
    public void addGrades(Grades grades){
        
        // Try to add grades.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("insert into student_grades (student_id, discipline, grade) "
                            + " values (?, ?, ?)");

            // Parameters start with 1
            // Get the student faculty number from the passed Grades variable and set it as 
            // parameter for the prepared statement.
            preparedStatement.setString(1, grades.getStudent_id());
           
            // Get the student discipline from the passed Grades variable and set it as 
            // parameter for the prepared statement.
            preparedStatement.setString(2, grades.getDiscipline());

            // Get the student grade from the passed Grades variable and set it as 
            // parameter for the prepared statement.
            preparedStatement.setString(3, grades.getGrade());
            
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();

            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
    }
    
    /**
     * Method that shows the grades of a given student.
     * 
     * @param studentId - faculty number of the student.
     * @return - a list with Grades objects.
     */
    public List<Grades> showGrades(String studentId){
        
        // Declare a list of type Grades
        List<Grades> grades = new ArrayList<>();
        
        // Try to show the lsit of grades:
           try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select * from student_grades where student_id=?");

            // Set the first parameter of the prepared statemnt object to be the student faculty number.
            preparedStatement.setString(1, studentId);
            
            // Execute the sql query.
            ResultSet rs = preparedStatement.executeQuery();
            
            // While there are results.
            while(rs.next()){
                
                // Declare a new object of type Grades
                Grades grade = new Grades();
                
                // Get grade variable from the db and set it as the grade in the above created variable.
                grade.setGrade(rs.getString("grade"));
                
                // Get discipline variable from the db and set it as the discipline in the above created variable.
                grade.setDiscipline(rs.getString("discipline"));
                
                // Add the variable to the list of grades created at the beginning.
                grades.add(grade);
            }
            
            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
        // Return the list of grades for the given student.
        return grades;
}
    
    /**
     * Method that updates the student grades.
     * 
     * @param grades - a variable of type Grades.
     */
    public void updateStudentGrades(Grades grades) {

        // Try to update the student grades.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("update student_grades set grade=? where student_id=? and discipline=?"
                            );

            // Parameters start with 1

            // Get the grade from the passed grades variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(1, grades.getGrade());
            
            // Get the student faculty number from the passed grades variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(2, grades.getStudent_id());
            
            // Get the discipline from the passed grades variable and set it as a parameter to the prepared statement.
            preparedStatement.setString(3, grades.getDiscipline());
            
            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();
         
            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
     }
    
    /**
     * Method that returns a list of all disciplines depending on the given specialty.
     * 
     * @param specialty string which is the specialty given by the user
     * @return a list with disciplines for the given specialty
     */
    public List<Discipline> listDisciplines(String specialty){
        
        // Declare a list with Discipline objects.
        List<Discipline> discipline = new ArrayList<>();
        
        // Try to list all disciplines.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select * from disciplines where specialty = ?");
            

            // Set the 1st parameter of the prepared statement to be equal to the passed specialty name.
            preparedStatement.setString(1, specialty);
            
            // Execute the query.
            ResultSet rs = preparedStatement.executeQuery();
            
            // While there are results.
            while(rs.next()){
                
                // Create a new variable of type Descipline
                Discipline d = new Discipline();
                
                // Set the specialty to be equal to the passed specialty.
                d.setSpecialty(specialty);
                
                // Set the discipline to be equal to the discipline parameter from the db.
                d.setDiscipline(rs.getString("discipline"));
                
                // Add the Discipline variable to the lsit of disciplines.
                discipline.add(d);
            }
            
            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
        // Return the list with disciplines.
        return discipline;
    } 
     
    /**
     * Method that is used when creating a new student. 
     * It takes into account the faculty number of the student and the faculty 
     * and specialty (and therefore the disciplines as well). 
     * Each of the disciplines from the list is added along with the student 
     * faculty number to the table containing student grades. 
     * Initially we add an empty string for the grade which could be changed after.
     * 
     * @param facNumber - String representing the faculty number of the student
     * @param listD - List of Disciplines containing all discilines for the 
     * given specialty.
     */
    public void addInitialGrades(String facNumber, List<Discipline> listD){
  
        // Iterate through the lsit of disciplines
        for (int i = 0; i<listD.size(); i++){
          
            // Save each discipline in a string for more convenience
            String disc = listD.get(i).getDiscipline();
            
        // Try to add the initial grades.
        try {

             // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    // Create a sql insert statement that inserts values into the 
                    // student_grades table.
                    .prepareStatement("insert into student_grades(student_id, discipline, grade) "
                            + " values (?, ?, '')");

            // Parameters start with 1

            // Set the 1st parameter of the prepared statement to be equal to the
            // passed faculty Number
            preparedStatement.setString(1, facNumber);

            // Set the 2nd parameter of the prepared statement to be equal to the
            // passed discipline
            preparedStatement.setString(2, disc);

            // Execute the SQL statement in the PreparedStatement object
            preparedStatement.executeUpdate();
            
            // Catch a possible SQL Exception
        } catch (SQLException e) {
        }
        
        }
    } 
    
    /**
     * Method that is used when we want to retrieve all faculties from the db.
     * It takes into account the faculty of the user that is being updated.
     * 
     * @param faculty
     * @return A list of faculties
     */
    public List<Faculty> listFaculty(String faculty){
        
        // Declare a list with objects of type Faculty.
        List<Faculty> faculties = new ArrayList<>();
        
        // Try to retrieve all faculties from the db.
        try {
            
            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select facultyCode from faculty where facultyCode != ?");
            
            // Set the 1st parameter of the prepared statement to be equal to the passed faculty value.
            preparedStatement.setString(1, faculty);
            
            // Execute the sql query and store the result as a result set variable.
            ResultSet rs = preparedStatement.executeQuery();
           
            // While there are results.
            while(rs.next()){
                
                // Declare a new variable of type Faculty.
                Faculty f = new Faculty();

                // Get the facultyCode and set it as faculty to the variable created above.
                f.setFaculty(rs.getString("facultyCode"));
                
                // Add the faculty to the list of faculties.
                faculties.add(f);
            }
            
        // Catch a possible SQL Exception    
        } catch (SQLException e) {
        }
        
        // Return the list of faculties.
        return faculties;
    } 
     
    /**
     * Method that lists specialties. The method takes into account the current 
     * specialty and lists all remaining specialties from the db.
     * 
     * @param specialty - The specialty that we do NOT want the method to show.
     * @return A list of specialties from the db.
     */
    public List<Faculty> listSpecialty(String specialty){
        
        // Declare a list with Faculty objects.
        List<Faculty> specialties = new ArrayList<>();
        
        // try to list all specialties.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select specialtyCode from specialty where specialtyCode != ?");
            
            // Set the 1st parameter of the prepared statement to be equal to the specialty passed to the method.
            preparedStatement.setString(1, specialty);
            
            // Execute the query and save the result in a result set object.
            ResultSet rs = preparedStatement.executeQuery();
           
            // While there are results.
            while(rs.next()){
                
                // Create a new Faculty variable.
                Faculty f = new Faculty();
                
                // Set the specialty of the above created variable to be equal to the specialtyCode.
                f.setSpecialty(rs.getString("specialtyCode"));
                
                // Add the Faculty variable to the list of specialties.
                specialties.add(f);
            }
            
         // Catch a possible SQL Exception   
        } catch (SQLException e) {
        }
        
        // Return the list of specialties without the one passed at the beginning.
        return specialties;
    } 
    
    /**
     * Method that gets the full name of the specialty.
     * 
     * @param specialty - the specialty string that we want to get the full  name for.
     * @return 
     */
    public String listFullNameOfSpecialty(String specialty){
        
       // Declare a string variable to hold the full name of the specialty.
       String fullName = null;
        
       // Try to get the full name of the specialty.
        try {

            // Create a PreparedStatement object for sending parameterized SQL statements to the database
            PreparedStatement preparedStatement = connection

                    .prepareStatement("select specialtyName from specialty where specialtyCode = ?");
            
            // Set the 1st paramete of the prepared statement to be equal to the specialty passed at the beginning.
            preparedStatement.setString(1, specialty);
            
            // Execute the query and save the result in a result set variable.
            ResultSet rs = preparedStatement.executeQuery();
           
            // While there are results.
            while(rs.next()){
                
                // Get the specialtyName variable from the db and set it as for the null string declared at the beginning.
                fullName = rs.getString("specialtyName");
                
            }
            
        // Catch a possible SQL Exception    
        } catch (SQLException e) {
        }
        
        // Return the full name of the specialty.
        return fullName;
    } 
    
}
    

             
        