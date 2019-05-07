<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
	<div class="modal-dialog" style="width:1200px">
		<div class="modal-content">
		<div class="modal-body">
			<form class="form-horizontal" role="form" id="editForm">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title" style="font-family: 'Microsoft Yahei'"><span class="glyphicon glyphicon-plus"></span> 添加客户组</h3>
							</div>
							<div class="panel-body">
								<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">客户组编号</label>
									<div class="col-md-6">
										<input type="text" readonly="readonly" class="form-control" name="ugroupId" fill="obj.ugroupId">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">客户组名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control" name="name" fill="obj.name"> 
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">客户组类型</label>
									<div class="col-md-6">
										<select id="editType" class="bs-select form-control " style="display: none;" name="type" fill="obj.type">
											<option value="-1">请选择</option>
											<option value="0">临时客户</option>
											<option value="1">普通会员</option>
											<option value="2">长期会员</option>
											<option value="3">默认临时客户组</option>
										</select>
									</div>
								</div>
								<div class="form-group chang_Ugroup_Div" style="display: none;">
										<label class="col-md-4 control-label">到期变更客户组</label>
										<div class="col-md-6">
											<input class="form-control select2 ugroup_notlong_name_id"  name="changeGroup">
											<label>默认为临时客户组</label>
										</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<input type="checkbox" fill="obj.enableFlag"  class="make-switch" data-on-text="启用" data-off-text="禁用" data-on-color="danger" name="enableFlag" data-off-color="default" checked>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">描述</label>
									<div class="col-md-6">
										<textarea class="form-control normal-input" name="describion"  rows="3" maxlength="254" fill="obj.describion"></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">优惠规则组</label>
									<div class="col-md-6">
										<input class="form-control select2 o_dctRuleGroupPK"  name="dctRuleGroupPK" fill="obj.dctRuleGroupPK">
									</div>
								</div>
								<div class="form-group long_rule_div" style="display: none;">
									<label class="col-md-4 control-label ">长期收费规则</label>
									<div class="col-md-6">
										<input class="form-control select2 o_chargeRuleLong" name="chargeRuleLongPk" fill="obj.chargeRuleLongPk" >
									</div>
								</div>
								<div class="form-group count_rule_div" style="display: none;">
									<label class="col-md-4 control-label ">计时、计次收费规则</label>
									<div class="col-md-6">
										<input class="form-control select2 o_chargeRuleCountOrTime" name="chargeRuleCountOrTimePk" fill="obj.chargeRuleCountOrTimePk">
									</div>
								</div>
								<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
								<input type="hidden" class="form-control normal-input" name="createTime" fill="obj.createTime">
								<input type="hidden" class="form-control normal-input" name="lastEnable" fill="obj.lastEnable">
							</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title" style="font-family: 'Microsoft Yahei'"><span class="glyphicon glyphicon-plus"></span> 绑定停车权限</h3>
							</div>
							<div class="panel-body">
								<div class="form-body">
									<div id="park_tree_edit">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" class="form-control normal-input" id="park_PKs_edit" name="authPKs">
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn bindParkAuth_close">取消</button>
			<button type="button"  id="UGroup_edit_save" class="btn green">保存</button>
		</div>
	</div>
	</div>
</div>
<script>
var edit=function(){
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	var initTree = function(){
		$.ajax({
	        type : "GET",
	        url : "/park/device/park/getParkTree",
	        dataType : "json",    
	        success : function(json) {
	            parkTree(json); 
	        }   
    	});
	}
	
	var parkTree = function(json){
        $('#park_tree_edit').jstree({
            'core' : {
       			'data' : jQuery.parseJSON(json)
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "plugins" : [ "checkbox","dnd","types","wholerow"]
        });
    }
	
	var closeModal = function(){
		$('.bindParkAuth_close').on('click', function () {
			$("#park_tree_edit").jstree().uncheck_all();	
		})
	}
	
	var bindSubmit = function(){
		$("#UGroup_edit_save").on("click",function(){
			if(!check($("#editForm")).form()) return;
			$('#park_PKs_edit').val($('#park_tree_edit').jstree().get_selected());
			if($('.has-error').length<=0){
			 $.ajax({
		       type: "POST",
		       url: pageUrl.user_userGroup_edit,
		       data:$('#editForm').serialize(),// 你的formid
		       async: false,
		       error: function(request) {
		           alert("Connection error");
		       },
		       success: function(data) {
		       	var obj = data;
		       	if(obj.success){
		       		$("#park_tree_edit").jstree().uncheck_all();
		       		$("#modify").modal('hide');
		       		toastr.success(obj.msg,"客户组管理模块");
		       		reload();//重新加载列表
		       	}
		       	else{
		       		toastr.error(obj.msg, "客户组管理模块");
		       	}
		       }
		   }); 
		}
		})
	}
	var typeChanged=function(){
		$('[name=type]').bind('change',function(){
			var type=$(this).val();
			if(type==2){
				$('.long_rule_div').show();
				$('.count_rule_div').hide();
				$('.chang_Ugroup_Div').show();
			}else if(type==0||type==1||type==3){
				$('.count_rule_div').show();
				$('.long_rule_div').hide();
				$('.chang_Ugroup_Div').hide();
			}else if(type==-1){
				$('.count_rule_div').hide();
				$('.long_rule_div').hide();
			}else{
				$('.count_rule_div').show();
				$('.long_rule_div').show();
			}
		})
	}
	
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			if(formObj.changeGroup!=0){
				$("#editForm").find("[name=changeGroup]").val(formObj.changeGroup);
				$("#editForm").find(".ugroup_notlong_name_id > a > span").eq(0).text(formObj.changeGroupName);
			}
			$("#editForm").find(".o_dctRuleGroupPK > a > span").eq(0).text(formObj.dctRuleGroupName);
			$("#editForm").find(".o_chargeRuleLong > a > span").eq(0).text(formObj.chargeRuleLongName);
			$("#editForm").find(".o_chargeRuleCountOrTime > a > span").eq(0).text(formObj.chargeRuleCountOrTimeName);
			//初始化树
			var authPKs = formObj.authPKs;
			var arr = new Array();
			arr = authPKs.split(",");
			$("#park_tree_edit").jstree("open_all");
			for (var i = 0; i < arr.length; i++) {
				$("#park_tree_edit").jstree("check_node", $("#"+arr[i]+""));
			}
			if(formObj.type==3){
				$('#editType').attr('disabled',true);
			}else{
				$('#editType').attr('disabled',false);
			}
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
			closeModal();
			typeChanged();
			initTree();
		},
		 callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		} 
	};
	
}();
</script>