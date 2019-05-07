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
								<div class="col-md-6" style="margin:5px 0px;" id="anchorRentId_div">
									<label class="col-md-3 control-label">长期规则编号</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="anchorRentId" />
									</div>
								</div>
								<div class="col-md-6" style="margin:5px 0px;">
									<label class="col-md-3 control-label">长期规则名称</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="name"/>
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
							</div><!--form-group-->
						</div><!--form-body-->
					</form>
				</div>
			</div>
			<!-- END POTTLEX 检索------------>

