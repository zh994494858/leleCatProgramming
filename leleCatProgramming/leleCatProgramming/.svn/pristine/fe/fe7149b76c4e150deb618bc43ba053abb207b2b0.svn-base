<%@ tag description="HTML标签" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<!-- start: 属性 -->
<%@ attribute name="title"		description="页面标题" %>
<%@ attribute name="emailType"		description="页面邮件类型" %>
<%@ attribute name="ctx" 		description="页面相对地址"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- end: 属性 -->
<!DOCTYPE html>
<html>
<email:head ctx="${ctx}" title="${title}" emailType="${emailType}" />
<body class="gray-bg">
<div class="wrapper wrapper-content" id="main-body">
	<div class="row">
		<jsp:doBody/>
	</div>
</div>
<email:foot ctx="${ctx}" emailType="${emailType}"/>
</body>
</html>

<%-- ACat i lele --%>