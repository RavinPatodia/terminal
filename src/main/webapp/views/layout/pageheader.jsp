<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title" style="font-family: 'Microsoft Yahei'">
	${requestScope.currentMenu.name} <small><i class="icon-home"></i> 一段解释性文字</small>
</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> 
			<a href="index.html">首页</a> 
			<i class="fa fa-angle-right"></i>
		</li>
		<c:if test="${!empty requestScope.selectingMenugrand}">
			<li>
				<a href = "<%=request.getContextPath()%>${requestScope.selectingMenugrand.href}">${requestScope.selectingMenugrand.name}</a>
				<i class="fa fa-angle-right"></i>
			</li>
		</c:if>
		<c:if test="${!empty requestScope.selectingMenuparent}">
			<li>
				<a href = "<%=request.getContextPath()%>${requestScope.selectingMenuparent.href}">${requestScope.selectingMenuparent.name}</a>
				<i class="fa fa-angle-right"></i>
			</li>
		</c:if>
		<c:if test="${!empty requestScope.selectingMenu}">
			<li>
				<a href = "<%=request.getContextPath()%>${requestScope.selectingMenu.href}">${requestScope.selectingMenu.name}</a>
			</li>
		</c:if>
	</ul>
</div>
<!-- END PAGE HEADER-->
	
