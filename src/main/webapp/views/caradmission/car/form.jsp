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
						<form class="form-horizontal" role="form" method="post"	id="searchForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-8">
											<input class="form-control select2 car_licensePlate_licensePlate input-sm" id="licensePlate">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌类型</label>
										<div class="col-md-8">
											<input class="form-control select2 car_type input-sm" id="type">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none;">
										<label class="col-md-3 control-label">车辆颜色</label>
										<div class="col-md-8">
											<input class="form-control select2 car_color input-sm" id="carColor">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
										<label class="col-md-3 control-label">车辆型号</label>
										<div class="col-md-8">
											<input class="form-control select2 car_module input-sm" id="carModel">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none;">
										<label class="col-md-3 control-label">车辆类型</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm" data-selected="-1" id="carType">
												<option value="-1">请选择</option>
												<option value="0">小车</option>
												<option value="1">大车</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none;">
										<label class="col-md-3 control-label">是否免费放行</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm"	data-selected="-1" id="isFree">
												<option value="-1">请选择</option>
												<option value="1">是</option>
												<option value="0">否</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;display: none;">
										<label class="col-md-3 control-label">客户名</label>
										<div class="col-md-8">
											<input class="form-control select2 user_Name input-sm" id="uacc">
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


