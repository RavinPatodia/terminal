<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog"  style="width:1200px">
			<div class="modal-content">
				<!-- <div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改收费规则
					</h4>
				</div> -->
				<div class="modal-body">
				    <form class="form-horizontal" role="form" id="editForm">
						<div class="row">
							<div class="col-md-6 chargeruleH">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title" style="font-family: 'Microsoft Yahei'"><span class="glyphicon glyphicon-plus"></span>修改收费规则</h3>
									</div>
									<div class="panel-body">
										<div class="form-body">
											<input type="hidden" readonly="readonly" class="form-control" name="id" fill="obj.id">
											<input type="hidden"  id="baseRuleId" class="form-control" name="baseRuleId" fill="obj.baseRuleId">
											<input type="hidden" fill="obj.rentType" name="rentType" onchange="edit.formatRentType(this)"/>
											<div class="form-group">
												<label class="col-md-4 control-label">收费规则编号</label>
												<div class="col-md-6">
													<input type="text" readonly="readonly" class="form-control" name="rentRuleId" fill="obj.rentRuleId">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-4 control-label">收费规则名称</label>
												<div class="col-md-6">
													<input type="text" class="form-control" name="ruleName" fill="obj.ruleName">
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
															<select class="bs-select form-control input-sm" name="timeChargeModel.billingModel" fill="obj.timeChargeModel.billingModel"  data-width="125px">
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
																<input  type="text" value="1" name="timeChargeModel.dailyCharge" fill="obj.timeChargeModel.dailyCharge" class="form-control touchspin input-sm">
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
																			<input type="text" readonly="readonly" value="0" class="spinner-input form-control input-sm" fill="obj.timeChargeModel.carUnitTime" name="timeChargeModel.carUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" fill="obj.timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv" >
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" fill="obj.timeChargeModel.truckUnitTime" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.truckUnitFee" fill="obj.timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
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
																			<input type="text" readonly="readonly" value="0" disabled="disabled" fill="obj.timeChargeModel.timeOneLen" class="spinner-input form-control input-sm" name="timeChargeModel.timeOneLen">
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
																			<input type="text" readonly="readonly" value="0" disabled="disabled" fill="obj.timeChargeModel.carUnitTime" class="spinner-input form-control  input-sm" name="timeChargeModel.carUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" fill="obj.timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime" fill="obj.timeChargeModel.truckUnitTime" >
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
																		<input  type="text"  value="1" name="timeChargeModel.truckUnitFee" fill="obj.timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
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
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" fill="obj.timeChargeModel.nightCarUnitTime" name="timeChargeModel.nightCarUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.nightCarUnitFee" fill="obj.timeChargeModel.nightCarUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0"  disabled="disabled" class="spinner-input form-control input-sm" fill="obj.timeChargeModel.nightTruckUnitTime" name="timeChargeModel.nightTruckUnitTime">
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
																		<input  type="text"  value="1" name="timeChargeModel.nightTruckUnitFee" fill="obj.timeChargeModel.nightTruckUnitFee" class="form-control touchspin input-sm">
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
																			<input type="text"  class="form-control input-sm timepicker timepicker-24" fill="obj.timeChargeModel.beginDay" name="timeChargeModel.beginDay">
																			<span class="input-group-btn">
																			<button class="btn btn-sm default" type="button"><i class="fa fa-clock-o"></i></button>
																			</span>
																		</div>
																	</div>
																	<label class="col-md-2 control-label">至</label>
																	<div class="col-md-4 padding0">
																		<div class="input-group">
																			<input type="text"  class="form-control input-sm timepicker timepicker-24" fill="obj.timeChargeModel.endDay" name="timeChargeModel.endDay">
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
																		<input  type="text" value="1" name="timeChargeModel.carUnitFee" fill="obj.timeChargeModel.carUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">白天大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control input-sm" name="timeChargeModel.truckUnitTime" fill="obj.timeChargeModel.truckUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.truckUnitFee" fill="obj.timeChargeModel.truckUnitFee" class="form-control touchspin input-sm">
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
																			<input type="text" readonly="readonly" value="0" disabled="disabled" fill="obj.timeChargeModel.nightCarUnitTime" class="spinner-input form-control input-sm" name="timeChargeModel.nightCarUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.nightCarUnitFee" fill="obj.timeChargeModel.nightCarUnitFee" class="form-control touchspin input-sm">
																	</div>
																</div>
														</div>
														<div class="form-group">
																<label class="col-md-3 control-label">夜间大车单价</label>
																<div class="col-md-3 marginLeftDiv">
																	<div class="spinner">
																		<div class="input-group" style="width:130px;">
																			<input type="text" readonly="readonly" value="0" disabled="disabled" fill="obj.timeChargeModel.nightTruckUnitTime" class="spinner-input form-control input-sm" name="timeChargeModel.nightTruckUnitTime">
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
																		<input  type="text" value="1" name="timeChargeModel.nightTruckUnitFee" fill="obj.timeChargeModel.nightTruckUnitFee" class="form-control touchspin input-sm">
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
																		<input type="text" readonly="readonly" value="0" class="spinner-input form-control input-sm" fill="obj.timeChargeModel.freeTime" name="timeChargeModel.freeTime">
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
																		<input type="text" readonly="readonly" value="0" class="input-sm spinner-input form-control" fill="obj.timeChargeModel.timeout" name="timeChargeModel.timeout">
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
																<label><input id="add_timeHandle0" type="radio" data-check="true" checked="checked" name="timeChargeModel.timeHandle" fill="obj.timeChargeModel.timeHandle" class="icheck ic_default" value="0" />记为一时间单位</label> 
																<label><input id="add_timeHandle1" type="radio" name="timeChargeModel.timeHandle" fill="obj.timeChargeModel.timeHandle" class="icheck" value="1" />舍</label>
															</div>
														</div>
												 	 </div>
												 	<div class="form-group">
												 	 	<label class="col-md-3 control-label">对于费用总计小数部分的处理</label>
														<div class="col-md-6">
															<div class="icheck-inline"  >
																<label><input id="add_deciHandle0" type="radio" fill="obj.timeChargeModel.deciHandle" data-check="true" checked="checked"  name="timeChargeModel.deciHandle" class="icheck ic_default" value="0" />记为一元</label> 
																<label><input id="add_deciHandle1" type="radio" fill="obj.timeChargeModel.deciHandle" name="timeChargeModel.deciHandle"  class="icheck" value="1" />舍</label>
																<label><input id="add_deciHandle2" type="radio" fill="obj.timeChargeModel.deciHandle" name="timeChargeModel.deciHandle"  class="icheck" value="2" />不处理</label>
															</div>
													    </div>
												 	 </div>
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
														<div class="col-md-3 marginLeftDiv">
															<div class="spinner">
																<div class="input-group" style="width:130px;">
																	<input type="text" value="0" readonly="readonly" class="spinner-input form-control input-sm" fill="obj.meterChargeModel.meteringFree" name="meterChargeModel.meteringFree">
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
																<input  type="text" value="1.00" name="meterChargeModel.carMetering" fill="obj.meterChargeModel.carMetering" class="form-control input-sm touchspin">
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-4 control-label">大车计次收费</label>
														<div class="col-md-2">
															<div class="input-inline" style="width:150px;">
																<input  type="text" value="1.00" name="meterChargeModel.truckMetering" fill="obj.meterChargeModel.truckMetering" class="form-control input-sm touchspin">
															</div>
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
													<div class="form-group anchorGChargeDiv">
														<label class="col-md-4 control-label">长期收费规则组</label>
														<div class="col-md-6">
															<input disabled="disabled" class="form-control input-sm select2 anchorGroup"  fill="obj.baseRuleId" name="baseRuleId">
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
						<button type="button"  id="edit_save" class="btn green save">保存</button>
					</div>
				</div>
			</div>
	</div><!--修改 end-->
	
<script>

var edit=function(){
	
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	var bindbilling_model=function(){
		$('#editForm select[name="timeChargeModel.billingModel"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#editForm .timeCharge_periodDiv").hide();
				$("#editForm .timecharge_day_nightdiv").hide();
				$("#editForm .timeCharge_stardard").show();
				$("#editForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#editForm .timeCharge_stardard input").prop("disabled",false);
				$("#editForm .timecharge_day_nightdiv input").prop("disabled", true);
			}
			else if(value=='1'){
				$("#editForm .timeCharge_periodDiv").show();
				$("#editForm .timecharge_day_nightdiv").hide();
				$("#editForm .timeCharge_stardard").hide();
				$("#editForm .timeCharge_periodDiv input").prop("disabled", false);
				$("#editForm .timeCharge_stardard input").prop("disabled",true);
				$("#editForm .timecharge_day_nightdiv input").prop("disabled", true);
			}
			else if(value=='2'){
				$("#editForm .timecharge_day_nightdiv").show();
				$("#editForm .timeCharge_periodDiv").hide();
				$("#editForm .timeCharge_stardard").hide();
				$("#editForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#editForm .timeCharge_stardard input").prop("disabled",true);
				$("#editForm .timecharge_day_nightdiv input").prop("disabled", false);
			}
			
		})
	};
	
	var bindSubmit = function(){
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			$("#edit_save").prop('disabled',true);
			 $.ajax({
		       type: "POST",
		       url: pageUrl.charge_chargeRule_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"收费规则模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "收费规则模块");
		       	}
		       }
		   }); 
		}
		})
	}
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		if(data.success){
			$("#editForm",dialog).fill(data.object);//表单填充插件
			$("#editForm").find(".anchorGroup > a > span").eq(0).text(data.object.baseRuleName);
			dialog.modal('show');
		}
		else{
			alert(data.msg);
		}
	}

	var formatRentType=function(ele){
		var value = $(ele).val();
		if(value=="0"){
			$("#editForm .timeCharge_Div").show();
			$("#editForm .meterCharge_Div").hide();
			$("#editForm .anchorCharge_Div").hide();
			$("#editForm .timeCharge_Div input").prop("disabled", false);
			$("#editForm .meterCharge_Div input").prop("disabled", true); 
			$("#editForm .anchorCharge_Div input").prop("disabled", true);
			$("#baseRuleId").removeAttr("disabled");
		}
		else if(value=="1"){
			$("#editForm .timeCharge_Div").hide();
			$("#editForm .meterCharge_Div").show();
			$("#editForm .anchorCharge_Div").hide();
			$("#editForm .timeCharge_Div input").prop("disabled", true);
			$("#editForm .meterCharge_Div input").prop("disabled", false);
			$("#editForm .anchorCharge_Div input").prop("disabled", true);
			$("#baseRuleId").removeAttr("disabled");
		}
		else if(value=='2'){
			$("#editForm .timeCharge_Div").hide();
			$("#editForm .meterCharge_Div").hide();
			$("#editForm .anchorCharge_Div").show();
			$("#editForm .timeCharge_Div input").prop("disabled", true);
			$("#editForm .meterCharge_Div input").prop("disabled", true); 
			$("#editForm .anchorCharge_Div input").prop("disabled", false);
			$("#baseRuleId").prop("disabled", true);
		}
		else if(value=='3'){
			$("#editForm .timeCharge_Div").hide();
			$("#editForm .meterCharge_Div").hide();
			$("#editForm .anchorCharge_Div").hide();
			$("#editForm .timeCharge_Div input").prop("disabled", true);
			$("#editForm .meterCharge_Div input").prop("disabled", true); 
			$("#editForm .anchorCharge_Div input").prop("disabled", true);
			$("#baseRuleId").prop("disabled", true);
			$("#add .modal-dialog").attr("style","width:1200px");
			$("#addForm .chargeruleH").toggleClass("col-md-6");
		}
	} 
	
	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindSubmit();
			bindbilling_model();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		},
		formatRentType:function(ele){
			formatRentType(ele);
        }
	};
	
}();

</script>