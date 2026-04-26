<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:choose><c:when test="${isEdit}">Edit group</c:when><c:otherwise>Create group</c:otherwise></c:choose></title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${isEdit}">Edit group</c:when>
        <c:otherwise>Create group</c:otherwise>
    </c:choose>
</h2>

<form method="post" action="${pageContext.request.contextPath}/groups/save">
    <c:if test="${isEdit}">
        <input type="hidden" name="id" value="${group.groupId}">
    </c:if>

    Name:
    <input name="groupName" required value="${fn:escapeXml(group.groupName)}">
    <br><br>

    Course:
    <input type="number" min="1" name="course" required value="${group.course}">
    <br><br>

    Faculty:
    <input name="faculty" required value="${fn:escapeXml(group.faculty)}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${isEdit}">Update</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose>
    </button>
</form>

<br>
<a href="${pageContext.request.contextPath}/groups">Back to groups</a>
</body>
</html>

