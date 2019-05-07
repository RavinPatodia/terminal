<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ include file="../include/taglib.jsp"%>

<!-- BEGIN SIDEBAR -->
	<!-- 左侧菜单栏 -->
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->       
			<ul class="page-sidebar-menu page-sidebar-menu-closed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
   				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="sidebar-search-wrapper">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
					<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
					<form class="sidebar-search " action="extra_search.html" method="post">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="搜索"/>
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form>
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				
				<!-- 左侧栏菜单动态加载begin -->
				<c:set var="firstMenu" value="true" />
				<c:forEach items="${fns:getAuthList()}" var="resource" varStatus="status">
					<c:if test="${resource.parentId == 1 && resource.isShow == true}">
						<c:choose>
							<c:when test="${firstMenu}">
								<c:choose>
									<c:when test="${requestScope.menuFlag eq 'yes'}">
										<li class="start active">
									</c:when>
									<c:when test="${resource.id eq requestScope.selectingMenugrand.id}">
										<li class="start active">
									</c:when>
									<c:otherwise>
										<li class="start">
									</c:otherwise>
								</c:choose>
	        				</c:when>  
							<c:otherwise>  
		            			<c:choose>
		            				<c:when test="${resource.id eq requestScope.selectingMenugrand.id}">
		            					<li class="active">
		            				</c:when>
		            				<c:otherwise>
		            					<li class="">
		            				</c:otherwise>
		            			</c:choose>
		        			</c:otherwise>  
	    				</c:choose>
	    				<c:choose>
	    					<c:when test="${fns:haveChildAuth(resource.id) }">
	    						<a href="javascript:;">
	    						<c:choose>
									<c:when test="${resource.id eq requestScope.selectingMenugrand.id}">
										<span class="arrow open"></span>
									</c:when>
									<c:otherwise>
										<span class="arrow"></span>
									</c:otherwise>
								</c:choose>
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<c:url value='${empty resource.href?"javascript:;": resource.href }'/>"> 
	    					</c:otherwise>
	    				</c:choose>
	    				<i class='${ empty resource.icon?"icon-list": resource.icon}'></i>
						<span class="title">${ resource.name }</span>
						<span class="selected"></span>
						</a>	
					<c:if test="${fns:haveChildAuth(resource.id) }"> <!-- 若一级菜单无子节点，则不执行下面方法 -->	
						<c:set var="resourceList" value="${fns:getAuthList()}" />
						<c:set var="firstSubMenu" value="true" />
						<c:forEach items="${resourceList}" var="menu" varStatus="subStatus">
							<c:if test="${subStatus.first}">
									<ul class="sub-menu">
							</c:if>
							<c:if test="${menu.parentId == resource.id && menu.isShow == true }">
								
								<c:choose>
									<c:when test="${menu.id eq requestScope.selectingMenuparent.id}">
										<li class="active">
									</c:when>
									<c:otherwise>  
			            				<li>
			        				</c:otherwise> 
								</c:choose>
									<a href="<c:url value='${ menu.href }'/>">
										<i class='${ empty menu.icon?"icon-tag": menu.icon}'></i>
										<span class="title">${ menu.name }</span>
										<c:if test="${fns:haveChildAuth(menu.id) }">
											<c:choose>
												<c:when test="${menu.id eq requestScope.selectingMenuparent.id}">
													<span class="arrow open"></span>
												</c:when>
												<c:otherwise>
													<span class="arrow"></span>
												</c:otherwise>
											</c:choose>
										</c:if>
									</a>
										
									<c:if test="${fns:haveChildAuth(menu.id) }">
										<c:forEach items = "${resourceList}" var="submenu" varStatus="subStatus2"> 
											<c:if test="${subStatus2.first }">
												<ul class="sub-menu">
											</c:if>
											<c:if test="${submenu.parentId == menu.id && submenu.isShow == true }">
												<c:choose>
													<c:when test="${submenu.id eq requestScope.selectingMenu.id}">
														<li class="open">
													</c:when>
													<c:otherwise>
														<li>
													</c:otherwise>
												</c:choose>
													<a href="<c:url value='${ submenu.href }'/>">
													<i class='${ empty submenu.icon?"icon-pencil": submenu.icon}'></i>
													<span class="title">${ submenu.name }</span></a>
												</li>
											</c:if>
											<c:if test="${subStatus2.last }">
												</ul>
											</c:if>	
										</c:forEach>	
									</c:if>
																
								</li>
								<c:set var="firstSubMenu" value="false" />	
							</c:if>
							<c:if test="${subStatus.last}">
								</ul>
							</c:if>
						</c:forEach>
					</c:if>
					<c:set var="firstMenu" value="false" />
					</li>
					</c:if>							
				</c:forEach>													
				<!-- 左侧栏菜单动态加载end -->	
 			</ul>
 		</div>
	<!-- END SIDEBAR MENU -->
	</div>
<!-- END SIDEBAR -->