<%--
Created by 王冀琛
Date: 2016/11/26
Time: 16:35
垃圾箱页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<email:html title="草稿箱" emailType="草稿箱" ctx="${ctx}">
	<div class="col-sm-3">
		<email:ibox ctx="${ctx}" draftsCount="${draftsCount}" dustbinCount="${dustbinCount}" inboxCount="${inboxCount}" outboxCount="${outboxCount}"/>
	</div>
	<div class="col-sm-9 animated fadeInRight">
		<div class="mail-box-header">
			<h2>
				草稿箱 (${draftsCount})
			</h2>
			<email:tools ctx="${ctx}" emailType="草稿箱" />
		</div>
		<div class="mail-box">
			<div class="ibox-content">
				<form method="post" id="allEmail">
					<table class="table table-striped table-bordered table-hover table-mail dataTables-example">
						<thead>
						<tr class="unread">
							<td width="15%"><i class="glyphicon glyphicon-check"></i> 选择</td>
							<td width="39%"><i class="fa fa-user"></i> 收件人</td>
							<td width="26%"><i class="fa fa-star"></i> 主题</td>
							<td width="20%" align="center"><i class="fa fa-clock-o modal-icon"></i> 时间</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${requestScope.emails}" var="email">
							<tr class="unread readSign">
								<td class="check-mail">
									<input type="checkbox" name="message" value="${email.key.emailId}" class="i-checks">
								</td>
								<td class="mail-ontact">
									<a href="${ctx}/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=草稿箱">${email.key.receiver.name}</a>
								</td>
								<td class="mail-subject">
									<span class="readState label label-warning pull-right">${email.value}</span>
									<a href="${ctx }/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=草稿箱">${email.key.emailTheme}</a>
								</td>
								<td align="center">
										${email.key.time}
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</email:html>
