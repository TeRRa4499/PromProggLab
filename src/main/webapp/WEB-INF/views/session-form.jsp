<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${isEdit}">Edit session</c:when><c:otherwise>Create session</c:otherwise></c:choose></title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit session</c:when>
        <c:otherwise>Create session</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/sessions/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${session.sessionId}">
    </c:if>

    Session Number:
    <input type="number" name="sessionNumber" required value="${session.sessionNumber}">
    <br><br>

    Year:
    <input type="number" name="year" required value="${session.year}">
    <br><br>

    Semester:
    <input name="semester" required value="${fn:escapeXml(session.semester)}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/sessions">Back to sessions</a>
</body>
</html>