<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.blueHouse.pojo.*" %>
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
    <title>欢迎进入蓝房子-订单管理</title>
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
            <p class="lead">订单管理</p>
        </header>
    </div>
</section>
<div class="container">
    <div class="navbar col-md-12 bg-dark">
        <form class="form-inline my-2 my-lg-0 float-right">
            <input class="form-control mr-sm-2" type="text" placeholder="订单号/用户名" aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">搜索</button>
        </form>
        <label class="btn-outline-light">如果搜索条件为空，默认将列出所有订单！</label>
    </div>
    <br>
    <c:if test="${isSearching == false }">
        <div class="alert alert-success">
            <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("ordersCount")%>个订单，您可以通过搜索框对某个订单/用户进行搜索！如果搜索条件为空，那么系统将为您列出所有订单！
        </div>
    </c:if>
    <c:if test="${isSearching == true }">
        <div class="alert alert-success">
            <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("ordersCount")%>个相关订单，您可以通过将搜索框置空，并点击"搜索"重新获取所有订单！
        </div>
    </c:if>
    <div id="accordion">
        <c:forEach var="orderItem" items="${orderItems}">
            <%
                OrderItems orderItem = (OrderItems) pageContext.getAttribute("orderItem");

                Measure measure = (orderItem.getMeasure().size() == 0)? null : orderItem.getMeasure().get(0);

                List<Contract> contracts = orderItem.getContracts();

                List<Contract> designContracts = (contracts.size() == 0)? null :
                        contracts
                        .stream()
                        .filter((Contract contract) -> contract.getType().startsWith("设计"))
                        .collect(Collectors.toList());

                List<Design> designs = (orderItem.getDesigns().size() == 0)? null: orderItem.getDesigns();

                List<Contract> projectContracts = (contracts.size() == 0)? null :
                        contracts
                        .stream()
                        .filter((Contract contract) -> contract.getType().startsWith("施工"))
                        .collect(Collectors.toList());

                Disclaim disclaim = (orderItem.getDisclaim().size() == 0)? null : orderItem.getDisclaim().get(0);

                List<Project> projects = (orderItem.getProjects().size() == 0)? null: orderItem.getProjects();

                pageContext.setAttribute("measure", measure);
                pageContext.setAttribute("designContracts", designContracts);
                pageContext.setAttribute("designs", designs);
                pageContext.setAttribute("projectContracts", projectContracts);
                pageContext.setAttribute("disclaim", disclaim);
                pageContext.setAttribute("projects", projects);
            %>
            <div class="card">
                <div class="card-header">
                    <a class="card-link collapsed" data-toggle="collapse" href="#Collapse_${orderItem.order_id}_${orderItem.user_id}">
                        订单: ${orderItem.order_id} / 用户: ${orderItem.user_id}
                    </a>
                </div>
                <div id="Collapse_${orderItem.order_id}_${orderItem.user_id}" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <ul class="nav nav-tabs nav-justified" role="tablist">
                                <li class="nav-item active">
                                    <a class="nav-link active" data-toggle="tab" href="#panel1_${orderItem.order_id}" role="tab">
                                        <h6>预约测量</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel2_${orderItem.order_id}" role="tab">
                                        <h6>设计合同</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel3_${orderItem.order_id}" role="tab">
                                        <h6>设计方案</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel4_${orderItem.order_id}" role="tab">
                                        <h6>施工交底</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel5_${orderItem.order_id}" role="tab">
                                        <h6>施工合同</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel6_${orderItem.order_id}" role="tab">
                                        <h6>施工项目</h6>
                                    </a>
                                </li>
                            </ul>
                            <br>
                            <div class="tab-content">
                                <div id="panel1_${orderItem.order_id}" class="container tab-pane active"><br>
                                    <c:choose>
                                        <c:when test="${measure == null}">
                                            <p>目前还没有对预约测量进行管理，点击<span class="badge badge-primary">新建预约测量</span>添加测量记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#new_measure_modal_${orderItem.order_id}">新建预约测量</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/uploadMeasure" method="get" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_measure_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="measure_file" id="measure_file" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-outline-success" data-dismiss="modal">确定</button>
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
                                                        <h4>${measure.name}</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <p><label>地址:</label>${measure.address}</p>
                                                                <p><label>测量时间:</label>${measure.ts}</p>
                                                                <p><label>测量人:</label>${measure.crew}</p>
                                                                <button type="button" class="btn btn-lg btn-block btn-outline-info">更新</button>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <img src="<%=request.getContextPath() %>/resources/img/measures/${measure.url}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#measure_${orderItem.order_id}">
                                                                <div class="modal fade" id="measure_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="<%=request.getContextPath() %>/resources/img/measures/${measure.url}" alt="" style="width:100%;">
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
                                <div id="panel2_${orderItem.order_id}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${designContracts == null}">
                                            <p>目前还没有对设计合同进行管理，点击<span class="badge badge-danger">新建设计合同</span>添加合同记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-danger" data-toggle="modal" data-target="#new_design_contr_modal_${orderItem.order_id}">新建设计合同</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_design_contr_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="txt_file" id="design_contr_file" multiple class="file-loading" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row">
                                                <ul class="nav flex-column nav-justified" role="tablist">
                                                    <c:forEach var="designContract" items="${designContracts}">
                                                        <c:choose>
                                                            <c:when test="${designContract.type == '设计合同'}">
                                                                <li class="nav-item active">
                                                                    <a class="nav-link active" href="#design_contr_${orderItem.order_id}_${designContract.id}" data-toggle="tab" role="tab">设计合同</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="nav-item">
                                                                    <a class="nav-link" href="#design_contr_${orderItem.order_id}_${designContract.id}" data-toggle="tab" role="tab">补充合同</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="#" data-toggle="tab" role="tab">上传设计合同</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach var="designContract" items="${designContracts}">
                                                        <c:set var="design_contr_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${designContract.type == '设计合同'}">
                                                            <c:set var="design_contr_tab_state" scope="session" value="active"/>
                                                        </c:if>
                                                        <div id="design_contr_${orderItem.order_id}_${designContract.id}" class="col-md-8 container tab-pane ${design_contr_tab_state}">
                                                            <div><h5>${designContract.name}</h5></div>
                                                            <img src="<%=request.getContextPath() %>/resources/img/contracts/${designContract.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_con_${orderItem.order_id}_${designContract.id}">
                                                            <div class="modal fade" id="design_con_${orderItem.order_id}_${designContract.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <img src="<%=request.getContextPath() %>/resources/img/contracts/${designContract.url}" alt="" style="width:100%;">
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel3_${orderItem.order_id}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${designs == null}">
                                            <p>目前还没有对设计方案进行管理，点击<span class="badge badge-info">新建设计方案</span>添加设计方案记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#new_design_modal_${orderItem.order_id}">新建设计方案</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_design_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="txt_file" id="design_file" multiple class="file-loading" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row">
                                                <ul class="nav flex-column nav-justified" role="tablist">
                                                    <c:forEach var="design" items="${designs}">
                                                        <li class="nav-item">
                                                            <a class="nav-link" href="#design_${orderItem.order_id}_${design.id}" data-toggle="tab" role="tab">设计方案_${design.id}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="nav-item active">
                                                        <a class="nav-link active" href="#" data-toggle="tab" role="tab">上传设计方案</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach var="design_index" begin="0" end="${designs.size() - 1}" step="1">
                                                        <c:set var="design" scope="session" value="${designs.get(design_index)}"/>
                                                        <c:set var="design_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${design_index == 0}">
                                                            <c:set var="design_tab_state" scope="session" value="active"/>
                                                        </c:if>
                                                        <div id="design_${orderItem.order_id}_${design.id}" class="col-md-8 container tab-pane ${design_tab_state}">
                                                            <div><h5>${design.name}</h5></div>
                                                            <div><h5>设计师: ${design.designer}</h5></div>
                                                            <div><h5>出版时间: ${design.ts}</h5></div>
                                                            <img src="<%=request.getContextPath() %>/resources/img/designs/${design.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic_${orderItem.order_id}_${design.id}">
                                                            <div class="modal fade" id="design_pic_${orderItem.order_id}_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <img src="<%=request.getContextPath() %>/resources/img/designs/${design.url}" alt="" style="width:100%;">
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel4_${orderItem.order_id}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${disclaim == null}">
                                            <p>目前还没有对施工交底进行管理，点击<span class="badge badge-secondary">新建施工交底</span>添加交底记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-secondary" data-toggle="modal" data-target="#new_disclaim_modal_${orderItem.order_id}">新建施工交底</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_disclaim_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="txt_file" id="disclaim_file" multiple class="file-loading" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
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
                                                        <h4>${disclaim.name}</h4>
                                                    </div>
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <p><label>测量时间:</label>${disclaim.ts}</p>
                                                                <p><label>测量人:</label>${disclaim.crew}</p>
                                                                <button type="button" class="btn btn-lg btn-block btn-outline-info">更新</button>
                                                            </div>
                                                            <div class="col-md-8">
                                                                <img src="<%=request.getContextPath() %>/resources/img/disclaims/${disclaim.url}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#disclaim_${orderItem.order_id}">
                                                                <div class="modal fade" id="disclaim_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="<%=request.getContextPath() %>/resources/img/disclaims/${disclaim.url}" alt="" style="width:100%;">
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel5_${orderItem.order_id}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${projectContracts == null}">
                                            <p>目前还没有对施工合同进行管理，点击<span class="badge badge-success">新建施工合同</span>添加合同记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-success" data-toggle="modal" data-target="#new_project_contr_modal_${orderItem.order_id}">新建施工合同</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_project_contr_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="txt_file" id="project_contr_file" multiple class="file-loading" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="row">
                                                <ul class="nav flex-column nav-justified" role="tablist">
                                                    <c:forEach var="projectContract" items="${projectContracts}">
                                                        <c:choose>
                                                            <c:when test="${projectContract.type == '施工合同'}">
                                                                <li class="nav-item active">
                                                                    <a class="nav-link active" href="#project_${orderItem.order_id}_${projectContract.id}" data-toggle="tab" role="tab">施工合同</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="nav-item">
                                                                    <a class="nav-link" href="#project_${orderItem.order_id}_${projectContract.id}" data-toggle="tab" role="tab">补充合同</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="#" data-toggle="tab" role="tab">上传施工合同</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach var="projectContract" items="${projectContracts}">
                                                        <c:set var="project_contr_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${projectContract.type == '施工合同'}">
                                                            <c:set var="project_contr_tab_state" scope="session" value="active"/>
                                                        </c:if>
                                                        <div id="project_${orderItem.order_id}_${projectContract.id}" class="col-md-8 container tab-pane ${project_contr_tab_state}">
                                                            <div><h5>${projectContract.name}</h5></div>
                                                            <img src="<%=request.getContextPath() %>/resources/img/contracts/${projectContract.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#project_con_${orderItem.order_id}_${projectContract.id}">
                                                            <div class="modal fade" id="project_con_${orderItem.order_id}_${projectContract.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <img src="<%=request.getContextPath() %>/resources/img/contracts/${projectContract.url}" alt="" style="width:100%;">
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel6_${orderItem.order_id}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${projects == null}">
                                            <p>目前还没有对施工项目进行管理，点击<span class="badge badge-warning">新建施工项目</span>添加项目记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-warning" data-toggle="modal" data-target="#new_project_modal_${orderItem.order_id}">新建施工项目</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_project_modal_${orderItem.order_id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <input type="file" name="txt_file" id="project_file" multiple class="file-loading" />
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <table class="table table-condensed table-hover">
                                                <thead>
                                                <tr>
                                                    <th>编号</th>
                                                    <th>施工名称</th>
                                                    <th>施工大类</th>
                                                    <th>当前状态</th>
                                                    <th>标记完成</th>
                                                    <th>施工截图</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="project_index" begin="0" end="${projects.size() - 1}" step="1">
                                                    <c:set var="project" scope="session" value="${projects.get(project_index)}"/>
                                                    <tr><td>00${project_index + 1}</td><td>${project.name}</td><td>${project.category}</td><td>${project.status}</td>
                                                        <td><button type="button" class="btn btn-block btn-outline-success">标记完成</button></td>
                                                        <td><button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#project_modal_${orderItem.order_id}_${project.id}">查看截图</button></td>
                                                        <div class="modal fade" id="project_modal_${orderItem.order_id}_${project.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <img src="<%=request.getContextPath() %>/resources/img/projects/${project.url}" alt="" style="width:100%;">
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
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

</body>
</html>
