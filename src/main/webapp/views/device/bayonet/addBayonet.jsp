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
					<span class="glyphicon glyphicon-plus"></span> 添加卡口
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">卡口编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax=pageUrl.device_bayonet_getBayonetId readonly="readonly" class="form-control" name="bayonetId" id="bayonetIdAdd">
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input  class="form-control select2 name" name="name" id="name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">停车场</label>
									<div class="col-md-6">
										<input  class="form-control select2 parkId" name="parkId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">卡口位置</label>
									<div class="col-md-6">
										<input  class="form-control select2 posit" name="posit">
									</div>
								</div>
					</div>
				</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="bayonet_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">


var add=function(){
	
	var validateBayonetId = function(){
		$('#name').blur(function(){
			var bayonetId = $(this).val();
				$.ajax({
					url:pageUrl.device_bayonet_getSameBayonetId,
					type:"POST",
					data:{bayonetId:bayonetId},
					success:function(data){
						var obj = data;
						if(!obj.success){
							toastr.options = {
									  "positionClass": "toast-top-center",
							}
							toastr.info("【"+$('#name').val()+"】"+obj.msg);
							$("#name").val("");
						}
					},
					error:function(){
						toastr.info("数据连接错误，无法校验卡口编号！");
					}
				});
		});
	}
	
	
	var bindSubmit=function(){
		$('#bayonet_add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_bayonet_add,
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
        //	getBayonetRuleId();
        //},
        init:function(){
        	bindSubmit();
        	validateBayonetId();
        }
    };
}();
</script>

