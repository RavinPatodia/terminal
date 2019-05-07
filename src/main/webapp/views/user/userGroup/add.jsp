<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="add" class="modal fade" tabindex="-1" data-width="400"	data-backdrop="static" data-keyboard="false">
<div class="modal-dialog" style="width:1200px">
	<div class="modal-content">
		<div class="modal-body">
			<form class="form-horizontal" role="form" id="addForm">
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
											<input type="text" data-ajax="@{pageUrl.user_userGroup_getUserGroupId}"  readonly="readonly" class="form-control"
												name="ugroupId" id="ugroupIdAdd">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">客户组名称</label>
										<div class="col-md-6">
											<input type="text" class="form-control" name="name">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">客户组类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control" data-selected="-1" style="display: none;" name="type">
												<option value="-1">请选择</option>
												<option value="0">临时客户</option>
												<option value="1">普通会员</option>
												<option value="2">长期会员</option>
											</select>
										</div>
									</div>
									<div class="form-group chang_Ugroup_Div" style="display: none;">
										<label class="col-md-4 control-label">到期变更客户组</label>
										<div class="col-md-6">
											<input class="form-control select2 ugroup_notlong_name_id"  name="changeGroup" fill="obj.changeGroup">
											<label>默认为临时客户组</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">状态</label>
										<div class="col-md-6">
											<input type="checkbox" class="make-switch" data-on-text="启用"
												data-off-text="禁用" data-on-color="danger" name="enableFlag"
												data-off-color="default" checked>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">描述</label>
										<div class="col-md-6">
											<textarea class="form-control normal-input" id="describion"
												name="describion" rows="3" maxlength="254"></textarea>
										</div>
									</div>
									<div class="form-group" >
										<label class="col-md-4 control-label">优惠规则组</label>
										<div class="col-md-6">
											<input class="form-control select2 o_dctRuleGroupPK" name="dctRuleGroupPK">
										</div>
									</div>
									<div class="form-group long_rule_div" style="display: none;">
										<label class="col-md-4 control-label">长期收费规则</label>
										<div class="col-md-6">
											<input class="form-control select2 o_chargeRuleLong" name="chargeRuleLongPk">
										</div>
									</div>
									<div class="form-group count_rule_div" style="display: none;">
										<label class="col-md-4 control-label">计时、计次收费规则</label>
										<div class="col-md-6">
											<input class="form-control select2 o_chargeRuleCountOrTime" id="chargeRuleCountOrTimePk" name="chargeRuleCountOrTimePk">
										</div>
									</div>
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
									<div id="park_tree_add">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" class="form-control normal-input" id="park_PKs_add" name="authPKs">
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" data-dismiss="modal" class="btn">取消</button>
			<button type="button" class="btn green" id="UGroup_add_save">保存</button>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
var add = function() {
		var bindSubmit = function() {
			$('#UGroup_add_save').on('click', function() {
				if(!check($("#addForm")).form()) return;
				$('#park_PKs_add').val($('#park_tree_add').jstree().get_selected());
				if ($('.has-error').length <= 0) {
					$.ajax({
						type : "POST",
						url : pageUrl.user_userGroup_add,
						data : $('#addForm').serialize(),// 你的formid
						async : false,
						error : function(request) {
							alert("Connection error");
						},
						success : function(data) {
							if (data == null || data == "") {
								return;
							}
							var obj = data;
							if (!obj.validateResult) {
								$("#addForm").clearValidator();
								$("#addForm").fillValidator(obj.validateMsg);
								return;
							}
							if (obj.success) {
								$("#add").modal('hide');
								$("#park_tree_add").jstree().uncheck_all();
								toastr.success(obj.msg, "客户组管理模块");
								reload();//重新加载列表
							} else {
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
					$('.long_rule_div').toggle();
					$('.count_rule_div').hide();
					$('.chang_Ugroup_Div').show();
				}else if(type==0||type==1){
					$('.count_rule_div').show();
				}else if(type==-1){
					$('.count_rule_div').hide();
					$('.long_rule_div').hide();
				}else{
					$('.count_rule_div').show();
					$('.long_rule_div').show();
				}
				
			})
		}
		//初始化下拉框
		var initDrop = function() {
			$('.o_dctRuleGroupPK').select2({
				placeholder : "搜索优惠规则组",
				allowClear : true,
				formatNoMatches : "没有匹配的优惠规则组",
				formatSearching : "查询中...",
				ajax : {
					url : pageUrl.user_userGroup_getDctRuleGroup,
					dataType : 'json',
					delay : 500,
					data : function(term, page) {
						return {
							dctRuleGroupName:term
						};
					},
					results : function(data, page) {
						return {
							results : data
						};
					}
				},
				initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength : 0,
				escapeMarkup : function(m) {
					return m;
				}
			});
			
			$('.o_chargeRuleLong').select2({
				placeholder : "搜索长期收费规则",
				allowClear : true,
				formatNoMatches : "没有匹配的长期收费规则",
				formatSearching : "查询中...",
				ajax : {
					url : pageUrl.user_userGroup_getChargeRuleLong,
					dataType : 'json',
					delay : 500,
					data : function(term, page) {
						return {
							chargeRuleLong:term
						};
					},
					results : function(data, page) {
						return {
							results : data
						};
					}
				},
				initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength : 0,
				escapeMarkup : function(m) {
					return m;
				}
			});
			$('.o_chargeRuleCountOrTime').select2({
				placeholder : "搜索计时、计次收费规则",
				allowClear : true,
				formatNoMatches : "没有匹配的计时、计次收费规则",
				formatSearching : "查询中...",
				ajax : {
					url : pageUrl.user_userGroup_getChargeRuleCountOrTime,
					dataType : 'json',
					delay : 500,
					data : function(term, page) {
						return {
							chargeRuleCount:term
						};
					},
					results : function(data, page) {
						return {
							results : data
						};
					}
				},
				initSelection: function (element, callback) {
		        	var id=element.id;
		        	var text = element.name;
		        	if(id!=''&&text!=""){
		                callback({id:id,text:text});
		        	}
		        },
				minimumInputLength : 0,
				escapeMarkup : function(m) {
					return m;
				}
			});
		}
		return {
			init : function() {
				bindSubmit();
				typeChanged();
				initDrop();
			}
		};
	}();
	
	var uitree_add = function(){
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
	        $('#park_tree_add').jstree({
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
	 	return {
	        init: function () {
	            initTree();
	        }
	  	};
	}();

</script>

