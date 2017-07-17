<!--
子章节信息（包括子章节标识符、名字、所属父章节、章节介绍）
fixed: fixed: 被iframe引用时高度自适应bug

@author 王冀琛
@time 2016/11/01

Modified by ACat.
2016-11-13 21:21:17
ACat i lele.
-->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>
<%@ taglib prefix="awd" tagdir="/WEB-INF/tags/admin/widget/dialog" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<a:html title="乐乐猫儿童趣味编程 - 管理主题章节" ctx="${ctx}">

	<a:main-content>
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
					<div class="panel-heading">
						<h3 class="panel-title">该章节的一些相关设置.</h3>
					</div>
					<div class="panel-body">

						<form name="chapter" action="${ctx}/admin/chapter/add" role="form" class="form-horizontal">

							<!-- 名称 -->
							<div class="form-group">
								<label class="col-sm-2 control-label" for="name">章节名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="name" name="chapterName">
								</div>
							</div>

							<div class="form-group-separator"></div>

							<!-- 组件标识 -->
							<div class="form-group">
								<label class="col-sm-2 control-label" for="identification">章节组件标识</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="identification" name="identification">
								</div>
							</div>

							<div class="form-group-separator"></div>

							<!-- 描述 -->
							<div class="form-group">
								<label class="col-sm-2 control-label" for="info">章节描述</label>
								<div class="col-sm-10">
									<textarea class="form-control autogrow" cols="5" id="info" style="resize: none;" name="chapterIntroduce" placeholder="描述..."></textarea>
								</div>
							</div>

							<div class="form-group-separator"></div>
							<!-- 提交, 重置 -->
							<div class="form-group">
								<div class="col-sm-12" style="text-align: center">
									<button class="btn btn-success btn-icon" onclick="submit1()">
										<i class="fa-check"></i>
										<span>提交</span>
									</button>
									<button class="btn btn-info btn-icon" type="reset">
										<i class="fa-remove"></i>
										<span>重置</span>
									</button>
								</div>
							</div>
						</form>

					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>
	<script>
		function submit1() {
			document.chapter.submit();
		}


	</script>
</a:html>

<%-- ACat i lele. --%>