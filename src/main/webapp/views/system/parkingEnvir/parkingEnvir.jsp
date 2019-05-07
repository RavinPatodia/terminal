﻿<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html>

<%@ include file="/views/include/taglib.jsp"%>

<html lang="utf-8">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>

<jsp:include page="/views/include/globalCss.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="<%=request.getContextPath()%>/assets/global/plugins/jcrop/css/jquery.Jcrop.min.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/assets/admin/pages/css/image-crop.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/jquery-multi-select/css/multi-select.css"/> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/icheck/skins/all.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.css"></link>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/master/dist/css/bootstrapValidator.min.css"/><!-- 表单验证的样式 -->
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/clockface/css/clockface.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css"/>
 <!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="<%=request.getContextPath()%>/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=request.getContextPath()%>/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!--引入自己定义的一部分css-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/diy_style.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/progress.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
	<jsp:include page="/views/layout/topBar.jsp" />
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<jsp:include page="/views/layout/menu.jsp" />
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->
				
				<!-- END PAGE HEADER-->
				<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm" enctype="multipart/form-data">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>停车场环境变量
										</div>
									</div>
									<div class="portlet-body">
										<div class="row">
											<div class="col-md-2 col-sm-2 col-xs-2">
												<ul class="nav nav-tabs tabs-left">
													<li class="active">
														<a href="#tab_1_1" data-toggle="tab">网域参数配置</a>
													</li>
													<li>
														<a href="#tab_1_2" data-toggle="tab">系统参数配置</a>
													</li>
													<li>
														<a href="#tab_1_3" data-toggle="tab">本地参数配置</a>
													</li>
													<li>
														<a href="#tab_1_4" data-toggle="tab">数据备份还原</a>
													</li>
													<li>
														<a href="#tab_1_5" data-toggle="tab">短信猫配置</a>
													</li>
													<li>
														<a href="#tab_1_6" data-toggle="tab">客户组配置</a>
													</li>
												</ul>
											</div>
											<div class="col-md-10 col-sm-10 col-xs-10">
												<div class="tab-content">
												    <div class="tab-pane active" id="tab_1_1">
														<table class="table table-bordered table-hover" id='ruleRecs'>
															<thead>
																<tr>
																	<th>网域名称</th>
																	<th>平台地址</th>
																	<th>平台端口</th>
																	<th>数据库IP</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														
													</div>
													<div class="tab-pane fade" id="tab_1_2">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">数据保存时间</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">正常过车数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="normalAdmissionDataKeep" fill="obj.normalAdmissionDataKeep" readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
															    </div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">违章过车数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="violationsAdmissionDataKeep" fill="obj.violationsAdmissionDataKeep" readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">设备状态数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="deviceStateDataKeep" fill="obj.deviceStateDataKeep" readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">场内同步数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="syncDataKeep" fill="obj.syncDataKeep" readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">收费明细数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="chargeRecDataKeep" fill="obj.chargeRecDataKeep" readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">日志保存时间</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-5 control-label">操作日志数据</label>
																	<div class="col-md-5">
																		<div class="monthSpinner" style="width:150px; height:28px;">
																			<div class="input-group">
																				<input type="text" value="1" class="spinner-input form-control" name="logDataKeep" fill="obj.logDataKeep"readonly>
																				<div class="spinner-buttons input-group-btn">
																					<button type="button" class="btn spinner-up default">
																					<i class="fa fa-angle-up"></i>
																					</button>
																					<button type="button" class="btn spinner-down default">
																					<i class="fa fa-angle-down"></i>
																					</button>
																				</div>
																			</div>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">个月</label>
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default control_label_middle">
															<div class="panel-heading">
																<h3 class="panel-title">系统参数</h3>
															</div>
															<div class="panel-body">
																<div class="form-body">
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">停车场编号</label>
																			<div class="col-md-6">
																				<input type="text" class="form-control input-sm" readonly name="parkingId" fill="obj.parkingId">
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">总车位数</label>
																			<div class="col-md-6">
																				<input type="text" class="form-control input-sm" name="spNumber" fill="obj.spNumber">
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">当前剩余车位数</label>
																			<div class="col-md-6">
																				<input type="text" class="form-control input-sm" name="nowSpNumber" fill="obj.nowSpNumber">
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">识别模式</label>
																			<div class="col-md-6">
																				<select class="bs-select form-control input-sm" name="pattern" id="" >
																					<option value="0">卡识别</option>
																					<option value="1">车牌识别</option>
																					<option value="2">双重识别</option>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">用户到期时间提醒(天)</label>
																			<div class="col-md-6">
																				<input type="text" class="form-control input-sm" name="uexpirReminder" fill="obj.uexpirReminder">
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">车位租赁到期时间提醒(天)</label>
																			<div class="col-md-6">
																				<input type="text" class="form-control input-sm" name="pspExpirReminder" fill="obj.pspExpirReminder">
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">使用车位识别摄像机</label>
																			<div class="col-md-6">
																				<input name="plateRecoCameraFlagStr" fill="obj.plateRecoCameraFlag" type="checkbox"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">是否收费</label>
																			<div class="col-md-6">
																				<input name="chargeFlagStr" fill="obj.chargeFlag" type="checkbox"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">使用射频识别</label>
																			<div class="col-md-6">
																				<input name="useRfidFlagStr" fill="obj.useRfidFlag" type="checkbox"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">是否显示视频</label>
																			<div class="col-md-6">
																				<input name="videoShowFlagStr" fill="obj.videoShowFlag" type="checkbox" class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">是否允许换车</label>
																			<div class="col-md-6">
																				<input name="changeCarFlagStr" fill="obj.changeCarFlag" type="checkbox" class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">使用车位锁</label>
																			<div class="col-md-6">
																				<input name="pspLockFlagStr" type="checkbox" fill="obj.pspLockFlag"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">固定车位用户进出场计算车位</label>
																			<div class="col-md-6">
																				<input name="countSpFlagStr" type="checkbox" fill="obj.countSpFlag"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">储蓄用户额度提醒</label>
																			<div class="col-md-6">
																				<div class="input-inline" style="width:150px;">
																					<input name="minBalance" fill="obj.minBalance" type="text" value="1.00" class="form-control touchspin input-sm">
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">最小缴费金额</label>
																			<div class="col-md-6">
																				<div class="input-inline" style="width:150px;">
																					<input  type="text" value="1.00" name="minPay" fill="obj.minPay" class="form-control touchspin input-sm" >
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div class="form-group">
																			<label class="col-md-6 control-label">换车时间（分）</label>
																			<div class="col-md-6">
																				<div class="monthSpinner" style="width:150px; height:28px;">
																					<div class="input-group">
																						<input name="changeCarTime" fill="obj.changeCarTime" type="text" value="1" class="spinner-input form-control input-sm" readonly>
																						<div class="spinner-buttons input-group-btn">
																							<button type="button" class="btn btn-sm spinner-up default">
																							<i class="fa fa-angle-up"></i>
																							</button>
																							<button type="button" class="btn btn-sm spinner-down default">
																							<i class="fa fa-angle-down"></i>
																							</button>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
															</div>
								                        </div>
													</div>
													<div class="tab-pane fade control_label_middle" id="tab_1_3">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">抓图</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">保存格式</label>
																	<div class="col-md-8">
																		<select class="bs-select form-control input-sm" id="" >
																			<option value="0">JPEG格式</option>
																			<option value="1">GIF格式</option>
																			<option value="3">PNG格式</option>
																			<option value="4">BMP格式</option>
																			<option value="5">JPG格式</option>
																		</select>
																	</div>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">保存路径</label>
																	<div class="col-md-5">
																		<input class="form-control normal-input input-sm" name="" onclick="BrowseFolder(this)" placeholder="点击选择保存路径">
																	</div>
																	<!-- <button type="button" class="btn btn-sm blue" onclick="BrowseFolder()" >选择</button> -->
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">录像</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">文件打包大小</label>
																	<div class="col-md-8">
																		<select class="bs-select form-control input-sm" id="" >
																			<option value="0">124M</option>
																			<option value="1"></option>
																			<option value="3"></option>
																			<option value="4"></option>
																			<option value="5"></option>
																		</select>
																	</div>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">保存路径</label>
																	<div class="col-md-5">
																		<input class="form-control normal-input input-sm" name="" onclick="BrowseFolder(this)" placeholder="点击选择保存路径">
																	</div>
																	<!-- <button type="button" class="btn btn-sm blue" onclick="BrowseFolder()" >选择</button> -->
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">录像回放剪辑</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">文件打包大小</label>
																	<div class="col-md-8">
																		<select class="bs-select form-control input-sm" id="" >
																			<option value="0">124M</option>
																			<option value="1"></option>
																			<option value="3"></option>
																			<option value="4"></option>
																			<option value="5"></option>
																		</select>
																	</div>
																</div>
																<div class="col-md-6" style="margin: 5px 0px;">
																	<label class="col-md-4 control-label">保存路径</label>
																	<div class="col-md-5">
																		<input class="form-control normal-input input-sm" name="" onclick="BrowseFolder(this)" placeholder="点击选择保存路径">
																	</div>
																</div>
															</div>
								                        </div>
													</div>
													<div class="tab-pane fade control_label_middle" id="tab_1_4">
														<div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">数据备份路径</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-12" style="margin: 5px 0px;">
																	<label class="col-md-2 control-label" style="padding-left: 0px;">文件保存路径</label>
																	<div class="col-md-5">
																		<input class="form-control normal-input input-sm" name="backupPath" onclick="BrowseFolder(this)" placeholder="点击选择保存路径">
																	</div>
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">数据手动备份</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-12" style="margin: 5px 0px;">
																	<label class="col-md-2 control-label" style="padding-left: 0px;">备份数据类型</label>
																	<div class="col-md-5">
																		<select class="bs-select form-control input-sm" id="" >
																			<option value="0">卡车资料、收费规则、系统配置等信息</option>
																			<option value="1"></option>
																			<option value="3"></option>
																			<option value="4"></option>
																			<option value="5"></option>
																		</select>
																	</div>
																	<button type="button" class="btn btn-sm blue progress-button" id="backup">手动备份</button>
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">数据手动还原</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-12" style="margin: 5px 0px;">
																	<label class="col-md-2 control-label" style="padding-left: 0px;">还原文件</label>
																	<div class="col-md-5">
																		<div id="dataFile" class="fileinput fileinput-new" data-provides="fileinput">
																			<div class="input-group">
																				<div class="form-control uneditable-input input-sm" data-trigger="fileinput"  >
																					<i class="fa fa-file fileinput-exists"></i>&nbsp;
																					<span class="fileinput-filename"></span>
																				</div>
																				<span class="input-group-addon btn default btn-file">
																				<span class="fileinput-new">
																				选择 </span>
																				<span class="fileinput-exists">
																				更改 </span>
																				<input type="file" name="myfiles">
																				</span>
																				<a href="#" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput">
																				删除 </a>
																			</div>
																		</div>
																	</div>
																	<button type="button" class="btn btn-sm blue progress-button"  id="load">手动还原</button>
																</div>
															</div>
								                        </div>
								                        <div class="panel panel-default">
															<div class="panel-heading">
																<h3 class="panel-title">数据自动备份配置（备份的数据为卡车资料、收费规则、系统配置等信息）</h3>
															</div>
															<div class="panel-body">
																<div class="col-md-12" style="margin: 5px 0px;">
																	<label class="col-md-2 control-label" style="padding-left: 0px;">每日自动备份时间</label>
																	<div class="col-md-3">
																		<input type="text" value="0:00 PM" data-format="hh:mm A" class="input-sm form-control clockface_1" id ="backupTime" name="backupTime"/>
																	</div>
																	<label class="col-md-3 control-label" style="padding-left: 0px;">启用数据自动备份</label>
																	<div class="col-md-4" style="height:28px;">
																		<input type="checkbox" fill=""  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" name="" data-off-color="default" checked>
																	</div>
																</div>
															</div>
								                        </div>
													</div>
													<div class="tab-pane fade control_label_middle" id="tab_1_5">
														<div class="wavecom" id="wavecomId">
														</div>
														<div class="row">
															<div class="col-md-1">
																<button type="button" class="btn btn-sm blue clonebtn">新增</button>
															</div>
														</div>
													</div>
													<div class="tab-pane fade control_label_middle" id="tab_1_6">
														<div class="col-md-12" style="margin: 5px 0px;">
															<label class="col-md-2 control-label" style="padding-left: 0px;">选择默认临时客户组</label>
															<div class="form-group">
															<div class="col-md-4">
																<input class="form-control select2 ugroup_temp_name_id" name="defaultGroup" fill="obj.defaultGroup"/>
															</div>
														</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button"  id="save" class="btn green">保存</button>
									</div>	
								</div>
							</form>
						</div>
					</div>
			</div>

			<!--这个页面的弹窗都在这里 start-->
			
			<!--这个页面的弹窗都在这里 end-->
		</div>
	</div>
	<!-- END CONTAINER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="/views/include/globalJs.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- <script src="<%=request.getContextPath()%>/assets/global/plugins/jcrop/js/jquery.color.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jcrop/js/jquery.Jcrop.min.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.min.js"></script><!--下拉框插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script><!--表格插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script><!--bootstrap的表格插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script><!--bootstrap的下拉框插件-->
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script> --%><!--多选插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script><!--日期选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.min.js"></script><!--日期选择插件 国际化-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script><!--时间选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js"></script><!--折扣那个插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootbox/bootbox.min.js"></script><!--alert插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/master/dist/js/bootstrapValidator.min.js"></script><!-- 表单验证的插件 -->
 <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script><!--单选按钮和复选按钮的插件--> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/fuelux/js/spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/format.js"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/components-pickers.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
<script src="<%=request.getContextPath()%>/js/progress.js"></script>
<script>
jQuery(document).ready(function() {
// initiate layout and plugins
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
park.init(); //Form中精确搜索的的初始化 可以查看/js/park.js
park.dateTimeInit(); //日期框的初始化 
park.modalInit();
park.monthSpinnerInit();
park.touchspinInit();
ComponentsDropdowns.init();
ComponentsPickers.init();
ugroupSelect2.initTempUgroupNameId();
UIIdleTimeout.init();
});
</script>
<script type="text/javascript">
$(function(){
	edit.init();
});
var edit=function(){
	var count = 0;
	var addWavecom=function(){
		$(".clonebtn").on("click",function(){
			$('.wavecom').append(
					'<form id="newForm_'+count+'">'+
					'<input type="hidden" class="form-control normal-input" name="id_'+count+'" fill="obj.id">'+
					'<div class="form-body combody">'+
						'<div class="form-group">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">发送消息至</label>'+
							'<div class="col-md-5">'+
								'<select class="bs-select form-control input-sm" id="address_'+count+'" name="address_'+count+'">'+
									'<option value="0">用户联系电话</option>'+
								'</select>'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">发送条件</label>'+
							'<div class="col-md-5">'+
								'<select class="bs-select form-control input-sm" name="wavecom_rentType_'+count+'" id="wavecom_rentType_'+count+'">'+
								'<option value="0">余额不足</option>'+
								'<option value="1">停车时长超过阀值</option>'+
								'<option value="2">长期车位即将到期</option>'+
								'<option value="3">长期会员即将到期</option>'+
								'</select>'+
							'</div>'+
						'</div>'+
						'<div class="form-group balanceWc_Div_'+count+'">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">余额（元）</label>'+
							'<div class="col-md-5">'+
								'<input type="text" class="form-control input-sm" name="balanceWc_'+count+'" fill="">'+
							'</div>'+
						'</div>'+
						'<div class="form-group parktimeWc_Div_'+count+'" style="display:none;">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">停车时长（小时）</label>'+
							'<div class="col-md-5">'+
								'<input type="text" class="form-control input-sm" name="parktimeWc_'+count+'" fill="">'+
							'</div>'+
						'</div>'+
						'<div class="form-group  parkspaceWc_Div_'+count+'" style="display:none;">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">长期车位（天）</label>'+
							'<div class="col-md-5">'+
								'<input type="text" class="form-control input-sm" name="parkspaceWc_'+count+'" fill="">'+
							'</div>'+
						'</div>'+
						'<div class="form-group vipWc_Div_'+count+'" style="display:none;">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">长期会员（天）</label>'+
							'<div class="col-md-5">'+
								'<input type="text" class="form-control input-sm" name="vipWc_'+count+'" fill="">'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<label class="col-md-2 control-label" style="padding-left: 0px;">发送内容</label>'+
							'<div class="col-md-5">'+
								'<input type="text" class="form-control input-sm" name="content_'+count+'" fill="">'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<label class="col-md-2 control-label">是否启用</label>'+
							'<div class="col-md-5" style="height:28px;">'+
								'<input name="enableFlag_'+count+'" type="checkbox" class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" data-off-color="default" checked>'+
							'</div>'+
						'</div>'+
						'<div class="form-group">'+
							'<div class="col-md-1 col-md-offset-6">'+
								'<button type="button" class="btn btn-sm blue removebtn" id="removebtn_'+count+'">删除</button>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</form>');
			count=count+1;
			park.touchspinInit();
			ComponentsDropdowns.init();
			Metronic.init(); 
			bindRentTypeChange();
			delWavecom();
		})
	};

	var delWavecom=function(){
		var countNum=count;
		for(var i=0;i<countNum;i++){
			$("#removebtn_"+i).live("click",function(){
				var id = $(this).attr('id');
				var idNum = id.split("_")[1];
				$("#newForm_"+idNum).remove();
			})
		}
	}; 

	var bindRentTypeChange = function() {
		for(var i=0;i<count;i++){
			$("select[id='wavecom_rentType_"+i+"']").on('change',
				function(ele) {
					var value = $(this).val();
					var id = $(this).attr('id');
					var idNum = id.split("_")[2];
					if (value == '0') {
						$(".balanceWc_Div_"+idNum).show();
						$(".parktimeWc_Div_"+idNum).hide();
						$(".parkspaceWc_Div_"+idNum).hide();
						$(".vipWc_Div_"+idNum).hide();
						$(".balanceWc_Div_"+idNum+" :input").removeAttr("disabled");
						$(".parktimeWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parkspaceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".vipWc_Div_"+idNum+" :input").attr("disabled","disabled");
					} else if (value == '1') {
						$(".balanceWc_Div_"+idNum).hide();
						$(".parktimeWc_Div_"+idNum).show();
						$(".parkspaceWc_Div_"+idNum).hide();
						$(".vipWc_Div_"+idNum).hide();
						$(".balanceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parktimeWc_Div_"+idNum+" :input").removeAttr("disabled");
						$(".parkspaceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".vipWc_Div_"+idNum+" :input").attr("disabled","disabled");
					} else if (value == '2') {
						$(".balanceWc_Div_"+idNum).hide();
						$(".parktimeWc_Div_"+idNum).hide();
						$(".parkspaceWc_Div_"+idNum).show();
						$(".vipWc_Div_"+idNum).hide();
						$(".balanceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parktimeWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parkspaceWc_Div_"+idNum+" :input").removeAttr("disabled");
						$(".vipWc_Div_"+idNum+" :input").attr("disabled","disabled");
					} else if (value == '3') {
						$(".balanceWc_Div_"+idNum).hide();
						$(".parktimeWc_Div_"+idNum).hide();
						$(".parkspaceWc_Div_"+idNum).hide();
						$(".vipWc_Div_"+idNum).show();
						$(".balanceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parktimeWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".parkspaceWc_Div_"+idNum+" :input").attr("disabled","disabled");
						$(".vipWc_Div_"+idNum+" :input").removeAttr("disabled");
					}
				})
		}
		
	}
	var binddata =function(){
		$.ajax({
			url :"${getDataUrl}",
			success : function(data) {
				var obj = data;
				var formObj = obj.object;
				var countSpFlag = formObj.countSpFlag;
				var chargeFlag = formObj.chargeFlag;
				var useRfidFlag = formObj.useRfidFlag;
				var pspLockFlag = formObj.pspLockFlag;
				var plateRecoCameraFlag = formObj.plateRecoCameraFlag;
				var pattern = formObj.pattern;
				var smsRecModels = formObj.smsRecModels;
				if(obj.success){
					$("#editForm").fill(obj.object);//表单填充插件
					bindSmsRecModels(smsRecModels);
					$('#editForm').find(".ugroup_temp_name_id > a > span").eq(0).text(formObj.defaultGroupName);
					$('#editForm input[name="backupPath"]').val(formObj.backupPath);
					$('#editForm input[name="countSpFlag"]').bootstrapSwitch('state',countSpFlag,countSpFlag);
					$('#editForm input[name="chargeFlag"]').bootstrapSwitch('state',chargeFlag,chargeFlag);
					$('#editForm input[name="useRfidFlag"]').bootstrapSwitch('state',useRfidFlag,useRfidFlag);
					$('#editForm input[name="pspLockFlag"]').bootstrapSwitch('state',pspLockFlag,pspLockFlag);
					$('#editForm input[name="plateRecoCameraFlag"]').bootstrapSwitch('state',plateRecoCameraFlag,plateRecoCameraFlag);
					if(pattern == 0)
						$('#edit_deciHandle0').iCheck('check');
					else if(pattern==1)
						$('#edit_deciHandle1').iCheck('check');
					else if(pattern==2)
						$('#edit_deciHandle2').iCheck('check');
				}else{
					alert(obj.msg);		
				}
			},
			error : function(error) {
				alert(error.status + "," + error.readyState);
			}
		});
	}
	var formToJson = function (data) {
	       data=data.replace(/&/g,"\",\"");
	       data=data.replace(/=/g,"\":\"");
	       data=data.replace(/\+/g," ");
	       data="{\""+data+"\"}";
	       return data;
	}
	var bindSubmit = function() {
		$("#save").on("click", function() {
			var backupPath=$('[name=backupPath]').val();
			backupPath=backupPath.replace(/\\/g,"/");
			$('[name=backupPath]').val(backupPath);
			var parkingEnvirStr = $('#editForm').serialize();
			parkingEnvirStr = decodeURIComponent(parkingEnvirStr,true);//防止中文乱码
			var parkingEnvirJsonStr=formToJson(parkingEnvirStr);//转化为json
			var infoJsonStr='['+jsonData()+']';
			if(!check($("#editForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
					type : "POST",
					url : "${saveUrl}",
					data : {parkingEnvirJsonStr:parkingEnvirJsonStr,infoJsonStr:infoJsonStr},// 你的formid
					dataType:'json', 
					error : function(request) {
						alert("Connection error");
					},
					success : function(data) {
						var obj = data;
						if (obj.success) {
							toastr.success(obj.msg, "停车场环境变量模块");
						} else {
							toastr.error(obj.msg, "停车场环境变量模块");
						}
					}
				});
			}
		})
		function jsonData() {
			var jsonList = new Array();
			for (var i = 0; i < count; i++) {
				var obj = $("#newForm_" + i).serialize();
				if(obj!=""){
					obj = decodeURIComponent(obj,true);//防止中文乱码
					var strs = obj.split("&");
						var jsonStr = "{\"id\":\"";
						jsonStr += strs[0].split("=")[1];
						jsonStr += "\",";
						
						jsonStr += "\"address\":\"";
						jsonStr += strs[1].split("=")[1] ;
						jsonStr += "\",";

						jsonStr += "\"type\":\"";
						jsonStr += strs[2].split("=")[1];
						jsonStr += "\",";
						
						if(strs[2].split("=")[1] == 0){
							jsonStr += "\"condition\":\"";
							jsonStr += strs[3].split("=")[1];
							jsonStr += "\",";
						}else if(strs[2].split("=")[1] == 1){
							jsonStr += "\"condition\":\"";
							jsonStr += strs[3].split("=")[1];
							jsonStr += "\",";
						}else if(strs[2].split("=")[1] == 2){
							jsonStr += "\"condition\":\"";
							jsonStr += strs[3].split("=")[1];
							jsonStr += "\",";
						}else{
							jsonStr += "\"condition\":\"";
							jsonStr += strs[3].split("=")[1];
							jsonStr += "\",";
						}
					    if(strs.length==5||strs.length==6){
					    	jsonStr += "\"content\":\"";
							jsonStr += strs[4].split("=")[1];
							jsonStr += "\",";	
					    }else if(strs.length==8||strs.length==9){
					    	jsonStr += "\"content\":\"";
							jsonStr += strs[7].split("=")[1];
							jsonStr += "\",";
					    }
						if(strs.length==5){
							jsonStr += "\"enableFlag\":\"0\"}";
						}else if(strs.length==6){
							jsonStr += "\"enableFlag\":\"1\"}";
						}else if(strs.length==8){
							jsonStr += "\"enableFlag\":\"0\"}";
						}else if(strs.length==9){
							jsonStr += "\"enableFlag\":\"1\"}";
						}
						jsonList.push(jsonStr);
				}
			}
			return eval(jsonList);//关键在于转换。 
		}
			
		$("#backup").on("click",function(){
			var formData = $('#editForm').serialize();
			formData = decodeURIComponent(formData,true);//防止中文乱码
			var opp= $.extend({
				type:'background-horizontal',
				loading: '数据备份中...',
				finished: '手动备份'
			}, $("#backup").data());
			$("#backup").progressInitialize(opp);
			$("#backup").progressStart();
			$("#backup").progressIncrement();
			$.ajax({
		       type: "POST",
		       url: "${backupUrl}",
		       data:$('#editForm').serialize(),// 你的formid
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$('#backup').progressFinish();
		       		toastr.success(obj.msg,"停车场环境变量模块");
		       	}
		       	else{
		       		toastr.error(obj.msg, "停车场环境变量模块");
		       	}
		       }
		   }); 
		})
		var options = {
					dataType:  'json',
					type: "post",
					url :"${loadUrl}",
				    async: true,
					success: afterSubmit,
		}	
		function afterSubmit(data){
			if(data==null||data==""){
        		return;
        	}
        	var obj = data;
        	if(obj.success){
        		$('#dataFile').removeClass('fileinput-exists');
        		$('#dataFile').addClass('fileinput-new');
        		$('.fileinput-filename').text("");
        		$('#load').progressFinish();
        		toastr.success(obj.msg,"停车场环境变量模块");
	       	} else{
	       		toastr.error(obj.msg, "停车场环境变量模块");
	       	}
		}
		$("#load").on("click",function(e){
			var ext = '.sql.'; 
			var file = $(".fileinput-filename").text();  
			if(file == ""){
				bootbox.alert("请选择一个数据文件行再进行操作！");
			}else{
				file = file.substr(file.lastIndexOf('.')+1).toLowerCase();
				if(ext.indexOf('.'+file+'.')==-1){
					bootbox.alert("文件格式不正确，文件格式应为.sql");   
				}else{
					var op = $.extend({
						type:'background-horizontal',
						loading: '数据还原中...',
						finished: '手动还原'
					}, $('#load').data());
					$('#load').progressInitialize(op);
					$('#load').progressStart();
					$('#load').progressIncrement();
					$("#editForm").ajaxSubmit(options);
				}
			}
		})
	}
	return {
		init:function(){
			addWavecom();
			bindRentTypeChange();
			binddata();
			bindSubmit();
		}
	};
	
}();
function bindSmsRecModels(smsRecModels){
	for(var i=0;i<smsRecModels.length;i++){
		var address = smsRecModels[i].address; // 发送消息至
		var condition = smsRecModels[i].condition;// 余额（元）
		var content = smsRecModels[i].content; // 发送内容
		var enableFlag = smsRecModels[i].enableFlag; // 是否启用
		var id = smsRecModels[i].id;
		var name = smsRecModels[i].name; // 
		var type = smsRecModels[i].type;
		$(".clonebtn").click();
		// 得到控件
		$("input[name='id_"+i+"']").val(id);
		$("select[name='address_"+i+"'] option[selected]").val(address);
		$("select[name='wavecom_rentType_"+i+"']").val(type);
		$("select[name='wavecom_rentType_"+i+"']").trigger("change");
		if(type == 0){
			$("input[name='balanceWc_"+i+"']").val(condition);
		}else if(type == 1){
			$("input[name='parktimeWc_"+i+"']").val(condition);
		}else if(type == 2){
			$("input[name='parkspaceWc_"+i+"']").val(condition);
		}else{
			$("input[name='vipWc_"+i+"']").val(condition);
		}
		$("input[name='content_"+i+"']").val(content);
		$("input[name='enableFlag_"+i+"']").bootstrapSwitch('state',enableFlag,enableFlag);
	}
}

//验证表单的方法
function check(form){
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		    	spNumber: {
		    		digits: true,
		            required: true
		        },
		        nowSpNumber: {
		            digits: true,
		            required: true
		        },
		        uexpirReminder: {
		        	min: 1,
		            digits: true,
		            required: true
		        },
		        pspExpirReminder: {
		        	min: 1,
		            digits: true,
		            required: true
		        },
		        minBalance: {
		            min: 1,
		            required: true
		        },
		        minPay: {
		            min: 1,
		            required: true
		        },
		      
		    },
			    invalidHandler: function (event, validator) { //display error alert on form submit   
			        success3.hide();
			        error3.show();
			        Metronic.scrollTo(error3, -200);
			    }
			};
		return form.validate($.extend({},config,$.global_config.validator_config));
};

function BrowseFolder(obj) {
    try {
        var Message = "请选择保存路径";  //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 0x0040, 0x11); //起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0,Message,0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items();  // 返回 FolderItems 对象
            Folder = Folder.item();  // 返回 Folderitem 对象
            Folder = Folder.Path;   // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            obj.value = Folder;
            return Folder;
        }
    } catch (e) {
        alert(e.message);
    }

}
</script>


<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>