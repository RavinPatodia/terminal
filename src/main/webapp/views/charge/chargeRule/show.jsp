<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog" style="width:1200px;">
			<div class="modal-content">
				<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看收费规则
					</h4>
				</div> -->
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="row">
									<div class="col-md-6 chargeruleH">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title" style="font-family: 'Microsoft Yahei'"><span class="glyphicon glyphicon-list-alt"></span> 查看收费规则</h3>
											</div>
											<div class="panel-body">
												<div class="form-body">
													<input type="hidden" fill="obj.rentType" name="rentType" onchange="chargeRule_show.formatRentType(this)"/>
													<div class="form-group">
														<label class="col-md-4 control-label">收费规则编号</label>
														<div class="col-md-6">
															<label class="form-control-static show" fill="obj.rentRuleId"></label>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">收费规则名称</label>
														<div class="col-md-6">
															<label class="form-control-static show" fill="obj.ruleName">
															</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
										<div>
											<div class="timeCharge_Div">
												<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">计时收费规则</h3>
														</div>
														<div class="panel-body">
															<div class="form-body">
								                                <!-- <div class="form-group">
																	<label class="col-md-4 control-label">是否是历史规则</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.historyFlag" data-type="boolean" data-true="是" data-false="否" id="history"></p>
																	</div>
																</div> -->
																<div class="form-group">
																	<label class="col-md-3 control-label">收费模式</label>
																	<div class="col-md-6">
																	    <input type="hidden" class="form-control"  onchange="chargeRule_show.formatBillingModel(this)" fill="obj.billingModel"/>
																		<p class="form-control-static" id="show_billingModel"></p>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">进场免费时间</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.freeTime"></p>
																	</div>
																	<label class="col-md-4 control-label" style="text-align: left">分钟内免费</label>
																</div>
																
																<div class="timeCharge_stardard">
																<div class="form-group">
																	<label class="col-md-3 control-label">小车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.carUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.carUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">大车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.truckUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.truckUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
																</div>
																
																<div class="timeCharge_periodDiv" style="display:none;">
																<div class="form-group">
																	<label class="col-md-3 control-label">时段一长度</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.timeOneLen"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">时段一：小车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.carUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.carUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">大车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.truckUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.truckUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
															</div>
															
															<div class="timeCharge_periodDiv" style="display:none;">
																<div class="form-group">
																	<label class="col-md-3 control-label">时段二：小车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.carUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.carUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>	
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">大车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.truckUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.truckUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>	
																</div>
															</div>
															
															<div class="timecharge_day_nightdiv" style="display:none;">
																<div class="form-group">
																	<label class="col-md-3 control-label">白天长度</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.beginDay"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">至</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.endDay"></p>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">白天小车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.carUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.carUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>	
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">白天大车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.truckUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.truckUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>	
																</div>
															</div>
															<div class="timecharge_day_nightdiv"  style="display:none;">
																<div class="form-group">
																	<label class="col-md-3 control-label">夜间小车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.nightCarUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.nightCarUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">夜间大车单价</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.nightTruckUnitTime"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																	<div class="col-md-2">
																	    <p class="form-control-static" fill="obj.nightTruckUnitFee"></p>
																	</div>
																	<label class="col-md-1 control-label" style="text-align: left">元</label>
																</div>
															</div>	
																<div class="form-group">
																	<label class="col-md-3 control-label">出场超时长</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.timeout"></p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">对于不足于一时间单位的处理</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.timeHandle" data-type="int" data-min="0" data-max="1" data-num0="记为一时间单位" data-num1="舍" id="timeHandle"></p>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">对于费用总计小数部分的处理</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.deciHandle" data-type="int" data-min="0" data-max="2" data-num0="记为一元" data-num1="舍" data-num2="不处理" id="deciHandle"></p>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label">创建时间</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.createTime"></p>
																	</div>
																</div>

																<!-- <div class="form-group">
																	<label class="col-md-4 control-label">上次启用时间</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.lastEnable"></p>
																	</div>
																</div> -->
															 </div>
														</div>
													</div>
												</div>
											</div>
											<div class="meterCharge_Div" style="display: none;">
												<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">计次收费规则</h3>
														</div>
														<div class="panel-body">
															<div class="form-body">
																<div class="form-group">
																	<label class="col-md-4 control-label">计次免费时间</label>
																	<div class="col-md-2"><p class="form-control-static" fill="obj.meteringFree">
																		</p></div>
																	<label class="col-md-2 control-label" style="text-align: left">分钟</label>
																</div>

																<div class="form-group">
																	<label class="col-md-4 control-label">小车计次收费</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.carMetering">
																		</p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">元</label>
																</div>
																
																<div class="form-group">
																	<label class="col-md-4 control-label">大车计次收费</label>
																	<div class="col-md-2">
																		<p class="form-control-static" fill="obj.truckMetering">
																		</p>
																	</div>
																	<label class="col-md-2 control-label" style="text-align: left">元</label>
																</div>
																<!-- <div class="form-group">
																	<label class="col-md-4 control-label">是否是历史规则</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.historyFlag" data-type="boolean" data-true="是" data-false="否" id="history"></p>
																	</div>
																</div> -->
																<div class="form-group">
																	<label class="col-md-4 control-label">创建时间</label>
																	<div class="col-md-6">
																		<p class="form-control-static" fill="obj.createTime">
																		</p>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="anchorCharge_Div" style="display: none;">
												<div class="col-md-6">
													<div class="panel panel-default">
														<div class="panel-heading">
															<h3 class="panel-title">长期收费规则</h3>
														</div>
														<div class="panel-body">
															<div class="form-body">
																<div class="form-group">
																	<label class="col-md-4 control-label">长期收费规则组编号</label>
																	<div class="col-md-6"><p class="form-control-static" fill="obj.anchorGroupId">
																		</p></div>
																</div>
																<div class="form-group">
																	<label class="col-md-4 control-label">长期收费规则组名称</label>
																	<div class="col-md-6"><p class="form-control-static" fill="obj.name">
																		</p></div>
																</div>
																<div class="form-group">
																	<label class="col-md-4 control-label">长期收费规则组描述</label>
																	<div class="col-md-6"><p class="form-control-static" fill="obj.describion">
																	</p></div>
																</div>
															</div>
															<table class="table table-bordered table-hover" id="recs">
																<thead>
																	<tr>
																		<th>长期收费规则编号</th>
																		<th>长期收费规则名称</th>
																		<th>月数</th>
																		<th>费用</th>
																		<th>附加费用</th>
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
									</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!--查看 end-->
<script>
var chargeRule_show = function () {
	var formatBillingModel=function(ele){
		var value = $(ele).val();
		if(value=="0"){
			$("#show_billingModel").text("标准模式");
			$(".timeCharge_stardard").show();
			$(".timecharge_day_nightdiv").hide();
			$(".timeCharge_periodDiv").hide();
		}
		else if(value=="1"){
			$("#show_billingModel").text("时段模式");
			$(".timeCharge_periodDiv").show();
			$(".timecharge_day_nightdiv").hide();
			$(".timeCharge_stardard").hide();
		}
		else if(value=="2"){
			$("#show_billingModel").text("昼夜模式");
			$(".timecharge_day_nightdiv").show();
			$(".timeCharge_periodDiv").hide();
			$(".timeCharge_stardard").hide();
		}
		else if(value=="3"){
			$("#show_billingModel").text("昼夜模式二");
			$(".timecharge_day_nightdiv").show();
			$(".timeCharge_periodDiv").hide();
			$(".timeCharge_stardard").hide();
		}
	} 
	
var anchorGroupRecs = function(jsonData){
	var dataTable_config = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    "destroy":true,
	    "data": jsonData,
        "bProcessing": true,
        "bFilter": false,
        "bPaginate": false,
        "bAutoWidth":false,//自动宽度
        "bLengthChange":false, //关闭每页显示多少条数据
        "language": {
            "lengthMenu": "  _MENU_ 条数据",
            "emptyTable": "表格中没有数据~",
            "info": "显示 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
            "infoEmpty": "没有数据~",
            "infoFiltered": "(在 _MAX_ 条数据中查询)",
            "lengthMenu": "显示 _MENU_ 条数据",
            "search": "查询:",
            "zeroRecords": "没有找到对应的数据",
   		}, 
	    "aoColumns" : [ 
	           	  	{
				        "mDataProp" : "anchorRentId",
				    }, {
				        "mDataProp" : "name",
				        "sWidth": "400px"
				    }, {
				        "mDataProp" : "month",
				        "sWidth": "100px"
				    }, {
				        "mDataProp" : "fee",
				        "sWidth": "100px"
				    },{
				        "mDataProp" : "addFee",
				        "sWidth": "100px"
				    }
	           	    ],
	};
	var $table = $('#recs');
	oTable = $table.dataTable(dataTable_config);
}; 

var formatRentType=function(ele){
	var value = $(ele).val();
	if(value=='0'){
		$("#showForm .timeCharge_Div").show();
		$("#showForm .meterCharge_Div").hide();
		$("#showForm .anchorCharge_Div").hide();
	}
	else if(value=='1'){
		$("#showForm .timeCharge_Div").hide();
		$("#showForm .meterCharge_Div").show();
		$("#showForm .anchorCharge_Div").hide();
	}
	else if(value=='2'){
		$("#showForm .timeCharge_Div").hide();
		$("#showForm .meterCharge_Div").hide();
		$("#showForm .anchorCharge_Div").show();
	}
	else if(value=='3'){
		$("#showForm .timeCharge_Div").hide();
		$("#showForm .meterCharge_Div").hide();
		$("#showForm .anchorCharge_Div").hide();
		$("#show .modal-dialog").removeAttr("style","width:1200px");
		$("#showForm .chargeruleH").removeClass("col-md-6");
	}
} 
	
	return {
        //main function to initiate the module
        
        formatBillingModel:function(ele){
        	formatBillingModel(ele);
        },
        formatRentType:function(ele){
        	formatRentType(ele);
        }
    };
	
}();


</script>