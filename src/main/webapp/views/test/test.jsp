﻿<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <jsp:include page="/views/layout/menu.jsp" /> 
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<!-- <h3 class="page-title" style="font-family:'Microsoft Yahei'">
				收费规则 <small><i class="icon-home"></i> 一段解释性文字</small>
			</h3> -->
			
			<!-- END PAGE HEADER-->
			<jsp:include page="./form.jsp" />

		</div>

	</div>
</div>
<!-- END CONTAINER -->


<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<jsp:include page="/views/include/globalJs.jsp" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- <script src="<%=request.getContextPath()%>/assets/global/plugins/jcrop/js/jquery.color.js"></script>
<script src="<%=request.getContextPath()%>/assets/global/plugins/jcrop/js/jquery.Jcrop.min.js"></script> -->
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
 <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script><!--单选按钮和复选按钮的插件--> -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/global.js"></script><!--一些自己写的插件的-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.formFill.js"></script><!--自己写的表单填充插件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/myjquery.ValidatorFill.js"></script><!--自己写的表单填充插件-->
<script src="<%=request.getContextPath()%>/assets/global/plugins/icheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>

<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath()%>/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/pageLevelJs/charge/chargeGlobal.js"></script>
<script src="<%=request.getContextPath()%>/js/components-dropdowns.js"></script>
<script src="<%=request.getContextPath()%>/js/park.js"></script>
<script src="<%=request.getContextPath()%>/js/common/parkSelect.js"></script>


<script>
jQuery(document).ready(function() {
 Metronic.init();
Layout.init(); 
ComponentsDropdowns.init();
park.init();
park.dateTimeInit();
park.toastrInit();
park.timepickerInit();
park.discountInit();
park.modalInit(); 
initDrop();
deviceSelect2.initInDrivewaySelect();
deviceSelect2.initOutDrivewaySelect();
carSelect2.initCarType();
carSelect2.initCarColor();
carSelect2.initCarModule();


$('#inForm input[name="carModel.licensePlate"]').keyup(function(){
	var licensePlate = $(this).val();
	if(licensePlate.length < 7)
		return;
	else{
		$.ajax({
			url:"/park/user/user/getCar",
			type:"POST",
			data:{licensePlate:licensePlate},
			success:function(data){
				var obj = data;
				var formObj = obj.object;
				if(obj.success){
					$("#inForm").fill(obj.object);
					$("#inForm").find(".car_type > a > span").eq(0).text(formObj.licensePlateType);
					$("#inForm").find(".car_color > a > span").eq(0).text(formObj.carColor);
					$("#inForm").find(".car_module > a > span").eq(0).text(formObj.carModel);
					if(obj.msg!=null&&obj.msg!=""){
						$("#editcar_existmsg").html(obj.msg);
					}else{
						$("#editcar_existmsg").html("");
					}
				}else{								
					$("#editcar_existmsg").html("未查询到该车牌号对应的车辆");
				}
			},
			error:function(){
				alert('数据获取错误！');
			}
		});
	}
});


});

var initDrop=function(){
	$('.pspId').select2({
		placeholder: "搜索车位编号",
        allowClear: true,
        formatNoMatches: "没有匹配的车位编号",
        formatSearching: "查询中...",
        ajax: {
            url: "${getPspId}",
            dataType: 'json',
            delay: 500,
            data: function (term, page) {
                return {};
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
</script>

<!-- END PAGE LEVEL SCRIPTS -->
</body>
<!-- END BODY -->
</html>