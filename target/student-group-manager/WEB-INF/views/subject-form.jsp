<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${isEdit}">Edit subject</c:when><c:otherwise>Create subject</c:otherwise></c:choose></title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit subject</c:when>
        <c:otherwise>Create subject</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/subjects/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${subject.subjectId}">
    </c:if>

    Name:
    <input name="subjectName" required value="${fn:escapeXml(subject.subjectName)}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/subjects">Back to subjects</a>
</body>
</html>