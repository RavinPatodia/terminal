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
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed page-sidebar-closed-hide-logo" style="height:800px;">
	<jsp:include page="/views/layout/topBar.jsp" />
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<jsp:include page="/views/layout/menu.jsp" />
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
			<div class="monitor_control_panel">
				<div class="m_main_pl">
					<div class="m_video" ></div>
					<div class="m_footer">
						<div class="btn-left">
							<a href="javascript:void(0)" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-fullscreen"></span></a>
						</div>
						<div class="btn-right">
							<a href="javascript:void(0)" class="btn btn-primary btn-sm"><span class="fa fa-th"></span></a>
							<a href="javascript:void(0)" class="btn btn-primary btn-sm"><span class="fa fa-th-large"></span></a>
						</div>
					</div>
				</div>
				<div class="m_nav_center">
					<div class="m_nav_center_text">
						<label>车位</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>余位</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>临时</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>长期</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>储值</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>本班应收款合计</label>
						<input type="text" readonly="readonly" style="width: 43%;" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>本班实收款合计</label>
						<input type="text" readonly="readonly" style="width: 43%;" class="form-control normal-input input-sm" name="">
					</div>
					<div class="text_box">
						<label>收费信息</label>
					</div>
					<div class="m_nav_center_text">
						<label>应收车费</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>实收车费</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
					<div class="m_nav_center_text">
						<label>停车时间</label>
						<input type="text" readonly="readonly" class="form-control normal-input input-sm" name="">
					</div>
				</div>
				<div class="m_nav">
				    <div class="text_left">
					    <form id="enterForm">
					    	<ul class="nav nav-pills">
								<li class="active">
									<a href="#tab_1_1"  data-toggle="tab" style="height:23px; font-size: 12px; padding-top:2px;">
									入场图片 </a>
								</li>
								<li>
									<a href="#tab_1_2" data-toggle="tab" style="height:23px; font-size: 12px; padding-top:2px;">
									车辆原始图片 </a>
								</li>
							</ul>
							<div class="pic_box tab-content">
								<div class="tab-pane fade active in" id="tab_1_1">
									<img src="" id="enter_pic" alt="" width="233px" height="182px">
								</div>
								<div class="tab-pane fade" id="tab_1_2">
									<img src="../images/noEnterPic.jpg" alt="" width="233px" height="182px">
								</div>
							</div>
					    	<div class="text_box">
									<label>入场信息</label>
									<a href="javascript:void(0)" class="btn btn-primary btn-sm">校对</a>
							</div>
							<div class="m_nav_left">
								<div class="input_style">
									<label>时&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp间：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.inTime"/>
								</div>
								<div class="input_style">
									<label>车牌号码：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.licensePlate"/>
								</div>
								<div class="input_style">
									<label>车牌颜色：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.carColor"/>
								</div>
								<div class="input_style">
									<label>车牌类型：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.type"/>
								</div>
								<div class="input_style">
									<label>车辆类型：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.type" />
								</div>
								<div class="input_style">
									<label>客&nbsp&nbsp户&nbsp&nbsp名：</label><p class="form-control-static" fill="obj.uacc" ></p>
									<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.uacc"/>
								</div>
								<div class="input_style">
									<label>客户类型：</label><p class="form-control-static" fill="obj.type" data-type="int" data-min="0" data-max="3" data-num0="临时用户" data-num1="" data-num2="普通会员" data-num3="长期会员"  ></p>
									<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.type"/>
								</div>
								<div class="input_style">
									<label>车&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp道：</label><p class="form-control-static" fill="obj.drivewayName" ></p>
									<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.drivewayPK"/>
								</div>
								<div class="input_style">
									<label>卡&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp口：</label><p class="form-control-static" fill="obj.bayonetEntranceName" ></p>
									<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.bayonetEntrancePK"/>
								</div>
								<div class="input_style">
									<label>停&nbsp&nbsp车&nbsp&nbsp场：</label><p class="form-control-static" fill="obj.bayonetEntranceName" ></p>
									<input type="text" class="form-control input-sm" readonly="readonly" fill=""/>
								</div>
							</div>
							<div class="text_box">
								<label>入场控制</label>
							</div>
							<div class="e_o_btn_left">
								<a href="javascript:void(0)" class="btn btn-primary">入口开闸</a>
								<a href="javascript:void(0)" class="btn btn-primary">入口落闸</a>
								<a href="javascript:void(0)" class="btn btn-primary">确认入场</a>
								<a href="javascript:void(0)" class="btn btn-primary">禁止入场</a>
							</div>
						</form>
				    </div>
					<div class="text_right">
						<ul class="nav nav-pills">
							<li class="active">
								<a href="#tab_2_1" data-toggle="tab" style="height:23px; font-size: 12px; padding-top:2px;">
								出场图片 </a>
							</li>
							<li>
								<a href="#tab_2_2" data-toggle="tab" style="height:23px; font-size: 12px; padding-top:2px;">
								车辆原始图片 </a>
							</li>
						</ul>
						<div class="pic_box tab-content">
							<div class="tab-pane fade active in" id="tab_2_1">
								<img src="../images/admission1.PNG" alt="" width="234px" height="182px">
							</div>
							<div class="tab-pane fade" id="tab_2_2">
								<img src="../images/admission1.PNG" alt="" width="234px" height="182px">
							</div>
						</div>
				    	<div class="text_box">
								<label>出场信息</label>
								<a href="javascript:void(0)" class="btn btn-primary btn-sm">校对</a>
						</div>
						<div class="m_nav_right">
							<div class="input_style">
								<label>时&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp间：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.inTime"/>
							</div>
							<div class="input_style">
								<label>车牌号码：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.licensePlate"/>
							</div>
							<div class="input_style">
								<label>车牌颜色：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.carColor"/>
							</div>
							<div class="input_style">
								<label>车牌类型：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.type"/>
							</div>
							<div class="input_style">
								<label>车辆类型：</label><input type="text" class="form-control input-sm" readonly="readonly" fill="obj.carModel.type" />
							</div>
							<div class="input_style">
								<label>客&nbsp&nbsp户&nbsp&nbsp名：</label><p class="form-control-static" fill="obj.uacc" ></p>
								<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.uacc"/>
							</div>
							<div class="input_style">
								<label>客户类型：</label><p class="form-control-static" fill="obj.type" data-type="int" data-min="0" data-max="3" data-num0="临时用户" data-num1="" data-num2="普通会员" data-num3="长期会员"  ></p>
								<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.type"/>
							</div>
							<div class="input_style">
								<label>车&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp道：</label><p class="form-control-static" fill="obj.drivewayName" ></p>
								<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.drivewayPK"/>
							</div>
							<div class="input_style">
								<label>卡&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp口：</label><p class="form-control-static" fill="obj.bayonetEntranceName" ></p>
								<input type="text" class="form-control input-sm" readonly="readonly" fill="obj.bayonetEntrancePK"/>
							</div>
							<div class="input_style">
								<label>停&nbsp&nbsp车&nbsp&nbsp场：</label><p class="form-control-static" fill="obj.bayonetEntranceName" ></p>
								<input type="text" class="form-control input-sm" readonly="readonly" fill=""/>
							</div>
						</div>
						<div class="text_box">
							<label>出场控制</label>
						</div>
						<div class="e_o_btn_right">
							<a href="javascript:void(0)" class="btn btn-primary">出口开闸</a>
							<a href="javascript:void(0)" class="btn btn-primary">出口落闸</a>
							<a href="javascript:void(0)" class="btn btn-primary">免费放行</a>
							<a href="javascript:void(0)" class="btn btn-primary">确定放行</a>
							<a href="javascript:void(0)" class="btn btn-primary">不予处理</a>
						</div>
				    </div>
				</div>
				 
				<div class="m_main_left">
					<div class="tabbable-line">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="#tab_3_1" data-toggle="tab">
								Section 1 </a>
							</li>
							<li>
								<a href="#tab_3_2" data-toggle="tab">
								人工输入 </a>
							</li>
							<li>
								<a href="#tab_3_3" data-toggle="tab">
								信息发布 </a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_3_1">
								<table class="table table-bordered table-hover" id="favourable" >
									<thead>
										<tr>
											<th>车牌号码</th>
											<th>车牌类型</th>
											<th>入车道</th>
											<th>入场时间</th>
											<th>出车道</th>
											<th>出场时间</th>
											<th>应付金额</th>
											<th>是否已付款</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							<div class="tab-pane" id="tab_3_2">
								<div class="row">
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-2 control-label">输入入场车牌</label>
										<div class="col-md-7">
											<input type="text" class="form-control normal-input input-sm" name="">
										</div>
										<a href="javascript:void(0)" class="btn btn-primary btn-sm">确认</a>
									</div>
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-2 control-label">输入出场车牌</label>
										<div class="col-md-7">
											<input type="text" class="form-control normal-input input-sm" name="">
										</div>
										<a href="javascript:void(0)" class="btn btn-primary btn-sm">确认</a>
									</div>
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-2 control-label">输入黑名单</label>
										<div class="col-md-7">
											<input type="text" class="form-control normal-input input-sm" name="">
										</div>
										<a href="javascript:void(0)" class="btn btn-primary btn-sm">确认</a>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="tab_3_3">
								<div class="row">
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">入口屏平时用语</label>
										<div class="col-md-7">
											<input type="text" class="form-control normal-input input-sm" name="">
										</div>
									</div>
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">出口屏平时用语</label>
										<div class="col-md-7">
											<input type="text" class="form-control normal-input input-sm" name="">
										</div>
									</div>
									<div class="col-md-12" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">（限xx个汉字长度）</label>
									</div>
									<div class="col-md-2 col-md-offset-8" >
										<a href="javascript:void(0)" class="btn btn-primary btn-sm">确认</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script><!--单选按钮和复选按钮的插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- cometd -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/cometd/org/cometd.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/cometd/jquery/jquery.cometd.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>

<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
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
park.datetimepickerInit();
UIIdleTimeout.init();

(function () {
var dataTable_config = {//jquery_datatable插件的默认参数配置
    //下面设置数据栏目是否可排序
    "bServerSide": true,
    "scrollY": "140px",
    "scrollCollapse": true,
    "bLengthChange":false, //关闭每页显示多少条数据
    "sAjaxSource": pageUrl.caradmission_admissRec_list,
    "fnServerData":retrieveData,
    "aoColumns" : [ 
     {
        "mDataProp" : "licensePlate",
        'orderable': false,
        "sWidth": "50px"
    }, {
        "mDataProp" : "licenseType",
        'orderable': false,
        "sWidth": "50px"
    }, {
        "mDataProp" : "inDriveWayName",
        'orderable': false,
        "sWidth": "50px"
    }, {
        "mDataProp" : "inTime",
        "sWidth": "50px"
    },{
        "mDataProp" : "outDriveWayName",
        'orderable': false,
        "sWidth": "50px"
    },{
        "mDataProp" : "outTime",
        "sWidth": "50px"
    },{
        "mDataProp" : "fee",
        "sWidth": "50px"
    },{
        "mDataProp" : "isPay",
        "sWidth": "50px"
    } 
    ],
    "columnDefs": [
    {   aTargets: [7],
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
    "order": [[ 3, "desc" ]]
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
var $table = $('#favourable');
	dataTable_config = $.extend({},dataTable_config,$.global_config.table_config);
	oTable = $table.dataTable(dataTable_config);
	//$.table_init($table);

})();       
});

var cometd = $.cometd;
$(function(){
	var init_enter = function(){
		 function _metaHandshake(message)
        {
        	if (message.successful)
            {
        		//订阅信道
                cometd.subscribe("/enterChannel", function(msg)
                {
                	var data = msg.data;
                    var obj = data.jsonObj;
                    var inPicUrl = obj.inPicUrl;
                    obj.inTime=timeformat(obj.inTime,'yyyy-MM-dd HH:mm:ss');
                   	$("#enterForm").fill(obj);
					if(inPicUrl)
						$("#enter_pic").attr("src",inPicUrl);
					else
						$("#enter_pic").attr("src","<%=request.getContextPath()%>/images/noEnterPic.jpg");
					toastr.success(obj.msg,"入场模块");   
                });
            }
            else
            {
            }
            	
        }

        // 当用户离开页面的时候断开连接
        $(window).unload(function()
        {
            cometd.disconnect(true);
        });

        var cometURL = location.protocol + "//" + "localhost" + ":8081/terminal" + "/cometd";
        cometd.configure({
            url: cometURL,
            //logLevel: 'debug'
        });

        cometd.addListener('/meta/handshake', _metaHandshake);//监听handshake是否成功
       // cometd.addListener('/meta/connect', _metaConnect);
        
        cometd.handshake();
	}

	init_enter();

})
</script>

<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>