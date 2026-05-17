<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Student Group Manager</title>
</head>
<body>
<h2>Student Group Manager</h2>
нираотаит
<c:choose>
    <c:when test="${not empty user}">
        ${user.login}&nbsp;&mdash; <A href="logout.html">выйти</A>
    </c:when>
    <c:otherwise>
        <A href="login-form.jsp">войти</A>
    </c:otherwise>
</c:choose>
<ul>
    <li><a href="${pageContext.request.contextPath}/groups">Группы</a></li>
    <li><a href="${pageContext.request.contextPath}/students">Студенты</a></li>
    <li><a href="${pageContext.request.contextPath}/grades">Оценки</a></li>
    <li><a href="${pageContext.request.contextPath}/users">Пользователи</a></li>
    <li><a href="${pageContext.request.contextPath}/subjects">Предметы</a></li>
    <li><a href="${pageContext.request.contextPath}/sessions">Сессии</a></li>
</ul>
</body>
</html>
