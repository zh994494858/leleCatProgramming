<!-- 
	小测验成功设定
	包括对（选择题、判断题、编程题的数量 以及及格率的设置）

	@author 王冀琛
	@time   2016/11/01

	Modified by ACat.
	2016-11-13 21:15:59
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
						<h3 class="panel-title">${requestScope.description}</h3>
					</div>

					<div class="panel-body">

						<form name="chapter" action="${ctx}/admin/chapter/editNP" class="form-horizontal form-wizard validate">
							<input type="hidden" value="${requestScope.chapter.chapterId}" name="chapterId" id="chaid">
							<div class="col-sm-12">
								<!-- start: 小测试题类型 -->
								<div class="row draggable-portlets">

									<!--start： 小测试题的第一个板块（选择） -->
									<div class="form-group">
										<div class="col-sm-3 text-center">
											<div class="inline">
												<input name="choiceNumber" class="knob" data-min="0" data-max="10" data-width="145"
													   data-height="145" data-thickness=".1" data-fgColor="#8dc63f"
													   data-bgColor="#ebebeb" value="${requestScope.chapter.choiceNumber}">
											</div>
										</div>
										<div class="col-sm-3 text-center">
											<div class="inline">
												<input name="trueFalseNumber" class="knob" data-min="0" data-max="10" data-width="145"
													   data-height="145" data-thickness=".1" data-fgColor="#8dc63f"
													   data-bgColor="#ebebeb" value="${requestScope.chapter.trueFalseNumber}">
											</div>
										</div>
										<div class="col-sm-2"></div>
										<div class="col-sm-4 text-center">
											<div class="inline">
												<input name="passStandard" class="knob" data-min="0" data-max="100" data-width="230"
													   data-height="145" data-thickness=".3" data-fgColor="#7c38bc"
													   data-bgColor="#ebebeb" data-angleArc="210"
													   data-angleOffset="-105" value="${requestScope.chapter.passStandard}">
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-3 text-center">选择题个数</div>
										<div class="col-sm-3 text-center">判断题个数</div>
										<div class="col-sm-2"></div>
										<div class="col-sm-4 text-center">通过标准</div>
									</div>
									<!--end： 小测试题的第一个板块（选择） -->

								</div>
								<!-- end:  小测试题类型 -->

								<div class="form-group-separator"></div>

								<!-- start: 提交按钮 -->
								<div class="form-group">
									<div class="col-sm-10"></div>
									<div class="col-sm-2" style="text-align: center;">
										<button class="btn btn-success btn-icon" onclick="submit()">
											<i class="fa-check"></i>
											<span>提交</span>
										</button>
									</div>
								</div>
								<!-- end: 提交按钮 -->

							</div>

						</form>

					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>

	<link rel="stylesheet" href="../assets/js/jquery-ui/jquery-ui.min.css">
	<script src="../assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="../assets/js/knob/jquery.knob.min.js"></script>

</a:html>

<%-- ACat i lele. --%>
