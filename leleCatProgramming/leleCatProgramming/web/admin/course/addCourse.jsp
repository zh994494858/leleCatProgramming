<!--
	编辑章节

    Created by ACat.
    ACat i lele.

	Modified by ACat.
	2016-11-12 22:59:39
	ACat i lele.
-->


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>
<%@ taglib prefix="awf" tagdir="/WEB-INF/tags/admin/widget/form" %>

<%@ page import="cc.lelecat.tag.lable.Lable" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cc.lelecat.tag.menu.Item" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%--<%
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
	Item active_item = new Item("添加课程", "admin/course/addCourse.jsp", "fa-hand-o-up");
	left_tags.add(active_item);
	pageContext.setAttribute("left_tags", left_tags);
	pageContext.setAttribute("active_tag", active_item);

	List<Item> right_tags = new ArrayList<>();

	Item item = new Item("选择课程", "/admin/course/list", "fa-plus");
	item.setCmd("tag-success");
	right_tags.add(item);
	pageContext.setAttribute("right_tags", right_tags);
%>--%>

<a:html title="乐乐猫儿童趣味编程 - 管理课程" ctx="${ctx}" cmd="main-content">
	<script type="text/javascript">
		function submit1(){
			document.course.submit();
		};
		//重置图片
		function resetImg(){
			$('#previewImg').attr("src",'${ctx}/admin/data/vw1.jpg');
		};
		//图片预览
		function showPreview() {
			// get selected file
			var oFile = $('#imgFile')[0].files[0];
			// hide all errors
			$('.error').hide();
			// check for image type (jpg and png are allowed)
			var rFilter = /^(image\/jpeg|image\/png)$/i;
			if (! rFilter.test(oFile.type)) {
				$('#sub').attr("disabled",true);
				$('#previewImg').attr("src",'${ctx}/res/raw-assets/resources/School/HTML/arcate.jpg');
				$('.error').html('请上传jpg、jpge、png、gif、bmp等图片格式').show();
				return;
			}
			// check for file size
			if (oFile.size > 2 * 1024 * 1024) {
				$('#sub').attr("disabled",true);
				$('#previewImg').attr("src",'${ctx}/res/raw-assets/resources/School/HTML/arcate.jpg');
				$('.error').html('图片大小不能超过2M').show();
				return;
			}
			$('#sub').attr("disabled",false);
			// preview element
			var oImage = document.getElementById('previewImg');

			// prepare HTML5 FileReader
			var oReader = new FileReader();
			oReader.onload = function(e) {

				// e.target.result contains the DataURL which we can use as a source of the image
				oImage.src = e.target.result;
				oImage.onload = function () { // onload event handler

					// Create variables (in this scope) to hold the Jcrop API and image size
					var jcrop_api, boundx, boundy;

					// destroy Jcrop if it is existed
					if (typeof jcrop_api != 'undefined')
						jcrop_api.destroy();

					// initialize Jcrop
					$('#preview').Jcrop({
						minSize: [32, 32], // min crop size
						aspectRatio : 1, // keep aspect ratio 1:1
						bgFade: true, // use fade effect
						bgOpacity: .3, // fade opacity
						onChange: updateInfo,
						onSelect: updateInfo,
						onRelease: clearInfo
					}, function(){

						// use the Jcrop API to get the real image size
						var bounds = this.getBounds();
						boundx = bounds[0];
						boundy = bounds[1];

						// Store the Jcrop API in the jcrop_api variable
						jcrop_api = this;
					});
				};
			};

			// read selected file as DataURL
			oReader.readAsDataURL(oFile);
		}
	</script>
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
					该课程的一些相关设置.
				</div>

				<div class="panel-body">

					<form role="form" class="form-horizontal" name="course" action="${ctx}/admin/course/add" method="post" enctype="multipart/form-data">

						<!-- 名称 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">课程名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="courseName" placeholder="输入名称...">
                            </div>
						</div>

						<div class="form-group-separator"></div>

						<!-- 描述 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="info">课程描述</label>
							<div class="col-sm-10">
								<textarea class="form-control autogrow" cols="5" id="info" style="resize: none;" name="courseIntroduce" placeholder="描述..."></textarea>
							</div>
						</div>

						<div class="form-group-separator"></div>

						<!-- 是否启用实验室功能 -->
						<div class="form-group">
								<%--<label class="col-sm-2 control-label" for="info">是否启用</label>--%>
								<%--<div class="col-sm-4">--%>
								<%--<input type="checkbox" checked class="iswitch iswitch-secondary" name="useOrNot">--%>
								<%--</div>--%>
							<label class="col-sm-3 control-label" for="info">是否启用实验室功能</label>
							<div class="col-sm-3">
								<input type="checkbox" checked class="iswitch iswitch-secondary" name="enableAtLab">
							</div>
						</div>

						<!-- 组件标识 -->
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">实验室组件标识</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="clPlugin" name="clPlugin" placeholder="输入组件标识...">
							</div>
						</div>

						<div class="form-group-separator"></div>

						<!-- start: 图标 -->
						<%--<div class="form-group">--%>
							<%--<awf:cropper name="主题背景" description="为新主题设置一个背景." id="img-preview"--%>
										 <%--img_src="${course.imgUrl}"  ctx="${ctx}"/>--%>
						<%--</div>--%>
						<!-- end: 图标 -->

						<div class="form-group-separator"></div>

						<!-- 提交, 重置 -->
						<div class="form-group">
							<div class="col-sm-12" style="text-align: center">

                                <button id ="sub" class="btn btn-success btn-icon" onclick="submit1()">
                                    <i class="fa-check"></i>
                                    <span>提交</span>
                                </button>
								<button class="btn btn-info btn-icon" id="resetBtn" type="reset" value="Reset" onclick="resetImg()">
									<i class="fa-remove"></i>
									<span>重置</span>
								</button>
							</div>
						</div>
					</form>

				</div>
			</ac:tab>

		</div>

	</div>
	<!-- end: 标签选项控件 -->

</a:html>

<%-- ACat i lele. --%>