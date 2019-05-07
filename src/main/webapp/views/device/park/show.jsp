<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>查看车位组信息
										</div>
										<div class="tools">
											<a href="javascript:;" class="collapse"></a>
											<a href="#portlet-config" data-toggle="modal" class="config"></a>
										</div>
									</div>
									<div class="portlet-body">
										<div class="row">
											<div class="col-md-3 col-sm-3 col-xs-3">
												<ul class="nav nav-tabs tabs-left">
													<li class="active">
														<a href="#tab_6_1" data-toggle="tab">车位组信息</a>
													</li>
													<li>
														<a href="#tab_6_2" data-toggle="tab">收费规则</a>
													</li>
													<li>
														<a href="#tab_6_3" data-toggle="tab">车位</a>
													</li>
												</ul>
											</div>
											<div class="col-md-9 col-sm-9 col-xs-9">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_6_1">
														<div class="form-group">
										                     <label class="col-md-4 control-label">车位组编号</label>
										                     <div class="col-md-6"><p class="form-control-static" fill="obj.spGroupId">
											                 </p></div>
									                    </div>
														<div class="form-group">
										                     <label class="col-md-4 control-label">车位组名称</label>
										                     <div class="col-md-6"><p class="form-control-static" fill="obj.name">
											                 </p></div>
									                    </div>
														<div class="form-group">
										                     <label class="col-md-4 control-label">车位组描述</label>
										                     <div class="col-md-6"><p class="form-control-static" fill="obj.describion">
											                 </p></div>
									                    </div>
														<div class="form-group">
										                    <label class="col-md-4 control-label">是否启用</label>
										                    <div class="col-md-6">
										                       	<p class="form-control-static" fill="obj.isEnable" data-type="int" data-min="0" data-max="1" data-num0="禁用" data-num1="启用" id="venable"></p>
										                    </div>
									                    </div>
														<div class="form-group">
															<label class="col-md-4 control-label">创建时间</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.createTime">
															</p></div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">上次启用时间</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.lastEnable">
															</p></div>
														</div>	
														<div class="form-group">
															<label class="col-md-4 control-label">上次禁用时间</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.lastDisable">
															</p></div>
														</div>		
													</div>
													<div class="tab-pane fade" id="tab_6_2">
														<table class="table table-bordered table-hover" id=ruleRecs>
															<thead>
																<tr>
																	<th>收费规则编号</th>
																	<th>收费规则名称</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														
													</div>
													<div class="tab-pane fade" id="tab_6_3">
														<table class="table table-bordered table-hover" id="pSpGroupRecs">
															<thead>
																<tr>
																	<th>车位编号</th>
																	<th>所属区域</th>
																	<th>是否被租用</th>
																	<th>车位租赁规则</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>
var ruleRecs = function(jsonData){
	var dataTable_config = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    "data": jsonData,
        "bProcessing": true,
        "bFilter": false,
        "bPaginate": false,
        "bAutoWidth":false,//自动宽度
        "bLengthChange":false, //关闭每页显示多少条数据
	    "aoColumns" : [
					
	           	  	{
				        "mDataProp" : "rentRuleId",
				        "sWidth": "200px"
				    }, {
				        "mDataProp" : "ruleName",
				        "sWidth": "200px"
				    }
	           	    ],
	};
	var $table = $('#ruleRecs');
	oTable = $table.dataTable(dataTable_config);
}; 
var pSpGroupRecs = function(jsonData){
	var dataTable_config = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    "data": jsonData,
        "bProcessing": true,
        "bFilter": false,
        "bPaginate": false,
        "bAutoWidth":false,//自动宽度
        "bLengthChange":false, //关闭每页显示多少条数据
	    "aoColumns" : [ 
	           	  	{
				        "mDataProp" : "pspId",
				        "sWidth": "200px"
				    }, {
				        "mDataProp" : "areaId",
				        "sWidth": "200px"
				    }, {
				        "mDataProp" : "isRent",
				        "sWidth": "100px"
				    }, {
				        "mDataProp" : "rentRuleId",
				        "sWidth": "100px"
				    }
	           	    ],
  		"columnDefs": [
  						{  aTargets: [2],
  							   "render": function ( data, type, row ) {
  								   if(data=="1"){
  									   return "是";
  								   }else if(data=="0"){
  									   return "否";
  								   }else{
  									   return "";
  								   }
  								}
  						}
  					]
	};
	var $table = $('#pSpGroupRecs');
	oTable = $table.dataTable(dataTable_config);
}; 


</script>