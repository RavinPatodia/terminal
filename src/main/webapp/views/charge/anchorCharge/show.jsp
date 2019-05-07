<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看长期收费规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">长期收费规则编号</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.anchorRentId">
											</p></div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">长期收费规则名称</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.name">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">月数</label>
										<div class="col-md-1"><p class="form-control-static" fill="obj.month">
										</p></div>
										<label class="col-md-2 control-label" style="text-align: left">个月</label>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">费用</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.fee">
											</p>
										</div>
										<label class="col-md-2 control-label" style="text-align: left">元</label>
									</div>
									
									<div class="form-group">
										<label class="col-md-4 control-label">附加费用</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.addFee">
											</p>
										</div>
										<label class="col-md-2 control-label" style="text-align: left">元</label>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!--查看 end-->
