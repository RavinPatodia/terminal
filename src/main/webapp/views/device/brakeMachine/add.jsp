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
					<span class="glyphicon glyphicon-plus"></span> 添加闸机
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">闸机编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="${getDeviceIdUrl}"  readonly="readonly" class="form-control" name="deviceId" id="deviceIdAdd">
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="name" id="name">
										
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="posit" id="posit">
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">卡口</label>
									<div class="col-md-6">
										<input class="form-control select2 name" name="bayonetId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6">
										<input class="form-control" name="ip">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">开信号</label>
									<div class="col-md-6">
										<input class="form-control " name="open">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">关信号</label>
									<div class="col-md-6">
										<input class="form-control " name="close">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">端口号</label>
									<div class="col-md-6">
										<input class="form-control " name="port">
									</div>
								</div>
					</div>
				</div>
			</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="device_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">


var add=function(){
	
	
	var bindSubmit=function(){
		$('#device_add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//$('#addForm').bootstrapValidator('validate');
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: "${addUrl}",
			        data:/* {deviceId:deviceId,name:name,posit:posit,type:1,bayonetId:bayonetId,ip:ip,open:open,close:close}, */ $('#addForm').serialize(),// 你的formid 
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
			        		toastr.success("硬件模块", obj.msg);
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error("硬件模块", obj.msg);
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
        	bindSubmit();
        }
    };
}();
</script>

