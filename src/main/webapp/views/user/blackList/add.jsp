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
					<span class="glyphicon glyphicon-plus"></span> 加入黑名单
				</h4>
			</div>
			<div class="modal-body">
		<form class="form-horizontal" role="form" id="addForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-4 control-label">黑名单用户</label>
									<div class="col-md-6">
										<input class="form-control select2 u_Name" id="userId" name="userPK">
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
										<textarea class="form-control normal-input" id="listReason" name="listReason"  rows="3" maxlength="254"></textarea>
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
									<div class="col-md-3">
										<input type="text" class="form-control normal-input" name="needFee" id="needFee">
									</div>
									<label class="col-md-1 control-label">元</label>
								</div>
							</div>
					</div>
				</div>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal"  class="btn">取消</button>
				<button type="button" class="btn green" id="blackList_add_save">保存</button>
			</div>
		</div>
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
var add=function(){
	var bindSubmit=function(){
		$('#blackList_add_save').on('click',function(){
			if(!check($("#addForm")).form()) return;
			if($('.has-error').length<=0){
				$.ajax({
			        type: "POST",
			        url: pageUrl.user_blackList_add,
			        data:$('#addForm').serialize(),// 你的formid
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
			        		$("#addForm").clearValidator();
			        		$("#addForm").fillValidator(obj.validateMsg);
			        		return;
			        	}
			        	if(obj.success){
			        		$("#add").modal('hide');
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

	//初始化下拉框
	var initDrop=function(){
		$('.u_Name').select2({
			placeholder: "搜索用户",
	      allowClear: true,
	      formatNoMatches: "没有匹配的用户",
	      formatSearching: "查询中...",
	      ajax: {
	          url: pageUrl.user_blackList_getAllUser,
	          dataType: 'json',
	          delay: 500,
	          data: function (term, page) {
	              return {};
	          },
	          results: function (data, page) {
	              return {results: data};
	          }
	      }, 
	      initSelection: function (element, callback) {
	        	var id=element.id;
	        	var text = element.name;
	        	if(id!=''&&text!=""){
	                callback({id:id,text:text});
	        	}
	        },
			minimumInputLength: 0,
			escapeMarkup: function (m) {
	          return m;
	      }
	  });
		
	}
	return {
        init:function(){
        	bindSubmit();
        	initDrop();
        }
    };
}();
</script>

