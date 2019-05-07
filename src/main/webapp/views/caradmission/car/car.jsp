﻿<%@ page contentType="text/html; charset=utf-8" language="java"	import="java.sql.*" errorPage=""%>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/master/dist/css/bootstrapValidator.min.css"/><!-- 表单验证的样式 -->
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
				<jsp:include page="/views/layout/pageheader.jsp" />
				<jsp:include page="./form.jsp" />

				<!-- START POTTLEX demo2------------>
				<div class="portlet box grey-cascade"
					style="margin-top: 15px; border-top: 1px solid #b1bdbd">
					<div class="portlet-body" style="position:relative;">
						<div class="btn-group" role="group" aria-label="..." >
							<button type="button" class="btn btn-sm btn-default tb_add">
								<span class="glyphicon glyphicon-plus"></span> 新增 
							</button>
							<button type="button" class="btn btn-sm btn-default tb_modify">
								<span class="glyphicon glyphicon-pencil"></span> 修改 
							</button>
							<button type="button" class="btn btn-sm btn-default tb_del">
								<span class="glyphicon glyphicon-ban-circle"></span> 删除
							</button>
						</div>
						<table class="table table-bordered table-hover" id="favourable">
							<thead>
								<tr>
									<th class="table-checkbox">
										<input type="checkbox" class="group-checkable" data-set="#favourable .checkboxes" />
									</th>
									<th>车牌号码</th>
									<th>车牌类型</th>
									<th>车辆颜色</th>
									<th>车辆类型</th>
									<th>车辆型号</th>
									<th>客户姓名(双击查看客户信息)</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- END POTTLEX demo2------------>
			</div>
			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="/views/caradmission/car/add.jsp" />
			<jsp:include page="/views/caradmission/car/addColor.jsp" />
			<jsp:include page="/views/caradmission/car/addModule.jsp" />
			<jsp:include page="/views/caradmission/car/addType.jsp" />
			<jsp:include page="/views/caradmission/car/edit.jsp" />
			<jsp:include page="/views/caradmission/car/show.jsp" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>
<script>
jQuery(document).ready(function() {
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	ComponentsDropdowns.init(); //初始化普通下拉框
	park.dateTimeInit();
	park.init();
	park.toastrInit();
	park.modalInit();
	carSelect2.initLicensePlateLicensePlate();
	carSelect2.initCarType();
	carSelect2.initCarColor();
	carSelect2.initCarModule();
	userSelect2.initUserNameId();
	(function () {
		var dataTable_config = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.caradmission_car_list,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
			    {
			    	"mDataProp" :"id",
			    	"sClass":"center",
			    }, {
			        "mDataProp" : "licensePlate",
			        "sWidth": "100px"
			    }, {
			        "mDataProp" : "type",
			        'orderable': false,
			        "sWidth": "100px"
			    }, {
			        "mDataProp" : "carColor",
			        'orderable': false,
			        "sWidth": "100px"
			    }, 
			    {
			        "mDataProp" : "carType",
			        "sWidth": "100px"
			    }, 
			    {
			        "mDataProp" : "carModel",
			        'orderable': false,
			        "sWidth": "100px"
			    },{
			        "mDataProp" : "name",
			        'orderable': false,
			        "sWidth": "200px"
			    }
		    ],
		    "columnDefs": [
				{  // 设置默认值
				    'orderable': false,
				    'targets': [0]
				}, {
				    "searchable": false,
				    "targets": [0]
				},
				{
					 sDefaultContent: '',
					 aTargets: [ '_all' ]
				},
				{  aTargets: [0],
				   "render": function ( data, type, row ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
				},
				{  aTargets: [4],
					   "render": function ( data, type, row ) {
						   if(data=="1"){
							   return "大车";
						   }else if(data=="0"){
							   return "小车";
						   }else{
							   return "";
						   }
						}
				}
			],
			 "order": [
			            [1, "asc"]
			 ] //升序排列    
		};
		function retrieveData( sSource, aoData, fnCallback ) {     
		    var licensePlate =$("#licensePlate").val();
		    var type =$("#type").val();
		    var carColor =$("#carColor").val();
		    var carModel =$("#carModel").val(); 
		    var uacc =$("#uacc").val();
		    var isFree=$("#isFree").val();
		    var carType=$("#carType").val();
		    $.ajax( {     
		        type: "POST",
		        url: sSource,   
		        dataType:"json",  
		        data: "jsonParam="+JSON.stringify(aoData)+"&licensePlate="
		        +licensePlate+"&type="+type+"&carColor="+carColor+"&carModel="+carModel+"&uacc="+uacc+"&isFree="+isFree+"&carType="+carType,  
		        success: function(data) {   
		            fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式  
		            Metronic.init();
		        }     
		    });    
		} 
		var $table = $('#favourable');
		dataTable_config = $.extend({},dataTable_config,$.global_config.table_config);
		oTable = $table.dataTable(dataTable_config);
		$.table_init($table);

	})();       
});

$(function() {
	var dataSource = $("#favourable");
	var dblUrl = pageUrl.caradmission_car_dbl;
	var getCarUrl = pageUrl.caradmission_car_getCar;
	userInfoDblShow($('tbody > tr',dataSource),dblUrl,$('#show'),$('#showForm'));
	$('.tb_del').delUseDialog($('#favourable'),pageUrl.caradmission_car_del,"车辆管理");
	add.init();
	edit.init(dataSource,$("#modify"),getCarUrl);
	addType.init();//add modal初始化
	addColor.init();
	addModule.init();
	UIIdleTimeout.init();
});

var userInfoDblShow = function(dblObj,showUrl,dialog,form){
	dblObj.live('dblclick',function(){
		var id = $(this).find(".checkboxes").val();//获取双击那行checkbox，获取checkbox的值（这里传入的是 id)
		$.ajax({
			url:showUrl+id,
			success:function(data){
				var obj = data;
				var formObj = obj.object;
				var recs = formObj.carModels;
				if(obj.success){
					$(showForm,dialog).fill(formObj);//表单填充插件
					dialog.modal('show');
					carInfo(recs);
				}
				else{
				}
			}
		});
		dialog.modal('show');
	})
}
var initDel=function(dataObj,dialog){
	var ids="";
	dataObj.each(function(i, item){
		var id = $(item).find(".checkboxes").val();
		ids=ids+id+",";
	});
	if(ids.length==0)
		return;
	ids=ids.substring(0,ids.length-1);
	$("#carIds").val(ids);
	dialog.modal('show');
}

//验证表单的方法
function check(form){
		
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		    licensePlate: {
	            maxlength: 7,
	            required: true
	        },
	        type: {
	        	maxlength: 10,
	            required: true
	        },
	        carColor: {
	        	maxlength: 10,
	            required: true
	        },
	        carModel: {
	        	maxlength: 10,
	            required: true
	        },
	        userPK: {
	        	maxlength: 10,
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