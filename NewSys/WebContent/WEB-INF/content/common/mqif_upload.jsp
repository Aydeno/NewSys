<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script id="mqif_js">

function selectImage(file) {
	if (!file.files || !file.files[0]) {
		return;
	}
	$('#upicon').attr('value','');
	var reader = new FileReader();
	reader.onload = function(evt) {
		var image = evt.target.result;
		var str = image.split(",");
		if(str.length!=2){
			$('#icoimage').attr('src','image/image.png');
			$.jBox.tip("图片文件格式错误！");
			return;
		}else{
			var hstr = str[0].split(";");
			if(hstr[0]!="data:image/png"&&hstr[0]!="data:image/jpeg"&&hstr[0]!="data:image/gif"){
				$('#icoimage').attr('src','image/image.png');
				$.jBox.tip("只能上传 png | gif | jpg 格式的图片！");
				return;
			}
		}
		$('#upicon').attr('value',evt.target.result);
		$('#icoimage').attr('src',evt.target.result);
	};
	reader.readAsDataURL(file.files[0]);
};
</script>
<div class="ico_center">
<input type="text" value="" name="upicon" id="upicon" readonly="readonly" style="display:none;">
	<div class="div_nav">
		<ul>
			<li id="upico" class="right on"><a href="javascript:;">上传图片</a></li>
		</ul>
	</div>
	<div class="upico">
		<br>
		<img src="image/image.png" id="icoimage">
		<br>
		<label onclick="$('#file').click();">点击选择图片</label>
		<input size="200" type="file" id="file" style="display:none;" onchange="selectImage(this);"/>
	</div>
</div>

