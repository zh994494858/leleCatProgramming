<!--
角色增加和修改页面

fixed: iframe 引用自适应高度

Created by ACat.
2016-11-5 22:28:35
ACat i lele.
revised by 苗怀雨
date:2016-11-15 11:02:49
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<admin:html title="编辑角色信息" body_class="page-body" ctx="${ctx}">

	<style>
		.ms-container {
			margin-left: auto;
			margin-right: auto;
			width: 80% !important;
		}
	</style>

	<div class="page-container">
		<div class="panel panel-default" id="main-body">

			<div class="panel-body">

				<form action="${requestScope.account == null ? "add" : "edit"}" class="form-horizontal form-wizard validate" id="edit-body" method="get">

					<c:if test="${requestScope.account != null}">
						<input type="hidden" name="accountId" value="${requestScope.account.id}">
					</c:if>

					<!-- start: 员工登录信息 -->
					<!-- 登录名 -->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="username">登录名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="username" id="username"
								   value="${requestScope.account != null ? requestScope.account.username : ""}"
								   data-validate="required,minlength[6]" placeholder="输入新账户的登录名">
						</div>
					</div>
					<!-- 密码 -->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">登录密码</label>
						<div class="col-sm-10">
							<input type="password" name="password" class="form-control" id="password"
								   value="${requestScope.account != null ? requestScope.account.password : ""}"
								   data-validate="required,minlength[6]" placeholder="输入新账户的登录密码...">
						</div>
					</div>
					<!-- 真实姓名 -->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="name">姓名</label>
						<div class="col-sm-10">
							<input type="text" name="name" class="form-control" id="name"
								   value="${requestScope.account != null ? requestScope.account.name : ""}"
								   data-validate="required" placeholder="输入新账户的姓名...">
						</div>
					</div>
					<!-- 分配角色 -->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="role-id">分配角色</label>
						<div class="col-sm-10">
							<select class="form-control" id="role-id" name="role" data-validate="required">
								<c:forEach var="role" items="${requestScope.roles}">
									<option value="${role.id}" ${requestScope.account != null && requestScope.account.role.equals(role) ? "selected" : ""}>
										${role.name}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group-separator"></div>

					<!-- 提交按钮 -->
					<div class="form-group">
						<div class="col-sm-6"></div>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-secondary btn-single">提交</button>
						</div>
					</div>

					<!-- end: 表单内容 -->
				</form>
			</div>

		</div>
	</div>

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx}/admin/assets/js/cropper/cropper.min.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2.css">
	<link rel="stylesheet" href="${ctx}/admin/assets/js/select2/select2-bootstrap.css">

	<!-- Imported scripts on this page -->
	<script src="${ctx}/admin/assets/js/wysihtml5/src/bootstrap-wysihtml5.js"></script>
	<script src="${ctx}/admin/assets/js/uikit/vendor/codemirror/codemirror.js"></script>
	<script src="${ctx}/admin/assets/js/uikit/vendor/marked.js"></script>
	<script src="${ctx}/admin/assets/js/uikit/js/uikit.min.js"></script>
	<script src="${ctx}/admin/assets/js/uikit/js/addons/htmleditor.min.js"></script>
	<script src="${ctx}/admin/assets/js/ckeditor/ckeditor.js"></script>
	<script src="${ctx}/admin/assets/js/ckeditor/adapters/jquery.js"></script>
	<script src="${ctx}/admin/assets/js/cropper/cropper.min.js"></script>
	<script src="${ctx}/admin/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${ctx}/admin/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
	<script src="${ctx}/admin/assets/js/formwizard/jquery.bootstrap.wizard.min.js"></script>
	<script src="${ctx}/admin/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx}/admin/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctx}/admin/assets/js/daterangepicker/daterangepicker.js"></script>
	<script src="${ctx}/admin/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx}/admin/assets/js/daterangepicker/daterangepicker.js"></script>

</admin:html>

<!-- 苗怀雨 添加新员工 -->