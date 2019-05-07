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
						<form class="form-horizontal" role="form" method="post"	id="searchForm">
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-6" style="margin: 5px 0px; ;">
										<label class="col-md-3 control-label">客户名</label>
										<div class="col-md-8">
											<input class="form-control input-sm select2 user_uacc_uacc" id="uacc">
										</div>
									</div>
									<div class="col-md-6" style="margin: 5px 0px;">
										<label class="col-md-3 control-label">姓名</label>
										<div class="col-md-8">
											<input class="form-control input-sm select2 user_name_name" id="name">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
										<label class="col-md-3 control-label">所属客户组</label>
										<div class="col-md-8">
											<input class="form-control input-sm select2 ugroup_name_id" id="ugroupName">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
										<label class="col-md-3 control-label">车牌号码</label>
										<div class="col-md-8">
											<input class="form-control input-sm select2 car_licensePlate_licensePlate" id="licensePlate">
										</div>
									</div>
									<div class="col-md-6 search_2" style="margin: 5px 0px; display: none;">
										<label class="col-md-3 control-label">状态</label>
										<div class="col-md-4">
											<select class="bs-select form-control input-sm" data-selected="-1" id="state">
												<option value="-1">请选择</option>
												<option value="0">待审批</option>
												<option value="1">审批通过</option>
												<option value="2">审批未通过</option>
												<option value="3">暂停</option>
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
<script type="text/javascript">



</script>

