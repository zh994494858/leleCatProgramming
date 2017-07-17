<%--
	图片剪裁
	图片剪裁

	Created by ACat.
	ACat i lele.
--%>

<%@ tag description="图片剪裁" pageEncoding="UTF-8" %>

<%@ attribute name="name"%>
<%@ attribute name="description"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="img_src"%>
<%@ attribute name="ctx"%>

<label class="col-sm-2 control-label" for="icon">${name}</label>
<div class="col-sm-10">
	<script type="text/javascript">
		jQuery(document).ready(function ($) {
			var preview_size = [145, 145],
				aspect_ratio = 1,

				$image = $(".img-container img"),
				$x = $("#img-1-x"),
				$y = $("#img-1-y"),
				$w = $("#img-1-w"),
				$h = $("#img-1-h");

			// Plugin Initialization
			$image.cropper({
				aspectRatio: aspect_ratio,
				preview: '#${id}',
				done: function (data) {

				}
			});
			// Preview Image Setup (based on defined crop width and height
			var preview_img_width = $("div.img-shade").width();
			$("#${id}").css({
				width: preview_img_width,
				height: preview_img_width
			});

			$("#crop-img").on('click', function (ev) {
				ev.preventDefault();
				window.open($(this).attr('href') + "?x=" + $x.text() + "&y=" + $y.text() + "&w=" + $w.text() + "&h=" + $h.text() + "&tw=" + preview_size[0] + "&th=" + preview_size[1]);
			});
		});
	</script>
	<div class="row">
		<div class="col-md-3">
			<label class="control-label">
				${description}
			</label>

			<br>
			<br>

			<button class="btn btn-info" id="icon">上传图片</button>

			<div style="margin-top: 20px;">

				<label class="control-label">预览</label>
				<br/>
				<div class="img-shade" style="background: #ffffff;">
					<div id="${id}" class="img-preview"></div>
				</div>
			</div>

		</div>
		<div class="col-md-9">

			<div class="img-container" style="margin-bottom: 0;">
				<img src="${ctx}${img_src}" class="img-responsive"/>
			</div>

		</div>

	</div>
</div>

<link rel="stylesheet" href="${ctx}/admin/assets/js/cropper/cropper.min.css">
<script src="${ctx}/admin/assets/js/cropper/cropper.min.js"></script>
<%-- ACat i lele --%>