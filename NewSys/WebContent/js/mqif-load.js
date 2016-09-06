//禁止网页被嵌入
if (window != top) {
	top.location.href = window.location.href;
}
//去除Backspace键
$(document).keydown(function (e) {
	var doPrevent;
	if (e.keyCode == 8) {//注：8为Backspace键，13为Enter键
		var d = e.srcElement || e.target;
		if (d.tagName.toUpperCase()=='INPUT'||d.tagName.toUpperCase()=='TEXTAREA') {
			doPrevent = d.readOnly || d.disabled;
		}else{
			doPrevent = true;
		}
	}else{
		doPrevent = false;
	}
	if (doPrevent){
		e.preventDefault();
	}
});
//初始化首页
if(null!=$.cookie('URL')){
	mqif_GoUrl($.cookie('URL'));
}else{
	mqif_GoUrl("home.action");
};

//退出系统
$('#exitsystem').click(function() {
	var submit = function (v, h, f) {
	    if (v == 'ok'){
	    	location.replace('exit.action');
	    }
	    return true;
	};
	$.jBox.confirm("你确定要退出系统吗？", "退出提示", submit);
});

//我的帐户容器
$("#myuser").hover(function(){
	$("#index_my").css("display","block");
},function(){
	$("#index_my").css("display","none");
});
$(document).ready(function() {
	var accordion_head = $('.accordion > li > a'), accordion_body = $('.accordion li > .sub-menu');
	if(null!=$.cookie('MENUID')){
		$(".accordion >li").each(function(){
			if($.cookie('MENUID')==$(this).attr("id")){
				$("#"+$(this).attr("id")+" > a").addClass('active').next().slideDown('normal');
			}
		});
	}else{
		accordion_head.first().addClass('active').next().slideDown('normal');
	}
	accordion_head.on('click', function(event) {
		event.preventDefault();
		if ($(this).attr('class') != 'active') {
			$.cookie("MENUID",$(this).parent().attr("id"),{path:'/'});
			accordion_body.slideUp('normal');
			$(this).next().stop(true, true).slideToggle('normal');
			accordion_head.removeClass('active');
			$(this).addClass('active');
		}else{
			$(this).next().stop(true, true).slideToggle('normal');
			accordion_head.removeClass('active');
		}
	});
});

$(window).resize(function() {
	$("#menu").css("height",$(this).height()-60);
	$(".home-iframe").css("height",$(this).height()-100);
});
$(window).scroll(function(){
	var stop = $(window).scrollTop();
	if(stop >= 0){$("#menu").css('position','fixed').css('top','60px');}
	if(stop < 0 && stop >0){$("#menu").css('position','fixed').css('top',-stop+'px');}
	if(stop <= 0){$("#menu").css('position','fixed').css('top','60px');}

	if(stop >= 0){$("#header").css('position','fixed').css('top','0');}
	if(stop < 0 && stop >0){$("#header").css('position','fixed').css('top',-stop+'px');}
	if(stop <= 0){$("#header").css('position','fixed').css('top','0');}
});
$("#menu").css("height",$(this).height()-60);
$(".home-iframe").css("height",$(this).height()-100);

