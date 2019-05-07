<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="/views/include/taglib.jsp"%>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="<%=request.getContextPath()%>/admin">
			<img style="height: 40px; margin-top:3px;" src="<%=request.getContextPath()%>/assets/admin/layout/img/logo.png" alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler hide">
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:void(0)" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown dropdown-user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="${empty fns:getOperFtpImgUrl()?'/terminal/assets/admin/layout/img/avatar.jpg':fns:getOperFtpImgUrl()}"/>
					<span class="username username-hide-on-mobile">
					${fns:getOperName()} </span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li>
							<a href="javascript:void(0);" onclick="editLoginOperator.init()">
							<i class="icon-user loginInfo"></i> 个人信息 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="changePwd.init()">
							<i class="icon-calendar"></i> 密码修改 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="shift.init()">
							<i class="fa fa-exchange"></i> 交接班</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="/terminal/admin/lock">
							<i class="icon-lock"></i> 锁屏 </a>
						</li>
						<li>
							<a href="/terminal/admin/logout">
							<i class="icon-key"></i> 登出 </a>
						</li>
					</ul>
				</li>
				
			</ul>
			<jsp:include page="/views/system/operater/changePwd.jsp" />
			<jsp:include page="/views/system/operater/shift.jsp" />
		</div>
	</div>
</div>