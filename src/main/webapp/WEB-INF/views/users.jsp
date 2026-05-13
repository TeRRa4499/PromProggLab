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
<p><a href="${pageContext.request.contextPath}/">на домашнюю</a></p>
<c:if test="${not empty user}">
<p><a href="${pageContext.request.contextPath}/users/edit">Add new user</a></p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Role</th>
        <th>Student ID</th>
        <th>Email</th>
        <th>Created</th>
        <th>Active</th>
        <c:if test="${not empty user}">
        <th>Action</th>
        </c:if>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td><c:out value="${u.userId}" /></td>
            <td><c:out value="${u.login}" /></td>
            <td><c:out value="${u.role}" /></td>
            <td><c:out value="${u.studentId}" /></td>
            <td><c:out value="${u.email}" /></td>
            <td><c:out value="${u.createdDate}" /></td>
            <td><c:out value="${u.isActive}" /></td>
            <c:if test="${not empty user}">
            <td>
                <a href="${pageContext.request.contextPath}/users/edit?id=${u.userId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/users/delete" style="display:inline">
                    <input type="hidden" name="id" value="${u.userId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>