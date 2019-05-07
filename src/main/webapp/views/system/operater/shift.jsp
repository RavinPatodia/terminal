<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="/views/include/taglib.jsp"%>
<div id="shiftModal" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
	<div class="modal-dialog" style="width:900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
					<span class="glyphicon glyphicon-pencil" ></span>  操作员交接班
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="shiftForm">
							<div class="form-body">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="col-md-6"  style="margin: 5px 0px;">
											<label class="col-md-4 control-label">上班时间</label>
											<div class="col-md-8">
												<input class="form-control input-sm select2" name="workTime" fill="obj.workTime" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6"  style="margin: 5px 0px;">
											<label class="col-md-4 control-label">交班人</label>
											<div class="col-md-8">
												<input class="form-control input-sm select2 " fill="obj.offOperName" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6"  style="margin: 5px 0px;">
											<label class="col-md-4 control-label">交班时间</label>
											<div class="col-md-8">
												<input class="form-control input-sm select2" name="offWorkTime" fill="obj.offWorkTime" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-4 control-label">接班人</label>
											<div class="col-md-8">
												<input class="form-control input-sm select2 oper_name_id_terminal" name="workOperPK">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;"></div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-4 control-label">接班人密码</label>
											<div class="col-md-8">
												<input type="password" class="form-control input-sm select2 " name="pwd">
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">总流水</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="totalPay" fill="obj.totalPay" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">临时车辆收费金额</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="tempCash" fill="obj.tempCash" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">普通会员扣款金额</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="memberAccountPay" fill="obj.memberAccountPay" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">普通会员支付现金</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="memberCash" fill="obj.memberCash" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">应收现金金额</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="amoutPay" fill="obj.amoutPay" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">实收现金金额</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="actualPay" fill="obj.actualPay" readonly="readonly">
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">收费车辆数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="chargeCar" fill="obj.chargeCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">临时车辆出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="tempCar" fill="obj.tempCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">普通会员出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="memberCar" fill="obj.memberCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">长期会员出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="anchorCar" fill="obj.anchorCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">VIP免费车辆出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="freeCar" fill="obj.freeCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">免费车牌数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="freePlate" fill="obj.freePlate" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">发生费用免费出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="specialFreeCar" fill="obj.specialFreeCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">上班时场内停车数 </label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="workCar" fill="obj.workCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">下班时场内停车数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="offWorkCar" fill="obj.offWorkCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">自动开闸出场数</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="outCar" fill="obj.outCar" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">手动开闸出场数 </label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="outLift" fill="obj.outLift" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6" style="margin: 5px 0px;">
											<label class="col-md-5 control-label">终端机</label>
											<div class="col-md-7">
												<input class="form-control input-sm select2" name="terminalName" fill="obj.terminalName" readonly="readonly">
											</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
							<input type="hidden" class="form-control normal-input" name="offOperPK" fill="obj.offOperPK">
							<input type="hidden" class="form-control normal-input" name="terminalPK" fill="obj.terminalPK">
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn">取消</button>
				<button type="button"  id="shift_save_btn" class="btn green">交班</button>
			</div>
		</div>
	</div>
</div>
<script>
var shift=function(){	
	var bindClick=function(){
			$.ajax({
				url: "/terminal/admin/sys/shift/getShiftRec"+"?t="+Math.random(),
				success : function(data) {
					bindDataAndOpenModal(data, $('#shiftModal'));
				},
				error : function(error) {
					alert(error.status + "," + error.readyState);
				}
			});
	}
	
	var bindSubmit = function(){
		$("#shift_save_btn").on("click",function(){
		//	if(!check($("#shiftForm")).form()) return;
			if($('.has-error').length<=0){
				var options = {
					dataType:  'json',
					type: "post",
					url :"/terminal/admin/sys/shift/shift",
					success: afterSubmit,
				}	
				$("#shiftForm").ajaxSubmit(options);
				function afterSubmit(data){
					if(data==null||data==""){
		        		return;
		        	}
		        	var obj = data;
		        	/* if(!obj.validateResult){
		        		$("#shiftForm").clearValidator();
		        		$("#shiftForm").fillValidator(obj.validateMsg);
		        		return;
		        	} */
		        	if(obj.success){
		        		window.location.href="/terminal/index.jsp?backurl="+window.location.href; 
		        		$("#shiftModal").modal('hide');
		        	} else{
		        		toastr.error(obj.msg, "操作员交接班");
		        	}
				}  
			
			}
		})
	}
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		initOperNameIdTerminal();
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#shiftForm",dialog).fill(obj.object);//表单填充插件
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
		}
	};
	
}();
var initOperNameIdTerminal=function(){
	$('.oper_name_id_terminal').select2({
			placeholder: "搜索操作员",
	        allowClear: true,
	        formatNoMatches: "没有匹配的操作员",
	        formatSearching: "查询中...",
	        ajax: {
	            url: "/terminal/admin/sys/operater/getOperNameIdTerminal?",
	            dataType: 'json',
	            delay: 500,
	            data: function (term, page) {
	                return {
	                	name:term
	                };
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
</script>