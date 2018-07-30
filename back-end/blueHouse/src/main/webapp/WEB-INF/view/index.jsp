<%--
  Created by IntelliJ IDEA.
  User: wulei
  Date: 27/07/2018
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入蓝房子</title>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="../../resources/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron alert-info">
    <tr>
        requestScope: ${users}
    </tr>

    <c:forEach var="user" items="${users}">
        <table>
            <tr>
                <th>大神ID</th>
                <th>大神-名字</th>
            </tr>
            <tr>
                <td>${user}</td>
                <td>${user.name}</td>
            </tr>
        </table>
    </c:forEach>
</div>

</body>
</html>
