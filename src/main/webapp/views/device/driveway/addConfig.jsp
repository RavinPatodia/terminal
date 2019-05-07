<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="addConfig" class="modal fade" tabindex="-1" data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog">
		<div class="modal-content" >
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 车道配置
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addConfigForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">车道名称</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control normal-input" name="name" fill="obj.name">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-md-4 control-label">方向</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control normal-input" fill="obj.direction"  name="qdirection" id="qdirection" onchange="addConfig.formatDirection(this)"> 
									</div>  
								</div>
								<div class="form-group col-md-12">
									<label class="col-md-4 control-label col-md-offset-0">摄像头</label>
									<div class="col-md-8">
										<div class="form-group container muti-wrap col-md-12">
											<select multiple="multiple" class="multi-select"  name="cameraSelect"></select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">闸机</label>
									<div class="col-md-6">
										<input class="form-control select2 brakeMachine"  name="brakeMachine" id="brakeMachine">
									</div>  
								</div>
								<div class="form-group ledScreenIn_Div">
									<label class="col-md-4 control-label">显示屏</label>
									<div class="col-md-6">
										<input class="form-control select2 ledScreenIn"  name="ledScreenIn">
									</div>  
								</div>
								<div class="form-group ledScreenOut_Div" style="display:none;">
									<label class="col-md-4 control-label">显示屏</label>
									<div class="col-md-6">
										<input class="form-control select2 ledScreenOut"  name="ledScreenOut">
									</div>  
								</div>
								<input type="hidden" fill="obj.id" name="id">
								
						</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="addConfig_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script>
var addConfig=function(){
	
	var bindClick=function(dataSource,modal,url){
		//var $this = this;
		if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		$(".tb_addConfig").bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行编辑！");   
				return;
			}
			var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
				$.ajax({
					url : url + id,
					success : function(data) {
						addConfig.callback(data, modal);
					},
					error : function(error) {
						alert(error.status + "," + error.readyState);
					}
				});
			});
	}
	
	
	var bindSubmit = function(){
		$("#addConfig_save").on("click",function(){
			if(!check($("#addConfigForm")).form()) return;
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.device_driveway_config,
		       data:$('#addConfigForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#addConfig").modal('hide');
		       		toastr.success(obj.msg,"硬件模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "硬件模块");
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
		if(obj.success){
			$("#addConfigForm",dialog).fill(obj.object);//表单填充插件
		//	$('#addConfigForm .muti_wrap').order(formObj,"selects");
			$("#addConfigForm").find(".brakeMachine > a > span").eq(0).text(formObj.brakeMachine);
			$("#addConfigForm").find(".ledScreenIn > a > span").eq(0).text(formObj.ledScreenIn);
			$("#addConfigForm").find(".ledScreenOut > a > span").eq(0).text(formObj.ledScreenOut);
			$('.multi-select').multiSelect("addParkOptionAndSelectOption",formObj.selects);
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
		}
	}
	
	var formatDirection=function(ele){
		var value = $(ele).val();
		if(value=="0"){
			$("#qdirection").val("入口");
			$(".ledScreenOut_Div").hide();
			$(".ledScreenIn_Div").show();		
		}
		else if(value=="1"){
			$("#qdirection").val("出口");
			$(".ledScreenOut_Div").show();
			$(".ledScreenIn_Div").hide();
		}
	}  
	
	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindSubmit();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		},
		formatDirection:function(ele){
			formatDirection(ele);
        } 
	};
	
}();

</script>

