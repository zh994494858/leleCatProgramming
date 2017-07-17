<%@ tag description="内容页头" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="title"	description="页面标题" %>
<%@ attribute name="ctx" type="java.lang.String"%>
<%@ attribute name="emailType" type="java.lang.String"%>

<!-- start: 子页面的页头 -->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">


	<title> ${title}</title>
	<meta name="keywords" content="">
	<meta name="description" content="">

	<script src="${ctx}/admin/assets/mail/js/jquery.min.js?v=2.1.4"></script>
	<link rel="shortcut icon" href="favicon.ico">
	<link href="${ctx}/admin/assets/mail/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/font-awesome.css?v=4.4.0" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/animate.css" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="${ctx}/admin/assets/mail/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/admin/assets/mail/css/plugins/markdown/bootstrap-markdown.min.css" />
	<link href="${ctx}/admin/assets/mail/css/style.css?v=4.1.0" rel="stylesheet">


	<script type="text/javascript">
		<c:choose>
		   <c:when test="${emailType.equals('发件箱')}">
				function moveToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/moveToDustbin/Outbox");
					document.getElementById("allEmail").submit();
				}
		</c:when>
		   <c:when test="${emailType.equals('收件箱')}">
				function moveToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/moveToDustbin/InboxMove");
					document.getElementById("allEmail").submit();
				}
		</c:when>
		   <c:when test="${emailType.equals('垃圾箱')}">
				function submitFormToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/dustbin/delete/downright");
					document.getElementById("allEmail").submit();
				}
		</c:when>
		   <c:when test="${emailType.equals('草稿箱')}">
				function moveToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/moveToDustbin/Drafts");
					document.getElementById("allEmail").submit();
				}
				function markAsRead() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/");
					documnet.getElementById("allEmail").submit();
				}
		</c:when>
		   <c:when test="${emailType.equals('草稿邮件信息')}">
				function submitFormToOutbox() {
					document.getElementById("emailContent").setAttribute("action","${ctx}/admin/email/fromDraftsToOutbox/moveAndInsert");
					document.getElementById("emailContent").submit();
				}
				function moveToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/moveToDustbin/Dustbin?message=${email.emailId}");
					document.getElementById("allEmail").submit();
				}
		</c:when>
		   <c:when test="${emailType.equals('邮件信息')}">
				function moveToDustbin() {
					document.getElementById("allEmail").setAttribute("action","${ctx}/admin/email/moveToDustbin/Dustbin?message=${email.emailId}");
					document.getElementById("allEmail").submit();
				}
		</c:when>
		</c:choose>
	</script>
	<script type="text/javascript">
		$(document).ready(function () {

			//当页面一加载就把每个邮箱的数量查找出来，放到每个邮箱的后面
			$.ajax({
				type:"POST",
				url:'${ctx}/admin/email/emailbox/EmailCount',
				dataType:'JSON',
				success:function (emailCounts,status) {
					$("#inboxCount").text(emailCounts[0]);
					$("#outboxCount").text(emailCounts[1]);
					$("#draftsCount").text(emailCounts[2]);
					$("#dustbinCount").text(emailCounts[3]);
				},
				error:function () {
					alert("error!")
				}
			})

			//当页面一加载的时候就把未读的邮件表示在该邮件主题的后面
			$(".readState").each(function () {
				if ($(this).html() != "未读"){
					$(this).html("");
				}
			})

			//点击标记为已读的按钮后，将邮件标记为已读
			$("#labelState").click(function () {
				var emailIds = [];
				var spans = [];
				$("input:checkbox[name='message']:checked").each(function () {
					emailIds.push($(this).val());
					spans.push($(this).parents(".readSign").find(".readState"));
				})

				$.ajax({
					type:'POST',
					url:'${ctx}/admin/email/mark/read',
					contentType:"application/json",
					data:JSON.stringify({"emailIds":emailIds.toString()}),
					success:function (status) {
						for (var i = 0;i<spans.length;i++){
							spans[i].html("");
						}
					}.bind(this)
				});

				//将选中的邮件标为已读状态后，把选中的复选框的样式恢复到未选中状态
				$(".icheckbox_square-green").each(function () {
					$(this).removeClass("checked");
				})

			});

		})

	</script>

</head>
<!-- end: 子页面的页头 -->

<%-- ACat i lele --%>