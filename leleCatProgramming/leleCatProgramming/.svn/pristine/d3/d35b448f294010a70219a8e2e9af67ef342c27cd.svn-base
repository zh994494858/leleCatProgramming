
<%@ tag description="HTML 大懒猫标签" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>

<!-- start: 属性 -->
<%@ attribute name="title"		description="页面标题" %>
<%@ attribute name="body_class"	description="自定body标签class属性" %>
<%@ attribute name="ctx" 		description="页面相对地址"%>
<%@ attribute name="cmd"		description="页面命令" %>
<!-- end: 属性 -->

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" content="text/html">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta name="description" content="Xenon Boostrap Admin Panel"/>
	<meta name="author" content=""/>

	<title>${title}</title>

	<!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	<link rel="stylesheet" href="${ctx}/admin/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/custom.css">

	<link rel="stylesheet" href="${ctx}/admin/assets/css/lelecat-custom/lelecat-tag.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/lelecat-custom/lelecat-text.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/css/lelecat-custom/lelecat-table.css">

	<script src="${ctx}/admin/assets/js/jquery-1.11.1.min.js"></script>
	<script src="${ctx}/admin/assets/js/maxcdn/respond/1.4.2/respond.min.js"></script>
	<script src="${ctx}/admin/assets/js/maxcdn/html5shiv/3.7.2/html5shiv.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

</head>
<body class="${body_class == null ? "page-bdoy" : body_class}">

<c:choose>
	<c:when test="${cmd != null && cmd.equals('main-content')}">
		<a:main-content>
			<jsp:doBody/>
		</a:main-content>
	</c:when>
	<c:otherwise>
		<jsp:doBody/>
	</c:otherwise>
</c:choose>

<script src="${ctx}/admin/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/admin/assets/js/TweenMax.min.js"></script>
<script src="${ctx}/admin/assets/js/resizeable.js"></script>
<script src="${ctx}/admin/assets/js/joinable.js"></script>
<script src="${ctx}/admin/assets/js/xenon-api.js"></script>
<script src="${ctx}/admin/assets/js/xenon-toggles.js"></script>
<script src="${ctx}/admin/assets/js/xenon-custom.js"></script>

</body>
</html>

<%-- ACat i lele --%>