<%-- 
    Document   : RegisterNewStudent
    Created on : Dec 7, 2017, 7:35:45 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="bootstrap.css" />
        <title>SIS</title>
    </head>
    <body>
    <center>
        <a href="RegisterNewStudent.jsp"  > Register New Student | </a><a href="ListAllStudents?action=listStudent"> Update Student | </a><a href="ListStudentGradesServlet?action=listStudent"> Add Grades | </a> <a href="index.jsp"  > Logout </a>
        <br><br>
        <h1>Register New Student</h1>
        <br>
        <form action="RegisterNewStudentServlet" method="POST">
            <table border="0" cellspacing="5" cellpadding="5">
                
                <tbody>
                    <tr>
                        <td>Faculty Number: </td>
                        <td><input type="text" name="txtFacultyNumber" placeholder="Enter faculty number" required=""/></td>
                    </tr>
                    <tr>
                        <td>First Name: </td>
                        <td><input type="text" name="txtFirstName" placeholder="Enter first name" required="" /></td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td><input type="text" name="txtLastName" placeholder="Enter last name" required=""/></td>
                    </tr>
                    <tr>
                        <td>Personal ID Number: </td>
                        <td><input type="text" name="txtPidNumber" placeholder="Enter PID number" required=""/></td>
                    </tr>
                    
                    <tr>
                        <td>Phone: </td>
                        <td><input type="text" name="txtPhone" placeholder="Enter phone" required=""/></td>
                    </tr>
                    <tr>
                        <td>Faculty: </td>
                        <td><select name="txtFaculty" required="">
                                <option>FA</option>
                                <option>FEE</option>
                                <option>FCST</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Specialty: </td>
                        <td><select name="txtSpecialty" required="">
                                <option>AIC</option>
                                <option>EPEEE</option>
                                <option>EE</option>
                                <option>CSE</option>
                                <option>ITI</option>
                                <option>ITT</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Degree: </td>
                        <td><select name="txtDegree" required="">
                                <option>Bachelor</option>
                                <option>Master</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="txtEmail" placeholder="Enter email" required=""/></td>
                    </tr>
                    <tr>
                        <td>
                           
                        </td>
                        
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Register" class="btn btn-primary" /> <input type="reset" value="Clear" class="btn btn-primary"/></td>
                    </tr>
                </tbody>
            </table>
                        <br>
                        
                        <span style="color:red"><%=(request.getAttribute("successMsg")==null)?"":request.getAttribute("successMsg")%></span>
                        <span style="color:red"><%=(request.getAttribute("errorMessage")==null)?"":request.getAttribute("errorMessage")%></span>
        </form>
    
    </center>

    </body>
</html>
