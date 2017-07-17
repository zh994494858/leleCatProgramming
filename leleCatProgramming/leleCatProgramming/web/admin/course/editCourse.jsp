<%--
  Created by IntelliJ IDEA.
  User: 张欢
  Date: 2016/11/24
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>

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

<a:html title="乐乐猫儿童趣味编程 - 管理课程" ctx="${ctx}" cmd="main-content">
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
                    ${requestScope.description}
                </div>

                <div class="panel-body">

                    <form role="form" class="form-horizontal" name="course" action="${ctx}/admin/course/edit" enctype="multipart/form-data" method="post" >

                        <!-- ID -->
                        <input type="hidden" class="form-control" id="courseId" value="${requestScope.course.courseId}" name="courseId">

                        <!-- 名称 -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name">课程名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" value="${requestScope.course.courseName}"
                                       name="courseName">
                            </div>
                        </div>

                        <div class="form-group-separator"></div>

                        <!-- 描述 -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="info">课程描述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control autogrow" cols="5" id="info" style="resize: none;" name="courseIntroduce">${requestScope.course.courseIntroduce}</textarea>
                            </div>
                        </div>

                        <div class="form-group-separator"></div>

                        <!-- 是否启用实验室功能 -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="info">是否在实验室中启用</label>
                            <div class="col-sm-4">
                                <input type="checkbox" name="enableAtLab" ${requestScope.course.enableAtLab ? "checked" : ""} class="iswitch iswitch-secondary">
                            </div>
                        </div>

                        <!-- 组件标识 -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name">实验室组件标识</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="clPlugin" name="clPlugin" value="${requestScope.course.clPlugin}" placeholder="输入组件标识...">
                            </div>
                        </div>

                        <div class="form-group-separator"></div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="info">是否启用该课程</label>
                            <div class="col-sm-4">
                                <input type="checkbox" name="openOrNot" ${requestScope.course.openOrNot ? "checked" : ""} class="iswitch iswitch-secondary">
                            </div>
                        </div>

                        <div class="form-group-separator"></div>


                        <%--<!-- start: 背景 -->--%>
                        <%--<div class="form-group">--%>
                            <%--<awf:cropper name="主题背景" description="为新主题设置一个背景." id="img-preview"--%>
                                         <%--img_src="${requestScope.course.imgUrl}" ctx="${ctx}"/>--%>
                        <%--</div>--%>
                        <%--<!-- end: 图标 -->--%>

                        <%--<div class="form-group-separator"></div>--%>

                        <!-- 提交 -->
                        <div class="form-group">
                            <div class="col-sm-12" style="text-align: center">
                                <button id="sub" class="btn btn-success btn-icon" onclick="submit1()">
                                    <i class="fa-check"></i>
                                    <span>提交</span>
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </ac:tab>

        </div>

    </div>
    <!-- end: 标签选项控件 -->
    <script >
        function submit1(){
            document.course.submit();
        }
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
                $('.error').html('请上传jpg、jpge、png、gif、bmp等图片格式').show();
                return;
            }
            // check for file size
            if (oFile.size > 2 * 1024 * 1024) {
                $('#sub').attr("disabled",true);
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
</a:html>

<%-- ACat i lele. --%>