<%@ page contentType="text/html; charset=utf-8" language="java"
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-multi-select/css/multi-select.css"/>
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
				
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form">
							<div class="portlet box blue">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i>设备管理
									</div>
								</div>
								<div class="portlet-body">
									<ul class="nav nav-pills">
										<li class="active">
											<a href="#tab_1_1" data-toggle="tab">闸机管理</a>
										</li>
										<li>
											<a href="#tab_1_2" data-toggle="tab">缴费机管理</a>
										</li>
										<li>
											<a href="#tab_1_3" data-toggle="tab">终端机管理</a>
										</li>
										<li>
											<a href="#tab_1_4" data-toggle="tab">查询一体机管理</a>
										</li>
										<li>
											<a href="#tab_1_5" data-toggle="tab">数据转换主机管理</a>
										</li>
									</ul>
									<div class="tab-content">
									    <div class="tab-pane active" id="tab_1_1">
											<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
												<div class="portlet-body" style="position:relative;">
													<div class="btn-group" role="group" aria-label="...">
														<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add1">
														    <span class="glyphicon glyphicon-plus"></span> 新增
													    </a>
													    <button type="button" class="btn btn-sm btn-default brake_modify">
															<span class="glyphicon glyphicon-pencil"></span> 修改 
														</button>
														<button type="button" class="btn btn-sm btn-default brake_del">
															<span class="glyphicon glyphicon-ban-circle"></span> 删除 
														</button>
														<button type="button" class="btn btn-sm btn-default tb_disabled">
															<span class="glyphicon glyphicon-ban-circle"></span> 关闭闸机
														</button>
														<button type="button" class="btn btn-sm btn-default tb-enable">
															<span class="glyphicon glyphicon-ok-circle"></span> 开启闸机
														</button>
													</div>
													<table id="brake" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="table-checkbox">
																	<input type="checkbox" class="group-checkable" data-set="#brake .checkboxes" />
																</th>
																<th>闸机编号</th>
																<th>闸机名称</th>
																<th>闸机位置</th>
																<th>IP</th>
																<th>端口号</th>
																<th>开信号</th>
																<th>关信号</th>
																<th>在线/离线</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<div class="tab-pane fade" id="tab_1_2">
											<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
												<div class="portlet-body" style="position:relative;">
													<div class="btn-group" role="group" aria-label="...">
														<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add2">
														    <span class="glyphicon glyphicon-plus"></span> 新增
													    </a>
													    <button type="button" class="btn btn-sm btn-default payment_modify">
															<span class="glyphicon glyphicon-pencil"></span> 修改 
														</button>
														<button type="button" class="btn btn-sm btn-default payment_del">
															<span class="glyphicon glyphicon-ban-circle"></span> 删除 
														</button>
													</div>
													<table id="payment" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="table-checkbox">
																	<input type="checkbox" class="group-checkable" data-set="#payment .checkboxes" />
																</th>
																<th>缴费机编号</th>
																<th>缴费机名称</th>
																<th>缴费机位置</th>
																<th>IP</th>
																<th>端口号</th>
																<th>在线/离线</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>

										<div class="tab-pane fade control_label_middle" id="tab_1_3">
											<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
												<div class="portlet-body" style="position:relative;">
													<div class="btn-group" role="group" aria-label="...">
														<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add3">
														    <span class="glyphicon glyphicon-plus"></span> 新增
													    </a>
													    <button type="button" class="btn btn-sm btn-default terminal_modify">
															<span class="glyphicon glyphicon-pencil"></span> 修改 
														</button>
														<button type="button" class="btn btn-sm btn-default terminal_del">
															<span class="glyphicon glyphicon-ban-circle"></span> 删除 
														</button>
													</div>
													<table id="terminal" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="table-checkbox">
																	<input type="checkbox" class="group-checkable" data-set="#terminal .checkboxes" />
																</th>
																<th>终端机编号</th>
																<th>终端机名称</th>
																<th>终端机位置</th>
																<th>IP</th>
																<th>端口号</th>
																<th>在线/离线</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<div class="tab-pane fade control_label_middle" id="tab_1_4">
											<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
												<div class="portlet-body" style="position:relative;">
													<div class="btn-group" role="group" aria-label="...">
														<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add4">
														    <span class="glyphicon glyphicon-plus"></span> 新增
													    </a>
													    <button type="button" class="btn btn-sm btn-default integrated_modify">
															<span class="glyphicon glyphicon-pencil"></span> 修改 
														</button>
														<button type="button" class="btn btn-sm btn-default integrated_del">
															<span class="glyphicon glyphicon-ban-circle"></span> 删除 
														</button>
													</div>
													<table id="integrated" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="table-checkbox">
																	<input type="checkbox" class="group-checkable" data-set="#integrated .checkboxes" />
																</th>
																<th>查询一体机编号</th>
																<th>查询一体机名称</th>
																<th>查询一体机位置</th>
																<th>IP</th>
																<th>端口号</th>
																<th>在线/离线</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
										<div class="tab-pane fade control_label_middle" id="tab_1_5">
											<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
												<div class="portlet-body" style="position:relative;">
													<div class="btn-group" role="group" aria-label="...">
														<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add5">
														    <span class="glyphicon glyphicon-plus"></span> 新增
													    </a>
													    <button type="button" class="btn btn-sm btn-default dataMaster_modify">
															<span class="glyphicon glyphicon-pencil"></span> 修改 
														</button>
														<button type="button" class="btn btn-sm btn-default dataMaster_del">
															<span class="glyphicon glyphicon-ban-circle"></span> 删除 
														</button>
													</div>
													<table id="dataMaster" class="table table-bordered table-hover">
														<thead>
															<tr>
																<th class="table-checkbox">
																	<input type="checkbox" class="group-checkable" data-set="#dataMaster .checkboxes" />
																</th>
																<th>数据转换主机编号</th>
																<th>数据转换主机名称</th>
																<th>数据转换主机位置</th>
																<th>IP</th>
																<th>端口号</th>
																<th>在线/离线</th>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="./addBrake.jsp" />
			<jsp:include page="./editBrake.jsp" />
			<jsp:include page="./addPayment.jsp" />
			<jsp:include page="./editPayment.jsp" />
			<jsp:include page="./addTerminal.jsp" />
			<jsp:include page="./editTerminal.jsp" />
			<jsp:include page="./addIntegrated.jsp" />
			<jsp:include page="./editIntegrated.jsp" />
			<jsp:include page="./addDataMaster.jsp" />
			<jsp:include page="./editDataMaster.jsp" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-multi-select/js/jquery.multi-select.js"></script><!--多选插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script><!--日期选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.min.js"></script><!--日期选择插件 国际化-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script><!--时间选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js"></script><!--折扣那个插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootbox/bootbox.min.js"></script><!--alert插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/master/dist/js/bootstrapValidator.min.js"></script><!-- 表单验证的插件 -->
 <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script><!--单选按钮和复选按钮的插件--> -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/pageLevelJs/device/deviceGlobal.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/fuelux/js/spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>

<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/components-pickers.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
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
UIIdleTimeout.init();
(function () {
	var dataTable_brake = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    "bServerSide": true,
	    "sAjaxSource": pageUrl.device_deviceAll_brakeList,
	    "fnServerData":retrieveData,
	    "aoColumns" : [ 
	    {
	    	"mDataProp" :"id",
	    	"sClass":"center",
	    }, {
	        "mDataProp" : "deviceId",
	        "sWidth": "150px"
	    }, {
	        "mDataProp" : "name",
	        "sWidth": "150px"
	    }, {
	        "mDataProp" : "posit",
	        "sWidth": "150px"
	    },{
	        "mDataProp" : "ip",
	        "sWidth": "150px"
	    },{
	        "mDataProp" : "port",
	        "sWidth": "150px"
	    },{
	        "mDataProp" : "open",
	        "sWidth": "150px"
	    },{
	        "mDataProp" : "close",
	        "sWidth": "150px"
	    },{
	        "mDataProp" : "lineState",
	        "sWidth": "150px"
	    }  
	    ],
	    "columnDefs": [
		{  // 设置默认值
		    'orderable': false,
		    'targets': [0]
		},
		{
		 sDefaultContent: '',
		 aTargets: [ '_all' ]
		},
		{  aTargets: [0],
			"render": function ( data, type, row ) {
		   return '<input type="checkbox" class="checkboxes" data-name="'+row.name+'" data-state="'+row.enableFlag+'" data-linestate="'+row.lineState+'" value="'+data+'"/>';
		   }
		},
		{   aTargets: [8],
			"render": function ( data, type, full, meta ) {
    		 	var result;
	            if(data==true){
	            	result = "在线";
	            }
	            else{
	            	result = "离线";
	            }
	            return result;
	     }
	    }
	    ],
	    "order": [
		            [1, "asc"]
		 ] //升序排列
	};
	var dataTable_payment = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.device_deviceAll_paymentList,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
		    {
		    	"mDataProp" :"id",
		    	"sClass":"center",
		    }, {
		        "mDataProp" : "deviceId",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "name",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "posit",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "ip",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "port",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "lineState",
		        "sWidth": "150px"
		    }  
		    ],
		    "columnDefs": [
			{  // 设置默认值
			    'orderable': false,
			    'targets': [0]
			},
			{
			 sDefaultContent: '',
			 aTargets: [ '_all' ]
			},
			{  aTargets: [0],
				   "render": function ( data, type, full, meta ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
			},
		    {   aTargets: [6],
				"render": function ( data, type, full, meta ) {
	    		 	var result;
		            if(data==true){
		            	result = "在线";
		            }
		            else{
		            	result = "离线";
		            }
		            return result;
		     }
		    }
		    ],
		    "order": [
			            [1, "asc"]
			 ] //升序排列
		};
	var dataTable_terminal = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.device_deviceAll_terminalList,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
		    {
		    	"mDataProp" :"id",
		    	"sClass":"center",
		    }, {
		        "mDataProp" : "deviceId",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "name",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "posit",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "ip",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "port",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "lineState",
		        "sWidth": "150px"
		    }  
		    ],
		    "columnDefs": [
			{  // 设置默认值
			    'orderable': false,
			    'targets': [0]
			},
			{
			 sDefaultContent: '',
			 aTargets: [ '_all' ]
			},
			{  aTargets: [0],
				   "render": function ( data, type, full, meta ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
			},
		    {   aTargets: [6],
				"render": function ( data, type, full, meta ) {
	    		 	var result;
		            if(data==true){
		            	result = "在线";
		            }
		            else{
		            	result = "离线";
		            }
		            return result;
		     }
		    }
		    ],
		    "order": [
			            [1, "asc"]
			 ] //升序排列
		};
	var dataTable_integrated = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.device_deviceAll_integratedList,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
		    {
		    	"mDataProp" :"id",
		    	"sClass":"center",
		    }, {
		        "mDataProp" : "deviceId",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "name",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "posit",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "ip",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "port",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "lineState",
		        "sWidth": "150px"
		    }  
		    ],
		    "columnDefs": [
			{  // 设置默认值
			    'orderable': false,
			    'targets': [0]
			},
			{
			 sDefaultContent: '',
			 aTargets: [ '_all' ]
			},
			{  aTargets: [0],
				   "render": function ( data, type, full, meta ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
			},
		    {   aTargets: [6],
		    	"render": function ( data, type, full, meta ) {
		    		 	var result;
			            if(data==true){
			            	result = "在线";
			            }
			            else{
			            	result = "离线";
			            }
			            return result;
			     } 
		    }
		    ],
		    "order": [
			            [1, "asc"]
			 ] //升序排列
		};
		var dataTable_dataMaster = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.device_deviceAll_dataMasterList,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
		    {
		    	"mDataProp" :"id",
		    	"sClass":"center",
		    }, {
		        "mDataProp" : "deviceId",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "name",
		        "sWidth": "150px"
		    }, {
		        "mDataProp" : "posit",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "ip",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "port",
		        "sWidth": "150px"
		    },{
		        "mDataProp" : "lineState",
		        "sWidth": "150px"
		    }  
		    ],
		    "columnDefs": [
			{  // 设置默认值
			    'orderable': false,
			    'targets': [0]
			},
			{
			 sDefaultContent: '',
			 aTargets: [ '_all' ]
			},
			{  aTargets: [0],
				   "render": function ( data, type, full, meta ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
			},
		    {   aTargets: [6],
		    	"render": function ( data, type, full, meta ) {
		    		 	var result;
			            if(data==true){
			            	result = "在线";
			            }
			            else{
			            	result = "离线";
			            }
			            return result;
			     } 
		    }
		    ],
		    "order": [
			            [1, "asc"]
			 ] //升序排列
		};
	
		function retrieveData( sSource, aoData, fnCallback ) {     
		    $.ajax( {     
		        type: "POST",
		        url: sSource,   
		        dataType:"json",  
		        data: "jsonParam="+JSON.stringify(aoData),  
		        success: function(data) {   
		            fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式  
		            Metronic.init();
		        }     
		    });    
		}
	var $table1 = $('#brake');
	dataTable_brake = $.extend({},dataTable_brake,$.global_config.table_config);
	oTable1 = $table1.dataTable(dataTable_brake);
	$.table_init($table1);

	var $table2 = $('#payment');
	dataTable_payment = $.extend({},dataTable_payment,$.global_config.table_config);
	oTable2 = $table2.dataTable(dataTable_payment);
	$.table_init($table2);

	var $table3 = $('#terminal');
	dataTable_terminal = $.extend({},dataTable_terminal,$.global_config.table_config);
	oTable3 = $table3.dataTable(dataTable_terminal);
	$.table_init($table3);
	
	var $table4 = $('#integrated');
	dataTable_integrated = $.extend({},dataTable_integrated,$.global_config.table_config);
	oTable4 = $table4.dataTable(dataTable_integrated);
	$.table_init($table4);
	

	var $table5 = $('#dataMaster');
	dataTable_dataMaster = $.extend({},dataTable_dataMaster,$.global_config.table_config);
	oTable5 = $table5.dataTable(dataTable_dataMaster);
	$.table_init($table5);
	})();       
});

$(function() {
	var dataSource1 = $("#brake");
	var dataSource2 = $("#payment");
	var dataSource3 = $("#terminal");
	var dataSource4 = $("#integrated");
	var dataSource5 = $("#dataMaster");
	var editAndDblUrl = pageUrl.device_deviceAll_editAndDbl;
	addBrake.init();
	addPayment.init();
	addTerminal.init();
	addIntegrated.init();
	addDataMaster.init();
	editBrake.init(dataSource1,$("#modify1"),editAndDblUrl);
	editPayment.init(dataSource2,$("#modify2"),editAndDblUrl);
	editTerminal.init(dataSource3,$("#modify3"),editAndDblUrl);
	editIntegrated.init(dataSource4,$("#modify4"),editAndDblUrl);
	editDataMaster.init(dataSource5,$("#modify5"),editAndDblUrl);
	
	$('.brake_del').delUseDialog(dataSource1,pageUrl.device_deviceAll_del,"设备管理");
    $('.payment_del').delUseDialog(dataSource2,pageUrl.device_deviceAll_del,"设备管理");
    $('.terminal_del').delUseDialog(dataSource3,pageUrl.device_deviceAll_del,"设备管理");
    $('.integrated_del').delUseDialog(dataSource4,pageUrl.device_deviceAll_del,"设备管理");
    $('.dataMaster_del').delUseDialog(dataSource5,pageUrl.device_deviceAll_del,"设备管理");
	$('.tb-enable').openUseDialog(dataSource1,pageUrl.device_deviceAll_enableBrake,"设备管理");
	$('.tb_disabled').closeUseDialog(dataSource1,pageUrl.device_deviceAll_disableBrake,"设备管理");
    $('.multi-select').multiSelect({keepOrder: true,selectableOptgroup: true,selectableHeader: "<div class='custom-header'>可选的车道</div>",
  	  selectionHeader: "<div class='custom-header'>选中的车道</div>"});
     
    
});

//验证表单的方法
function check(form){
		
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		        open: {
		    		digits: true,
		            maxlength: 1,
		            required: true
		        },
		        close: {
		            digits: true,
		            maxlength: 1,
		            required: true
		        },
		        name: {
		            maxlength: 20,
		            required: true
		        },
		        posit: {
		            maxlength: 20,
		            required: true
		        },
		        ip: {
		            ip: true,
		            required: true
		        },
		        port: {
		        	digits: true,
		        	maxlength: 5,
		            required: true
		        } 
		    },
			    invalidHandler: function (event, validator) { //display error alert on form submit   
			        success3.hide();
			        error3.show();
			        Metronic.scrollTo(error3, -200);
			    }
			};
		return form.validate($.extend({},config,$.global_config.validator_config));
};

</script>

<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>