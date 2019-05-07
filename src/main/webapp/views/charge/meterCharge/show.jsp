<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看计次收费规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">计次收费规则编号</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.meterChargeId">
											</p></div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">计次收费规则名称</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.name">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">计次免费时间</label>
										<div class="col-md-1"><p class="form-control-static" fill="obj.meteringFree">
											</p></div>
										<label class="col-md-2 control-label" style="text-align: left">分钟</label>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">小车计次收费</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.carMetering">
											</p>
										</div>
										<label class="col-md-2 control-label" style="text-align: left">元</label>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">大车计次收费</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.truckMetering">
											</p>
										</div>
										<label class="col-md-2 control-label" style="text-align: left">元</label>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">是否是历史规则</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.historyFlag" data-type="boolean" data-true="是" data-false="否" id="history"></p>
										</div>
									</div>
									<!-- <div class="form-group">
										<label class="col-md-4 control-label">状态</label>
										<div class="col-md-6">
											<input type="hidden" class="form-control" fill="obj.enableFlag" onchange="chargeRule_show.formatEnableFlag(this)"/>
											<p class="form-control-static" id="enableFlag">
										</div>
									</div> -->

									<div class="form-group">
										<label class="col-md-4 control-label">创建时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.createTime">
											</p>
										</div>
									</div>

									<!-- <div class="form-group">
										<label class="col-md-4 control-label">上次启用时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.lastEnable">
											</p>
										</div>
									</div> -->
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!--查看 end-->
