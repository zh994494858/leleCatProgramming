<!--
管理课程的页面

Created by ACat.
修改及功能实现：王子恒
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

		function deleteBlockUpdate(id, name) {
			$("#delete-chapterId").attr("value", id);
			$(".modal-title").html("删除章节: " + name);
		}
		function editBlockUpdate(id, name, identification, introduce) {
			$("#chapterId").attr("value", id);
			$("#chapterName").attr("value", name);
			$("#identification").attr("value", identification);
			$("#chapterIntroduce").html(introduce);
			$(".modal-title").html("编辑章节: " + name);
		}
		function disableBlockUpdate(id, name) {
			$('#disable-accept').click(function () {
				location.href = '${ctx}/admin/chapter/disable?chapterId=' + id;
			});
			$(".modal-title").html("禁用章节: " + name);
		}
		function enableBlockUpdate(id, name) {
			$('#enable-accept').click(function () {
				location.href = '${ctx}/admin/chapter/enable?chapterId=' + id;
			});
			$(".modal-title").html("启用章节: " + name);
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
						<h3 class="panel-title">${requestScope.description}</h3>

						<div class="table-right-opreation-button">
							<a href="${ctx}/admin/chapter/add-page?courseId=${requestScope.courseId}">
								<awb:button name="添加章节" icon="fa-pencil" class_name="btn-info btn-icon-standalone"/>
							</a>
								<%--onclick="$('#edit-block').modal('show', {backdrop: 'fade'});"/>--%>
								<%--<awb:button name="排序" icon="fa fa-sort-numeric-asc"
											class_name="btn-info btn-icon-standalone"/>--%>
						</div>
					</div>
					<div class="panel-body">

						<link rel="stylesheet" href="../assets/css/lelecat-custom/lelecat-table.css">

						<div class="col-sm-12">
							<div class="table-responsive" data-pattern="priority`-columns"
								 data-focus-btn-icon="fa-asterisk" data-sticky-table-header="true"
								 data-add-display-all-btn="true" data-add-focus-btn="true">

								<link rel="stylesheet" href="../assets/css/lelecat-custom/lelecat-table.css">
								<table cellspacing="0"
									   class="table table-small-font table-bordered table-striped table-text-center">
									<thead>
									<tr>

										<th width="10%">ID</th>
										<th width="10%">章节名称</th>
										<th>章节介绍</th>
										<c:if test="${requestScope.get('test-power')}">
											<th width="20%">查看测试题</th>
										</c:if>
										<c:if test="${requestScope.get('chapter-power')}">
											<th width="14%">排序</th>
											<th width="20%">操作</th>
										</c:if>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${requestScope.page}" var="page">
										<tr>

											<td>${page.chapterId}</td>
											<td>${page.chapterName}</td>
											<td class="info-column">${page.chapterIntroduce}</td>
											<c:if test="${requestScope.get('test-power')}">
												<th>
													<a href="${ctx}/admin/choice/list?chapterId=${page.chapterId}">
														查看测试题
													</a>
													&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
													<a href="${ctx}/admin/chapter/pass-standard?chapterId=${page.chapterId}">
														通过标准
													</a>
												</th>
											</c:if>
											<c:if test="${requestScope.get('chapter-power')}">
												<td>
													<a type="button" class="btn btn-info btn-xs up"
													   href="${ctx}/admin/chapter/moveonit?chapterId=${page.chapterId}">
														上移
													</a>
													<a type="button" class="btn btn-info btn-xs down"
													   href="${ctx}/admin/chapter/movedownit?chapterId=${page.chapterId}">
														下移
													</a>
												</td>
												<td>
													<a type="button" class="btn btn-info btn-xs"
													   onclick="$('#edit-block').modal('show', {backdrop: 'fade'});editBlockUpdate(${page.chapterId}, '${page.chapterName}', '${page.identification}', '${page.chapterIntroduce}')">
														编辑
													</a>

													<c:choose>
														<c:when test="${page.block==true}">
															<a type="button" class="btn btn-warning btn-xs"
															   onclick="$('#enable-block').modal('show', {backdrop: 'fade'});enableBlockUpdate(${page.chapterId}, '${page.chapterName}')"
															   style="background-color: #00aa00">
																解锁
															</a>
														</c:when>
														<c:otherwise>
															<a type="button" class="btn btn-warning btn-xs"
															   onclick="$('#disable-block').modal('show', {backdrop: 'fade'});disableBlockUpdate(${page.chapterId}, '${page.chapterName}')">
																禁用
															</a>
														</c:otherwise>
													</c:choose>

													<a type="button" class="btn btn-danger btn-xs" onclick="$('#delete-block').modal('show');deleteBlockUpdate(${page.chapterId}, '${page.chapterName}');">删除</a>
												</td>
											</c:if>
										</tr>
									</c:forEach>
									</tbody>
								</table>

							</div>

						</div>

					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>

	<!--start: 编辑弹出框-->
	<awd:modal-dialog title="编辑章节" width="96%" id="edit-block">
		<form action="${ctx}/admin/chapter/edit" role="form" name="editForm" class="form-horizontal">
			<input type="hidden" name="chapterId" id="chapterId">
			<!-- 名称 -->
			<div class="form-group">
				<label class="col-sm-2 control-label" for="chapterName">章节名称</label>
				<div class="col-sm-9">
					<input type="text" name="chapterName" class="form-control" id="chapterName"
						   placeholder="输入新的新章节的名称...">
				</div>
			</div>
			<div class="form-group-separator"></div>

			<!-- 组件标识 -->
			<div class="form-group">
				<label class="col-sm-2 control-label" for="identification">章节组件标识</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="identification" name="identification">
				</div>
			</div>
			<div class="form-group-separator"></div>

			<!-- 描述 -->
			<div class="form-group">
				<label class="col-sm-2 control-label" for="chapterIntroduce">章节描述</label>
				<div class="col-sm-9">
					<textarea class="form-control autogrow" cols="5" id="chapterIntroduce" style="resize: none;"
							  name="chapterIntroduce" placeholder="描述..."></textarea>
				</div>
			</div>
			<div class="form-group-separator"></div>

			<div class="form-group">
				<div class="col-sm-12" style="text-align: center">
					<awb:button name="提交" icon="fa-check" class_name="btn-success"/>
					<button class="btn btn-info btn-icon" id="resetBtn" type="reset" value="Reset">
						<i class="fa-remove"></i>
						<span>重置</span>
					</button>
				</div>
			</div>
		</form>
	</awd:modal-dialog>
	<!-- end: 编辑弹出框 -->

	<%--start:禁用章节弹出框--%>
	<awd:modal-dialog title="禁用章节" width="50%" id="disable-block">
		<div class="modal-body">
			确认禁用该章节？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button" class="btn btn-info" id="disable-accept">确定</button>
		</div>
	</awd:modal-dialog>
	<%--end:禁用章节弹出框--%>

	<!-- start: 启用弹出框 -->
	<awd:modal-dialog id="enable-block" title="启用章节" width="50%">
		<div class="modal-body">
			确认启用该章节？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button" class="btn btn-info" id="enable-accept">确定</button>
		</div>
	</awd:modal-dialog>

	<%--start:删除章节弹出框--%>
	<awd:modal-dialog id="delete-block" title="删除章节" width="50%">
		<form action="${ctx}/admin/chapter/delete" role="form" class="form-horizontal">
			<div class="modal-body danger-text">
				<div class="row">
					<label class="col-sm-12">
						您正在删除该章节, 该操作将删除该章节以及该章节下的所有测试题, 若您确定要删除该章节, 请在下面的文本框中输入您的账户密码, 以确定该操作为您需要的.
					</label>
				</div>
				<br>
				<br>
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<input type="hidden" name="chapterId" id="delete-chapterId">
						<input type="password" name="password" class="form-control" placeholder="您的账户密码">
					</div>
					<div class="col-sm-2"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-single"><span style="color: white">删除</span></button>
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</form>
	</awd:modal-dialog>
	<%--end:删除章节弹出框--%>

	<script src="${ctx}/admin/assets/js/lelecat-custom/moveUpDown.js"></script>

</a:html>

<%-- ACat i lele. --%>