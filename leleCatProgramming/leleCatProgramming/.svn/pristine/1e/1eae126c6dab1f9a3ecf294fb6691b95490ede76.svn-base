<!--
	管理角色的页面

	Created by 苗怀雨.
	date:2016/11/2.
	revised by 苗怀雨
	date:2016-11-15 11:02:49

	Modified by ACat,
	2016-11-27 10:38:54,
	ACat i lele.
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin"%>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/admin/content"%>
<%@ taglib prefix="tab" tagdir="/WEB-INF/tags/admin/content/tab"%>
<%@ taglib prefix="dialog" tagdir="/WEB-INF/tags/admin/widget/dialog"%>
<%@ taglib prefix="au" tagdir="/WEB-INF/tags/admin/ui" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<admin:html title="管理角色" body_class="page-body" ctx="${ctx}">

	<script>
		var interval_tag_iframe;
		var interval_tag_refresh;
		var iframe_src;
		var edit_body;
		
		function waitToRefresh() {
			location.reload();
		}
		
		function bindSubmit() {
			edit_body = $("#edit-body", document.getElementById('edit-iframe').contentWindow.document);
			if (edit_body.length > 0) {
				$('#edit-accept').unbind("click").click(function() {
					edit_body.submit();
					setTimeout(waitToRefresh, 100);
				});

				clearInterval(interval_tag_iframe);
			}
		}



		function deleteBlockUpdate(id, name) {
			$("#delete-roleId").attr("value", id);
			$(".modal-title").html("删除角色: " + name);
		}
		function editBlockUpdate(id, name) {

			iframe_src = 'edit-body?roleId=' + id;
            $('#edit-iframe').attr('src', iframe_src);

			$(".modal-title").html("编辑角色: " + name);

			// 计时器使提交按钮绑定iframe中form提交事件
			interval_tag_iframe = setInterval(bindSubmit, 100);
		}
		function disableBlockUpdate(id, name) {
			$('#disable-accept').attr('href', 'disable?roleId=' + id);
			$(".modal-title").html("禁用角色: " + name);
        }
        function enableBlockUpdate(id, name) {
			$('#enable-accept').attr('href', 'enable?roleId=' + id);
			$(".modal-title").html("启用角色: " + name);
        }
    </script>

	<admin:main-content>

		<content:page-title title="${requestScope.title}" ctx="${ctx}" descrption="${requestScope.get('title-description')}" breadcrumb="${requestScope.crumbs}">
		</content:page-title>

		<!-- start: 标签选项控件 -->
		<div class="row">
			<div class="col-md-12">
				<tab:tab-nav active_tag="${requestScope.active_tag}" left_tags="${requestScope.left_tags}" right_tags="${requestScope.right_tags}" ctx="${ctx}"/>
				<tab:tab-content description="${requestScope.description}">
						<form>
							<div class="col-sm-12">
								<div class="table-responsive" data-pattern="priority-columns" data-focus-btn-icon="fa-asterisk" data-sticky-table-header="true" data-add-display-all-btn="true" data-add-focus-btn="true">

									<table cellspacing="0" class="table table-small-font table-bordered table-striped table-text-center">
										<thead>
										<tr>
											<th width="10%">角色ID</th>
											<th width="15%">角色名称</th>
											<th width="">角色描述</th>
											<th width="20%">内容操作</th>
										</tr>
										</thead>
										<tbody>
										<c:if test="${requestScope.get('role-list') != null}">
											<c:forEach var="role" items="${requestScope.get('role-list')}">
												<tr>
													<td>${role.id}</td>
													<td>${role.name}</td>
													<td>${role.description}</td>
                                                    <td>
                                                        <a type="button" class="btn btn-info btn-xs" onclick="$('#edit-block').modal('show', {backdrop: 'fade'});editBlockUpdate(${role.id}, '${role.name}');">编辑</a>
														<c:choose>
															<c:when test="${role.disabled}">
																<a type="button" class="btn btn-info btn-xs" onclick="$('#enable-block').modal('show', {backdrop: 'fade'});enableBlockUpdate(${role.id}, '${role.name}');" >
																	启用
																</a>
															</c:when>
															<c:otherwise>
																<a type="button" class="btn btn-warning btn-xs" onclick="$('#disable-block').modal('show', {backdrop: 'fade'});disableBlockUpdate(${role.id}, '${role.name}');" >
																	禁用
																</a>
															</c:otherwise>
														</c:choose>
                                                        <a type="button" class="btn btn-danger btn-xs" onclick="$('#delete-block').modal('show');deleteBlockUpdate(${role.id}, '${role.name}');">删除</a>
                                                    </td>
												</tr>
											</c:forEach>
										</c:if>
										</tbody>
									</table>

								</div>
							</div>
						</form>
				</tab:tab-content>
			</div>

		</div>
		<!-- end: 标签选项控件 -->
	</admin:main-content>

	<!--start: 编辑弹出框-->
	<dialog:modal-dialog id="edit-block" title="编辑角色" width="96%">
		<div class="modal-body">
			<au:iframe ctx="${ctx}" url="#" id="edit-iframe" name="edit-iframe" style="margin-top: -20px;"/>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info" id="edit-accept" data-dismiss="modal">确定</button>
		</div>
	</dialog:modal-dialog>
	<!-- end: 编辑弹出框 -->

	<!-- start: 删除弹出框 -->
	<dialog:modal-dialog id="delete-block" title="删除角色" width="50%">
		<form action="delete" role="form" class="form-horizontal">
			<div class="modal-body danger-text">
				<div class="row">
                    <label class="col-sm-12">
                        您正在删除该角色, 该操作将删除该角色以及该角色下的所有员工账户, 若您确定要删除该角色, 请在下面的文本框中输入您的账户密码, 以确定该操作为您需要的.
                    </label>
                </div>
				<br>
				<br>
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<input type="hidden" name="roleId" id="delete-roleId">
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
	</dialog:modal-dialog>
	<!--end: 删除弹出框-->

	<!-- start: 禁用弹出框 -->
	<dialog:modal-dialog id="disable-block" title="禁用角色" width="50%">
		<div class="modal-body">
			确认禁用该角色？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info">
				<a style="color: white" id="disable-accept">确定</a>
			</button>
		</div>
	</dialog:modal-dialog>
	<!--end: 禁用弹出框-->

	<!-- start: 启用弹出框 -->
	<dialog:modal-dialog id="enable-block" title="启用角色" width="50%">
		<div class="modal-body">
			确认启用该角色？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info">
				<a style="color: white" id="enable-accept">确定</a>
			</button>
		</div>
	</dialog:modal-dialog>
	<!-- end: 启用弹出框-->

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx}/admin/assets/js/datatables/dataTables.bootstrap.css">
</admin:html>
<!-- ACat i lele -->