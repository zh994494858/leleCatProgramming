<!--
管理测试题的页面

Created by ACat.
ACat i lele.
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>
<%@ taglib prefix="awd" tagdir="/WEB-INF/tags/admin/widget/dialog" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<a:html title="乐乐猫儿童趣味编程 - 管理主题章节" ctx="${ctx}">

	<script>
		function deleteBlockUpdate(id) {
			$('#deleteChoiceId').attr('href', '${ctx}/admin/choice/delete?choiceId=' + id);
			$(".modal-title").html("删除测试题");
		}
		function editBlockUpdate(id, content, oA, oB, oC, answer) {
			$('#itemContent').html(content);
			$('#optionA').attr('value', oA);
			$('#optionB').attr('value', oB);
			$('#optionC').attr('value', oC);

			if (answer == 0) $('#answer1').attr('checked', 'checked');
			else if (answer == 1) $('#answer2').attr('checked', 'checked');
			else if (answer == 2) $('#answer3').attr('checked', 'checked');

			$('#Id').attr('value', id);
			$(".modal-title").html("编辑测试题");
		}
		function disableBlockUpdate(id) {
			$('#disabledChoiceId').attr('href', '${ctx}/admin/choice/disable?choiceId=' + id);
			$(".modal-title").html("禁用测试题");
		}
		function enableBlockUpdate(id) {
			$('#enabledChoiceId').attr('href', '${ctx}/admin/choice/enable?choiceId=' + id);
			$(".modal-title").html("启用测试题");
		}
	</script>

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
						<h3 class="panel-title">管理测试题</h3>

						<div class="table-right-opreation-button">
							<awb:button class_name="btn-success btn-icon-standalone" icon="fa-pencil" name="添加新测试题"
										onclick="location.href='${ctx}/admin/choice/add-test-page?chapterId=${requestScope.chapterId}'"/>
						</div>
					</div>
					<div class="panel-body">

						<link rel="stylesheet" href="../assets/css/lelecat-custom/lelecat-table.css">

						<script type="text/javascript">
							$(document).ready(function ($) {
								$("#test-table").dataTable().yadcf([
									{column_number: 0, filter_type: 'text'},
									{column_number: 1, filter_type: 'text'},
									{column_number: 2, filter_type: 'text'}
								]);

								$("#test-table_filter").remove();

								setInterval(operationToNormal, 200);
							});

							function operationToNormal() {
								var operation = $("#test-table").find(".operation");
								operation.unbind();
								operation.removeClass("sorting");
								var operation_input = operation.find("input");
								operation_input.attr("disabled", "disabled");
								operation_input.attr("placeholder", "");
								operation_input.css("background", "rgb(235, 235, 228)");
							}
						</script>

						<table class="table table-striped table-bordered" id="test-table">
							<thead>
							<tr class="replace-inputs">
								<th width="20%">ID</th>
								<th>题干</th>
								<th class="operation" width="20%">操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${requestScope.page}" var="p">
								<tr>
									<td>${p.choiceId}</td>
									<td>${p.itemContent}</td>
									<td>
										<!--编辑按钮-->
										<a type="button" href="javascript:void(0);" class="btn btn-info btn-xs"
										   onclick="$('#edit-block').modal('show', {backdrop: 'fade'});editBlockUpdate(${p.choiceId}, '${p.itemContent}', '${p.optionA}', '${p.optionB}','${p.optionC}', '${p.answer}');">编辑</a>
										<!--启用/禁用按钮-->
										<c:choose>
											<c:when test="${p.disabled}">
												<a type="button" href="javascript:void(0);"
												   class="btn btn-warning btn-xs"
												   onclick="$('#enable-block').modal('show', {backdrop: 'fade'});enableBlockUpdate(${p.choiceId})"
												   style="background-color: #00aa00">启用</a>
											</c:when>
											<c:otherwise>
												<a type="button" href="javascript:void(0);"
												   class="btn btn-warning btn-xs"
												   onclick="$('#disable-block').modal('show', {backdrop: 'fade'});disableBlockUpdate(${p.choiceId})">禁用</a>
											</c:otherwise>
										</c:choose>
										<!--删除按钮-->
										<a type="button" href="javascript:void(0);" class="btn btn-danger btn-xs"
										   onclick="$('#delete-block').modal('show', {backdrop: 'fade'});deleteBlockUpdate(${p.choiceId})">删除</a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>

					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>


	<!--start: 编辑弹出框-->
	<awd:modal-dialog title="编辑该题" width="96%" id="edit-block">
		<jsp:include page="main.jsp"/>
		<form action="${ctx}/admin/choice/edit" method="post" name="editChoice" class="form-horizontal">
			<div class="modal-body">
				<!-- id -->
				<input type="hidden" id="Id" name="choiceId" value="">

				<div class="form-group">
					<div class="col-md-12">
						<label class="col-sm-2 control-label">题干：</label>
						<div class="col-sm-10">
							<textarea class="form-control autogrow" cols="5" style="resize: none;" id="itemContent" name="itemContent" placeholder="输入题干..."></textarea>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<label class="col-sm-2 control-label">选项A：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="optionA" name="optionA">
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<label class="col-sm-2 control-label">选项B：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="optionB" name="optionB">
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<label class="col-sm-2 control-label">选项C：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="optionC" name="optionC">
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<label class="col-sm-2 control-label">答案</label>
						<div class="col-sm-10">
							<label class="control-label">选项A</label>
							<input type="radio" id="answer1" name="answer" value="0">
							&nbsp;&nbsp;
							<label class="control-label">选项B</label>
							<input type="radio" id="answer2" name="answer" value="1">
							&nbsp;&nbsp;
							<label class="control-label">选项C</label>
							<input type="radio" id="answer3" name="answer" value="2">
						</div>
					</div>
				</div>

			</div>


			<!--提交按钮-->
			<div class="modal-footer">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-12" style="text-align:center;margin-top: 10px">
							<awb:button class_name="btn-success" icon="fa-check" name="提交"/>
							<button class="btn btn-icon btn-info" type="reset">
								<i class="fa-remove"></i>
								<span>重置</span>
							</button>

						</div>
					</div>
				</div>
			</div>

		</form>
	</awd:modal-dialog>
	<!-- end: 编辑弹出框 -->

	<!-- start: 删除弹出框 -->
	<awd:modal-dialog title="删除测试题" width="50%" id="delete-block">
		<div class="modal-body danger-text">
			<div class="row">
				<label class="col-sm-10">警告: 您确定要删除此题吗？</label>
			</div>
		</div>
		<div class="modal-footer">
			<div class="col-sm-12">
				<div class="col-sm-10"></div>
				<div class="col-sm-2">
					<button class="btn btn-danger">
						<a id="deleteChoiceId" href="#" style="color:white;">删除</a>
					</button>
				</div>
			</div>
		</div>
	</awd:modal-dialog>
	<!--end: 删除弹出框-->

	<!-- start: 禁用弹出框 -->
	<awd:modal-dialog title="禁用测试题" width="50%" id="disable-block">
		<div class="modal-body">
			您确认禁用该题?
		</div>
		<div class="modal-footer">
			<div class="col-sm-8"></div>
			<div class="col-sm-4">
				<a id="disabledChoiceId" href="#">
					<awb:button class_name="btn-warning" name="禁用"/>
				</a>
			</div>
		</div>
	</awd:modal-dialog>
	<!--end: 禁用弹出框-->

	<!-- start: 启用弹出框 -->
	<awd:modal-dialog title="启用测试题" width="50%" id="enable-block">
		<div class="modal-body">
			确认启用该测试题？
		</div>
		<div class="modal-footer">
			<div class="col-sm-8"></div>
			<div class="col-sm-4">
				<a id="enabledChoiceId" href="#">
					<awb:button class_name="btn-warning" name="启用"/>
				</a>
			</div>
		</div>
	</awd:modal-dialog>
	<!--end: 启用弹出框-->

	<script src="../assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<script src="../assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="../assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="../assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
</a:html>

<%-- ACat i lele. --%>