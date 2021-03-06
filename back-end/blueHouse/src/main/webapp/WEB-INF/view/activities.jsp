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
<section class="jumbotron alert-light">
    <div class="row">
        <div class="col-md-3">
            <img src="/img/common/logo.jpeg" class="rounded" width="320" height="150">
        </div>
        <div class="container col-md-9">
            <header>
                <h1><a href="/console/getAll">蓝房子后台管理中心</a></h1>
                <p class="lead">活动管理</p>
                <p><%=session.getAttribute("username")%> : 您好! </p>
            </header>
            <nav class="nav">
                <a class="nav-link" href="/user/getAll">用户管理</a>
                <a class="nav-link active" href="/order/getAll">订单管理</a>
                <a class="nav-link" href="/activity/getAll">活动管理</a>
                <a class="nav-link" href="/project/getAll">施工项目</a>
                <a class="nav-link" href="/design/getAll">设计方案</a>
                <a class="nav-link" href="/solution/getAll">套餐管理</a>
                <a class="nav-link" href="/material/getAll">建材管理</a>
                <a class="nav-link" href="/admin/getAll">权限管理</a>
            </nav>
        </div>
    </div>
</section>
<div class="container">
    <c:if test="${permissionCode == true}">
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item active">
            <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                <h4>活动列表</h4>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                <h4>添加活动</h4>
            </a>
        </li>
    </ul>
    <br>
    <div class="tab-content">
        <div id="panel1" class="container tab-pane active">
            <c:if test="${isSearching == false }">
                <div class="alert alert-primary">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("activitiesCount")%>个优惠活动，您可以通过搜索框对活动进行搜索！如果搜索条件为空，那么系统将为您列出所有优惠活动！
                </div>
            </c:if>
            <c:if test="${isSearching == true }">
                <div class="alert alert-primary">
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("activitiesCount")%>个优惠活动，您可以通过将搜索框置空，并点击"搜索"重新获取所有优惠活动！
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4">
                    <form class="form-inline mt-2 mt-md-0" action="/activity/searchActivities" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="活动名称" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${isSearching}">
                        <div class="col-md-8">
                            <c:if test="${activitiesCount == 0}">
                                <div class="alert alert-warning">
                                    <p>抱歉！系统中没有搜索到相关活动！请重新选择搜索条件并继续搜索！</p>
                                </div>
                            </c:if>
                            <div id="accordion1">
                                <c:forEach var="activity" items="${searchActivities}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${activity.id}">
                                                活动: (${activity.id})
                                            </a>
                                        </div>
                                        <div id="collapse${activity.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/activity/updateActivity" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>活动描述: </label>
                                                                <input id="des" name="des" type="text" value="${activity.des}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>链接: </label>
                                                                <input id="url" name="url" type="text" value="${activity.url}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>开始时间: </label>
                                                                <input id="startTime" name="startTime" type="text" value="${activity.start_time}" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>结束时间: </label>
                                                                <input id="endTime" name="endTime" type="text" value="${activity.end_time}" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="activityID" name="activityID" value="${activity.id}"/>
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
                                <c:forEach var="activity" items="${activities}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${activity.id}">
                                                活动 :(${activity.id})
                                            </a>
                                        </div>
                                        <div id="collapse${activity.id}" class="collapse" data-parent="#accordion2">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/activity/updateActivity" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>活动描述: </label>
                                                                <input id="des" name="des" type="text"
                                                                       value="${activity.des}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>链接: </label>
                                                                <input id="url" name="url" type="text"
                                                                       value="${activity.url}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>开始时间: </label>
                                                                <input id="startTime" name="startTime" type="text"
                                                                       value="${activity.start_time}" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>结束时间: </label>
                                                                <input id="endTime" name="endTime" type="text"
                                                                       value="${activity.end_time}" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="activityID" name="activityID" value="${activity.id}"/>
                                                            <button type="submit" class="btn btn-sm btn-block btn-outline-success">保存</button>
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
                <form class="form-inline mt-2 mt-md-0" action="/activity/insertActivity" method="get">
                    <blockquote class="blockquote mb-0">
                        <div class="row">
                            <div class="col-md-6">
                                <label>活动ID: </label>
                                <input id="activityID" name="activityID" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>活动描述: </label>
                                <input id="des" name="des" type="text" value="">
                            </div>
                            <div class="col-md-6">
                                <label>链接: </label>
                                <input id="url" name="url" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>开始时间: </label>
                                <input id="startTime" name="startTime" type="text" value="" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                            </div>
                            <div class="col-md-6">
                                <label>结束时间: </label>
                                <input id="endTime" name="endTime" type="text" value="" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
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
    <c:if test="${permissionCode == false}">
        <div class="alert alert-warning">
            <p>抱歉！你没有此模块的登录权限,请联系管理员添加!</p>
        </div>
    </c:if>
</div>
</body>
</html>
