<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改优惠规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则编号</label>
										<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control normal-input" name="dctId" fill="obj.dctId"></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则名称</label>
										<div class="col-md-6"><input type="text" class="form-control normal-input" name="dctName" fill="obj.dctName"></div>
									</div>
									 <div class="form-group">
										<label class="col-md-4 control-label">优惠规则类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control " name="type"  id="dctRule_edit_Type" fill="obj.type" style="display: none;">
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
												<input type="text" readonly="readonly" class="form-control timepicker timepicker-24 dctRule_time" name="beginTime" fill="obj.beginTime">
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
												<input type="text" readonly="readonly" class="form-control timepicker timepicker-24 dctRule_time" name="endTime" fill="obj.endTime">
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
												<input type="text" class="form-control dctRule_date" readonly="readonly" name="beginDate" fill="obj.beginDate">
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
												<input type="text" class="form-control dctRule_date" readonly="readonly" name="endDate" fill="obj.endDate">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group dctRule_weekendDiv" style="display:none;">
										<label class="col-md-4 control-label">双休日类型</label>
										<div class="col-md-6">
											<div class="icheck-list dctRule_weekend">
												<label><input id="add_saturday" type="radio" name="weekendType" checked="checked" class="icheck dctRule_weekend ic_default" value="0"/> 周六</label>
												
												<label><input id="add_sunday" type="radio" name="weekendType" class="icheck dctRule_weekend" value="1"/> 周天</label>
												
												<label><input id="add_sat_sun" type="radio" name="weekendType" class="icheck dctRule_weekend" value="2"/> 周六+周日</label>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">优惠形式</label>
										<div class="col-md-6">
											<select class="bs-select form-control " name="dctType" fill="obj.dctType">
									            <option value="0">减免金额</option>
									            <option value="1">折扣</option>
									            <option value="2">全免</option>
											</select>
										</div>
									</div>
									
									<div class="form-group discountDiv" style="display:none;">
										<label class="col-md-4 control-label">折扣</label>
										<div class="col-md-4">
											<div class="input-inline input-medium">
												<input type="text" disabled="disabled" value="0.00" name="dctPer" class="form-control discount" fill="obj.dctPer">
											</div>
										</div>
									</div>
									
									<div class="form-group dctFeeDiv">
										<label class="col-md-4 control-label">金额</label>
										<div class="col-md-4">
											<div class="input-inline" style="width:200px;">
												<input  type="text" value="1" name="dctPer" fill="obj.dctPer" class="form-control touchspin dctFee">
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
					<button type="button" data-dismiss="modal" class="btn">取消</button>
					<button type="button" class="btn green save" id="edit_save">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
<script>
var edit=function(){
	
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	
	var bindSubmit = function(){
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			$("#edit_save").prop('disabled',true);
			 $.ajax({
		       type: "POST",
		       url: pageUrl.charge_dctRule_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"优惠规则模块");
		       		//alert(obj.msg);
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
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var rentType = formObj.rentType;
		var enableFlag = formObj.enableFlag;
		var baseRuleName;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
		}
	}
	
	var bindDctRuleTypeChange=function(){
		$('#editForm select[name="type"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#editForm .dctRule_dateDiv").hide();
				$("#editForm .dctRule_timeDiv").hide();
				$("#editForm .dctRule_weekendDiv").hide();
				$("#editForm .dctRule_date").prop("disabled", true);
				$("#editForm .dctRule_time").prop("disabled", true);
				$("#editForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='1'){//节假日
				$("#editForm .dctRule_dateDiv").hide();
				$("#editForm .dctRule_timeDiv").hide();
				$("#editForm .dctRule_weekendDiv").hide();
				$("#editForm .dctRule_date").prop("disabled", true);
				$("#editForm .dctRule_time").prop("disabled", true);
				$("#editForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='2'){//双休日
				$("#editForm .dctRule_dateDiv").hide();
				$("#editForm .dctRule_timeDiv").hide();
				$("#editForm .dctRule_weekendDiv").show();
				$("#editForm .dctRule_date").prop("disabled", true);
				$("#editForm .dctRule_time").prop("disabled", true);
				$("#editForm .dctRule_weekend").prop("disabled",false);
			}
			else if(value=='3'){//规定时间
				$("#editForm .dctRule_dateDiv").hide();
				$("#editForm .dctRule_timeDiv").show();
				$("#editForm .dctRule_weekendDiv").hide();
				$("#editForm .dctRule_date").prop("disabled", true);
				$("#editForm .dctRule_time").prop("disabled", false);
				$("#editForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='4'){//规定日期
				$("#editForm .dctRule_dateDiv").show();
				$("#editForm .dctRule_timeDiv").hide();
				$("#editForm .dctRule_weekendDiv").hide();
				$("#editForm .dctRule_date").prop("disabled", false);
				$("#editForm .dctRule_time").prop("disabled", true);
				$("#editForm .dctRule_weekend").prop("disabled",true);
			}
			else if(value=='5'){//续费
				$("#editForm .dctRule_dateDiv").hide();
				$("#editForm .dctRule_timeDiv").hide();
				$("#editForm .dctRule_weekendDiv").hide();
				$("#editForm .dctRule_date").prop("disabled", true);
				$("#editForm .dctRule_time").prop("disabled", true);
				$("#editForm .dctRule_weekend").prop("disabled",true);
			}
		})
	}
	var bindDctTypeChange=function(){
		$('#editForm select[name="dctType"]').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#editForm .dctFeeDiv").show();
				$("#editForm .discountDiv").hide();
				$("#editForm .discountDiv input").prop("disabled",true);
				$("#editForm .dctFeeDiv input").prop("disabled",false);
			}
			else if(value=='1'){
				$("#editForm .discountDiv").show();
				$("#editForm .dctFeeDiv").hide();
				$("#editForm .dctFeeDiv input").prop("disabled",true);
				$("#editForm .discountDiv input").prop("disabled",false);
			}
			else if(value=='2'){
				$("#editForm .discountDiv").hide();
				$("#editForm .dctFeeDiv").hide();
				$("#editForm .discountDiv input").prop("disabled",true);
				$("#editForm .dctFeeDiv input").prop("disabled",true);
			}
		})
	}
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var rentType = formObj.rentType;
		var baseRuleName;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
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
			bindDctRuleTypeChange();
			bindDctTypeChange();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();
</script>