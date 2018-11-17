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
    <title>欢迎进入蓝房子-总控制台</title>
    <link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
</head>
<body>

<section class="jumbotron alert-info">
    <div class="container">
        <header>
            <h1><a href="/console/getAll">蓝房子后台管理中心</a></h1>
            <p class="lead">总控制台</p>
            <p><%=session.getAttribute("user")%> : 您好! </p>
        </header>
    </div>
</section>
<div class="container">
    <div class="card-deck mb-4">
        <div class="card mb-3">
            <div class="card-header alert-success">
                <h4>用户管理</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/user/getAll" method="get">
                    <p>所有用户的维护入口，通过该Panel对用户数据进行定期维护与更新。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-success">进入入口</button>
                </form>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header alert-warning">
                <h4>订单管理</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/order/getAll" method="get">
                    <p>所有用户的订单入口，通过该Panel对所有用户的订单进行统一管理。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-warning">进入入口</button>
                </form>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header alert-primary">
                <h4>活动管理</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/activity/getAll" method="get">
                    <p>推广活动、广告管理入口，通过该Panel更新定期的活动或广告推广。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-primary">进入入口</button>
                </form>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header alert-danger">
                <h4>施工项目</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/project/getAll" method="get">
                    <p>该Panel用于维护所有蓝房子支持的施工具体项目，如吊顶、防水等。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-danger">进入入口</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="card-deck mb-3">
        <div class="card mb-4">
            <div class="card-header alert-info">
                <h4>设计方案</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/design/getAll" method="get">
                    <p>设计方案是设计师入口，管理所有用户的设计方案，定期维护更新。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-info">进入入口</button>
                </form>
            </div>
        </div>
        <div class="card mb-4">
            <div class="card-header alert-link">
                <h4>建材管理</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/material/getAll" method="get">
                    <p>所有建材的维护入口，通过该入口对蓝房子支持的建材进行维护。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-dark">进入入口</button>
                </form>
            </div>
        </div>
        <div class="card mb-4">
            <div class="card-header alert-dark">
                <h4>添加角色</h4>
            </div>
            <div class="card-body">
                <form class="form-inline mt-2 mt-md-0" action="/admin/getAll" method="get">
                    <p>只有超级管理员能够进入，该入口添加各种角色，如管理员、设计师等。</p>
                    <button type="submit" class="btn btn-lg btn-block btn-outline-dark">进入入口</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
