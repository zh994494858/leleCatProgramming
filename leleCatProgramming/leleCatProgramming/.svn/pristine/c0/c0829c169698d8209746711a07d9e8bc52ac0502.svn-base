<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="内容尾部" pageEncoding="UTF-8" %>
<%@ taglib prefix="email" tagdir="/WEB-INF/tags/admin/email" %>

<%@ attribute name="ctx" type="java.lang.String"%>
<%@ attribute name="emailType" type="java.lang.String"%>


<!-- start: 子页面的尾部 -->
<!-- 全局js -->
<script src="${ctx}/admin/assets/mail/js/bootstrap.min.js?v=3.3.6"></script>


<!-- 自定义js -->
<script src="${ctx}/admin/assets/mail/js/content.js?v=1.0.0"></script>


<!-- search -->
<script src="${ctx}/admin/assets/mail/js/lelecat-custom/searchByTheme.js"></script>

<!-- iCheck -->
<script src="${ctx}/admin/assets/mail/js/plugins/iCheck/icheck.min.js"></script>

<!--table-->
<script src="${ctx}/admin/assets/mail/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${ctx}/admin/assets/mail/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- simditor -->
<script type="text/javascript" src="${ctx}/admin/assets/mail/js/plugins/markdown/markdown.js"></script>
<script type="text/javascript" src="${ctx}/admin/assets/mail/js/plugins/markdown/to-markdown.js"></script>
<script type="text/javascript" src="${ctx}/admin/assets/mail/js/plugins/markdown/bootstrap-markdown.js"></script>
<script type="text/javascript" src="${ctx}/admin/assets/mail/js/plugins/markdown/bootstrap-markdown.zh.js"></script>

<script type="text/javascript">
	$(".read").click(function () {
		var emailId = $(this).parents(".unread").find(".i-checks").val();
		var readState = $(this).parents(".unread").find(".readState");
		$.ajax({
			type:'POST',
			url:'${ctx}/admin/email/mark/read',
			contentType:"application/json",
			data:JSON.stringify({"emailId":emailId.toString()}),
			success:function (status) {
				readState.html("");
			}.bind(this)
		});

	})
</script>

<script>
	$(document).ready(function () {
		$('.i-checks').iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});
	});
	var edit = function () {
		$('.click2edit').summernote({
			focus: true
		});
	};
	var save = function () {
		var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
		$('.click2edit').destroy();
	};

</script>
<script type="text/javascript">
	function submitFormToDrafts() {
		document.getElementById("emailContent").setAttribute("action","${ctx}/admin/email/writeEmail/addToDrafts");
		document.getElementById("emailContent").submit();
	}
</script>
<script type="text/javascript">
	function submitFormToOutbox() {
		document.getElementById("emailContent").setAttribute("action","${ctx}/admin/email/writeEmail/addToOutbox");
		document.getElementById("emailContent").submit();
	}
</script>

<script>
	$(document).ready(function () {
		$('.dataTables-example').dataTable();

		/* Init DataTables */
		var oTable = $('#editable').dataTable();

		/* Apply the jEditable handlers to the table */
		oTable.$('td').editable('../example_ajax.php', {
			"callback": function (sValue, y) {
				var aPos = oTable.fnGetPosition(this);
				oTable.fnUpdate(sValue, aPos[0], aPos[1]);
			},
			"submitdata": function (value, settings) {
				return {
					"row_id": this.parentNode.getAttribute('id'),
					"column": oTable.fnGetPosition(this)[2]
				};
			},

			"width": "90%",
			"height": "100%"
		});


	});

	function fnClickAddRow() {
		$('#editable').dataTable().fnAddData([
			"Custom row",
			"New row",
			"New row",
			"New row",
			"New row"]);

	}
</script>
<!-- end: 子页面的尾部 -->

<%-- ACat i lele --%>