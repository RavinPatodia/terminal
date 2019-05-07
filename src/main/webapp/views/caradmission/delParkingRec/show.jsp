<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看已删除停车记录信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
			<form class="form-horizontal" role="form" id="showForm">
				<div class="row">
					<div class="col-md-12">
							<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">车牌号码</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.licensePlate"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">车牌类型</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.licenseType"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">车位编号</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.pspId"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">进入车位时间</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.inTime"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">离开车位时间</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.outTime"></label>
										</div>
									</div>
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
var showInfo = function () {
	var formatPay=function(ele){
		var value = $(ele).val();
		if(value=='false'){
			$("#isPay").text("未付");
		}
		else if(value=="true"){
			$("#isPay").text("已付");
		}
	}
	
	return {
        formatEnableFlag:function(ele){
        	formatPay(ele);
        }
    };
	
}();


</script>