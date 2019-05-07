<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看终端机信息
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
										<label class="col-md-4 control-label">终端机编号</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.deviceId"></label>
										</div>
									</div>	
								<div class="form-group">
									<label class="col-md-4 control-label">名称</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.name"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.posit"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" fill="obj.ip"></label>
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
