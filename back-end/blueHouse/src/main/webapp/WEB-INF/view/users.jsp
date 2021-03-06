<%@ page import="com.blueHouse.pojo.orders.Order" %>
<%@ page import="com.blueHouse.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.blueHouse.service.OrderService" %>
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
    <%
        WebApplicationContext springContext =  WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        OrderService orderService = (OrderService) springContext.getBean("orderService");
    %>
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
                <p class="lead">用户管理</p>
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
    <c:if test="${permissionCode == true}" >
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
            <c:if test="${isSearching == false}">
                <div class="alert alert-success">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("usersCount")%>位下单用户，您可以通过搜索框对某个用户进行搜索！如果搜索条件为空，那么系统将为您列出所有下单用户！
                </div>
            </c:if>
            <c:if test="${isSearching == true}">
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
                            <c:if test="${usersCount == 0}">
                                <div class="alert alert-warning">
                                    <p>抱歉！系统中没有搜索到相关用户！请重新选择搜索条件并继续搜索！</p>
                                </div>
                            </c:if>
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
                                                <form class="form-inline mt-2 mt-md-0" action="/user/updateUser" method="get">
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户姓名</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.name}" id="userName" name="userName">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户年龄</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.age}" id="userAge" name="userAge">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户电话</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.phone}" id="userPhone" name="userPhone">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户地址</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.address}" id="userAddress" name="userAddress">
                                                    </div>
                                                    <div class="col-md-3 float-left">
                                                        <input type="hidden" id="userID" name="userID" value="${user.id}"/>
                                                        <button type="submit" class="btn btn-sm btn-block btn-outline-success">更新</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="card-footer">
                                                <%
                                                    User user = (User) pageContext.getAttribute("user");
                                                    List<Order> orders = orderService.findOrderByUserId(user.getId());

                                                    pageContext.setAttribute("orders", orders);
                                                %>
                                                <c:forEach var="order" items="${orders}">
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><a href="/order/searchOrders?order_id=${order.id}&user_id=${user.id}&searchKey=users.jsp">订单: ${order.id}</a></span>
                                                        </div>
                                                    </div>
                                                </c:forEach>
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
                                                <form class="form-inline mt-2 mt-md-0" action="/user/updateUser" method="get">
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户姓名</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.name}" id="userName" name="userName">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户年龄</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.age}" id="userAge" name="userAge">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户电话</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.phone}" id="userPhone" name="userPhone">
                                                    </div>
                                                    <div class="input-group mb-3 col-md-6">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text">用户地址</span>
                                                        </div>
                                                        <input type="text" class="form-control" value="${user.address}" id="userAddress" name="userAddress">
                                                    </div>
                                                    <div class="col-md-3 float-left">
                                                        <input type="hidden" id="userID" name="userID" value="${user.id}"/>
                                                        <button type="submit" class="btn btn-sm btn-block btn-outline-success">更新</button>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="card-footer">
                                                <%
                                                    User user = (User) pageContext.getAttribute("user");
                                                    List<Order> orders = orderService.findOrderByUserId(user.getId());

                                                    pageContext.setAttribute("orders", orders);
                                                %>
                                                <c:forEach var="order" items="${orders}">
                                                        <div class="input-group mb-3 col-md-6">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text"><a href="/order/searchOrders?order_id=${order.id}&user_id=${user.id}&searchKey=users.jsp">订单: ${order.id}</a></span>
                                                            </div>
                                                        </div>
                                                </c:forEach>
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
    </c:if>
    <c:if test="${permissionCode == false }">
        <div class="alert alert-warning">
            <p>抱歉！你没有此模块的登录权限,请联系管理员添加!</p>
        </div>
    </c:if>
</div>

</body>
</html>
