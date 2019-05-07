<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--查看 start-->
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看收费记录
					</h4>
				</div> 
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="portlet-title">
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
														<a href="#tab_6_1" data-toggle="tab">收费记录</a>
													</li>
													<!-- <li>
														<a href="#tab_6_2" data-toggle="tab">home</a>
													</li> -->
													
												</ul>
											</div>
											<div class="col-md-9 col-sm-9 col-xs-9">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_6_1">
														<div class="form-group">
															<label class="col-md-4 control-label">车牌号码</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.carModel.licensePlate">
																</p></div>
														</div>
					
														<div class="form-group">
															<label class="col-md-4 control-label">车牌类型</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.carModel.type">
																</p></div>
														</div>
					
														<div class="form-group">
															<label class="col-md-4 control-label">付款时间</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.payTime">
																</p></div>
														</div>
					
														<div class="form-group">
															<label class="col-md-4 control-label">优惠规则组</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.dctRuleGroupModel.name">
																</p></div>
														</div>
					
														<div class="form-group">
															<label class="col-md-4 control-label">付款类型</label>
															<div class="col-md-6">
																<p class="form-control-static" fill="obj.payType" data-type="int" data-min="0" data-max="2" data-num0="账户扣款" data-num1="手工收费" data-num2="缴费机" id="payType"></p>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">应付金额</label>
															<div class="col-md-1">
																<p class="form-control-static" fill="obj.amoutPay"></p>
															</div>
															<label class="col-md-1 control-label" >元</label>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">实付金额</label>
															<div class="col-md-1">
																<p class="form-control-static" fill="obj.actualPay">
																</p>
															</div>
															<label class="col-md-1 control-label" >元</label>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">优惠金额</label>
															<div class="col-md-1">
																<p class="form-control-static" fill="obj.dctPay">
																</p>
															</div>
															<label class="col-md-1 control-label" >元</label>
														</div>
													</div>
													<div class="tab-pane fade" id="tab_6_2">
														<table class="table table-bordered table-hover" id="recs">
															<thead>
																<tr>
																	<th>车位编号</th>
																	<th>进入车位时间</th>
																	<th>离开车位时间</th>
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
	</div><!--查看 end-->
<script>
var chargeRec = function(jsonData){
	var dataTable_config = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    "destroy":true,
	    "data": jsonData,
        "bProcessing": true,
        "bFilter": false,
        "bPaginate": false,
        "bAutoWidth":false,//自动宽度
        "bLengthChange":false, //关闭每页显示多少条数据
	    
	    "aoColumns" : [ 
	           	  	{
	           	        "mDataProp" : "id",
	           	        "sWidth": "200px"
	           	    }, {
	           	        "mDataProp" : "if_select",
	           	        "sWidth": "200px"
	           	    },{
	           	        "mDataProp" : "text",
	           	        "sWidth": "200px"
	           	    }
	           	    ],
        "language": {
            "lengthMenu": "  _MENU_ 条数据",
            "emptyTable": "表格中没有数据~",
            "info": "显示 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
            "infoEmpty": "没有数据~",
            "infoFiltered": "(在 _MAX_ 条数据中查询)",
            "lengthMenu": "显示 _MENU_ 条数据",
            "search": "查询:",
            "zeroRecords": "没有找到对应的数据",
   		} 
	};
	var $table = $('#recs');
	oTable = $table.dataTable(dataTable_config);
}; 


</script>