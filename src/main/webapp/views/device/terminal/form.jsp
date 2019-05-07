<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<!-- START POTTLEX 检索------------>
				<div class="portlet box grey-cascade" style="margin-bottom: 0px;">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-magnifier"></i>数据检索
						</div>
					</div>
					<div class="portlet-body">
						<form class="form-horizontal" role="form" method="post"
							id="searchForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6" id="rentRuleId_div" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">终端机编号</label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="deviceId" value="${deviceId}"/>
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">名称</label>
										<div class="col-md-6">
											<input type="text" class="form-control" id="name" value="${name}"/>
										</div>
									</div>
								
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
								</div>
								<!--form-group-->
							</div>
							<!--form-body-->
						</form>
					</div>
				</div>
<!-- END POTTLEX 检索------------>

