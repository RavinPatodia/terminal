<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400"> <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看优惠规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则编号</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.dctId">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则名称</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.dctName">
										</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则类型</label>
										<div class="col-md-6">
										    <input type="hidden" class="form-control" fill="obj.type" onchange="chargeRule_show.formatType(this)"/>
											<p class="form-control-static" id="type">
											</p>
										</div>
									</div>
									<div class="form-group dctRule_weekendDiv">
										<label class="col-md-4 control-label">双休日类型</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.weekendType" data-type="int" data-min="0" data-max="2" data-num0="周六" data-num1="周日" data-num2="周六+周日" id="weekendType">
											</p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">优惠形式</label>
										<div class="col-md-6">
											<input type="hidden" class="form-control" fill="obj.dctType" onchange="chargeRule_show.formatDctType(this)"/>
											<p class="form-control-static" id="dctType">
											</p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">是否是历史规则</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.historyFlag" data-type="boolean" data-true="是" data-false="否" id="history"></p>
										</div>
									</div>
									<div class="form-group dctRule_dctPerDiv">
										<label class="col-md-4 control-label">折扣</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.dctPer">
											</p>
										</div>
									</div>
                                    <div class="form-group dctRule_dctFeeDiv">
										<label class="col-md-4 control-label">金额</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.dctFee">
											</p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">创建时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.createTime">
											</p>
										</div>
									</div>
									<div class="form-group dctRule_timeDiv">
										<label class="col-md-4 control-label">起始时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.beginTime">
											</p>
										</div>
									</div>
									<div class="form-group dctRule_timeDiv">
										<label class="col-md-4 control-label">结束时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.endTime">
											</p>
										</div>
									</div>
									<div class="form-group dctRule_dateDiv">
										<label class="col-md-4 control-label">起始日期</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.beginDate">
											</p>
										</div>
									</div>
									<div class="form-group dctRule_dateDiv">
										<label class="col-md-4 control-label">结束日期</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.endDate">
											</p>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!--查看 end-->
<script>
var chargeRule_show = function () {
	var formatType=function(ele){
		var value = $(ele).val();
		if(value=="0"){
			$("#type").text("会员生日");
			$(".dctRule_dateDiv").hide();
			$(".dctRule_timeDiv").hide();
			$(".dctRule_weekendDiv").hide();
		}
		else if(value=="1"){
			$("#type").text("节假日");
			$(".dctRule_dateDiv").hide();
			$(".dctRule_timeDiv").hide();
			$(".dctRule_weekendDiv").hide();
		}
		else if(value=="2"){
			$("#type").text("双休日");
			$(".dctRule_dateDiv").hide();
			$(".dctRule_timeDiv").hide();
			$(".dctRule_weekendDiv").show();
		}
		else if(value=="3"){
			$("#type").text("规定时间段");
			$(".dctRule_dateDiv").hide();
			$(".dctRule_timeDiv").show();
			$(".dctRule_weekendDiv").hide();
		}
		else if(value=="4"){
			$("#type").text("规定日期段");
			$(".dctRule_dateDiv").show();
			$(".dctRule_timeDiv").hide();
			$(".dctRule_weekendDiv").hide();
		}
		else if(value=="5"){
			$("#type").text("续办折扣");
			$(".dctRule_dateDiv").hide();
			$(".dctRule_timeDiv").hide();
			$(".dctRule_weekendDiv").hide();
		};

	var formatDctType=function(ele){
		var value = $(ele).val();
		if(value=="0"){
			$("#dctType").text("减免金额");
			$(".dctRule_dctPerDiv").hide();
			$(".dctRule_dctFeeDiv").show();		
		}
		else if(value=="1"){
			$("#dctType").text("折扣");
			$(".dctRule_dctPerDiv").show();
			$(".dctRule_dctFeeDiv").hide();
		}
		else if(value=="2"){
			$("#dctType").text("全免");
			$(".dctRule_dctPerDiv").hide();
			$(".dctRule_dctFeeDiv").hide();
		}
	} 
	return {
        //main function to initiate the module
        formatType:function(ele){
        	formatType(ele);
        	formatDctType(ele);
        }
    };
	
}();


</script>