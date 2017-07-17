<!--
管理测试题的页面

Created by 王冀琛
2016/11/24
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>
<%@ taglib prefix="awd" tagdir="/WEB-INF/tags/admin/widget/dialog" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<a:html title="乐乐猫儿童趣味编程 - 管理主题章节" ctx="${ctx}">

	<a:main-content>
		<!-- start: 子页面的页头 -->
		<ac:page-title title="${requestScope.title}"
					   descrption="${requestScope.get('title-description')}"
					   breadcrumb="${requestScope.crumbs}" ctx="${ctx}"/>

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
						<form role="form" class="form-horizontal" name="addTrueFalse" action="${ctx}/admin/judge/add" method="post">
							<div class="col-md-12">
								<input type="hidden" name="chapterId" value="${requestScope.chapterId}"/>

                                <!--题目-->
                                <div class="form-group-separator"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">题目</label>
                                    <div class="col-sm-10">
										<textarea class="form-control autogrow" id="itemContent" cols="5" style="resize: none;" name="itemContent" placeholder="输入题干..."></textarea>
                                    </div>
                                </div>

                                <!--答案-->
                                <div class="form-group-separator"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">答案</label>
                                    <div class="col-sm-10">
                                        <label class="control-label">错误</label>
                                        <input type="radio"  name="answer" value="false">
                                        &nbsp;&nbsp;
                                        <label class="control-label">正确</label>
                                        <input type="radio"  name="answer" value="true">
                                    </div>
                                </div>

								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-12" style="text-align: center">
										<awb:button name="提交" icon="fa-check" class_name="btn-success" onclick="submit()"/>
                                        <button class="btn btn-icon btn-info" type="reset">
                                            <i class="fa-remove"></i>
                                            <span>重置</span>
                                        </button>
									</div>
								</div>
							</div>

						</form>
					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>

	<link rel="stylesheet" href="../assets/js/multiselect/css/multi-select.css">
	<script src="../assets/js/multiselect/js/jquery.multi-select.js"></script>

</a:html>

<%-- ACat i lele. --%>