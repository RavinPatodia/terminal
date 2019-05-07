<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:800px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加计时收费规则
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="addForm">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">计时收费规则编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="${getTimeChargeIdUrl}" readonly="readonly" class="form-control" name="timeChargeId" id="timeChargeIdAdd">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">规则名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="name">
									</div>
								</div>

								<!-- <div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<div class="icheck-list">
										  <input type="checkbox"  class="make-switch" data-on-text="启用" data-off-text="禁用" data-on-color="danger" name="enableFlag" data-off-color="default" checked>
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
								<div class="timeCharge_stardard">
									<div class="form-group">
											<label class="col-md-4 control-label">小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="carUnitTime">
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
											<div class="col-md-4">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0" name="carUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv" >
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="truckUnitTime">
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
													<input  type="text" value="0" name="truckUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
								</div>
		<!--标准模式结束-->
		<!--时段模式开始-->		
								<div class="timeCharge_periodDiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">时段一长度</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="timeOneLen">
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
											<label class="col-md-1 control-label" style="text-align: left">分钟</label>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">时段一：小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="carUnitTime">
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
													<input  type="text" value="0.00" name="carUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="truckUnitTime" >
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
													<input  type="text"  value="0.00" name="truckUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
								</div>
								<div class="timeCharge_periodDiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">时段二：小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightCarUnitTime">
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
													<input  type="text" value="0.00" name="nightCarUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0"  disabled="disabled" class="spinner-input form-control" name="nightTruckUnitTime">
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
													<input  type="text"  value="0.00" name="nightTruckUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
								</div>
		<!--时段模式结束-->
		<!--昼夜模式开始-->			
								<div class="timecharge_day_nightdiv" style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">白天长度</label>
											<div class="col-md-8">
												<div class="col-md-4 padding0">
													<div class="input-group">
														<input type="text"  class="form-control timepicker timepicker-24" name="beginDay">
														<span class="input-group-btn">
														<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
														</span>
													</div>
												</div>
												<label class="col-md-1 control-label">至</label>
												<div class="col-md-4 padding0">
													<div class="input-group">
														<input type="text"  class="form-control timepicker timepicker-24" name="endDay">
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
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="carUnitTime">
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
											<label class="col-md-1 leftTextLabel" >分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="carUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">白天大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="truckUnitTime">
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
											<label class="col-md-1 leftTextLabel" >分钟</label>
											<div class="col-md-2">
												<div class="input-inline" style="width:200px;">
													<input  type="text" value="0.00" name="truckUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
								</div>
								<div class="timecharge_day_nightdiv"  style="display:none;">
									<div class="form-group">
											<label class="col-md-4 control-label">夜间小车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightCarUnitTime">
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
													<input  type="text" value="0.00" name="nightCarUnitFee" class="form-control touchspin">
												</div>
											</div>
									</div>
									<div class="form-group">
											<label class="col-md-4 control-label">夜间大车单价</label>
											<div class="col-md-2 marginLeftDiv">
												<div class="spinner">
													<div class="input-group" style="width:130px;">
														<input type="text" readonly="readonly" value="0" disabled="disabled" class="spinner-input form-control" name="nightTruckUnitTime">
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
													<input  type="text" value="0.00" name="nightTruckUnitFee" class="form-control touchspin">
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
													<input type="text" readonly="readonly" value="0" class="spinner-input form-control" id="freeTime" name="freeTime">
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
										<label class="col-md-2 control-label" style="text-align: left">分钟内免费</label>
								</div>
								<div class="form-group">
										<label class="col-md-4 control-label">出场超时长</label>
										<div class="col-md-2 marginLeftDiv">
											<div class="spinner">
												<div class="input-group" style="width:130px;">
													<input type="text" readonly="readonly" value="0" class="spinner-input form-control" name="timeout">
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
											<label><input id="add_timeHandle0" type="radio" data-check="true" checked="checked" name="timeHandle" class="icheck ic_default" value="0" />记为一时间单位</label> 
											<label><input id="add_timeHandle1" type="radio" name="timeHandle" class="icheck" value="1" />舍</label>
										</div>
									</div>
							 	 </div>
							 	<div class="form-group">
							 	 	<label class="col-md-4 control-label">对于费用总计小数部分的处理</label>
									<div class="col-md-6">
										<div class="icheck-inline">
											<label><input id="add_deciHandle0" type="radio" data-check="true" checked="checked" name="deciHandle" class="icheck ic_default" value="0" />记为一元</label> 
											<label><input id="add_deciHandle1" type="radio" name="deciHandle" class="icheck" value="1" />舍</label>
											<label><input id="add_deciHandle2" type="radio" name="deciHandle" class="icheck" value="2" />不处理</label>
										</div>
								    </div>
							 	 </div>
							 </div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn">取消</button>
				<button type="button" id="add_save" class="btn green save">保存</button>
				<!-- <button type="button" class="btn green" onclick="reset()">icheck</button> -->
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
/* var reset = function(){
	$('#add_deciHandle1').iCheck('check');
} */

var add=function(){
	//动态生成收费规则编号
	var getTimeChargeId=function(){
		
		$("#addForm .timeCharge_periodDiv input").prop("disabled", true);
		$("#addForm .timecharge_day_nightdiv input").prop("disabled", true);
	};
	
	
	var bindbilling_model=function(){
		$('#addForm select[name="billingModel"]').on('change',function(ele){
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
		$('#add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$("#add_save").prop('disabled',true);
				$.ajax({
			        type: "POST",
			        url: "${addUrl}",
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
			        		toastr.success(obj.msg,"收费规则管理");
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
	}
	
	return {
        //main function to initiate the module
        //openModal: function () {            
        //	getChargeRuleId();
        //},
        init:function(){
        	bindbilling_model();
        	bindSubmit();
        }
    };
}();

</script>

