<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改车辆信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
										<div class="form-group">
									<label class="col-md-3 control-label">车牌号码</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="licensePlate" fill="obj.licensePlate">	
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车牌类型</label>
									<div class="col-md-6">
										<input class="form-control select2 car_type" name="type" fill="obj.type">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆颜色</label>
									<div class="col-md-6">
										<input class="form-control select2 car_color" name="carColor" fill="obj.carColor">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆型号</label>
									<div class="col-md-6">
										<input class="form-control select2 car_module" name="carModel" fill="obj.carModel">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" fill="obj.carType" name="carType">
											<option value="0">小车</option>
											<option value="1">大车</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">客户</label>
									<div class="col-md-6">
										<input class="form-control select2 user_Name" name="userPK" fill="obj.userPK">
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
					<button type="button"  id="car_edit_save" class="btn green">保存</button>
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
		$("#car_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
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
		       		toastr.success(obj.msg,"收费规则模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "收费规则模块");
		       	}
		       }
		   }); 
		})
	}
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var baseRuleName;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#editForm").find(".car_type > a > span").eq(0).text(formObj.type);
			$("#editForm").find(".car_color > a > span").eq(0).text(formObj.carColor);
			$("#editForm").find(".car_module > a > span").eq(0).text(formObj.carModel);
			$("#editForm").find(".user_Name > a > span").eq(0).text(formObj.uacc);
			$('#editForm input[name="licensePlate"]').val(formObj.licensePlate);
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