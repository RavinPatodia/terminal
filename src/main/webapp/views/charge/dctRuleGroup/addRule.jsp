<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="addRule" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:700px">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加优惠规则
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addRuleForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">优惠规则组编号</label>
									<div class="col-md-6"><p class="form-control-static" fill="obj.groupId">
									</p></div>
								</div>
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label col-md-offset-0">组内优惠规则</label>
									<div class="col-md-8">
										<div class="form-group container muti-wrap col-md-12">
												<div class="col-md-4 bd left_pt fake_select">
												</div>
												<div class="col-md-4 col-md-offset-1 bd right_pt result_pt">
												</div>
												<div class="col-md-3 operation_pt">
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 top">置顶</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 prev">向上</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 next">向下</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 bottom">置底</a>
													<a href="javascript:;" class="btn btn-danger  btn-sm col-md-12 delete">删除</a>
												</div>
												<div class="col-md-12" style="margin-top:15px;padding:0px;">
													<div class="alert alert-danger col-md-6 error_log" role="alert"></div>
												</div>
												<div class="col-md-12" style="padding:0px;">
													<div class="col-md-6" style="padding:0px;">
														<input type="hidden" class="form-control value_text" name="dctRuleIds">
													</div>
												</div>
											</div>
									</div>
								</div>
								<input type="hidden" fill="obj.id" name="id">
						</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="charge_addRule_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">

var addRule=function(){
	var bindClick=function(dataSource,modal,url){
		$('.tb_addRule').addRule(dataSource,modal,url);
	}
	var bindModalHide = function(){
		$('#addRule').on('hidden.bs.modal', function () {
			$("#addRuleForm").data('bootstrapValidator').resetForm(true);
		})
	}
	
	var bindSubmit = function(){
		$("#charge_addRule_save").on("click",function(){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.charge_dctRuleGroup_editRulesInGroup,
		       data:$('#addRuleForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#addRule").modal('hide');
		       		toastr.success(obj.msg,"收费规则模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg,"收费规则模块");
		       	}
		       }
		   }); 
		})
	}
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#addRuleForm",dialog).fill(formObj);//表单填充插件
			$('#addRuleForm .muti-wrap').initOrder(formObj,"selects");
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
			bindModalHide();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();
</script>