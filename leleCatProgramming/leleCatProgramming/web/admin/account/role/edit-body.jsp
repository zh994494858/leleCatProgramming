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

	<script type="text/javascript">
		jQuery(document).ready(function ($) {
			/**
			 * 流程组件js实现
			 */
			$(".multi-select").multiSelect({
				afterInit: function () {
					// Add alternative scrollbar to list
					this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
				},
				afterSelect: function () {
					// Update scrollbar size
					this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
				}
			});

			$(".selectboxit").selectBoxIt().on('open', function () {
				// Adding Custom Scrollbar
				$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
			});


			/**
			 * 角色权限选择组件js实现
			 */
			$(".multi-select").multiSelect({
				afterInit: function () {
					// Add alternative scrollbar to list
					this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
				},
				afterSelect: function () {
					// Update scrollbar size
					this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
				}
			});

			$(".selectboxit").selectBoxIt().on('open', function () {
				// Adding Custom Scrollbar
				$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
			});


			// 将禁用的 下一个 按钮转换为提交按钮
			<c:if test="${requestScope.role == null}">
			var isSubmit = false;
			var btnChangeIntervalId;
			$("li.next, li.previous, ul.tabs li").bind("click", null, function () {
				if (typeof btnChangeIntervalId != "number") {
					btnChangeIntervalId = setInterval(btnChange, 50);
				}
			});

			// 改变btn
			var liNext, btnNext;
			function btnChange() {
				if (!isSubmit) {
					// 没有提交按钮
					liNext = $("li.next.disabled");
					if (liNext.length != 0) {
						// 如果检测到被禁用的按钮, 修改该按钮
						liNext.removeClass("disabled");
						liNext.addClass("submit");
						btnNext = liNext.children("a");
						btnNext.attr({
							"class": "btn btn-secondary btn-single",
							"href": "javascript:void(0);",
							"onClick": "javascript:document.mainForm.submit();"
						});
						btnNext.html("提交");

						isSubmit = true;

						clearInterval(btnChangeIntervalId);
						btnChangeIntervalId = "";
					}
				} else {
					// 有提交按钮
					liNext = $("li.next.submit");
					if (liNext.length != 0) {
						// 如果检测到提交按钮, 修改该按钮
						liNext.removeClass("submit");
						btnNext = liNext.children("a");
						btnNext.attr({
							"class": "",
							"href": "#"
						});
						btnNext.removeAttr("onClick");
						btnNext.html("下一个");

						isSubmit = false;

						clearInterval(btnChangeIntervalId);
						btnChangeIntervalId = "";
					}
				}
			}
			</c:if>

		});
	</script>

	<div class="page-container">
		<div class="panel panel-default" id="main-body">

			<div class="panel-body">

				<form action="${requestScope.role == null ? "add" : "edit"}" name="mainForm" class="form-horizontal form-wizard validate" id="edit-body" method="get">

					<!-- start: 流程标签 -->
					<ul class="tabs">
						<li class="active">
							<a href="#info" data-toggle="tab">
								角色信息
							</a>
						</li>
						<li>
							<a href="#direction" data-toggle="tab">
								添加课程管理权限
							</a>
						</li>
						<li>
							<a href="#power" data-toggle="tab">
								添加其他权限
							</a>
						</li>
					</ul>
					<!-- start: 流程标签 -->

					<div class="progress-indicator">
						<span></span>
					</div>

					<!-- start: 表单内容 -->
					<div class="tab-content no-margin">

						<!-- start: 员工登录信息 -->
						<div class="tab-pane with-bg active" id="info">

							<div class="row">
								<div class="form-group">
									<!-- 角色信息 -->
									<input type="hidden" name="roleId"
										   value="${requestScope.role != null ? requestScope.role.id : ""}">
									<label class="col-sm-2 control-label" for="login-name">角色名</label>
									<div class="col-sm-10">
										<input type="text"
											   class="form-control" id="login-name"
											   name="name"
											   value="${requestScope.role != null ? requestScope.role.name : ""}"
											   data-validate="required" placeholder="输入角色的名称...">
									</div>
								</div>

								<div class="form-group-separator"></div>

								<div class="form-group">
									<!-- 描述 -->
									<label class="col-sm-2 control-label" for="description">角色描述</label>
									<div class="col-sm-10">
								<textarea class="form-control autogrow" cols="5" id="description" name="description"
										  style="resize: none;"
										  placeholder="输入新角色的描述...">${requestScope.role != null ? requestScope.role.description : ""}</textarea>
									</div>
								</div>
							</div>

							<div class="form-group-separator"></div>

						</div>
						<!-- start: 员工登录信息 -->

						<!-- start: 分配角色 -->
						<div class="tab-pane with-bg" id="direction">

							<div class="row">

								<div class="form-group">
									<div class="row">
										<select multiple="multiple" name="coursePowerIds"
												class="form-control multi-select">
											<option disabled
													style="font-size: 20px; line-height: 25px; font-weight: bold;">
												选择添加的课程管理权限
											</option>

											<c:forEach var="item" items="${requestScope.get('course-powers')}">
												<optgroup label="${item.key.courseName}">
													<c:forEach var="power" items="${item.value}">
														<option value="${power.id}" ${requestScope.role != null && requestScope.role.powers.contains(power) ? "selected" : ""}>
															${power.name}
														</option>
													</c:forEach>
												</optgroup>
											</c:forEach>
												<%--<optgroup label="HTML">--%>
												<%--<option value="1">HTML</option>--%>
												<%--<option value="2">CSS</option>--%>
												<%--<option value="3">JavaScript</option>--%>
												<%--</optgroup>--%>
										</select>

									</div>
								</div>

								<div class="form-group-separator"></div>

							</div>


						</div>
						<!-- end: 分配角色 -->

						<!-- start: 分配角色 -->
						<div class="tab-pane with-bg" id="power">

							<div class="row">

								<div class="form-group">
									<select multiple="multiple" name="otherPowerIds" class="form-control multi-select">
										<option disabled
												style="font-size: 20px; line-height: 25px; font-weight: bold;">
											选择添加的其他管理权限
										</option>

										<c:forEach var="power" items="${requestScope.get('other-powers')}">
											<option value="${power.id}"	${requestScope.role != null && requestScope.role.powers.contains(power) ? "selected" : "" }>
												${power.name}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<!-- end: 分配角色 -->

						<!-- Tabs Pager -->

						<ul class="pager wizard">
							<li class="previous">
								<a href="#"><i class="entypo-left-open"></i>上一个</a>
							</li>

							<li class="next">
								<a href="#">下一个<i class="entypo-right-open"></i></a>
							</li>
						</ul>

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
	<link rel="stylesheet" href="${ctx}/admin/assets/js/multiselect/css/multi-select.css">

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
	<script src="${ctx}/admin/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx}/admin/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctx}/admin/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
	<script src="${ctx}/admin/assets/js/daterangepicker/daterangepicker.js"></script>
	<script src="${ctx}/admin/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx}/admin/assets/js/daterangepicker/daterangepicker.js"></script>

</admin:html>

<!-- 苗怀雨 添加新员工 -->