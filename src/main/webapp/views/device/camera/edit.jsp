<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改摄像头信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<input type="hidden" class="form-control" name="id" fill="ss.id">
									<label class="col-md-4 control-label">摄像头编号 </label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="cameraId" fill="obj.cameraId">
									</div>
								</div>

								<div class="form-group type">
									<label class="col-md-4 control-label">摄像头类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="type" id="camera_edit_type"  data-width="125px" fill="obj.type">
											<option value="0">车道-识别</option>
											<option value="1">车道-车身</option>
											<option value="2">过道</option>
											<option value="3">车位</option>
										</select>
									</div>
								</div>
								<!-- <div class="form-group name1">
									<label class="col-md-4 control-label">车道</label>
									<div class="col-md-6">
										<input class="form-control select2 allDriveway"  type="hidden" name="drivewayId" fill="obj.drivewayId">
									</div>
								</div> -->
							<div class="form-group">
									<label class="col-md-4 control-label">品牌</label>
									<div class="col-md-6">
										<input  class="form-control select2 brand" name="brand" fill="obj.brand">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">型号</label>
									<div class="col-md-6">
										<input class="form-control select2 model" name="model" fill="obj.model">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">描述</label>
									<div class="col-md-6">
										<input  class="form-control select2 describion" name="describion" fill="obj.describion">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<input class="form-control select2 devicePosit" name="devicePosit"  fill="obj.devicePosit">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<input class="form-control input_ipv4" type="text" maxlength="15" size="15" name="ip" fill="obj.ip">
									</div>
								</div>
								<div class="form-group cameraState">
									<label class="col-md-4 control-label">摄像头状态</label>
									<div class="col-md-6">
									    <input type="checkbox" fill="obj.cameraState"  class="make-switch" data-on-text="启用" data-off-text="禁用"  data-on-color="danger" name="cameraStateBoolean" data-off-color="default" checked>
									</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				   <!--  <button type="button" onclick="changeICheck()" class="btn">icheck Test</button>
				    <button type="button" onclick="changeICheck2()" class="btn">false check</button> -->
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="camera_edit_save" class="btn green">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>
var bindTypeChange=function(){
	$('#type').on('change',function(ele){
		var value= $(this).val();
		if(value=='0'){
			$(".bayonetId").show();

			$('select[name=bayonetId]').val('0');
		}
		else{
			$(".bayonetId").hide();

			$('select[name=bayonetId]').val('-1');
		}
	});
	/* $('#camera_edit_type').on('change',function(ele){
		var value= $(this).val();
		if(value=='0'){
			$("#editForm .allDriveway").prop("disabled", false);
			$("#editForm .name1").show();
			
		}else { 
			$("#editForm .allDriveway").prop("disabled", true);
			$("#editForm .name1").hide();
		}
	}) */
}

var edit=function(){
	
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	
	var bindSubmit = function(){
		$("#camera_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_camera_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"硬件模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg,"硬件模块");
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
		var type = formObj.type;
		var enableFlag = formObj.enableFlag;
		var baseRuleName;
		//TODO 将icheck晴空
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#editForm").find(".type > a > span").eq(0).text(formObj.type);
			if(type==0){
				$("#editForm").find(".allDriveway > a > span").eq(0).text(formObj.drivewayName);
			}
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
			bindTypeChange();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();

</script>