<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
	<div id="del" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ban-circle" ></span>
							删除
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
						你确定要删除<label id="nameDel"></label>闸机?
							<input type="hidden" class="form-control" id="idDel"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">Close</button>
					<button type="button" class="btn red" onclick="del()">Ok</button>
				</div>
			</div>
		</div>
	</div>
	
<script>
var del  = function(){
	var id = $("#idDel").val();
	$.ajax({
        url: "${delUrl}"+id,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	var obj = data;
        	if(obj.success){
        		$('#del').modal('hide');
        		toastr.success(obj.msg, "闸机管理");
        		reload();//重新加载列表
        	}
        	else{
        		$('#del').modal('hide');
        		toast.error(obj.msg,"闸机管理");
        	}
        }
    }); 
}

</script>