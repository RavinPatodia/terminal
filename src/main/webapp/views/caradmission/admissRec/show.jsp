<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
	<div class="modal-dialog" style="width:800px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 查看出入场信息
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="showForm">
								<div class="portlet box blue" style="border:0">
									<div class="portlet-body">
										<div class="row">
											<div class="col-md-3 col-sm-3 col-xs-3">
												<ul class="nav nav-tabs tabs-left">
													<li class="active"><a href="#tab_6_1" data-toggle="tab">出入场记录</a></li>
													<li><a href="#tab_6_2" data-toggle="tab">停车记录 </a></li>
												</ul>
											</div>
											<div class="col-md-9 col-sm-9 col-xs-9">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_6_1">
														<div class="form-group">
															<label class="col-md-4 control-label">车牌号码</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.licensePlate"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">车牌类型</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.licenseType"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">车辆型号</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.module"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">车辆颜色</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.color"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">车辆类型</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.carType"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">入卡口</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.bayonetEntranceName"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">入车道</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.inDriveWayName"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">入场时间</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.inTime"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">出卡口</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.bayonetExitName"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">出车道</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.outDriveWayName"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">出场时间</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.outTime"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">入场识别方式</label>
															<div class="col-md-6">
																	<p class="form-control-static" fill="obj.inRecoWay" data-type="int" data-min="0" data-max="1" data-num0="车牌识别" data-num1="人工识别" id="inWay"></p>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">出场识别方式</label>
															<div class="col-md-6">
																	<p class="form-control-static" fill="obj.outRecoWay" data-type="int" data-min="0" data-max="1" data-num0="车牌识别" data-num1="人工识别" id="outWay"></p>
															</div>
														</div>
						
														<div class="form-group">
															<label class="col-md-4 control-label">应付金额</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.fee">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">停车场</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.parkName"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">是否付款</label>
															<div class="col-md-6">
																	<p class="form-control-static" fill="obj.isPay" data-type="boolean" data-true="已付" data-false="未付" id="pay"></p>
															</div>
														</div>					
													</div>
													<div class="tab-pane fade" id="tab_6_2">
														<table class="table table-bordered table-hover" id="parkingRecs">
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
</div>
<script>
var parkingRecs = function(jsonData){
	var dataTable_config = {//jquery_datatable插件的默认参数配置
	    //下面设置数据栏目是否可排序
	    destroy: true,
	    "data": jsonData,
        "bProcessing": true,
        "bFilter": false,
        "bPaginate": false,
        "bAutoWidth":false,//自动宽度
        "bLengthChange":false, //关闭每页显示多少条数据
        "language": {
            "lengthMenu": "  _MENU_ 条数据",
            "emptyTable": "表格中没有数据~",
            "info": "显示 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
            "infoEmpty": "没有数据~",
            "infoFiltered": "(在 _MAX_ 条数据中查询)",
            "lengthMenu": "显示 _MENU_ 条数据",
            "search": "查询:",
            "zeroRecords": "没有找到对应的数据",
   		}, 
	    "aoColumns" : [ 
	           	  	{
	           	        "mDataProp" : "pspId",
	           	        "sWidth": "200px"
	           	    }, {
	           	        "mDataProp" : "inTime",
	           	        "sWidth": "200px"
	           	    },{
	           	        "mDataProp" : "outTime",
	           	        "sWidth": "200px"
	           	    }
	           	    ],
	};
	var $table = $('#parkingRecs');
	var oTable_parkingRec = $table.dataTable(dataTable_config);
}; 


</script>