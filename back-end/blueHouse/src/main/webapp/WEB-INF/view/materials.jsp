<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lihan
  Date: 2018/10/1
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎进入蓝房子-建材管理</title>
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
            <p class="lead">建材管理</p>
            <p><%=session.getAttribute("username")%> : 您好! </p>
        </header>
        <nav class="nav float-right">
            <a class="nav-link" href="/user/getAll">用户管理</a>
            <a class="nav-link active" href="/order/getAll">订单管理</a>
            <a class="nav-link" href="/activity/getAll">活动管理</a>
            <a class="nav-link" href="/project/getAll">施工项目</a>
            <a class="nav-link" href="/design/getAll">设计方案</a>
            <a class="nav-link" href="/material/getAll">建材管理</a>
            <a class="nav-link" href="/admin/getAll">权限管理</a>
        </nav>
    </div>
</section>
<div class="container">
    <c:if test="${permissionCode == true}" >
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item active">
            <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                <h4>建材列表</h4>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                <h4>添加建材</h4>
            </a>
        </li>
    </ul>
    <br>
    <div class="tab-content">
        <div id="panel1" class="container tab-pane active">
            <c:if test="${isSearching == false }">
                <div class="alert alert-link">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("materialsCount")%>个建材，您可以通过搜索框对活动进行搜索！如果搜索条件为空，那么系统将为您列出所有建材！
                </div>
            </c:if>
            <c:if test="${isSearching == true }">
                <div class="alert alert-link">
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("materialsCount")%>个优惠活动，您可以通过将搜索框置空，并点击"搜索"重新获取所有优惠活动！
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4">
                    <form class="form-inline mt-2 mt-md-0" action="/material/searchMaterials" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="建材名称" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${isSearching}">
                        <div class="col-md-8">
                            <c:if test="${materialsCount == 0}">
                                <div class="alert alert-warning">
                                    <p>抱歉！系统中没有搜索到相关建材！请重新选择搜索条件并继续搜索！</p>
                                </div>
                            </c:if>
                            <div id="accordion1">
                                <c:forEach var="material" items="${materials}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${material.id}">
                                                建材: (${material.id})
                                            </a>
                                        </div>
                                        <div id="collapse${material.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/material/updateMaterial" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>建材品牌: </label>
                                                                <input id="brand" name="brand" type="text" value="${material.brand}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材名称: </label>
                                                                <input id="name" name="name" type="text" value="${material.name}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材链接: </label>
                                                                <input id="url" name="url" type="text" value="${material.url}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>建材分类: </label>
                                                                <input id="category" name="category" type="text" value="${material.category}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材价格: </label>
                                                                <input id="price" name="price" type="text" value="${material.price}">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="materialId" name="materialId" value="${material.id}"/>
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
                                <c:forEach var="material" items="${materials}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${material.id}">
                                                建材: (${material.id})
                                            </a>
                                        </div>
                                        <div id="collapse${material.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/material/updateMaterial" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>建材品牌: </label>
                                                                <input id="brand" name="brand" type="text" value="${material.brand}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材名称: </label>
                                                                <input id="name" name="name" type="text" value="${material.name}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材链接: </label>
                                                                <input id="url" name="url" type="text" value="${material.url}">
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <label>建材分类: </label>
                                                                <input id="category" name="category" type="text" value="${material.category}">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>建材价格: </label>
                                                                <input id="price" name="price" type="text" value="${material.price}">
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-3 float-right">
                                                            <input type="hidden" id="materialId" name="materialId" value="${material.id}"/>
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
                <form class="form-inline mt-2 mt-md-0" action="/material/insertiMaterial" method="get">
                    <blockquote class="blockquote mb-0">
                        <div class="row">
                            <div class="col-md-6">
                                <label>建材ID: </label>
                                <input id="materialId" name="materialId" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>建材品牌: </label>
                                <input id="brand" name="brand" type="text" value="${material.brand}">
                            </div>
                            <div class="col-md-6">
                                <label>建材名称: </label>
                                <input id="name" name="name" type="text" value="${material.name}">
                            </div>
                            <div class="col-md-6">
                                <label>建材链接: </label>
                                <input id="url" name="url" type="text" value="${material.url}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>建材分类: </label>
                                <input id="category" name="category" type="text" value="${material.category}">
                            </div>
                            <div class="col-md-6">
                                <label>建材价格: </label>
                                <input id="price" name="price" type="text" value="${material.price}">
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
    <c:if test="${permissionCode == false }">
        <div class="alert alert-warning">
            <p>抱歉！你没有此模块的登录权限,请联系管理员添加!</p>
        </div>
    </c:if>
</div>
</body>
</html>
