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
					<span class="glyphicon glyphicon-plus"></span> 添加车位锁
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">车位</label>
									<div class="col-md-6">
										<input class="form-control select2 pspId" id="pspId" name="pspId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">车位锁编号</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="lockId" id="lockIdAdd">
										
									</div>
								</div>
								
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
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6">
										<input class="form-control" name="ip">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">端口号</label>
									<div class="col-md-6">
										<input class="form-control select2 port" type="text" maxlength="15" size="15" name="port">
									</div>
								</div>
							   <!--  <div class="form-group lockState" >
									<label class="col-md-4 control-label">车位锁状态</label>
									<div class="col-md-6">
										<input type="checkbox"  class="make-switch" data-on-text="开启" data-off-text="关闭"  data-on-color="danger" name="state" data-off-color="default" checked>
									</div>
								</div>  -->
					</div>
				</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="lock_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">


var add=function(){
	
	var getLockId = function(){
			$('#pspId').change(function(){
			   var value= "Lock-"+$('.select2-hidden-accessible').text();
			   $('#lockIdAdd').val(value);
			})
	}

	
	var bindSubmit=function(){
		$('#lock_add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_pSpLock_add,
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
			        	}
			        }
				  }); 
			}
		})
	}
	
	return {
        //main function to initiate the module
        //openModal: function () {            
        //	getLockRuleId();
        //},
        init:function(){
        	bindSubmit();
        	getLockId();
        }
    };
}();
</script>

