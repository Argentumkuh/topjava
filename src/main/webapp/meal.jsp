
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>

    <style>
        .tableColorRed {
            background-color: red;
        }

        .tableColorGreen {
            background-color: green;
        }
    </style>

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
        <tr
                <c:choose>
                    <c:when test="${meal.exceed}">
                        class="tableColorRed"
                    </c:when>
                    <c:otherwise>
                        class="tableColorGreen"
                    </c:otherwise>
                </c:choose>
        >

            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
