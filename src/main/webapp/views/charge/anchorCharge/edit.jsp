<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改长期收费规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则编号</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control normal-input" name="anchorRentId" fill="obj.anchorRentId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则名称</label>
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
										<label class="col-md-4 control-label">月数</label>
										<div class="col-md-3 marginLeftDiv">
											<div class="monthSpinner">
												<div class="input-group" style="width:150px;">
													<input type="text" value="1" class="spinner-input form-control" name="month" maxlength="2" fill="obj.month">
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
										<label  class="col-md-2 leftTextLabel">个月</label>
								</div>
								<div class="form-group">
										<label class="col-md-4 control-label">费用</label>
										<div class="col-md-5 marginLeftDiv">
											<div class="input-inline input-medium">
												<input  type="text" value="1" name="fee" class="form-control touchspin" fill="obj.fee">
											</div>
										</div>
										<label class="col-md-1 leftTextLabel">元</label>
								</div>
								<div class="form-group">
										<label class="col-md-4 control-label">附加费用</label>
										<div class="col-md-5 marginLeftDiv">
											<div class="input-inline input-medium">
												<input  type="text" value="1" name="addFee"  class="form-control touchspin" fill="obj.addFee">
											</div>
										</div>
										<label class="col-md-1 leftTextLabel">元</label>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
							</div>
							</form>
						</div>
					</div>
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
	
	var bindSubmit = function(){
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
				//禁用提交按钮
			 $("#edit_save").prop("disabled",true);
			 $.ajax({
		       type: "POST",
		       url: pageUrl.charge_anchorRent_edit,
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
		       		//alert(obj.msg);
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
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var rentType = formObj.rentType;
		var baseRuleName;
		//TODO 将icheck晴空
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
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();
</script>