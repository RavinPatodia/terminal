<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog" style="width:700px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改长期收费组规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组编号</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control normal-input" name="anchorGroupId" fill="obj.anchorGroupId">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="name" fill="obj.name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组描述</label>
									<div class="col-md-6">
										<textarea class="form-control normal-input" id="describion" name="describion"  rows="3" maxlength="254" fill="obj.describion"></textarea>
									</div>
								</div>
								
								<div class="form-group">
											<label class="col-md-4 control-label">组内优惠规则</label>
											<div class="col-md-6">
												<select multiple="multiple"  class="multi-select"  name="anchorRents">
												</select>
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
			 $("#edit_save").prop('disabled',true);
			 $.ajax({
		       type: "POST",
		       url: pageUrl.charge_anchorGroup_edit,
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
		       		toastr.error(obj.msg,"收费规则模块");
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
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$('#editForm .multi-select').multiSelect("addParkOptionAndSelectOption",formObj.selects);
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