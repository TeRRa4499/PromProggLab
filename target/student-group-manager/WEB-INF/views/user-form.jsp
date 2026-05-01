<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${isEdit}">Edit user</c:when><c:otherwise>Create user</c:otherwise></c:choose></title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit user</c:when>
        <c:otherwise>Create user</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/users/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${user.userId}">
    </c:if>

    Login:
    <input name="login" required value="${fn:escapeXml(user.login)}">
    <br><br>

    Password Hash:
    <input type="password" name="passwordHash" value="">
    <br><br>

    Role:
    <select name="role" required>
        <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>admin</option>
        <option value="teacher" ${user.role == 'teacher' ? 'selected' : ''}>teacher</option>
        <option value="student" ${user.role == 'student' ? 'selected' : ''}>student</option>
    </select>
    <br><br>

    Student ID:
    <input type="number" name="studentId" value="${user.studentId}">
    <br><br>

    Email:
    <input type="email" name="email" value="${fn:escapeXml(user.email)}">
    <br><br>

    Created Date:
    <input type="date" name="createdDate" value="${user.createdDate}">
    <br><br>

    Is Active:
    <input type="text" name="isActive" value="${user.isActive}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/users">Back to users</a>
</body>
</html>