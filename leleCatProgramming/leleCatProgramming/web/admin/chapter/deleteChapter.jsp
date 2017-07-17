<%--
  Created by IntelliJ IDEA.
  User: 张欢
  Date: 2016/11/17
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="ac" tagdir="/WEB-INF/tags/admin/content" %>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button" %>

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
    Item active_item = new Item("删除课程", "/admin/course/deleteCourse.jsp", "fa-hand-o-up");
    left_tags.add(active_item);
    pageContext.setAttribute("left_tags", left_tags);
    pageContext.setAttribute("active_tag", active_item);

    List<Item> right_tags = new ArrayList<>();
    Item item = new Item("选择课程", "/admin/course/manageNode.jsp", "fa-plus");
    item.setCmd("tag-success");
    right_tags.add(item);
    item = new Item("添加课程", "/admin/course/manageNode.jsp", "fa-plus");
    item.setCmd("tag-success");
    right_tags.add(item);
    pageContext.setAttribute("right_tags", right_tags);
%>

<a:html title="乐乐猫儿童趣味编程 - 管理课程" ctx="${ctx}" cmd="main-content">
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

                <div class="panel-heading" style="color: #cc3f44;">
                    删除 <strong>HTML</strong> 课程
                </div>

                <div class="panel-body">
                    <div class="row" style="color: #cc3f44;">
                        <label class="col-sm-1">警告: </label>
                        <label class="col-sm-11">
                            您正在删除 HTML 课程, 该操作将删除该课程所包含的所有章节并且该操作不可逆, 若您确定要删除该方向, 请在下面的文本框中输入您的账户密码, 以确定该操作为您需要的.
                        </label>
                    </div>
                    <br>
                    <br>
                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <form role="form" class="form-horizontal">
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="field-1" placeholder="您的账户密码">
                                </div>
                                <div class="col-sm-2">
                                    <awb:button name="删除" class_name="btn-danger" onclick="location.href='delete'"/>
                                </div>
                            </form>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                </div>
            </ac:tab>

        </div>

    </div>
    <!-- end: 标签选项控件 -->

</a:html>

<%-- ACat i lele. --%>

