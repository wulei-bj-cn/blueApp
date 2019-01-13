<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.blueHouse.pojo.*" %>
<%@ page import="com.blueHouse.pojo.orders.*" %>
<%@ page import="java.util.Properties" %>
<%@ page import="org.springframework.core.io.support.PropertiesLoaderUtils" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
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

<section class="jumbotron alert-light">
    <div class="row">
        <div class="col-md-3">
            <img src="/img/common/logo.jpeg" class="rounded" width="320" height="150">
        </div>
        <div class="container col-md-9">
            <header>
                <h1><a href="/console/getAll">蓝房子后台管理中心</a></h1>
                <p class="lead">订单管理</p>
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
    <div class="navbar col-md-12 bg-dark">
        <form class="form-inline my-2 my-lg-0 float-right" action="/order/searchOrders" method="get">
            <input class="form-control mr-sm-2" type="text" placeholder="订单号/用户名" id="searchKey" name="searchKey" aria-label="Search">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">搜索</button>
        </form>
        <label class="btn-outline-light">如果搜索条件为空，默认将列出所有订单！</label>
    </div>
    <br>
    <c:if test="${isSearching == false}">
        <div class="alert alert-success">
            <strong>老板您好!</strong> 我们蓝房子目前总共有<%=request.getAttribute("ordersCount")%>个订单，您可以通过搜索框对某个订单/用户进行搜索！如果搜索条件为空，那么系统将为您列出所有订单！
        </div>
    </c:if>
    <c:if test="${isSearching == true}">
        <div class="alert alert-success">
            <c:choose>
                <c:when test="${searchKey == 'users.jsp'}">
                    <strong>老板您好!</strong> 您搜索的相关用户的订单如下；如果需要获取所有订单，您可以通过将搜索框置空，并点击"搜索"！
                </c:when>
                <c:otherwise>
                    <strong>老板您好!</strong> 根据关键词"${searchKey}"，搜索到<%=request.getAttribute("ordersCount")%>个相关订单，您可以通过将搜索框置空，并点击"搜索"重新获取所有订单！
                </c:otherwise>
            </c:choose>
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

                Contract firstDesignContract = (designContracts == null || designContracts.size() == 0)? null:
                        designContracts
                        .stream()
                        .filter((Contract contract) -> contract.getType().equals("设计合同"))
                        .collect(Collectors.toList()).get(0);

                List<Design> designs = (orderItem.getDesigns().size() == 0)? null: orderItem.getDesigns();

                List<Contract> projectContracts = (contracts.size() == 0)? null :
                        contracts
                        .stream()
                        .filter((Contract contract) -> contract.getType().startsWith("施工"))
                        .collect(Collectors.toList());

                Contract firstProjectContract = (projectContracts == null || projectContracts.size() == 0)? null:
                        projectContracts
                                .stream()
                                .filter((Contract contract) -> contract.getType().equals("施工合同"))
                                .collect(Collectors.toList()).get(0);

                Disclaim disclaim = (orderItem.getDisclaim().size() == 0)? null : orderItem.getDisclaim().get(0);

                List<Project> projects = (orderItem.getProjects().size() == 0)? null: orderItem.getProjects();

                User pojoUser = orderItem.getUser();
                Order order = orderItem.getOrder();
                Map<String,String> orderStatusMap = new HashMap<>();
                orderStatusMap.put("0", "预约成功，待分配测量师");
                orderStatusMap.put("1", "完成测量");
                orderStatusMap.put("2", "确认设计合同");
                orderStatusMap.put("3", "已支付定金");
                orderStatusMap.put("4", "完成设计方案上传");
                orderStatusMap.put("5", "用户已确认设计方案");
                orderStatusMap.put("6", "确认施工合同");
                orderStatusMap.put("7", "施工交底完成上传");
                orderStatusMap.put("8", "施工中");
                orderStatusMap.put("9", "施工完成，待双方确认");
                orderStatusMap.put("10", "订单已完成");

                pageContext.setAttribute("measure", measure);
                pageContext.setAttribute("designContracts", designContracts);
                pageContext.setAttribute("designs", designs);
                pageContext.setAttribute("projectContracts", projectContracts);
                pageContext.setAttribute("disclaim", disclaim);
                pageContext.setAttribute("projects", projects);
                pageContext.setAttribute("pojoUser", pojoUser);
                pageContext.setAttribute("order", order);
                pageContext.setAttribute("orderStatusMap", orderStatusMap);
                pageContext.setAttribute("firstDesignContract", firstDesignContract);
                pageContext.setAttribute("firstProjectContract", firstProjectContract);
            %>
            <div class="card">
                <div class="card-header">
                    <a class="card-link collapsed" data-toggle="collapse" href="#Collapse_${orderItem.order}_${orderItem.user}">
                        用户: ${pojoUser.name} (下单时间: ${order.start_time} / 订单状态: ${orderStatusMap.get(order.status)})
                    </a>
                </div>
                <div id="Collapse_${orderItem.order}_${orderItem.user}" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <blockquote class="blockquote mb-0">
                            <ul class="nav nav-tabs nav-justified" role="tablist">
                                <li class="nav-item active">
                                    <a class="nav-link active" data-toggle="tab" href="#panel1_${orderItem.order}" role="tab">
                                        <h6 class="text-primary">预约测量</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel2_${orderItem.order}" role="tab">
                                        <h6 class="text-danger">设计合同</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel3a_${orderItem.order}" role="tab">
                                        <h6 class="text-success">设计定金</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel3_${orderItem.order}" role="tab">
                                        <h6 class="text-info">设计方案</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel5_${orderItem.order}" role="tab">
                                        <h6 class="text-success">施工合同</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel5a_${orderItem.order}" role="tab">
                                        <h6 class="text-success">施工定金</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel4_${orderItem.order}" role="tab">
                                        <h6 class="text-secondary">施工交底</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel6_${orderItem.order}" role="tab">
                                        <h6 class="text-warning">施工项目</h6>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#panel7_${orderItem.order}" role="tab">
                                        <h6 class="text-success">完成订单</h6>
                                    </a>
                                </li>
                            </ul>
                            <br>
                            <div class="tab-content">
                                <div id="panel1_${orderItem.order}" class="container tab-pane active"><br>
                                    <c:choose>
                                        <c:when test="${measure == null || measure.url == null}">
                                            <p>目前还没有对预约测量进行管理，点击<span class="badge badge-primary">新建预约测量</span>添加测量记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#new_measure_modal_${orderItem.order}">新建预约测量</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/uploadMeasure" method="post" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_measure_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-primary float-left">新建预约测量</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="measure_name">名称</label>
                                                                    &nbsp;
                                                                    <input type="text" class="form-control" id="measure_name" name="measure_name" placeholder="请输入名称">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="measure_address">地址</label>
                                                                    &nbsp;
                                                                    <input type="text" class="form-control" id="measure_address" name="measure_address" placeholder="请输入地址">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="measure_file">上传测量截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="measure_file" id="measure_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="measure_order_id" name="measure_order_id" value="${orderItem.order}" hidden="true"/>
                                                                    <input type="text" id="measure_user_id" name="measure_user_id" value="${orderItem.user}" hidden="true"/>
                                                                    <input type="text" id="measure_id" name="measure_id" value="${measure.id}" hidden="true"/>
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
                                                                <img src="/img/measures/${measure.url}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#measure_${orderItem.order}">
                                                                <div class="modal fade" id="measure_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="/img/measures/${measure.url}" alt="" style="width:100%;">
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
                                <div id="panel2_${orderItem.order}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${firstDesignContract == null || firstDesignContract.url == null}">
                                            <p>目前还没有对设计合同进行管理，点击<span class="badge badge-danger">新建设计合同</span>添加合同记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-danger" data-toggle="modal" data-target="#new_design_contr_modal_${orderItem.order}">新建设计合同</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/uploadContract" method="post" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_design_contr_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-danger float-left">新建设计合同</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="contract_name">名称</label>
                                                                    <input type="text" class="form-control" id="contract_name" name="contract_name" placeholder="请输入名称">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label>合同类型</label>
                                                                    <br>
                                                                    <label class="radio-inline">
                                                                        <input type="radio"  value="设计合同" name="contract_type" id="contract_type_option1" checked>设计合同
                                                                    </label>
                                                                    <br>
                                                                    <label class="radio-inline">
                                                                        <input type="radio"  value="设计合同-补充" name="contract_type" id="contract_type_option2">设计合同-补充
                                                                    </label>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contract_file">上传设计合同截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="contract_file" id="contract_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="contract_order_id" name="contract_order_id" value="${orderItem.order.id}" hidden="true"/>
                                                                    <input type="text" id="contract_user_id" name="contract_user_id" value="${orderItem.user.id}" hidden="true"/>
                                                                    <input type="text" id="contract_id" name="contract_id" value="${firstDesignContract.id}" hidden="true"/>
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
                                            <div class="row">
                                                <ul class="nav flex-column nav-justified" role="tablist">
                                                    <c:forEach var="designContract" items="${designContracts}">
                                                        <c:choose>
                                                            <c:when test="${designContract.type == '设计合同'}">
                                                                <li class="nav-item active">
                                                                    <a class="nav-link active" href="#design_contr_${orderItem.order}_${designContract.id}" data-toggle="tab" role="tab">设计合同</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="nav-item">
                                                                    <a class="nav-link" href="#design_contr_${orderItem.order}_${designContract.id}" data-toggle="tab" role="tab">补充合同</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach var="designContract" items="${designContracts}">
                                                        <c:set var="design_contr_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${designContract.type == '设计合同'}">
                                                            <c:set var="design_contr_tab_state" scope="session" value="active"/>
                                                        </c:if>

                                                        <div id="design_contr_${orderItem.order}_${designContract.id}" class="col-md-8 container tab-pane ${design_contr_tab_state}">
                                                            <c:if test="${designContract.url != null}">
                                                                <div><h5>${designContract.name}</h5></div>
                                                                <img src="/img/contracts/${designContract.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_con_${orderItem.order}_${designContract.id}">
                                                                <div class="modal fade" id="design_con_${orderItem.order}_${designContract.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="/img/contracts/${designContract.url}" alt="" style="width:100%;">
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${designContract.url == null}">
                                                                <form class="form-inline mt-2 mt-md-0" action="/order/uploadContract" method="post" enctype="multipart/form-data">
                                                                    <div class="modal-dialog modal-lg" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <span class="badge badge-danger float-left">新建设计合同</span>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="form-group">
                                                                                    <label for="contract_name">名称</label>
                                                                                    <input type="text" class="form-control" id="contract_name" name="contract_name" placeholder="请输入名称">
                                                                                </div>
                                                                                <br>
                                                                                <div class="form-group">
                                                                                    <label>合同类型</label>
                                                                                    <br>
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio"  value="设计合同" name="contract_type" id="contract_type_option1">设计合同
                                                                                    </label>
                                                                                    <br>
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio"  value="设计合同-补充" name="contract_type" id="contract_type_option2" checked>设计合同-补充
                                                                                    </label>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="contract_file">上传设计合同截图</label>
                                                                                    &nbsp;
                                                                                    <input type="file" name="contract_file" id="contract_file" />
                                                                                    &nbsp;
                                                                                    <input type="text" id="contract_order_id" name="contract_order_id" value="${orderItem.order.id}" hidden="true"/>
                                                                                    <input type="text" id="contract_user_id" name="contract_user_id" value="${orderItem.user.id}" hidden="true"/>
                                                                                    <input type="text" id="contract_id" name="contract_id" value="${designContract.id}" hidden="true"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="submit" class="btn btn-outline-danger">确定</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </c:if>
                                                        </div>

                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel3a_${orderItem.order}" class="container tab-pane fade"><br>
                                    <form class="form-inline mt-2 mt-md-0" action="/cash/confirmCash" method="get">
                                        <c:if test="${order.status >= '21'}">
                                            <p><span class="badge badge-success">已确认收到设计方案定金</span></p>
                                            <p><span class="badge badge-warning">如有疑义，请尽快联系超级管理员！</span></p>
                                        </c:if>
                                        <c:if test="${order.status < '21'}">
                                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">确认收到设计方案定金</button>
                                            <input type="text" id="cash_order_id" name="cash_order_id" value="${order.id}" hidden="true"/>
                                            <input type="text" id="cash_user_id" name="cash_user_id" value="${orderItem.user.id}" hidden="true"/>
                                            <input type="text" id="cash_type" name="cash_type" value="DesignCash" hidden="true"/>
                                        </c:if>
                                    </form>
                                </div>
                                <div id="panel3_${orderItem.order}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${designs == null}">
                                            <p>目前还没有对设计方案进行管理，点击<span class="badge badge-info">新建设计方案</span>添加设计方案记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#new_design_modal_${orderItem.order}">新建设计方案</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/insertDesign" method="post" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_design_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-info float-left">新建设计方案</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="design_name">名称</label>
                                                                    &nbsp;
                                                                    <input type="text" class="form-control" id="design_name" name="design_name" placeholder="请输入名称">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="designer">设计师</label>
                                                                    &nbsp;
                                                                    <select class="form-control select" id="designer" name="designer" placeholder="请选择设计师姓名">
                                                                        <option value="adm1">小旗</option>
                                                                        <option value="adm2">秦阳</option>
                                                                        <option value="adm3">Kevin老师</option>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="designer">设计明细</label>
                                                                    &nbsp;
                                                                    <textarea name="design_details" id="design_details" cols="30" rows="10" class="form-control" placeholder="设计方案明细"></textarea>
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="design_file">上传设计方案截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="design_file" id="design_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="design_order_id" name="design_order_id" value="${orderItem.order.id}" hidden="true"/>

                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-outline-info">确定</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="container row">
                                                <p>点击<span class="badge badge-info">添加补充方案</span>添加设计方案记录。</p>
                                                <br>
                                                <div class="col-md-3">
                                                    <button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#new_design_modal_${orderItem.order}">添加补充方案</button>
                                                </div>
                                                <form class="form-inline mt-2 mt-md-0" action="/order/insertDesign" method="post" enctype="multipart/form-data">
                                                    <div class="modal fade" id="new_design_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                        <div class="modal-dialog modal-lg" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <span class="badge badge-info float-left">添加补充方案</span>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label for="design_name">名称</label>
                                                                        &nbsp;
                                                                        <input type="text" class="form-control" id="design_name" name="design_name" placeholder="请输入名称">
                                                                    </div>
                                                                    <br>
                                                                    <div class="form-group">
                                                                        <label for="designer">设计师</label>
                                                                        &nbsp;
                                                                        <select class="form-control select" id="designer" name="designer" placeholder="请选择设计师姓名">
                                                                            <option value="adm1">小旗</option>
                                                                            <option value="adm2">秦阳</option>
                                                                            <option value="adm3">Kevin老师</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="designer">设计明细</label>
                                                                        &nbsp;
                                                                        <textarea name="design_details" id="design_details" cols="30" rows="10" class="form-control" placeholder="设计方案明细"></textarea>
                                                                    </div>
                                                                    <br>
                                                                    <div class="form-group">
                                                                        <label for="design_file">上传设计方案截图</label>
                                                                        &nbsp;
                                                                        <input type="file" name="design_file" id="design_file" />
                                                                        &nbsp;
                                                                        <input type="text" id="design_order_id" name="design_order_id" value="${orderItem.order.id}" hidden="true"/>

                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="submit" class="btn btn-outline-info">确定</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="card-deck mb-1">
                                                <div class="card mb-12">
                                                    <c:forEach var="design_index" begin="0" end="${designs.size() - 1}" step="1">
                                                        <c:set var="design" scope="session" value="${designs.get(design_index)}"/>
                                                        <c:set var="design_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${design_index == 0}">
                                                            <c:set var="design_tab_state" scope="session" value="active"/>
                                                        </c:if>
                                                        <!-- <div id="design_${orderItem.order}_${design.id}" class="col-md-8 container tab-pane ${design_tab_state}">-->
                                                        <div class="card-header alert-info">
                                                            <h4>${design.name}</h4>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="row">
                                                                <div class="col-md-4">
                                                                    <p><label>设计师:</label> ${design.designer}</p>
                                                                    <p><label>出版时间</label>: ${design.ts}</p>
                                                                    <p><label>设计状态</label>: ${design.status}</p>
                                                                    <c:if test="${design.details == null}">
                                                                        <p><label>设计明细</label>:明细为空</p>
                                                                    </c:if>
                                                                    <c:if test="${design.details != null}">
                                                                        <p><label>设计明细</label>:${design.details}</p>
                                                                    </c:if>
                                                                    <button type="button" class="btn btn-lg btn-block btn-outline-info">更新</button>
                                                                </div>
                                                                <div class="col-md-8">
                                                                <img src="/img/designs/${design.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic_${orderItem.order}_${design.id}">
                                                                <div class="modal fade" id="design_pic_${orderItem.order}_${design.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="/img/designs/${design.url}" alt="" style="width:100%;">
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
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel5_${orderItem.order}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${firstProjectContract == null || firstProjectContract.url == null}">
                                            <p>目前还没有对施工合同进行管理，点击<span class="badge badge-success">新建施工合同</span>添加合同记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-success" data-toggle="modal" data-target="#new_project_contr_modal_${orderItem.order}">新建施工合同</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/uploadContract" method="post" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_project_contr_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-success float-left">新建施工合同</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="contract_name">名称</label>
                                                                    <input type="text" class="form-control" id="contract_name" name="contract_name" placeholder="请输入名称">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label>合同类型</label>
                                                                    <br>
                                                                    <label class="radio-inline">
                                                                        <input type="radio"  value="施工合同" name="contract_type" id="contract_type_option1" checked>施工合同
                                                                    </label>
                                                                    <br>
                                                                    <label class="radio-inline">
                                                                        <input type="radio"  value="施工合同-补充" name="contract_type" id="contract_type_option2">施工合同-补充
                                                                    </label>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="contract_file">上传施工合同截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="contract_file" id="contract_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="contract_order_id" name="contract_order_id" value="${orderItem.order.id}" hidden="true"/>
                                                                    <input type="text" id="contract_user_id" name="contract_user_id" value="${orderItem.user.id}" hidden="true"/>
                                                                    <input type="text" id="contract_id" name="contract_id" value="${firstProjectContract.id}" hidden="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-outline-success">确定</button>
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
                                                                    <a class="nav-link active" href="#project_contr_${orderItem.order}_${projectContract.id}" data-toggle="tab" role="tab">施工合同</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="nav-item">
                                                                    <a class="nav-link" href="#project_contr_${orderItem.order}_${projectContract.id}" data-toggle="tab" role="tab">补充合同</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach var="projectContract" items="${projectContracts}">
                                                        <c:set var="project_contr_tab_state" scope="session" value="fade"/>
                                                        <c:if test="${projectContract.type == '施工合同'}">
                                                            <c:set var="project_contr_tab_state" scope="session" value="active"/>
                                                        </c:if>

                                                        <div id="project_contr_${orderItem.order}_${projectContract.id}" class="col-md-8 container tab-pane ${project_contr_tab_state}">
                                                            <c:if test="${projectContract.url != null}">
                                                                <div><h5>${projectContract.name}</h5></div>
                                                                <img src="/img/contracts/${projectContract.url}" class="rounded" width="670" height="295" data-toggle="modal" data-target="#project_con_${orderItem.order}_${projectContract.id}">
                                                                <div class="modal fade" id="project_con_${orderItem.order}_${projectContract.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="/img/contracts/${projectContract.url}" alt="" style="width:100%;">
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${projectContract.url == null}">
                                                                <form class="form-inline mt-2 mt-md-0" action="/order/uploadContract" method="post" enctype="multipart/form-data">
                                                                    <div class="modal-dialog modal-lg" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <span class="badge badge-success float-left">新建施工合同</span>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="form-group">
                                                                                    <label for="contract_name">名称</label>
                                                                                    <input type="text" class="form-control" id="contract_name" name="contract_name" placeholder="请输入名称">
                                                                                </div>
                                                                                <br>
                                                                                <div class="form-group">
                                                                                    <label>合同类型</label>
                                                                                    <br>
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio"  value="施工合同" name="contract_type" id="contract_type_option1">施工合同
                                                                                    </label>
                                                                                    <br>
                                                                                    <label class="radio-inline">
                                                                                        <input type="radio"  value="施工合同-补充" name="contract_type" id="contract_type_option2" checked>施工合同-补充
                                                                                    </label>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label for="contract_file">上传施工合同截图</label>
                                                                                    &nbsp;
                                                                                    <input type="file" name="contract_file" id="contract_file" />
                                                                                    &nbsp;
                                                                                    <input type="text" id="contract_order_id" name="contract_order_id" value="${orderItem.order.id}" hidden="true"/>
                                                                                    <input type="text" id="contract_user_id" name="contract_user_id" value="${orderItem.user.id}" hidden="true"/>
                                                                                    <input type="text" id="contract_id" name="contract_id" value="${projectContract.id}" hidden="true"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="submit" class="btn btn-outline-success">确定</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </c:if>
                                                        </div>

                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div id="panel5a_${orderItem.order}" class="container tab-pane fade"><br>
                                    <form class="form-inline mt-2 mt-md-0" action="/cash/confirmCash" method="get">
                                        <c:if test="${order.status >= '51'}">
                                            <p><span class="badge badge-success">已确认收到施工方案定金</span></p>
                                            <p><span class="badge badge-warning">如有疑义，请尽快联系超级管理员！</span></p>
                                        </c:if>
                                        <c:if test="${order.status < '51'}">
                                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">确认收到施工方案定金</button>
                                            <input type="text" id="cash_order_id" name="cash_order_id" value="${order.id}" hidden="true"/>
                                            <input type="text" id="cash_user_id" name="cash_user_id" value="${orderItem.user.id}" hidden="true"/>
                                            <input type="text" id="cash_type" name="cash_type" value="ProjectCash" hidden="true"/>
                                        </c:if>
                                    </form>
                                </div>
                                <div id="panel4_${orderItem.order}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${disclaim == null || disclaim.url == null}">
                                            <p>目前还没有对施工交底进行管理，点击<span class="badge badge-secondary">新建施工交底</span>添加交底记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-secondary" data-toggle="modal" data-target="#new_disclaim_modal_${orderItem.order}">新建施工交底</button>
                                            </div>
                                            <form class="form-inline mt-2 mt-md-0" action="/order/uploadDisclaim" method="post" enctype="multipart/form-data">
                                                <div class="modal fade" id="new_disclaim_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-secondary float-left">新建施工交底</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="disclaim_name">名称</label>
                                                                    &nbsp;
                                                                    <input type="text" class="form-control" id="disclaim_name" placeholder="请输入名称">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="measure_file">上传施工交底截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="disclaim_file" id="disclaim_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="disclaim_order_id" name="disclaim_order_id" value="${orderItem.order.id}" hidden="true"/>
                                                                    <input type="text" id="disclaim_user_id" name="disclaim_user_id" value="${orderItem.user.id}" hidden="true"/>
                                                                    <input type="text" id="disclaim_id" name="disclaim_id" value="${disclaim.id}" hidden="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" class="btn btn-outline-secondary">确定</button>
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
                                                                <img src="/img/disclaims/${disclaim.url}" class="rounded" width="640" height="300" data-toggle="modal" data-target="#disclaim_${orderItem.order}">
                                                                <div class="modal fade" id="disclaim_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <img src="/img/disclaims/${disclaim.url}" alt="" style="width:100%;">
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
                                <div id="panel6_${orderItem.order}" class="container tab-pane fade"><br>
                                    <c:choose>
                                        <c:when test="${projects == null}">
                                            <p>目前还没有对施工项目进行管理，点击<span class="badge badge-warning">新建施工项目</span>添加项目记录。</p>
                                            <br>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-block btn-outline-warning" data-toggle="modal" data-target="#new_project_modal_${orderItem.order}">新建施工项目</button>
                                            </div>
                                            <form>
                                                <div class="modal fade" id="new_project_modal_${orderItem.order}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <span class="badge badge-warning float-left">新建施工项目</span>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="project_category">请选择施工大类</label>
                                                                    <select id="project_category" class="custom-select" title="请选择施工大类">
                                                                        <option value="瓦工">瓦工</option>
                                                                        <option value="水电">水电</option>
                                                                        <option value="泥工">泥工</option>
                                                                    </select>
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="project_name">施工项目</label>
                                                                    <input type="text" class="form-control" id="project_name" placeholder="吊顶/改水/改电...">
                                                                </div>
                                                                <br>
                                                                <div class="form-group">
                                                                    <label for="project_file">上传施工项目截图</label>
                                                                    &nbsp;
                                                                    <input type="file" name="project_file" id="project_file" />
                                                                    &nbsp;
                                                                    <input type="text" id="project_order_id" name="project_order_id" value="${orderItem.order}" hidden="true"/>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-outline-warning" data-dismiss="modal">确定</button>
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
                                                        <td><button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#project_modal_${orderItem.order}_${project.id}">查看截图</button></td>
                                                        <div class="modal fade" id="project_modal_${orderItem.order}_${project.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                <div id="panel7_${orderItem.order}" class="container tab-pane fade"><br>
                                    <form class="form-inline mt-2 mt-md-0" action="/cash/finishOrder" method="get">
                                        <c:if test="${order.status == '81'}">
                                            <p><span class="badge badge-success">已确认收到尾款</span></p>
                                            <p><span class="badge badge-warning">如有疑义，请尽快联系超级管理员！</span></p>
                                        </c:if>
                                        <c:if test="${order.status == '82'}">
                                            <p><span class="badge badge-success">用户已确认所有环节结束，订单完成！</span></p>
                                        </c:if>
                                        <c:if test="${order.status < '81'}">
                                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">确认收到尾款</button>
                                            <input type="text" id="cash_order_id" name="cash_order_id" value="${order.id}" hidden="true"/>
                                            <input type="text" id="cash_user_id" name="cash_user_id" value="${orderItem.user.id}" hidden="true"/>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </blockquote>
                    </div>
                </div>
            </div>
        </c:forEach>
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
