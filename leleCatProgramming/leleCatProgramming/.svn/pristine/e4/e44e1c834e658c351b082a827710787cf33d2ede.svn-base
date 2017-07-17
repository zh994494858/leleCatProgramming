<!--
	设置角色权限
	created by 苗怀雨
    date:2016/11/3

    fixed: iframe 引用自适应高度

    Modified by ACat.
    2016-11-5 23:05:42
    ACat i lele.
	revised by 苗怀雨
	date:2016-11-15 11:02:49
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin"%>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/admin/content"%>
<%@ taglib prefix="tab" tagdir="/WEB-INF/tags/admin/content/tab"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<admin:html title="添加角色信息" body_class="page-body" ctx="${ctx}">
	<script src="${ctx}/admin/assets/js/lelecat-custom/iframe.js"></script>
	<admin:main-content>
		<content:page-title title="${requestScope.title}" ctx="${ctx}" descrption="${requestScope.get('title-description')}" breadcrumb="${requestScope.crumbs}">
		</content:page-title>
		<div class="row">
			<!--
				class="col-sm-[xx]" 设置功能块所占的长度, xx取值1至12, 1最短, 12最长
				该div可为1个, 也可为多个, 但是总长度不能大于12, 如下面两个功能块一个为7, 一个为5, 总和12
			-->
			<!-- start: 功能块1 -->
			<div class="col-sm-12">
				<!-- 标签控件 -->
                <tab:tab-nav active_tag="${requestScope.active_tag}"
							 left_tags="${requestScope.left_tags}"
							 right_tags="${requestScope.right_tags}"
							 ctx="${ctx}"/>
				<tab:tab-content description="${requestScope.description}">
					<div class="panel-body" style="margin-top: -30px;">

						<jsp:include page="edit-body.jsp"/>
					</div>
				</tab:tab-content>

			</div>
			<!-- end: 功能块1 -->

		</div>
		<!-- end: 一个纵向Panel-->
	</admin:main-content>

<!-- Imported styles on this page -->
<link rel="stylesheet" href="${ctx}/admin/assets/js/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2-bootstrap.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/multiselect/css/multi-select.css">
	
<!-- 苗怀雨 角色权限设置 -->
</admin:html>