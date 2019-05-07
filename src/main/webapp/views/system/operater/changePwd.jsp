<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="changePwdDialog" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 密码修改
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="changePwdForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
							
								<div class="form-group">
									<label class="col-md-4 control-label">原密码</label>
									<div class="col-md-6">
										<input class="form-control" type="password" placeholder="原密码" name="oldPwd"/>	
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">新密码</label>
									<div class="col-md-6">
										<input class="form-control" type="password" placeholder="新密码" id="changePwd_newPwd" name="newPwd"/>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">确认新密码</label>
									<div class="col-md-6">
										<input class="form-control" type="password" placeholder="确认新密码" name="rPwd"/>
									</div>
								</div>		
								
							</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="btn_changePwd">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">

var changePwd=function(){
	
	
	var showDialog=function(){
		$("#changePwdDialog").modal('show');
	}
	
	var bindSubmit=function(){
		$('#btn_changePwd').on('click',function(){
				$.ajax({
			        type: "POST",
			        url: "/park/admin/sys/operater/changePwd",
			        data:$('#changePwdForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	if(data==null||data==""){
			        		return;
			        	}
			        	var obj = data;
			        	if(obj.success){
			        		$("#changePwdDialog").modal('hide');
			        		toastr.success(obj.msg);
			        	}
			        	else{
			        		toastr.error(obj.msg);
			        	}
			        }
				  }); 
		})
	}
	
	return {
        init:function(){
        	showDialog();
        	bindSubmit();
        }
    };
}();
</script>

