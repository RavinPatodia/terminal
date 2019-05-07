<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改车位锁信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<input type="hidden" class="form-control" name="id" fill="ss.id">
									<label class="col-md-4 control-label">车位锁编号 </label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="lockId" fill="obj.lockId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">品牌</label>
									<div class="col-md-6">
										<input  class="form-control select2 brand" name="brand" fill="obj.brand">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">型号</label>
									<div class="col-md-6">
										<input class="form-control select2 model" name="model" fill="obj.model">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<input class="form-control input_ipv4" type="text" maxlength="15" size="15" name="ip" fill="obj.ip">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">端口号</label>
									<div class="col-md-6"> 
										<input class="form-control select2 port" name="port" fill="obj.port">
									</div>
								</div>
								<!-- <div class="form-group">
									<label class="col-md-4 control-label">车位锁状态</label>
									<div class="col-md-6">
										<input type="checkbox" fill="obj.lockState" class="make-switch" data-on-text="开启" data-off-text="关闭"  data-on-color="danger" name="lockState" data-off-color="default" checked>
									</div>
								</div> -->
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				   <!--  <button type="button" onclick="changeICheck()" class="btn">icheck Test</button>
				    <button type="button" onclick="changeICheck2()" class="btn">false check</button> -->
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="lock_edit_save" class="btn green">保存</button>
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
		$("#lock_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_pSpLock_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
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
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
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