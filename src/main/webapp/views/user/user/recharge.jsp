<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="recharge" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!-- start-->
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
					<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
						<span class="glyphicon glyphicon-pencil" ></span>
							充值
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form class="form-horizontal" role="form" id="rechargeForm">
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">客户姓名</label>
										<div class="col-md-4">
											<label class="form-control-static show" fill="obj.name">
											</label>
										</div>
									</div>
									<div class="form-group balanceDiv">
										<label class="col-md-3 control-label">余额</label>
										<div class="col-md-4">
											<label class="form-control-static show" id="recharge_balance" name="balance" fill="obj.balance">
											</label>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">充值类型 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">	
											<input type="hidden" class="form-control" id="recharge_type" name="recharge_type" fill="obj.recharge_type">
											<select class="bs-select form-control"  id="rechargeType" data-selected="0">
												<option value="0">余额充值</option>
												<option value="1">长期会员办理</option>
											</select>
										</div>
									</div>
									<!-- 长期客户办理开始 -->
									<div class="form-group groupName" style="display: none">
										<label class="col-md-3 control-label">客户组名称<span class="required">* </span></label>
										<div class="col-md-4">
											<input class="form-control select2 ugroup_long_member_name_id" id="recharge_uGroupId" name="ugroupPK">
										</div>
									</div>
									<div class="long_term_userdiv" style="display:none;">
										<div class="form-group">
											<label class="control-label col-md-3">选择办理套餐 <span class="required">* </span>
											</label>
											<div class="col-md-9">
												<table class="table table-striped table-hover" id="tb_long_termrule">
													<thead style="display:none;">
													<tr><th></th><th ></th><th></th><th></th><th></th></tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div class="form-group beginTimeDiv" style="display:none;">
										<label class="col-md-3 control-label">开始时间</label>
										<div class="col-md-4">
											<label class="form-control-static show" id="recharge_beginTime" name="beginTime" fill="obj.beginTime">
											</label>
										</div>
									</div>
									<div class="form-group expTimeDiv" style="display:none;">
										<label class="col-md-3 control-label">截止时间</label>
										<div class="col-md-4">
											<label class="form-control-static show" id="recharge_expTime" name="expTime" fill="obj.expTime">
											</label>
										</div>
									</div>
									<!-- 长期客户办理结束 -->
									<div class="form-group">
										<label class="control-label col-md-3">充值方式 </label>
										<div class="col-md-4">
											<div class="checkbox-list">
												<label><input id="pay_type0" type="radio" checked="checked" name="payType"  class="icheck ic_default" value="0" />现金充值</label> 
												<label><input id="pay_type1" type="radio" name="payType"  class="icheck" value="1" />网上充值</label>
											</div>
											<div id="form_payment_error">
											</div>
										</div>
									</div>
									<div class="form-group">
											<label class="control-label col-md-3">充值金额 <span class="required">* </span>
											</label>
											<div class="col-md-2">
												<input  type="text" readonly="readonly" value="0.00" id="rechargeFee" name="rechargeFee" class="form-control">
											</div>
											<label class="control-label"><span>元</span>
											</label>
									</div>
						
								<input type="hidden" class="form-control" id="userPK" name="id">
								<input type="hidden" class="form-control" id="anchorRentid" name="anchorRentPK">
								<input type="hidden" class="form-control" id="recharge_expTime_input" fill="obj.expTime" >
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"  id="btn_pay" class="btn green">支付</button>
				</div>
			</div>
		</div>
	</div><!--修改 end-->
<script>
var recharge=function(){
	var oTable =null;
	var bindClick=function(dataSource,modal,url){
		if(!dataSource)//如果绑定的表格不对的话，直接返回（如果插件无效，且没有错误提示的话那就是这个有问题了）
			return;
		$(".tb_recharge").bind('click',function(){//按钮绑定点击事件
			var dataObj = dataSource.children("tbody").children(".active");//找到是否有被选中的数据项
			if(dataObj.length !=1 ){//若没有选中或者选中的行数大于的1话
				bootbox.alert("您选择了"+dataObj.length+"行数据项，请选择 1 行数据再进行操作！");   
				return;
			}
			var id = $(".checkboxes:checked").val(); //获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
			$.ajax({
				url : pageUrl.user_user_editAndDbl+ id+"?t="+Math.random(),
				success : function(data) {
					if(data.object.ugroupType==0 ){//若没有选中或者选中的行数大于的1话
						bootbox.alert("该客户为临时客户，不能进行充值操作");   
						return;
					}
					recharge.callback(data, $('#recharge'));
				},
				error : function(error) {
					alert(error.status + "," + error.readyState);
				}
			});
		});
	}
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#rechargeForm",dialog).fill(obj.object);//表单填充插件
			if(formObj.beginTime==null){
				$('#recharge_beginTime').text(moment().format('YYYY-MM-DD'));
			}
			if(formObj.expTime==null){
				$('#recharge_expTime').text(moment().format('YYYY-MM-DD'));
				$('#recharge_expTime_input').val(moment().format('YYYY-MM-DD'))
			}
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
		}
	}
	
	var tb_long_term_rule = function(jsonData){
		var dataTable_config = {//jquery_datatable插件的默认参数配置
		    //下面设置数据栏目是否可排序
		    destroy: true,
		    "data": jsonData,
	        "bProcessing": true,
	        "bFilter": false,
	        "bPaginate": false,
	        "bAutoWidth":false,//自动宽度
	        "bLengthChange":false, //关闭每页显示多少条数据
        	"ordering": false,
		    "aoColumns" : [ 
		           	  	{
		           	        "mDataProp" :"id",
		           	        "sClass":"center",
		           	    }, {
		           	        "mDataProp" : "name",
		           	        "sWidth": "100px"
		           	    },{
		           	        "mDataProp" : "month",
		           	        "sWidth": "100px"
		           	    },{
		           	        "mDataProp" : "fee",
		           	        "sWidth": "100px"
		           	    },{
		           	        "mDataProp" : "addFee",
		           	        "sWidth": "100px"
		           	    }
		           	    ],
		    "columnDefs": [
				{
					 sDefaultContent: '',
					 aTargets: [ '_all' ]
				},
				{  aTargets: [0], "render": function ( data, type, full, meta ) 
					{
				   		return '<input type="radio" name="id_radio"  class="icheck" value="'+data+'"/>';
					}
				},	
				{  aTargets: [2], "render": function ( data, type, full, meta ) 
					{
						return data+"个月";			           
				    }
				},
				{  aTargets: [3], "render": function ( data, type, full, meta ) 
					{
						return "￥"+data;			           
				    }
				},
				{  aTargets: [4], "render": function ( data, type, full, meta ) 
					{
						return "附加￥"+data;			           
				    }
				},
			],
			"createdRow": function ( row, data, index ) {
	            	$(row).addClass('success');
     		}
		};
		//不继承Global里面参数，直接初始化
		var $table = $('#tb_long_termrule');
		oTable = $table.dataTable(dataTable_config);
		Metronic.init(); 
	}; 
	
	var bindSubmit = function(){
		$("#btn_pay").on("click",function(){
			$("#userPK").val($(".checkboxes:checked").val());//获取编辑的那种中选中的checkbox，获取checkbox的值（这里传入的是 id)
			if($('#rechargeType').val()==0){
				$("#recharge_type").val("客户账户余额充值");
				$.ajax({
			       type: "POST",
			       url: pageUrl.user_user_recharge,
			       data:$('#rechargeForm').serialize(),
			       async: false,
			       success: function(data) {
				       	var obj = data;
				       	if(obj.success){
				       		$("#recharge").modal('hide');
				       		toastr.success(obj.msg);
				       	}
				       	else{
				       		toastr.error(obj.msg);
				       	}
			       }
			   }); 
			}else if($('#rechargeType').val()==1){
				$("#anchorRentid").val($("#tb_long_termrule input[name='id_radio']:checked").val());//把选择的套餐id放入
				$("#recharge_type").val("客户长期客户办理与续费");
				$.ajax({
			       type: "POST",
			       url: pageUrl.user_user_recharge,
			       data:$('#rechargeForm').serialize(),
			       async: false,
			       success: function(data) {
				       	var obj = data;
				       	if(obj.success){
				       		$("#recharge").modal('hide');
				       		toastr.success(obj.msg);
				       		reload();//重新加载列表
				       	}
				       	else{
				       		toastr.error(obj.msg);
				       	}
			       },
			       error: function(request) {
			           alert("Connection error");
			       }
 				}); 
			}
			 
		})
	}

	var bindbilling_model=function(){
		$('#rechargeType').on('change',function(ele){
			var value= $(this).val();
			if(value=='0'){
				$("#rechargeForm .balance_rechargeDiv").show();
				$("#rechargeForm .long_term_userdiv").hide();
				$(".beginTimeDiv").hide();
				$(".expTimeDiv").hide();
				$('.groupName').hide();
			}else if(value=='1'){
				$("#rechargeForm .balance_rechargeDiv").hide();
				$(".beginTimeDiv").show();
				$(".expTimeDiv").show();
				$('.groupName').show();
			}
		})
	}
	
	var uGroupChange=function(){
		$('#recharge_uGroupId').on('change',function(){
			var uGroupId=$('#recharge_uGroupId').val();
			$.ajax({
				url:pageUrl.user_user_getAnchorRents+uGroupId,
				success:function(data){
					var obj = data;
					var formObj = obj.object;
					var recs = formObj.anchorRentModels;
					if(obj.success){
						$("#rechargeForm .long_term_userdiv").show();
						tb_long_term_rule(recs);
						//隐藏表格左下方的文字信息 
		            	$('#tb_long_termrule_info').hide();

					}
					else{
						alert(obj.msg);
					}
				}
			});
		});
	}
	var radioChecked=function(){
		$("#tb_long_termrule input[name='id_radio']").live('ifChecked', function(event){
			var fee= $(this).parent().parent().parent().children('td').eq(3).html();
			var addfee= $(this).parent().parent().parent().children('td').eq(4).html();
			fee=fee.substring(1,fee.length);
			addfee=addfee.substring(3,addfee.length);
			var sum=Number(fee)+Number(addfee);
			$("#rechargeFee").val(sum);
			//截止时间=原先截止时间+套餐月份
			var addMonth= $(this).parent().parent().parent().children('td').eq(2).html();
			addMonth=addMonth.substring(0,addMonth.length-2);
			var expireTime=$('#recharge_expTime_input').val();
			$('#recharge_expTime').text(moment(expireTime, "YYYY-MM-DD").add('months',addMonth).format('YYYY-MM-DD'));
		});
		
	}
	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindSubmit();
			bindbilling_model();
			uGroupChange();
			radioChecked();
		},
		callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		}
	};
	
}();

</script>