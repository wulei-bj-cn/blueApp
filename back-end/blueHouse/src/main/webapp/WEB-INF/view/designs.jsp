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
    <title>欢迎进入蓝房子-设计管理</title>
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
                <p class="lead">设计管理</p>
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
                <h4>设计列表</h4>
            </a>
        </li>
        <!--
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                <h4>添加设计</h4>
            </a>
        </li>
        -->
    </ul>
    <br>
    <div class="tab-content">
        <div id="panel1" class="container tab-pane active">
            <c:if test="${isSearching == false }">
                <div class="alert alert-info">
                    <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("designsCount")%>个设计方案，您可以通过搜索框对设计进行搜索！如果搜索条件为空，那么系统将为您列出所有设计方案！
                </div>
            </c:if>
            <c:if test="${isSearching == true }">
                <div class="alert alert-info">
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("designsCount")%>个设计方案，，您可以通过将搜索框置空，并点击"搜索"重新获取所有设计方案！
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4">
                    <form class="form-inline mt-2 mt-md-0" action="/design/searchDesigns" method="get">
                        <input id="searchKey" name="searchKey" class="form-control mr-sm-2" type="text" placeholder="设计名称" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${isSearching}">
                        <div class="col-md-8">
                            <c:if test="${designsCount == 0}">
                                <div class="alert alert-warning">
                                    <p>抱歉！系统中没有搜索到相关设计方案！请重新选择搜索条件并继续搜索！</p>
                                </div>
                            </c:if>
                            <div id="accordion1">
                                <c:forEach var="design" items="${searchDesigns}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${design.id}">
                                                设计: (${design.id})
                                            </a>
                                        </div>
                                        <div id="collapse${design.id}" class="collapse" data-parent="#accordion1">
                                            <div class="card-body">
                                                <form class="form-inline mt-2 mt-md-0" action="/design/updateDesign" method="get">
                                                    <blockquote class="blockquote mb-0">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="">设计名称:${design.name}</div>
                                                                <div class="">设计师:${design.designer}</div>
                                                                <div class="">设计详情:${design.details}</div>
                                                                <div class="">设计时间:${design.ts}</div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <c:if test="${design.url != null && design.url != ''}">
                                                                    <img src="/img/designs/${design.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic_inline_${design.id}">
                                                                    <div class="col-md-6">
                                                                        <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#upload_design_modal_${design.id}">上传图片</button>
                                                                    </div>
                                                                    <form class="form-inline mt-2 mt-md-0" action="/design/uploadDesignWithFile" method="post" enctype="multipart/form-data">
                                                                    <div class="modal fade" id="upload_design_modal_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                        <div class="modal-dialog modal-lg" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <span class="badge badge-info float-left">上传设计图片</span>
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="">设计名称:<input id="name" name="name" type="text" value="${design.name}"></div>
                                                                                    <div class="">设计师:<input id="designer" name="designer" type="text" value="${design.designer}"></div>
                                                                                    <div class="">设计说明:<input id="details" name="details" type="text" value="${design.details}"></div>
                                                                                    <br>
                                                                                    <div class="form-group">
                                                                                        <label for="design_file">上传设计方案截图</label>
                                                                                        &nbsp;
                                                                                        <input type="file" name="design_file" id="design_file" />
                                                                                        &nbsp;
                                                                                        <input type="text" id="design_id" name="design_id" value="${design.id}" hidden="true"/>

                                                                                    </div>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="submit" class="btn btn-outline-info">确定</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    </form>
                                                                    <div class="modal fade" id="design_pic_inline_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                        <div class="modal-dialog">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <img src="/img/designs/${design.url}" alt="" style="width:100%;">
                                                                                </div>

                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${design.url == null || design.url == ''}">
                                                                    <p>目前还没有对设计方案上传图片，点击<span class="badge badge-info">上传设计图片</span>添加设计图片。</p>
                                                                    <br>
                                                                    <div class="col-md-6">
                                                                        <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#new_design_modal_${design.id}">上传图片</button>
                                                                    </div>
                                                                    <form class="form-inline mt-2 mt-md-0" action="/design/uploadDesignWithFile" method="post" enctype="multipart/form-data">
                                                                        <div class="modal fade" id="new_design_modal_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                            <div class="modal-dialog modal-lg" role="document">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <span class="badge badge-info float-left">上传设计图片</span>
                                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                    </div>
                                                                                    <div class="modal-body">
                                                                                        <div class="">设计名称:<input id="name" name="name" type="text" value="${design.name}"></div>
                                                                                        <div class="">设计师:<input id="designer" name="designer" type="text" value="${design.designer}"></div>
                                                                                        <div class="">设计说明:<input id="details" name="details" type="text" value="${design.details}"></div>
                                                                                        <br>
                                                                                        <div class="form-group">
                                                                                            <label for="design_file">上传设计方案截图</label>
                                                                                            &nbsp;
                                                                                            <input type="file" name="design_file" id="design_file" />
                                                                                            &nbsp;
                                                                                            <input type="text" id="design_id" name="design_id" value="${design.id}" hidden="true"/>

                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <button type="submit" class="btn btn-outline-info">确定</button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </c:if>
                                                            </div>
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
                                <c:forEach var="design" items="${designs}">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link collapsed" data-toggle="collapse" href="#collapse${design.id}">
                                                设计 :(${design.id})
                                            </a>
                                        </div>
                                        <div id="collapse${design.id}" class="collapse" data-parent="#accordion2">
                                            <div class="card-body">
                                                <blockquote class="blockquote mb-0">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="">设计名称:${design.name}</div>
                                                            <div class="">设计师:${design.designer}</div>
                                                            <div class="">设计详情:${design.details}</div>
                                                            <div class="">设计时间:${design.ts}</div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <c:if test="${design.url != null && design.url != ''}">
                                                            <img src="/img/designs/${design.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic_inline_${design.id}">
                                                                <div class="col-md-6">
                                                                    <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#upload_design_modal_${design.id}">上传图片</button>
                                                                </div>
                                                                <form class="form-inline mt-2 mt-md-0" action="/design/uploadDesignWithFile" method="post" enctype="multipart/form-data">
                                                                <div class="modal fade" id="upload_design_modal_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                    <div class="modal-dialog modal-lg" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <span class="badge badge-info float-left">上传设计图片</span>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="">设计名称:<input id="name" name="name" type="text" value="${design.name}"></div>
                                                                                <div class="">设计师:<input id="designer" name="designer" type="text" value="${design.designer}"></div>
                                                                                <div class="">设计说明:<input id="details" name="details" type="text" value="${design.details}"></div>
                                                                                <br>
                                                                                <div class="form-group">
                                                                                    <label for="design_file">上传设计方案截图</label>
                                                                                    &nbsp;
                                                                                    <input type="file" name="design_file" id="design_file" />
                                                                                    &nbsp;
                                                                                    <input type="text" id="design_id" name="design_id" value="${design.id}" hidden="true"/>

                                                                                </div>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="submit" class="btn btn-outline-info">确定</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                </form>

                                                            <div class="modal fade" id="design_pic_inline_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <img src="/img/designs/${design.url}" alt="" style="width:100%;">
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </c:if>
                                                            <c:if test="${design.url == null || design.url == ''}">
                                                                <p>目前还没有对设计方案上传图片，点击<span class="badge badge-info">上传设计图片</span>添加设计图片。</p>
                                                                <br>
                                                                <div class="col-md-6">
                                                                    <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#new_design_modal_${design.id}">上传图片</button>
                                                                </div>
                                                                <form class="form-inline mt-2 mt-md-0" action="/design/uploadDesignWithFile" method="post" enctype="multipart/form-data">
                                                                    <div class="modal fade" id="new_design_modal_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                                        <div class="modal-dialog modal-lg" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <span class="badge badge-info float-left">上传设计图片</span>
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="">设计名称:<input id="name" name="name" type="text" value="${design.name}"></div>
                                                                                    <div class="">设计师:<input id="designer" name="designer" type="text" value="${design.designer}"></div>
                                                                                    <div class="">设计说明:<input id="details" name="details" type="text" value="${design.details}"></div>
                                                                                    <br>
                                                                                    <div class="form-group">
                                                                                        <label for="design_file">上传设计方案截图</label>
                                                                                        &nbsp;
                                                                                        <input type="file" name="design_file" id="design_file" />
                                                                                        &nbsp;
                                                                                        <input type="text" id="design_id" name="design_id" value="${design.id}" hidden="true"/>

                                                                                    </div>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="submit" class="btn btn-outline-info">确定</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </c:if>
                                                        </div>
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
            <div class="container">
                <form class="form-inline mt-2 mt-md-0" action="/design/insertDesign" method="get">
                    <blockquote class="blockquote mb-0">
                        <div class="row">
                            <div class="col-md-6">
                                <label>设计ID: </label>
                                <input id="designId" name="designId" type="text" value="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>设计名称: </label>
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
                                <label>设计师: </label>
                                <input id="designer" name="designer" type="text" value="">
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
