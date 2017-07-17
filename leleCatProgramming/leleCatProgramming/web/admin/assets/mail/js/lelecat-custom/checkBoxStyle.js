/**
 * Created by 王冀琛
 * on 2016/11/28.
 * 实现已读和未读的状态的改变
 * 界面上已读和未读行样式有差别
 */
$(function () {
	$("#labelState").click(function () {
		var em = "";
		$("input:checkbox[name='message']:checked").each(function () {
			if($(this).parent().parent().parent().hasClass("unread")){
				$(this).parent().parent().parent().removeClass("unread");
			}else {
				$(this).parent().parent().parent().addClass("unread");
			}
		});
	})
})