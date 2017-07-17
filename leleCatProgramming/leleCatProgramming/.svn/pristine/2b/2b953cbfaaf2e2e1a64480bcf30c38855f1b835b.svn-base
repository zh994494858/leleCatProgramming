<%@ tag description="弹出框" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<%-- start: 参数 --%>
<%@ attribute name="emailType" type="java.lang.String"%>
<%@ attribute name="ctx" type="java.lang.String" %>
<%-- end: 参数 --%>

<c:choose>
	<c:when test="${emailType.equals('垃圾箱')}">
		<!-- start:提示确定删除邮件的弹出框 -->
		<div class="modal inmodal" id="delete" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h3 style="color: #c81828">确定将选中的邮件删除？</h3>
					</div>
					<div class="modal-footer">
						<button onclick="submitFormToDustbin()" type="button" class="btn btn-icon btn-success">确定</button>
						<button type="button" class="btn btn-info btn-icon" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!-- end:提示确定删除邮件的弹出框 -->
	</c:when>
	<c:when test="${emailType.equals('写信')}">
		<!-- start:提示确定删除邮件的弹出框 -->
		<div class="modal inmodal" id="saveToDrafts" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated flipInY">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<h3>该邮件已经存到草稿箱</h3>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-icon btn-success">确定</button>
					</div>
				</div>
			</div>
		</div>
		<!-- end:提示确定删除邮件的弹出框 -->
	</c:when>
</c:choose>

<%-- ACat i lele --%>