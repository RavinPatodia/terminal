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

				<!-- START POTTLEX demo2------------>
				<div class="portlet box grey-cascade" style="margin-top: 15px; border-top: 1px solid #b1bdbd">
					<div class="row">
						<div class="portlet light bordered">
							<div class="portlet-title">
								<div id="title" class="caption">
									<i class="icon-bar-chart font-green-haze"></i>
								</div>
								<div class="tools">
									<a href="javascript:;" class="fullscreen">
									</a>
								</div>
								<div class="actions">
									<div class="btn-group pull-left">
										<a href="" class="btn grey-steel btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
											年份  <span class="fa fa-angle-down"></span>
										</a>
										<ul class="dropdown-menu pull-right menu" id="menu">
										</ul>
									</div>
								</div>
							</div>
							<div class="portlet-body">
								<div id="chart_3" class="chart" style="height: 380px;width: 100%;">
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END POTTLEX demo2------------>
			</div>

			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="/views/report/charRec/del.jsp" />
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

<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script>
jQuery(document).ready(function() {    
		// initiate layout and plugins
		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
		ComponentsDropdowns.init(); //初始化普通下拉框
		//FormiCheck.init();
		park.dateTimeInit();
		park.toastrInit();
		park.modalInit();
	    search();
	    bindYear();
	    UIIdleTimeout.init();
	});
var search=function(){
	var max="${maxYear}";
	var min="${minYear}";
	actualPayOfMouth(max);
	if(max==""){
		$('#title').append('<span id="title" class="caption-subject bold uppercase font-green-haze">没有收费记录</span>');
	}else{
		$('#title').append('<span id="title" class="caption-subject bold uppercase font-green-haze">'+max+'年月收费统计</span>');
	}
	$("#menu").append('<li><a class="years">  ' + max + '  <span class="label label-sm label-success">  current </span> </a></li>');
	for(var year = max-1; year> min-1; year--){
		$("#menu").append('<li><a class="years">  ' + year + '  <span class="label label-sm label-default"> past </span></a></li>');
	}
}
var actualPayOfMouth=function(year){
	 $.ajax({
		url: pageUrl.report_charRec_search+year,   
	    dataType:"json",  
	    data:"", 
		type: 'POST',
		success: function(data) { 
			initChart(data);
		}
	});
}
var bindYear = function(){
	$.each($(".years"),function(){
		var yearAllString = $(this).text();
		var year = yearAllString.substring(0,6);
		$(this).on('click',function(){
			actualPayOfMouth(year);
		});
	});
}
var initChart = function(data) {
    var chart = AmCharts.makeChart("chart_3", {
        "type": "serial",
        "theme": "light",
        "fontFamily": 'Open Sans',            
        "color":    '#888888',
        "pathToImages": Metronic.getGlobalPluginsPath() + "amcharts/amcharts/images/",
        "dataProvider":$.parseJSON(data),
        "graphs": [{
            "bullet": "square",
            "bulletBorderAlpha": 1,
            "bulletBorderThickness": 1,
            "fillAlphas": 0.3,
            "fillColorsField": "lineColor",
            "legendValueText": "[[value]]",
            "lineColorField": "lineColor",
            "title": "bPay",
            "valueField": "bPay"
        }],
        "chartScrollbar": {},
        "chartCursor": {
            "categoryBalloonDateFormat": "YYYY MMM DD",
            "cursorAlpha": 0,
            "zoomable": false
        },
        "dataDateFormat": "YYYY-MM",
        "categoryField": "aMonth",
        "categoryAxis": {
            "dateFormats": [{
                "period": "DD",
                "format": "DD"
            }, {
                "period": "WW",
                "format": "MMM DD"
            }, {
                "period": "MM",
                "format": "MMM"
            }, {
                "period": "YYYY",
                "format": "YYYY"
            }],
            "parseDates": true,
            "autoGridCount": false,
            "axisColor": "#555555",
            "gridAlpha": 0,
            "gridCount": 50
        }
    });
    
}	
</script>
<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>