<%-- 
    Document   : listStudent
    Created on : Dec 29, 2017, 2:28:40 PM
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
        <a href="RegisterNewStudent.jsp"  > Register New Student | </a><a href="ListAllStudents?action=listStudent"> Update Student | </a><a href="ListStudentGradesServlet?action=listStudent"> Add Grades | </a> <a href="index.jsp"  > Logout </a>
        <br><br>
        <h1>Update Student</h1>
        <br>           
        <span style="color:red"><%=(request.getAttribute("successMsg")==null)?"":request.getAttribute("successMsg")%> 
                                <%=(request.getAttribute("deletemsg")==null)?"":request.getAttribute("deletemsg")%> </span>
        <br><br>
        
        <table border=1>

        <thead>

            <tr>

                <th>Faculty Number</th>

                <th>First Name</th>

                <th>Last Name</th>

                <th>Personal ID Number</th>

                <th>Phone</th>

                <th>Faculty</th>
                <th>Specialty</th>
                <th>Degree</th>
                <th>Email</th>
                <th colspan=2 >Action</th>


            </tr>

        </thead>

        <tbody>

            <c:forEach items="${students}" var="student">

                <tr>
 
                    <td><c:out value="${student.facultyNumber}" /></td>

                    <td><c:out value="${student.name}" /></td>
                    
                    <td><c:out value="${student.surname}" /></td>
                    
                    <td><c:out value="${student.pIdNumber}" /></td>
                    
                    <td><c:out value="${student.phone}" /></td>
                    
                    <td><c:out value="${student.faculty}" /></td>
                    
                    <td><c:out value="${student.specialty}" /></td>
                    
                    <td><c:out value="${student.degree}" /></td>
                    
                    <td><c:out value="${student.email}" /></td>

                    <td><a href="UpdateStudent?action=edit&facultyNumber=<c:out value="${student.facultyNumber}"/>">Update</a></td>
                    <td><a href="UpdateStudent?action=delete&facultyNumber=<c:out value="${student.facultyNumber}"/>">Delete</a></td>
                    
                </tr>

            </c:forEach>

        </tbody>

    </table>
    </center>
    </body>
</html>
