<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
        <c:when test="${isEdit}">Edit student</c:when>
        <c:otherwise>Create student</c:otherwise>
        </c:choose>
    </title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit student</c:when>
        <c:otherwise>Create student</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/students/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${student.studentId}">
    </c:if>

    Record book:
    <input name="recordBookNumber" required value="${fn:escapeXml(student.recordBookNumber)}">
    <br><br>

    Last name:
    <input name="lastName" required value="${fn:escapeXml(student.lastName)}">
    <br><br>

    First name:
    <input name="firstName" required value="${fn:escapeXml(student.firstName)}">
    <br><br>

    Group ID:
    <input type="number" min="1" name="groupId" required value="${student.groupId}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/students">Back to students</a>
</body>
</html>

