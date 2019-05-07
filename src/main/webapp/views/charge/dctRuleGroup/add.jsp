<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--添加 start-->
		<div class="modal-dialog" style="width:700px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-plus" ></span>
							 添加优惠规则组
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="addForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则组编号</label>
										<div class="col-md-6"><input type="text"  data-ajax="@{pageUrl.charge_dctRuleGroup_getDctRuleGroupId}" readonly="readonly" class="form-control" name="groupId" id="groupIdAdd"></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则组名称</label>
										<div class="col-md-6"><input type="text" class="form-control" name="name"></div>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">描述</label>
										<div class="col-md-6"><textarea class="form-control" id="message" rows="3" name="describion"></textarea></div>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">组内优惠规则</label>
										<div class="col-md-6">
											<select multiple="multiple" data-options="@{pageUrl.charge_dctRuleGroup_getDctRules}"  class="multi-select"  name="dctRules">
											</select>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">取消</button>
				<button type="button" class="btn green save" id="add_save">保存</button>
				</div>
			</div>
		</div>
	</div><!--添加 end-->
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
			        url: pageUrl.charge_dctRuleGroup_add,
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
			        		toastr.success(obj.msg,"优惠规则组模块");
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(obj.msg, "优惠规则组模块");
			        		//alert(obj.msg);
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
