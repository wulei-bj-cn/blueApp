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
            <h1>蓝房子后台管理中心</h1>
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
    <div id="accordion">
        <div class="card">
            <div class="card-header">
                <a class="card-link" data-toggle="collapse" href="#collapseOne">
                    订单: XXX3872024028
                </a>
            </div>
            <div id="collapseOne" class="collapse show" data-parent="#accordion">
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <ul class="nav nav-tabs nav-justified" role="tablist">
                            <li class="nav-item active">
                                <a class="nav-link active" data-toggle="tab" href="#panel1" role="tab">
                                    <h6>预约测量</h6>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#panel2" role="tab">
                                    <h6>设计合同</h6>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#panel3" role="tab">
                                    <h6>设计方案</h6>
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" data-toggle="tab" href="#panel4" role="tab">
                                    <h6>施工交底</h6>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#panel5" role="tab">
                                    <h6>施工合同</h6>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#panel6" role="tab">
                                    <h6>施工管理</h6>
                                </a>
                            </li>
                        </ul>
                        <br>
                        <div class="tab-content">
                            <div id="panel1" class="container tab-pane active"><br>
                                <div class="card-deck mb-1">
                                    <div class="card mb-12">
                                        <div class="card-header alert-info">
                                            <h4>西辛南区25号楼3-301室-如实测量</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <p><label>用户名:</label>吴磊</p>
                                                    <p><label>地址:</label>西辛南区25号楼3-301室</p>
                                                    <p><label>测量时间:</label>2018-10-24上午10点</p>
                                                    <p><label>测量人:</label>耿晓琪</p>
                                                    <button type="button" class="btn btn-lg btn-block btn-outline-info">更新</button>
                                                </div>
                                                <div class="col-md-8">
                                                    <img src="photos/入室测量1.jpeg" class="rounded" width="640" height="300" data-toggle="modal" data-target="#measure1">
                                                    <div class="modal fade" id="measure1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                </div>
                                                                <div class="modal-body">
                                                                    <img src="photos/入室测量1.jpeg" alt="" style="width:100%;">
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
                            </div>
                            <div id="panel2" class="container tab-pane fade"><br>
                                <div class="row">
                                    <ul class="nav flex-column nav-justified" role="tablist">
                                        <li class="nav-item active">
                                            <a class="nav-link active" href="#design_con001" data-toggle="tab" role="tab">设计合同</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#design_con002" data-toggle="tab" role="tab">补充合同1</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#design_con003" data-toggle="tab" role="tab">补充合同2</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="tab" role="tab">上传设计合同</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="design_con001" class="col-md-8 container tab-pane active">
                                            <div><h5>顺义胜利小区30号楼-201设计合同</h5></div>
                                            <img src="photos/设计合同1.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_con_pic001">
                                            <div class="modal fade" id="design_con_pic001" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计合同1.jpeg" alt="" style="width:100%;">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>
                                        <div id="design_con002" class="col-md-8 container tab-pane fade">
                                            <div><h5>顺义胜利小区30号楼-201补充合同1</h5></div>
                                            <img src="photos/设计合同-补充2.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_con_pic002">
                                            <div class="modal fade" id="design_con_pic002" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计合同-补充2.jpeg" alt="" style="width:100%;">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>
                                        <div id="design_con003" class="col-md-8 container tab-pane fade">
                                            <div><h5>顺义胜利小区30号楼-201补充合同2</h5></div>
                                            <img src="photos/施工交底2.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_con_pic003">
                                            <div class="modal fade" id="design_con_pic003" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/施工交底2.jpeg" alt="" style="width:100%;">
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
                            <div id="panel3" class="container tab-pane fade"><br>
                                <div class="row">
                                    <ul class="nav flex-column nav-justified" role="tablist">
                                        <li class="nav-item active">
                                            <a class="nav-link active" href="#design001" data-toggle="tab" role="tab">设计方案2</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#design002" data-toggle="tab" role="tab">设计方案1</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="tab" role="tab">上传设计方案</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="design001" class="col-md-8 container tab-pane active">
                                            <div><h5>顺义牛栏山幸福小区15号楼-3-202-第二版</h5></div>
                                            <img src="photos/设计方案1.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic001">
                                            <div class="modal fade" id="design_pic001" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计方案1.jpeg" alt="" style="width:100%;">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>
                                        <div id="design002" class="col-md-8 container tab-pane fade">
                                            <div><h5>顺义牛栏山幸福小区15号楼-3-202-第一版</h5></div>
                                            <img src="photos/设计方案2.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#design_pic002">
                                            <div class="modal fade" id="design_pic002" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计方案2.jpeg" alt="" style="width:100%;">
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
                            <div id="panel4" class="container tab-pane fade"><br>
                                <div class="card-deck mb-1">
                                    <div class="card mb-12">
                                        <div class="card-header alert-success">
                                            <h4>顺义鲁能7号院20号楼2-201施工交底</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <p><label>用户名:</label>吴磊</p>
                                                    <p><label>地址:</label>西辛南区25号楼3-301室</p>
                                                    <p><label>交底时间:</label>2018-11-12下午4点</p>
                                                    <p><label>交底人:</label>耿晓琪</p>
                                                    <button type="button" class="btn btn-lg btn-block btn-outline-success">更新</button>
                                                </div>
                                                <div class="col-md-8">
                                                    <img src="photos/施工交底1.jpeg" class="rounded" width="640" height="300" data-toggle="modal" data-target="#disclaimModal">
                                                    <div class="modal fade" id="disclaimModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                                </div>
                                                                <div class="modal-body">
                                                                    <img src="photos/施工交底1.jpeg" alt="" style="width:100%;">
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
                            </div>
                            <div id="panel5" class="container tab-pane fade"><br>
                                <div class="row">
                                    <ul class="nav flex-column nav-justified" role="tablist">
                                        <li class="nav-item active">
                                            <a class="nav-link active" href="#con001" data-toggle="tab" role="tab">施工合同</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#con002" data-toggle="tab" role="tab">补充合同1</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#con003" data-toggle="tab" role="tab">补充合同2</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#" data-toggle="tab" role="tab">上传施工合同</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="con001" class="col-md-8 container tab-pane active">
                                            <div><h5>顺义鲁能7号院20号楼2-201施工合同</h5></div>
                                            <img src="photos/设计合同1.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#con_pic001">
                                            <div class="modal fade" id="con_pic001" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计合同1.jpeg" alt="" style="width:100%;">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>
                                        <div id="con002" class="col-md-8 container tab-pane fade">
                                            <div><h5>顺义鲁能7号院20号楼2-201补充合同1</h5></div>
                                            <img src="photos/设计合同-补充2.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#con_pic002">
                                            <div class="modal fade" id="con_pic002" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/设计合同-补充2.jpeg" alt="" style="width:100%;">
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div>
                                        </div>
                                        <div id="con003" class="col-md-8 container tab-pane fade">
                                            <div><h5>顺义鲁能7号院20号楼2-201补充合同2</h5></div>
                                            <img src="photos/施工交底2.jpeg" class="rounded" width="670" height="295" data-toggle="modal" data-target="#con_pic003">
                                            <div class="modal fade" id="con_pic003" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                        </div>
                                                        <div class="modal-body">
                                                            <img src="photos/施工交底2.jpeg" alt="" style="width:100%;">
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
                            <div id="panel6" class="container tab-pane fade"><br>
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
                                    <tr><td>001</td><td>吊顶</td><td>瓦工</td><td>进行中</td>
                                        <td><button type="button" class="btn btn-block btn-outline-success">标记完成</button></td>
                                        <td><button type="button" class="btn btn-block btn-outline-primary" data-toggle="modal" data-target="#modal001">上传截图</button></td>
                                        <form>
                                            <div class="modal fade" id="modal001" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                                <div class="modal-dialog modal-lg" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">确定上传</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </tr>
                                    <tr><td>002</td><td>改水</td><td>水电</td><td>进行中</td>
                                        <td><button type="button" class="btn btn-block btn-outline-success">标记完成</button></td>
                                        <td><button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#modal002">查看截图</button></td>
                                        <div class="modal fade" id="modal002" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                    </div>
                                                    <div class="modal-body">
                                                        <img src="photos/施工交底1.jpeg" alt="" style="width:100%;">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div>
                                    </tr>
                                    <tr><td>003</td><td>改电</td><td>水电</td><td>进行中</td>
                                        <td><button type="button" class="btn btn-block btn-outline-success">标记完成</button></td>
                                        <td><button type="button" class="btn btn-block btn-outline-info" data-toggle="modal" data-target="#modal003">查看截图</button></td>
                                        <div class="modal fade" id="modal003" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                                                    </div>
                                                    <div class="modal-body">
                                                        <img src="photos/施工交底2.jpeg" alt="" style="width:100%;">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->
                                        </div>
                                    </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </blockquote>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
