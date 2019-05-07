
<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<div class="page-toolbar">
	<div class="btn-group pull-right">
		<button type="button"
			class="btn btn-fit-height grey-salt dropdown-toggle"
			data-toggle="dropdown" data-hover="dropdown" data-delay="1000"
			data-close-others="true">
			切换主题 <i class="fa fa-angle-down"></i>
		</button>
		<ul class="dropdown-menu pull-right col-md-6" role="menu"
			id="styleToggle">
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/default.css">default</a>
			</li>
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/grey.css">grey</a>
			</li>
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/darkblue.css">darkblue</a>
			</li>
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/blue.css">blue</a>
			</li>
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/light.css">light</a>
			</li>
			<li><a href="#"
				data-url="<%=request.getContextPath()%>/assets/admin/layout/css/themes/light2.css">light2</a>
			</li>
		</ul>
	</div>
</div>