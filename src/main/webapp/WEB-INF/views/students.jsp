<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Students</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Students</h2>
<p><a href="${pageContext.request.contextPath}/">на домашнюю</a></p>
<c:if test="${not empty user}">
<p><a href="${pageContext.request.contextPath}/students/edit">Add new student</a></p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Record Book</th>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Group ID</th>
        <c:if test="${not empty user}">
        <th>Action</th>
        </c:if>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td><c:out value="${student.studentId}" /></td>
            <td><c:out value="${student.recordBookNumber}" /></td>
            <td><c:out value="${student.lastName}" /></td>
            <td><c:out value="${student.firstName}" /></td>
            <td><c:out value="${student.groupId}" /></td>
            <c:if test="${not empty user}">
            <td>
                <a href="${pageContext.request.contextPath}/students/edit?id=${student.studentId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/students/delete" style="display:inline">
                    <input type="hidden" name="id" value="${student.studentId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
