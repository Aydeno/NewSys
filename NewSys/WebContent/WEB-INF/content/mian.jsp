<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:bean name="com.tools.TransTool" id="transTool" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${initParam.WebName}</title>
<link rel="stylesheet" href="css/css.css" type="text/css" />
<script type=text/javascript src="js/jquery-2.1.4.min.js"></script>
<script type=text/javascript src="js/jquery-migrate-1.2.1.min.js"></script>
<script type=text/javascript src="js/jquery.md5.js"></script>
<script type=text/javascript src="js/jquery.cookie.js"></script>
<script type=text/javascript src="js/Validform_v5.3.2_min.js"></script>
<script type=text/javascript src="js/passwordStrength-min.js"></script>
<script type=text/javascript src="js/datepicker.js"></script>
<script type=text/javascript src="jBox/jquery-box-2.3-server.js"></script>
<script type=text/javascript src="jBox/i18n/jquery-box-server-zh-CN.js"></script>
<script type=text/javascript src="js/modernizr.min.js"></script>
<script type=text/javascript src="js/jplist.min.js"></script>
<script type=text/javascript src="js/mqif-min.js"></script>
<script type=text/javascript src="js/mqif-pro.js"></script>
<script type=text/javascript src="js/jquery.dragsort-0.5.2.min.js"></script>
<script type=text/javascript src="js/geo.js"></script>
<script type=text/javascript src="js/jquery.lazyload.min.js"></script>
<link type="text/css" rel="stylesheet" href="jBox/Skins2/Green/jbox.css" />
<link type="text/css" rel="stylesheet" href="css/left_menu.css" />
<link type="text/css" rel="stylesheet" href="css/font-awesome-4.3.0/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="css/list_styles.min.css" />
<link type="text/css" rel="stylesheet" href="css/datepicker.css" />
<!-- UE编辑器引用开始 -->
<script type="text/javascript" src="editor/ueditor.config.js"></script>
<script type="text/javascript" src="editor/ueditor.all.min.js"></script>
<script type="text/javascript" src="editor/lang/zh-cn/zh-cn.js"></script>
<!-- UE编辑器引用结束 -->
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>

</head>
<body>
	<div id="header">
		<div class="logo">${initParam.WebName}</div>
		<a class="lefthidden" href="javascript:lefthidden();"><i id="lefthiddenIco" class="fa fa-angle-double-left fa-2x"></i> </a>
		<div class="topMenu">
			<div class="mymenu" onclick="javascript:mqif_GoUrl('home.action');">
				<i class="fa fa-home"></i>平台首页
			</div>
			<div class="mymenu" id="myuser">
				<i class="fa fa-user"></i>我的帐户
				<div id="index_my">
					<div class="myup"></div>
					<div class="myinfo">
						<ul>
							<li>欢 迎 您：${ctx.userinfo.userexinfo.nickname}</li>
							<li>帐号状态：<s:property value="#transTool.transUserState(ctx.userinfo.status)" /></li>
							<li></li>
							<li><a href="javascript:_editPwd('${ctx.userinfo.userexinfo.nickname}','${ctx.userinfo.number}');">修改密码</a>|<a href="#">修改资料</a></li>
						</ul>
						<img src="${ctx.userinfo.userexinfo.headimgurl}">
					</div>
				</div>
			</div>
			<div class="mymenu" id="exitsystem">
				<i class="fa fa-sign-out"></i>安全退出
			</div>
		</div>
	</div>
<div id="menu">
		<ul class="accordion">
			<li id="m1"><a href="#m1"><i class="fa fa-flag fa-lg"></i>平台管理</a>
					<ul class="sub-menu">
						<li><a href="javascript:mqif_GoUrl('user/list.action');"><em>01</em>用户管理 </a></li>
					</ul>
				</li>
			<li id="m3"><a href="#m3"><i class="fa fa-user fa-lg"></i>APP用户管理</a>
				<ul class="sub-menu">
					<li><a href="javascript:mqif_GoUrl('vmember/list.action');"><em>01</em>APP用户管理</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div id="content"></div>
	<script type=text/javascript src="js/mqif-load.js"></script>
</body>
</html>