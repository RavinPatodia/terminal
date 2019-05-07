<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="edit" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加停车场/区域
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="editForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
							
								<div class="form-group">
									<label class="col-md-4 control-label">停车场编号</label>
									<div class="col-md-6">
										<input type="text" class="form-control" readonly="readonly" name="parkId" fill="obj.parkId">	
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">停车场名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="name" id="parkNameAdd" fill="obj.name">	
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4 control-label">类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control type" name="type" id="edit_type" data-width="125px" fill="obj.type">
											<option value="0">区域</option>
											<option value="1">停车场</option>
										</select> 
									</div>
								</div>
								<div class="parkType" style="display:none">
									<div class="form-group">
										<label class="col-md-4 control-label">停车位总数</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="spNum" fill="obj.spNum"> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">剩余停车位</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="nowSpNum" fill="obj.nowSpNum"> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">高度限制</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="heightLimit" fill="obj.heightLimit"> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">是否收费</label>
										<div class="col-md-6">
											<input type="checkbox"  class="make-switch" data-on-text="是" data-off-text="否"  data-on-color="danger" fill="obj.isCharge" name="isCharge" id="charge_edit_isCharge" data-off-color="default" checked>
										</div>
									</div>
									<div class="form-group" id="chargeRule" style="display:none">
										<label class="col-md-4 control-label">租赁规则</label>
										<div class="col-md-6">
											<input class="form-control select2 rulePK"  name="rulePK" fill="obj.ruleName">
										</div>
									</div>
								 	 <div class="form-group">
										<label class="col-md-4 control-label">楼层</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="floor" fill="obj.floor"> 
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">停车场类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control" name="parkType" id="camera_edit_type" data-width="125px" fill="obj.parkType">
												<option value="0">室内</option>
												<option value="1">室外</option>
											</select> 
										</div>
									</div>
								 	 <div class="form-group">
										<label class="col-md-4 control-label">位置</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="posit" fill="obj.posit"> 
										</div>
									</div>	
								</div>
							 	<div class="form-group">
									<label class="col-md-4 control-label">备注</label>
									<div class="col-md-6">
										<input  type="text" class="form-control" name="remark" fill="obj.remark"> 
									</div>
								</div>	
								
								<input type="hidden" class="form-control" id="edit_parentId" name="parentId" fill="obj.parentId">
								<input type="hidden" class="form-control" name="id" fill="obj.id">
							</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="edit_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">

var edit=function(){
	var validateParkId = function(){
		$('#parkNameAdd').blur(function(){
			var name = $(this).val();
				$.ajax({
					url:pageUrl.device_park_getSameParkName,
					type:"POST",
					data:{name:name},
					success:function(data){
						var obj = data;
						if(!obj.success){
							toastr.options = {
									  "positionClass": "toast-top-center",
							}
							toastr.info("【"+$('#parkNameAdd').val()+"】"+obj.msg);
							$('#parkNameAdd').val("");
						}
					},
					error:function(){
						toastr.info("数据连接错误，无法校验车位编号！");
					}
				});
		});
	}

	var bindOpenClickEvent = function(){
		$('#edit_btn').bind("click",function(){
			var id = $("#park_tree").jstree().get_selected();
			$.ajax({
				url : pageUrl.device_park_editAndDbl + id,
				success : function(data) {
					bindDataAndOpenModal(data, $("#edit"));
				},
				error : function(error) {
					alert(error.status + "," + error.readyState);
				}
			});
		});
	}
	
	var typeChange = function(){
		$(".type").on('change',function(){
			if($(this).val()=="0"){
				$(".parkType").css('display', 'none');
			}else
				$(".parkType").css('display', 'block');
		});
		$("#charge_edit_isCharge").on('switchChange.bootstrapSwitch',function(event,state){
			if(state==false){
				$("#chargeRule").css('display', 'none');
				$(".rulePK").val("");
			}else
				$("#chargeRule").css('display', 'block');
		});
	}

	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#editForm").find(".rulePK > a > span").eq(0).text(obj.object.ruleName);
			dialog.modal('show');
			if(obj.object.type==0){
				$(".parkType").css('display', 'none');
			}else{
				$(".parkType").css('display', 'block');
			}
			if(obj.object.isCharge){
				$('#chargeRule').css('display', 'block');
			}else{
				$("#chargeRule").css('display', 'none');
			} 
		}else{
			alert(obj.msg);
		}
	}
	
	var bindSubmit=function(){
		$('#edit_save').on('click',function(){
			if(!check($("#addForm")).form()) return;
			if($('.has-error').length<=0){
			$.ajax({
		        type: "POST",
		        url: pageUrl.device_park_edit,
		        data:$('#editForm').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            alert("Connection error");
		        },
		        success: function(data) {
	        		$("#edit").modal('hide');
	        		$("#park_tree").jstree().destroy();
	        		uitree.init();
	        		toastr.success("修改成功");
		        }
			  }); 
			}
		})
	}
	
	return {
        init:function(){
        	bindOpenClickEvent();
        	bindSubmit();
        	typeChange();
        }
    };
}();
</script>

