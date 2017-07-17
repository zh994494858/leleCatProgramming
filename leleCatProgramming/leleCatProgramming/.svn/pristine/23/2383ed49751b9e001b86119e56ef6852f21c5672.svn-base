/**
 *
 *表格中 上移&下移 相邻的两行
 *
 *需要在上移下移按钮中 添加class值 up或down
 *
 * Created by 王冀琛
 *
 * on 2016/11/10.
 *
 */

$(function () {
	//上移
	var $up = $(".up");
	$up.click(function () {
		var $tr = $(this).parents("tr");

		var curIndex = $tr.index() + 1;
		if (curIndex > 1) {

			//得到当前行和上一行input中的值
			var prevInput = $tr.prev().find("input.sequence");
			var currInput = $tr.find("input.sequence");

			//修改当前行和上一行input中的值
			prevInput.val(curIndex);
			currInput.val(curIndex - 1);

			//设置淡入淡出样式
			$tr.prev().fadeOut().fadeIn();
			$tr.fadeOut().fadeIn();
			//交换当前行和上一行
			$tr.prev().before($tr);
		}
	});

	//下移
	var $down = $(".down");
	$down.click(function () {
		var $tr = $(this).parents("tr");

		var curIndex = $tr.index() + 1;
		if (curIndex < $down.length) {

			//得到当前行和下一行input中的值
			var nextInput = $tr.next().find("input.sequence");
			var currInput = $tr.find("input.sequence");

			//修改当前行和下一行input中的值
			nextInput.val(curIndex);
			currInput.val(curIndex + 1);

			//设置淡入淡出样式
			$tr.next().fadeOut().fadeIn();
			$tr.fadeOut().fadeIn();
			//交换当前行和下一行
			$tr.next().after($tr);
		}
	})
});