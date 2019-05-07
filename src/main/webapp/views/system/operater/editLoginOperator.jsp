<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="/views/include/taglib.jsp"%>
<div id="modifyLoginOperator" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改操作员信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editLoginOperForm" enctype="multipart/form-data">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">用户名</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="operName" fill="obj.operName">	
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<input type="checkbox" name="enableFlag" fill="obj.enableFlag" class="make-switch" data-on-text="启用" data-off-text="禁用"  data-on-color="danger" data-off-color="default" checked>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">姓名</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="name" fill="obj.name"> 
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">身份证号</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="idCard" fill="obj.idCard"> 
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">性别</label>
									<div class="col-md-6">
										<input type="checkbox" name="gender" fill="obj.gender" class="make-switch" data-on-text="男" data-off-text="女"  data-on-color="danger" data-off-color="default" checked>
									</div>
								</div>
							 	<div class="form-group">
									<label class="col-md-4 control-label">职务</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="job" fill="obj.job"> 
									</div>
								</div>
								<div class="form-group operImg">
									<label class="col-md-4 control-label">上传头像</label>
									<div class="col-md-6">
										<div class="fileinput fileinput-new" data-provides="fileinput">
											<div class="fileinput-new thumbnail col-md-10">
												<img src="" alt=""/>
											</div>
											<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
											</div>
											<div>
												<span class="btn default btn-file">
												<span class="fileinput-new">选择 </span>
												<span class="fileinput-exists">替换 </span>
												<input type="file" name="myfiles"></span>
												<a href="#" class="btn red fileinput-exists" data-dismiss="fileinput">移除 </a>
											</div>
										</div>
									</div>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
								<input type="hidden" class="form-control normal-input" name="createTime" fill="obj.createTime">
								<input type="hidden" class="form-control normal-input" name="lastDisable" fill="obj.lastDisable">
								<input type="hidden" class="form-control normal-input" name="lastEnable" fill="obj.lastEnable">
								<input type="hidden" class="form-control normal-input" name="picUrl" fill="obj.picUrl">
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">取消</button>
					<button type="button"  id="loginOper_edit_save" class="btn green">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>

var editLoginOperator=function(){	
	var bindClick=function(){
			$.ajax({
				url: "/park/admin/sys/operater/getLoginOperater"+"?t="+Math.random(),
				success : function(data) {
					bindDataAndOpenModal(data, $('#modifyLoginOperator'));
				},
				error : function(error) {
					alert(error.status + "," + error.readyState);
				}
			});
	}
	
	var bindSubmit = function(){
		$("#loginOper_edit_save").on("click",function(){
			if(!check($("#editLoginOperForm")).form()) return;
			if($('.has-error').length<=0){
				var options = {
					dataType:  'json',
					type: "post",
					url :"${editUrl}",
					success: afterSubmit,
				}	
				$("#editLoginOperForm").ajaxSubmit(options);
				function afterSubmit(data){
					if(data==null||data==""){
		        		return;
		        	}
		        	var obj = data;
		        	if(!obj.validateResult){
		        		$("#editLoginOperForm").clearValidator();
		        		$("#editLoginOperForm").fillValidator(obj.validateMsg);
		        		return;
		        	}
		        	if(obj.success){
		        		$("#modifyLoginOperator").modal('hide');
		        		toastr.success(obj.msg,"操作员管理模块");
		        		reload();//重新加载列表
		        	}
		        	else{
		        		toastr.error(obj.msg, "操作员管理模块");
		        	}
				}  
			
			}
		})
	}
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#editLoginOperForm",dialog).fill(obj.object);//表单填充插件
			
			if(formObj.ftpPicUrl!=null){
				$('.operImg').find('img').attr('src',formObj.ftpPicUrl);
			}else{
				$('.operImg').find('img').attr('src','/park/assets/admin/layout/img/AAAAAA&amp.gif');
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
		}
	};
	
}();

</script>