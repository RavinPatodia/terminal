<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改黑名单用户
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group"  style="display: none">
									<div class="col-md-6">
									    <input type="text"class="form-control" name="userPK" fill="obj.userPK">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">用户名</label>
									<div class="col-md-6">
									  <span id="uName"></span>
									</div>
								</div>
                                <div class="form-group">
									<label class="col-md-4 control-label">列入黑名单原因</label>
									<div class="col-md-6">
										<textarea class="form-control normal-input" id="listReason" name="listReason"  rows="3" maxlength="254" fill="obj.listReason"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">截止时间<span class="required">* </span></label>
									<div class="col-md-6">
										<div class="input-group input-lage date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
											<input class="form-control" type="text" readonly="readonly" name="endTime" id="endTime" fill="obj.endTime">
											<span class="input-group-btn">
											<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">应缴金额</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="needFee" fill="obj.needFee">
									</div>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
								<input type="hidden" class="form-control normal-input" name="blacklistId" fill="obj.blacklistId">
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">取消</button>
					<button type="button"  id="blackList_edit_save" class="btn green">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>

var edit=function(){
	
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	var bindSubmit = function(){
		$("#blackList_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.user_blackList_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"黑名单管理模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "黑名单管理模块");
		       	}
		       }
		   }); 
		}
		})
	}
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#uName").text(formObj.userName);
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
		},
		 callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		} 
	};
	
}();

</script>