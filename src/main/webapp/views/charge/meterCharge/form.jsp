<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<!-- START POTTLEX 检索------------>
			<div class="portlet box grey-cascade" style="margin-bottom:0px;">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-magnifier"></i>数据检索
					</div>
				</div>
				<div class="portlet-body">
					<form class="form-horizontal" role="form" id="searchForm">
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-6" style="margin:5px 0px;" id="meterChargeId_div">
									<label class="col-md-3 control-label">计次规则编号</label>
									<div class="col-md-6">
										<input type="text" class="form-control " id="meterChargeId" />
									</div>
								</div>
								<div class="col-md-6" style="margin:5px 0px;">
									<label class="col-md-3 control-label">计次规则名称</label>
									<div class="col-md-6">
										<input type="text" class="form-control " id="name"/>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-5">
									</div>
									<div class="col-md-7">
										<a href="javascript:void(0);" id="detailSearch" class="col-md-offset-1" style="display: inline-block;">精确搜索<span
												class="collapse"></span></a>
									</div>
								</div>
								<div class="col-md- search_2" style="padding:0px;display:none;">
									<!-- <div class="col-md-6" style="margin:5px 0px;">
										<label class="col-md-3 control-label">状态</label>
										<div class="col-md-6">
											<select class="bs-select form-control " style="display: none;" id="sectEnableFlag">
												<option selected="selected" value="-1">-请选择-</option>
												<option value="1">启用</option>
										        <option value="0">禁用</option>
											</select>
										</div>
									</div> -->
									<div class="col-md-6" style="margin:5px 0px;">
										<label class="col-md-3 control-label">创建时间</label>
										<div class="col-md-9">
											<div class="input-group input-large date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
												<input type="text" class="form-control" name="from" id="dtCreateTimeFrom">
												<span class="input-group-addon">
												至 </span>
												<input type="text" class="form-control" name="to" id="dtCreateTimeTo">
											</div>
										</div>
									</div>
								</div>
								<!-- <div class="col-md-6 search_2" style="margin:5px 0px;display:none;">
									<label class="col-md-3 control-label">上次启用时间</label>
									<div class="col-md-9">
										<div class="input-group input-large date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
											<input type="text" class="form-control" name="from" id="dtLastEnableFrom">
											<span class="input-group-addon">
											至 </span> 
											<input type="text" class="form-control" name="to" id="dtLastEnableTo">
										</div>
									</div>
								</div>

								<div class="col-md-6 search_2" style="margin:5px 0px;display:none;">
									<label class="col-md-3 control-label">上次禁用时间</label>
									<div class="col-md-9">
										<div class="input-group input-large date-picker input-daterange" data-date="10-11-2012" data-date-format="mm-dd-yyyy">
											<input type="text" class="form-control" name="from" id="dtLastDisableFrom">
											<span class="input-group-addon">
											至 </span>
											<input type="text" class="form-control" name="to" id="dtLastDisableTo">
										</div>
									</div>
								</div> -->
								<div class="col-md-12" style="margin-top: 15px;">
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="reload()"
												class="btn green col-md-2 col-md-offset-10">查询</a>
										</div>
										<div class="col-md-6">
											<a href="javascript:void(0)" onclick="clearForm()"
												class="btn default col-md-2">清空</a>
										</div>
								</div>
							</div><!--form-group-->
						</div><!--form-body-->
					</form>
				</div>
			</div>
			<!-- END POTTLEX 检索------------>
