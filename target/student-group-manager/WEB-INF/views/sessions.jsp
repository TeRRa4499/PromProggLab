<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Sessions</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Sessions</h2>
<p><a href="${pageContext.request.contextPath}/grades">Grades</a></p>
<p><a href="${pageContext.request.contextPath}/sessions/edit">Add new session</a></p>

<table>
    <tr>
        <th>ID</th>
        <th>Number</th>
        <th>Year</th>
        <th>Semester</th>
        <th>Action</th>
    </tr>
    <c:forEach var="session" items="${sessions}">
        <tr>
            <td><c:out value="${session.sessionId}" /></td>
            <td><c:out value="${session.sessionNumber}" /></td>
            <td><c:out value="${session.year}" /></td>
            <td><c:out value="${session.semester}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/sessions/edit?id=${session.sessionId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/sessions/delete" style="display:inline">
                    <input type="hidden" name="id" value="${session.sessionId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>