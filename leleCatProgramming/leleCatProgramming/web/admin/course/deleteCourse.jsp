<!--
删除课程页面

Created by ACat.
ACat i lele.

Modified by ACat.
2016-11-12 22:31:02
ACat i lele.
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<a:html title="乐乐猫儿童趣味编程 - 管理课程" ctx="${ctx}" cmd="main-content">
	<!-- 子页面的页头 -->
	<ac:page-title title="${requestScope.title}"
				   descrption="${requestScope.get('title-description')}"
				   breadcrumb="${requestScope.crumbs}" ctx="${ctx}"/>

	<!-- start: 标签选项控件 -->
	<div class="row">

		<div class="col-md-12">

			<!-- start: 标签选项卡 -->
			<ac:tab active_tag="${requestScope.active_tag}"
					left_tags="${requestScope.left_tags}"
					right_tags="${requestScope.right_tags}"
					ctx="${ctx}">

				<div class="panel-heading" style="color: #cc3f44;">
					${requestScope.description}
				</div>

				<div class="panel-body">
					<div class="row" style="color: #cc3f44;">
						<label class="col-sm-1">警告: </label>
						<label class="col-sm-11">
							您正在删除 ${requestScope.courseName} 课程, 该操作将删除该课程所包含的所有章节并且该操作不可逆, 若您确定要删除该方向, 请在下面的文本框中输入您的账户密码, 以确定该操作为您需要的.
						</label>
					</div>
					<br>
					<br>
					<div class="row">
						<div class="col-sm-4"></div>
						<div class="col-sm-4">
							<form role="form" class="form-horizontal" name="course" action="${ctx}/admin/course/del">
								<div class="col-sm-10">
									<input type="password" class="form-control" id="field-1" name="password" placeholder="您的账户密码">
								</div>
								<input type="hidden" name="courseId" value="${param.courseId }"/>
								<div class="col-sm-2">

									<button class="btn btn-danger btn-icon" onclick="submit1()">
										<i class="fa-check"></i>
										<span>删除</span>
									</button>
								</div>

							</form>
						</div>
						<div class="col-sm-4"></div>
					</div>
				</div>
			</ac:tab>

		</div>

	</div>
	<!-- end: 标签选项控件 -->
	<script>
		function submit1() {
			document.course.submit();
		}
	</script>
</a:html>

<%-- ACat i lele. --%>
