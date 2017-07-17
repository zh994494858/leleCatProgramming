<%--
Created by 王冀琛
Date: 2016/11/26
Time: 16:35
邮件信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<email:html emailType="邮件信息" ctx="${ctx}" title="邮件信息">
	<div class="col-sm-3">
		<email:ibox ctx="${ctx}" draftsCount="${draftsCount}" dustbinCount="${dustbinCount}" inboxCount="${inboxCount}" outboxCount="${outboxCount}"/>
	</div>
	<div class="col-sm-9 animated fadeInRight">
		<div class="mail-box-header">
			<form id="allEmail" method="post">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<c:choose>
							<c:when test="${requestScope.detailType.equals('草稿箱') || requestScope.detailType.equals('发件箱')}">
								<a href="${ctx}/admin/mail/writeLatter.jsp?receiver=${email.receiver.name}&theme=${email.emailTheme}&content=${email.emailContent}&emailId=${email.emailId}">
									<button class="btn btn-info btn-circle" type="button" data-toggle="tooltip" title="继续编辑">
										<i class="glyphicon glyphicon-transfer"></i>
									</button>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/admin/mail/writeLatter.jsp?receiver=${email.sender.name}">
									<button class="btn btn-success btn-circle" type="button" data-toggle="tooltip" title="回复">
										<i class="glyphicon glyphicon-transfer"></i>
									</button>
								</a>
							</c:otherwise>
						</c:choose>
						<a href="#">
							<button onclick="moveToDustbin()" type="button" class="btn btn-danger btn-circle" data-toggle="tooltip" title="删除">
								<i class="fa fa-trash-o"></i>
							</button>
						</a>
					</div>
					<h2>
						<strong>
							查看邮件
						</strong>
					</h2>
					<input type="hidden" name="message" value="${email.emailId}"/>
					<div class="mail-tools tooltip-demo m-t-md">
						<h3>
							<span class="font-noraml"><strong>主题：</strong> </span>${email.emailTheme}
						</h3>
						<h5>
							<span class="pull-right font-noraml"><strong>${email.time}</strong></span>
							<span class="font-noraml"><strong>发件人：</strong> </span>${email.sender.name}<br/><br/>
							<span class="font-noraml"><strong>收件人：</strong> </span>${email.receiver.name}
						</h5>
					</div>
				</div>
				<div class="mail-box">
					<!-- start:邮件内容 -->
					<div class="mail-body">
						<h3 class="m-t-lg">邮件正文</h3>
						<div class="well">
							<div class="row diff-wrapper">
								<!-- start:邮件正文 -->
									${email.emailContent}
								<!-- end:邮件正文 -->
							</div>
						</div>
					</div>
					<!-- end:邮件内容 -->
					<div class="mail-body text-right tooltip-demo">
						<c:choose>
							<c:when test="${requestScope.detailType.equals('草稿箱') || requestScope.detailType.equals('发件箱')}">
								<a href="${ctx}/admin/mail/writeLatter.jsp?receiver=${email.receiver.name}&theme=${email.emailTheme}&content=${email.emailContent}">
									<button class="btn btn-info btn-circle" type="button" data-toggle="tooltip" title="继续编辑">
										<i class="glyphicon glyphicon-transfer"></i>
									</button>
								</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/admin/mail/writeLatter.jsp?receiver=${email.sender.name}">
									<button class="btn btn-success btn-circle" type="button" data-toggle="tooltip" title="回复">
										<i class="glyphicon glyphicon-transfer"></i>
									</button>
								</a>
							</c:otherwise>
						</c:choose>
						<a href="#">
							<button onclick="moveToDustbin()" type="button" class="btn btn-danger btn-circle" data-toggle="tooltip" title="删除">
								<i class="fa fa-trash-o"></i>
							</button>
						</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</form>
		</div>
	</div>
</email:html>
