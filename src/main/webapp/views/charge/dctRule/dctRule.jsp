<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<jsp:include page="/views/layout/menu.jsp" />
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<jsp:include page="/views/layout/pageheader.jsp" />
			<jsp:include page="./form.jsp" />

			<!-- START POTTLEX demo2------------>
			<div class="portlet box grey-cascade" style="margin-top:15px;border-top:1px solid #b1bdbd">
				<div class="portlet-body"  style="position:relative;">
					<div class="btn-group" role="group" aria-label="..." >
						<a class="btn btn-sm btn-default tb_add" data-toggle="modal" href="#add">
							<span class="glyphicon glyphicon-plus"></span> 新增
						</a>
						<button type="button" class="btn btn-sm btn-default tb_modify ">
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
									<input type="checkbox" class="group-checkable" data-set="#favourable .checkboxes"/>
								</th>
								<th>
									 优惠规则编号
								</th>
								<th>
									 优惠规则名称
								</th>
								<th>
									 类型
								</th>
								<th>
									 优惠形式
								</th>
							</tr>
							</thead>
							<tbody>
							</tbody>
							</table>
				</div>
			</div>
			<!-- END POTTLEX demo2------------>
		</div>

		<!--这个页面的弹窗都在这里 start-->
		<jsp:include page="./add.jsp" />
		<jsp:include page="./edit.jsp" />
		<jsp:include page="./show.jsp" />
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

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script>
jQuery(document).ready(function() {
Metronic.init();
Layout.init(); 
ComponentsDropdowns.init();
park.init();
park.dateTimeInit();
park.toastrInit();
park.timepickerInit();
park.discountInit();
park.modalInit();
park.touchspinInit();
UIIdleTimeout.init();
add.init();
edit.init($('#favourable'),$("#modify"),pageUrl.charge_dctRule_editAndDbl);
dbclick.init($('tbody > tr',$('#favourable')),$('#show'),pageUrl.charge_dctRule_editAndDbl,$('#showForm'));
$('.tb_del').delUseDialog($('#favourable'),pageUrl.charge_dctRule_del,"优惠规则");

//那个表格的初始化
;(function () {
var dataTable_config = {//jquery_datatable插件配置
    //下面设置数据栏目是否可排序
    "bServerSide": true,
    "sAjaxSource": pageUrl.charge_dctRule_list,
    "fnServerData":retrieveData,
    "aoColumns" : [ 
    {
    	"mDataProp" : "id",
    	"sClass":"center",
    }, {
        "mDataProp" : "dctId",
        "sWidth": "100px"
    }, {
        "mDataProp" : "dctName",
        "sWidth": "400px"
    }, {
        "mDataProp" : "type",
        "sWidth": "100px"
    }, {
        "mDataProp" : "dctType",
        "sWidth": "100px"
    }
    ],
    "columnDefs": [{  // 设置默认值
        'orderable': false,
        'targets': [0]
    }, 
    {
		 sDefaultContent: '',
		 aTargets: [ '_all' ]
		},
		{  aTargets: [0],
        "render": function ( data, type, row ) {
   return '<input type="checkbox" class="checkboxes" data-name="'+row.dctName+'" data-state="'+row.enableFlag+'" value="'+data+'"/>';
   }
       // return '<a href="../../'+full.path+'">'+data+'</a>'
    },
    {   aTargets: [4],
    	"render": function ( data, type, full, meta ) {
    		 	var result;
	            if(data==0){
	            	result = "减免金额";
	            }
	            else if(data==1){
	            	result = "折扣";
	            }
	            else if(data==2){
	            	result = "全免";
	            }
	            return result;
	     }
    },
    {   aTargets: [3],
    	"render": function ( data, type, full, meta ) {
    		 	var result;
	            if(data==0){
	            	result = "会员生日";
	            }
	            else if(data==1){
	            	result = "节假日";
	            }
	            else if(data==2){
	            	result = "双休日";
	            }
	            else if(data==3){
	            	result = "规定时间段";
	            }
	            else if(data==4){
	            	result = "规定日期段";
	            }
	            else if(data==5){
	            	result = "续办折扣";
	            }
	            else{
	            	result = "未选择";
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
    //查询条件称加入参数数组     
    var tDctId=$("#tDctId").val();
    var tDctName =$("#tDctName").val();
    var selType =$("#selType").val();
    $.ajax( {     
        type: "POST",
        url: sSource,   
        dataType:"json",  
        data: "jsonParam="+JSON.stringify(aoData)+"&dctId="
        +tDctId+"&dctName="+tDctName+"&type="+selType, //以json格式传递(struts2后台还是以string类型接受),year和month直接作为参数传递。  
        success: function(data) {   
           //$("#url_sortdata").val(data.aaData);  
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

//验证表单的方法
function check(form){
		
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		    	dctId: {
		            maxlength: 30,
		            required: true
		        },
		    	dctName: {
		    		maxlength: 30,
		            required: true
		        },
		        dctPer: {
		            min: 0.01,
		            maxlength: 6,
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