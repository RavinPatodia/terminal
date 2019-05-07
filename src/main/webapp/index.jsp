<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="../views/include/taglib.jsp"%>

<html lang="utf-8">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>

<link href="<%=request.getContextPath()%>/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<jsp:include page="/views/include/globalCss.jsp" />
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="<%=request.getContextPath()%>/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="<%=request.getContextPath()%>/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
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
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/charts-amcharts.js"></script>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
	<jsp:include page="/views/layout/topBar.jsp" />
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<jsp:include page="/views/layout/menu.jsp" />
		<div class="page-content-wrapper">
			<div class="page-content">
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat blue-madison">
							<div class="visual">
								<i class="icon-user-follow "></i>
							</div>
							<div class="details">
								<div class="number">
									 账户充值
								</div>
								<div class="desc">
									 Recharge
								</div>
							</div>
							<a class="more" href="/park/user/user">
							查看详情<i class="m-icon-swapright m-icon-white"></i>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat red-intense">
							<div class="visual">
								<i class="fa fa-car "></i>
							</div>
							<div class="details">
								<div class="number">
									 车位租赁
								</div>
								<div class="desc">
									 Carport Rent
								</div>
							</div>
							<a class="more" href="/park/pSp/pSp">
							查看详情<i class="m-icon-swapright m-icon-white"></i>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat green-haze">
							<div class="visual">
								<i class="glyphicon glyphicon-transfer "></i>
							</div>
							<div class="details">
								<div class="number">
									 出入管理
								</div>
								<div class="desc">
									 Access-control
								</div>
							</div>
							<a class="more" href="/park/caradmission/enter">
							查看详情<i class="m-icon-swapright m-icon-white"></i>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat purple-plum">
							<div class="visual">
								<i class="icon-settings "></i>
							</div>
							<div class="details">
								<div class="number">
									 车位配置
								</div>
								<div class="desc">
									 Carport Configur
								</div>
							</div>
							<a class="more" href="/park/pSp/arrange">
							查看详情<i class="m-icon-swapright m-icon-white"></i>
							</a>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
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
							</div>
							<div class="portlet-body">
								<div id="chart_3" class="chart" style="height: 380px;width: 100%;">
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
	<div class="page-footer">
		<div class="page-footer-inner">
			 杭州朗米科技有限公司（LANGMY Technology Co.,Ltd）版权所有 Copyright © 2014-2020 ICP证： 浙ICP备1403970号
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<jsp:include page="/views/include/globalJs.jsp" />
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="<%=request.getContextPath()%>/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/ckeditor/ckeditor.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
   Demo.init(); // init demo features 
   Index.init();   
   Index.initDashboardDaterange();
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
   UIIdleTimeout.init();
   search();
});
var search=function(){
	 $.ajax({
			url: "/park/report/charRecOfMonth/getMaxYear",   
		    dataType:"json",  
		    data:"", 
			type: 'POST',
			success: function(data) { 
				if(data==null){
					$('#title').append('<span id="title" class="caption-subject bold uppercase font-green-haze">没有收费记录</span>');
				}else{
					$('#title').append('<span id="title" class="caption-subject bold uppercase font-green-haze">'+data+'年月收费统计</span>');
				}
				actualPayOfMouth(data)
			}
		});
	
}
var actualPayOfMouth=function(year){
	 $.ajax({
		url: "/park/report/charRec/getActualPayOfMouth/"+year,   
	    dataType:"json",  
	    data:"", 
		type: 'POST',
		success: function(data) { 
			initChart(data);
		}
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
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>