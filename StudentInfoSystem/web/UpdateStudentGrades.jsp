<%-- 
    Document   : UpdateStudentGrades
    Created on : Jan 20, 2018, 8:01:34 PM
    Author     : User
--%>

<%@page import="model.Discipline"%>
<%@page import="java.util.List"%>
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
        <a href="RegisterNewStudent.jsp"  > Register New Student | </a><a href="ListAllStudents?action=listStudent"> Update Student | </a><a href="ListStudentGradesServlet?action=listStudent"> Add Grades | </a> <a href="index.jsp"  > Logout </a>
        <br><br>
        <h1>Update Grades</h1>
        <h3> Specialty: <c:out value="${specialty}" /> </h3>
        <h3> Faculty Number: <c:out value="${user.facultyNumber}" /> </h3>
        
        
        <br>
        
      <%--  <span style="color:red"><%=(request.getAttribute("error")==null)?"":request.getAttribute("error")%></span> --%>
      
      
      <br><br>
        
        
        <form action="UpdateStudentGradesServlet" method="POST">
        <table border=0>

        <thead>

            <tr>

                <th>Discipline</th>
                <th>Grade</th>

            </tr>

        </thead>

        <tbody>

            
                <c:forEach var="grade" items="${grades}" >
                    
                <tr>
                    
                   <td><c:out value="${grade.discipline}" /> </td> 

                   <td> <input type="number" max="6" min="2" name="txtGrade" 
                         value="<c:out value="${grade.grade}" />" /></td> 
                    
                </tr>
             
                </c:forEach>
               
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" class="btn btn-primary"/>
                    </td>
                </tr>     
          
        </tbody>

    </table>
            
    <br><br>
    
            </center>
</html>