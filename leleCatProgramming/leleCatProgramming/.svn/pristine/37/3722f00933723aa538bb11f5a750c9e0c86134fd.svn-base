<!--
	实验室-添加新员工

	fixed: 被iframe引用时高度自适应bug

	Created by 苗怀雨
	date: 	2016/10/31
	revised by 苗怀雨
	date:2016-11-15 11:02:49
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin"%>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/admin/content"%>
<%@ taglib prefix="tab" tagdir="/WEB-INF/tags/admin/content/tab"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<admin:html title="添加账户信息" body_class="page-body" ctx="${ctx}">
	<admin:main-content>
		<content:page-title title="${requestScope.title}" descrption="${requestScope.get('title-description')}" breadcrumb="${requestScope.crumbs}"  ctx="${ctx}">
		</content:page-title>
		<!-- start: 标签选项控件 -->
		<div class="row">

			<div class="col-md-12">
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

		</div>
		<!-- end: 标签选项控件 -->
	</admin:main-content>
<!-- Imported styles on this page -->
<link rel="stylesheet" href="${ctx}/admin/assets/js/cropper/cropper.min.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2-bootstrap.css">
<link rel="stylesheet" href="${ctx}/admin/assets/js/multiselect/css/multi-select.css">

</admin:html>



<!-- 苗怀雨 添加新员工 -->