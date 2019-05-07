<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看摄像头信息
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
										<label class="col-md-4 control-label">摄像头编号</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.cameraId">
											</p>
										</div>
									</div>	
								<div class="form-group">
									<label class="col-md-4 control-label">摄像头类型</label>
									<div class="col-md-6">
										<input type="hidden" class="form-control" fill="obj.type" onchange="camera_show.formatType(this)"/>
										<label class="form-control-static show" id="pType"></label>
									</div>
								</div>
								<div class="form-group name1">
									<label class="col-md-4 control-label">车道</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="drivewayId" fill="obj.drivewayName"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">品牌</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="brand" fill="obj.brand"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">型号</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="model" fill="obj.model"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">描述</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="describion" fill="obj.describion"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="devicePosit" fill="obj.devicePosit"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" id="ip" fill="obj.ip"></label>
									</div>
								</div>
								<div class="form-group lightState">
									<label class="col-md-4 control-label">指示灯状态</label>
									<div class="col-md-6">
											<p class="form-control-static" fill="obj.lightState" data-type="int" data-min="0" data-max="1" data-num0="空闲" data-num1="占用"  id="pLightState"></p>
									</div>
								</div>
								<div class="form-group cameraState">
									<label class="col-md-4 control-label">摄像头状态</label>
									<div class="col-md-6">
										<div class="icheck-list">
											<p class="form-control-static" fill="obj.cameraState" data-type="int" data-min="0" data-max="1" data-num0="禁用" data-num1="启用"  id="pEnableFlag"></p>
										</div>
									</div>
								</div>
								<!-- <div class="form-group">
									<label class="col-md-4 control-label">车位信息</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" id="pspId" fill="obj.pspId"></label>
									</div>
								</div> -->
							</div>
							</div>
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
<script>
var camera_show = function () {
	var formatType=function(ele){
		var value = $(ele).val();
		if(value==0){
			$("#pType").text("车道");
			$(".name1").show();
		}
		else if(value==1){
			$("#pType").text("过道");
			$(".name1").hide();
		}
		else if(value==2){
			$("#pType").text("车位");
			$(".name1").hide();
		}
			
	}

	return {
		formatType:function(ele){
			formatType(ele);
        },
    };
  
}();

</script>
