<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify2" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							 修改缴费机信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="editPayment">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">缴费机编号</label>
										<div class="col-md-6">
											<input type="text" readonly="readonly" class="form-control" name="deviceId" fill="obj.deviceId">
										</div>  
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">缴费机名称</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="name" fill="obj.name">
										</div>  
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">位置</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="posit" fill="obj.posit">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">IP</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="ip" fill="obj.ip">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">端口号</label>
										<div class="col-md-6">
											<input type="text"  class="form-control" name="port" fill="obj.port">
										</div>
									</div>
									<input type="hidden" name="id" fill="obj.id">
									<input type="hidden" name="type" value="2" />
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="editPayment_save" class="btn green">保存</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>

var editPayment=function(){
	
	var bindClick=function(dataSource,modal,url){
		//var $this = this;
		if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		$(".payment_modify").bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行编辑！");   
				return;
			}
			var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
				$.ajax({
					url : url + id,
					success : function(data) {
						editPayment.callback(data, modal);
					},
					error : function(error) {
						alert(error.status + "," + error.readyState);
					}
				});
			});
	}
	
	var bindSubmit = function(){
		$("#editPayment_save").on("click",function(){
			if(!check($("#editPayment")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_deviceAll_edit,
		       data:$('#editPayment').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#modify2").modal('hide');
		       		toastr.success(obj.msg,"硬件模块");
		       		oTable2.fnDraw();
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
		//var direction = formObj.direction;
		
		if(obj.success){
			$("#editPayment",dialog).fill(obj.object);//表单填充插件
			/* if(direction==0)
				$('#edit_direction0').iCheck('check');
			else if(direction==1)
				$('#edit_direction1').iCheck('check');
			$("#editPayment").find(".bayonetName > a > span").eq(0).text(formObj.bayonetName); */
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