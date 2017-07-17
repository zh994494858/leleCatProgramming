/**
 * Created by 王冀琛
 * on 2016/11/28.
 * 实现在搜索框中输入某邮件的主题，部分匹配到页面中
 */
$(function(){
	$("#search").click(function(){
		$.ajax({
			type: 'GET',//提交方式 post 或者get
			url: "${ctx}/adminEmail/list",//提交到那里 后他的服务
			data: document.getElementById("searchContent").val().serialize(),//提交的参数
			success:function(msg){
				alert("msg");
			}
		});

	});
});