<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="yh_group_rule" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-ban-circle" ></span>
							 查看优惠规则
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则编号</label>
										<div class="col-md-6"><input type="text" class="form-control"></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则名称</label>
										<div class="col-md-6"><input type="text" class="form-control"></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control " style="display: none;">
												<option value="规定时间段">规定时间段</option>
									            <option value="规定日期段">规定日期段</option>
									            <option value="节假日">节假日</option>
									            <option value="双休日">双休日</option>
									            <option value="会员生日">会员生日</option>
									            <option value="续办折扣">续办折扣</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">创建时间</label>
										<div class="col-md-6">
											<div class="input-group">
												<input type="text" class="form-control timepicker timepicker-24">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">上次启用时间</label>
										<div class="col-md-6">
											<div class="input-group">
												<input type="text" class="form-control timepicker timepicker-24">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
												</span>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">上次禁用时间</label>
										<div class="col-md-6">
											<div class="input-group">
												<input type="text" class="form-control timepicker timepicker-24">
												<span class="input-group-btn">
												<button class="btn default" type="button"><i class="fa fa-clock-o"></i></button>
												</span>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">取消</button>
					<button type="button" class="btn green">确定</button>
				</div>
			</div>
		</div>
	</div><!--查看 end-->