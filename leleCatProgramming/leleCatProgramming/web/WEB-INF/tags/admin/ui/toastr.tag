
<%@ tag description="用户登录通知标签" pageEncoding="UTF-8" %>

<%@ attribute name="timeOut" description="推迟显示, 单位ms" %>
<%@ attribute name="name" %>
<%@ attribute name="ctx"%>

<script type="text/javascript">

	jQuery(document).ready(function($)
	{
		// 通知
		setTimeout(function()
		{
			var opts = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-right toast-default",
				"toastClass": "black",
				"onclick": null,
				"showDuration": "100",
				"hideDuration": "1000",
				"timeOut": "5000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
			toastr.info("登录成功, 开始新的工作.", "${name}", opts);
		}, ${timeOut != null ? timeOut : 3000});
	});

</script>

<script src="${ctx}/admin/assets/js/toastr/toastr.min.js"></script>

<%-- ACat i lele --%>