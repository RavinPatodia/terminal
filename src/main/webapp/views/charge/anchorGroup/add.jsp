<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加长期收费组规则
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" action=pageUrl.charge_anchorGroup_add  role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="@{pageUrl.charge_anchorGroup_getAnchorGroupId}" readonly="readonly" class="form-control normal-input" name="anchorGroupId" id="anchroGroupIdAdd">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="name">
									</div>
									<p class="form-control-static" fill="obj.name"></p>  
								</div>
								<!-- <div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										  <input type="checkbox"  class="make-switch" data-on-text="启用" data-off-text="禁用" data-on-color="danger" name="enableFlag" data-off-color="default" checked>
									</div>
								</div> -->
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则组描述</label>
									<div class="col-md-6">
										<textarea class="form-control normal-input" id="describion" name="describion"  rows="3" maxlength="254"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">组内长期规则</label>
									<div class="col-md-6">
										<select multiple="multiple" data-options="@{pageUrl.charge_anchorGroup_getAnchorRents}"  class="multi-select"  name="anchorRents">
										</select>
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
			        url: pageUrl.charge_anchorGroup_add,
			        data:$('#addForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	if(data.success){
			        		$("#add").modal('hide');
			        		toastr.success(data.msg,"收费规则模块");
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(data.msg,"收费规则模块");
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

