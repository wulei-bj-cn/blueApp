<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihan
  Date: 2018/9/8
  Time: 上午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入蓝房子-活动管理</title>
    <link href="<%=request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/resources/js/dateformat.js"></script>
</head>
<body>
<section class="jumbotron alert-info">
    <div class="container">
        <header>
            <h1><a href="/console/getAll">蓝房子后台管理中心</a></h1>
            <p class="lead">管理者管理</p>
            <p><%=session.getAttribute("user")%> : 您好! </p>
        </header>
    </div>
</section>
<div class="container">
    <c:if test="false" >
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item active">
            <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                <h4>管理者列表</h4>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                <h4>添加管理者</h4>
            </a>
        </li>
    </ul>
    <br>
    <div class="tab-content">
        <div id="panel1" class="container tab-pane active">
            <c:if test="${isSearching == false }">
                <div class="alert alert-light">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("adminsCount")%>个管理者，您可以通过搜索框对活动进行搜索！如果搜索条件为空，那么系统将为您列出所有管理者！
                </div>
            </c:if>
            <c:if test="${isSearching == true }">
                <div class="alert alert-light">
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("adminsCount")%>个管理者，您可以通过将搜索框置空，并点击"搜索"重新获取所有管理者！
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4">
                    <form class="form-inline mt-2 mt-md-0" action="/admin/searchAdmins" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="管理者名称" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${isSearching}">
                        <div class="col-md-8">
                            <c:if test="${adminsCount == 0}">
                                <div class="alert alert-warning">
                                    <p>抱歉！系统中没有搜索到相关管理员！请重新选择搜索条件并继续搜索！</p>
                                </div>
                            </c:if>
                            <div id="accordion1">
                                <c:forEach var="admin" items="${searchAdmins}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${admin.id}">
                                                管理员: (${admin.id})
                                            </a>
                                        </div>
                                        <div id="collapse${admin.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/admin/updateAdmin" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>管理者登录名: </label>
                                                                <input id="login" name="login" type="text" value="${admin.login}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>管理者名称: </label>
                                                                <input id="name" name="name" type="text" value="${admin.name}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>密码: </label>
                                                                <input id="password" name="password" type="text" value="${admin.password}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>管理者角色: </label>
                                                                <input id="role" name="role" type="text" value="${admin.role}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>管理者状态: </label>
                                                                <input id="status" name="status" type="text" value="${admin.status}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>最后登录时间: </label>
                                                                <input id="last_log_on" name="last_log_on" type="text" value="${admin.last_log_on}">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="adminId" name="adminId" value="${admin.id}"/>
                                                            <button type="submit" class="btn btn-sm btn-block btn-outline-success">更新</button>
                                                        </div>
                                                        <br>
                                                    </blockquote>
                                                </form>
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
                                <c:forEach var="admin" items="${admins}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${admin.id}">
                                                管理员 :(${admin.id})
                                            </a>
                                        </div>
                                        <div id="collapse${admin.id}" class="collapse" data-parent="#accordion2">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/admin/updateAdmin" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>管理者登录名: </label>
                                                                <input id="login" name="login" type="text" value="${admin.login}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>管理者名称: </label>
                                                                <input id="name" name="name" type="text" value="${admin.name}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>密码: </label>
                                                                <input id="password" name="password" type="text" value="${admin.password}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>管理者角色: </label>
                                                                <input id="role" name="role" type="text" value="${admin.role}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>管理者状态: </label>
                                                                <input id="status" name="status" type="text" value="${admin.status}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>最后登录时间: </label>
                                                                <input id="last_log_on" name="last_log_on" type="text" value="${admin.last_log_on}">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="adminId" name="adminId" value="${admin.id}"/>
                                                            <button type="submit" class="btn btn-sm btn-block btn-outline-success">更新</button>
                                                        </div>
                                                        <br>
                                                    </blockquote>
                                                </form>
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
            <div class="container">
                <form class="form-inline mt-2 mt-md-0" action="/admin/insertAdmin" method="get">
                    <blockquote class="blockquote mb-0">
                        <div class="row">
                            <div class="col-md-6">
                                <label>管理员ID: </label>
                                <input id="adminId" name="adminId" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>管理者登录名: </label>
                                <input id="login" name="login" type="text" value="">
                            </div>
                            <div class="col-md-6">
                                <label>管理者名称: </label>
                                <input id="name" name="name" type="text" value="">
                            </div>
                            <div class="col-md-6">
                                <label>密码: </label>
                                <input id="password" name="password" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>管理者角色: </label>
                                <input id="role" name="role" type="text" value="">
                            </div>
                            <div class="col-md-6">
                                <label>管理者状态: </label>
                                <input id="status" name="status" type="text" value="">
                            </div>
                            <div class="col-md-6">
                                <label>最后登录时间: </label>
                                <input id="last_log_on" name="last_log_on" type="text" value="">
                            </div>
                        </div>
                        <br>
                        <div class="col-md-3 float-right">
                            <button type="submit" class="btn btn-sm btn-block btn-outline-success">插入</button>
                        </div>
                        <br>
                    </blockquote>
                </form>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="true">
        <div class="alert alert-warning">
            <p>抱歉！系统中没有搜索到相关管理员！请重新选择搜索条件并继续搜索！</p>
        </div>
    </c:if>
</div>
</body>
</html>
