<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${isEdit}">Edit grade</c:when><c:otherwise>Create grade</c:otherwise></c:choose></title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit grade</c:when>
        <c:otherwise>Create grade</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/grades/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${grade.gradeId}">
    </c:if>

    Student ID:
    <input type="number" name="studentId" required value="${grade.studentId}">
    <br><br>

    Subject ID:
    <input type="number" name="subjectId" required value="${grade.subjectId}">
    <br><br>

    Session ID:
    <input type="number" name="sessionId" required value="${grade.sessionId}">
    <br><br>

    Score (2-5):
    <input type="number" min="2" max="5" name="score" required value="${grade.score}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/grades">Back to grades</a>
</body>
</html>