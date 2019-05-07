<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
	<div id="recover" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ban-circle" ></span>
							恢复
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
						你确定要恢复<label id="delLog"></label>已删除出入场记录?
							<input type="hidden" class="form-control" id="recoversIds"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">Close</button>
					<button type="button" class="btn red" onclick="recover()">Ok</button>
				</div>
			</div>
		</div>
	</div>
	
<script>
var recover  = function(){
	var id = $("#recoversIds").val();
  	$.ajax({
        url: "${recoverUrl}"+id,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	var obj = data;
        	if(obj.success){
        		$('#recover').modal('hide');
        		reload();//重新加载列表
        		toastr.success(obj.msg, "已删除出入场记录管理");
        	}
        	else{
        		$('#recover').modal('hide');
        		toast.error(obj.msg,"已删除出入场记录管理");
        	}
        }
    }); 
}

</script>