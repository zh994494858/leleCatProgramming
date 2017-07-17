<%@ tag description="左侧显示功能菜单项" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="value" description="菜单项对象" type="cc.lelecat.tag.menu.Item" required="true" %>
<%@ attribute name="ctx" description="ctx" type="java.lang.String"%>



<c:if test="${value != null}">
	<a href="javascript:void(0);"
		${value.url == null ? "" : "onclick=\"$('#index-iframe')[0].src='".concat(ctx).concat(value.url).concat("';\"")}>
		<i class="${value.icon}"></i>
		<span class="title">${value.name}</span>
		<c:if test="${value.remind != 0}">
			<span class="label label-success pull-right">${value.remind}</span>
		</c:if>
	</a>
</c:if>

<%-- ACat i lele --%>