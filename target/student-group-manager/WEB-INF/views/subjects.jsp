<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Subjects</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Subjects</h2>
<p><a href="${pageContext.request.contextPath}/">на домашнюю</a></p>
<p><a href="${pageContext.request.contextPath}/subjects/edit">Add new subject</a></p>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td><c:out value="${subject.subjectId}" /></td>
            <td><c:out value="${subject.subjectName}" /></td>
            <td>
                <a href="${pageContext.request.contextPath}/subjects/edit?id=${subject.subjectId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/subjects/delete" style="display:inline">
                    <input type="hidden" name="id" value="${subject.subjectId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>