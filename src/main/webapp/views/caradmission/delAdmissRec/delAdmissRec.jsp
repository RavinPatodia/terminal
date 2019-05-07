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
				<jsp:include page="/views/layout/pageheader.jsp" />
				<jsp:include page="./form.jsp" />

				<!-- START POTTLEX demo2------------>
				<div class="portlet box grey-cascade"
					style="margin-top: 15px; border-top: 1px solid #b1bdbd">
					<div class="portlet-body">
						<div class="btn-group" role="group" aria-label="..."
							style="margin-bottom: 15px;">
							<button type="button" class="btn btn-default tb_del">
								<span class="glyphicon glyphicon-ban-circle"></span>彻底删除
							</button>
							<button type="button" class="btn btn-default tb_recover ">
								<span class="glyphicon glyphicon-pencil"></span> 恢复
							</button>
							
						</div>
						<table class="table table-bordered table-hover" id="favourable">
							<thead>
								<tr>
									<th class="table-checkbox"><input type="checkbox"
										class="group-checkable" data-set="#favourable .checkboxes" />
									</th>
									<th>车牌号码</th>
									<th>车牌类型</th>
									<th>入口</th>
									<th>入场时间</th>
									<th>出口</th>
									<th>出场时间</th>
									<th>应付金额</th>
									<th>是否已付款</th>
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
			<jsp:include page="/views/caradmission/delAdmissRec/del.jsp" />
			<jsp:include page="/views/caradmission/delAdmissRec/show.jsp" />
			<jsp:include page="/views/caradmission/delAdmissRec/recover.jsp" />
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
<script>
jQuery(document).ready(function() {
// initiate layout and plugins
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
ComponentsDropdowns.init();
FormiCheck.init();
park.init();
park.dateTimeInit();
park.modalInit();
initDrop();
UIIdleTimeout.init();
//那个表格的初始化
(function () {
var dataTable_config = {//jquery_datatable插件的默认参数配置
    //下面设置数据栏目是否可排序
    "bServerSide": true,
    "sAjaxSource": "${listUrl}",
    "fnServerData":retrieveData,
    "aoColumns" : [ 
    {
    	"mDataProp" :"id",
    	"sClass":"center",
    }, {
        "mDataProp" : "licensePlate",
    }, {
        "mDataProp" : "licenseType",
        'orderable': false,
        "sWidth": "100px"
    }, {
        "mDataProp" : "bayonetEntranceName",
        'orderable': false,
        "sWidth": "100px"
    }, {
        "mDataProp" : "inTime",
        "sWidth": "150px"
    },{
        "mDataProp" : "bayonetExitName",
        'orderable': false,
        "sWidth": "100px"
    },{
        "mDataProp" : "outTime",
        "sWidth": "150px"
    },{
        "mDataProp" : "fee",
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
    {   aTargets: [8],
    	"render": function ( data, type, full, meta ) {
    		 	var result;
	            if(data==true){
	            	result = "已付";
	            }
	            else{
	            	result = "未付";
	            }
	            return result;
	     }
    }
    ]
    
};

function retrieveData( sSource, aoData, fnCallback ) { 
	
    //查询条件称加入参数数组     
    var licensePlate =$("#licensePlate").val();
    var licenseType =$("#licenseType").val();
    var isPay =$("#isPay").val();
    var bayonetEntrancePK =$("#bayonetEntrancePK").val();
    var bayonetExitPK =$("#bayonetExitPK").val();
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
        "&isPay="+isPay+"&bayonetEntrancePK="+bayonetEntrancePK+"+&bayonetExitPK="+bayonetExitPK+"&inRecoWay="+inRecoWay+ "&outRecoWay="+outRecoWay+
        "&bsm.dtLastEnableFrom="+dtLastEnableFrom+"&bsm.dtLastEnableTo="+dtLastEnableTo+"&bsm.dtLastDisableFrom="
        +dtLastDisableFrom+"&bsm.dtLastDisableTo="+dtLastDisableTo, //以json格式传递(struts2后台还是以string类型接受),year和month直接作为参数传递。  
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
	$('#styleToggle').toggleStyle('style_color');
})();       
});
</script>
<script type="text/javascript">
	$(function(){
		var dataSource = $("#favourable");
		var editAndDblUrl = "${editAndDblUrl}";
		$('.tb_del').del(dataSource,$("#del"));
		$('.tb_recover').recover(dataSource,$("#recover"));
		dbclick.init($('tbody > tr',$('#favourable')),$('#show'),editAndDblUrl,$('#showForm'));
	});
	//删除
	var initDel=function(dataObj,dialog){
		var ids="";
		dataObj.each(function(i, item){
			var id = $(item).find(".checkboxes").val();
			ids=ids+id+",";
		});
		if(ids.length==0)
			return;
		ids=ids.substring(0,ids.length-1);
		$("#delIds").val(ids);
		dialog.modal('show');
	}
	 
	var initRecover=function(dataObj,dialog){
		var ids="";
		dataObj.each(function(i, item){
			var id = $(item).find(".checkboxes").val();
			ids=ids+id+",";
		});
		if(ids.length==0)
			return;
		ids=ids.substring(0,ids.length-1);
		$("#recoversIds").val(ids);
		dialog.modal('show');
	}
	//清除
	function clearFun(){
		$("#searchForm input").val('');
		$("#searchForm .select2").val('').trigger("change");
		$("#searchForm .bs-select").val('-1').trigger("change");
		initDrop();
		oTable.fnDraw();
	};
	//初始化下拉框
	var initDrop=function(){
		$('.entrancePK').select2({
			placeholder: "搜索所有入口",
	        allowClear: true,
	        formatNoMatches: "没有匹配的入口",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "${entranceUrl}",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {};
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
		
		$('.car_type').select2({
			placeholder: "搜索车牌类型",
	        allowClear: true,
	        formatNoMatches: "没有匹配的车牌类型",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "${typeUrl}",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {};
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        }, 
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
		
		$('.exitPK').select2({
			placeholder: "搜索所有出口",
	        allowClear: true,
	        formatNoMatches: "没有匹配出口",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "${exitUrl}",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {};
	            },
	            results: function (data, page) {
	                return {results: data};
	            }
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	            return m;
	        }
	    });
	}
	
</script>

<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>