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
	Item item = new Item("添加", "/admin/direction/manageNode.jsp", "fa-plus");
	item.setCmd("tag-success");
	right_tags.add(item);
	item = new Item("删除课程", "/admin/direction/manageNode.jsp", "fa-plus");
	item.setCmd("tag-danger");
	right_tags.add(item);
	pageContext.setAttribute("right_tags", right_tags);
%>

<a:html title="乐乐猫儿童趣味编程 - 管理主题章节" ctx="${ctx}">

	<a:main-content>
		<!-- 子页面的页头 -->
		<ac:page-title title="小测验成功设定"
					   descrption="小测验成功设定"
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
						<h3 class="panel-title">对小测验模块进行管理</h3>
					</div>

					<div class="panel-body">

						<form action="" method="">

							<div class="col-sm-12">

								<!-- start: 小测试题类型 -->
								<div class="row draggable-portlets">

									<!--start： 小测试题的第一个板块（选择） -->
									<div class="col-sm-12">
										<div class="form-group">
											<div class="col-sm-2">选择题：</div>
											<span style="margin-left:60px;">该题型数目</span>
											<span style="margin-left:290px;">通过标准</span>
										</div>

										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="3" data-max="10" data-width="145"
														   data-height="145" data-thickness=".1" data-fgColor="#8dc63f"
														   data-bgColor="#ebebeb" value="5">
												</div>
											</div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="0" data-max="100" data-width="230"
														   data-height="230" data-thickness=".3" data-fgColor="#7c38bc"
														   data-bgColor="#ebebeb" data-angleArc="210"
														   data-angleOffset="-105" value="60">
												</div>
											</div>
										</div>

									</div>
									<!--end： 小测试题的第一个板块（选择） -->


									<!--start： 小测试题的第二个板块（判断） -->
									<div class="col-sm-12">
										<div class="form-group">
											<div class="col-sm-2">判断题：</div>
											<span style="margin-left:60px;">该题型数目</span>
											<span style="margin-left:290px;">通过标准</span>
										</div>

										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="3" data-max="10" data-width="145" data-height="145" data-thickness=".1" data-fgColor="#8dc63f" data-bgColor="#ebebeb" value="5">
												</div>
											</div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="0" data-max="100" data-width="230" data-height="230" data-thickness=".3" data-fgColor="#7c38bc" data-bgColor="#ebebeb" data-angleArc="210" data-angleOffset="-105" value="60">
												</div>

											</div>
										</div>
									</div>
									<!--end： 小测试题的第二个板块（判断） -->


									<!--start： 小测试题的第三个板块（编程） -->
									<div class="col-sm-12">
										<div class="form-group">
											<div class="col-sm-2">编程题：</div>
											<span style="margin-left:60px;">该题型数目</span>
											<span style="margin-left:290px;">通过标准</span>
										</div>

										<div class="form-group">
											<div class="col-sm-1"></div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="3" data-max="10" data-width="145" data-height="145" data-thickness=".1" data-fgColor="#8dc63f" data-bgColor="#ebebeb" value="5">
												</div>
											</div>
											<div class="col-sm-4 text-center">
												<div class="inline">
													<input class="knob" data-min="0" data-max="100" data-width="230" data-height="230" data-thickness=".3" data-fgColor="#7c38bc" data-bgColor="#ebebeb" data-angleArc="210" data-angleOffset="-105" value="60">
												</div>

											</div>
										</div>
									</div>
									<!--start： 小测试题的第三个板块（编程） -->


								</div>
								<!-- end:  小测试题类型 -->


								<!-- start: 提交按钮 -->
								<div class="form-group">
									<div class="col-sm-12" style="text-align: center;">
										<button class="btn btn-success btn-icon">
											<i class="fa-check"></i>
											<span>提交</span>
										</button>
										<button class="btn btn-info btn-icon">
											<i class="fa-remove"></i>
											<span>重置</span>
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
