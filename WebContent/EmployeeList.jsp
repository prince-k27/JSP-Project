<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application From Prince Kumar</title>
</head>
<body>
    <center>
        <h1>Employee Management Project By Prince Kumar</h1>
        <h2>
            <a href="/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employee</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Employee</h2></caption>
            <tr>
                <th>EID</th>
                <th>Ename</th>
                <th>Emailid</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="employee" items="${listEmployee}">
                <tr>
                    <td><c:out value="${employee.eid}" /></td>
                    <td><c:out value="${employee.ename}" /></td>
                    <td><c:out value="${employee.emailid}" /></td>
                    <td><c:out value="${employee.phone}" /></td>
                    <td><c:out value="${employee.status}" /></td>
                    <td>
                        <a href="/edit?eid=<c:out value='${employee.eid}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?eid=<c:out value='${employee.eid}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>