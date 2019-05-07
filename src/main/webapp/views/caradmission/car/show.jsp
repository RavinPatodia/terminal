<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400" > <!--查看 start-->
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
						aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
						<span class="glyphicon glyphicon-plus"></span> 查看用户信息
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
						<form class="form-horizontal" role="form" id="showForm">
							<div class="portlet box blue" style="border:0">
									<div class="portlet-body">
										<div class="row">
											<div class="col-md-2 col-sm-3 col-xs-3">
												<ul class="nav nav-tabs tabs-left">
													<li class="active">
														<a href="#tab_6_1" data-toggle="tab">用户信息</a>
													</li>
													<li>
														<a href="#tab_6_2" data-toggle="tab">联系方式</a>
													</li>
													<li>
														<a href="#tab_6_3" data-toggle="tab">账户信息</a>
													</li>
													<li>
														<a href="#tab_6_4" data-toggle="tab">拥有车辆</a>
													</li>
												</ul>
											</div>
											<div class="col-md-10 col-sm-9 col-xs-9">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_6_1">
														<div class="form-group">
															<label class="col-md-4 control-label">用户名</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.uacc"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">姓名</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.name"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">身份证号</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.idCard"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">出生日期</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.birthday"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">状态</label>
															<div class="col-md-6">
																<p class="form-control-static" fill="obj.state" data-type="int" data-min="0" data-max="3" data-num0="待审批" data-num1="审批通过" data-num2="审批未通过" data-num3="暂停"></p>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">所属用户组</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.ugroupName"></label>
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-md-4 control-label">是否拥有专属车位</label>
															<div class="col-md-6">
																	<p class="form-control-static" fill="obj.hasParkingSpaces" data-type="boolean" data-true="是" data-false="否" id="hasParkingSpaces"></p>
															</div>
														</div>
													</div>
													<div class="tab-pane fade" id="tab_6_2">
														<div class="form-group">
															<label class="col-md-4 control-label">联系地址</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.addr"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">单位名称</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.company"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">手机号</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.tel"></label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">固定电话</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.mobilePhone">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">QQ</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.qq">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">邮编</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.zipCode">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">传真</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.fax">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">Email</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.email">
																</label>
															</div>
														</div>
													</div>
													<div class="tab-pane fade" id="tab_6_3">
														<div class="form-group">
															<label class="col-md-4 control-label">余额</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.balance">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">办理时间</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.procTime">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">创建时间</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.createTime">
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">会员失效日期</label>
															<div class="col-md-6">
																<label class="form-control-static show" fill="obj.expTime">
																</label>
															</div>
														</div>
													</div>
													<div class="tab-pane fade" id="tab_6_4">
														<table class="table table-bordered table-hover" id="carInfoTable">
															<thead>
																<tr>
																	<th>车牌号码</th>
																	<th>车牌类型</th>
																	<th>车辆颜色</th>
																	<th>车辆类型</th>
																	<th>车辆型号</th>
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
var carInfo = function(jsonData){
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
	           	        "mDataProp" : "licensePlate",
	           	        "sWidth": "200px"
	           	    }, {
	           	        "mDataProp" : "type",
	           	        "sWidth": "200px"
	           	    },{
	           	        "mDataProp" : "carColor",
	           	        "sWidth": "200px"
	           	    },
	           	 	{
	           	        "mDataProp" : "carType",
	           	        "sWidth": "200px"
	           	    },
	           	 	{
	           	        "mDataProp" : "carModel",
	           	        "sWidth": "250px"
	           	    }
	        ],
			"columnDefs": [
					{  aTargets: [3],
						   "render": function ( data, type, row ) {
							   if(data=="1"){
								   return "大车";
							   }else if(data=="0"){
								   return "小车";
							   }else{
								   return "";
							   }
							}
					}
				]
	      
	};
	var $table = $('#carInfoTable');
	var oTable_car = $table.dataTable(dataTable_config);
}; 


</script>