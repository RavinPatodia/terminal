﻿<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html>

<%@ include file="/views/include/taglib.jsp"%>

<html lang="utf-8">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>

<jsp:include page="/views/include/globalCss.jsp" />
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="<%=request.getContextPath()%>/assets/global/plugins/jcrop/css/jquery.Jcrop.min.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/assets/admin/pages/css/image-crop.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery-multi-select/css/multi-select.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/icheck/skins/all.css"/>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.css"></link>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/master/dist/css/bootstrapValidator.min.css"/><!-- 表单验证的样式 -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/global/plugins/jstree/dist/themes/default/style.min.css"/><!--js树-->

 <!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="<%=request.getContextPath()%>/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=request.getContextPath()%>/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!--引入自己定义的一部分css-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/diy_style.css"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-quick-sidebar-over-content ">
	<jsp:include page="/views/layout/topBar.jsp" />
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<jsp:include page="/views/layout/menu.jsp" />
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<jsp:include page="/views/layout/pageheader.jsp" />
				<!-- START POTTLEX demo2------------>
				<div class="row">
					
					<div class="col-md-5">
						<div class="portlet grey-cascade  box" style="margin-top: 15px;">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i>停车场管理
								</div>
							</div>
							<div class="portlet-body">
								<div id="park_tree">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" id="save_btn" class="btn btn-sm btn-default ">创建子节点</button>
								<button type="button" id="edit_btn" class="btn btn-sm btn-default ">修改节点</button>
								<button type="button" id="delete_btn" class="btn btn-sm btn-default ">删除节点</button>
								<a href="javascript:void(0)" onclick="reload()"	class="btn btn-sm btn-default">卡口配置</a>
							</div>	
						</div>
					</div>
					<div class="col-md-7">
						<div class="portlet box grey-cascade"
							style="margin-top: 15px; border-top: 1px solid #b1bdbd">
							<div class="portlet-body"  style="position:relative;">
								<div class="btn-group" role="group" aria-label="..." >
									<button type="button" class="btn btn-sm btn-default tb_add">
									    <span class="glyphicon glyphicon-plus"></span> 新增
								    </button>
									<button type="button" class="btn btn-sm btn-default bayonet_modify ">
										<span class="glyphicon glyphicon-pencil"></span> 修改
									</button>
									<button type="button" class="btn btn-sm btn-default tb_del">
										<span class="glyphicon glyphicon-plus"></span> 删除
									</button>
								</div>
								<table class="table table-bordered table-hover" id="favourable">
									<thead>
										<tr>
											<th class="table-checkbox"><input type="checkbox"
												class="group-checkable" data-set="#favourable .checkboxes" />
											</th>
											<th>卡口编号</th>
											<th>卡口名称</th>
											<th>卡口位置</th>
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
			<!--这个页面的弹窗都在这里 start-->
			<jsp:include page="/views/device/park/add.jsp" />
			<jsp:include page="/views/device/park/edit.jsp" />
			<jsp:include page="/views/device/park/addBayonet.jsp" />
			<jsp:include page="/views/device/park/editBayonet.jsp" />
			<jsp:include page="./delete.jsp" />
			<!--这个页面的弹窗都在这里 end-->
		</div>
	</div>
	<!-- END CONTAINER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="/views/include/globalJs.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/select2/select2.min.js"></script><!--下拉框插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script><!--表格插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script><!--bootstrap的表格插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script><!--bootstrap的下拉框插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-multi-select/js/jquery.multi-select.js"></script><!--多选插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script><!--日期选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.min.js"></script><!--日期选择插件 国际化-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script><!--时间选择插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js"></script><!--折扣那个插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootbox/bootbox.min.js"></script><!--alert插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/master/dist/js/bootstrapValidator.min.js"></script><!-- 表单验证的插件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>

<script src="<%=request.getContextPath()%>/assets/global/plugins/jstree/dist/jstree.min.js"></script><!--js树-->

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/assets/admin/pages/scripts/form-icheck.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/js/pageUrl.js"></script>
<script>
jQuery(document).ready(function() {
// initiate layout and plugins
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
park.init(); //Form中精确搜索的的初始化 可以查看/js/park.js
park.dateTimeInit(); //日期框的初始化
park.toastrInit();
park.modalInit();
initTree();
UIIdleTimeout.init();
initDrop();
delPark();
add.init();
addBayonet.init();
edit.init();
var dataSource = $("#favourable");
var editAndDblBayonetUrl = pageUrl.device_bayonet_editAndDblBayonet;
editBayonet.init(dataSource,$("#modify1"),editAndDblBayonetUrl);
$('.tb_del').delUseDialog(dataSource,pageUrl.device_bayonet_delBayonet,"停车场管理");
})
</script>

<script type="text/javascript">
var initTable = function() {
    var dataTable_config = {//jquery_datatable插件的默认参数配置
    	    //下面设置数据栏目是否可排序
    	    "bServerSide": true,
    	    //"paging": false,
    	    "sAjaxSource": pageUrl.device_bayonet_list,
    	    "fnServerData":retrieveData,
    	    "aoColumns" : [ 
    	    {
    	    	"mDataProp" :"id",
    	    	"sClass":"center",
    	    }, {
    	        "mDataProp" : "bayonetId",
    	        "sWidth": "150px",
    	    }, {
    	        "mDataProp" : "name",
    	        "sWidth": "150px"
    	    }, {
    	        "mDataProp" : "posit",
    	        "sWidth": "150px"
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
    			{  aTargets: [0],
    		   //"data": "download_link",
    		    "render": function ( data, type, row ) {
        	   return '<input type="checkbox" class="checkboxes" data-name="'+row.bayonetId+'" value="'+data+'"/>';
	   			}
    		  // return '<a href="../../'+full.path+'">'+data+'</a>'
    		}
    	    ],
    	    "order": [
    		            [1, "asc"]
    		        ] //升序排列
    	};
    var $table = $('#favourable');
    dataTable_config = $.extend({},dataTable_config,$.global_config.table_config);
    oTable = $table.dataTable(dataTable_config);
    $.table_init($table);
    

    function retrieveData( sSource, aoData, fnCallback ) {     
        //查询条件称加入参数数组 

     	var parkId = $("#park_tree").jstree().get_selected();
     	if(parkId==""){
     		parkId=0;
     	}
     	
        $.ajax( {     
            type: "POST",
            url: sSource,   
            dataType:"json",  
            data: "jsonParam="+JSON.stringify(aoData)+"&parkId="+parkId,
            success: function(data) {   
               //$("#url_sortdata").val(data.aaData);  
                fnCallback(data); //服务器端返回的对象的returnObject部分是要求的格式  
                Metronic.init();
           }     
        });    
    }
} 

//初始化下拉框
var initDrop=function(){
	$('.rulePK').select2({
		placeholder: "搜索租赁规则",
        allowClear: true,
        formatNoMatches: "没有匹配的租赁规则",
        formatSearching: "查询中...",
        ajax: {
            url: pageUrl.device_park_getChargeRule,
            dataType: 'json',
            delay: 500,
            data: function (term, page) {
                return {
                	ruleName:term
                };
            },
            results: function (data, page) {
                return {results: data};
            }
        }, 
		minimumInputLength: 0,
		escapeMarkup: function (m) {
            return m;
        }
    });
}
var initTree = function(){
		$.ajax({
	        type : "GET",
	        url : "/park/device/park/getParkTree",
	        dataType : "json",    

	        success : function(json) {
	            parkTree(json); 
	            initTable();
	        }   
    	});
	}
		
	var parkTree = function(json){
        $('#park_tree').jstree({
            'core' : {
            	//'check_callback' : true,
       			'data' : jQuery.parseJSON(json)
            },
     
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            },
            "plugins" : [ "dnd","types","wholerow"]
        });
    }

var delPark  = function(){
	$('#delete_btn').bind("click",function(){
		var id = $("#park_tree").jstree().get_selected();
		var name = $("#"+id).text();
		$("#idDelete").val(id);
		$("#nameDelete").text(name);
		$("#delete").modal('show');
	});
};	

//验证表单的方法
function check(form){
		
		var error3 = $('.alert-danger', form);
		var success3 = $('.alert-success', form);
		var config = {
		    rules: {
		    	parkId: {
		            maxlength: 20,
		        	required: true
		        },
		        remark: {
		        	maxlength: 100,
		        	required: true
		        }
		    },
			    invalidHandler: function (event, validator) { //display error alert on form submit   
			        success3.hide();
			        error3.show();
			        Metronic.scrollTo(error3, 200);
			    }
			};
		return form.validate($.extend({},config,$.global_config.validator_config));
};
</script>
<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>