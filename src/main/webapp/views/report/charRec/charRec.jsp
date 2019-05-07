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
					style=" border-top: 1px solid #b1bdbd">
					<div class="portlet-body" >
						<ul class="nav nav-tabs">
									<li  class="active">
										<a href="#tab_1" data-toggle="tab">统计图 </a>
									</li>
									<li>
										<a href="#tab_2" data-toggle="tab">记录 </a>
									</li>
						</ul>
						<div class="tab-content">
								<div class="tab-pane fade active in" id="tab_1">
									<div class="portlet light bordered">
										<div class="portlet-title">
											<div class="caption">
												<i class="icon-bar-chart font-green-haze"></i>
												<span class="caption-subject bold uppercase font-green-haze"> 操作员结账记录统计图</span>
												<span class="caption-helper"></span>
											</div>
											<div class="tools">
												<a href="javascript:;" class="fullscreen tb_fullScreen">
												</a>
											</div>
											<div class="actions">
												<div class="btn-group pull-left">
													<a class="btn btn-sm btn-default tb_export">
														<span class="glyphicon glyphicon-export"></span> 导出Excel
													</a>
												</div>
											</div>
										</div>
										<div class="portlet-body">
											<div id="chart_1" class="chart" style="height: 300px;"></div>
											<div class="well margin-top-20">
												<div class="row">
													<div class="col-sm-3">
														<label class="text-left">弧半径:</label>
														<input class="chart_1_chart_input" data-property="topRadius" type="range" min="0" max="1.5" value="1" step="0.01"/>
													</div>
													<div class="col-sm-3">
														<label class="text-left">角度:</label>
														<input class="chart_1_chart_input" data-property="angle" type="range" min="0" max="89" value="30" step="1"/>
													</div>
													<div class="col-sm-3">
														<label class="text-left">深度:</label>
														<input class="chart_1_chart_input" data-property="depth3D" type="range" min="1" max="120" value="40" step="1"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="tab_2" style="position:relative;">
									<div class="btn-group" role="group" aria-label="..." style="margin-top: 10px;">
										<a class="btn btn-sm btn-default tb_export_data" href="">
											<span class="glyphicon glyphicon-export"></span> 导出Excel
										</a>
									</div>
									<table class="table table-bordered table-hover" id="favourable">
										<thead>
											<tr>
												<th class="table-checkbox">
													<input type="checkbox" class="group-checkable" data-set="#favourable .checkboxes" />
												</th>
												<th>操作员账户名</th>
												<th>操作员姓名</th>
												<th>实付金额</th>
												<th>应付金额</th>
												<th>结账时间</th>
											</tr>
										</thead>
									</table>
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
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>
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
	operSelect2.initOperNameId();
	 UIIdleTimeout.init();
	 fullscreen();
	//那个表格的初始化
	(function () {
		var dataTable_config = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
		    "sAjaxSource": pageUrl.report_charRec_list,
		    "fnServerData":retrieveData,
		    "aoColumns" : [ 
			    {
			    	"mDataProp" :"id",
			    	'orderable': false,
			    	"sClass":"center",
			    }, 
			    {
			        "mDataProp" : "operName",
			        'orderable': false,
			        "sWidth": "120px"
			    }, {
			        "mDataProp" : "name",
			        'orderable': false,
			        "sWidth": "120px"
			    }, {
			        "mDataProp" : "actualPay",
			        "sWidth": "100px"
			    }, {
			        "mDataProp" : "amoutPay",
			        "sWidth": "100px"
			    }, {
			        "mDataProp" : "payTime",
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
				   "render": function ( data, type, full, meta ) {
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
				}
			],
			 "order": [
			            [3, "asc"]
			 ] //升序排列    
		};

		function retrieveData( sSource, aoData, fnCallback ) {     
		    //查询条件称加入参数数组     
		    var operId =$("#operId").val();
		    var startTime =$("#startTime").val();
		    var endTime =$("#endTime").val();
		    $.ajax( {     
		        type: "POST",
		        url: sSource,   
		        dataType:"json",  
		        data: "jsonParam="+JSON.stringify(aoData)+"&operId="
		        +operId+"&startTime="+startTime+"&endTime="+endTime, 
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
	$('.tb_del').del(dataSource,$("#del"));
	//初始化柱状图
	search();
	$('#searchBtn').bind('click', function() {	
		search();	
	});
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		var tabText = $(e.target).text(); 
		if($.trim(tabText) == $.trim("记录")){
			$("#operater").show();
			$("#listDiv").show();
			$("#searchDiv").hide();
		}else{
			$("#operater").hide();
			$("#listDiv").hide();
			$("#searchDiv").show();
		}
		clearForm();
	 });
	//导出收费记录统计数据
	$('.tb_export').bind('click',function(){
		 var startTime =$("#startTime").val();
		 var endTime =$("#endTime").val();
		 $(this).attr('href',"/park/report/charRec/export?startTime="+startTime+"&endTime="+endTime);
	});
	//导出收费记录
	$('.tb_export_data').bind('click',function(){
		 var startTime =$("#startTime").val();
		 var endTime =$("#endTime").val();
		 var operId=$('#operId').val();
		 $(this).attr('href',"/park/report/charRec/exportData?startTime="+startTime+"&endTime="+endTime+"&operId="+operId);
	});
});
var fullscreen = function(){
        // handle portlet fullscreen
        $('.tb_fullScreen').on('click',function(e) {
            e.preventDefault();
            var portlet = $(this).closest(".portlet");
            if (portlet.hasClass('portlet-fullscreen')) {
                $(this).removeClass('on');
                portlet.removeClass('portlet-fullscreen');
                $('body').removeClass('page-portlet-fullscreen');
                portlet.children('.portlet-body').css('height', 'auto');
            } else {
                var height = Metronic.getViewPort().height -
                    portlet.children('.portlet-title').outerHeight() -
                    parseInt(portlet.children('.portlet-body').css('padding-top')) -
                    parseInt(portlet.children('.portlet-body').css('padding-bottom'));

                $(this).addClass('on');
                portlet.addClass('portlet-fullscreen');
                $('body').addClass('page-portlet-fullscreen');
                portlet.children('.portlet-body').css('height', height);
            }
        });
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
	$("#charRecIds").val(ids);
	dialog.modal('show');
}

//AmCharts的默认参数配置	
var search=function(){
	 var startTime =$("#startTime").val();
	 var endTime =$("#endTime").val();
	 $.ajax({
		url: pageUrl.report_charRec_search,   
	    dataType:"json",  
	    data: "jsonParam="+"&startTime="+startTime+"&endTime="+endTime, 
		type: 'POST',
		success: function(data) { 
			initChart(data);
		}
	});

}

var initChart = function(data) {
  var chart = AmCharts.makeChart("chart_1", {
      "theme": "light",
      "type": "serial",
      "startDuration": 2,
      "fontFamily": 'Open Sans',
      "color":    '#888',
      "dataProvider":$.parseJSON(data) ,
      "valueAxes": [{
          "position": "left",
          "axisAlpha": 0,
          "gridAlpha": 0
      }],
      "graphs": [{
          "balloonText": "[[category]]: <b>[[value]]</b>",
          "colorField": "zColor",
          "fillAlphas": 0.85,
          "lineAlpha": 0.1,
          "type": "column",
          "topRadius": 1,
          "valueField": "bSum"
      }],
      "depth3D": 40,
      "angle": 30,
      "chartCursor": {
          "categoryBalloonEnabled": false,
          "cursorAlpha": 0,
          "zoomable": false
      },
      "categoryField": "aUser",
      "categoryAxis": {
          "gridPosition": "start",
          "axisAlpha": 0,
          "gridAlpha": 0

      },
      "exportConfig": {
          "menuTop": "20px",
          "menuRight": "20px",
          "menuItems": [{
              "icon": '/lib/3/images/export.png',
              "format": 'png'
          }]
      }
  }, 0);

  jQuery('.chart_1_chart_input').off().on('input change', function() {
      var property = jQuery(this).data('property');
      var target = chart;
      chart.startDuration = 0;

      if (property == 'topRadius') {
          target = chart.graphs[0];
      }
      target[property] = this.value;
      chart.validateNow();
  });
}
</script>
<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>