<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 添加显示屏信息
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">显示屏编号</label>
									<div class="col-md-6">
										<input type="text" data-ajax="@{pageUrl.device_screen_getLedScreenId}"  readonly="readonly" class="form-control" name="ledId" id="ledIdAdd">
										
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input  class="form-control normal-input" name="name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">显示屏类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="type" id="screen_add_type" data-width="125px">
											<option value="0">信息显示屏</option>
											<option value="1">区位屏</option>
											<option value="2">车位引导屏</option>
											<option value="3">收费屏</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">数据转换主机</label>
									<div class="col-md-6">
										<input  class="form-control select2 dataMasterName" name="dataMasterName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">控制卡编号</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="cardNum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">字体颜色</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="color" data-width="125px">
											<option value="0">红</option>
											<option value="1">绿</option>
											<option value="2">黄</option>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">宽</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="width">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">高</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="height">
									</div>
								</div>
								<div class="form-group directionDiv" style="display:none;">
									<label class="col-md-4 control-label">指示方向</label>
									<div class="col-md-6">
										<select class="bs-select form-control direction" name="direction" data-width="125px">
											<option value="0">上</option>
											<option value="1">下</option>
											<option value="2">左</option>
											<option value="3">右</option>
										</select>
									</div>
								</div>
								<div class="form-group messageDiv">
									<label class="col-md-4 control-label">显示信息</label>
									<div class="col-md-6">
										<textarea class="form-control message" rows="3" name="message"></textarea>
									</div>
								</div>
								<div class="form-group areaSelectDiv" style="display:none;">
									<label class="col-md-4 control-label">区域配置</label>
									<div class="col-md-6">
										<select multiple="multiple" data-options="@{pageUrl.device_screen_getAreas}"  class="multi-select"  name="areaSelect"></select>
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
	
	var bindRentTypeChange=function(){
		$('#screen_add_type').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#addForm .directionDiv").hide();
				$("#addForm .messageDiv").show();
				$("#addForm .areaSelectDiv").hide();
				$("#addForm .direction").attr("disabled", "disabled");
				$("#addForm .message").removeAttr("disabled");
			}
			else if(value=='1'){
				$("#addForm .directionDiv").hide();
				$("#addForm .messageDiv").hide();
				$("#addForm .areaSelectDiv").show();
				$("#addForm .message").attr("disabled", "disabled");
				$("#addForm .direction").attr("disabled", "disabled");
			}
			else if(value=='2'){
				$("#addForm .messageDiv").hide();
				$("#addForm .directionDiv").show();
				$("#addForm .areaSelectDiv").show();
				$("#addForm .direction").removeAttr("disabled");
				$("#addForm .message").attr("disabled", "disabled");
			}
			else{
				$("#addForm .directionDiv").hide();
				$("#addForm .messageDiv").show();
				$("#addForm .areaSelectDiv").hide();
				$("#addForm .direction").attr("disabled", "disabled");
				$("#addForm .message").removeAttr("disabled");
			}
		})
	}
	var bindSubmit=function(){
		$('#add_save').on('click',function(){
			//验证所有组件的方法
			if(!check($("#addForm")).form()) return;
			//$('#addForm').bootstrapValidator('validate');
			//判断是否存在不合法的输入
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.device_screen_add,
			        data:$('#addForm').serialize(),// 你的formid
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
			        	if(!obj.validateResult){
			        		//alert(obj.validateMsg);
			        		$("#addForm").clearValidator();
			        		$("#addForm").fillValidator(obj.validateMsg);
			        		//alert(obj.msg);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add").modal('hide');
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
	
	return {
        //main function to initiate the module
        //openModal: function () {            
        //	getDeviceRuleId();
        //},
        init:function(){
        	bindRentTypeChange();
        	bindSubmit();
        }
    };
}();
</script>

