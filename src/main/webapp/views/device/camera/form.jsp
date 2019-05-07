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
									<div class="col-md-6" id="rentRuleId_div" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">摄像头编号</label>
										<div class="col-md-8">
											<input type="text" class="form-control input-sm" id="cameraId" value="${cameraId}"/>
										</div>
									</div>
									<div class="col-md-6" id="rentRuleId_div" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">摄像头类型</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm"
												style="display: none;" id="type">
												<option value="-1">请选择</option>
												<option value="0">车道</option>
												<option value="1">过道</option>
												<option value="2">车位</option>
											</select>
										</div>
									</div>
									    
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
										<label class="col-md-3 control-label">车道</label>
										<div class="col-md-8">
											<input class="form-control select2 allDriveway"  id="driveWayPK">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
									<label class="col-md-3 control-label">摄像头状态</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm"
												style="display: none;" id="cameraState">
												<option value="-1">请选择</option>
												<option value="1">启用</option>
												<option value="0">禁用</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
									    <label class="col-md-3 control-label">指示灯状态</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm" id="lightState">
												<option value="-1">请选择</option>
												<option value="0">空闲</option>
												<option value="1">占用</option>
												
											</select>
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

