<%-- 
    Document   : index
    Created on : Dec 7, 2017, 6:47:04 PM
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
        
        <h2>Student Information System</h2>
        <br>
        <h4>Please login: </h4>
        
        <form action="LoginServlet" method="POST">
            <table border="0" cellpadding="3">
                <tbody>
                    <tr>
                        <td>Username: </td>
                        <td><input type="text" name="txtUsername" required="" placeholder="Enter Username"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="txtPassword" required="" placeholder="Enter Password"/></td>
                    </tr>
                    <tr>
                        <td><span style="color:red"><%=(request.getAttribute("errMessage")==null)?"":request.getAttribute("errMessage")%></span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login" class="btn btn-primary" /> <input type="reset" value="Clear" class="btn btn-primary"/></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </center>
        
    </body>
</html>
