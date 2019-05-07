<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加摄像头
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">摄像头编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="@{pageUrl.device_camera_getCameraId}" readonly="readonly" class="form-control" name="cameraId" id="cameraIdAdd">
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">摄像头类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="type" id="camera_add_type" data-width="125px">
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
										<input type="hidden"  class="form-control select2 allDriveway"  name="drivewayId">
									</div>
								</div> -->
								<div class="form-group">
									<label class="col-md-4 control-label">品牌</label>
									<div class="col-md-6">
										<input  class="form-control select2 brand" name="brand">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">型号</label>
									<div class="col-md-6">
										<input class="form-control select2 model" name="model">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">描述</label>
									<div class="col-md-6">
										<input  class="form-control select2 describion" name="describion">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<input class="form-control select2 devicePosit" name="devicePosit">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6">
										<input class="form-control" name="ip">
									</div>
								</div>
								<div class="form-group lightState" style="display:none;">
									<label class="col-md-4 control-label">指示灯状态</label>
									<div class="col-md-6">
										<!-- <select class="bs-select form-control" name="lightState" id="camera_add_lightState" data-width="125px">
											<option value="0">空闲</option>
											<option value="1">占用</option>
										</select> -->
										<!-- <input type="checkbox"  class="make-switch" data-on-text="启用" data-off-text="禁用"  data-on-color="danger" name="lightStateBoolean" data-off-color="default" checked> -->
									</div>
								</div>
								<div class="form-group cameraState">
									<label class="col-md-4 control-label">摄像头状态</label>
									<div class="col-md-6">
										<input type="checkbox"  class="make-switch" data-on-text="启用" data-off-text="禁用"  data-on-color="danger" name="cameraStateBoolean" data-off-color="default" checked>
									</div>
								</div>
					</div>
				</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="camera_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">


var add=function(){
	

	/* var bindTypeChange=function(){
		$('#camera_add_type').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .allDriveway").prop("disabled", false);
				$("#addForm .name1").show();
				
			}else { 
				$("#addForm .allDriveway").prop("disabled", true);
				$("#addForm .name1").hide();
			}
		})
	} */
	
	var bindSubmit=function(){
		$('#camera_add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_camera_add,
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
	
	return {
        //main function to initiate the module
        //openModal: function () {            
        //	getcameraRuleId();
        //},
        init:function(){
        	bindTypeChange();
        	bindSubmit();
        }
    };
}();
</script>

