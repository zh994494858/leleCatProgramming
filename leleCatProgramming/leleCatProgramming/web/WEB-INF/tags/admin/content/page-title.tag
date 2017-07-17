<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="内容页头" pageEncoding="UTF-8" %>

<%@ attribute name="title"		description="页面标题" %>
<%@ attribute name="descrption" description="页面描述" %>
<%@ attribute name="breadcrumb" description="面包屑, 接受类型 List<Lable>" type="java.util.List" %>
<%@ attribute name="ctx" %>

<!-- start: 子页面的页头 -->
<div class="page-title">

	<!-- start: 子页面信息 -->
	<div class="title-env">
		<!-- 子页面功能名称 -->
		<h1 class="title">${title}</h1>
		<!-- 子页面功能介绍 -->
		<p class="description">${descrption}</p>
	</div>
	<!-- end: 子页面信息 -->

	<!-- start: 面包屑 -->
	<div class="breadcrumb-env">
		<ol class="breadcrumb bc-1">
			<c:forEach var="item" items="${breadcrumb}">
				<c:choose>
					<c:when test="${item == breadcrumb.get(breadcrumb.size() - 1)}">
						<li class="active">
							<strong>${item.name}</strong>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<c:choose>
								<c:when test="${item.url == '#'}">
									<a href="javascript:void(0);"><i class="${item.icon}"></i>${item.name}</a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}${item.url}"><i class="${item.icon}"></i>${item.name}</a>
								</c:otherwise>
							</c:choose>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ol>
	</div>
	<!-- end: 面包屑 -->

</div>
<!-- end: 子页面的页头 -->

<%-- ACat i lele --%>