<%--
Created by 王冀琛
Date: 2016/11/26
Time: 16:35
写信页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<email:html ctx="${ctx}" emailType="写信" title="写信">
	<div class="col-sm-3">
		<email:ibox ctx="${ctx}" draftsCount="${draftsCount}" dustbinCount="${dustbinCount}" inboxCount="${inboxCount}" outboxCount="${outboxCount}"/>
	</div>
	<div class="col-sm-9 animated fadeInRight">
		<div class="mail-box-header">
				<form class="form-horizontal" id="emailContent" method="post">
					<div class="mail-box-header">
						<div class="pull-right tooltip-demo">
							<button onclick="submitFormToDrafts()" type="button" class="btn btn-info btn-circle" data-toggle="modal" data-target="#saveToDrafts" title="存为草稿">
								<i class="glyphicon glyphicon-import"></i>
							</button>
							<a href="${ctx}/admin/mail/writeLatter.jsp">
								<button type="button" class="btn btn-danger btn-circle" data-toggle="tooltip" title="放弃编辑">
									<i class="fa fa-times"></i>
								</button>
							</a>
						</div>
						<h2>
							写信
						</h2>
					</div>
					<div class="mail-box">
						<div class="mail-body">
							<input type="hidden" value="${param.emailId}" name="emailId"/>
							<!-- start:发动给谁？ -->
							<div class="form-group">
								<label class="col-sm-2 control-label">发送到：</label>
								<div class="col-sm-10">
									<input type="text" name="receiver" class="form-control" value="${param.receiver}">
								</div>
							</div>
							<!-- end:发动给谁？ -->

							<!-- start:邮件标题 -->
							<div class="form-group">
								<label class="col-sm-2 control-label">主题：</label>

								<div class="col-sm-10">
									<input type="text" class="form-control" name="emailTheme" value="${param.theme}">
								</div>
							</div>
							<!-- end:邮件标题 -->

						</div>

						<!-- start:邮件内容 -->
						<div class="col-lg-12">
							<div class="ibox float-e-margins">
								<div class="ibox-title">
									<h5>邮件正文内容</h5>
								</div>
								<div class="ibox-content">
									<textarea name="emailContent" data-provide="markdown" rows="10">${param.content}</textarea>
								</div>
							</div>
						</div>
						<!-- end:邮件内容 -->

						<!-- start:底部的发送，存为草稿，放弃编辑的按钮 -->
						<div class="mail-body text-right tooltip-demo">
							<button onclick="submitFormToOutbox()" class="btn btn-success btn-circle" type="button" data-toggle="tooltip" title="发送">
								<i class="glyphicon glyphicon-send"></i>
							</button>
							<button onclick="submitFormToDrafts()" type="button" class="btn btn-info btn-circle" data-toggle="modal" data-target="#saveToDrafts" title="存为草稿">
								<i class="glyphicon glyphicon-import"></i>
							</button>
							<a href="${ctx}/admin/mail/writeLatter.jsp">
								<button type="button" class="btn btn-danger btn-circle" data-toggle="tooltip" title="放弃编辑">
									<i class="fa fa-times"></i>
								</button>
							</a>
						</div>
						<!-- end:底部的发送，存为草稿，放弃编辑的按钮 -->

						<div class="clearfix"></div>

					</div>
				</form>
		</div>
	</div>
</email:html>
<!-- start:弹出框 -->
<email:dialog emailType="写信" ctx="${ctx}" />
<!-- end:弹出框 -->