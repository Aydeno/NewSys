<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script id="mqif_js">
function setCssIco(obj){
	$(".cssico >div").each(function(){
		$(this).removeClass("on");
	});
	$(obj).parent().addClass("on");
	$("#cssiconame").val($(obj).attr("title"));
};
function setPngIco(obj){
	$(".pngico >div").each(function(){
		$(this).removeClass("on");
	});
	$(obj).parent().addClass("on");
	$("#pngiconame").val($(obj).attr("title"));
};

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
<input type="text" value="1" name="selecttab" id="selecttab" readonly="readonly" style="display:none;">
<input type="text" value="" name="cssiconame" id="cssiconame" readonly="readonly" style="display:none;">
<input type="text" value="" name="pngiconame" id="pngiconame" readonly="readonly" style="display:none;">
<input type="text" value="" name="upicon" id="upicon" readonly="readonly" style="display:none;">
	<div class="div_nav">
		<ul>
			<li id="cssico" class="on"><a href="javascript:;" onclick="showTab(1);$('#selecttab').val('1');">系统矢量图标</a></li>
			<li id="pngico"><a href="javascript:;" onclick="showTab(2);$('#selecttab').val('2');">系统PNG图标</a></li>
			<li id="upico" class="right"><a href="javascript:;" onclick="showTab(3);$('#selecttab').val('3');">上传自定义图标</a></li>
		</ul>
	</div>
	<div class="cssico">
			<div class="mqif-ico-div"><i class="fa fa-adjust fa-3x fa-fw" title="adjust" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-adn fa-3x fa-fw" title="adn" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-align-center fa-3x fa-fw" title="align-center" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-align-justify fa-3x fa-fw" title="align-justify" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-align-left fa-3x fa-fw" title="align-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-align-right fa-3x fa-fw" title="align-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ambulance fa-3x fa-fw" title="ambulance" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-anchor fa-3x fa-fw" title="anchor" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-android fa-3x fa-fw" title="android" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angellist fa-3x fa-fw" title="angellist" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-double-down fa-3x fa-fw" title="angle-double-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-double-left fa-3x fa-fw" title="angle-double-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-double-right fa-3x fa-fw" title="angle-double-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-double-up fa-3x fa-fw" title="angle-double-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-down fa-3x fa-fw" title="angle-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-left fa-3x fa-fw" title="angle-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-right fa-3x fa-fw" title="angle-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-angle-up fa-3x fa-fw" title="angle-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-apple fa-3x fa-fw" title="apple" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-archive fa-3x fa-fw" title="archive" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-area-chart fa-3x fa-fw" title="area-chart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-down fa-3x fa-fw" title="arrow-circle-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-left fa-3x fa-fw" title="arrow-circle-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-o-down fa-3x fa-fw" title="arrow-circle-o-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-o-left fa-3x fa-fw" title="arrow-circle-o-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-o-right fa-3x fa-fw" title="arrow-circle-o-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-o-up fa-3x fa-fw" title="arrow-circle-o-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-right fa-3x fa-fw" title="arrow-circle-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-circle-up fa-3x fa-fw" title="arrow-circle-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-down fa-3x fa-fw" title="arrow-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-left fa-3x fa-fw" title="arrow-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-right fa-3x fa-fw" title="arrow-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrow-up fa-3x fa-fw" title="arrow-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrows fa-3x fa-fw" title="arrows" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrows-alt fa-3x fa-fw" title="arrows-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrows-h fa-3x fa-fw" title="arrows-h" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-arrows-v fa-3x fa-fw" title="arrows-v" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-asterisk fa-3x fa-fw" title="asterisk" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-at fa-3x fa-fw" title="at" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-automobile fa-3x fa-fw" title="automobile" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-backward fa-3x fa-fw" title="backward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ban fa-3x fa-fw" title="ban" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bank fa-3x fa-fw" title="bank" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bar-chart fa-3x fa-fw" title="bar-chart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bar-chart-o fa-3x fa-fw" title="bar-chart-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-barcode fa-3x fa-fw" title="barcode" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bars fa-3x fa-fw" title="bars" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bed fa-3x fa-fw" title="bed" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-beer fa-3x fa-fw" title="beer" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-behance fa-3x fa-fw" title="behance" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-behance-square fa-3x fa-fw" title="behance-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bell fa-3x fa-fw" title="bell" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bell-o fa-3x fa-fw" title="bell-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bell-slash fa-3x fa-fw" title="bell-slash" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bell-slash-o fa-3x fa-fw" title="bell-slash-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bicycle fa-3x fa-fw" title="bicycle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-binoculars fa-3x fa-fw" title="binoculars" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-birthday-cake fa-3x fa-fw" title="birthday-cake" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bitbucket fa-3x fa-fw" title="bitbucket" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bitbucket-square fa-3x fa-fw" title="bitbucket-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bitcoin fa-3x fa-fw" title="bitcoin" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bold fa-3x fa-fw" title="bold" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bolt fa-3x fa-fw" title="bolt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bomb fa-3x fa-fw" title="bomb" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-book fa-3x fa-fw" title="book" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bookmark fa-3x fa-fw" title="bookmark" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bookmark-o fa-3x fa-fw" title="bookmark-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-briefcase fa-3x fa-fw" title="briefcase" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-btc fa-3x fa-fw" title="btc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bug fa-3x fa-fw" title="bug" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-building fa-3x fa-fw" title="building" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-building-o fa-3x fa-fw" title="building-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bullhorn fa-3x fa-fw" title="bullhorn" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bullseye fa-3x fa-fw" title="bullseye" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-bus fa-3x fa-fw" title="bus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-buysellads fa-3x fa-fw" title="buysellads" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cab fa-3x fa-fw" title="cab" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-calculator fa-3x fa-fw" title="calculator" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-calendar fa-3x fa-fw" title="calendar" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-calendar-o fa-3x fa-fw" title="calendar-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-camera fa-3x fa-fw" title="camera" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-camera-retro fa-3x fa-fw" title="camera-retro" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-car fa-3x fa-fw" title="car" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-down fa-3x fa-fw" title="caret-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-left fa-3x fa-fw" title="caret-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-right fa-3x fa-fw" title="caret-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-square-o-down fa-3x fa-fw" title="caret-square-o-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-square-o-left fa-3x fa-fw" title="caret-square-o-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-square-o-right fa-3x fa-fw" title="caret-square-o-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-square-o-up fa-3x fa-fw" title="caret-square-o-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-caret-up fa-3x fa-fw" title="caret-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cart-arrow-down fa-3x fa-fw" title="cart-arrow-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cart-plus fa-3x fa-fw" title="cart-plus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc fa-3x fa-fw" title="cc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-amex fa-3x fa-fw" title="cc-amex" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-discover fa-3x fa-fw" title="cc-discover" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-mastercard fa-3x fa-fw" title="cc-mastercard" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-paypal fa-3x fa-fw" title="cc-paypal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-stripe fa-3x fa-fw" title="cc-stripe" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cc-visa fa-3x fa-fw" title="cc-visa" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-certificate fa-3x fa-fw" title="certificate" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chain fa-3x fa-fw" title="chain" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chain-broken fa-3x fa-fw" title="chain-broken" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-check fa-3x fa-fw" title="check" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-check-circle fa-3x fa-fw" title="check-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-check-circle-o fa-3x fa-fw" title="check-circle-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-check-square fa-3x fa-fw" title="check-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-check-square-o fa-3x fa-fw" title="check-square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-circle-down fa-3x fa-fw" title="chevron-circle-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-circle-left fa-3x fa-fw" title="chevron-circle-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-circle-right fa-3x fa-fw" title="chevron-circle-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-circle-up fa-3x fa-fw" title="chevron-circle-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-down fa-3x fa-fw" title="chevron-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-left fa-3x fa-fw" title="chevron-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-right fa-3x fa-fw" title="chevron-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-chevron-up fa-3x fa-fw" title="chevron-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-child fa-3x fa-fw" title="child" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-circle fa-3x fa-fw" title="circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-circle-o fa-3x fa-fw" title="circle-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-circle-o-notch fa-3x fa-fw" title="circle-o-notch" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-circle-thin fa-3x fa-fw" title="circle-thin" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-clipboard fa-3x fa-fw" title="clipboard" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-clock-o fa-3x fa-fw" title="clock-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-close fa-3x fa-fw" title="close" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cloud fa-3x fa-fw" title="cloud" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cloud-download fa-3x fa-fw" title="cloud-download" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cloud-upload fa-3x fa-fw" title="cloud-upload" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cny fa-3x fa-fw" title="cny" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-code fa-3x fa-fw" title="code" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-code-fork fa-3x fa-fw" title="code-fork" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-codepen fa-3x fa-fw" title="codepen" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-coffee fa-3x fa-fw" title="coffee" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cog fa-3x fa-fw" title="cog" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cogs fa-3x fa-fw" title="cogs" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-columns fa-3x fa-fw" title="columns" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-comment fa-3x fa-fw" title="comment" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-comment-o fa-3x fa-fw" title="comment-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-comments fa-3x fa-fw" title="comments" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-comments-o fa-3x fa-fw" title="comments-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-compass fa-3x fa-fw" title="compass" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-compress fa-3x fa-fw" title="compress" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-connectdevelop fa-3x fa-fw" title="connectdevelop" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-copy fa-3x fa-fw" title="copy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-copyright fa-3x fa-fw" title="copyright" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-credit-card fa-3x fa-fw" title="credit-card" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-crop fa-3x fa-fw" title="crop" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-crosshairs fa-3x fa-fw" title="crosshairs" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-css3 fa-3x fa-fw" title="css3" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cube fa-3x fa-fw" title="cube" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cubes fa-3x fa-fw" title="cubes" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cut fa-3x fa-fw" title="cut" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-cutlery fa-3x fa-fw" title="cutlery" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dashboard fa-3x fa-fw" title="dashboard" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dashcube fa-3x fa-fw" title="dashcube" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-database fa-3x fa-fw" title="database" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dedent fa-3x fa-fw" title="dedent" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-delicious fa-3x fa-fw" title="delicious" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-desktop fa-3x fa-fw" title="desktop" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-deviantart fa-3x fa-fw" title="deviantart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-diamond fa-3x fa-fw" title="diamond" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-digg fa-3x fa-fw" title="digg" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dollar fa-3x fa-fw" title="dollar" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dot-circle-o fa-3x fa-fw" title="dot-circle-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-download fa-3x fa-fw" title="download" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dribbble fa-3x fa-fw" title="dribbble" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-dropbox fa-3x fa-fw" title="dropbox" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-drupal fa-3x fa-fw" title="drupal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-edit fa-3x fa-fw" title="edit" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eject fa-3x fa-fw" title="eject" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ellipsis-h fa-3x fa-fw" title="ellipsis-h" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ellipsis-v fa-3x fa-fw" title="ellipsis-v" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-empire fa-3x fa-fw" title="empire" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-envelope fa-3x fa-fw" title="envelope" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-envelope-o fa-3x fa-fw" title="envelope-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-envelope-square fa-3x fa-fw" title="envelope-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eraser fa-3x fa-fw" title="eraser" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eur fa-3x fa-fw" title="eur" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-euro fa-3x fa-fw" title="euro" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-exchange fa-3x fa-fw" title="exchange" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-exclamation fa-3x fa-fw" title="exclamation" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-exclamation-circle fa-3x fa-fw" title="exclamation-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-exclamation-triangle fa-3x fa-fw" title="exclamation-triangle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-expand fa-3x fa-fw" title="expand" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-external-link fa-3x fa-fw" title="external-link" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-external-link-square fa-3x fa-fw" title="external-link-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eye fa-3x fa-fw" title="eye" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eye-slash fa-3x fa-fw" title="eye-slash" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-eyedropper fa-3x fa-fw" title="eyedropper" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-facebook fa-3x fa-fw" title="facebook" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-facebook-f fa-3x fa-fw" title="facebook-f" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-facebook-official fa-3x fa-fw" title="facebook-official" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-facebook-square fa-3x fa-fw" title="facebook-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fast-backward fa-3x fa-fw" title="fast-backward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fast-forward fa-3x fa-fw" title="fast-forward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fax fa-3x fa-fw" title="fax" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-female fa-3x fa-fw" title="female" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fighter-jet fa-3x fa-fw" title="fighter-jet" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file fa-3x fa-fw" title="file" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-archive-o fa-3x fa-fw" title="file-archive-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-audio-o fa-3x fa-fw" title="file-audio-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-code-o fa-3x fa-fw" title="file-code-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-excel-o fa-3x fa-fw" title="file-excel-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-image-o fa-3x fa-fw" title="file-image-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-movie-o fa-3x fa-fw" title="file-movie-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-o fa-3x fa-fw" title="file-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-pdf-o fa-3x fa-fw" title="file-pdf-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-photo-o fa-3x fa-fw" title="file-photo-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-picture-o fa-3x fa-fw" title="file-picture-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-powerpoint-o fa-3x fa-fw" title="file-powerpoint-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-sound-o fa-3x fa-fw" title="file-sound-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-text fa-3x fa-fw" title="file-text" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-text-o fa-3x fa-fw" title="file-text-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-video-o fa-3x fa-fw" title="file-video-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-word-o fa-3x fa-fw" title="file-word-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-file-zip-o fa-3x fa-fw" title="file-zip-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-files-o fa-3x fa-fw" title="files-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-film fa-3x fa-fw" title="film" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-filter fa-3x fa-fw" title="filter" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fire fa-3x fa-fw" title="fire" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-fire-extinguisher fa-3x fa-fw" title="fire-extinguisher" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flag fa-3x fa-fw" title="flag" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flag-checkered fa-3x fa-fw" title="flag-checkered" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flag-o fa-3x fa-fw" title="flag-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flash fa-3x fa-fw" title="flash" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flask fa-3x fa-fw" title="flask" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-flickr fa-3x fa-fw" title="flickr" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-floppy-o fa-3x fa-fw" title="floppy-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-folder fa-3x fa-fw" title="folder" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-folder-o fa-3x fa-fw" title="folder-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-folder-open fa-3x fa-fw" title="folder-open" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-folder-open-o fa-3x fa-fw" title="folder-open-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-font fa-3x fa-fw" title="font" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-forumbee fa-3x fa-fw" title="forumbee" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-forward fa-3x fa-fw" title="forward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-foursquare fa-3x fa-fw" title="foursquare" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-frown-o fa-3x fa-fw" title="frown-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-futbol-o fa-3x fa-fw" title="futbol-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gamepad fa-3x fa-fw" title="gamepad" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gavel fa-3x fa-fw" title="gavel" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gbp fa-3x fa-fw" title="gbp" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ge fa-3x fa-fw" title="ge" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gear fa-3x fa-fw" title="gear" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gears fa-3x fa-fw" title="gears" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-genderless fa-3x fa-fw" title="genderless" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gift fa-3x fa-fw" title="gift" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-git fa-3x fa-fw" title="git" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-git-square fa-3x fa-fw" title="git-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-github fa-3x fa-fw" title="github" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-github-alt fa-3x fa-fw" title="github-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-github-square fa-3x fa-fw" title="github-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gittip fa-3x fa-fw" title="gittip" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-glass fa-3x fa-fw" title="glass" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-globe fa-3x fa-fw" title="globe" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-google fa-3x fa-fw" title="google" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-google-plus fa-3x fa-fw" title="google-plus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-google-plus-square fa-3x fa-fw" title="google-plus-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-google-wallet fa-3x fa-fw" title="google-wallet" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-graduation-cap fa-3x fa-fw" title="graduation-cap" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-gratipay fa-3x fa-fw" title="gratipay" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-group fa-3x fa-fw" title="group" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-h-square fa-3x fa-fw" title="h-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hacker-news fa-3x fa-fw" title="hacker-news" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hand-o-down fa-3x fa-fw" title="hand-o-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hand-o-left fa-3x fa-fw" title="hand-o-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hand-o-right fa-3x fa-fw" title="hand-o-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hand-o-up fa-3x fa-fw" title="hand-o-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hdd-o fa-3x fa-fw" title="hdd-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-header fa-3x fa-fw" title="header" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-headphones fa-3x fa-fw" title="headphones" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-heart fa-3x fa-fw" title="heart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-heart-o fa-3x fa-fw" title="heart-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-heartbeat fa-3x fa-fw" title="heartbeat" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-history fa-3x fa-fw" title="history" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-home fa-3x fa-fw" title="home" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hospital-o fa-3x fa-fw" title="hospital-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-hotel fa-3x fa-fw" title="hotel" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-html5 fa-3x fa-fw" title="html5" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ils fa-3x fa-fw" title="ils" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-image fa-3x fa-fw" title="image" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-inbox fa-3x fa-fw" title="inbox" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-indent fa-3x fa-fw" title="indent" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-info fa-3x fa-fw" title="info" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-info-circle fa-3x fa-fw" title="info-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-inr fa-3x fa-fw" title="inr" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-instagram fa-3x fa-fw" title="instagram" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-institution fa-3x fa-fw" title="institution" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ioxhost fa-3x fa-fw" title="ioxhost" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-italic fa-3x fa-fw" title="italic" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-joomla fa-3x fa-fw" title="joomla" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-jpy fa-3x fa-fw" title="jpy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-jsfiddle fa-3x fa-fw" title="jsfiddle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-key fa-3x fa-fw" title="key" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-keyboard-o fa-3x fa-fw" title="keyboard-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-krw fa-3x fa-fw" title="krw" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-language fa-3x fa-fw" title="language" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-laptop fa-3x fa-fw" title="laptop" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-lastfm fa-3x fa-fw" title="lastfm" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-lastfm-square fa-3x fa-fw" title="lastfm-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-leaf fa-3x fa-fw" title="leaf" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-leanpub fa-3x fa-fw" title="leanpub" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-legal fa-3x fa-fw" title="legal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-lemon-o fa-3x fa-fw" title="lemon-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-level-down fa-3x fa-fw" title="level-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-level-up fa-3x fa-fw" title="level-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-life-bouy fa-3x fa-fw" title="life-bouy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-life-buoy fa-3x fa-fw" title="life-buoy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-life-ring fa-3x fa-fw" title="life-ring" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-life-saver fa-3x fa-fw" title="life-saver" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-lightbulb-o fa-3x fa-fw" title="lightbulb-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-line-chart fa-3x fa-fw" title="line-chart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-link fa-3x fa-fw" title="link" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-linkedin fa-3x fa-fw" title="linkedin" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-linkedin-square fa-3x fa-fw" title="linkedin-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-linux fa-3x fa-fw" title="linux" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-list fa-3x fa-fw" title="list" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-list-alt fa-3x fa-fw" title="list-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-list-ol fa-3x fa-fw" title="list-ol" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-list-ul fa-3x fa-fw" title="list-ul" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-location-arrow fa-3x fa-fw" title="location-arrow" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-lock fa-3x fa-fw" title="lock" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-long-arrow-down fa-3x fa-fw" title="long-arrow-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-long-arrow-left fa-3x fa-fw" title="long-arrow-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-long-arrow-right fa-3x fa-fw" title="long-arrow-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-long-arrow-up fa-3x fa-fw" title="long-arrow-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-magic fa-3x fa-fw" title="magic" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-magnet fa-3x fa-fw" title="magnet" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mail-forward fa-3x fa-fw" title="mail-forward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mail-reply fa-3x fa-fw" title="mail-reply" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mail-reply-all fa-3x fa-fw" title="mail-reply-all" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-male fa-3x fa-fw" title="male" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-map-marker fa-3x fa-fw" title="map-marker" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mars fa-3x fa-fw" title="mars" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mars-double fa-3x fa-fw" title="mars-double" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mars-stroke fa-3x fa-fw" title="mars-stroke" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mars-stroke-h fa-3x fa-fw" title="mars-stroke-h" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mars-stroke-v fa-3x fa-fw" title="mars-stroke-v" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-maxcdn fa-3x fa-fw" title="maxcdn" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-meanpath fa-3x fa-fw" title="meanpath" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-medium fa-3x fa-fw" title="medium" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-medkit fa-3x fa-fw" title="medkit" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-meh-o fa-3x fa-fw" title="meh-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mercury fa-3x fa-fw" title="mercury" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-microphone fa-3x fa-fw" title="microphone" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-microphone-slash fa-3x fa-fw" title="microphone-slash" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-minus fa-3x fa-fw" title="minus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-minus-circle fa-3x fa-fw" title="minus-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-minus-square fa-3x fa-fw" title="minus-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-minus-square-o fa-3x fa-fw" title="minus-square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mobile fa-3x fa-fw" title="mobile" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mobile-phone fa-3x fa-fw" title="mobile-phone" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-money fa-3x fa-fw" title="money" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-moon-o fa-3x fa-fw" title="moon-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-mortar-board fa-3x fa-fw" title="mortar-board" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-motorcycle fa-3x fa-fw" title="motorcycle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-music fa-3x fa-fw" title="music" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-navicon fa-3x fa-fw" title="navicon" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-neuter fa-3x fa-fw" title="neuter" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-newspaper-o fa-3x fa-fw" title="newspaper-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-openid fa-3x fa-fw" title="openid" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-outdent fa-3x fa-fw" title="outdent" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pagelines fa-3x fa-fw" title="pagelines" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paint-brush fa-3x fa-fw" title="paint-brush" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paper-plane fa-3x fa-fw" title="paper-plane" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paper-plane-o fa-3x fa-fw" title="paper-plane-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paperclip fa-3x fa-fw" title="paperclip" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paragraph fa-3x fa-fw" title="paragraph" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paste fa-3x fa-fw" title="paste" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pause fa-3x fa-fw" title="pause" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paw fa-3x fa-fw" title="paw" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-paypal fa-3x fa-fw" title="paypal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pencil fa-3x fa-fw" title="pencil" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pencil-square fa-3x fa-fw" title="pencil-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pencil-square-o fa-3x fa-fw" title="pencil-square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-phone fa-3x fa-fw" title="phone" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-phone-square fa-3x fa-fw" title="phone-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-photo fa-3x fa-fw" title="photo" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-picture-o fa-3x fa-fw" title="picture-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pie-chart fa-3x fa-fw" title="pie-chart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pied-piper fa-3x fa-fw" title="pied-piper" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pied-piper-alt fa-3x fa-fw" title="pied-piper-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pinterest fa-3x fa-fw" title="pinterest" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pinterest-p fa-3x fa-fw" title="pinterest-p" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-pinterest-square fa-3x fa-fw" title="pinterest-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plane fa-3x fa-fw" title="plane" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-play fa-3x fa-fw" title="play" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-play-circle fa-3x fa-fw" title="play-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-play-circle-o fa-3x fa-fw" title="play-circle-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plug fa-3x fa-fw" title="plug" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plus fa-3x fa-fw" title="plus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plus-circle fa-3x fa-fw" title="plus-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plus-square fa-3x fa-fw" title="plus-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-plus-square-o fa-3x fa-fw" title="plus-square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-power-off fa-3x fa-fw" title="power-off" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-print fa-3x fa-fw" title="print" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-puzzle-piece fa-3x fa-fw" title="puzzle-piece" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-qq fa-3x fa-fw" title="qq" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-qrcode fa-3x fa-fw" title="qrcode" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-question fa-3x fa-fw" title="question" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-question-circle fa-3x fa-fw" title="question-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-quote-left fa-3x fa-fw" title="quote-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-quote-right fa-3x fa-fw" title="quote-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ra fa-3x fa-fw" title="ra" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-random fa-3x fa-fw" title="random" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rebel fa-3x fa-fw" title="rebel" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-recycle fa-3x fa-fw" title="recycle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-reddit fa-3x fa-fw" title="reddit" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-reddit-square fa-3x fa-fw" title="reddit-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-refresh fa-3x fa-fw" title="refresh" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-remove fa-3x fa-fw" title="remove" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-renren fa-3x fa-fw" title="renren" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-reorder fa-3x fa-fw" title="reorder" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-repeat fa-3x fa-fw" title="repeat" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-reply fa-3x fa-fw" title="reply" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-reply-all fa-3x fa-fw" title="reply-all" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-retweet fa-3x fa-fw" title="retweet" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rmb fa-3x fa-fw" title="rmb" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-road fa-3x fa-fw" title="road" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rocket fa-3x fa-fw" title="rocket" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rotate-left fa-3x fa-fw" title="rotate-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rotate-right fa-3x fa-fw" title="rotate-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rouble fa-3x fa-fw" title="rouble" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rss fa-3x fa-fw" title="rss" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rss-square fa-3x fa-fw" title="rss-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rub fa-3x fa-fw" title="rub" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ruble fa-3x fa-fw" title="ruble" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-rupee fa-3x fa-fw" title="rupee" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-save fa-3x fa-fw" title="save" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-scissors fa-3x fa-fw" title="scissors" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-search fa-3x fa-fw" title="search" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-search-minus fa-3x fa-fw" title="search-minus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-search-plus fa-3x fa-fw" title="search-plus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sellsy fa-3x fa-fw" title="sellsy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-send fa-3x fa-fw" title="send" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-send-o fa-3x fa-fw" title="send-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-server fa-3x fa-fw" title="server" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-share fa-3x fa-fw" title="share" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-share-alt fa-3x fa-fw" title="share-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-share-alt-square fa-3x fa-fw" title="share-alt-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-share-square fa-3x fa-fw" title="share-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-share-square-o fa-3x fa-fw" title="share-square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-shekel fa-3x fa-fw" title="shekel" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sheqel fa-3x fa-fw" title="sheqel" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-shield fa-3x fa-fw" title="shield" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ship fa-3x fa-fw" title="ship" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-shirtsinbulk fa-3x fa-fw" title="shirtsinbulk" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-shopping-cart fa-3x fa-fw" title="shopping-cart" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sign-in fa-3x fa-fw" title="sign-in" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sign-out fa-3x fa-fw" title="sign-out" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-signal fa-3x fa-fw" title="signal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-simplybuilt fa-3x fa-fw" title="simplybuilt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sitemap fa-3x fa-fw" title="sitemap" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-skyatlas fa-3x fa-fw" title="skyatlas" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-skype fa-3x fa-fw" title="skype" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-slack fa-3x fa-fw" title="slack" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sliders fa-3x fa-fw" title="sliders" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-slideshare fa-3x fa-fw" title="slideshare" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-smile-o fa-3x fa-fw" title="smile-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-soccer-ball-o fa-3x fa-fw" title="soccer-ball-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort fa-3x fa-fw" title="sort" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-alpha-asc fa-3x fa-fw" title="sort-alpha-asc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-alpha-desc fa-3x fa-fw" title="sort-alpha-desc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-amount-asc fa-3x fa-fw" title="sort-amount-asc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-amount-desc fa-3x fa-fw" title="sort-amount-desc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-asc fa-3x fa-fw" title="sort-asc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-desc fa-3x fa-fw" title="sort-desc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-down fa-3x fa-fw" title="sort-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-numeric-asc fa-3x fa-fw" title="sort-numeric-asc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-numeric-desc fa-3x fa-fw" title="sort-numeric-desc" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sort-up fa-3x fa-fw" title="sort-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-soundcloud fa-3x fa-fw" title="soundcloud" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-space-shuttle fa-3x fa-fw" title="space-shuttle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-spinner fa-3x fa-fw" title="spinner" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-spoon fa-3x fa-fw" title="spoon" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-spotify fa-3x fa-fw" title="spotify" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-square fa-3x fa-fw" title="square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-square-o fa-3x fa-fw" title="square-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stack-exchange fa-3x fa-fw" title="stack-exchange" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stack-overflow fa-3x fa-fw" title="stack-overflow" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star fa-3x fa-fw" title="star" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star-half fa-3x fa-fw" title="star-half" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star-half-empty fa-3x fa-fw" title="star-half-empty" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star-half-full fa-3x fa-fw" title="star-half-full" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star-half-o fa-3x fa-fw" title="star-half-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-star-o fa-3x fa-fw" title="star-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-steam fa-3x fa-fw" title="steam" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-steam-square fa-3x fa-fw" title="steam-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-step-backward fa-3x fa-fw" title="step-backward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-step-forward fa-3x fa-fw" title="step-forward" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stethoscope fa-3x fa-fw" title="stethoscope" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stop fa-3x fa-fw" title="stop" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-street-view fa-3x fa-fw" title="street-view" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-strikethrough fa-3x fa-fw" title="strikethrough" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stumbleupon fa-3x fa-fw" title="stumbleupon" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-stumbleupon-circle fa-3x fa-fw" title="stumbleupon-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-subscript fa-3x fa-fw" title="subscript" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-subway fa-3x fa-fw" title="subway" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-suitcase fa-3x fa-fw" title="suitcase" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-sun-o fa-3x fa-fw" title="sun-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-superscript fa-3x fa-fw" title="superscript" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-support fa-3x fa-fw" title="support" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-table fa-3x fa-fw" title="table" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tablet fa-3x fa-fw" title="tablet" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tachometer fa-3x fa-fw" title="tachometer" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tag fa-3x fa-fw" title="tag" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tags fa-3x fa-fw" title="tags" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tasks fa-3x fa-fw" title="tasks" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-taxi fa-3x fa-fw" title="taxi" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tencent-weibo fa-3x fa-fw" title="tencent-weibo" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-terminal fa-3x fa-fw" title="terminal" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-text-height fa-3x fa-fw" title="text-height" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-text-width fa-3x fa-fw" title="text-width" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-th fa-3x fa-fw" title="th" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-th-large fa-3x fa-fw" title="th-large" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-th-list fa-3x fa-fw" title="th-list" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-thumb-tack fa-3x fa-fw" title="thumb-tack" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-thumbs-down fa-3x fa-fw" title="thumbs-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-thumbs-o-down fa-3x fa-fw" title="thumbs-o-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-thumbs-o-up fa-3x fa-fw" title="thumbs-o-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-thumbs-up fa-3x fa-fw" title="thumbs-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-ticket fa-3x fa-fw" title="ticket" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-times fa-3x fa-fw" title="times" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-times-circle fa-3x fa-fw" title="times-circle" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-times-circle-o fa-3x fa-fw" title="times-circle-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tint fa-3x fa-fw" title="tint" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-down fa-3x fa-fw" title="toggle-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-left fa-3x fa-fw" title="toggle-left" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-off fa-3x fa-fw" title="toggle-off" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-on fa-3x fa-fw" title="toggle-on" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-right fa-3x fa-fw" title="toggle-right" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-toggle-up fa-3x fa-fw" title="toggle-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-train fa-3x fa-fw" title="train" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-transgender fa-3x fa-fw" title="transgender" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-transgender-alt fa-3x fa-fw" title="transgender-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-trash fa-3x fa-fw" title="trash" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-trash-o fa-3x fa-fw" title="trash-o" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tree fa-3x fa-fw" title="tree" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-trello fa-3x fa-fw" title="trello" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-trophy fa-3x fa-fw" title="trophy" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-truck fa-3x fa-fw" title="truck" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-try fa-3x fa-fw" title="try" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tty fa-3x fa-fw" title="tty" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tumblr fa-3x fa-fw" title="tumblr" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-tumblr-square fa-3x fa-fw" title="tumblr-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-turkish-lira fa-3x fa-fw" title="turkish-lira" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-twitch fa-3x fa-fw" title="twitch" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-twitter fa-3x fa-fw" title="twitter" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-twitter-square fa-3x fa-fw" title="twitter-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-umbrella fa-3x fa-fw" title="umbrella" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-underline fa-3x fa-fw" title="underline" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-undo fa-3x fa-fw" title="undo" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-university fa-3x fa-fw" title="university" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-unlink fa-3x fa-fw" title="unlink" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-unlock fa-3x fa-fw" title="unlock" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-unlock-alt fa-3x fa-fw" title="unlock-alt" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-unsorted fa-3x fa-fw" title="unsorted" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-upload fa-3x fa-fw" title="upload" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-usd fa-3x fa-fw" title="usd" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-user fa-3x fa-fw" title="user" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-user-md fa-3x fa-fw" title="user-md" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-user-plus fa-3x fa-fw" title="user-plus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-user-secret fa-3x fa-fw" title="user-secret" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-user-times fa-3x fa-fw" title="user-times" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-users fa-3x fa-fw" title="users" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-venus fa-3x fa-fw" title="venus" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-venus-double fa-3x fa-fw" title="venus-double" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-venus-mars fa-3x fa-fw" title="venus-mars" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-viacoin fa-3x fa-fw" title="viacoin" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-video-camera fa-3x fa-fw" title="video-camera" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-vimeo-square fa-3x fa-fw" title="vimeo-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-vine fa-3x fa-fw" title="vine" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-vk fa-3x fa-fw" title="vk" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-volume-down fa-3x fa-fw" title="volume-down" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-volume-off fa-3x fa-fw" title="volume-off" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-volume-up fa-3x fa-fw" title="volume-up" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-warning fa-3x fa-fw" title="warning" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-wechat fa-3x fa-fw" title="wechat" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-weibo fa-3x fa-fw" title="weibo" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-weixin fa-3x fa-fw" title="weixin" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-whatsapp fa-3x fa-fw" title="whatsapp" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-wheelchair fa-3x fa-fw" title="wheelchair" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-wifi fa-3x fa-fw" title="wifi" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-windows fa-3x fa-fw" title="windows" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-won fa-3x fa-fw" title="won" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-wordpress fa-3x fa-fw" title="wordpress" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-wrench fa-3x fa-fw" title="wrench" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-xing fa-3x fa-fw" title="xing" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-xing-square fa-3x fa-fw" title="xing-square" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-yahoo fa-3x fa-fw" title="yahoo" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-yelp fa-3x fa-fw" title="yelp" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-yen fa-3x fa-fw" title="yen" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-youtube fa-3x fa-fw" title="youtube" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-youtube-play fa-3x fa-fw" title="youtube-play" onclick="setCssIco(this);"></i></div>
			<div class="mqif-ico-div"><i class="fa fa-youtube-square fa-3x fa-fw" title="youtube-square" onclick="setCssIco(this);"></i></div>
	</div>
	<div class="pngico" style="display:none;">
		<s:iterator value="iconlist" id="pngicon">
			<div class="mqif-ico-div"><img src="${initParam.WebUrl}/<s:property value="#pngicon" />" title="<s:property value="#pngicon" />" onclick="setPngIco(this);"></div>
		</s:iterator>
	</div>
	<div class="upico" style="display:none;">
		<br>
		<img src="image/image.png" id="icoimage">
		<br>
		<label onclick="$('#file').click();">点击选择图片</label>
		<input size="200" type="file" id="file" style="display:none;" onchange="selectImage(this);"/>
	</div>
</div>

