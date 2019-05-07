<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<div id="modify" class="modal fade" tabindex="-1" data-width="400" data-backdrop="static" data-keyboard="false"> <!--修改 start-->
	<div class="modal-dialog" style="width:800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family:'Microsoft Yahei'">
					<span class="glyphicon glyphicon-pencil" ></span> 修改用户
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">  
					<div class="col-md-12">
						<form class="form-horizontal" role="form" id="editForm">
						<input type="hidden" class="form-control normal-input" name="id" fill="obj.id">
						<input type="hidden" class="form-control normal-input" name="upwd" fill="obj.upwd">
						<input type="hidden" class="form-control normal-input" name="ugroupPK" fill="obj.ugroupPK">
							<div class="portlet box blue" style="border:0">
								<div class="portlet-body">
									<div class="row">
										<div class="col-md-2 col-sm-3 col-xs-3">
											<ul class="nav nav-tabs tabs-left">
												<li class="active">
													<a href="#tab_edit_1" data-toggle="tab">用户信息</a>
												</li>
												<li>
													<a href="#tab_edit_2" data-toggle="tab">联系方式</a>
												</li>
												<li>
													<a href="#tab_edit_3" data-toggle="tab">拥有车辆</a>
												</li>
											</ul>
										</div>
										<div class="col-md-10 col-sm-9 col-xs-9">
											<div class="tab-content">
												<div class="tab-pane active" id="tab_edit_1">
													<div class="form-group">
														<label class="control-label col-md-3">用户名<span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" readonly="readonly" class="form-control" name="uacc" fill="obj.uacc"/>
														</div>
													</div>
					  								<div class="form-group">
														<label class="control-label col-md-3">姓名 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="name" fill="obj.name"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">身份证号 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="idCard" fill="obj.idCard"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">生日 <span class="required">* </span></label>
														<div class="col-md-6">
															<div class="input-group input-lage date date-picker" data-date-start-date="+0d" data-date-format="dd-mm-yyyy">
																<input class="form-control" type="text" readonly="readonly" name="birthday" fill="obj.birthday">
																<span class="input-group-btn">
																<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
																</span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">性别 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="checkbox" class="make-switch" data-on-text="男" data-off-text="女" data-on-color="danger" name="genderStr" fill="obj.gender" data-off-color="default" checked>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">所属用户组 <span class="required">* </span>
														</label>
														<div class="col-md-6">
															<input type="text" readonly="readonly" class="form-control" fill="obj.ugroupName"/>
														</div>
													</div>
												</div>
												<div class="tab-pane fade" id="tab_edit_2">
													<div class="form-group">
														<label class="control-label col-md-3">联系地址 <span class="required">* </span></label>
														<div class="col-md-6">
															<div id="address2">
															    <select class="prov" name="province" fill="obj.province"></select>
															    <select class="city" name="city" disabled="disabled" fill="obj.city"></select>
															    <select class="dist" name="dist" disabled="disabled" fill="obj.dist"></select>
															    <input type="text" class="form-control" name="addr"  fill="obj.addr"/>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">单位名称 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="company" fill="obj.company"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">手机号 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="mobilePhone" fill="obj.mobilePhone"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">固定电话 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="tel" fill="obj.tel"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">QQ <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="qq" fill="obj.qq"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">邮编 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="zipCode" fill="obj.zipCode"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">传真 <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="fax" fill="obj.fax"/>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">Email <span class="required">* </span></label>
														<div class="col-md-6">
															<input type="text" class="form-control" name="email" fill="obj.email"/>
														</div>
													</div>
												</div>
												<div class="tab-pane fade" id="tab_edit_3">
													<div class="form-group">
														<div class="col-md-4">
															<button type="button" class="btn btn-default car_add_btn">
																<span class="glyphicon glyphicon-plus"></span>添加车辆
															</button>
														</div>
												    </div>
												    <div id="addCarDiv" style="display: none;">
													    <div class="form-group">
															<label class="control-label col-md-2">车牌号码 <span class="required">* </span></label>
															<div class="col-md-6">
																<input type="hidden" class="form-control" id="editcarPK" name="carPK"/>
																<input type="text" class="form-control" id="editlicensePlate" name="licensePlate"/>
															</div>
															<span class="help-block" id="editcar_existmsg">(如：浙A88888)</span>
														</div>
														<div class="form-group">
															<label class="control-label col-md-2">车牌类型 <span class="required">* </span></label>
															<div class="col-md-6">
																<input class="form-control select2 car_type" id="editlicensePlateType" name="licensePlateType"/>
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-2">车辆颜色 <span class="required">* </span></label>
															<div class="col-md-6">
																<input class="form-control select2 car_color" id="editcarColor" name="carColor">
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-2">车辆型号 <span class="required">* </span></label>
															<div class="col-md-6">
																<input class="form-control select2 car_module" id="editcarModel" name="carModel">
															</div>
														</div>
														<div class="form-group">
															<label class="control-label col-md-2">车辆类型 <span class="required">*</span></label>
															<div class="col-md-6">
																<select class="bs-select form-control" data-selected="0" id="editcarType"  name="carType">
																	<option value="0">小车</option>
																	<option value="1">大车</option>
																</select>
															</div>
														</div>
														<div class="form-group">
															<div class="col-md-6 col-md-offset-4">
															    <button type="button" class="btn btn-default editCarCancle">取消</button>
																<button type="button" class="btn btn-default car_edit">添加</button>
															</div>
														</div>
													</div>
													<table class="table table-bordered table-hover" id="userCarEditTable">
														<thead>
															<tr>
																<th>车牌号码</th>
																<th>车牌类型</th>
																<th>车辆颜色</th>
																<th>车辆型号</th>
																<th>车辆类型</th>
																<td>操作</td>
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
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn">取消</button>
				<button type="button"  id="user_edit_save" class="btn green">保存</button>
			</div>
		</div>
	</div>
</div>
<script>
var edit=function(){
	var bindClick=function(dataSource,modal,url){
		$('.tb_modify').edit(dataSource,modal,url);
	}
	var bindSubmit = function(){
		$("#user_edit_save").on("click",function(){
			if($('.has-error').length<=0){
				editUser.init();
			}
		})
	}
	//绑定数据打开modal框
	var bindDataAndOpenModal=function(data,dialog){
		var obj = data;
		var formObj = obj.object;
		if(obj.success){
			$("#editForm",dialog).fill(obj.object);//表单填充插件
			$("#address2").citySelect({"prov":"浙江","city":"杭州","dist":"西湖区",nodata:"none"});
			$("#editForm").find(".u_ugroup > a > span").eq(0).text(formObj.ugroupName);
		 	userCarEdit(formObj.carModels); 
			dialog.modal('show');
		}
		else{
			alert(obj.msg);
		}
	}
	var initAddCar=function(){
		$('.car_add_btn').bind('click',function(){
			$(this).hide();
			$('#addCarDiv').toggle();
			
		});
		$('.editCarCancle').bind('click',function(){
			$('#addCarDiv').toggle();
			$('.car_add_btn').toggle();
		})
	}
	var userCarEdit = function(jsonData){
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
		           	        'orderable': false,
		           	        "sWidth": "200px"
		           	    },{
		           	        "mDataProp" : "carColor",
		           	        'orderable': false,
		           	        "sWidth": "200px"
		           	    },
		           	 	{
		           	        "mDataProp" : "carModel",
		           	        'orderable': false,
		           	        "sWidth": "200px"
		           	    },
		           		{
		           	        "mDataProp" : "carType",
		           	        'orderable': false,
		           	        "sWidth": "200px"
		           	    },
		           	    {
		           	        "mDataProp" : "",
		           	        'orderable': false,
		           	        "sWidth": "200px"
		           	    }
		           	    ],
	  		"columnDefs": [
	  						{  aTargets: [4],
	  							   "render": function ( data, type, row ) {
	  								   if(data=="1"){
	  									   return "大车";
	  								   }else if(data=="0"){
	  									   return "小车";
	  								   }else{
	  									   return "";
	  								   }
	  								}
	  						},
	  						{  aTargets: [5],
	  							   "render": function ( data, type, row ) {
	  								   return '<a class="btn btn-primary btn-sm del_add_record">删除</a>';
	  								}
	  						}
	  					]
		};
		var $table = $('#userCarEditTable');
		var oTable_car = $table.dataTable(dataTable_config);
	}; 

	return {
		init:function(dataSource,modal,url){
			bindClick(dataSource,modal,url);
			bindSubmit();
			initAddCar();
		},
		 callback:function(data,dialog){
			bindDataAndOpenModal(data,dialog);
		} 
	};
	
}();
var edit_car_init = function(){
	var init = function(){
		$('input[name="licensePlate"]').keyup(function(){
			var licensePlate = $(this).val();
			if(licensePlate.length < 7)
				return;
			else{
				var re=/[ ]/g;
				if(re.test(licensePlate)){
					var n=licensePlate.match(re).length;
					if(n>0){
						$("#editcar_existmsg").html("请注意,车牌号码不包含空格");
					}
					$('.car_edit').attr('disabled',true);
					return;
				}
				$.ajax({
					url:pageUrl.user_user_getCar,
					type:"POST",
					data:{licensePlate:licensePlate},
					success:function(data){
						var obj = data;
						var formObj = obj.object;
						if(obj.success){
							$('#editcarPK').val(formObj.carPK);
							$('#editlicensePlate').val(formObj.licensePlate);
							$('#editlicensePlateType').val(formObj.licensePlateType);
							$('#editcarColor').val(formObj.carColor);
							$('#editcarModel').val(formObj.carModel);
							$('#editcarType').val(formObj.carType);
							
							$("#editForm").find(".car_type > a > span").eq(0).text(formObj.licensePlateType);
							$("#editForm").find(".car_color > a > span").eq(0).text(formObj.carColor);
							$("#editForm").find(".car_module > a > span").eq(0).text(formObj.carModel);
							if(obj.msg!=null&&obj.msg!=""){
								$("#editcar_existmsg").html(obj.msg);
								$('.car_edit').attr('disabled',true);
							}else{
								$("#editcar_existmsg").html("");
								$('.car_edit').attr('disabled',false);
							}
						}else{								
							$("#editcar_existmsg").html("未查询到该车牌号对应的车辆");
							$('.car_edit').attr('disabled',false);
							editClear();
						}
					},
					error:function(){
						alert('数据获取错误！');
					}
				});
			}
		});
		
		$('.car_edit').live('click',function(){
			if ($('#editForm').valid() == false) {
                 return false;
            }
			var $input_list = $('#tab_edit_3').find('input[type="text"]');
			var value_list  = [];
			value_list.push($('#tab_edit_3').find('input[type="text"]').val());
			value_list.push($('#editlicensePlateType').val());
			value_list.push($('#editcarColor').val());
			value_list.push($('#editcarModel').val());
			var carTypeText=$("#tab_edit_3").find(".selectpicker > span").eq(0).text();
			value_list.push(carTypeText);
			$('#tab_edit_3').find('input[type="text"]').val('');
			$("#editcar_existmsg").html("(如：浙A88888)");
			 editClear(); 
			$table = $('#userCarEditTable');
			$table.append('<tr data-pid='+$('#editcarPK').val()+'><td name="licensePlate">'+value_list[0]+'</td><td name="licensePlateType">'
						 +value_list[1]+'</td><td name="carColor">'+value_list[2]+'</td><td name="carModel">'+value_list[3]+
						 '</td><td name="carType">'+value_list[4]+'</td><td><a class="btn btn-primary btn-sm del_add_record">删除</a></td></tr>');

		});
		
		$('#userCarEditTable').on('click','.del_add_record',function(){
			$(this).parent().parent().remove();
		});

	}
	return{ init:function(){
		init();
	}
}
}();

var editUser = function(){
	var item_list = [];
	var getCarData = function(){
		$('#userCarEditTable').find('tbody').find('tr').each(function(i,v){
			var d = [];
			d[0] = $(this).data('pid');
		 	$(this).find('td').each(function(i,v){
		 		d.push($(this).text());
		 	});
		 	var type = '0';
		 	if(d[5] =='大车'){
		 		type ='1';
		 	}
		 	var td = {carColor:d[3],carModel:d[4],id:0,licensePlate:d[1],licensePlateType:d[2],carType:type};
		 	item_list.push(td);
		});
	}
    //将从form中通过$('#form').serialize()获取的值转成json
	var formToJson = function (data) {
	       data=data.replace(/&/g,"\",\"");
	       data=data.replace(/=/g,"\":\"");
	       data="{\""+data+"\"}";
	       return data;
	}
	
	var init_edit = function(){
		getCarData();
		var userdata = $('#editForm').serialize();
		userdata = decodeURIComponent(userdata,true);//防止中文乱码
		var json=formToJson(userdata);//转化为json
	    $.ajax({
	        type: "POST",
	        url: pageUrl.user_user_edit,
	        data:{
	        	user:json,
	        	"orderJson":"{\"car\":"+JSON.stringify(item_list)+"}"
	        },
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	            if(data==null||data==""){
			        return;
			    }
	            $("#modify").modal('hide');
			    var obj = data;
			    if(obj.success){
	        		toastr.success(obj.msg,"客户管理模块");
	        		reload();
	        	}
	        	else{
	        		toastr.error(obj.msg, "客户管理模块");
	        	}
	        }
	      });
	}
	return{
		init:function(){
			init_edit();
		}
	}
}();
var editClear=function(){
	//清空表单
		 $("#tab_edit_3").find("select").each(function(i,obj){
			var select = $(this).data("selected");
			$(this).val(select).trigger("change");
		}); 
		 $("#tab_edit_3").find(".select2").each(function(i,obj){
			var select = $(this).data("selected");
			$(this).val("").trigger("change");
		}); 
	 }
</script>