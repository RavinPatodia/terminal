<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="add" class="modal fade" tabindex="-1"  data-width="400"data-backdrop="static" data-keyboard="false">
	<!--添加 start-->
	<div class="modal-dialog" style="width:800px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" 
					aria-hidden="true"></button>
				<h4 class="modal-title" style="font-family: 'Microsoft Yahei'">
					<span class="glyphicon glyphicon-plus"></span> 会员办理
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1" style="border:0">
						<!-- <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> Form Wizard - <span class="step-title">
								Step 1 of 2 </span>
							</div>
							<div class="tools hidden-xs">
							</div>
						</div> -->
						<div class="portlet-body form">
							<form  class="form-horizontal" role="form" id="submit_form">
								<div class="form-wizard">
									<div class="form-body">
										<ul class="nav nav-pills nav-justified steps">
											<!-- <li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> 设置用户名 </span>
												</a>
											</li> -->
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> 填写基本信息 </span>
												</a>
											</li>
											<li>
												<a href="#tab3" data-toggle="tab" class="step active">
												<span class="number">
												2 </span>
												<span class="desc">
												<i class="fa fa-check"></i> 绑定车辆信息 </span>
												</a>
											</li>
										</ul>
										<div id="bar" class="progress progress-striped" role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
										<div class="tab-content">
											<div class="alert alert-danger display-none">
												<button class="close" data-dismiss="alert"></button>
												信息填写不完整
											</div>
											<div class="alert alert-success display-none">
												<button class="close" data-dismiss="alert"></button>
											</div>
											<div class="tab-pane active" id="tab1">
												<h3 class="block">请输入客户信息</h3>
												<div class="form-group">
													<label class="control-label col-md-4">用户名 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="uacc"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">密码 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="password" class="form-control" name="upwd" id="submit_form_password"/>
													</div>
												</div> 
												<div class="form-group">
													<label class="control-label col-md-4">确认密码 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="password" class="form-control" name="rpassword"/>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab2">
												<div class="form-group">
													<label class="control-label col-md-4">姓名 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="name"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">身份证号 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="idCard"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">生日 <span class="required">													* </span>
													</label>
													<div class="col-md-4">
														<div class="input-group input-lage date date-picker" data-date-start-date="+0d" data-date-format="dd-mm-yyyy">
															<input class="form-control" type="text" readonly="readonly" name="birthday" >
															<span class="input-group-btn">
															<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
															</span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">性别 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<label><input id="gender0" type="radio" checked="checked " name="gender" class="icheck ic_default" value="0" />男</label> 
														<label><input id="gender1" type="radio" name="gender" class="icheck" value="1" />女</label>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">联系地址 <span class="required">
													* </span>
													</label>
													<div class="col-md-4 addr">
														<div id="address" editable="false">
														    <select class="prov" name="province"></select>
														    <select class="city" name="city" disabled="disabled"></select>
														    <select class="dist" name="dist" disabled="disabled"></select>
														    <input type="text" class="form-control" name="addr"/>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">单位名称 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="company"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">手机号 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="mobilePhone"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">固定电话 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="tel"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">QQ <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="qq"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">邮编 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="zipCode"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">传真 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="fax"/>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-md-4">Email <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input type="text" class="form-control" name="email"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">所属客户组 <span class="required">
													* </span>
													</label>
													<div class="col-md-4">
														<input class="form-control select2 ugroup_member_name_id" name="ugroupPK" id="ugroupPK"/>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="tab3">
												<div class="form-group">
													<label class="control-label col-md-4">车牌号码 <span class="required">* </span></label>
													<div class="col-md-4">
														<input type="hidden" class="form-control" id="carPK" name="carPK" fill="obj.carPK" />
														<input type="text" class="form-control" name="licensePlate" fill="obj.licensePlate"/>
													</div>
													<span class="help-block" id="car_existmsg">请输入车牌号码 </span>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">车牌类型 <span class="required">* </span></label>
													<div class="col-md-4">
														<input class="form-control select2 car_type" id="licensePlateType" name="licensePlateType" fill="obj.licensePlateType"/>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">车辆颜色 <span class="required">* </span></label>
													<div class="col-md-4">
														<input class="form-control select2 car_color" id="carColor" name="carColor" fill="obj.carColor">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">车辆型号 <span class="required">* </span></label>
													<div class="col-md-4">
														<input class="form-control select2 car_module" id="carModel" name="carModel" fill="obj.carModel">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-4">车辆类型 <span class="required">*</span></label>
													<div class="col-md-4">
														<select class="bs-select form-control" data-selected="0" id="carType"  name="carType" fill="obj.carType">
															<option value="0">小车</option>
															<option value="1">大车</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<div class="col-md-offset-4 col-md-9">
														<button type="button" class="btn btn-default car_add">
															<span class="glyphicon glyphicon-plus"></span>添加
														</button>
													</div>
												</div>
												<div class="form-group">
												<div class="col-md-8 col-md-offset-2">
													<table class="table table-bordered table-hover" id="add_table">
														<tr class="a_head">
															<td>车牌号码</td>
															<td>车牌类型</td>
															<td>车辆颜色</td>
															<td>车辆型号</td>
															<td>车辆类型</td>
															<td>操作</td>
														</tr>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
											</div>
											
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-4 col-md-9">
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> 上一步 </a>
												<a href="javascript:;" class="btn blue button-next">
												下一步 <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<a href="javascript:;" class="btn green button-submit">
												提交 <i class="m-icon-swapright m-icon-white"></i>
												</a>
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
	</div>
</div>
<!--添加 end-->
<script type="text/javascript">
	var add_car_init = function(){
		var init = function(){
			$('input[name="licensePlate"]').keyup(function(){
				var licensePlate = $(this).val();
				var re=/[ ]/g;
				if(re.test(licensePlate)){
					var n=licensePlate.match(re).length;
					if(n>0){
						$("#car_existmsg").html("请注意,车牌号码不包含空格");
						$('#add_save').attr('disabled',true);
					}
					return;
				}
				$.ajax({
					url:"${ifExistUrl}",
					type:"POST",
					data:{licensePlate:licensePlate},
					success:function(data){
						var obj = data;
						if(obj.success){
							$("#car_existmsg").html("该车牌号已经存在");
							$('#add_save').attr('disabled',true);
						}else{
							$("#car_existmsg").html("");
							$('#add_save').attr('disabled',false);
						}
					},
					error:function(){
					}
				});
			});
			
			$('.car_add').bind('click',function(){
				if ($('#submit_form').valid() == false) {
                     return false;
                }
				var $input_list = $('#tab3').find('input[type="text"]');
				var value_list  = [];
				value_list.push($('#tab3').find('input[type="text"]').val());
				value_list.push($('#licensePlateType').val());
				value_list.push($('#carColor').val());
				value_list.push($('#carModel').val());
				var carTypeText=$("#tab3").find(".selectpicker > span").eq(0).text();
				value_list.push(carTypeText);
				$('#tab3').find('input[type="text"]').val('');
				$("#car_existmsg").html("请输入车辆信息");
				addClear();
				$table = $('#add_table');
				$table.append('<tr data-pid='+$('#carPK').val()+'><td name="licensePlate">'+value_list[0]+'</td><td name="licensePlateType">'
							 +value_list[1]+'</td><td name="carColor">'+value_list[2]+'</td><td name="carModel">'+value_list[3]+
							 '</td><td name="carType">'+value_list[4]+'</td><td><a class="btn btn-primary btn-sm del_add_record">删除</a></td></tr>');
				
				if($("#add_table tr").length>1){
					$("#car_msg").html("");
					$('#form_wizard_1 .button-submit').attr('disabled',false);
				}
	         	
			});
			
			$('#add_table').on('click','.del_add_record',function(){
				$(this).parent().parent().remove();
			});
		}

		return{ init:function(){
			init();
		}
	}
}();

var addUser = function(){
	var item_list = [];
	var getCarData = function(){
		$('#add_table').find('tr:not(.a_head)').each(function(i,v){
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

	var init_add = function(){
		getCarData();
		var userdata = $('#submit_form').serialize();
		userdata = decodeURIComponent(userdata,true);//防止中文乱码
		var json=formToJson(userdata);//转化为json
        $.ajax({
            type: "POST",
            url: pageUrl.user_user_add,
            data:{
            	user:json,
            	"orderJson":"{\"car\":"+JSON.stringify(item_list)+"}",
            },
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                if(data==null||data==""){
			        return;
			    }
			    $("#add").modal('hide');
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
			init_add();
		}
	}
}();

var add=function(){
	var bindOpenClickEvent = function(){
		$('.tb_add').bind("click",function(){
			$("#add").modal('show');
			$("#address").citySelect({"prov":"浙江","city":"杭州","dist":"西湖区",nodata:"none"});
		});
	}
	return {
        init:function(){
        	bindOpenClickEvent();
        }
    };
}();
var addClear=function(){
	//清空表单
	 $("#tab3").find("select").each(function(i,obj){
		var select = $(this).data("selected");
		$(this).val(select).trigger("change");
	}); 
	 $("#tab3").find(".select2").each(function(i,obj){
		var select = $(this).data("selected");
		$(this).val("").trigger("change");
	}); 
}
</script>

