<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- 页面返回的JSON,ID不允许修改,必须固定ID：mqif_json -->
<div id="mqif_json">${returnInfo}</div>

<!-- 页面返回的JS代码,ID不允许修改,必须固定mqif_js -->
<script id="mqif_js">
	$(function() {	
		$('.edui-body-container').css('width', '100%');
		$('#edui_fixedlayer').remove();
		$('#global-zeroclipboard-html-bridge').remove();
		
		var validJs = $('.validForma').Validform({
			tiptype : 3,
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 32
				}
			},
			callback : function(form) {
				mqif_Post($('#edit-form'));
				return false;
			},
			label : '.label',
			showAllError : true,
			datatype : {
				'zh1-6' : '/^[\\u4E00-\\u9FA5\\uf900-\\ufa2d]{1,6}$/'
				
			},
		});
		validJs.tipmsg.w['zh1-6'] = '请输入1到6个中文字符！';
	});
	//TAB页签js
	TabBlock.init();
	$('.datepicker').Zebra_DatePicker();
	
	$("input[name='memChk']").click(function() {
		var pre = $(this).val();
		if ($(this).is(":checked")) {
			$("input[value^='"+pre+"']").attr("checked", true);
		} else {
			$("input[value^='"+pre+"']").attr("checked", false);
		}
	});
</script>

<!-- 页面返回的HTML代码,ID不允许修改,必须固定ID：mqif_html -->
<div id="mqif_html">
	<form class="validForma" id="edit-form" action="user/save.action<s:if test="userInfo.uuid!=null">?id=${userInfo.uuid}</s:if>" method="post">
		<div class="box-title">
			<h3><a href="javascript:mqif_GoUrl('user/list.action');"><i class="fa fa-user"></i>平台用户管理</a></h3>
			<h3><i class="fa fa-chevron-right"></i></h3>
			<s:if test="userInfo.uuid!=null">
				<h3><i class="fa fa-edit"></i>修改(${userInfo.number})</h3>
			</s:if>
			<s:else>
				<h3><i class="fa fa-pencil"></i>添加平台用户</h3>
			</s:else>
		</div>
		<ul class="edit">
			<li>
				<label class="label"> <span class="need">*</span>用户名称：</label>
				<input type="text" value="${userInfo.number}" name="userInfo.number" class="inputxt"  >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>用户密码：</label>
				<input type="password" value="${userInfo.password}" name="userInfo.password" class="inputxt" plugin="passwordStrength" datatype="*6-32" errormsg="密码至少6个字符,最多32个字符！" >
				<span class="Validform_checktip"></span>
			</li>
			<li>
				<label class="label">
				<span class="need"></span>
				</label>
				<div class="passwordStrength"><span>弱</span><span>中</span><span class="last">强</span></div>
			</li>
				
			<li>
				<label class="label"><span class="need">*</span>确认密码：</label>
				<input type="password" value="${userInfo.password}" name="repassword" class="inputxt" recheck="userInfo.password" datatype="*6-32" errormsg="两次输入的密码不一致！" >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>用户状态：</label>
				<s:select list="stateMap" listKey="key" listValue="value" name="userInfo.status" headerValue="%{userInfo.status}"/> 
				<span class="Validform_checktip"></span>
			</li>
			
		<%-- 	<li>
				<label class="label"><span class="need">*</span>用户等级：</label>
				<s:select list="lvMap" listKey="key" listValue="value" name="userInfo.lv" headerValue="%{userInfo.lv}"/> 
				<span class="Validform_checktip"></span>
			</li> --%>
			
			<li class="footerNav" style="background:#f4f4f4">
				<label class="label">扩展信息（选填）</label>
			</li>
			<li>
				<label class="label"><span class="need">*</span>用户昵称：</label>
				<input type="text" value="${userInfo.userexinfo.nickname}" name="userInfo.userexinfo.nickname" class="inputxt" datatype="s0-20" >
				<span class="Validform_checktip"></span>
			</li>
			
			 <li>
				<label class="label"><span class="need">*</span>真实姓名：</label>
				<input type="text" value="${userInfo.userexinfo.realname}" name="userInfo.userexinfo.realname" class="inputxt" datatype="s0-0|zh2-10" >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>身份证号码：</label>
				<input type="text" value="${userInfo.userexinfo.idnum}" name="userInfo.userexinfo.idnum" class="inputxt" datatype="s0-0|s18-18" errormsg="身份证号有误！" >
				<span class="Validform_checktip"></span>
			</li>
		
			<li>
				<label class="label"><span class="need">*</span>生日：</label>
				<input type="text" value="${userInfo.userexinfo.birthday}" name="userInfo.userexinfo.birthday" class="inputxt datepicker" >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>性别：</label>
				<input type="radio" value="1" name="userInfo.userexinfo.sexenum" id="sex_man" <s:if test="userInfo.userexinfo.sexenum == 1">checked="checked"</s:if>/>
				<label for="isdefault_true">男</label> 
				<input type="radio" value="2" name="userInfo.userexinfo.sexenum" id="sex_woman" <s:if test="userInfo.userexinfo.sexenum == 2">checked="checked</s:if>/>
				<label for="isdefault_false">女</label> 
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>手机号码：</label>
				<input type="text" value="${userInfo.userexinfo.mobile}" name="userInfo.userexinfo.mobile" class="inputxt" datatype="s0-0|m" >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>QQ号码：</label>
				<input type="text" value="${userInfo.userexinfo.qq}" name="userInfo.userexinfo.qq" class="inputxt" datatype="s0-0|n5-12" >
				<span class="Validform_checktip"></span>
			</li>
			
			<li>
				<label class="label"><span class="need">*</span>邮箱地址：</label>
				<input type="text" value="${userInfo.userexinfo.email}" name="userInfo.userexinfo.email" class="inputxt" datatype="s0-0|e" >
				<span class="Validform_checktip"></span> 
			</li>
			
			<li class="footerNav"><label class="label"></label>
				<button type="submit" class="btn-primary">保存</button>
				<button type="reset">重置</button>
			</li>
		</ul>
	</form>
</div>
