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
								<div class="col-md-6" style="margin:5px 0px;" id="searchForm_dctId_div">
									<label class="col-md-3 control-label">优惠规则编号</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="tDctId"/>
									</div>
								</div>
								<div class="col-md-6" style="margin:5px 0px;">
									<label class="col-md-3 control-label">优惠规则名称</label>
									<div class="col-md-8">
										<input type="text" class="form-control input-sm" id="tDctName"/>
									</div>
								</div>
								<div class="col-md-6 search_2" style="margin:5px 0px; display:none;">
									<label class="col-md-3  control-label">类型</label>
									<div class="col-md-4">
										<select class="bs-select form-control input-sm" style="display: none;" id="selType">
											<option selected="selected" value="-1">-请选择-</option>
											<option value="0">会员生日</option>
								          	<option value="1">节假日</option>
								            <option value="2">双休日</option>
								            <option value="3">规定时间段</option>
								            <option value="4">规定日期段</option>
								            <option value="5">续办折扣</option>
										</select>
									</div>
								</div>
                                <div class="col-md-6 search_2" style="margin:5px 0px; display:none;">
									<label class="col-md-3 control-label">优惠形式</label>
									<div class="col-md-4">
										<select class="bs-select form-control input-sm" id="tDctType">
											<option selected="selected" value="-1">-请选择-</option>
								            <option value="0">减免金额</option>
								            <option value="1">折扣</option>
								            <option value="2">全免</option>
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
							</div><!--form-group-->
						</div><!--form-body-->
					</form>
				</div>
			</div>
			<!-- END POTTLEX 检索------------>
