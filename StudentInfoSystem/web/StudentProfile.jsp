<%-- 
    Document   : StudentProfile
    Created on : Dec 16, 2017, 1:32:16 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="bootstrap.css" />
        <title>SIS</title>
    </head>
    <body>
    <center>
        <a href="StudentProfile?action=listStudentInfo&facultyNumber=<c:out value="${user.facultyNumber}"/>">View Profile |</a>
        <a href="StudentProfile?action=listStudentGrades&facultyNumber=<c:out value="${user.facultyNumber}"/>"> View Grades | </a><a href="index.jsp"  > Logout </a>
        <br><br>
        <h1>Student Profile Page</h1>
        
       <form action="StudentProfile" method="POST">
            <table border="1" cellspacing="5" cellpadding="5">
                
                <tbody>
                    <tr>
                        <td>Faculty Number: </td>
                        <td> 
                                   <c:out value="${user.facultyNumber}" /></td>
                    </tr>
                    <tr>
                        <td>First Name: </td>
                        <td> 
                                   <c:out value="${user.name}" /> </td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td> <c:out value="${user.surname}" /> </td>
                    </tr>
                    <tr>
                        <td>Personal ID Number: </td>
                        <td>
                                   <c:out value="${user.pIdNumber}" /> </td>
                    </tr>
                    
                    <tr>
                        <td>Phone: </td>
                        <td>
                              <c:out value="${user.phone}" /> </td>
                    </tr>
                    <tr>
                        <td>Faculty: </td>
                        <td> <c:out value="${user.faculty}" /> </td>
                    </tr>
                    <tr>
                        <td>Specialty: </td>
                        <td> <c:out value="${user.specialty}" /> </td>
                    </tr>
                    <tr>
                        <td>Degree: </td>
                        <td> <c:out value="${user.degree}" /> </td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td> <c:out value="${user.email}" /> </td>
                    </tr>
                    
                    
                </tbody>
            </table>
       </form>
        
        
    </center>
    </body>
</html>
