<%@ tag description="新闻标签-大" pageEncoding="UTF-8" %>

<%@ attribute name="courseId"%>
<%@ attribute name="name"%>
<%@ attribute name="description"%>
<%@ attribute name="ctx"%>
<%@ attribute name="url" %>
<%@ attribute name="img_url"%>

<a href="${ctx}${url}">
	<div class="xe-widget xe-single-news">
		<div class="xe-image">
			<img src="${ctx}${img_url}" width="240px" height ="155px" />
			<span class="xe-gradient"></span>
		</div>

		<div class="xe-details">
			<h3>
				<a href="${ctx}${url}">${name}</a>
			</h3>
			<%--<h3>--%>
				<%--<a href="/admin/course/deleteCourse.jsp?courseId=${courseId}&courseName=${name}">删除课程</a>--%>
			<%--</h3>--%>
			<time>
				${description}
			</time>
		</div>
	</div>
</a>

<%-- ACat i lele --%>