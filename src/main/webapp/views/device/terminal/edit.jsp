<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改终端机信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<input type="hidden" class="form-control" name="id" fill="ss.id">
									<label class="col-md-4 control-label">终端机编号 </label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="deviceId" fill="obj.deviceId">
									</div>
								</div>
							<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input  class="form-control select2 name" name="name" fill="obj.name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<input class="form-control select2 posit" name="posit"  fill="obj.posit">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<input class="form-control input_ipv4" type="text" maxlength="15" size="15" name="ip" fill="obj.ip">
									</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				   <!--  <button type="button" onclick="changeICheck()" class="btn">icheck Test</button>
				    <button type="button" onclick="changeICheck2()" class="btn">false check</button> -->
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="device_edit_save" class="btn green">保存</button>
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
		$("#device_edit_save").on("click",function(){
			//验证所有组件的方法
			if(!check($("#editForm")).form()) return;
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: "${editUrl}",
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
		       		toastr.success("硬件模块", obj.msg);
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error("硬件模块", obj.msg);
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
		var type = formObj.type;
		var enableFlag = formObj.enableFlag;
		var baseRuleName;
		//TODO 将icheck晴空
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#editForm").find(".type > a > span").eq(0).text(formObj.type);
			if(type==0){
				$("#editForm").find(".bayonetId > a > span").eq(0).text(formObj.bayonetId);
			}
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