<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="au" tagdir="/WEB-INF/tags/admin/ui" %>

<!--
管理中心首页

Created by ACat.
ACat i lele.
-->

<c:set var="ctx" value="c"/>

<a:html title="乐乐猫儿童趣味编程 - 网站管理中心" ctx="${ctx}">
	<style>
		.sidebar-menu {
			width: 280px !important;
		}
	</style>

	<!-- 登录提示 -->
	<au:toastr timeOut="3000" ctx="${ctx}" name="${sessionScope.account.name}"/>

	<div class="page-container">

		<!-- 左侧功能菜单 -->
		<au:fun-menu items="${sessionScope.get('menu-list')}" ctx="${ctx}"/>

		<!-- start: 右侧内容部分 -->
		<div class="main-content">

			<!-- start: 用户信息, 通知 以及菜单栏 -->
			<nav class="navbar user-info-navbar" role="navigation">

				<!-- start: 用户信息导航栏左侧 -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
					<!-- 邮件 -->
					<au:email ctx="${ctx}"/>

					<!-- 通知 -->
					<%--<au:notification/>--%>
				</ul>
				<!-- end: 用户信息导航栏左侧 -->

				<!-- start: 用户信息导航栏右侧 -->
				<ul class="user-info-menu right-links list-inline list-unstyled">
					<!-- 锁定账户 -->
					<%--<au:lock-account/>--%>

					<!-- 用户显示以及账户功能菜单栏 -->
					<au:user-menu ctx="${ctx}" username="${sessionScope.account.name}"
								  user_image="${ctx}${sessionScope.account.imgUrl}"
								  functions="${sessionScope.get('function-list')}" lastNum="2"/>

					<!-- 联系人 -->
					<%--<au:chat/>--%>

				</ul>
				<!-- end: 用户信息导航栏右侧 -->
			</nav>
			<!-- end: 用户信息, 通知 以及菜单栏 -->

			<!-- iframe 引用的页面 -->
			<au:iframe ctx="${ctx}" url="${requestScope.get('content-url')}" id="index-iframe"/>

			<!-- 页脚 -->
			<au:footer/>
		</div>
		<!-- end: 右侧内容部分 -->

		<!-- 通信部分 -->
		<%--<au:chat-widget/>--%>

	</div>

	<!-- 初始加载动画 -->
	<au:loading/>

</a:html>