<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改计时收费规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">计时收费规则编号</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="timeChargeId"  fill="obj.timeChargeId">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">规则名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="name" fill="obj.name">
									</div>
								</div>

								<!-- <div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<div class="icheck-list">
										  <input type="checkbox" fill="obj.enableFlag"  class="make-switch" data-on-text="启用" data-off-text="禁用" data-on-color="danger" name="enableFlag" data-off-color="default" checked>
										</div>
									</div>
								</div> -->
                                <div class="form-group">
									<label class="col-md-4 control-label">收费模式</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="billingModel"  data-width="125px">
												<option value="0">标准模式</option>
												<option value="1">时段模式</option>
												<option value="2">昼夜模式</option>
												<option value="3">昼夜模式二</option>
										</select>
									</div>
								</div>
		<!--标准模式开始-->					
								<div class="form-group timeCharge_stardard">
									<div class="form-group">
											<label class="col-md-4 control-label">小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="carUnitTime" fill="obj.carUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0" name="carUnitFee" class="form-control touchspin" fill="obj.carUnitFee">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="truckUnitTime" fill="obj.truckUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0" name="truckUnitFee" class="form-control touchspin" fill="obj.truckUnitFee">
												</div>
											</div>
									</div>
								</div>
		<!--标准模式结束-->
		<!--时段模式开始-->		
								<div class="form-group timeCharge_periodDiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">时段一长度</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0"  disabled="disabled" class="spinner-input form-control" name="timeOneLen" fill="obj.timeOneLen">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">时段一：小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0"  disabled="disabled"class="spinner-input form-control" name="carUnitTime" fill="obj.carUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="carUnitFee" class="form-control touchspin" fill="obj.carUnitFee">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="truckUnitTime" fill="obj.truckUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="truckUnitFee" class="form-control touchspin" fill="obj.truckUnitFee">
												</div>
											</div>
									</div>
								</div>
								<div class="form-group timeCharge_periodDiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">时段二：小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightCarUnitTime" fill="obj.nightCarUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="nightCarUnitFee" class="form-control touchspin" fill="obj.nightCarUnitFee">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightTruckUnitTime" fill="obj.nightTruckUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel" style="text-align: left">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="nightTruckUnitFee" class="form-control touchspin" fill="obj.nightTruckUnitFee">
												</div>
											</div>
									</div>
								</div>
		<!--时段模式结束-->
		<!--昼夜模式开始-->			
								<div class="form-group timecharge_day_nightdiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">白天长度</label>
											<div class="col-md-8">
												<div class="col-md-4 padding0">
													<div class="input-group">
														<input type="text"  class="form-control timepicker timepicker-24" name="beginDay" fill="obj.beginDay">
														<span class="input-group-btn">
														<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
														</span>
													</div>
												</div>
												<label class="col-md-1 control-label">至</label>
												<div class="col-md-4 padding0">
													<div class="input-group">
														<input type="text"  class="form-control timepicker timepicker-24" name="beginDay" fill="obj.beginDay">
														<span class="input-group-btn">
														<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
														</span>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">白天小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="carUnitTime" fill="obj.carUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="carUnitFee" class="form-control touchspin" fill="obj.carUnitFee">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">白天大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="truckUnitTime" maxlength="2" fill="obj.truckUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="truckUnitFee" class="form-control touchspin" fill="obj.truckUnitFee">
												</div>
											</div>
									</div>
								</div>
								<div class="form-group timecharge_day_nightdiv"  style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">夜间小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightCarUnitTime" maxlength="2" fill="obj.nightCarUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="nightCarUnitFee" class="form-control touchspin" fill="obj.nightCarUnitFee">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">夜间大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightTruckUnitTime" maxlength="2" fill="obj.nightTruckUnitTime">
														<div class="spinner-buttons input-group-btn">
															<button type="button" class="btn spinner-up default">
															<i class="fa fa-angle-up"></i>
															</button>
															<button type="button" class="btn spinner-down default">
															<i class="fa fa-angle-down"></i>
															</button>
														</div>
													</div>
												</div>
											</div>
											<label class="col-md-1 leftTextLabel">分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="nightTruckUnitFee" class="form-control touchspin" fill="obj.nightTruckUnitFee">
												</div>
											</div>
									</div>
								</div>
		<!--昼夜模式结束-->							
								
								<div class="form-group">
										<label class="col-md-4 control-label">进场免费时间</label>
										<div class="col-md-2 marginLeftDiv">
											<div class="spinner">
												<div class="input-group" style="width:130px;">
													<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="freeTime" maxlength="2" fill="obj.freeTime">
													<div class="spinner-buttons input-group-btn">
														<button type="button" class="btn spinner-up default">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down default">
														<i class="fa fa-angle-down"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<label class="col-md-2 leftTextLabel">分钟内免费</label>
								</div>
								<div class="form-group">
										<label class="col-md-4 control-label">出场超时长</label>
										<div class="col-md-2 marginLeftDiv">
											<div class="spinner">
												<div class="input-group" style="width:130px;">
													<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="timeout" maxlength="2" fill="obj.timeout">
													<div class="spinner-buttons input-group-btn">
														<button type="button" class="btn spinner-up default">
														<i class="fa fa-angle-up"></i>
														</button>
														<button type="button" class="btn spinner-down default">
														<i class="fa fa-angle-down"></i>
														</button>
													</div>
												</div>
											</div>
										</div>
										<label class="col-md-1 leftTextLabel">分钟</label>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">对于不足于一时间单位的处理</label>
									<div class="col-md-6">
										<div class="icheck-inline">
											<label><input id="edit_timeHandle0" type="radio" data-check="true" checked="checked" name="timeHandle" class="icheck" value="0" />记为一时间单位</label> 
											<label><input id="edit_timeHandle1" type="radio"  name="timeHandle" class="icheck" value="1" />舍</label>
										</div>
									</div>
							 	 </div>
							 	<div class="form-group">
							 	 	<label class="col-md-4 control-label">对于费用总计小数部分的处理</label>
									<div class="col-md-6">
										<div class="icheck-inline">
											<label><input id="edit_deciHandle0" type="radio" checked="checked" data-check="true" name="deciHandle" class="icheck" value="0" />记为一元</label> 
											<label><input id="edit_deciHandle1" type="radio" name="deciHandle" class="icheck" value="1" />舍</label>
											<label><input id="edit_deciHandle2" type="radio" name="deciHandle" class="icheck" value="2" />不处理</label>
										</div>
								    </div>
							 	 </div>
							 	<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
							 </div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button" id="edit_save" class="btn green save">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>
var edit=function(){
	
	//验证表单的方法
	
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	};
	
	var bindSubmit = function(){
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			$("#edit_save").prop('disabled',true);
			 $.ajax({
		       type: "POST",
		       url: "${editUrl}",
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"收费规则管理");
		       		//alert(obj.msg);
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "收费规则管理");
		       		//alert(obj.msg);
		       	}
		       }
		   }); 
		}
		})
	};
	
	var bindbilling_model=function(){
		$('#editForm select[name="billingModel"]').on('change',function(ele){
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
			else if(value=='3'){
				$("#editForm .timecharge_day_nightdiv").show();
				$("#editForm .timeCharge_periodDiv").hide();
				$("#editForm .timeCharge_stardard").hide();
				$("#editForm .timeCharge_periodDiv input").prop("disabled", true);
				$("#editForm .timeCharge_stardard input").prop("disabled",true);
				$("#editForm .timecharge_day_nightdiv input").prop("disabled", false);
			}
		})
	};
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var billing_model = formObj.billingModel;
		var deciHandle=formObj.deciHandle;
		var timeHandle=formObj.timeHandle;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$('#editForm select[name=billingModel]').val(billing_model).trigger("change");;
			if(deciHandle==0)
				$('#edit_deciHandle0').iCheck('check');
			else if(deciHandle==1)
				$('#edit_deciHandle1').iCheck('check');
			else if(deciHandle==2)
				$('#edit_deciHandle2').iCheck('check');
			if(timeHandle==0)
				$('#edit_timeHandle0').iCheck('check');
			else if(timeHandle==1)
				$('#edit_timeHandle1').iCheck('check');
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
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
		}
	};
	
}();
</script>