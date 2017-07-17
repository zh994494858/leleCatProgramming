/**
 * iframe 高度自适应
 * Created by ACat on 2016/11/2.
 * ACat i lele.
 */

function reinitIframe(){
	var iframes = document.getElementsByClassName("auto-iframe");
	try{
		// var bHeight = iframe.contentWindow.document.body.scrollHeight;
		// var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		// var height = Math.max(bHeight, dHeight);

		for (var i = 0; i < iframes.length; i++) {
			var height = iframes[i].contentWindow.document.getElementById("main-body").scrollHeight;
			iframes[i].height = height;
		}
	}catch (ex){}
}

if (typeof _AUTO_IFRAME_INTERVAL == "number") {
	window.clearInterval(_AUTO_IFRAME_INTERVAL);
}

_AUTO_IFRAME_INTERVAL = window.setInterval("reinitIframe()", 200);