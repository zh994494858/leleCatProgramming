<%@ tag description="显示列表邮件信息" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- start: 参数 --%>
<%@ attribute name="emails" type="java.util.Map" %>
<%@ attribute name="ctx" type="java.lang.String" %>
<%@ attribute name="emailType" type="java.lang.String" %>


<%-- end: 参数 --%>

<!-- start: 显示邮件信息 -->
<div class="mail-box">
	<div class="ibox-content">
		<form method="post" id="allEmail">
			<table class="table table-striped table-bordered table-hover table-mail dataTables-example">
				<thead>
				<tr class="unread">
					<td width="15%"><i class="glyphicon glyphicon-check"></i> 选择</td>
					<c:choose>
						<c:when test="${emailType.equals('收件箱')}">
							<td width="39%"><i class="fa fa-user"></i> 发件人</td>
						</c:when>
						<c:when test="${emailType.equals('垃圾箱')}">
							<td width="19.5%"><i class="fa fa-user"></i> 发件人</td>
							<td width="19.5%"><i class="fa fa-user"></i> 收件人</td>
						</c:when>
						<c:otherwise>
							<td width="39%"><i class="fa fa-user"></i> 收件人</td>
						</c:otherwise>
					</c:choose>
					<td width="26%"><i class="fa fa-star"></i> 主题</td>
					<td width="20%" align="center"><i class="fa fa-clock-o modal-icon"></i> 时间</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${emails}" var="email">
					<tr class="unread readSign">
						<td class="check-mail">
							<input type="checkbox" name="message" value="${email.key.emailId}" class="i-checks">
						</td>
						<c:choose>
							<c:when test="${emailType.equals('收件箱')}">
								<td class="mail-ontact">
									<a href="${ctx}/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=${emailType}">${email.key.sender.name}</a>
								</td>
							</c:when>
							<c:when test="${emailType.equals('垃圾箱')}">
								<td class="mail-ontact">
									<a href="${ctx}/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=${emailType}">${email.key.sender.name}</a>
								</td>
								<td class="mail-ontact">
									<a href="${ctx}/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=${emailType}">${email.key.receiver.name}</a>
								</td>
							</c:when>
							<c:otherwise>
								<td class="mail-ontact">
									<a href="${ctx}/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=${emailType}">${email.key.receiver.name}</a>
								</td>
							</c:otherwise>
						</c:choose>
						<td class="mail-subject">
							<span class="readState label label-warning pull-right">${email.value}</span>
							<a href="${ctx }/admin/email/seeDetail/detail?emailId=${email.key.emailId}&detailType=${emailType}">${email.key.emailTheme}</a>
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
	<!-- end: 邮件信息 -->

<%-- ACat i lele --%>