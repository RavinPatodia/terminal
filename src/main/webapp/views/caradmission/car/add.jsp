<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400"  data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:650px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加车辆信息
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">车牌号码</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="licensePlate"/>
									</div>
									<span class="help-block" id="car_existmsg">(输入格式如:浙A88888)</span>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车牌类型</label>
									<div class="col-md-6">
										<input class="form-control select2 car_type" name="type">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆颜色</label>
									<div class="col-md-6">
										<input class="form-control select2 car_color" name="carColor">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆型号</label>
									<div class="col-md-6">
										<input class="form-control select2 car_module" name="carModel">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">车辆类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" data-selected="0" name="carType">
											<option value="0">小车</option>
											<option value="1">大车</option>
										</select>
									</div>
								</div>
								<div class="form-group timeChargeDiv" >
									<label class="col-md-3 control-label">客户</label>
									<div class="col-md-6">
										<input type="hidden"  class="form-control select2 user_name_id " name="userPK">
									</div>
								</div>
							</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
var add=function(){
	var bindOpenClickEvent = function(){
		$('.tb_add').bind("click",function(){
			$("#add").modal('show');
		});
	}
	var initLicensePlate = function(){
		$('input[name="licensePlate"]').keyup(function(){
			var licensePlate = $(this).val();
				var re=/[ ]/g;
				if(re.test(licensePlate)){
					var n=licensePlate.match(re).length;
					if(n>0){
						$("#car_existmsg").html("请注意,车牌号码不包含空格");
						$('#add_save').attr('disabled',true);
					}
					return;
				}
				$.ajax({
					url:"${ifExistUrl}",
					type:"POST",
					data:{licensePlate:licensePlate},
					success:function(data){
						var obj = data;
						if(obj.success){
							$("#car_existmsg").html("该车牌号已经存在");
							$('#add_save').attr('disabled',true);
						}else{
							$("#car_existmsg").html("");
							$('#add_save').attr('disabled',false);
						}
					},
					error:function(){
					}
				});
		});
	}
	var bindSubmit=function(){
		$('#add_save').on('click',function(){
			if(!check($("#addForm")).form()) return;
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: "${addUrl}",
			        data:$('#addForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	if(data==null||data==""){
			        		return;
			        	}
			        	var obj = data;
			        	if(!obj.validateResult){
			        		$("#addForm").clearValidator();
			        		$("#addForm").fillValidator(obj.validateMsg);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add").modal('hide');
			        		toastr.success(obj.msg,"车辆管理模块");
			        		reload();//重新加载列表
			        	}
			        	else{
			        		toastr.error(obj.msg, "车辆管理模块");
			        	}
			        }
				  }); 
			}
		})
	}
	return {
        init:function(){
        	bindOpenClickEvent();
        	bindSubmit();
        	initLicensePlate();
        }
    };
}();
</script>

