<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/admin/content/tab" %>
<%@ tag description="标签控件" pageEncoding="UTF-8" %>

<%-- 属性 --%>
<%@ attribute name="description"%>
<%@ attribute name="active_tag" type="cc.lelecat.tag.menu.Item" required="true" %>
<%@ attribute name="left_tags" type="java.util.List" %>
<%@ attribute name="right_tags" type="java.util.List" %>
<%@ attribute name="ctx"%>
<%-- 属性 --%>

<link rel="stylesheet" href="${ctx}/admin/assets/css/lelecat-custom/lelecat-tag.css">

<!-- start: 标签选项卡 -->
<tags:tab-nav active_tag="${active_tag}" left_tags="${left_tags}" right_tags="${right_tags}" ctx="${ctx}"/>
<div class="tab-content">
<div class="tab-pane active panel">
	<jsp:doBody/>
</div>
</div>
<%-- ACat i lele --%>