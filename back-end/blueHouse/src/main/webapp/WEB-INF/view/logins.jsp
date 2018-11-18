<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>欢迎进入蓝房子-登录</title>
    <link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
</head>
<body>

<section class="jumbotron alert-info">
    <div class="container">
        <header>
            <h1><a href="/login/logins">蓝房子后台管理中心</a></h1>
            <p class="lead">登录</p>
        </header>
    </div>
</section>
<div class="container">
    <c:if test="${loginStatus == '-1' }">
    <div class="card-deck mb-4">
        <div class="card mb-3">
            <div class="card-header alert-success">
                <h4>请登录</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/login/signin" method="post">
                    <div class="col-md-6">
                        账号: <input id="user" name="user" type="text" placeholder="请输入账号" value="">
                    </div>
                    <div class="col-md-6">
                        密码: <input id="password" name="password" type="password"  placeholder="请输入密码" value="">
                    </div>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-success">登录</button>
                </form>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${loginStatus == '0' }">
    <div class="card-deck mb-4">
        <div class="card mb-3">
            <div class="card-header alert-error">
                <h4>账号密码错误,请重新登录</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/login/logins" method="get">
                    <button type="submit" class="btn btn-lg btn-block btn-outline-success">返回登录</button>
                </form>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${loginStatus == '1' }">
        <div class="card-deck mb-4">
            <div class="card mb-3">
                <div class="card-header alert-error">
                    <h4>登录成功,欢迎:${username}</h4>
                    <form class="form-inline mt-2 mt-md-0" action="/console/getAll" method="get">
                        <button type="submit" class="btn btn-lg btn-block btn-outline-success">进入控制台</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${loginStatus == '-2' }">
        <div class="card-deck mb-4">
            <div class="card mb-3">
                <div class="card-header alert-sucess">
                    <h4>注销成功,再见:${username}</h4>
                </div>
                <div class="card-body">
                    <form class="form-inline mt-2 mt-md-0" action="/login/logins" method="get">
                        <button type="submit" class="btn btn-lg btn-block btn-outline-success">返回登录</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

</div>
</body>
</html>
