<%-- 
    Document   : StudentGrades
    Created on : Jan 21, 2018, 4:10:17 PM
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
        <h1>Student Grades Page</h1>
        
        <span style="color:red"><%=(request.getAttribute("error")==null)?"":request.getAttribute("error")%></span>
        
        
       <table border=0>

        <thead>

            <tr>

                <th>Discipline</th>

                <th>Grade</th>

            </tr>

        </thead>

        <tbody>

            <c:forEach items="${grades}" var="grade" >

                <tr>
 
                   <td><c:out value="${grade.discipline}" /> </td> 

                   <td align="center" ><c:out value="${grade.grade}" /> </td> 

                </tr>
      
            </c:forEach>
    
                     
        </tbody>

        
      
    </table>
  
</html>
