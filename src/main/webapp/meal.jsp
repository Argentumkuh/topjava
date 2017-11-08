<%--
  Created by IntelliJ IDEA.
  User: Argentum_kuh
  Date: 08.11.2017
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meal</h2>


<table>
    <tr>
        <th>Date and time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${model}" var="meal">
    <tr>
        <td>${meal.dateTime}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
