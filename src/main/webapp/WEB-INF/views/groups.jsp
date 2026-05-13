<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Groups</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 6px; }
    </style>
</head>
<body>
<h2>Groups</h2>
<p><a href="${pageContext.request.contextPath}/">на домашнюю</a></p>
<c:if test="${not empty user}">
<p><a href="${pageContext.request.contextPath}/groups/edit">Add new group</a></p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Course</th>
        <th>Faculty</th>
        <c:if test="${not empty user}">
        <th>Action</th>
        </c:if>
    </tr>
    <c:forEach var="group" items="${groups}">
        <tr>
            <td><c:out value="${group.groupId}" /></td>
            <td><c:out value="${group.groupName}" /></td>
            <td><c:out value="${group.course}" /></td>
            <td><c:out value="${group.faculty}" /></td>
            <c:if test="${not empty user}">
            <td>
                <a href="${pageContext.request.contextPath}/groups/edit?id=${group.groupId}">Edit</a>
                <form method="post" action="${pageContext.request.contextPath}/groups/delete" style="display:inline">
                    <input type="hidden" name="id" value="${group.groupId}">
                    <button type="submit">Delete</button>
                </form>
            </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>

