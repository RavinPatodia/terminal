<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<!-- START POTTLEX 检索------------>
				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>数据检索
						</div>
					</div>
					<div class="portlet-body control_label_middle">
						<form class="form-horizontal" role="form" method="post"
							id="searchForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6"  style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-8">
											<input type="text" class="form-control input-sm" id="licensePlate" />
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌类型</label>
										<div class="col-md-8">
											<input class="form-control select2 car_type input-sm" id="licenseType">
										</div>
									</div>
									<div class="col-md-6"  style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车位编号</label>
										<div class="col-md-8">
											<input type="text" class="form-control input-sm" id="pspId" />
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none">
										<label class="col-md-3 control-label">进入车位时间</label>
										<div class="col-md-8">
											<div class="input-group date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
												<input type="text" class="form-control input-sm" name="from" id="dtLastEnableFrom" /> 
												<span class="input-group-addon">至 </span> 
												<input type="text" class="form-control input-sm" name="to" id="dtLastEnableTo" />
											</div>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none">
										<label class="col-md-3 control-label">离开车位时间</label>
										<div class="col-md-8">
											<div class="input-group date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
												<input type="text" class="form-control input-sm" name="from" id="dtLastDisableFrom" /> 
												<span class="input-group-addon">至 </span> 
												<input type="text" class="form-control input-sm" name="to" id="dtLastDisableTo" />
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
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>
<!-- END POTTLEX 检索------------>

