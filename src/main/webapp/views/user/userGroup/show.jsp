<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看客户组信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-group">
									<label class="col-md-4 control-label">客户组编号</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.ugroupId"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">客户组名称</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.name"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">客户组类型</label>
									<div class="col-md-6">
										<p class="form-control-static" fill="obj.type" data-type="int" data-min="0" data-max="3" data-num0="临时客户组" data-num1="普通客户组" data-num2="长期客户组" data-num3="默认临时客户组" id="type"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">状态</label>
									<div class="col-md-6">
										<p class="form-control-static" fill="obj.enableFlag" data-type="boolean" data-true="启用" data-false="禁用" id="pEnableFlag"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">客户组描述</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.describion"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">创建时间</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.createTime"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">上次启用时间</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.lastEnable"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">上次禁用时间</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.lastDisable"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">优惠规则组</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.dctRuleGroupName"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">长期收费规则</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.chargeRuleLongName"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">计时、计次收费规则</label>
									<div class="col-md-6">
										<label class="form-control-static show" fill="obj.chargeRuleCountOrTimeName"></label>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!--查看 end-->
