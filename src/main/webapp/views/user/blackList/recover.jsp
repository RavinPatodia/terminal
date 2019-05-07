<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
	<div id="recover" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ban-circle" ></span>
							解除黑名单
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
					   <div class="col-md-12">
						<form class="form-horizontal" role="form" id="recoverForm">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">黑名单编号</label>
									<div class="col-md-6">
									    <input type="text" readonly="readonly" class="form-control" name="blacklistId" fill="obj.blacklistId">
									</div>
								</div>
                                <div class="form-group">
									<label class="col-md-4 control-label">用户名</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="userName" fill="obj.userName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">解除黑名单原因</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="removeWay" >
									</div>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
							</div>
						</form>
					</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">close</button>
					<button type="button"  id="blackList_recover_save" class="btn green">ok</button>
				</div>
			</div>
		</div>
	</div>
	
<script>
var recover =function(){
	var bindClick=function(dataSource,modal,url){
		$('.tb_recover').recover(dataSource,modal,url);
	}

	var bindSubmit = function(){
		$("#blackList_recover_save").on("click",function(){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.user_blackList_recover,
		       data:$('#recoverForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#recover").modal('hide');
		       		toastr.success(obj.msg,"黑名单管理模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "黑名单管理模块");
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
			$("#recoverForm",dialog).fill(obj.object);//表单填充插件
			dialog.modal('show');
		}else{
			alert(obj.msg);
		}
	}
	
	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindSubmit();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();
</script>