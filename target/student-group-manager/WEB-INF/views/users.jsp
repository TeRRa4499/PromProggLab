<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Users</h2>
<p><a href="${pageContext.request.contextPath}/students">Students</a></p>
<p><a href="${pageContext.request.contextPath}/users/edit">Add new user</a></p>

<table>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Role</th>
        <th>Student ID</th>
        <th>Email</th>
        <th>Created</th>
        <th>Active</th>
        <th>Action</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.userId}" /></td>
            <td><c:out value="${user.login}" /></td>
            <td><c:out value="${user.role}" /></td>
            <td><c:out value="${user.studentId}" /></td>
            <td><c:out value="${user.email}" /></td>
            <td><c:out value="${user.createdDate}" /></td>
            <td><c:out value="${user.isActive}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/users/edit?id=${user.userId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/users/delete" style="display:inline">
                    <input type="hidden" name="id" value="${user.userId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>