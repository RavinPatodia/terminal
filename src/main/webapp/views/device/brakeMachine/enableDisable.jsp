<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="enable" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ok-circle" ></span>
							 开启
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							你确定要开启<label id="deviceNameEnable"></label>闸机?
							<input type="hidden" class="form-control" id="deviceIdEnable"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">Close</button>
					<button type="button" class="btn red" onclick="enable()">Ok</button>
				</div>
			</div>
		</div>
	</div>
	<div id="disable" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ban-circle" ></span>
							 关闭
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
						你确定要关闭<label id="deviceNameDisable"></label>闸机?
							<input type="hidden" class="form-control" id="deviceIdDisable"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">Close</button>
					<button type="button" class="btn red" onclick="disable()">Ok</button>
				</div>
			</div>
		</div>
	</div>
<script>
var enable  = function(){
	var id = $("#deviceIdEnable").val();
	$.ajax({
        url: "${enableBrakeUrl}"+id,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	var obj = data;
        	if(obj.success){
        		$('#enable').modal('hide');
        		reload();//重新加载列表
        		toastr.success(obj.msg, "硬件管理");
        		//alert(obj.msg);
        	}
        	else{
        		$('#enable').modal('hide');
        		//alert(obj.msg);
        		toastr.error(obj.msg,"硬件管理");
        	}
        }
    }); 
}

var disable  = function(){
	var id = $("#deviceIdDisable").val();
	$.ajax({
        url: "${disableBrakeUrl}"+id,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	var obj = data;
        	if(obj.success){
        		$('#disable').modal('hide');
        		reload();//重新加载列表
        		toastr.success(obj.msg, "硬件管理");
        		//alert(obj.msg);
        	}
        	else{
        		$('#disable').modal('hide');
        		toastr.error(obj.msg,"硬件管理");
        		//alert(obj.msg);
        	}
        }
    }); 
}

</script>