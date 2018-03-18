<%-- 
    Document   : ListStudentsGrades
    Created on : Jan 7, 2018, 5:42:52 PM
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
        <h1>Student Grades</h1>
        <br>
        <span style="color:red"><%=(request.getAttribute("successMsg")==null)?"":request.getAttribute("successMsg")%> 
                                 </span>
                                 <br><br>
        <table border=1>

        <thead>

            <tr>

                <th>Faculty Number</th>

                <th>First Name</th>

                <th>Last Name</th>

                <th>Faculty</th>
                <th>Specialty</th>
                <th>Degree</th>
                
                <th colspan="2" >Action</th>
                

            </tr>

        </thead>

        <tbody>

            <c:forEach items="${students}" var="student">

                <tr>
 
                    <td><c:out value="${student.facultyNumber}" /></td>

                    <td><c:out value="${student.name}" /></td>
                    
                    <td><c:out value="${student.surname}" /></td>
                    
                    <td><c:out value="${student.faculty}" /></td>
                    
                    <td><c:out value="${student.specialty}" /></td>
                    
                    <td><c:out value="${student.degree}" /></td>
                    
                    
                <%--
                    <td><a href="ListStudentGradesServlet?action=addGrade&facultyNumber=<c:out value="${student.facultyNumber}"/>">Add Grade</a></td>
                    <td><a href="ListStudentGradesServlet?action=updateGrade&facultyNumber=<c:out value="${student.facultyNumber}"/>">Update Grade</a></td>
                   --%>
                    <td><a href="UpdateStudentGradesServlet?action=updateGrade&facultyNumber=<c:out value="${student.facultyNumber}"/>">Update Grade</a></td>
                    
                </tr>

            </c:forEach>
                    
        </tbody>

    </table>
        
        
        </center>
    </body>
</html>
