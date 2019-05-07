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
									<div class="col-md-6" style="margin: 5px 0px; height:28px;">
										<label class="col-md-3 control-label">操&nbsp;&nbsp;作&nbsp;&nbsp;员</label>
										<div class="col-md-8">
											<input class="form-control input-sm select2 operName_operName" id="operMan">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">操作模块</label>
										<div class="col-md-8">
											<input  class="form-control input-sm" id="operModule"/>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">操作时间</label>
										<div class="col-md-8">
											<div
												class="input-group date-picker input-daterange"
												data-date="10-11-2012" data-date-format="mm-dd-yyyy">
												<input type="text" class="form-control input-sm" name="startTime"
													id="startTime" /> <span class="input-group-addon">
													至 </span> <input type="text" class="form-control input-sm" name="endTime"
													id="endTime" />
											</div>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">操作类型</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm" data-selected="-1" id="operOption">
												<option value="-1">---请选择---</option>
												<option value="新增">新增</option>
												<option value="修改">修改</option>
												<option value="删除">删除</option>
												<option value="禁用">禁用</option>
												<option value="启用">启用</option>
											</select>
										</div>
									</div>
									<div class="col-md-12" style="margin-top: 15px;">
									    <div class="col-md-4 col-md-offset-4">
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

