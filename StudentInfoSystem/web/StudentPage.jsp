<%-- 
    Document   : StudentPage
    Created on : Dec 7, 2017, 7:27:31 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.RegisterUser" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="bootstrap.css" />
        <title>SIS</title>
    </head>
    <body>
    <center>
        <a href="StudentProfile?action=listStudentInfo&facultyNumber=<c:out value="${student.facultyNumber}"/>">View Profile |</a>
        <a href="StudentProfile?action=listStudentGrades&facultyNumber=<c:out value="${student.facultyNumber}"/>"> View Grades | </a><a href="index.jsp"  > Logout </a>
        <br><br>
        <h1>Student Page</h1>
       <%-- <h3> Welcome <%= request.getAttribute("username") %> </h3> --%>
       <br><br>
        <h3> Welcome <c:out value="${student.name}" /> </h3>
        <h3> Faculty Number <c:out value="${student.facultyNumber}" /> </h3>
       
        
        
    </center>

    </body>
</html>
