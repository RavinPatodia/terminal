<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="black" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 添加黑名单
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="blackForm">
								<div class="form-body">
								<div class="form-group" style="display: none">
									<div class="col-md-6">
									    <input type="text"class="form-control" name="id" id="id" fill="obj.id">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">用户名</label>
									<div class="col-md-6">
									    <input type="text" readonly="readonly" class="form-control" fill="obj.uacc">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">是否允许进场</label>
									<div class="col-md-6">
										<input type="checkbox"  class="make-switch" data-on-text="是" data-off-text="否" data-on-color="danger" name="inFlag" data-off-color="default" checked>
									</div>
								</div>
                                <div class="form-group">
									<label class="col-md-4 control-label">列入黑名单原因</label>
									<div class="col-md-6">
										<textarea class="form-control normal-input" id="listReason" name="listReason"  rows="3" maxlength="254" ></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4">截止时间<span class="required">* </span></label>
									<div class="col-md-6">
										<div class="input-group input-lage date date-picker" data-date-format="dd-mm-yyyy" data-date-start-date="+0d">
											<input class="form-control" type="text" readonly="readonly" name="endTime" id="endTime">
											<span class="input-group-btn">
											<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
											</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">应缴金额</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="needFee" id="needFee">
									</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">取消</button>
					<button type="button"  id="blackList_save" class="btn green">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>
var addBlack=function(){
	var bindClick=function(dataSource,modal,url){
		if(!dataSource)
			return;
		$(".tb_add_black").bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行操作！");   
				return;
			}
			var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
			$.ajax({
				url : pageUrl.user_user_editAndDbl+ id+"?t="+Math.random(),
				success : function(data) {
					if(data.object.isBlack==true){//若没有选中或者选中的行数大于的1话
						bootbox.alert("该客户已经加入黑名单,不能重复添加");   
						return;
					}
					addBlack.callback(data, $('#black'));
				},
				error : function(error) {
					alert(error.status + "," + error.readyState);
				}
			});
		});
	}
	var bindSubmit = function(){
		$("#blackList_save").on("click",function(){
			if(!check($('#blackForm')).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.user_user_addBlackList,
		       data:$('#blackForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#black").modal('hide');
		       		toastr.success(obj.msg,"黑名单管理模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "黑名单管理模块");
		       	}
		       }
		   }); 
		}
		})
	}
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;  
		var formObj = obj.object;
		if(obj.success){
			$("#blackForm",dialog).fill(obj.object);//表单填充插件
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