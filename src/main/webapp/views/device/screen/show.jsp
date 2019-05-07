<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看显示屏信息
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
										<label class="col-md-4 control-label">显示屏编号</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.ledId"></label>
										</div>
									</div>	
									<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.name"></label>
									</div>
									</div>
								<div class="form-group">
									<label class="col-md-4 control-label">显示屏类型</label>
									<div class="col-md-6">
										<input type="hidden" class="form-control" fill="obj.type" onchange="screen_show.formatState(this)"/>
										<label class="form-control-static show" id="pState"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">数据转换主机</label>
									<div class="col-md-6">
									    <label class="form-control-static show" fill="obj.dataMaster"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">控制卡编号</label>
									<div class="col-md-6">
									    <label class="form-control-static show" fill="obj.cardNum"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">字体颜色</label>
									<div class="col-md-6">
										<p class="form-control-static" fill="obj.color" data-type="int" data-min="0" data-max="2" data-num0="红" data-num1="绿" data-num2="黄" id="pColor"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">宽</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.width"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">高</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" fill="obj.height"></label>
									</div>
								</div>
								<div class="form-group directionDiv" style="display:none;">
									<label class="col-md-4 control-label">指示方向</label>
									<div class="col-md-6">
									    <p class="form-control-static" fill="obj.direction" data-type="int" data-min="0" data-max="3" data-num0="上" data-num1="下" data-num2="左" data-num3="右" id="vDirection"></p>
									</div>
								</div>
								<div class="form-group messageDiv">
									<label class="col-md-4 control-label">显示信息</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" fill="obj.message"></label>
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
		</div>
<script>
var screen_show = function () {
	var formatState=function(ele){
		var value = $(ele).val();
		if(value==0){
			$("#pState").text("信息显示屏");
			$(".messageDiv").show();
			$(".directionDiv").hide();
		}
		else if(value==1){
			$("#pState").text("区位屏");
			$(".messageDiv").hide();
			$(".directionDiv").hide();
		}
		else if(value==2){
			$("#pState").text("车位引导屏");
			$(".messageDiv").hide();
			$(".directionDiv").show();
		}
		else{
			$("#pState").text("收费屏");
			$(".messageDiv").show();
			$(".directionDiv").hide();
		}
	}
	
	return {
		formatState:function(ele){
			formatState(ele);
        }
    };
	
}();

</script>