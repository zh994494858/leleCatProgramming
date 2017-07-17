<%@ tag description="用户信息栏邮件控件" pageEncoding="UTF-8" %>

<%@ attribute name="ctx" 		description="页面相对地址"%>
<%--用户登陆上后，把该用户的所有在收件箱里的未读邮件的数量显示在邮件栏--%>
<script type="text/javascript">

	$(document).ready(function () {
		var accountId = ${sessionScope.account.id};

		$.ajax({
			type:'post',
			url:'${ctx}/admin/email/inboxEmails/count',
			contentType:"application/json",
			dataType:'JSON',
			data:JSON.stringify({"accountId":accountId}),
			success:function (unreadEmailsCountFromInbox,status) {

				//如果收件箱中没有未读邮件，则不显示提醒的数量和新邮件的数量
				//若有新邮件则将邮件提醒显示在页面上
				if(unreadEmailsCountFromInbox.toString() == "0"){
					$(".unreadCount").html("");
					$(".top").html("");
				}else {
					$(".unreadCount").html(unreadEmailsCountFromInbox);
				}
			},
			error:function () {
				alert("error");
			},
			fail:function () {
				alert("fail");
			},
			cache:true,
		}).done(function (unreadEmailsCountFromInbox) {
			console.log(unreadEmailsCountFromInbox)
		});

		$(".seeAll").click(function () {
			$(".unreadCount").html("");
			$(".top").html("");
		});
	});

</script>

<li class="dropdown hover-line">
	<a href="#" data-toggle="dropdown">
		<i class="fa-envelope-o"></i>
		<span class="badge badge-green unreadCount"></span>
	</a>

	<ul class="dropdown-menu messages">
		<li class="top">
			<p class="small">
				<%--<a href="#" class="pull-right">全部标记为已读</a>--%>
				您有 <strong class="unreadCount">3</strong> 封新邮件.
			</p>
		</li>
		<%--如果收件箱里的未读邮件数量太多，罗列出来的话页面太乱，省去--%>
		<%--<li>
			<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
				<!-- class="active" 该信息未读 -->
				<li class="active">
					<a href="#">
						<span class="line">
							<strong>乐乐</strong>
							<span class="light small">- 1分钟前</span>
						</span>

						<span class="line desc small">
							要加油工作 :)
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="line">
							<strong>坤硕</strong>
							<span class="light small">- 昨天</span>
						</span>

						<span class="line desc small">
							我发现学堂模块HTML课程里第3节课程的动画不能播放.
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="line">
							<strong>冀琛</strong>
							<span class="light small">- 2天前</span>
						</span>

						<span class="line desc small">
							JavaScript课程是不是要在CSS课程完成后才能学习?
						</span>
					</a>
				</li>
				<li class="active">
					<a href="#">
						<span class="line">
							子恒
							<span class="light small">- 1周前</span>
						</span>

						<span class="line desc small">
							我有一个新的游戏构思.
						</span>
					</a>
				</li>
				<li class="active">
					<a href="#">
						<span class="line">
							<strong>怀雨</strong>
							<span class="light small">- yesterday</span>
						</span>

						<span class="line desc small">
							This ain’t our first item, it is the best of the rest.
						</span>
					</a>
				</li>
				<li>
					<a href="#">
						<span class="line">
							<strong>张欢</strong>
							<span class="light small">- 2周前</span>
						</span>

						<span class="line desc small">
							据市场调研我们的网站非常受到小孩子们的欢迎!
						</span>
					</a>
				</li>
			</ul>
		</li>--%>

		<li class="external">
			<a href="javascript:void(0);" onclick="$('#index-iframe')[0].src='${ctx}/admin/email/inbox/list'" class="seeAll">
				<span>查看所有邮件</span>
				<i class="fa-link-ext"></i>
			</a>
		</li>
	</ul>
</li>

<%-- ACat i lele --%>