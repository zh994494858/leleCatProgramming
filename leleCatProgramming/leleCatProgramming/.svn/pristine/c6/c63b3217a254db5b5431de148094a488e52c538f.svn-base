<%@ tag description="联系人控件, 配合chat使用" pageEncoding="UTF-8" %>

<!-- start: 通信部分 -->
<div id="chat" class="fixed">

	<script type="text/javascript">
		//  打开通信框的示例
		jQuery(document).ready(function($)
		{
			var $chat_conversation = $(".chat-conversation");

			$(".chat-group a").on('click', function(ev)
			{
				ev.preventDefault();

				$chat_conversation.toggleClass('is-open');

				$(".chat-conversation textarea").trigger('autosize.resize').focus();
			});

			$(".conversation-close").on('click', function(ev)
			{
				ev.preventDefault();
				$chat_conversation.removeClass('is-open');
			});
		});
	</script>

	<!-- start: 联系人面板 -->
	<div class="chat-inner">

		<h2 class="chat-header">
			<a href="#" class="chat-close" data-toggle="chat">
				<i class="fa-plus-circle rotate-45deg"></i>
			</a>

			通信
			<span class="badge badge-success is-hidden">0</span>
		</h2>

		<div class="chat-group">
			<strong>特别关注</strong>
			<!--
				class
					is-online	在线
					is-offline	离线
				排序: 在线 > 离线 > 按拼音排序
			-->
			<a href="#"><span class="user-status is-online"></span> <em>乐乐</em></a>
		</div>

		<div class="chat-group">
			<strong>学堂模块</strong>

			<a href="#"><span class="user-status is-online"></span> <em>苗怀雨</em></a>
			<a href="#"><span class="user-status is-offline"></span> <em>崔坤硕</em></a>
		</div>

		<div class="chat-group">
			<strong>实验室模块</strong>

			<a href="#"><span class="user-status is-online"></span> <em>王子恒</em></a>
			<a href="#"><span class="user-status is-online"></span> <em>张欢</em></a>
			<a href="#"><span class="user-status is-offline"></span> <em>王冀琛</em></a>
		</div>

	</div>
	<!-- end: 联系人面板 -->

	<!-- start: 通信窗口 -->
	<div class="chat-conversation">

		<div class="conversation-header">
			<a href="#" class="conversation-close">
				&times;
			</a>

			<span class="user-status is-online"></span>
			<span class="display-name">王子恒</span>
			<small>Online</small>
		</div>

		<ul class="conversation-body">
			<li>
				<span class="user">王子恒</span>
				<span class="time">09:01</span>
				<p>女神和她男朋友分手了!</p>
			</li>
			<li class="odd">
				<span class="user">我</span>
				<span class="time">09:04</span>
				<p>你的机会到了啊 :)</p>
			</li>
			<li class="odd">
				<span class="user">王子恒</span>
				<span class="time">09:27</span>
				<p>刚刚我去到她前面说, 你们分手了我终于有机会了!</p>
			</li>
			<li class="odd">
				<span class="user">王子恒</span>
				<span class="time">09:28</span>
				<p>结果她眼神复杂地看着我, 说, 那你去试试吧...</p>
			</li>
			<li>
				<span class="user">我</span>
				<span class="time">09:29</span>
				<p>哈哈!</p>
			</li>
		</ul>

		<div class="chat-textarea">
			<textarea class="form-control autogrow" placeholder="输入您的消息" style="resize: none;"></textarea>
		</div>

	</div>
	<!-- end: 通信窗口 -->

</div>
<!-- end: 通信部分 -->

<%-- ACat i lele --%>