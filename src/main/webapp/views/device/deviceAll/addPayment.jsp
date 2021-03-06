<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="add2" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加缴费机信息
				</h4>
			</div>
			<div class="modal-body">
			<form class="form-horizontal"  role="form" id="addPayment">
				<div class="row">
					<div class="col-md-12">
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-4 control-label">缴费机编号</label>
								<div class="col-md-6">
									<input type="text" data-ajax="@{pageUrl.device_deviceAll_getDeviceId}"  readonly="readonly" class="form-control" name="deviceId" id="deviceIdAdd">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">缴费机名称</label>
								<div class="col-md-6">
									<input type="text" class="form-control normal-input" name="name">
								</div>  
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">位置</label>
								<div class="col-md-6">
									<input  class="form-control" name="posit">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">IP</label>
								<div class="col-md-6">
									<input class="form-control" name="ip">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">端口号</label>
								<div class="col-md-6">
									<input class="form-control" name="port">
								</div>
							</div>
							<input type="hidden" name="type" value="2" />
						</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green save" id="addPayment_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
var addPayment=function(){

	var bindSubmit=function(){
		$('#addPayment_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addPayment")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$("#addPayment_save").prop('disabled',true);
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_deviceAll_add,
			        data:$('#addPayment').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	if(data.success){
			        		$("#add2").modal('hide');
			        		toastr.success(data.msg,"硬件模块");
			        		oTable2.fnDraw();
			        	}
			        	else{
			        		toastr.error(data.msg,"硬件模块");
			        	}
			        }
				  }); 
			}
		})
	} 
	
	return {
        init:function(){
        	bindSubmit();
        }
    };
}();
</script>

