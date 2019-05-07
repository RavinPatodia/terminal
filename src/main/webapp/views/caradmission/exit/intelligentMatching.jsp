<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="bindRole" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!-- start-->
		<div class="modal-dialog" style="width:800px" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							智能匹配
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered table-hover" id="favourable">
							<thead>
								<tr>
									<th class="table-checkbox"><input type="checkbox"
										class="group-checkable" data-set="#favourable .checkboxes" />
									</th>
									<th>车牌号</th>
									<th>车牌类型</th>
									<th>车辆颜色</th>
									<th>车辆型号</th>							
								
								</tr>
							</thead>
							<tbody>
							</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal"  class="btn">取消</button>
					<button type="button"  id="submit" class="btn green">确定</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
	
<script>
var intelligentMatching=function(){
	var oTable =null;
	
	var dataTable_config = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    "bServerSide": true,
	        "bProcessing": true,
	        "bFilter": false,
	        "bPaginate": false,
	        "bAutoWidth":false,//自动宽度
	        "bLengthChange":false, //关闭每页显示多少条数据
	        "language": {
	            "search": "搜索: ",
	            "lengthMenu": "  _MENU_ 条数据",
	            "emptyTable": "表格中没有数据~",
	            "info": "显示 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
	            "infoEmpty": "没有数据~",
	            "infoFiltered": "(在 _MAX_ 条数据中查询)",
	            "search": "查询:",
	            "zeroRecords": "没有找到对应的数据",
	        },
			    "sAjaxSource": pageUrl.caradmission_exit_matching,
			    "fnServerData":retrieveData,
			    "aoColumns" : [ 
			    {
			    	"mDataProp" :"carPK",
			    	"sClass":"center",
			    },
			    {
			    	 "mDataProp" : "licensePlate",
			        'orderable': false,
			        "sWidth": "100px"
			    }, {
			    	 "mDataProp" : "licensePlateType",
			        'orderable': false,
			        "sWidth": "100px"
			    },{
			    	 "mDataProp" : "carColor",
			        'orderable': false,
			        "sWidth": "100px"
			    },{
			    	 "mDataProp" : "carModel",
			        'orderable': false,
			        "sWidth": "100px"
			    }
		    ],
		    "columnDefs": [
				{  // 设置默认值
				    'orderable': false,
				    'targets': [0]
				}, {
				    "searchable": false,
				    "targets": [0]
				},
				{
					 sDefaultContent: '',
					 aTargets: [ '_all' ]
				},
				{  aTargets: [0], "render": function ( data, type, full, meta ) 
					{
				   		return '<input type="checkbox" class="checkboxes" value="'+data+'"/>';
					}
				}
			],
		    "order": [
			            [2, "asc"]
			        ] //升序排列
		};
	
		function retrieveData( sSource, aoData, fnCallback ) {     
		    //查询条件称加入参数数组      
		    var licensePlate =$("#get_licensePlate").val();
		    var licensePlateType =$("#get_licensePlateType").val();
		    var carColor=$("#get_carColor").val();
		    var carModel =$("#get_carModel").val();
		    $.ajax( {     
		        type: "POST",
		        url: sSource,   
		        dataType:"json",  
		        data: "jsonParam="+JSON.stringify(aoData)+"&licensePlate="+licensePlate+"&licensePlateType="+licensePlateType
		        +"&carColor="+carColor+"&carModel="+carModel,  
		        success: function(data) {   
		            fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式  
		            Metronic.init();
		        },
		        error : function(data){
		        	alert(data);
		        }
		    });    
		}
	var dataTableInit = function(){
		//判断表单是否初始化  是：刷新|| 否：初始化
		if(oTable!=null){
			oTable.fnDraw();
			return;
		}
		//不继承Global里面参数，直接初始化
		var $table = $('#favourable');
		oTable = $table.dataTable(dataTable_config);
		$.table_init($table);//使用Global.js里面初始化checkbox
	}
	
	var bindOpenClickEvent = function(){
		$('#btn_intellmatching').bind("click",function(){
			dataTableInit();
			$("#bindRole").modal('show');
		});
	}

	var bindSubmit = function(){
		$("#submit").on("click",function(){
			var carId = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
			$("#bindRole").modal('hide');
			$.ajax({
				url:pageUrl.caradmission_exit_getData+"/"+carId,
			});
		})
	}
	
	return {
		init:function(){
			bindOpenClickEvent();
			bindSubmit();
		}
	};
	
}();

</script>