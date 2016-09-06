<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 页面返回的JSON,ID不允许修改,必须固定ID：mqif_json -->
<div id="mqif_json">${returnInfo}</div>

<!-- 页面返回的JS代码,ID不允许修改,必须固定mqif_js -->
<script id="mqif_js">
$(".home-iframe").css("height",$(this).height()-100);
</script>
<!-- 页面返回的HTML代码,ID不允许修改,必须固定ID：mqif_html -->
<div id="mqif_html">
</div>