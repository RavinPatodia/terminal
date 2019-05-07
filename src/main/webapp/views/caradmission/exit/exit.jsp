<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
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
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<jsp:include page="/views/layout/menu.jsp" />
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->
				
				<!-- END PAGE HEADER-->
				
				<div class="row">
					<div class="col-md-12">
							<div class="portlet box blue">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-gift"></i>车辆出场显示
											</div>
											<div class="tools">
												<a href="javascript:;" class="collapse">
												</a>
												<a href="#portlet-config" data-toggle="modal" class="config">
												</a>
												<a href="javascript:;" class="reload">
												</a>
												<a href="javascript:;" class="remove">
												</a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form class="form-horizontal" role="form"  id="exitForm">
												<div class="form-body">
													<h3 class="form-section">用户信息</h3>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-3">车牌号:</label>
																<div class="col-md-9">
																	<p class="form-control-static" fill="obj.carModel.licensePlate" ></p>
																</div>
															</div>
														</div>
														<!--/span-->
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-3">车牌类型:</label>
																<div class="col-md-9">
																	<p class="form-control-static" fill="obj.carModel.type" ></p>
																</div>
															</div>
														</div>
														
														<!--/span-->
													</div>
													
													<!--/row-->
													<div id="user_model" style="display:none;">
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3">用户名:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.uacc" ></p>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3">用户类型:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" id="exit_type"></p>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<h3 class="form-section">出入场信息</h3>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-3">出口:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.bayonetExitName" ></p>
																	</div>
																</div>
															</div>
														</div>
														
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-3">出场时间:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.outTime" ></p>
																	</div>
																</div>
															</div>
														</div>
														
														<div class="row">
															<!--/span-->
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-3">抓拍图片:</label>
																	<div class="col-md-9">
																		<img src="" class="img-responsive" id="exit_pic"/>
																	</div>
																</div>
															</div>
															<!--/span-->
														</div>
													</div>
													<div class="col-md-6">
														<div class="row">
															<div class="col-md-12">
															
																<div class="form-group">
																	<label class="control-label col-md-3">入口:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.bayonetEntranceName" ></p>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-3">入场时间:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.inTime" ></p>
																	</div>
																</div>
															</div>
														</div>
														
														<div class="row">
															<!--/span-->
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-3">抓拍图片:</label>
																	<div class="col-md-9">
																		<img src="" class="img-responsive" id="enter_pic"/>
																	</div>
																</div>
															</div>
															<!--/span-->
														</div>
													</div>
													
													<div class="row">
														<div class="col-md-12">
															<a href="javascript:void(0)" id="btn_intellmatching"
															class="btn green col-md-2 col-md-offset-5">智能匹配</a>
														</div>
													</div>
													<div id="charge_model" style="display:none;">
														<h3 class="form-section">收费信息</h3>
														<div class="row" >
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3">应付金额:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.amoutPay" ></p>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3">优惠金额:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.dctPay" ></p>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-2">实付金额:</label>
																	<div class="col-md-10">
																		<p class="form-control-static" fill="obj.actualPay" ></p>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-2">是否已付款:</label>
																	<div class="col-md-10">
																		<p class="form-control-static" id = "payflag" ></p>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-12">
																<div class="form-group">
																	<label class="control-label col-md-2">收费方式:</label>
																	<div class="col-md-10">
																		<label><input id="pay_type" type="radio"  name="payType" class="icheck" value="0" />账户扣款</label> 
																		<label><input id="pay_type" type="radio" checked="checked" name="payType" class="icheck" value="1" />人工收费</label>
																		
																	</div>
																</div>
															</div>
														</div>
													</div>

													<div id="blacklist_model" style="display:none;">
														<h3 class="form-section">黑名单信息</h3>
														<div class="row" >
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-3">列入黑名单原因:</label>
																	<div class="col-md-9">
																		<p class="form-control-static" fill="obj.listReason" ></p>
																	</div>
																</div>
															</div>
															
														</div>
														<div class="row">
														<div class="col-md-12">
															<a href="javascript:void(0)" onclick="" id="btn_blacklist"
															class="btn green col-md-2 col-md-offset-5 ">黑名单处理</a>
														</div>
													</div>
													</div>

													<div class="row">
														<div class="col-md-12">
															<a href="javascript:void(0)" onclick="" id="btn_complete" style="display:none;" 
															class="btn green col-md-2 col-md-offset-5 ">收费完成</a>
														</div>
													</div>
													
													
													<!--/row-->

													<input type="hidden" class="form-control normal-input" id="get_licensePlate" name="carModel.licensePlate" fill="obj.carModel.licensePlate">
													<input type="hidden" class="form-control normal-input" id="get_licensePlateType" name="carModel.licensePlateType" fill="obj.carModel.type">
													<input type="hidden" class="form-control normal-input" id="get_carColor" name="carModel.carColor" fill="obj.carModel.carColor">
													<input type="hidden" class="form-control normal-input" id="get_carModel" name="carModel.carModel" fill="obj.carModel.carModel">
													<input type="hidden" class="form-control normal-input" name="bayonetExitPK" fill="obj.bayonetExitPK">
													<input type="hidden" class="form-control normal-input" name="drivewayPK" fill="obj.drivewayPK">
													<input type="hidden" class="form-control normal-input" name="carModel.id" fill="obj.carModel.id">
													<input type="hidden" class="form-control normal-input" name="outRecoWay" fill="obj.outRecoWay">
													<input type="hidden" class="form-control normal-input" name="outTime" fill="obj.outTime">
													<input type="hidden" class="form-control normal-input" name="outPicUrl" fill="obj.outPicUrl">
													<input type="hidden" class="form-control normal-input" name="fee" fill="obj.fee">
													<input type="hidden" class="form-control normal-input" name="payFlag" fill="obj.payFlag">
													<input type="hidden" class="form-control normal-input" name="usertype" fill="obj.usertype">
													<input type="hidden" class="form-control normal-input" name="actualPay" fill="obj.actualPay">
													<input type="hidden" class="form-control normal-input" name="amoutPay" fill="obj.amoutPay">
													<input type="hidden" class="form-control normal-input" name="dctPay" fill="obj.dctPay">
													<input type="hidden" class="form-control normal-input" name="dctRuleGroupPK" fill="obj.dctRuleGroupPK">
													<input type="hidden" class="form-control normal-input" name="admissionRecPK" fill="obj.admissionRecPK">
													<input type="hidden" class="form-control normal-input" name="dctRulePKs" fill="obj.dctRulePKs">
													<input type="hidden" class="form-control normal-input" name="chargeRulePKs" fill="obj.chargeRulePKs">

								
												</div>
		
											</form>
											
											<!-- END FORM-->
										</div>
							</div>
					</div>
			</div>
				
			</div>

			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="/views/caradmission/exit/intelligentMatching.jsp" />	
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
 <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>

<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/js/global.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<!-- cometd -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/cometd/org/cometd.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/cometd/jquery/jquery.cometd.js"></script>

<script>
jQuery(document).ready(function() {
// initiate layout and plugins
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
park.init(); //Form中精确搜索的的初始化 可以查看/js/park.js
park.dateTimeInit(); //日期框的初始化 
park.modalInit();
UIIdleTimeout.init();
})

</script>
<script type="text/javascript">

var timeformat = function(time, format) {
    var t = new Date(time);
    var tf = function(i) {
        return (i < 10 ? '0': '') + i
    };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
    function(a) {
        switch (a) {
        case 'yyyy':
            return tf(t.getFullYear());
            break;
        case 'MM':
            return tf(t.getMonth() + 1);
            break;
        case 'mm':
            return tf(t.getMinutes());
            break;
        case 'dd':
            return tf(t.getDate());
            break;
        case 'HH':
            return tf(t.getHours());
            break;
        case 'ss':
            return tf(t.getSeconds());
            break;
        }
    });
}

var cometd = $.cometd;
$(function(){
	var getExitData = function(){
		 function _metaHandshake(message)
        {
        	if (message.successful)
            {
        		console.log("握手成功");
        		console.log("/exitChannel/${fns:getClientIp()}");
                cometd.subscribe('/exitChannel/${fns:getClientIp()}', function(msg)
                {
                	var data = msg.data;
                    var obj = data.jsonObj;
                    var type = obj.usertype;
					var inPicUrl = obj.inPicUrl;
					var outPicUrl= obj.outPicUrl;
					var payFlag = obj.payFlag; 
					obj.outTime=timeformat(obj.outTime,'yyyy-MM-dd HH:mm:ss');
					alert(obj.outTime);
					if(obj.successflag){
						$("#exitForm").fill(obj);//表单填充插件
						if(inPicUrl)
							$("#enter_pic").attr("src",inPicUrl);
						else
							$("#enter_pic").attr("src","<%=request.getContextPath()%>/assets/global/plugins/jcrop/demos/demo_files/image1.jpg");
						if(outPicUrl)
							$("#exit_pic").attr("src",outPicUrl);
						else
							$("#exit_pic").attr("src","<%=request.getContextPath()%>/assets/global/plugins/jcrop/demos/demo_files/image1.jpg");
						if(type == 0){
							$('#exit_type').text("临时用户");
							$('#user_model').show();
							$('#blacklist_model').hide();
							$('#btn_complete').show();
							$('#charge_model').show();
		       				$('#btn_intellmatching').attr("disabled", false);
		       				$('#btn_complete').attr("disabled", false);
						}	
						else if(type == 1){
							$('#exit_type').text("黑名单用户");
							$('#user_model').show();
							$('#blacklist_model').show();
							$('#btn_complete').hide();
							$('#charge_model').show();
							$('#btn_intellmatching').attr("disabled", false);
		       				$('#btn_complete').attr("disabled", true);
						}	
						else if(type == 2){
							$('#exit_type').text("普通会员");
							$('#user_model').show();
							$('#blacklist_model').hide();
							$('#btn_complete').show();
							$('#charge_model').show();
							$('#btn_intellmatching').attr("disabled", false);
		       				$('#btn_complete').attr("disabled", false);
						}	
						else if(type == 3){
							$('#exit_type').text("长期会员");
							$('#user_model').show();
							$('#blacklist_model').hide();
							$('#btn_complete').show();
							$('#charge_model').hide();
							$('#btn_intellmatching').attr("disabled", true);
		       				$('#btn_complete').attr("disabled", true);
						}
							
						if(payFlag == 0)
							$('#payflag').text("未付款");
						else if(payFlag == 1){
							$('#payflag').text("已付款");
							$('#btn_complete').prop("disable",true);
						}
					
						toastr.success(obj.msg,"出场模块");
					}
					else{
						$("#exitForm").fill(obj);//表单填充插件
						if(inPicUrl)
							$("#enter_pic").attr("src",inPicUrl);
						else
							$("#enter_pic").attr("src","<%=request.getContextPath()%>/assets/global/plugins/jcrop/demos/demo_files/image1.jpg");
						if(outPicUrl)
							$("#exit_pic").attr("src",outPicUrl);
						else
							$("#exit_pic").attr("src","<%=request.getContextPath()%>/assets/global/plugins/jcrop/demos/demo_files/image1.jpg");		
						$('#user_model').hide();
						$('#blacklist_model').hide();
						$('#btn_complete').hide();
						$('#charge_model').hide();
						$('#btn_intellmatching').attr("disabled", false);
		       			$('#btn_complete').attr("disabled", false);
						toastr.error("找不到对应的入场记录");
					}
                    // Find the div for the given stock symbol
                 
                });
            }
            else
            {
            }
            	
        }

        // Disconnect when the page unloads
        $(window).unload(function()
        {
            cometd.disconnect(true);
        });

        var cometURL = location.protocol + "//" + "${serverIp}" + ":8080/park" + "/cometd";
        cometd.configure({
            url: cometURL,
            //logLevel: 'debug'
        });

        cometd.addListener('/meta/handshake', _metaHandshake);//监听handshake是否成功
       // cometd.addListener('/meta/connect', _metaConnect);
        
       
        cometd.handshake();

	}

	var bindSubmit = function(){
		$("#btn_complete").on("click",function(){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.caradmission_exit_complete,
		       data:$('#exitForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$('#payflag').text("已付款");
		       		$('#btn_intellmatching').attr("disabled", true);
		       		$('#btn_complete').attr("disabled", true);
		       		toastr.success(obj.msg,"出场模块");
		       	}
		       	else{
		       		toastr.error(obj.msg, "出场模块");
		       	}
		       }
		   }); 
		})
	}

	getExitData();
	intelligentMatching.init();
	bindSubmit();

})

</script>


<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>