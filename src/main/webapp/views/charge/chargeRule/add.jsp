<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:1200px">
		<div class="modal-content">
			<!-- <div class="modal-header">
				 <button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加收费规则
				</h4>
			</div> -->
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addForm">
					<div class="row">
						<div class="col-md-6 chargeruleH">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title" style="font-family: 'Microsoft Yahei'"><span class="glyphicon glyphicon-plus"></span>添加收费规则</h3>
								</div>
								<div class="panel-body">
									<div class="form-body">
										<div class="form-group">
											<label class="col-md-4 control-label">收费规则编号</label>
											<div class="col-md-6">
												<input type="text" data-ajax="@{pageUrl.charge_chargeRule_getChargeRuleId}" readonly="readonly" class="form-control input-sm" name="rentRuleId" id="chargeRuleIdAdd">
												
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-4 control-label">收费规则名称</label>
											<div class="col-md-6">
												<input type="text" class="form-control input-sm" name="ruleName">
												<small class="h-validator" fill="obj.ruleName"></small>  
											</div>
										</div>

										<!--<div class="form-group timeChargeDiv" >
											<label class="col-md-4 control-label">计时收费规则</label>
											<div class="col-md-6">
												<input type="hidden"  class="form-control input-sm select2 timeCharge " name="baseRuleId">
											</div>
										</div>

										<div class="form-group meterChargeDiv"  style="display:none;">
											<label class="col-md-4 control-label">计次收费规则</label>
											<div class="col-md-6">
												<input type="hidden" disabled="disabled"  class="form-control input-sm select2 meterCharge " name="baseRuleId">
											</div>
										</div>

										<div class="form-group anchorGChargeDiv" style="display:none;">
											<label class="col-md-4 control-label">长期收费规则组</label>
											<div class="col-md-6">
												<input type="hidden" disabled="disabled" class="form-control input-sm select2 anchorGroup " name="baseRuleId">
											</div>
										</div> -->
										<div class="form-group">
											<label class="col-md-4 control-label">收费规则类型</label>
											<div class="col-md-6">
												<select class="bs-select form-control" name="rentType" id="charge_add_rentType" data-width="125px">
													<option value="0">计时</option>
													<option value="1">计次</option>
													<option value="2">长期</option>
													<option value="3">免费放行</option>
													
												</select>
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
					                                <div class="form-group">
														<label class="col-md-3 control-label">收费模式</label>
														<div class="col-md-6">
															<select class="bs-select form-control input-sm" name="timeChargeModel.billingModel"  data-width="125px">
																<option value="0">标准模式</option>
																<option value="1">时段模式</option>
																<option value="2">昼夜模式</option>
																<option value="3">昼夜模式二</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">当日收费上限</label>
														<div class="col-md-6">
															<div class="input-inline" style="width:150px;">
																<input  type="text" value="1" name="timeChargeModel.dailyCharge" class="form-control touchspin input-sm">
															</div>
														</div>
													</div>
							<!--标准模式开始-->					
													<div class="timeCharge_stardard">
														<div class="form-group">
																<label class="col-md-3 control-label">小车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" class="spinner-input form-control input-sm" name="timeChargeModel.carUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv" >
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
													</div>
							<!--标准模式结束-->
							<!--时段模式开始-->		
													<div class="timeCharge_periodDiv" style="display:none;">
														<div class="form-group">
																<label class="col-md-3 control-label">时段一长度</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.timeOneLen">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 control-label" style="text-align: left">分</label>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">时段一：小车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control  input-sm" name="timeChargeModel.carUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime" >
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text"  value="1" name="timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
													</div>
													<div class="timeCharge_periodDiv" style="display:none;">
														<div class="form-group">
																<label class="col-md-3 control-label">时段二：小车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.nightCarUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.nightCarUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0"  disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.nightTruckUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text"  value="1" name="timeChargeModel.nightTruckUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
													</div>
							<!--时段模式结束-->
							<!--昼夜模式开始-->			
													<div class="timecharge_day_nightdiv" style="display:none;">
														<div class="form-group">
																<label class="col-md-3 control-label">白天长度</label>
																<div class="col-md-8">
																	<div class="col-md-4 padding0">
																		<div class="input-group">
																			<input type="text"  class="form-control input-sm timepicker timepicker-24" name="timeChargeModel.beginDay">
																			<span class="input-group-btn">
																			<button class="btn btn-sm default" type="button"><i class="fa fa-clock-o"></i></button>
																			</span>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">至</label>
																	<div class="col-md-4 padding0">
																		<div class="input-group">
																			<input type="text"  class="form-control input-sm timepicker timepicker-24" name="timeChargeModel.endDay">
																			<span class="input-group-btn">
																			<button class="btn btn-sm default" type="button"><i class="fa fa-clock-o"></i></button>
																			</span>
																		</div>
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">白天小车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.carUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel" >分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">白天大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel" >分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
													</div>
													<div class="timecharge_day_nightdiv"  style="display:none;">
														<div class="form-group">
																<label class="col-md-3 control-label">夜间小车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.nightCarUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.nightCarUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">夜间大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.nightTruckUnitTime">
																			<div class="spinner-buttons input-group-btn">
																				<button type="button" class="btn btn-sm spinner-up default">
																				<i class="fa fa-angle-up"></i>
																				</button>
																				<button type="button" class="btn btn-sm spinner-down default">
																				<i class="fa fa-angle-down"></i>
																				</button>
																			</div>
																		</div>
																	</div>
																</div>
																<label class="col-md-1 leftTextLabel">分</label>
																<div class="col-md-2">
																	<div class="input-inline" style="width:150px;">
																		<input  type="text" value="1" name="timeChargeModel.nightTruckUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
													</div>
							<!--昼夜模式结束-->							
													
													<div class="form-group">
															<label class="col-md-3 control-label">进场免费时间</label>
															<div class="col-md-3 marginLeftDiv">
																<div class="spinner">
																	<div class="input-group" style="width:130px;">
																		<input type="text" readonly="readonly" value="0" class="spinner-input form-control input-sm"  name="timeChargeModel.freeTime">
																		<div class="spinner-buttons input-group-btn">
																			<button type="button" class="btn btn-sm spinner-up default">
																			<i class="fa fa-angle-up"></i>
																			</button>
																			<button type="button" class="btn btn-sm spinner-down default">
																			<i class="fa fa-angle-down"></i>
																			</button>
																		</div>
																	</div>
																</div>
															</div>
															<label class="col-md-3 control-label" style="text-align: left">分钟内免费</label>
													</div>
													<div class="form-group">
															<label class="col-md-3 control-label">出场超时长</label>
															<div class="col-md-3 marginLeftDiv">
																<div class="spinner">
																	<div class="input-group" style="width:130px;">
																		<input type="text" readonly="readonly" value="0" class="input-sm spinner-input form-control" name="timeChargeModel.timeout">
																		<div class="spinner-buttons input-group-btn">
																			<button type="button" class="btn btn-sm spinner-up default">
																			<i class="fa fa-angle-up"></i>
																			</button>
																			<button type="button" class="btn btn-sm spinner-down default">
																			<i class="fa fa-angle-down"></i>
																			</button>
																		</div>
																	</div>
																</div>
															</div>
															<label class="col-md-1 leftTextLabel">分</label>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">对于不足于一时间单位的处理</label>
														<div class="col-md-6">
															<div class="icheck-inline">
																<label><input id="add_timeHandle0" type="radio" data-check="true" checked="checked" name="timeChargeModel.timeHandle" class="icheck ic_default" value="0" />记为一时间单位</label> 
																<label><input id="add_timeHandle1" type="radio" name="timeChargeModel.timeHandle" class="icheck" value="1" />舍</label>
															</div>
														</div>
												 	 </div>
												 	<div class="form-group">
												 	 	<label class="col-md-3 control-label">对于费用总计小数部分的处理</label>
														<div class="col-md-6">
															<div class="icheck-inline">
																<label><input id="add_deciHandle0" type="radio" data-check="true" checked="checked" name="timeChargeModel.deciHandle" class="icheck ic_default" value="0" />记为一元</label> 
																<label><input id="add_deciHandle1" type="radio" name="timeChargeModel.deciHandle" class="icheck" value="1" />舍</label>
																<label><input id="add_deciHandle2" type="radio" name="timeChargeModel.deciHandle" class="icheck" value="2" />不处理</label>
															</div>
													    </div>
												 	 </div>
												 </div>
											</div>
										</div>
									</div>
								</div>
								<div class="meterCharge_Div" style="display:none;">
									<div class="col-md-6">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title">计次收费规则</h3>
											</div>
											<div class="panel-body">
												<div class="form-body">
													<div class="form-group">
												        <label class="col-md-4 control-label">计次免费时间</label>
														<div class="col-md-3 marginLeftDiv">
															<div class="spinner">
																<div class="input-group" style="width:130px;">
																	<input type="text" value="0" readonly="readonly" class="spinner-input form-control input-sm" name="meterChargeModel.meteringFree">
																	<div class="spinner-buttons input-group-btn">
																		<button type="button" class="btn btn-sm spinner-up default">
																		<i class="fa fa-angle-up"></i>
																		</button>
																		<button type="button" class="btn btn-sm spinner-down default">
																		<i class="fa fa-angle-down"></i>
																		</button>
																	</div>
																</div>
															</div>
														</div>
														<label class="col-md-1 leftTextLabel">分</label>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">小车计次收费</label>
														<div class="col-md-2">
															<div class="input-inline" style="width:150px;">
																<input  type="text" value="0.00" name="meterChargeModel.carMetering" class="form-control input-sm touchspin">
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">大车计次收费</label>
														<div class="col-md-2">
															<div class="input-inline" style="width:150px;">
																<input  type="text" value="0.00" name="meterChargeModel.truckMetering" class="form-control input-sm touchspin">
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="anchorCharge_Div" style="display:none;">
									<div class="col-md-6">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title">长期收费规则</h3>
											</div>
											<div class="panel-body">
												<div class="form-body">
													<div class="form-group anchorGChargeDiv">
														<label class="col-md-4 control-label">长期收费规则组</label>
														<div class="col-md-6">
															<input disabled="disabled" class="form-control input-sm select2 anchorGroup " name="baseRuleId">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button" class="btn green save">保存</button>
				</div>
			</div>
		</div>
	</div>
<!--添加 end-->
<script type="text/javascript">

var add=function(){
	var bindRentTypeChange=function(){
		$('#charge_add_rentType').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .timeCharge_Div").show();
				$("#addForm .meterCharge_Div").hide();
				$("#addForm .anchorCharge_Div").hide();
				$("#addForm .timeCharge_Div :input").removeAttr("disabled"); 
				$("#addForm .meterCharge_Div :input").attr("disabled","disabled"); 
				$("#addForm .anchorCharge_Div :input").attr("disabled","disabled");
				$("#add .modal-dialog").attr("style","width:1200px");  
				$("#addForm .chargeruleH").addClass("col-md-6");
			}
			else if(value=='1'){
				$("#addForm .timeCharge_Div").hide();
				$("#addForm .meterCharge_Div").show();
				$("#addForm .anchorCharge_Div").hide();
				$("#addForm .timeCharge_Div :input").attr("disabled","disabled");
				$("#addForm .meterCharge_Div :input").removeAttr("disabled"); 
				$("#addForm .anchorCharge_Div :input").attr("disabled","disabled"); 
				$("#add .modal-dialog").attr("style","width:1200px");
				$("#addForm .chargeruleH").addClass("col-md-6");
			}
			else if(value=='2'){
				$("#addForm .timeCharge_Div").hide();
				$("#addForm .meterCharge_Div").hide();
				$("#addForm .anchorCharge_Div").show();
				$("#addForm .timeCharge_Div :input").attr("disabled","disabled");
				$("#addForm .meterCharge_Div :input").attr("disabled","disabled"); 
				$("#addForm .anchorCharge_Div :input").removeAttr("disabled"); 
				$("#add .modal-dialog").attr("style","width:1200px");
				$("#addForm .chargeruleH").addClass("col-md-6");
			}
			else if(value=='3'){
				$("#addForm .timeCharge_Div").hide();
				$("#addForm .meterCharge_Div").hide();
				$("#addForm .anchorCharge_Div").hide();
				$("#addForm .timeCharge_Div :input").attr("disabled","disabled");
				$("#addForm .meterCharge_Div :input").attr("disabled","disabled"); 
				$("#addForm .anchorCharge_Div :input").attr("disabled","disabled");
				$("#add .modal-dialog").removeAttr("style","width:1200px");
				$("#addForm .chargeruleH").removeClass("col-md-6");
			}
		})
	}
	var bindbilling_model=function(){
		$('#addForm select[name="timeChargeModel.billingModel"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .timeCharge_periodDiv").hide();
				$("#addForm .timecharge_day_nightdiv").hide();
				$("#addForm .timeCharge_stardard").show();
				$("#addForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#addForm .timeCharge_stardard input").prop("disabled",false);
				$("#addForm .timecharge_day_nightdiv input").prop("disabled", true);
			}
			else if(value=='1'){
				$("#addForm .timeCharge_periodDiv").show();
				$("#addForm .timecharge_day_nightdiv").hide();
				$("#addForm .timeCharge_stardard").hide();
				$("#addForm .timeCharge_periodDiv input").prop("disabled", false);
				$("#addForm .timeCharge_stardard input").prop("disabled",true);
				$("#addForm .timecharge_day_nightdiv input").prop("disabled", true);
			}
			else if(value=='2'){
				$("#addForm .timecharge_day_nightdiv").show();
				$("#addForm .timeCharge_periodDiv").hide();
				$("#addForm .timeCharge_stardard").hide();
				$("#addForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#addForm .timeCharge_stardard input").prop("disabled",true);
				$("#addForm .timecharge_day_nightdiv input").prop("disabled", false);
			}
			else if(value=='3'){
				$("#addForm .timecharge_day_nightdiv").show();
				$("#addForm .timeCharge_periodDiv").hide();
				$("#addForm .timeCharge_stardard").hide();
				$("#addForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#addForm .timeCharge_stardard input").prop("disabled",true);
				$("#addForm .timecharge_day_nightdiv input").prop("disabled", false);
			}
		})
	}
	var bindSubmit=function(){
		$('#add .save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$("#add .save").prop('disabled',true);
				$.ajax({
			        type: "POST",
			        url: pageUrl.charge_chargeRule_add,
			        data:$('#addForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	// alert(data);
			        	if(data==null||data==""){
			        		return;
			        	}
			        	var obj = data;
			        	if(!obj.validateResult){
			        		//alert(obj.validateMsg);
			        		$("#addForm").clearValidator();
			        		$("#addForm").fillValidator(obj.validateMsg);
			        		//alert(obj.msg);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add").modal('hide');
			        		toastr.success(obj.msg,"收费规则模块");
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(obj.msg, "收费规则模块");
			        		//alert(obj.msg);
			        	}
			        }
				  }); 
			}
		})
	}
	
	return {
        init:function(){
        	bindRentTypeChange();
        	bindSubmit();
        	bindbilling_model();
        }
    };
}();
</script>

