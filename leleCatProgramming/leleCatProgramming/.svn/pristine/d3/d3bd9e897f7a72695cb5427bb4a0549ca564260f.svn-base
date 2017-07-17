<%@ tag description="面板上的按钮如刷新、删除邮件" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- start: 参数 --%>
<%@ attribute name="emailType" type="java.lang.String"%>
<%@ attribute name="ctx" type="java.lang.String" %>
<%-- end: 参数 --%>

<!-- start: 显示按钮 -->
<div class="mail-tools tooltip-demo m-t-md">
	<div class="btn-group pull-right">
		<c:choose>
			<c:when test="${emailType.equals('收件箱')}">
				<a href="${ctx}/admin/email/inbox/list">
					<button class="btn btn-outline btn-success dim" type="button" data-toggle="tooltip" data-placement="left" title="刷新邮件列表">
						<i class="fa fa-refresh"></i>
					</button>
				</a>
				<a>
					<button class="btn btn-outline btn-info dim" id="labelState" type="button" data-toggle="tooltip" data-placement="left" title="标为已读">
						<i class="fa fa-eye"></i>
					</button>
				</a>
				<a href="#">
					<button onclick="moveToDustbin()" class="btn btn-outline btn-warning dim" type="button" data-toggle="tooltip" data-placement="left" title="标为垃圾邮件">
						<i class="fa fa-warning"></i>
					</button>
				</a>
				<a href="#">
					<button type="button" onclick="moveToDustbin()" class="btn btn-outline btn-danger dim" data-toggle="tooltip" data-placement="left" title="删除">
						<i class="fa fa-trash-o"></i>
					</button>
				</a>
			</c:when>
			<c:when test="${emailType.equals('发件箱') || emailType.equals('草稿箱')}">
				<c:choose>
					<c:when test="${emailType.equals('发件箱')}">
						<a href="${ctx}/admin/email/outbox/list">
							<button class="btn btn-outline btn-success dim" type="button" data-toggle="tooltip" data-placement="left" title="刷新邮件列表">
								<i class="fa fa-refresh"></i>
							</button>
						</a>
					</c:when>
					<c:when test="${emailType.equals('草稿箱')}">
						<a href="${ctx}/admin/email/drafts/list">
							<button class="btn btn-outline btn-success dim" type="button" data-toggle="tooltip" data-placement="left" title="刷新邮件列表">
								<i class="fa fa-refresh"></i>
							</button>
						</a>
					</c:when>
				</c:choose>
				<a href="#">
					<button onclick="moveToDustbin()" type="button" class="btn btn-outline btn-danger dim" data-toggle="tooltip" data-placement="left" title="删除">
						<i class="fa fa-trash-o"></i>
					</button>
				</a>
			</c:when>
			<c:when test="${emailType.equals('垃圾箱')}">
				<a href="${ctx}/admin/email/dustbin/list">
					<button class="btn btn-outline btn-success dim" type="button" data-toggle="tooltip" data-placement="left" title="刷新邮件列表">
						<i class="fa fa-refresh"></i>
					</button>
				</a>
				<a>
					<button id="labelState" class="btn btn-outline btn-info dim" type="button" data-toggle="tooltip" data-placement="left" title="标为已读">
						<i class="fa fa-eye"></i>
					</button>
				</a>
				<a href="#">
					<button type="button" class="btn btn-danger dim" data-toggle="modal" data-target="#delete" title="删除">
						<i class="fa fa-trash-o"></i>
					</button>
				</a>
			</c:when>
		</c:choose>
	</div>
</div>
	<!-- end: 按钮 -->
<!-- end: 用户显示以及账户功能菜单栏 -->

<%-- ACat i lele --%>