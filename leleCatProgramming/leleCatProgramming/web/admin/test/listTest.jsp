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

<%@ page import="cc.lelecat.tag.lable.Lable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cc.lelecat.tag.menu.Item" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	// 页头面包屑参数对象示例
	List<Lable> lables = new ArrayList<>();
	Lable lable = new Lable("主页", null, "fa-home");
	lables.add(lable);
	lable = new Lable("选择课程", "choose.html");
	lables.add(lable);
	lable = new Lable("HTML 课程管理");
	lables.add(lable);

	pageContext.setAttribute("breadcrumb", lables);

	// tag参数对象示例
	List<Item> left_tags = new ArrayList<>();
	Item active_item = new Item("选择课程", "/admin/direction/manageNode.jsp", "fa-hand-o-up");
	left_tags.add(active_item);
	pageContext.setAttribute("left_tags", left_tags);
	pageContext.setAttribute("active_tag", active_item);

	List<Item> right_tags = new ArrayList<>();

	/*返回到章节的管理界面*/
	Item item = new Item("返回", "/admin/direction/manageNode.jsp", "fa-plus");
	item.setCmd("tag-success");
	right_tags.add(item);
	/*item = new Item("删除课程", "/admin/direction/manageNode.jsp", "fa-plus");
	item.setCmd("tag-danger");
	right_tags.add(item);*/
	pageContext.setAttribute("right_tags", right_tags);
%>

<a:html title="乐乐猫儿童趣味编程 - 管理主题章节" ctx="${ctx}">

	<a:main-content>
		<!-- 子页面的页头 -->
		<ac:page-title title="HTML 课程管理中心"
					   descrption="在这里您可以管理 <strong>HTML</strong> 课程内容, 包括编辑章节, 添加测验题, 设置课程依章节以及课程属性, 或是删除该课程."
					   breadcrumb="${breadcrumb}"/>

		<!-- start: 标签选项控件 -->
		<div class="row">

			<div class="col-md-12">

				<!-- start: 标签选项卡 -->
				<ac:tab active_tag="${active_tag}"
						left_tags="${left_tags}"
						right_tags="${right_tags}"
						ctx="${ctx}">
					<div class="panel-heading">
						<h3 class="panel-title">管理测试题</h3>

						<div class="table-right-opreation-button">
							<a href="addTest.jsp">
								<awb:button class_name="btn-success btn-icon-standalone" icon="fa-pencil" name="添加新测试题"/>
							</a>
						</div>
					</div>
					<div class="panel-body">

						<link rel="stylesheet" href="../assets/css/lelecat-custom/lelecat-table.css">

						<script type="text/javascript">
							jQuery(document).ready(function ($) {
								$("#test-table").dataTable().yadcf([
									{column_number : 0, filter_type: 'text'},
									{column_number : 1, filter_type: 'text'},
									{column_number : 2, filter_type: 'text'},
									{column_number : 3, filter_type: 'text'}

								]);

								var operation = $("#test-table").find(".operation");
								operation.unbind();
								operation.removeClass("sorting");
								var operation_input = operation.find("input");
								operation_input.attr("disabled", "disabled");
								operation_input.attr("placeholder", "");
								operation_input.css("background", "rgb(235, 235, 228)");
							});
						</script>

						<table class="table table-striped table-bordered" id="test-table">
							<thead>
							<tr class="replace-inputs">
								<th>题型</th>
								<th>章节名称</th>
								<th>题干</th>
								<th class="operation">操作</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td>选择题</td>
								<td>测试题1</td>
								<td>blabla...</td>
								<td>
									<a type="button" href="javascript:void(0);" class="btn btn-info btn-xs"
									   onclick="jQuery('#edit-block').modal('show', {backdrop: 'fade'});">编辑</a>
									<a type="button" href="javascript:void(0);" class="btn btn-warning btn-xs"
									   onclick="jQuery('#lock-block').modal('show', {backdrop: 'fade'});">禁用</a>
									<a type="button" href="javascript:void(0);" class="btn btn-danger btn-xs"
									   onclick="jQuery('#delete-block').modal('show', {backdrop: 'fade'});">删除</a>
								</td>
							</tr>
							</tbody>
						</table>

					</div>
				</ac:tab>

			</div>

		</div>
	</a:main-content>

	<!--start: 编辑弹出框-->
	<awd:modal-dialog title="编辑该题" width="50%" id="edit-block">
		<jsp:include page="main.jsp"/>

		<div class="modal-body">
			<div class="row">

				<%--测试题型--%>
				<div class="col-md-12">
					<label class="col-sm-2 control-lable">测试题型：</label>
					<div class="col-sm-10">
						<div class="radio">
							<label>
								<input type="radio" name="radio-1" checked>选择题
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="radio-1">判断题
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="radio-1">编程题
							</label>
						</div>
					</div>
				</div>

				<%--章节名称--%>
				<div class="col-md-12">
					<label class="col-sm-2 control-lable">章节名称：</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="field-1" placeholder="原始的章节名称">
					</div>
				</div>

				<%--题干--%>
				<div class="col-md-12" style="margin-top: 10px">
					<label class="col-sm-2 control-lable">题干：</label>
					<div class="col-sm-10">
						<textarea cols="5" class="form-control" id="field-5" placeholder="原始的题干描述"></textarea>
					</div>
				</div>

				<%--提交按钮--%>
				<div class="form-group">
					<div class="col-sm-12" style="text-align:center;margin-top: 10px">
						<button class="btn btn-icon btn-success" onclick="" style="width: ;">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>

						<button class="btn btn-icon btn-info" onclick="" style="width: ;">
							<i class="fa-remove"></i>
							<span>重置</span>
						</button>

					</div>
				</div>
			</div>
		</div>

	</awd:modal-dialog>
	<!-- end: 编辑弹出框 -->

	<!-- start: 删除弹出框 -->
	<awd:modal-dialog title="删除该题" width="50%" id="delete-block">
		<div class="danger-text">
			<div class="row">
				<label class="col-sm-10">警告: 您确定要删除 <strong>xxx</strong> 题吗？</label>
			</div>
			<br>
			<br>
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-10"></div>

					<div class="col-sm-2">
						<form role="form" class="form-horizontal">
							<button class="btn btn-danger">删除</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</awd:modal-dialog>
	<!--end: 删除弹出框-->

	<!-- start: 禁用弹出框 -->
	<awd:modal-dialog title="封锁账户" width="50%" id="lock-block">
		<div class="row">
			<label class="col-sm-2"></label>
			<label class="col-sm-10">您确认禁用该账户?</label>
		</div>
		<div class="row">
			<div class="col-sm-8"></div>
			<div class="col-sm-4">
				<awb:button name="禁用" class_name="btn-warning"/>
			</div>
		</div>
	</awd:modal-dialog>
	<!--end: 禁用弹出框-->


	<script src="../assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<script src="../assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="../assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="../assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
</a:html>

<%-- ACat i lele. --%>