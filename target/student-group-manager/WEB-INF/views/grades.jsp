<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Grades</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Grades</h2>
<p><a href="${pageContext.request.contextPath}/students">Students</a></p>
<p><a href="${pageContext.request.contextPath}/grades/edit">Add new grade</a></p>

<table>
    <tr>
        <th>ID</th>
        <th>Student ID</th>
        <th>Subject ID</th>
        <th>Session ID</th>
        <th>Score</th>
        <th>Action</th>
    </tr>
    <c:forEach var="grade" items="${grades}">
        <tr>
            <td><c:out value="${grade.gradeId}" /></td>
            <td><c:out value="${grade.studentId}" /></td>
            <td><c:out value="${grade.subjectId}" /></td>
            <td><c:out value="${grade.sessionId}" /></td>
            <td><c:out value="${grade.score}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/grades/edit?id=${grade.gradeId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/grades/delete" style="display:inline">
                    <input type="hidden" name="id" value="${grade.gradeId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>