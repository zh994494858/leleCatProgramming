<!--
管理测试题的页面

Created by ACat.
ACat i lele.
更新 kishow
16-12-3
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
<%@page  import="java.lang.Integer" %>
<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    // 页头面包屑参数对象示例
    List<Lable> lables = new ArrayList<>();
    Lable lable = new Lable("主页", null, "fa-home");
    lables.add(lable);
     lable = new Lable("选择课程", "../course/list");
    lables.add(lable);
    Integer courseId=(Integer)request.getAttribute("courseId");
    lable = new Lable("选择章节","../chapter/list?courseId="+courseId);
    lables.add(lable);
    lable = new Lable("判断题管理","null");
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
    Item item = new Item("返回", "/admin/chapter/list?courseId="+courseId, "fa-plus");
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
        <ac:page-title title="选择题管理"
                       descrption="在这里您可以管理 <strong>${chapterName}</strong>章节内容编辑测验题."
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
                            <a href="${ctx}/admin/test/addJudge.jsp?chapterId=${chapterId}&chapterName=${chapterName}">
                                <awb:button class_name="btn-success btn-icon-standalone" icon="fa-pencil"
                                            name="添加新测试题"/>
                            </a>
                        </div>
                    </div>
                    <div class="panel-body">

                        <link rel="stylesheet" href="../assets/css/lelecat-custom/lelecat-table.css">

                        <script type="text/javascript">
                            jQuery(document).ready(function ($) {
                                $("#test-table").dataTable().yadcf([
                                    {column_number: 0, filter_type: 'text'},
                                    {column_number: 1, filter_type: 'text'},
                                    {column_number: 2, filter_type: 'text'},
                                    {column_number: 3, filter_type: 'text'}

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
                                <th width="172px">题型</th>
                                <th width="234px">章节名称</th>
                                <th width="177px">题目</th>
                                <th class="operation">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${page}" var="p">
                                <tr>
                                    <td>${type}</td>
                                    <td>${chapterName}</td>
                                    <td>${p.itemContent}</td>
                                    <td>
                                        <!--编辑按钮-->
                                        <a type="button" href="javascript:void(0);" class="btn btn-info btn-xs"
                                           onclick="jQuery('#edit-block').modal('show', {backdrop: 'fade'});
                                                   jQuery('#itemContent').attr('value','${p.itemContent}');
                                           <c:if test="${p.answer==0}">
                                                   jQuery('#answer1').attr('checked','checked');
                                           </c:if>
                                           <c:if test="${p.answer==1}">
                                                   jQuery('#answer2').attr('checked','checked');
                                           </c:if>
                                                   jQuery('#score').attr('value','${p.score}');
                                                   jQuery('#degree').attr('value','${p.degree}');
                                                   jQuery('#Id').attr('value','${p.trueFalseId}');
                                                   ">编辑/查看</a>
                                        <!--启用/禁用按钮-->
                                        <c:if test="${p.disabled==false}">
                                            <a type="button" href="javascript:void(0);" class="btn btn-warning btn-xs"
                                               onclick="jQuery('#lock-block').modal('show', {backdrop: 'fade'});
                                                       jQuery('#disabledTrueFalseId')
                                                       .attr('href','${ctx}/admin/trueFalse/disabled?trueFalseId='
                                                       +${p.trueFalseId});">禁用</a>
                                        </c:if>
                                        <c:if test="${p.disabled==true}">
                                            <a type="button" href="javascript:void(0);" class="btn btn-warning btn-xs"
                                               onclick="jQuery('#lock-block').modal('show', {backdrop: 'fade'});
                                                       jQuery('#disabledTrueFalseId')
                                                       .attr('href','${ctx}/admin/trueFalse/disabled?trueFalseId='
                                                       +${p.trueFalseId});" style="background-color: #00aa00">启用</a>
                                        </c:if>
                                        <!--删除按钮-->
                                        <a type="button" href="javascript:void(0);" class="btn btn-danger btn-xs"
                                           onclick="jQuery('#delete-block').modal('show', {backdrop: 'fade'});
                                                   jQuery('#deleteTrueFalseId')
                                                   .attr('href','${ctx}/admin/trueFalse/delete?trueFalseId='
                                                   +${p.trueFalseId});">删除</a>
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
    <awd:modal-dialog title="编辑该题" width="50%" id="edit-block">
        <jsp:include page="main.jsp"/>
        <form action="${ctx}/admin/trueFalse/edit" method="post" name="editTrueFalse">
            <div class="modal-body">
                 <%--id--%>
                  <input type="hidden" id="Id" name="trueFalseId" value="">
            </div>
                        <%--题干--%>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <label class="col-sm-2 control-lable">题目：</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="itemContent" name="itemContent"
                                           value=""></div>
                            </div>
                        </div>
                    </div>
                        <%--答案--%>
                   <div class="modal-body">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <label class="col-sm-2 control-label" for="info">答案</label>
                                    <div class="col-sm-10">
                                        <label class="control-label">错误</label>
                                        <input type="radio" id="answer1" name="answer" value="0">
                                        &nbsp;&nbsp;
                                        <label class="control-label">正确</label>
                                        <input type="radio" id="answer2" name="answer" value="1">
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                                <%--分值--%>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="col-sm-2 control-lable">分值：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="score" name="score" value="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <%--难度系数--%>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="col-sm-2 control-lable">难度系数：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="degree" name="degree" value="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                            <%--提交按钮--%>
                        <div class="form-group">
                            <div class="row">
                            <div class="col-sm-12" style="text-align:center;margin-top: 10px">
                                <button class="btn btn-icon btn-success" onclick="submitEidtTrueFalse()"
                                        style="width: ;">
                                    <i class="fa-check"></i>
                                    <span>提交</span>
                                </button>

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
    <awd:modal-dialog title="删除该题" width="50%" id="delete-block">
        <div class="danger-text">
            <div class="row">
                <label class="col-sm-10">警告: 您确定要删除此题吗？</label>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="col-sm-12">
                    <div class="col-sm-10"></div>

                    <div class="col-sm-2">
                        <form role="form" class="form-horizontal">
                            <button class="btn btn-danger">
                                <a id="deleteTrueFalseId" href="#">删除</a>
                            </button>
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
            <label class="col-sm-10">您确认禁用/启用该题?</label>
        </div>
        <div class="row">
            <div class="col-sm-8"></div>
            <div class="col-sm-4">
                <a id="disabledTrueFalseId" href="#">
                    <awb:button class_name="btn-warning" name="禁用/启用"/>
                </a>
            </div>
        </div>
    </awd:modal-dialog>
    <!--end: 禁用弹出框-->


    <script src="../assets/js/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../assets/js/datatables/dataTables.bootstrap.js"></script>
    <script src="../assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
    <script src="../assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
    <script>
        function submitEidtTrueFalse() {
            document.editTrueFalse.submit();
        }
    </script>
</a:html>

<%-- ACat i lele. --%>