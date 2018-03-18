<%-- 
    Document   : AdminPage
    Created on : Dec 7, 2017, 7:27:21 PM
    Author     : User
--%>

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
        <h1>Admin Page</h1>
        <h3> Welcome <%=request.getAttribute("username") %> </h3>
    
    </center>

    </body>
</html>
