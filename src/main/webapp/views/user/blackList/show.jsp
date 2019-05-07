<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看黑名单信息
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
										<label class="col-md-4 control-label">黑名单编号</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.userPK"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">用户名</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.userName"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">姓名</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.name"></label>
										</div>
									</div>
	                                <div class="form-group">
										<label class="col-md-4 control-label">列入黑名单原因</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.listReason"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">应缴金额</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.needFee"></label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">是否生效</label>
										<div class="col-md-6">
												<p class="form-control-static" fill="obj.isEffect" data-type="int" data-min="0" data-max="1" data-num0="失效" data-num1="生效"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">列入时间</label>
										<div class="col-md-6">
											<label class="form-control-static show" fill="obj.listTime">
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">到期日期</label>
											<div class="col-md-6">
												<label class="form-control-static show" fill="obj.endTime">
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
