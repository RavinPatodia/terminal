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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
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
						<table class="table table-bordered table-hover" id="favourable">
							<thead>
								<tr>
									<th class="table-checkbox"><input type="checkbox"
										class="group-checkable" data-set="#favourable .checkboxes" />
									</th>
									<th>操作员</th>
									<th>车牌号码</th>
									<th>账户余额</th>
									<th>入车道</th>
									<th>入卡口</th>
									<th>入场时间</th>
									<th>出车道</th>
									<th>出卡口</th>
									<th>出场时间</th>
									<th>应付金额</th>
									<th>停车场</th>
									<th>是否已付款</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/views/include/globalJs.jsp" />
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script>
jQuery(document).ready(function() {
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
ComponentsDropdowns.init();
FormiCheck.init();
park.init();
park.dateTimeInit();
initDrop();
park.modalInit();
park.datetimepickerInit();
deviceSelect2.initInDrivewaySelect();
deviceSelect2.initOutDrivewaySelect();
UIIdleTimeout.init();
//那个表格的初始化
(function () {
var dataTable_config = {//jquery_datatable插件的默认参数配置
    //下面设置数据栏目是否可排序
    "bServerSide": true,
    "sAjaxSource": pageUrl.report_operLaundryList_list,
    "fnServerData":retrieveData,
    "aoColumns" : [ 
    {
    	"mDataProp" :"id",
    	"sClass":"center",
    }, {
        "mDataProp" : "licensePlate",
        'orderable': false,
        "sWidth": "120px"
    }, {
        "mDataProp" : "inDriveWayName",
        'orderable': false,
        "sWidth": "100px"
    }, {
        "mDataProp" : "bayonetEntranceName",
        'orderable': false,
        "sWidth": "80px"
    },{
        "mDataProp" : "inTime",
        "sWidth": "150px"
    },{
        "mDataProp" : "outDriveWayName",
        'orderable': false,
        "sWidth": "100px"
    },{
        "mDataProp" : "bayonetExitName",
        'orderable': false,
        "sWidth": "80px"
    },{
        "mDataProp" : "outTime",
        "sWidth": "150px"
    },{
        "mDataProp" : "fee",
    },{
        "mDataProp" : "parkName",
        'orderable': false,
        "sWidth": "100px"
    },{
        "mDataProp" : "isPay",
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
	   "render": function ( data, type, full, meta ) {
	   return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
	   }
	},
    {   aTargets: [10],
    	"render": function ( data, type, full, meta ) {
	            if(data==true){
	            	return "已付";
	            }
	            else if(data==false){
	            	return "未付";
	            }
	     }
    }
    ],
    "order": [
	            [4, "asc"]
	 ] //升序排列    
};

function retrieveData( sSource, aoData, fnCallback ) { 
    var licensePlate =$("#licensePlate").val();
    var licenseType =$("#licenseType").val();
    var isPayInt =$("#isPayInt").val();
    var inDriveWayPK =$("#inDriveWayPK").val();
    var outDriveWayPK =$("#outDriveWayPK").val();
    var inRecoWay =$("#inRecoWay").val();
    var outRecoWay =$("#outRecoWay").val();
    var dtLastEnableFrom =$("#dtLastEnableFrom").val();
    var dtLastEnableTo =$("#dtLastEnableTo").val();
    var dtLastDisableFrom =$("#dtLastDisableFrom").val();
    var dtLastDisableTo =$("#dtLastDisableTo").val(); 
    $.ajax( {     
        type: "POST",
        url: sSource,   
        dataType:"json",  
        data: "jsonParam="+JSON.stringify(aoData)+"&licensePlate="+licensePlate+"&licenseType="+licenseType+
        "&isPayInt="+isPayInt+"&inDriveWayPK="+inDriveWayPK+"+&outDriveWayPK="+outDriveWayPK+"&inRecoWay="+inRecoWay+ "&outRecoWay="+outRecoWay+
        "&bsm.dtLastEnableFrom="+dtLastEnableFrom+"&bsm.dtLastEnableTo="+dtLastEnableTo+"&bsm.dtLastDisableFrom="
        +dtLastDisableFrom+"&bsm.dtLastDisableTo="+dtLastDisableTo, //以json格式传递(struts2后台还是以string类型接受),year和month直接作为参数传递。  
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
	$('#styleToggle').toggleStyle('style_color');
})();       
});
</script>
<script type="text/javascript">
$(function(){
	var dataSource = $("#favourable");
	var editAndDblUrl = "${editAndDblUrl}";
	$('.tb_del').del(dataSource,$("#del"));
	edit.init(dataSource,$("#modify"),editAndDblUrl);
	admissRecDblClick($('tbody > tr',$('#favourable')),$('#show'),editAndDblUrl,$('#showForm'));
});
	
var admissRecDblClick = function(obj,dialog,showUrl,showForm){
	obj.live('dblclick',function(){
		var id = $(this).find(".checkboxes").val();//获取双击那行checkbox，获取checkbox的值（这里传入的是 id)
		$.ajax({
			url:showUrl+id,
			success:function(data){
				var obj = data;
				var formObj = obj.object;
				var recs = formObj.parkingRecModels;
				if(obj.success){
					$(showForm,dialog).fill(formObj);//表单填充插件
					dialog.modal('show');
					parkingRecs(recs);
				}
				else{
				}
			}
		});
	})
};
</script>
</body>
</html>