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
				<!-- END PAGE HEADER-->
				<jsp:include page="/views/layout/pageheader.jsp" />
				<jsp:include page="./form.jsp" />

				<!-- START POTTLEX demo2------------>
				<div class="portlet box grey-cascade"
					style="margin-top: 15px; border-top: 1px solid #b1bdbd">
					<div class="portlet-body" style="position:relative;">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-sm btn-default tb_add">
								<span class="glyphicon glyphicon-plus"></span> 会员办理 
							</button>
							<button type="button" class="btn btn-sm  btn-default tb_modify">
								<span class="glyphicon glyphicon-pencil"></span> 修改
							</button>
							<button type="button" class="btn btn-sm btn-default tb_del">
								<span class="glyphicon glyphicon-ban-circle"></span> 删除
							</button>
							<button type="button" class="btn btn-sm btn-default tb_add_black">
								<span class="glyphicon glyphicon-ok-circle"></span> 加入黑名单
							</button>
							<button type="button" class="btn btn-sm btn-default tb_recharge">
								<span class="glyphicon glyphicon-ok-circle"></span> 充值
							</button>
						</div>
						<table class="table table-bordered table-hover" id="favourable">
							<thead>
								<tr>
									<th class="table-checkbox">
										<input type="checkbox" class="group-checkable" data-set="#favourable .checkboxes" />
									</th>
									<th>客户名</th>
									<th>姓名</th>
									<th>状态</th>
									<th>所属客户组</th>
									<th>是否拥有专属车位</th>
									<th>是否黑名单用户</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<!-- END POTTLEX demo2------------>
			</div>
			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="/views/user/user/add.jsp" />
			<jsp:include page="/views/user/user/show.jsp" />
			<jsp:include page="/views/user/user/addBlack.jsp" />
			<jsp:include page="/views/user/user/recharge.jsp" />
			<jsp:include page="/views/user/user/edit.jsp" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/user/form-wizard.js"></script>
 <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script><!--单选按钮和复选按钮的插件--> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<!-- 三级联动  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cityselect/js/jquery.cityselect.js"></script>

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
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>

<!-- END PAGE LEVEL PLUGINS -->
<script>
jQuery(document).ready(function() {
	Metronic.init(); 
	Layout.init();
	FormWizard.init();
	ComponentsDropdowns.init(); //初始化普通下拉框
	park.dateTimeInit();
	park.toastrInit();
	park.init();
	park.modalInit();
	park.touchspinInit();
	park.timepickerInit();
	UIIdleTimeout.init();
	ugroupSelect2.initUGroupNameId();
	ugroupSelect2.initMemberUGroupNameId();
	ugroupSelect2.initLongMemberUgroupNameId();
	userSelect2.initUserUaccUacc();
	userSelect2.initUserNameName();
	carSelect2.initLicensePlateLicensePlate();
	carSelect2.initCarType();
	carSelect2.initCarColor();
	carSelect2.initCarModule();
	(function () {
		var dataTable_config = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.user_user_list,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
			    {
			    	"mDataProp" :"id",
			    	"sClass":"center",
			    }, {
			        "mDataProp" : "uacc",
			        "sWidth": "100px"
			    },  {
			        "mDataProp" : "name",
			        'orderable': false,
			        "sWidth": "100px"
			    },   {
			        "mDataProp" : "state",
			        'orderable': false,
			        "sWidth": "100px"
			    }, {
			        "mDataProp" : "ugroupName",
			        'orderable': false,
			        "sWidth": "100px"
			    },{
			        "mDataProp" : "hasParkingSpaces",
			        'orderable': false,
			        "sWidth": "100px"
			    },{
			        "mDataProp" : "isBlack",
			        'orderable': false,
			        "sWidth": "100px"
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
				{  aTargets: [0], "render": function ( data, type, full, meta ) 
					{
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
				},
				{   aTargets: [3], "render": function ( data, type, full, meta ) 
					{
						if(data==0){
							return "待审批";
						}else if(data==1){
							return "审批通过";
						}else if(data==2){
							return "审批未通过"
						}else if(data==3){
							return "暂停";
						}
				     }
			    },
				{   aTargets: [5], "render": function ( data, type, full, meta ) 
					{
					 	if(data==false){
			            	return "否";
			            }
			            else if(data==true){
			            	return "是";
			            }  
				     }
			    },
			    {   aTargets: [6], "render": function ( data, type, full, meta ) 
					{
					 	if(data==false){
			            	return "否";
			            }
			            else if(data==true){
			            	return "是";
			            }  
				     }
			    }
			],
			 "order": [
			            [1, "asc"]
			 ] //升序排列    
		};

		function retrieveData( sSource, aoData, fnCallback ) {     
		    //查询条件称加入参数数组      
		    var uacc =$("#uacc").val();
		    var name =$("#name").val();
		    var state=$("#state").val();
		    var ugroupName =$("#ugroupName").val();
		    var licensePlate =$("#licensePlate").val();
		    $.ajax( {     
		        type: "POST",
		        url: sSource,   
		        dataType:"json",  
		        data: "jsonParam="+JSON.stringify(aoData)+ "&uacc="+uacc+"&name="+name+"&state="+state+"&ugroupName="+ugroupName+"&licensePlate="+licensePlate,  
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
	var editAndDblUrl = pageUrl.user_user_editAndDbl;
	var rechargeUrl = pageUrl.user_user_recharge;
	add.init();
	add_car_init.init();
	edit_car_init.init();
	$('.tb_del').delUseDialog(dataSource,pageUrl.user_user_del,"角色管理");
	recharge.init(dataSource,$("#recharge"),rechargeUrl);
	edit.init(dataSource,$("#modify"),editAndDblUrl);
	addBlack.init(dataSource,$("#black"),editAndDblUrl);
	userInfoDblShow($('tbody > tr',dataSource),editAndDblUrl,$('#show'),$('#showForm'));
	$('.modal').on('hidden.bs.modal', function () {//在关闭模态框时触发两次form-wizard回退事件，回到第一步。
		$('.button-previous').trigger('click');
        $('.button-previous').trigger('click');
	});
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
					userInfo(recs);
				}
				else{
				}
			}
		});
		dialog.modal('show');
	})
}

//验证表单的方法
function check(form){
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		    	endTime: {
		            required: true
		        },
		        needFee: {
		        	digits: true,
		        	min: 1,
		            required: true
		        },
		        /*licensePlate: {
		            maxlength: 7,
		            required: true
		        },
		        licensePlateType: {
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
		        },*/
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