<%@ tag description="显示左侧邮箱列表=" pageEncoding="UTF-8" %>

<%-- start: 参数 --%>
<%@ attribute name="inboxCount" type="java.lang.String" %>
<%@ attribute name="outboxCount" type="java.lang.String" %>
<%@ attribute name="draftsCount" type="java.lang.String" %>
<%@ attribute name="dustbinCount" type="java.lang.String" %>
<%@ attribute name="ctx" type="java.lang.String" %>
<%-- end: 参数 --%>

<!-- start: 显示邮箱信息 -->
<div class="ibox float-e-margins">
	<div class="ibox-content mailbox-content">
		<div class="file-manager">
			<a class="btn btn-block btn-success compose-mail" href="${ctx}/admin/mail/writeLatter.jsp"><i class="fa fa-pencil-square-o"></i> <strong>写信</strong></a>
			<div class="space-25"></div>
			<h5><strong>您的信箱</strong></h5>
			<ul class="folder-list m-b-md" style="padding: 0">
				<li>
					<a href="${ctx}/admin/email/inbox/list"> <i class="glyphicon glyphicon-download-alt"></i> <strong>收件箱</strong><span id="inboxCount" class="badge label-success unreadCount pull-right">${inboxCount}</span><%-- <span id="inboxCount" class="label label-warning pull-right">${inboxCount}</span>--%>
					</a>
				</li>
				<li>
					<a href="${ctx}/admin/email/outbox/list"> <i class="fa fa-paper-plane"></i> <strong>发件箱</strong> <span id="outboxCount" class="badge label-primary unreadCount pull-right">${outboxCount}</span></a>
				</li>
				<li>
					<a href="${ctx}/admin/email/drafts/list"> <i class="fa fa-file-text"></i> <strong>草稿箱</strong> <span id="draftsCount" class="badge label-info unreadCount pull-right">${draftsCount}</span>
					</a>
				</li>
				<li>
					<a href="${ctx}/admin/email/dustbin/list"> <i class="fa fa-trash"></i> <strong>垃圾箱</strong> <span id="dustbinCount" class="badge label-default unreadCount pull-right">${dustbinCount}</span></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
	<!-- end: 邮箱信息 -->
<!-- end: 用户显示以及账户功能菜单栏 -->

<%-- ACat i lele --%>