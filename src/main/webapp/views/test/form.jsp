<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>

				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>入场
						</div>
					</div>
					<div class="portlet-body">
						<form class="form-horizontal" action="/terminal/caradmission/enter/getData" role="form" method="post" id="inForm">
							<div class="form-body">
								<div class="form-group">
								    <div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-4 control-label">车道</label>
										<div class="col-md-6">
											<input type="hidden"  class="form-control select2 inDriveway"  name="drivewayPK">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-6">
											<input type="text" class="form-control licensePlate" name="carModel.licensePlate"/>
										</div>
										<span class="help-block" id="editcar_existmsg">(如：浙A88888)</span>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌类型</label>
										<div class="col-md-6">
											<input class="form-control select2 car_type" name="carModel.type">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车辆颜色</label>
										<div class="col-md-6">
											<input class="form-control select2 car_color" name="carModel.carColor">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车辆型号</label>
										<div class="col-md-6">
											<input class="form-control select2 car_module" name="carModel.carModel">
										</div>
									</div>
									<div class="col-md-6">
										<label class="col-md-3 control-label">车辆类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control" name="carType" data-selected="-1" id="carModel.carType">
												<!-- <option value="-1">请选择</option> -->
												<option value="0">小车</option>
												<option value="1">大车</option>
											</select>
										</div>
									</div>
									<div class="col-md-12" style="margin-top: 15px;">
										<div class="col-md-6">
											<input  type="submit"
												class="btn green col-md-2 col-md-offset-10">
										</div>
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="clearForm()"
												class="btn default col-md-2">清空</a>
										</div>
									</div>
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>

				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>出场
						</div>
					</div>
					<div class="portlet-body">
						<form class="form-horizontal" action="/terminal/caradmission/exit/getData/-1" role="form" method="post"
							id="outForm">
							<div class="form-body">
								<div class="form-group">
									 <div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-4 control-label">车道</label>
										<div class="col-md-6">
											<input type="hidden"  class="form-control select2 outDriveway"  name="drivewayPK">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-6">
											<input type="text" class="form-control licensePlate" name="carModel.licensePlate"/>
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌类型</label>
										<div class="col-md-6">
											<input class="form-control select2 car_type" name="carModel.type">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车辆颜色</label>
										<div class="col-md-6">
											<input class="form-control select2 car_color" name="carModel.carColor">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车辆型号</label>
										<div class="col-md-6">
											<input class="form-control select2 car_module" name="carModel.carModel">
										</div>
									</div>
									<div class="col-md-6">
										<label class="col-md-3 control-label">车辆类型</label>
										<div class="col-md-6">
											<select class="bs-select form-control" name="carType" data-selected="-1" id="carModel.carType">
												<!-- <option value="-1">请选择</option> -->
												<option value="0">小车</option>
												<option value="1">大车</option>
											</select>
										</div>
									</div>
									<div class="col-md-12" style="margin-top: 15px;">
										<div class="col-md-6">
											<input  type="submit"
												class="btn green col-md-2 col-md-offset-10">
										</div>
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="clearForm()"
												class="btn default col-md-2">清空</a>
										</div>
									</div>
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>

				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>入车位
						</div>
					</div>
					<div class="portlet-body">
						<form class="form-horizontal" action="/terminal/caradmission/pspInOut/in" role="form" method="post"
							id="inCarForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车位编号</label>
										<div class="col-md-6">
											<input class="form-control select2 pspId" name="pspId">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-6">
											<input type="text" class="form-control licensePlate" name="licensePlate" />
										</div>
									</div>
									<div class="col-md-12" style="margin-top: 15px;">
										<div class="col-md-6">
											<input  type="submit"
												class="btn green col-md-2 col-md-offset-10">
										</div>
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="clearForm()"
												class="btn default col-md-2">清空</a>
										</div>
									</div>
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>

				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>出车位
						</div>
					</div>
					<div class="portlet-body">
						<form class="form-horizontal" action="/terminal/caradmission/pspInOut/out" role="form" method="post" id="outCarForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车位编号</label>
										<div class="col-md-6">
											<input class="form-control select2 pspId" name="pspId">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-6">
											<input type="text" class="form-control licensePlate" name="licensePlate" />
										</div>
									</div>
									
									<div class="col-md-12" style="margin-top: 15px;">
										<div class="col-md-6">
											<input  type="submit"
												class="btn green col-md-2 col-md-offset-10">
										</div>
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="clearForm()"
												class="btn default col-md-2">清空</a>
										</div>
									</div>
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>