<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add2" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加车辆型号
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addModule">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">车辆型号</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="model" id="model">
									</div>
								</div>
							</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="module_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
var addModule=function(){
	
	var bindSubmit=function(){

		$('#module_add_save').on('click',function(){
			if(!check($("#addModule")).form()) return;
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.caradmission_carType_addModule,
			        data:$('#addModule').serialize(),// 你的formid
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
			        		$("#addModule").clearValidator();
			        		$("#addModule").fillValidator(obj.validateMsg);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add2").modal('hide');
			        		toastr.success(obj.msg,"车辆相关信息");
			        		oTable2.fnDraw();
			        	}
			        	else{
			        		toastr.error(obj.msg, "车辆相关信息");
			        	}
			        }
				  }); 
			}
		})
	}		
	
	return {
        init:function(){
        	bindSubmit();
        }
    };
}();
</script>

