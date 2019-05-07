<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改出入场记录
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editForm">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-3 control-label">车牌号码</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="licensePlate" fill="obj.licensePlate">	
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">入车道</label>
									<div class="col-md-6">
										<input class="form-control select2 inDriveway" name="inDriveWayPK" fill="obj.inDriveWayPK">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">出车道</label>
									<div class="col-md-6">
										<input class="form-control select2 outDriveway" name="outDriveWayPK" fill="obj.outDriveWayPK">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">入场时间</label>
									<div class="col-md-6">
										<div class="input-group date form_meridian_datetime">
											<input type="text" size="16" class="form-control" readonly name="inTime" fill="obj.inTime">
											<span class="input-group-btn">
											<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
											<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">出场时间</label>
									<div class="col-md-6">
										<div class="input-group date form_meridian_datetime">
											<input type="text" size="16" class="form-control" readonly name="outTime" fill="obj.outTime">
											<span class="input-group-btn">
											<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
											<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
										<label class="col-md-3 control-label">入场识别方式</label>
										<div class="col-md-6">
											<select class="bs-select form-control "	data-selected="-1" name="inRecoWay" fill="obj.inRecoWay">
												<option value="-1">请选择</option>
												<option value="1">人工识别</option>
												<option value="0">车牌识别</option>
											</select>
										</div>
									</div>
								<div class="form-group">
										<label class="col-md-3 control-label">出场识别方式</label>
										<div class="col-md-6">
											<select class="bs-select form-control "	data-selected="-1" style="display: none;" name="outRecoWay" fill="obj.outRecoWay">
												<option value="-1">请选择</option>
												<option value="1">人工识别</option>
												<option value="0">车牌识别</option>
											</select>
										</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">应付金额</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="fee" fill="obj.fee">	
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">是否已付款</label>
									<div class="col-md-6">
										<input type="checkbox" fill="obj.isPay"  class="make-switch" data-on-text="已付" data-off-text="未付" data-on-color="danger" name="isPay" data-off-color="default" checked>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" fill="obj.inPicUrl" name="inPicUrl" id="inPicUrl">入场图片</label>
									<div class="col-md-9">
										<div class="fileinput fileinput-new" data-provides="fileinput">
											<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
												<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
											</div>
											<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
											</div>
											<div>
												<span class="btn default btn-file">
												<span class="fileinput-new">
												选择 </span>
												<span class="fileinput-exists">
												替换 </span>
												<input type="file" name="...">
												</span>
												<a href="#" class="btn red fileinput-exists" data-dismiss="fileinput">
												移除 </a>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" fill="obj.outPicUrl" name="outPicUrl" id="outPicUrl">出场图片</label>
									<div class="col-md-9">
										<div class="fileinput fileinput-new" data-provides="fileinput">
											<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
												<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
											</div>
											<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
											</div>
											<div>
												<span class="btn default btn-file">
												<span class="fileinput-new">
												选择 </span>
												<span class="fileinput-exists">
												替换 </span>
												<input type="file" name="...">
												</span>
												<a href="#" class="btn red fileinput-exists" data-dismiss="fileinput">
												移除 </a>
											</div>
										</div>
									</div>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="admission_edit_save" class="btn green">保存</button>
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
		$("#admission_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.caradmission_admissRec_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify").modal('hide');
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
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		var baseRuleName;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#editForm").find(".outDriveway > a > span").eq(0).text(formObj.outDriveWayName);
			$("#editForm").find(".inDriveway > a > span").eq(0).text(formObj.inDriveWayName);
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