<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="show" class="modal fade" tabindex="-1" data-width="400"> <!--查看 start-->
		<div class="modal-dialog" style="width:750px">
			<div class="modal-content">
				 <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-list-alt" ></span>
							 查看优惠规则组
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="showForm">
								<div class="form-body">
									<div class="portlet-body">
										<div class="row">
											<div class="col-md-3 col-sm-3 col-xs-3">
												<ul class="nav nav-tabs tabs-left">
													<li class="active">
														<a href="#tab_6_1" data-toggle="tab">优惠规则组</a>
													</li>
													<li>
														<a href="#tab_6_2" data-toggle="tab">组内优惠规则</a>
													</li>
													
												</ul>
											</div>
											<div class="col-md-9 col-sm-9 col-xs-9">
												<div class="tab-content">
													<div class="tab-pane active" id="tab_6_1">
														<div class="form-group">
															<label class="col-md-4 control-label">优惠规则组编号</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.groupId">
																</p></div>
														</div>
					
														<div class="form-group">
															<label class="col-md-4 control-label">优惠规则组名称</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.name">
																</p></div>
														</div>
														<div class="form-group">
															<label class="col-md-4 control-label">优惠规则组描述</label>
															<div class="col-md-6"><p class="form-control-static" fill="obj.describion">
																</p></div>
														</div>
													</div>
													<div class="tab-pane fade" id="tab_6_2">
														<table class="table table-bordered table-hover" id="recs">
															<thead>
																<tr>
																	<th>
																		 优惠规则编号
																	</th>
																	<th>
																		 优惠规则名称
																	</th>
																	<th>
																		 类型
																	</th>
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
var dctRuleGriupRec = function(jsonData){
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
	                       "mDataProp" : "dctId",
	                       "sWidth": "100px"
	                   }, {
	                       "mDataProp" : "dctName",
	                       "sWidth": "400px"
	                   }, {
	                       "mDataProp" : "type",
	                       "sWidth": "100px"
	                   },{
	                       "mDataProp" : "enableFlag",
	                       "sWidth": "100px"
	                   } 
	           	    ],
     	 "columnDefs": [{  // 设置默认值
           'orderable': false,
           'targets': [0]
       },
       {
   		 sDefaultContent: '',
   		 aTargets: [ '_all' ]
   		},
       {   aTargets: [2],
       	"render": function ( data, type, full, meta ) {
       		 	var result;
   	            if(data==0){
   	            	result = "会员生日";
   	            }
   	            else if(data==1){
   	            	result = "节假日";
   	            }
   	            else if(data==2){
   	            	result = "双休日";
   	            }
   	            else if(data==3){
   	            	result = "规定时间段";
   	            }
   	            else if(data==4){
   	            	result = "规定日期段";
   	            }
   	            else if(data==5){
   	            	result = "续办折扣";
   	            }
   	            else{
   	            	result = "未选择";
   	            }
   	            return result;
   	     }
       },
       {   aTargets: [3],
       	"render": function ( data, type, full, meta ) {
       		return formatEnableFlag(data);
   	     }
       }],
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