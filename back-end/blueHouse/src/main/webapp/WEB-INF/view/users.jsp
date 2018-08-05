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
    <title>欢迎进入蓝房子-用户管理</title>
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
            <p class="lead">用户管理</p>
        </header>
    </div>
</section>
<div class="container">
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item active">
            <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                <h4>下单用户</h4>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                <h4>到访用户</h4>
            </a>
        </li>
    </ul>
    <br>
    <div class="tab-content">
        <div id="panel1" class="container tab-pane active">
            <c:if test="${isSearching == false }">
                <div class="alert alert-success">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("usersCount")%>位下单用户，您可以通过搜索框对某个用户进行搜索！如果搜索条件为空，那么系统将为您列出所有下单用户！
                </div>
            </c:if>
            <c:if test="${isSearching == true }">
                <div class="alert alert-success">
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("usersCount")%>位相关用户，您可以通过将搜索框置空，并点击"搜索"重新获取所有下单用户！
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4">
                    <form class="form-inline mt-2 mt-md-0" action="/user/searchUsers" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="用户名或用户ID" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${isSearching}">
                        <div class="col-md-8">
                            <div id="accordion1">
                                <c:forEach var="user" items="${searchUsers}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${user.id}">
                                                用户: ${user.name}(${user.id})
                                            </a>
                                        </div>
                                        <div id="collapse${user.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <blockquote class="blockquote mb-0">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label>姓名: </label>
                                                            <input type="text" value="${user.name}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>年龄: </label>
                                                            <input type="text" value="${user.age}">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label>电话: </label>
                                                            <input type="text" value="${user.phone}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>住址: </label>
                                                            <input type="text" value="${user.address}">
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="col-md-3 float-right">
                                                        <button type="button" class="btn btn-sm btn-block btn-outline-success">保存</button>
                                                    </div>
                                                    <br>
                                                </blockquote>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-md-8">
                            <div id="accordion2">
                                <c:forEach var="user" items="${users}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${user.id}">
                                                用户: ${user.name}(${user.id})
                                            </a>
                                        </div>
                                        <div id="collapse${user.id}" class="collapse" data-parent="#accordion2">
                                            <div class="card-body">
                                                <blockquote class="blockquote mb-0">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label>姓名: </label>
                                                            <input type="text" value="${user.name}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>年龄: </label>
                                                            <input type="text" value="${user.age}">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <label>电话: </label>
                                                            <input type="text" value="${user.phone}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label>住址: </label>
                                                            <input type="text" value="${user.address}">
                                                        </div>
                                                    </div>
                                                    <br>
                                                    <div class="col-md-3 float-right">
                                                        <button type="button" class="btn btn-sm btn-block btn-outline-success">保存</button>
                                                    </div>
                                                    <br>
                                                </blockquote>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="panel2" class="container tab-pane fade"><br>
            <div class="alert alert-info">
                <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("accessCount")%>位到访用户，统计到访用户有利于后期我们做大数据统计分析，为您提供更好的决策!
            </div>
            <div class="container">
                <c:forEach var="acs" items="${access}">
                    <div class="card-deck mb-1">
                        <div class="card mb-1">
                            <div class="card-header alert-info">
                                <h4>到访用户: ${acs.id} (在网时间: ${acs.sojourn} 秒)</h4>
                            </div>
                            <div class="card-body">
                                <p>到访时间: ${acs.start_time}</p>
                                <p>离开时间: ${acs.end_time}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

</body>
</html>
