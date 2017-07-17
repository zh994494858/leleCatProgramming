<%@ tag description="" pageEncoding="UTF-8" %>

<%@ attribute name="description"%>

<div class="tab-content">

	<!-- start: 选择方向功能内容 -->
	<div class="tab-pane active panel">

		<div class="panel-heading">
			${description}
		</div>

		<div class="panel-body">

			<jsp:doBody/>

		</div>

	</div>
	<!-- end: 选择方向功能内容 -->
</div>

<%-- ACat i lele --%>