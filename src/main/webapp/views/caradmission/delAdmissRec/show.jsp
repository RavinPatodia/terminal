<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看已删除出入场记录信息
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
										<label class="col-md-4 control-label">车牌号码</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.car"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">车牌类型</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.licenseType"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">入口</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.bayonetEntranceName"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">入场时间</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.inTime"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">出口</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.bayonetExitName"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">出场时间</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.outTime"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">入场识别方式</label>
										<div class="col-md-6">
												<p class="form-control-static" fill="obj.inRecoWay" data-type="int" data-min="0" data-max="1" data-num0="车牌识别" data-num1="人工识别" id="inRecoWay"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">出场识别方式</label>
										<div class="col-md-6">
												<p class="form-control-static" fill="obj.outRecoWay" data-type="int" data-min="0" data-max="1" data-num0="车牌识别" data-num1="人工识别" id="outRecoWay"></p>
										</div>
									</div>
	
									<div class="form-group">
										<label class="col-md-4 control-label">应付金额</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.fee">
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">是否付款</label>
										<div class="col-md-6">
												<p class="form-control-static" fill="obj.isPay" data-type="boolean" data-true="已付" data-false="未付" id="pay"></p>
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
	</div><!--查看 end-->
