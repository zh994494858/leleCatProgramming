<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="标签空间的标签部分" pageEncoding="UTF-8" %>

<%@ attribute name="active_tag" type="cc.lelecat.tag.menu.Item" required="true" %>
<%@ attribute name="left_tags" type="java.util.List" %>
<%@ attribute name="right_tags" type="java.util.List" %>
<%@ attribute name="ctx"%>

<ul class="nav nav-tabs">

	<c:forEach var="tag" items="${left_tags}">
		<li ${active_tag == tag ? "class='active'" : ""}>
			<a href="${active_tag == tag || tag.url == null ? "javascript:void(0);" : ctx.concat(tag.url)}"
			   class="${tag.cmd}">
				<span class="visible-xs"><i class="${tag.icon}"></i></span>
				<span class="hidden-xs">${tag.name}</span>
			</a>
		</li>
	</c:forEach>


	<c:forEach var="tag" items="${right_tags}">
		<li class="right-tag ${active_tag == tag ? "active" : ""}">
			<a href="${active_tag == tag || tag.url == null ? "javascript:void(0);" : ctx.concat(tag.url)}" class="${tag.cmd}">
				<span class="visible-xs"><i class="${tag.icon}"></i></span>
				<span class="hidden-xs">${tag.name}</span>
			</a>
		</li>
	</c:forEach>

</ul>

<%-- ACat i lele --%>