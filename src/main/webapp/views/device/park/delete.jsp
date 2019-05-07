<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="delete" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ok-circle" ></span>
							删除
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							你确定要删除【<label id="nameDelete"></label>】停车场?
							<input type="hidden" class="form-control" id="idDelete"/>
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
var del = function(){
	var id = $("#idDelete").val();
	$.ajax({
        url: pageUrl.device_park_del+id,
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	var obj = data;
        	if(obj.success){
        		//$("#park_tree").jstree().destroy();
        		// var tree = jQuery.jstree._reference("#" + id);
                  //      tree.refresh();
        		//$("#park_tree").jstree("refresh",$("#"+id));
        		$('#park_tree').jstree('refresh', -1);
        		toastr.success("删除成功");
        		$("#delete").modal('hide');
        	}
        	else{
        		toast.error("删除失败");
        		$("#delete").modal('hide');
        	}
        }
    }); 
}
</script>