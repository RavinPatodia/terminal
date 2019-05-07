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
					<div class="portlet-body"  style="position:relative;">
						<div class="btn-group" role="group" aria-label="..." >
						</div>
						<div style="margin-top: 20px;">
							<table class="table table-bordered table-hover" id="favourable">
								<thead>
									<tr>
										<th class="table-checkbox"><input type="checkbox"
											class="group-checkable" data-set="#favourable .checkboxes" />
										</th>
										<th>车牌号码</th>
										<th>车牌类型</th>
										<th>付款方式</th>
										<th>付款时间</th>
										<th>优惠规则组名称</th>
										<th>应付金额(元)</th>
										<th>实付金额(元)</th>
										<th>优惠金额(元)</th>
										<th>删除时间</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- END POTTLEX demo2------------>
			</div>

			<!--这个页面的弹窗都在这里 start-->
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
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>

<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script>
jQuery(document).ready(function() {
// initiate layout and plugins
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
ComponentsDropdowns.init();
park.init();
park.dateTimeInit();
UIIdleTimeout.init();

//那个表格的初始化
;(function () {
var dataTable_config = {//jquery_datatable插件的默认参数配置
    //下面设置数据栏目是否可排序
    "bServerSide": true,
    "sAjaxSource": pageUrl.charge_delChargeRec_list,
    "fnServerData":retrieveData,
    "aoColumns" : [ 
    {
    	"mDataProp" :"id",
    	"sClass":"center",
    }, {
        "mDataProp" : "carModel.licensePlate",
        'orderable': false,
        "sWidth": "100px"
    }, {
        "mDataProp" : "carModel.type",
        'orderable': false,
        "sWidth": "100px"
    }, {
        "mDataProp" : "payType",
        "sWidth": "50px"
    }, {
        "mDataProp" : "payTime",
        "sWidth": "150px"
    },{
        "mDataProp" : "dctRuleGroupModel.name",
        'orderable': false,
        "sWidth": "100px"
    },{
        "mDataProp" : "amoutPay",
        "sWidth": "50px"
    },{
        "mDataProp" : "actualPay",
        "sWidth": "50px"
    },{
        "mDataProp" : "dctPay",
        "sWidth": "50px"
    }
    ,{
        "mDataProp" : "delTime",
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
	{   aTargets: [3],
    	"render": function ( data, type, full, meta ) {
    		 	var result;
	            if(data==0){
	            	result = "账户支付";
	            }
	            else if(data==1){
	            	result = "现金支付";
	            }
	            else{
	            	result = "缴费机支付";
	            }
	            return result;
	     }
    },
    ]
    
};

function retrieveData( sSource, aoData, fnCallback ) {     
    //查询条件称加入参数数组     
    //var rentRuleId =document.getElementById('rentRuleId').value;
    var tLicensePlate=$("#tLicensePlate").val();
    var sectLicensePlateType =$("#sectLicensePlateType").val();
    var sectPayType =$("#sectPayType").val();
    var dtPayTimeFrom =$("#dtPayTimeFrom").val();
    var dtPayTimeTo =$("#dtPayTimeTo").val();
    var tDctRuleGroupName =$("#tDctRuleGroupName").val();
    var dtDelTimeFrom =$("#dtDelTimeFrom").val();
	var dtDelTimeTo =$("#dtDelTimeTo").val();
    $.ajax( {     
        type: "POST",
        url: sSource,   
        dataType:"json",  
        data: "jsonParam="+JSON.stringify(aoData)+"&carModel.licensePlate="+tLicensePlate+"&carModel.type="+sectLicensePlateType
        +"&payType="+sectPayType+"&payTimeFrom="+dtPayTimeFrom+"&payTimeTo="+dtPayTimeTo+"&dctRuleGroupModel.name="+tDctRuleGroupName+
        "&delTimeFrom="+dtDelTimeFrom+"&delTimeTo="+dtDelTimeTo,
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
</script>

<script type="text/javascript">

function clearFun(){
	$("#searchForm input").val('');
	$("#searchForm .bs-select").val('-1').trigger("change");
	oTable.fnDraw(); 
};

$(function(){
	var dataSource = $("#favourable");
	var editAndDblUrl =  pageUrl.charge_delChargeRec_editAndDbl;
	dbclick.init($('tbody > tr',$('#favourable')),$('#show'),editAndDblUrl,$('#showForm'));
});
	
</script>

<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>