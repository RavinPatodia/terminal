<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改显示屏信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<input type="hidden" class="form-control" name="id" fill="ss.id">
									<label class="col-md-4 control-label">显示屏编号 </label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="ledId" fill="obj.ledId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<input  class="form-control select2 name" name="name" fill="obj.name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">显示屏类型</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="type" id="screen_edit_type" data-width="125px" fill="obj.type">
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
										<input  class="form-control select2 dataMasterName" name="dataMasterId" fill="obj.dataMasterId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">控制卡编号</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="cardNum" fill="obj.cardNum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">字体颜色</label>
									<div class="col-md-6">
										<select class="bs-select form-control" name="color" data-width="125px" fill="obj.color">
											<option value="0">红</option>
											<option value="1">绿</option>
											<option value="2">黄</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">宽</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="width" fill="obj.width">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">高</label>
									<div class="col-md-6">
										<input type="text" class="form-control normal-input" name="height" fill="obj.height">
									</div>
								</div>
								<div class="form-group directionDiv" style="display:none;">
									<label class="col-md-4 control-label">指示方向</label>
									<div class="col-md-6">
										<select class="bs-select form-control direction" name="direction" id="device_add_direction" data-width="125px" fill="obj.direction">
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
										<textarea class="form-control message" rows="3" name="message" fill="obj.message"></textarea>
									</div>
								</div>
								<div class="form-group areaSelectDiv" style="display:none;">
								    <label class="col-md-4 control-label">区域</label>
									<div class="col-md-6"> 
										<select multiple="multiple" class="multi-select"  name="areaSelect"></select>
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

	var bindRentTypeChange=function(){
		$('#screen_edit_type').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#editForm .directionDiv").hide();
				$("#editForm .messageDiv").show();
				$("#editForm .areaSelectDiv").hide();
				$("#editForm .direction").attr("disabled", "disabled");
				$("#editForm .message").removeAttr("disabled");
			}
			else if(value=='1'){
				$("#editForm .directionDiv").hide();
				$("#editForm .messageDiv").hide();
				$("#editForm .areaSelectDiv").show();
				$("#editForm .message").attr("disabled", "disabled");
				$("#editForm .direction").attr("disabled", "disabled");
			}
			else if(value=='2'){
				$("#editForm .messageDiv").hide();
				$("#editForm .directionDiv").show();
				$("#editForm .areaSelectDiv").show();
				$("#editForm .direction").removeAttr("disabled");
				$("#editForm .message").attr("disabled", "disabled");
			}
			else{
				$("#editForm .directionDiv").hide();
				$("#editForm .messageDiv").show();
				$("#editForm .areaSelectDiv").hide();
				$("#editForm .direction").attr("disabled", "disabled");
				$("#editForm .message").removeAttr("disabled");
			}
		})
	}
	var bindSubmit = function(){
		$("#edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_screen_edit,
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
			$("#editForm").find(".dataMasterName > a > span").eq(0).text(formObj.dataMasterName);
			$('.multi-select').multiSelect("addParkOptionAndSelectOption",formObj.selects);
			/* $("#editForm").find(".type > a > span").eq(0).text(formObj.type);
			if(type==2){
				$("#editForm").find(".bayonetName > a > span").eq(0).text(formObj.bayonetId);
			} */
			//禁用还是启用，给iCheckBox赋值选中
			/* if(enableFlag){
				$('#device_edit_enableflagtrue').iCheck('check');
			}
			else{
				$('#device_edit_enableflagfalse').iCheck('check');
			} */
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
		}
	}
	
	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindRentTypeChange();
			bindSubmit();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();

</script>