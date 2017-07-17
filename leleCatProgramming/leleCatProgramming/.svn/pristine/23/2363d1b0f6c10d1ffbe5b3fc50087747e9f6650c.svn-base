<!--
	有关客户账户管理页面，
	如修改客户账户信息，删除客户账户，封锁客户账户等

	@author 王冀琛
	@time  2016/10/31
	revised by 苗怀雨
	date:2016-11-14 15:01:57
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin"%>
<%@ taglib prefix="content" tagdir="/WEB-INF/tags/admin/content"%>
<%@ taglib prefix="tab" tagdir="/WEB-INF/tags/admin/content/tab"%>
<%@ taglib prefix="dialog" tagdir="/WEB-INF/tags/admin/widget/dialog"%>
<%@ taglib prefix="au" tagdir="/WEB-INF/tags/admin/ui" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<admin:html title="客户账户管理" body_class="page-body" ctx="${ctx}">
	<admin:main-content>
			<content:page-title title="${requestScope.title}" ctx="${ctx}" descrption="${requestScope.get('title-description')}" breadcrumb="${requestScope.crumbs}">
			</content:page-title>

			<!-- start: 标签选项控件 -->
			<div class="row">

				<div class="col-md-12">
					<tab:tab-nav active_tag="${requestScope.active_tag}" left_tags="${requestScope.left_tags}" right_tags="${requestScope.right_tags}" ctx="${ctx}"/>
					<div class="tab-content">

						<!-- start: 选择客户账户 -->
						<div class="tab-pane active panel" id="choose">

							<div class="panel-heading">
								选择一个您要管理的客户账户
							</div>

							<div class="panel-body">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#example-1").dataTable({
											aLengthMenu: [
												[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
											],
										});
									});
									function deleteBlockUpdate(id, name) {
										$('#delete-accountId').attr('href', 'delete?normalAccountId='+id);
										$(".modal-title").html("删除账户: " + name);
									}
									function editBlockUpdate(id, name) {
										$('#edit-iframe').attr('src','edit-body?normalAccountId='+id);
										$(".modal-title").html("编辑账户: " + name);
									}
									function disableBlockUpdate(id, name) {
										$('#disable-accept').attr('href', 'disable?normalAccountId=' + id);
										$(".modal-title").html("禁用账户: " + name);
									}
									function enableBlockUpdate(id, name) {
										$('#enable-accept').attr('href', 'enable?normalAccountId=' + id);
										$(".modal-title").html("启用账户: " + name);
									}
								</script>
									<!-- start: 账户详细信息表格 -->
								<table cellspacing="0" class="table table-small-font table-bordered table-striped table-text-center" id="example-1">

											<thead>
											<tr>
												<th width="25%">账户名</th>
												<th width="15%">登录次数</th>
												<th width="25%">最后登录时间</th>
												<th>操作</th>
											</tr>
											</thead>

											<tbody class="middle-align">
											<c:if test="${requestScope.get('account-list') != null }">
												<c:forEach var="account" items="${requestScope.get('account-list')}">
													<tr>
														<td>${account.username}</td>
														<td>${account.loginCount}</td>
														<td>${account.lastLoginTime}</td>
														<td>
															<c:choose>
																<c:when test="${account.disabled != true }">
																	<a type="button"  class="btn btn-warning btn-xs" onclick="jQuery('#disable-block').modal('show', {backdrop: 'fade'});disableBlockUpdate(${account.normalAccountId}, '${account.name}');">
																		禁用
																	</a>
																</c:when>
																<c:otherwise>
																	<a type="button"  class="btn btn-info btn-xs" onclick="jQuery('#enable-block').modal('show', {backdrop: 'fade'});enableBlockUpdate(${account.normalAccountId}, '${account.name}');">
																		启用
																	</a>
																</c:otherwise>
															</c:choose>
															<a type="button"  class="btn btn-danger btn-xs" onclick="jQuery('#delete-block').modal('show', {backdrop: 'fade'});deleteBlockUpdate(${account.normalAccountId}, '${account.name}');">删除</a>
														</td>
													</tr>
												</c:forEach>
											</c:if>

											</tbody>

										</table>
									<!-- end: 账户详细信息表格 -->
							</div>
						</div>

					</div>

				</div>

			</div>
			<!-- end: 标签选项控件 -->
	</admin:main-content>

	<!-- start Modal 3 (确认是否封锁该账户)-->
	<dialog:modal-dialog id="disable-block" title="封锁账户">
        <div class="modal-body">
            确认封锁该账户？
        </div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info"><a href="#" style="color: white" id="disable-accept">确定</a></button>
		</div>
	</dialog:modal-dialog>
	<!-- end Modal 3 (确认是否封锁该账户)-->

	<!-- start Modal 3 (确认是否封锁该账户)-->
	<dialog:modal-dialog id="enable-block" title="解锁账户">
        <div class="modal-body">
            确认解锁该账户？
        </div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info"><a href="#" style="color: white" id="enable-accept">确定</a></button>
		</div>
	</dialog:modal-dialog>
	<!-- end Modal 3 (确认是否封锁该账户)-->

	<!--start Modal 2 (确认是否删除该账户)-->
	<dialog:modal-dialog id="delete-block" title="删除账户">
        <div class="modal-body">
            确认删除该账户？
        </div>
		<div class="modal-footer">
			<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
			<button type="button"  class="btn btn-info"><a href="#" id="delete-accountId" style="color: white">删除</a></button>
		</div>
	</dialog:modal-dialog>
	<!--end Modal 2 (确认是否删除该账户)-->

	<!--start 更改弹出框-->
	<dialog:modal-dialog id="edit-block" title="编辑账户" width="96%">
		<div class="modal-body">
			<div style="margin-top: -30px;">
				<au:iframe ctx="${ctx}" url="#" id="edit-iframe" name="edit-iframe" style="margin-top: -20px;"/>
			</div>
		</div>
	</dialog:modal-dialog>
<!--end Modal 1 (修改密码)-->
	<!-- Imported scripts on this page -->
	<script src="${ctx}/admin/assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="${ctx}/admin/assets/js/datatables/dataTables.bootstrap.css">

	<!-- Imported scripts on this page -->
	<script src="${ctx}/admin/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx}/admin/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx}/admin/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
</admin:html>