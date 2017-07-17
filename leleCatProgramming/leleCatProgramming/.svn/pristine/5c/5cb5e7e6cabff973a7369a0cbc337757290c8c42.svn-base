<!--
	管理课程的页面

	Created by ACat.
	ACat i lele.

	Modified by ACat.
	2016-11-12 19:17:08
	ACat i lele.
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awn" tagdir="/WEB-INF/tags/admin/widget/news" %>


<%--<%
	// 页头面包屑参数对象示例
	List<Lable> lables = new ArrayList<>();
	Lable lable = new Lable("主页", null, "fa-home");
	lables.add(lable);
	lable = new Lable("选择课程");
	lables.add(lable);

	pageContext.setAttribute("breadcrumb", lables);


	// tag参数对象示例
	List<Item> left_tags = new ArrayList<>();
	Item active_item = new Item("选择课程", "course/list", "fa-hand-o-up");
	left_tags.add(active_item);
	pageContext.setAttribute("left_tags", left_tags);
	pageContext.setAttribute("active_tag", active_item);

	List<Item> right_tags = new ArrayList<>();
	Item item = new Item("添加课程", "/admin/course/addCourse.jsp", "fa-plus");
	item.setCmd("tag-success");
	right_tags.add(item);
	pageContext.setAttribute("right_tags", right_tags);

%>--%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<a:html ctx="${ctx}" cmd="main-content">

	<!-- 子页面的页头 -->
	<ac:page-title title="${requestScope.title}" ctx="${ctx}" descrption="${requestScope.get('title-description')}" breadcrumb="${requestScope.get('crumbs')}"/>

	<div class="row">

		<div class="col-md-12">

			<!-- start: 标签选项卡 -->
			<ac:tab active_tag="${requestScope.active_tag}"
					left_tags="${requestScope.left_tags}"
					right_tags="${requestScope.right_tags}"
					ctx="${ctx}">

				<div class="panel-heading">
					${requestScope.description}
				</div>

				<div class="panel-body">
					<!--c:forEach循环开始-->
					<c:forEach items="${requestScope.courses}" var="p">
						<div class="col-sm-4">
							<awn:news-big courseId="${p.courseId}" name="${p.courseName}"
										  description="${p.courseIntroduce}"
										  ctx="${ctx}"
										  url="/admin/course/list-chapters?courseId=${p.courseId}"
										  img_url="${p.imgUrl}"/>
						</div>
					</c:forEach>
					<!--c:forEach循环结束-->

				</div>

			</ac:tab>

		</div>

	</div>

</a:html>


<!-- ACat i lele -->