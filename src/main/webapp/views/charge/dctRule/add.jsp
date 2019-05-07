<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-plus" ></span>
							 添加优惠规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="addForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则编号</label>
										<div class="col-md-6">
										<input type="text" data-ajax="@{pageUrl.charge_dctRule_getDctRuleId}" class="form-control normal-input" readonly="readonly" name="dctId" id="dctRule_add_dctRuleId"></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则名称</label>
										<div class="col-md-6"><input type="text" class="form-control normal-input" name="dctName" maxlength="30"></div>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control "  name="type" style="display: none;">
									            <option value="0">会员生日</option>
									            <option value="1">节假日</option>
									            <option value="2">双休日</option>
												<option value="3">规定时间段</option>
									            <option value="4">规定日期段</option>
									            <option value="5">续办折扣</option>
											</select>
										</div>
									</div>
                                   
									<div class="form-group dctRule_timeDiv"  style="display:none;">
										<label class="col-md-4 control-label">起始时间</label>
										<div class="col-md-6">
											<div class="input-group">
												<input type="text" readonly="readonly" class="form-control timepicker timepicker-24 dctRule_time" name="beginTime">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group dctRule_timeDiv" style="display:none;">
										<label class="col-md-4 control-label">结束时间</label>
										<div class="col-md-6">
											<div class="input-group">
												<input type="text" readonly="readonly" class="form-control timepicker timepicker-24 dctRule_time" name="endTime">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group dctRule_dateDiv" style="display:none;">
										<label class="col-md-4 control-label">起始日期</label>
										<div class="col-md-6">
											<div class="input-group input-medium date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input type="text" class="form-control dctRule_date" readonly="readonly" name="beginDate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group dctRule_dateDiv" style="display:none;">
										<label class="col-md-4 control-label">结束日期</label>
										<div class="col-md-6">
											<div class="input-group input-medium date date-picker" data-date="12-02-2012" data-date-format="dd-mm-yyyy" data-date-viewmode="years">
												<input type="text" class="form-control dctRule_date" readonly="readonly" name="endDate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group dctRule_weekendDiv" style="display:none;">
										<label class="col-md-4 control-label">双休日类型</label>
										<div class="col-md-6">
											<div class="icheck-list">
												<label><input id="add_saturday" type="radio" name="weekendType" checked="checked" class="icheck dctRule_weekend ic_default" value="0"/> 周六</label>
												
												<label><input id="add_sunday" type="radio" name="weekendType" class="icheck dctRule_weekend" value="1"/> 周天</label>
												
												<label><input id="add_sat_sun" type="radio" name="weekendType" class="icheck dctRule_weekend" value="2"/> 周六+周日</label>
											</div>
										</div>
									</div>
									 <div class="form-group">
										<label class="col-md-4 control-label">优惠形式</label>
										<div class="col-md-6">
											<select class="bs-select form-control " name="dctType">
									            <option value="0">减免金额</option>
									            <option value="1">折扣</option>
									            <option value="2">全免</option>
											</select>
										</div>
									</div>
									<div class="form-group dctFeeDiv">
										<label class="col-md-4 control-label">金额</label>
										<div class="col-md-4">
											<div class="input-inline" style="width:200px;">
												<input  type="text" value="1" name="dctPer" class="form-control touchspin dctFee"/>
											</div>
										</div>
									</div>

									<div class="form-group discountDiv" style="display:none;">
										<label class="col-md-4 control-label">折扣</label>
										<div class="col-md-4">
											<div class="input-inline input-medium">
												<input type="text" disabled="disabled" value="0.00" name="dctPer" class="form-control discount"/>
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
					<button type="button" class="btn green save" id="add_save">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!--添加 end-->
<script>


var add=function(){
	
	
	var bindDctRuleTypeChange=function(){
		$('#addForm select[name="type"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .dctRule_dateDiv").hide();
				$("#addForm .dctRule_timeDiv").hide();
				$("#addForm .dctRule_weekendDiv").hide();
				$("#addForm .dctRule_date").prop("disabled", true);
				$("#addForm .dctRule_time").prop("disabled", true);
				$("#addForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='1'){//节假日
				$("#addForm .dctRule_dateDiv").hide();
				$("#addForm .dctRule_timeDiv").hide();
				$("#addForm .dctRule_weekendDiv").hide();
				$("#addForm .dctRule_date").prop("disabled", true);
				$("#addForm .dctRule_time").prop("disabled", true);
				$("#addForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='2'){//双休日
				$("#addForm .dctRule_dateDiv").hide();
				$("#addForm .dctRule_timeDiv").hide();
				$("#addForm .dctRule_weekendDiv").show();
				$("#addForm .dctRule_date").prop("disabled", true);
				$("#addForm .dctRule_time").prop("disabled", true);
				$("#addForm .dctRule_weekend").prop("disabled",false);
			}
			else if(value=='3'){//规定时间
				$("#addForm .dctRule_dateDiv").hide();
				$("#addForm .dctRule_timeDiv").show();
				$("#addForm .dctRule_weekendDiv").hide();
				$("#addForm .dctRule_date").prop("disabled", true);
				$("#addForm .dctRule_time").prop("disabled", false);
				$("#addForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='4'){//规定日期
				$("#addForm .dctRule_dateDiv").show();
				$("#addForm .dctRule_timeDiv").hide();
				$("#addForm .dctRule_weekendDiv").hide();
				$("#addForm .dctRule_date").prop("disabled", false);
				$("#addForm .dctRule_time").prop("disabled", true);
				$("#addForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='5'){//续费
				$("#addForm .dctRule_dateDiv").hide();
				$("#addForm .dctRule_timeDiv").hide();
				$("#addForm .dctRule_weekendDiv").hide();
				$("#addForm .dctRule_date").prop("disabled", true);
				$("#addForm .dctRule_time").prop("disabled", true);
				$("#addForm .dctRule_weekend").prop("disabled",true);
			}
		})
	}
	var bindDctTypeChange=function(){
		$('#addForm select[name="dctType"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .dctFeeDiv").show();
				$("#addForm .discountDiv").hide();
				$("#addForm .dctFeeDiv input").prop("disabled", false);
				$("#addForm .discountDiv input").prop("disabled", true);
			}
			else if(value=='1'){
				$("#addForm .discountDiv").show();
				$("#addForm .dctFeeDiv").hide();
				$("#addForm .discountDiv input").prop("disabled", false);
				$("#addForm .dctFeeDiv input").prop("disabled", true);
			}
			else if(value=='2'){
				$("#addForm .discountDiv").hide();
				$("#addForm .dctFeeDiv").hide();
				$("#addForm .discountDiv input").prop("disabled", true);
				$("#addForm .dctFeeDiv input").prop("disabled", true);
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
			        url: pageUrl.charge_dctRule_add,
			        data: $("#addForm").serialize(),// 你的formid
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
			        		toastr.success(obj.msg,"优惠规则模块");
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(obj.msg,"优惠规则模块");
			        		//alert(obj.msg);
			        	}
			        }
				  }); 
			}
		})
	}
	
	return {
        init:function(){
        	bindSubmit();
        	bindDctRuleTypeChange();
        	bindDctTypeChange();
        }
    };
}();
</script>