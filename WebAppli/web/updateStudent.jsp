<%-- 
    Document   : RegisterNewStudent
    Created on : Dec 7, 2017, 7:35:45 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1>Update Student Information</h1>
        <br>
        
        <form action="UpdateStudent" method="POST">
            <table border="0" cellspacing="5" cellpadding="5">
                
                <tbody>
                    <tr>
                        <td>Faculty Number: </td>
                        <td><input type="text" name="txtfacultyNumber"  readonly=""
                                   value="<c:out value="${user.facultyNumber}" />"/></td>
                    </tr>
                    <tr>
                        <td>First Name: </td>
                        <td><input type="text" name="txtname" required=""
                                   value="<c:out value="${user.name}" />"/></td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td><input type="text" name="txtsurname" required=""
                                   value="<c:out value="${user.surname}" />"/></td>
                    </tr>
                    <tr>
                        <td>Personal ID Number: </td>
                        <td><input type="text" name="txtpIdNumber" required=""
                                   value="<c:out value="${user.pIdNumber}" />"/></td>
                    </tr>
                    
                    <tr>
                        <td>Phone: </td>
                        <td><input type="text" name="txtphone" required=""
                                   value="<c:out value="${user.phone}" />"/></td>
                    </tr>
                    <tr>
                        <td>Faculty: </td>
                        <td>
                                    
                             <select name="txtfaculty" required="" >
                                 
                                <option>${user.faculty}</option>
                                <c:forEach items="${faculty}" var="f">
                                    <option>${f}</option>
                                </c:forEach>
                                
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Specialty: </td>
                        <td>
                          
                          <select name="txtspecialty" required="" >
                                
                                <option>${user.specialty}</option>
                                
                                <c:forEach items="${specialty}" var="s">
                                    
                                    <option>${s}</option>  
                                
                                </c:forEach>
                                
                            </select>  
                                
                           
                           
                        </td>
                    </tr>
                    <tr>
                        <td>Degree: </td>
                        <td>
                          <select name="txtdegree" required="" >
                                
                                <option>${user.degree}</option>
                                <option>${degree}</option>
                               
                            </select> 
                           
                        </td>
                    </tr> 
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="txtemail" required=""
                                   value="<c:out value="${user.email}" />"/></td>
                    </tr>
                    
                   <tr>
                        <td></td>
                        <td><input type="submit" value="Update" class="btn btn-primary"/>
                             </td>
                    </tr>
                </tbody>
            </table>
                        <br>
                        
                       <%-- <span style="color:red"><%=(request.getAttribute("successMsg")==null)?"":request.getAttribute("successMsg")%></span> --%>
        </form>
    
        
        
    </center>

    </body>
</html>
