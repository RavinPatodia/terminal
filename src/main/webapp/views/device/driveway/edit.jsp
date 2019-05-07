<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改车道信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">车道名称</label>
										<div class="col-md-6">
											<input type="text" class="form-control normal-input" name="name" fill="obj.name">
										</div>  
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">车道方向</label>
										<div class="col-md-6">
											<div class="icheck-inline">
												<label><input id="edit_direction0" type="radio" data-check="true" checked="checked" name="direction" class="icheck ic_default" value="0" />入口</label> 
												<label><input id="edit_direction1" type="radio" name="direction" class="icheck" value="1" />出口</label>
											</div>
										</div>
								 	 </div>
									<div class="form-group">
										<label class="col-md-4 control-label">对应卡口</label>
										<div class="col-md-6">
											<input class="form-control select2 bayonetName"  name="bayonetName" fill="obj.bayonetName">
										</div>  
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">放行规则</label>
										<div class="col-md-6">
											<div class="icheck-list">
												<label><input type="checkbox" class="icheck" id="checkboxA"  value="临时用户放行" name="releaseRule"> 临时用户放行</label>
												<label><input type="checkbox" class="icheck" id="checkboxB"  value="普通用户放行" name="releaseRule"> 普通用户放行</label>
												<label><input type="checkbox" class="icheck" id="checkboxC"  value="长期用户放行" name="releaseRule"> 长期用户放行 </label>
											</div>
										</div>  
									</div>
								</div>
								<input type="hidden" fill="obj.id" name="id">
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="edit_save" class="btn green">保存</button>
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
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_driveway_edit,
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
		       		//alert(obj.msg);
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
		var direction = formObj.direction;
	 	var releaseRule = formObj.releaseRule;
		
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			if(direction==0)
				$('#edit_direction0').iCheck('check');
			else if(direction==1)
				$('#edit_direction1').iCheck('check');
			$("#editForm").find(".bayonetName > a > span").eq(0).text(formObj.bayonetName);
			if(releaseRule!=null){
				var rules = new Array();
				rules = releaseRule.split(",");
	 			for(i=0;i<rules.length;i++){
					if(rules[i]=="临时用户放行"){
						$('#checkboxA').iCheck('check');
					}
					if(rules[i]=="普通用户放行"){
						$('#checkboxB').iCheck('check');
					}
					if(rules[i]=="长期用户放行"){
						$('#checkboxC').iCheck('check');
					}
				} 
				
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
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();

</script>