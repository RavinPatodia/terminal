<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看已删除收费记录
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-4 control-label">车牌号码</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.carModel.licensePlate">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">车牌类型</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.carModel.type">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">付款时间</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.payTime">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">优惠规则组</label>
										<div class="col-md-6"><p class="form-control-static" fill="obj.dctRuleGroupModel.name">
											</p></div>
									</div>

									<div class="form-group">
										<label class="col-md-4 control-label">付款类型</label>
										<div class="col-md-6"> 
											<p class="form-control-static" fill="obj.payType" data-type="int" data-min="0" data-max="2" data-num0="账户扣款" data-num1="手工收费" data-num2="缴费机" id="payType"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">应付金额</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.amoutPay">
											</p>
										</div>
										<label class="col-md-1 control-label" >元</label>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">实付金额</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.actualPay">
											</p>
										</div>
										<label class="col-md-1 control-label" >元</label>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">优惠金额</label>
										<div class="col-md-1">
											<p class="form-control-static" fill="obj.dctPay">
											</p>
										</div>
										<label class="col-md-1 control-label" >元</label>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">删除时间</label>
										<div class="col-md-6">
											<p class="form-control-static" fill="obj.delTime">
											</p>
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
