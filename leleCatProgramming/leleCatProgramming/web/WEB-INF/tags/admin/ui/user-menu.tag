
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/admin/ui/user-menu" %>
<%@ tag description="用户信息栏用户头像显示及账户功能菜单" pageEncoding="UTF-8" %>

<%-- start: 参数 --%>
<%@ attribute name="username" type="java.lang.String" %>
<%@ attribute name="user_image" type="java.lang.String" %>
<%@ attribute name="functions" type="java.util.List" %>
<%@ attribute name="lastNum" %>
<%@ attribute name="ctx"%>
<%-- end: 参数 --%>

<!-- start: 用户显示以及账户功能菜单栏 -->
<li class="dropdown user-profile">
	<!-- start: 用户显示 -->
	<a href="#" data-toggle="dropdown">
		<img src="${user_image}" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
		<span>
			${username}
			<i class="fa-angle-down"></i>
		</span>
	</a>
	<!-- end: 用户显示 -->

	<!-- start: 账户功能菜单栏 -->
	<ul class="dropdown-menu user-profile-menu list-unstyled">

		<c:forEach var="item" items="${functions}">
			
			<c:choose>
				<c:when test="${functions.size() - functions.indexOf(item) <= lastNum}">
					<tags:item value="${item}" last="last" ctx="${ctx}"/>
				</c:when>
				<c:otherwise>
					<tags:item value="${item}" ctx="${ctx}"/>
				</c:otherwise>
			</c:choose>

		</c:forEach>

	</ul>
	<!-- end: 账户功能菜单栏 -->
</li>
<!-- end: 用户显示以及账户功能菜单栏 -->

<%-- ACat i lele --%>