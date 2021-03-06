<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看闸机信息
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
										<label class="col-md-4 control-label">闸机编号</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.deviceId">
											</p>
										</div>
									</div>	
									<div class="form-group">
									<label class="col-md-4 control-label">名称 </label>
									<div class="col-md-6">
										<label class="form-control-static show" id="name" fill="obj.name"></label>
									</div>
								</div>
								<div class="form-group bayonetId" >
									<label class="col-md-4 control-label">对应出入口</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="bayonetId" fill="obj.bayonetId"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">位置</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="posit" fill="obj.posit"></label>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label">IP</label>
									<div class="col-md-6"> 
										<label class="form-control-static show" id="ip" fill="obj.ip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">开信号</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="open" fill="obj.open"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">关信号</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="close" fill="obj.close"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">端口号</label>
									<div class="col-md-6">
										<label class="form-control-static show" id="port" fill="obj.port"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<p class="form-control-static" fill="obj.enableFlag" data-type="boolean" data-true="开启" data-false="关闭" id="pEnableFlag">
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
