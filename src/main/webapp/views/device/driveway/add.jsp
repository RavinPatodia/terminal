<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="add" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加车道信息
				</h4>
			</div>
			<div class="modal-body">
			<form class="form-horizontal"  role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-4 control-label">车道名称</label>
								<div class="col-md-6">
									<input type="text" class="form-control normal-input" name="name">
								</div>  
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">车道方向</label>
								<div class="col-md-6">
									<div class="icheck-inline">
										<label><input id="add_direction0" type="radio" data-check="true" checked="checked" name="direction" class="icheck ic_default" value="0" />入口</label> 
										<label><input id="add_direction1" type="radio" name="direction" class="icheck" value="1" />出口</label>
									</div>
								</div>
						 	 </div>
							<div class="form-group">
								<label class="col-md-4 control-label">对应卡口</label>
								<div class="col-md-6">
									<input class="form-control select2 bayonetName"  id="bayonetName" name="bayonetName">
								</div>  
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">放行规则</label>
								<div class="col-md-6">
									<div class="icheck-list">
										<label><input type="checkbox" class="icheck" name="releaseRule" value="临时用户放行"> 临时用户放行</label>
										<label><input type="checkbox" class="icheck" name="releaseRule" value="普通用户放行"> 普通用户放行</label>
										<label><input type="checkbox" class="icheck" name="releaseRule" value="长期用户放行"> 长期用户放行 </label>
									</div>
								</div>  
							</div>
						</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green save" id="add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">


var add=function(){

	var bindSubmit=function(){
		$('#add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$("#add_save").prop('disabled',true);
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_driveway_add,
			        data:$('#addForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	if(data.success){
			        		$("#add").modal('hide');
			        		toastr.success(data.msg,"硬件模块");
			        		reload();//重新加载列表
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

