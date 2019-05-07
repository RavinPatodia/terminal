<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看车位锁信息
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
										<label class="col-md-4 control-label">车位锁编号</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.lockId"></label>
										</div>
									</div>
									<div class="form-group">
									    <label class="col-md-4 control-label">车位编号</label>
									    <div class="col-md-6">
										    <label class="form-control-static show" fill="obj.pspId"></label>
									    </div>
								    </div>	
									<div class="form-group">
									    <label class="col-md-4 control-label">品牌</label>
									    <div class="col-md-6">
										    <label class="form-control-static show" fill="obj.brand"></label>
									    </div>
								    </div>
								    <div class="form-group">
									    <label class="col-md-4 control-label">型号</label>
									    <div class="col-md-6">
										    <label class="form-control-static show" fill="obj.model"></label>
									    </div>
								    </div>
								    <div class="form-group">
									    <label class="col-md-4 control-label">IP</label>
									    <div class="col-md-6">
										    <label class="form-control-static show" fill="obj.ip"></label>
									    </div>
								    </div>
								    <div class="form-group">
									    <label class="col-md-4 control-label">端口</label>
									    <div class="col-md-6">
										    <label class="form-control-static show" fill="obj.var2"></label>
									    </div>
								    </div>
								    <div class="form-group">
									    <label class="col-md-4 control-label">车位锁状态</label>
									    <div class="col-md-6">
											<p class="form-control-static" fill="obj.lockState" data-type="int" data-min="0" data-max="1" data-num0="禁用" data-num1="启用"  id="plockState"></p>
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
