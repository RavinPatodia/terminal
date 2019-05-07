<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加计次收费规则
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="addForm">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">计次收费规则编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="${getMeterChargeIdUrl}" readonly="readonly" class="form-control" name="meterChargeId" id="meterChargeIdAdd">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">计次收费规则名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="name" maxlength="30">
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
								        <label class="col-md-4 control-label">计次免费时间</label>
										<div class="col-md-3 marginLeftDiv">
											<div class="spinner">
												<div class="input-group" style="width:130px;">
													<input type="text" value="0" readonly="readonly" class="spinner-input form-control" name="freeTime">
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
										<label class="col-md-2 leftTextLabel">分钟</label>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">小车计次收费</label>
									<div class="col-md-2">
										<div class="input-inline" style="width:200px;">
											<input  type="text" value="0.00" name="carMetering" class="form-control touchspin">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">大车计次收费</label>
									<div class="col-md-2">
										<div class="input-inline" style="width:200px;">
											<input  type="text" value="0.00" name="truckMetering" class="form-control touchspin">
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
<script type="text/javascript">
var add=function(){
	
	var lock = false;

	
	var bindSubmit=function(){
		$('#add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$("#add_save").prop('disabled',true);
				if (lock) {return;}
				lock = true;
				$.ajax({
			        type: "POST",
			        url: "${addUrl}",
			        data:$('#addForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			            //$('#meterCharge_add_save').attr("disabled",false);
			            lock = false;
			        },
			        success: function(data) {
			        	lock = false;
			        	if(data==null||data==""){
			        		return;
			        	}
			        	var obj = data;
			        	if(!obj.validateResult){
			        		//alert(obj.validateMsg);
			        		$("#addForm").clearValidator();
			        		$("#addForm").fillValidator(obj.validateMsg);
			        		//alert(obj.msg);
			        		//$('#meterCharge_add_save').attr("disabled",false);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add").modal('hide');
			        		toastr.success(obj.msg,"收费规则管理");
			        		//$('#meterCharge_add_save').attr("disabled",false);
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(obj.msg, "收费规则管理");
			        		//$('#meterCharge_add_save').attr("disabled",false);
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
        	bindSubmit();
        }
    };
	
	
}();
</script>

