<%@ page import="com.blueHouse.pojo.browse.T_Solution" %>
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
    <title>欢迎进入蓝房子-套餐管理</title>
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
            <p class="lead">套餐管理</p>
            <p><%=session.getAttribute("username")%> : 您好! </p>
        </header>
        <nav class="nav float-right">
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
</section>
<div class="container">
    <c:if test="${permissionCode == true}" >
        <ul class="nav nav-tabs nav-justified" role="tablist">
            <li class="nav-item active">
                <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                    <h4>套餐列表</h4>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                    <h4>添加套餐</h4>
                </a>
            </li>
        </ul>
        <br>
        <div class="tab-content">
            <div id="panel1" class="container tab-pane active">
                <div class="navbar col-md-12 bg-dark">
                    <form class="form-inline mt-2 mt-md-0" action="/solution/searchSolutions" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="套餐号/套餐名称" aria-label="Search">
                        <button class="btn btn-outline-light my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                    <label class="btn-outline-light">如果搜索条件为空，默认将列出所有订单！</label>
                </div>
                <br>
                <c:if test="${isSearching == false }">
                    <div class="alert alert-info">
                        <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("solutionsCount")%>个套餐方案，您可以通过搜索框对套餐进行搜索！如果搜索条件为空，那么系统将为您列出所有套餐方案！
                    </div>
                </c:if>
                <c:if test="${isSearching == true }">
                    <div class="alert alert-info">
                        <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("solutionsCount")%>个套餐方案，，您可以通过将搜索框置空，并点击"搜索"重新获取所有套餐方案！
                    </div>
                </c:if>
                <div class="row">
                    <c:choose>
                        <c:when test="${isSearching}">
                            <div class="col-md-12">
                                <c:if test="${solutionsCount == 0}">
                                    <div class="alert alert-warning">
                                        <p>抱歉！系统中没有搜索到相关套餐方案！请重新选择搜索条件并继续搜索！</p>
                                    </div>
                                </c:if>
                                <div id="accordion1">
                                    <c:forEach var="solution" items="${searchSolutions}">
                                        <div class="card">
                                            <div class="card-header">
                                                <a class="card-link collapsed" data-toggle="collapse" href="#collapse${solution.id}">
                                                    套餐: (${solution.name})
                                                </a>
                                            </div>
                                            <div id="collapse${solution.id}" class="collapse" data-parent="#accordion1">
                                                <div class="card-body">
                                                    <form class="form-inline mt-2 mt-md-0" action="/solution/updateSolution" method="get">
                                                        <blockquote class="blockquote mb-0">
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <label>套餐名称: </label>
                                                                    <input id="name" name="name" type="text" value="${solution.name}">
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <label>链接: </label>
                                                                    <input id="url" name="url" type="text" value="${solution.url}">
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-md-6">
                                                                    <label>时间: </label>
                                                                    <input id="ts" name="ts" type="text" value="${solution.ts}" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="col-md-3 float-right">
                                                                <input type="hidden" id="solutionId" name="solutionId" value="${solution.id}"/>
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
                            <div class="col-md-12">
                                <div id="accordion2">
                                    <c:forEach var="solution" items="${solutions}">
                                        <div class="card">
                                            <div class="card-header">
                                                <a class="card-link collapsed" data-toggle="collapse" href="#collapse${solution.id}">
                                                    套餐 :(${solution.name})
                                                </a>
                                            </div>
                                            <div id="collapse${solution.id}" class="collapse" data-parent="#accordion2">
                                                <div class="card-body">
                                                    <blockquote class="blockquote mb-0">
                                                        <ul class="nav nav-tabs nav-justified" role="tablist">
                                                            <li class="nav-item active">
                                                                <a class="nav-link active" data-toggle="tab" href="#panel1_${solution.id}" role="tab">
                                                                    <h6 class="text-primary">套餐封面</h6>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a class="nav-link" data-toggle="tab" href="#panel2_${solution.id}" role="tab">
                                                                    <h6 class="text-danger">套餐详情</h6>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                        <br>
                                                        <div class="tab-content">
                                                            <div id="panel1_${solution.id}" class="container tab-pane active"><br>
                                                                <c:choose>
                                                                    <c:when test="${solution.cover == null}">
                                                                        <p>目前该套餐还没有封面，点击<span class="badge badge-primary">添加封面</span>添加套餐封面</p>
                                                                        <br>
                                                                        <div class="col-md-3">
                                                                            <button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#add_solution_cover_${solution.id}">添加套餐封面</button>
                                                                        </div>
                                                                        <form class="form-inline mt-2 mt-md-0" action="/solution/uploadSolutionCover" method="post" enctype="multipart/form-data">
                                                                            <div class="modal fade" id="add_solution_cover_${solution.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                                <div class="modal-dialog modal-lg" role="document">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <span class="badge badge-primary float-left">添加套餐封面</span>
                                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                        </div>
                                                                                        <div class="modal-body">
                                                                                            <div class="form-group">
                                                                                                <label for="solution_name">名称</label>
                                                                                                &nbsp;
                                                                                                <input type="text" class="form-control" id="solution_name" name="solution_name" placeholder="请输入名称">
                                                                                            </div>
                                                                                            <br>
                                                                                            <div class="form-group">
                                                                                                <label for="solution_file">上传套餐封面</label>
                                                                                                &nbsp;
                                                                                                <input type="file" name="solution_file" id="solution_file" />
                                                                                                &nbsp;
                                                                                                <input type="text" id="solution_id" name="solution_id" value="${solution.id}" hidden="true"/>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="modal-footer">
                                                                                            <button type="submit" class="btn btn-outline-primary">确定</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </form>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="card-deck mb-1">
                                                                            <div class="card mb-12">
                                                                                <div class="card-header alert-info">
                                                                                    <h4>${solution.name}</h4>
                                                                                </div>
                                                                                <div class="card-body">
                                                                                    <div class="row">
                                                                                        <div class="col-md-4">
                                                                                            <p><label>套餐封面:</label></p>
                                                                                        </div>
                                                                                        <div class="col-md-8">
                                                                                            <img src="/img/solutions/${solution.cover}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#solution_cover_${solution.id}">
                                                                                            <div class="modal fade" id="solution_cover_${solution.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                                                <div class="modal-dialog">
                                                                                                    <div class="modal-content">
                                                                                                        <div class="modal-header">
                                                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                                                        </div>
                                                                                                        <div class="modal-body">
                                                                                                            <img src="/img/solutions/${solution.cover}" alt="" style="width:100%;">
                                                                                                        </div>
                                                                                                        <div class="modal-footer">
                                                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                                                        </div>
                                                                                                    </div><!-- /.modal-content -->
                                                                                                </div><!-- /.modal-dialog -->
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                            <div id="panel2_${solution.id}" class="container tab-pane fade"><br>
                                                                <c:choose>
                                                                    <c:when test="${solution.url == null}">
                                                                        <p>目前该套餐还没有图集，点击<span class="badge badge-danger">添加图集</span>添加套餐图集</p>
                                                                        <br>
                                                                        <div class="col-md-3">
                                                                            <button type="button" class="btn btn-block btn-outline-danger" data-toggle="modal" data-target="#add_solution_url_${solution.id}">添加套餐图集</button>
                                                                        </div>
                                                                        <form class="form-inline mt-2 mt-md-0" action="/solution/uploadSolutionUrl" method="post" enctype="multipart/form-data">
                                                                            <div class="modal fade" id="add_solution_url_${solution.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                                <div class="modal-dialog modal-lg" role="document">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <span class="badge badge-danger float-left">添加套餐图集</span>
                                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                        </div>
                                                                                        <div class="modal-body">
                                                                                            <div class="form-group">
                                                                                                <label for="solution_name">名称</label>
                                                                                                &nbsp;
                                                                                                <input type="text" class="form-control" id="solution_name" name="solution_name" placeholder="请输入名称">
                                                                                            </div>
                                                                                            <br>
                                                                                            <div class="form-group">
                                                                                                <label for="solution_file">上传套餐图集</label>
                                                                                                &nbsp;
                                                                                                <input type="file" name="solution_files" id="solution_files" multiple="multiple" />
                                                                                                &nbsp;
                                                                                                <input type="text" id="solution_id" name="solution_id" value="${solution.id}" hidden="true"/>
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="modal-footer">
                                                                                            <button type="submit" class="btn btn-outline-danger">确定</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </form>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <%
                                                                            T_Solution t_solution = (T_Solution) pageContext.getAttribute("solution");

                                                                            String[] sol_pics = t_solution.getUrl().split(",");
                                                                            pageContext.setAttribute("sol_pics", sol_pics);
                                                                        %>
                                                                        <div class="card-deck mb-1">
                                                                            <div class="card mb-12">
                                                                                <div class="card-header alert-danger">
                                                                                    <h4>${solution.name}</h4>
                                                                                </div>
                                                                                <div class="card-body">
                                                                                    <div class="row">
                                                                                        <div class="col-md-4">
                                                                                            <p><label>套餐图集:</label></p>
                                                                                        </div>
                                                                                        <c:forEach var="sol_pic" items="${sol_pics}">
                                                                                            <c:if test="${sol_pic != ''}">
                                                                                                <div class="col-md-12">
                                                                                                    <img src="/img/solutions/${sol_pic}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#solution_url_${sol_pic}">
                                                                                                    <div class="modal fade" id="solution_url_${sol_pic}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                                                        <div class="modal-dialog">
                                                                                                            <div class="modal-content">
                                                                                                                <div class="modal-header">
                                                                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                                                                </div>
                                                                                                                <div class="modal-body">
                                                                                                                    <img src="/img/solutions/${sol_pic}" alt="" style="width:100%;">
                                                                                                                </div>
                                                                                                                <div class="modal-footer">
                                                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                                                                </div>
                                                                                                            </div><!-- /.modal-content -->
                                                                                                        </div><!-- /.modal-dialog -->
                                                                                                    </div>
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
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
                <div class="container">
                    <form class="form-inline mt-2 mt-md-0" action="/solution/insertSolution" method="get">
                        <blockquote class="blockquote mb-0">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>套餐ID: </label>
                                    <input id="solutionId" name="solutionId" type="text" value="">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>套餐名称: </label>
                                    <input id="name" name="name" type="text" value="">
                                </div>
                                <div class="col-md-6">
                                    <label>链接: </label>
                                    <input id="url" name="url" type="text" value="">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>时间: </label>
                                    <input id="ts" name="ts" type="text" value="" onfocus="setDate(this,'yyyy-MM-dd HH:mm')">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>套餐师: </label>
                                    <input id="solutioner" name="solutioner" type="text" value="">
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

