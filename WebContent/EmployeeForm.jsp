<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>
</head>
<body>
    <center>
        <h1>Employee Management</h1>
        <h2>
            <a href="/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Employees</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${employee != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${employee == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${employee != null}">
                        Edit Employee
                    </c:if>
                    <c:if test="${employee == null}">
                        Add New Employee
                    </c:if>
                </h2>
            </caption>
                <c:if test="${employee != null}">
                    <input type="hidden" name="eid" value="<c:out value='${employee.eid}' />" />
                </c:if>           
            <tr>
                <th>Ename: </th>
                <td>
                    <input type="text" name="ename" size="45"
                            value="<c:out value='${employee.ename}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${employee.email}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Phone: </th>
                <td>
                    <input type="text" name="phone" size="45"
                            value="<c:out value='${employee.phone}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Status: </th>
                <td>
                    <input type="text" name="status" size="45"
                            value="<c:out value='${employee.status}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>