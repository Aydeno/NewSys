<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${initParam.WebName}</title>
<link rel="stylesheet" href="css/login.css" type="text/css" />
<script type=text/javascript src="js/jquery-1.7.2.min.js"></script>
<script type=text/javascript src="js/jquery.md5.js"></script>
<script type=text/javascript src="js/jquery.cookie.js"></script>
<script type=text/javascript src="jBox/jquery-box-2.3-server.js"></script>
<script type="text/javascript" src="jBox/i18n/jquery-box-server-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="jBox/Skins/Green/jbox.css" />
<link rel = "Shortcut Icon" href=image/favicon.ico> 
</head>
<body>
	<div id="login_content">
		<!-- <img src="image/login_right_icon.png" class="right-icon"> -->
		<div id="logo">${initParam.WebName}</div>
		<div id="login">
			<FORM id="loginform" method="post">
				<div class="control-group">
					<SPAN class="icon-user"></SPAN>
					<INPUT type="text" name="username" id="usernameInput" placeholder="请输入登陆帐号！" autocomplete="off" value="admin">
				</div>
				<div id="password" class="control-group">
					<SPAN class="icon-lock"></SPAN>
					<INPUT type="password" name="password" id="passwordInput" value="otoway.2015" placeholder="请输入登陆密码！" >
				</div>
				<s:if test="showcode">
					<div id="rand" class="control-group">
						<SPAN class="icon-lock"></SPAN>
						<INPUT type="text" name="code" id="codeInput" placeholder="请输入右侧验证码！" autocomplete="off">
						<img id="code" height="25px" border="0" src="" title="看不清点击这里换一张" style="cursor: pointer;">
					</div>
				</s:if>
				<div class="login-btn">
					<INPUT id="login-btn" value="登 录" type="button">
				</div>
			</FORM>
		</div>
		<div id="login-copyright">&copy;${initParam.Copyright}</div>
	</div>
	<SCRIPT>
		<s:if test="showcode">
			$('#code').attr('src', 'code.action?' + Math.random());
			$('#code').click(function() {
				$('#code').attr('src', 'code.action?' + Math.random());
			});
		</s:if>
		if (null != $.cookie("username")) {
			$('#usernameInput').val($.cookie("username"));
			$('#passwordInput').focus();
		} else {
			$('#usernameInput').focus();
		}
		if (window != top) {
			top.location.href = window.location.href;
		}
		$('#login-btn').click(function() {
			_submit();
		});

		$(function() {
			$('input:text:first').focus();
			var $inp = $('input');
			$inp.bind('keydown', function(e) {
				var key = e.which;
				if (key == 13) {
					e.preventDefault();
					var nxtIdx = $inp.index(this) + 1;
					$(":input:eq(" + nxtIdx + ")").focus();
					<s:if test="showcode">
					if (nxtIdx == 3) {
						_submit();
					}
					</s:if>
					<s:else>
					if (nxtIdx == 2) {
						_submit();
					}
					</s:else>
				}
			});
		});
		function jtip(str,focusInputId) {
			$.jBox.tip(str, 'error', {focusId:focusInputId,opacity:0.75,timeout:1000});
		};
		function jtipsuccess(str,focusInputId) {
			$.jBox.tip(str, 'success', {focusId:focusInputId,opacity:0.75,timeout:1000});
		};
		function _submit() {
			var username = $.trim($("#usernameInput").val());
			var password = $.trim($("#passwordInput").val());
			if(username==""){
				jtip('请输入用户名！','usernameInput');
				return;
			}
			if(password==""){
				jtip('请输入密码！','passwordInput');
				return;
			}
			<s:if test="showcode">
			var code = $.trim($("#codeInput").val());
			if(code==""){
				jtip('请输入验证码！','codeInput');
				return;
			}
			</s:if>
			$("#login-btn").attr("disabled", "disabled");
			$("#login-btn").val("登陆中...");
			password = $.md5($.trim($("#passwordInput").val()));
			$("#passwordInput").val(password);
			
			$.ajax({
				type : "POST",
				data : $('#loginform').serialize(),
				url : "gologin.action",
				cache : false,
				dataType : "json",
				timeout : 30000, //超时30秒
				beforeSend:function(){
					$.jBox.tip("正在登陆中，请稍候！", 'loading');
				},
				success:function(data) {
					$.jBox.closeTip();
					if (data.status) {
						jtipsuccess("登陆成功");
						location.replace('mian.action');
					} else {
						jtip(data.message);
						if (data.jsshowcode) {
							if ($('#code').length > 0) {
								$('#code').attr('src', 'code.action?' + Math.random());
							} else {
								location.reload();
							}
						}
						$("#login-btn").removeAttr("disabled");
						$("#login-btn").val("登陆");
						$("#passwordInput").val("");
					}
				},
				complete:function(XMLHttpRequest,status){
					if(status!='success'){
						$.jBox.closeTip();
						if(status=='timeout'){
							jtip("服务器连接超时！请重试！");
						}else{
							jtip("数据加载异常！请重试！");
						}
						$('#code').attr('src', 'code.action?' + Math.random());
						$("#login-btn").removeAttr("disabled");
						$("#login-btn").val("登陆");
					}
				}
			});
		};
	</SCRIPT>
</body>
</html>