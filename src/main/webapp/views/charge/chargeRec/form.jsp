<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<!-- START POTTLEX 检索------------>
			<div class="portlet box grey-cascade" style="margin-bottom:0px;">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-magnifier"></i>数据检索
					</div>
				</div>
				<div class="portlet-body control_label_middle">
					<form class="form-horizontal" role="form" id="searchForm">
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-6" style="margin:5px 0px;">
									<label class="col-md-3 control-label">车牌号码</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="tLicensePlate"/>
									</div>
								</div>
								<div class="col-md-6" style="margin:5px 0px;">
									<label class="col-md-3 control-label">车牌类型</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="sectLicensePlateType"/>
									</div>
								</div>
								<div class="col-md-12 search_2" style="padding:0px;display:none;">
									<div class="col-md-6" style="margin:5px 0px;">
										<label class="col-md-3 control-label">付款方式</label>
										<div class="col-md-4">
											<select class="bs-select form-control  input-sm" style="display: none;" id="sectPayType">
												<option selected="selected" value="-1">请选择</option>
												<option value="0">账户支付</option>
									          	<option value="1">现金支付</option>
									            <option value="2">缴费机支付</option>
											</select>
										</div>
									</div>
									<div class="col-md-6" style="margin:5px 0px;">
										<label class="col-md-3 control-label">付款时间</label>
										<div class="col-md-8">
											<div class="input-group date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
												<input type="text" class="form-control input-sm" name="from" id="dtPayTimeFrom">
												<span class="input-group-addon">
												至 </span>
												<input type="text" class="form-control input-sm" name="to" id="dtPayTimeTo">
											</div>
										</div>
									</div>
								</div>
								<div class="col-md- search_2" style="padding:0px;display:none;">
									<div class="col-md-6" style="margin:5px 0px;">
										<label class="col-md-3 control-label">优惠规则组名称</label>
										<div class="col-md-8">
											<input type="text" class="form-control input-sm" id="tDctRuleGroupName"/>
										</div>
									</div>
								</div>
								<div class="col-md-12" style="margin-top: 15px;">
								    <div class="col-md-4 col-md-offset-4">
									    <div class="col-md-4">
											<a href="javascript:void(0)" id="detailSearch" class="btn btn-sm btn-clear col-md-12">精确检索</a>
										</div>
										<div class="col-md-4">
											<a href="javascript:void(0)" onclick="reload()"	class="btn btn-sm btn-query col-md-12">查询</a>
										</div>
										<div class="col-md-4">
											<a href="javascript:void(0)" onclick="clearForm()" class="btn btn-sm btn-clear col-md-12">清空</a>
										</div>
									</div>
								</div> 
							</div><!--form-group-->
						</div><!--form-body-->
					</form>
				</div>
			</div>
			<!-- END POTTLEX 检索------------>
