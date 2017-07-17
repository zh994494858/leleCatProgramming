<!--
	有关方向课程的信息的操作页面,
	如新增方向课程, 更改方向课程信息等

	Created by ACat.
	ACat i lele.
	revise by 苗怀雨
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags/admin"%>
<%@ taglib prefix="awb" tagdir="/WEB-INF/tags/admin/widget/button"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<a:html title="乐乐猫儿童趣味编程 - 登录网站管理中心" body_class="page-body login-page" ctx="${ctx}">
    <div class="row">

        <div class="col-sm-6">

            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    // Reveal Login form
                    setTimeout(function () {
                        $(".fade-in-effect").addClass('in');
                    }, 1);


                    // Validation and Ajax action
                    $("form#login").validate({
                        rules: {
                            username: {
                                required: true
                            },

                            passwd: {
                                required: true
                            }
                        },

                        messages: {
                            username: {
                                required: '请输入您的用户名.'
                            },

                            passwd: {
                                required: '请输入您的密码.'
                            }
                        },


                    });

                    // Set Form focus
                    $("form#login .form-group:has(.form-control):first .form-control").focus();
                });
            </script>

            <!-- 错误信息列表 -->
            <div class="errors-container">
                <c:if test="${requestScope.error != null}">
                    <h5 style='color:rgb(255,0,0)'>${requestScope.error}</h5>
                    <br>
                </c:if>
            </div>

            <!-- class="fade-in-effect" 为登录表单设置淡入效果 -->
            <form action="${ctx}/admin/login/check" method="post" role="form" courseId="login" class="login-form fade-in-effect">

                <div class="login-header">
                    <a href="${ctx}/admin/index.jsp" class="logo">
                        <img src="${ctx}/admin/logo.png" alt="" width="80"/>
                        <span>登录</span>
                    </a>

                    <p>嗨, 在这里登录乐乐猫网站管理中心.</p>
                </div>


                <div class="form-group">
                    <label class="control-label">用户名</label>
                    <input type="text" class="form-control input-dark" name="username" courseId="username" value="admin"
                           autocomplete="off"/>
                </div>

                <div class="form-group">
                    <label class="control-label">密码</label>
                    <input type="password" class="form-control input-dark" name="password" courseId="password" value="admin"
                           autocomplete="off"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-dark  btn-block text-left">
                        <i class="fa-lock"></i>
                        登 录
                    </button>
                </div>

            </form>

        </div>

    </div>

    <script src="${ctx}/admin/assets/js/jquery-validate/jquery.validate.min.js"></script>
</a:html>
