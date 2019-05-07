<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="configure" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--配置 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 配置显示屏区域
				</h4>
			</div>
			<div class="modal-body">
	 			<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="configureForm">
							<div class="form-body">
								<div class="form-group">
									<input type="hidden" class="form-control" name="id" fill="ss.id">
									<label class="col-md-4 control-label">显示屏编号 </label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="deviceId" fill="obj.deviceId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="name" fill="obj.name">
									</div>
								</div>
									
									
								<div class="form-group col-md-12" id="areaDiv" style="display:block;">
									   <label class="col-md-4 control-label col-md-offset-0">区域ID</label>
										<div class="col-md-8">
											<div class="form-group container muti-wrap col-md-12">
												<div class="col-md-4 bd left_pt fake_select">
												</div>
												<div class="col-md-4 col-md-offset-1 bd right_pt result_pt">
												</div>
												<div class="col-md-3 operation_pt">
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 top">置顶</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 prev">向上</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 next">向下</a>
													<a href="javascript:;" class="btn btn-primary btn-sm col-md-12 bottom">置底</a>
													<a href="javascript:;" class="btn btn-danger  btn-sm col-md-12 delete">删除</a>
												</div>
												<div class="col-md-12" style="margin-top:15px;padding:0px;">
													<div class="alert alert-danger col-md-6 error_log" role="alert"></div>
												</div>
												<div class="col-md-12" style="padding:0px;">
													<div class="col-md-6" style="padding:0px;">
														<input type="hidden" class="form-control value_text" name="areasIds">
													</div>
												</div>
											</div>
									</div>
								</div>
						</div>
					</form>
				</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
/* var initOrder = function(){
	$.ajax({
	       url: "${getAreaId}",
	       error: function(request) {
	           alert("Connection error");
	       },
	       success: function(data) {
	       	var obj = data;
	       	if(obj.success){
	       		$('#addForm .muti-wrap').initOrder(obj,"object");
	       	}
	       	else{
	       		alert(obj.msg);
	       	}
	       }
	   });
	
	return {
		init:function(){
			initOrder();
		}
	}
}(); */

var configure=function(){
	//验证表单的方法
	function check(){
		
		var addform = $('#configureForm');
		var error3 = $('.alert-danger', addform);
		var success3 = $('.alert-success', addform);

		return addform.validate({
		    errorElement: 'span', //default input error message container
		    errorClass: 'help-block help-block-error', // default input error message class
		    focusInvalid: false, // do not focus the last invalid input
		    ignore: "", // validate all fields including form hidden input
		    rules: {
		    	deviceId: {
		            maxlength: 20
		        },
		    },
				errorPlacement: function (error, element) { // render error placement for each input type
		        if (element.parent(".input-group").size() > 0) {
		            error.insertAfter(element.parent(".input-group"));
		        } else if (element.attr("data-error-container")) { 
		            error.appendTo(element.attr("data-error-container"));
		        } else if (element.parents('.radio-list').size() > 0) { 
		            error.appendTo(element.parents('.radio-list').attr("data-error-container"));
		        } else if (element.parents('.radio-inline').size() > 0) { 
		            error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
		        } else if (element.parents('.checkbox-list').size() > 0) {
		            error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
		        } else if (element.parents('.checkbox-inline').size() > 0) { 
		            error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
		        } else {
		            error.insertAfter(element); // for other inputs, just perform default behavior
		        }
		    }, 

		    invalidHandler: function (event, validator) { //display error alert on form submit   
		        success3.hide();
		        error3.show();
		        Metronic.scrollTo(error3, -200);
		    },

		    highlight: function (element) { // hightlight error inputs
		       $(element)
		            .closest('.form-group').addClass('has-error'); // set error class to the control group
		    },

		    unhighlight: function (element) { // revert the change done by hightlight
		        $(element)
		            .closest('.form-group').removeClass('has-error'); // set error class to the control group
		    },

		    success: function (label) {
		        label
		            .closest('.form-group').removeClass('has-error'); // set success class to the control group
		    },
		});
		}
	
	
	
	var bindModalHide = function(){
		$('#add').on('hidden.bs.modal', function () {
			$("#device_add_type").val('-1').trigger("change");
			$('#configureForm input').val("");
			check().resetForm();
			initDrop();
		})
	}
	
	var bindSubmit=function(){
		$('#configure .save').on('click',function(){
			//验证所有组件的方法
			if(!check().form()) return;
			//$('#configureForm').bootstrapValidator('validate');
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_screen_configure,
			        data:$('#configureForm').serialize(),// 你的formid
			        async: false,
			        error: function(request) {
			            alert("Connection error");
			        },
			        success: function(data) {
			        	// alert(data);
			        	if(data==null||data==""){
			        		return;
			        	}
			        	var obj = data;
			        	if(obj.success){
			        		$("#configure").modal('hide');
			        		toastr.success("硬件模块", obj.msg);
			        		reload();//重新加载列表
			        	}
			        	else{
			        		alert(obj.msg);
			        	}
			        }
				  }); 
			}
		})
	}
	
	return {
        //main function to initiate the module
        //openModal: function () {            
        //	getDeviceRuleId();
        //},
        init:function(){
        	bindModalHide();
        	bindSubmit();
        }
    };
}();
</script>

