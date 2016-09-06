function lefthidden() {
	$("#lefthiddenIco").removeClass('fa fa-angle-double-left fa-angle-double-right fa-2x');
	if($("#menu").css("left") == "0px"){
		$("#menu").animate({left:"-300px"});
		$("#content").animate({marginLeft:"10px"});
		$("#lefthiddenIco").addClass('fa fa-angle-double-right fa-2x');
	}else{
		$("#menu").animate({left:"0px"});
		$("#content").animate({marginLeft:"290px"});
		$("#lefthiddenIco").addClass('fa fa-angle-double-left fa-2x');
	}
};

function showTab(id) {
	$("#cssico").removeClass("on");
	$(".cssico").css("display", "none");
	$("#pngico").removeClass("on");
	$(".pngico").css("display", "none");
	$("#upico").removeClass("on");
	$(".upico").css("display", "none");
	if (id == 1) {
		$("#cssico").addClass("on");
		$(".cssico").css("display", "");
	} else if (id == 2) {
		$("#pngico").addClass("on");
		$(".pngico").css("display", "");
	} else if (id == 3) {
		$("#upico").addClass("on");
		$(".upico").css("display", "");
	}
};

function delTr(delbtn){
	$(delbtn).parents("tr").remove();
};


var TabBlock = {
		s: {
			animLen: 200
		},
		init: function() {
			TabBlock.bindUIActions();
			TabBlock.hideInactive();
		},
		bindUIActions: function() {
			$('.tabBlock-tabs').on('click', '.tabBlock-tab', function(){
				TabBlock.switchTab($(this));
			});
		},
		hideInactive: function() {
			var $tabBlocks = $('.tabBlock');
			$tabBlocks.each(function(i) {
				var 
				$tabBlock = $($tabBlocks[i]),
				$panes = $tabBlock.find('.tabBlock-pane'),
				$activeTab = $tabBlock.find('.tabBlock-tab.is-active');
				$panes.hide();
				$($panes[$activeTab.index()]).show();
			});
		},
		switchTab: function($tab) {
			var $context = $tab.closest('.tabBlock');
			if (!$tab.hasClass('is-active')) {
				$tab.siblings().removeClass('is-active');
				$tab.addClass('is-active');

				TabBlock.showPane($tab.index(), $context);
			}
		},
		showPane: function(i, $context) {
			var $panes = $context.find('.tabBlock-pane');
			$panes.slideUp(TabBlock.s.animLen);
			$($panes[i]).slideDown(TabBlock.s.animLen);
		}
};

function mqif_del(url,title,centent){
	var submit = function(v,h,f){
		if(v=='yes'){
			mqif_GoUrl(url);
		}
		return true;
	};
	$.jBox.warning(centent,title,submit);
};

function mqif_GoUrl(httpurl) {
	$('#edui_fixedlayer').remove();
	$('#global-zeroclipboard-html-bridge').remove();
	$('#content').html("");
	$.ajax({
		type : "GET",
		url : httpurl,
		cache : false,
		dataType : "html",
		timeout : 30000, //超时60秒
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if($(data).filter('#isnologin').length>0){
				var submit = function (v, h, f) {
					if (v == true){
						location.replace('login.action');
						return true;
					}
				};
				$.jBox.confirm('登陆超时,请返回首页重新进入!', "登陆超时提示", submit,{buttons:{'确定':true}});
				return;
			};
			var tmpjson = $.parseJSON($(data).filter('#mqif_json').html());
			if(tmpjson.status){
				if(!tmpjson.hasOwnProperty("message")){
					$.cookie("URL",httpurl,{path:'/'});
					$('#content').html($(data).filter('#mqif_html').html());
					eval($(data).filter('#mqif_js').html());
					return;
				}else{
					if(tmpjson.hasOwnProperty("button")){
						var submit = function (v, h, f) {
							for(var key in tmpjson.url){
								if (v == key){
									mqif_GoUrl(tmpjson.url[key]);
									return true;
								}
							}
						};
						$.jBox.confirm(tmpjson.message, "信息提示", submit, {buttons:tmpjson.button});
					}else{
						$.jBox.confirm(tmpjson.message, "信息提示", submit,{buttons:{'确定':true}});
						$.cookie("URL",httpurl,{path:'/'});
						$('#content').html($(data).filter('#mqif_html').html());
						eval($(data).filter('#mqif_js').html());
						return;
					}
				}
			}else{
				if(tmpjson.hasOwnProperty("button")){
					var submit = function (v, h, f) {
						for(var key in tmpjson.url){
							if (v == key){
								mqif_GoUrl(tmpjson.url[key]);
								return true;
							}
						}
					};
					$.jBox.confirm(tmpjson.message, "失败信息提示", submit, {buttons:tmpjson.button});
				}else{
					$.jBox.confirm(tmpjson.message, "失败信息提示", submit,{buttons:{'确定':true}});
				}
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};
function mqif_load_table(cookieName) {
	$(".list_table-tbl tbody").html($("#list_table_cache tbody").html());
 	$("#list_table").jplist({
		itemsBox: ".list_table-tbl tbody" 
		,itemPath: ".tbl-item" 
		,panelPath: ".jplist-panel"
		,storage: "localstorage"		
		,storageName: cookieName
		,effect: 'fade'
	});
}
function mqif_LoadMorePage(obj) {
	var httpurl = $(obj).attr("data-mqif-url");
	var page = $(obj).attr("data-mqif-page");
	$.ajax({
		type : "GET",
		url : httpurl+"?p="+page,
		cache : false,
		dataType : "html",
		timeout : 30000,
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			var tmpjson = $.parseJSON($(data).filter('#mqif_json').html());
			if(tmpjson.status){
				$("#list_table_cache tbody").append($(data).filter('#mqif_html').find('table#list_table_cache').find('tbody').html());
				mqif_load_table(httpurl.replace("/",""));
				if(tmpjson.issurpluspage){
					$("#loadMore").attr("data-mqif-page",tmpjson.nextpageno);
					$("#loadMore").show();
				}else{
					$("#loadMore").attr("data-mqif-page","");
					$("#loadMore").hide();
				}
			}else{
				$("#loadMore").attr("data-mqif-page","");
				$("#loadMore").hide();
				$.jBox.error("已经没有更多数据可以加载了!", '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_LoadMorePage_filter(obj) {
	var httpurl = $(obj).attr("data-mqif-url");
	var page = $(obj).attr("data-mqif-page");
	var shopid = $("#shopid").val();
	var dishcatnum = $("#dishcatnum").val();
	
	$.ajax({
		type : "GET",
		url : httpurl+"?p="+page+"&shopid="+shopid+"&dishcatnum="+dishcatnum,
		cache : false,
		dataType : "html",
		timeout : 30000,
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			var tmpjson = $.parseJSON($(data).filter('#mqif_json').html());
			$(".loadAjaxData").html("");
			$("#list_table_cache tbody").html("");
			if(tmpjson.status){
				$("#list_table_cache tbody").append($(data).filter('#mqif_html').find('table#list_table_cache').find('tbody').html());
				mqif_load_table(httpurl.replace("/",""));
				if(tmpjson.issurpluspage){
					$("#loadMore").attr("data-mqif-page",tmpjson.nextpageno);
					$("#loadMore").show();
				}else{
					$("#loadMore").attr("data-mqif-page","");
					$("#loadMore").hide();
				}
			}else{
				$("#loadMore").attr("data-mqif-page","");
				$("#loadMore").hide();
				$.jBox.error("已经没有更多数据可以加载了!", '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_UpIcon(iconimage,inputObj) {
	$.ajax({
		type : "POST",
		url : 'common/upicon.action',
		cache : false,
		data : {
			'iconimage':iconimage
		},
		dataType : "json",
		timeout : 180000, //超时180秒
		beforeSend:function(){
			$.jBox.tip("文件上传中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if(data.state=='SUCCESS'){
				$.jBox.tip("上传成功！");
				inputObj.attr('value','urlico:'+data.url);
			}else{
				inputObj.attr('value','');
				$.jBox.error("上传失败！"+unescape(data.state), '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_UpImage(iconimage,inputObj) {
	$.ajax({
		type : "POST",
		url : 'common/upicon.action',
		cache : false,
		data : {
			'iconimage':iconimage
		},
		dataType : "json",
		timeout : 180000, //超时180秒
		beforeSend:function(){
			$.jBox.tip("文件上传中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if(data.state=='SUCCESS'){
				$.jBox.tip("上传成功！");
				inputObj.attr('value',data.url);
			}else{
				inputObj.attr('value','');
				$.jBox.error("上传失败！"+unescape(data.state), '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_Post(form) {
	$.ajax({
		type : "POST",
		url : form.attr('action'),
		cache : false,
		data : form.serialize(),
		dataType : "html",
		timeout : 300000, //超时30秒
		beforeSend:function(){
			$.jBox.tip("数据提交中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if($(data).filter('#isnologin').length>0){
				var submit = function (v, h, f) {
					if (v == true){
						location.replace('login.action');
						return true;
					}
				};
				$.jBox.confirm('登陆超时,请返回首页重新进入!', "登陆超时提示", submit,{buttons:{'确定':true}});
				return;
			};
			var tmpjson = $.parseJSON($(data).filter('#mqif_json').html());
			
			if(tmpjson.status){
				if(!tmpjson.hasOwnProperty("message")){
					$.cookie("URL",httpurl,{path:'/'});
					$('#content').html($(data).filter('#mqif_html').html());
					eval($(data).filter('#mqif_js').html());
					return;
				}else{
					if(tmpjson.hasOwnProperty("button")){
						var submit = function (v, h, f) {
							for(var key in tmpjson.url){
								if (v == key){
									mqif_GoUrl(tmpjson.url[key]);
									return true;
								}
							}
						};
						$.jBox.confirm(tmpjson.message, "信息提示", submit, {buttons:tmpjson.button});
					}else{
						$.jBox.confirm(tmpjson.message, "信息提示", submit,{buttons:{'确定':true}});
						$.cookie("URL",httpurl,{path:'/'});
						$('#content').html($(data).filter('#mqif_html').html());
						eval($(data).filter('#mqif_js').html());
						return;
					}
				}
			}else{
				if(tmpjson.hasOwnProperty("button")){
					var submit = function (v, h, f) {
						for(var key in tmpjson.url){
							if (v == key){
								mqif_GoUrl(tmpjson.url[key]);
								return true;
							}
						}
					};
					$.jBox.confirm(tmpjson.message, "失败信息提示", submit, {buttons:tmpjson.button});
				}else{
					$.jBox.confirm(tmpjson.message, "失败信息提示", submit,{buttons:{'确定':true}});
				}
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};


function _editPwd(username){
	var html = "<table border='0'>"
			+"<tr><td>旧密码：</td><td><input type=\"password\" name=\"oldPassword\" id=\"oldPassword\" class=\"inputxt\"></td></tr>"
			+"<tr><td>新密码：</td><td><input type=\"password\" name=\"password\" id=\"password\" class=\"inputxt\" ></td></tr>"
			+"<tr><td>确认密码：</td><td><input type=\"password\" name=\"repassword\" id=\"repassword\" class=\"inputxt\" ></td></tr>"
			+"</table>";
	var submit = function (v, h, f) {
		if(f.oldPassword==""){
			$.jBox.tip("密码不能为空",'错误信息提示');
			return false;
		}
		if(f.password==""){
			$.jBox.tip("新密码不能为空",'错误信息提示');
			return false;
		}else if(f.password.length<6){
			$.jBox.tip("新密码至少6个字符,最多18个字符！",'错误信息提示');
			return false;
		}
		if(f.repassword==""){
			$.jBox.tip("确认密码不能为空",'错误信息提示');
			return false;
		}else if(f.password!=f.repassword){
			$.jBox.tip("新密码和确认密码不一致！",'错误信息提示');
			return false;
		}
		if(f.oldPassword==f.repassword){
			$.jBox.tip("新密码和旧密码不能一样!",'错误信息提示');
			return false;
		}
		var oldpass = $.md5($.trim($("#oldPassword").val()));
		var newpass = $.md5($.trim($("#repassword").val()));
		$.ajax({
			type : "POST",
			url : "user/password.action",
			data : {
				'newpass':newpass,
				'oldpass':oldpass
			},
			cache : false,
			dataType : "json",
			timeout : 30000, //超时60秒
			beforeSend:function(){
				$.jBox.tip("数据装载中，请稍候！", 'loading');
			},
			success:function(data) {
				if (data.status) {
					$.jBox.tip("新密码修改成功！", '成功信息提示');
				} else {
					$.jBox.tip(data.message, '错误信息提示');
				}
			},
			complete:function(XMLHttpRequest,status){
				//异常处理
				if(status!='success'){
					$.jBox.closeTip();
					if(status=='timeout'){
						$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
					}else{
						$.jBox.error("数据加载异常！请重试！", '错误信息提示');
					}
				}
			}
		});

	};
	$.jBox(html, {title:"修改（"+username+"）密码", submit: submit });

};

function _toSend(str,posturl,relurl){
	var strlists = $("input[name='listCheckBox']:checked").map(function() { return $(this).attr('value'); }).get();
	if(strlists.length==0){
		$.jBox.tip('请先选择单据','错误信息提示',{timeout:700});
		return ;
	}
	var rs="";
	var submit = function (v, h, f) {
		if(v=='ok'){
			$.ajax({
				type : "POST",
				url :posturl,
				cache : false,
				data : {
					'strlist':strlists.toString(),
					'tosend':str.toString(),
					'reson':rs.toString()
				},
				dataType : "json",
				cache : false,
				timeout : 300000, //超时30秒
				beforeSend:function(){
					$.jBox.tip("数据提交中，请稍候！", 'loading');
				},
				success:function(datas) {
					$.jBox.closeTip();
					if(datas.status){
						$.jBox.tip(datas.message, 'success',{timeout:700,opacity:0.9,closed:function(){mqif_GoUrl(relurl);}});
					}else{
						$.jBox.error(datas.message, '错误！',{opacity:0.9,closed:function(){mqif_GoUrl(relurl);}});
					}
					return false;
				},
				complete:function(XMLHttpRequest,status){
					//异常处理
					if(status!='success'){
						$.jBox.closeTip();
						if(status=='timeout'){
							$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
						}else{
							$.jBox.error("数据加载异常！请重试！", '错误信息提示');
						}
						return false;
					}
				}
			});
		}
		return true;
		
	};
	if(str=='sendcancle'){
		var html = "<div style='padding:10px;'>填写原因：<input name='reson' id='reson' class='inputxt'></div>";
		var submits= function(v1,h1,f1){
			if(f1.reson==""){
				$.jBox.tip("请填写原因","error",{focusId:"reson"});
				return false;
			}
			rs=f1.reson;
//			ul=ul+"&reson="+f1.reson;
			$.jBox.confirm("确定批量操作状态？", "信息提示", submit);
			return true;
		}
		$.jBox(html, {title:"请填写原因", submit: submits });
		//ul=ul+"&reason=";
	}else if(str=='statuschange'){
		var html = "<div style='padding:10px;'>状态：<select name='statu' id='statu'>" +
				"<option value='realy'>待上架</option>"+
				"<option value='finish'>已上架</option>"+
				"<option value='lack'>缺货</option>"+
				"<option value='stop'>停售</option>"+
				"</select></div>";
		var _submits= function(v2,h2,f2){
			if(f2.statu==""){
				$.jBox.tip("请选择状态","error",{focusId:"statu"});
				return false;
			}
			str = f2.statu;
			$.jBox.confirm("确定批量操作状态？", "信息提示", submit);
			return true;
		}
		$.jBox(html, {title:"请选择状态", submit: _submits });
	}
	else{
		$.jBox.confirm("确定批量操作状态？", "信息提示", submit);
	}
	
};

function _saveSEQ(posturl,relurl){
	var idlist =$(".text-shadow input[name='seqs']").map(function() { return $(this).attr("id")+"&"+$(this).attr("value"); }).get();
	$.ajax({
		type : "POST",
		url :posturl,
		cache : false,
		data : {
			'seqlist':idlist.toString()
		},
		dataType : "json",
		cache : false,
		timeout : 30000, //超时30秒
		beforeSend:function(){
			$.jBox.tip("数据提交中，请稍候！", 'loading');
		},
		success:function(datas) {
			$.jBox.closeTip();
			if(datas.status){
				$.jBox.tip(datas.message, 'success',{timeout:1000});
				mqif_GoUrl(relurl);
			}else{
				$.jBox.tip(datas.message,{timeout:700});
			}
			
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
				return false;
			}
		}
	});
};


function showQQMAP(latlgn){
	var submit = function (v, h, f) {
		var latlng = $(window.frames["jbox-iframe"].document).find('#poi_cur').val().replace(' ','');
		if(latlng==null||latlng==''||latlng.split(",").length!=2){
			$.jBox.error("您还没选择地点，请选择！", '错误信息提示');
			return false;
		}else{
			$('#qqlatlng').val(latlng);
			return true;
		}
	};
	$.jBox("iframe:qq.map.html?latlgn:"+latlgn, {
	    title: "选取位置",
	    width: 920,
	    height: 673,
	    submit: submit,
	    top: '0'
	});
};
//删除标签
function removeProlab(obj){
	$(obj).parent().remove();
};

function _toCommond(str,posturl,relurl){
	var strlists = $("input[name='listCheckBox']:checked").map(function() { return $(this).attr('value'); }).get();
	if(strlists.length==0){
		$.jBox.tip('请先选择单据','错误信息提示',{timeout:700});
		return ;
	}
	var amount="";
	var submit = function (v, h, f) {
		if(v=='ok'){
			$.ajax({
				type : "POST",
				url :posturl,
				cache : false,
				data : {
					'strlist':strlists.toString(),
					'operation':str.toString(),
					'data':amount.toString()
				},
				dataType : "json",
				cache : false,
				timeout : 300000, //超时30秒
				beforeSend:function(){
					$.jBox.tip("数据提交中，请稍候！", 'loading');
				},
				success:function(datas) {
					$.jBox.closeTip();
					if(datas.status){
						$.jBox.tip(datas.message, 'success',{opacity:0.7,closed:function(){mqif_GoUrl(relurl);}});
					}else{
						$.jBox.error(datas.message, '错误！',{opacity:0.7,closed:function(){mqif_GoUrl(relurl);}});
					}
					return false;
				},
				complete:function(XMLHttpRequest,status){
					//异常处理
					if(status!='success'){
						$.jBox.closeTip();
						if(status=='timeout'){
							$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
						}else{
							$.jBox.error("数据加载异常！请重试！", '错误信息提示');
						}
						return false;
					}
				}
			});
		}
		return true;
		
	};
	if(str=='VRECH'){
		var html = "<div class=\"recharge\">"+
		"<ul><li>充值金额：</li><li style=\"padding:5px 0;\">"+
		"<p class=\"recharge_amount\"><i>¥100</i></p>"+
		"<p class=\"recharge_amount\"><i>¥200</i></p>"+
		"<p class=\"recharge_amount\"><i>¥300</i></p>"+
		"<p class=\"recharge_amount\"><i>¥500</i></p>"+
		"<p class=\"recharge_input\"><input name=\"recharge_amount\" id=\"recharge_amount\" type=\"number\" svalue=\"0\" value='' placeholder=\"其他金额\"></p>"+
		"</li><li class=\"tip\">其他金额，输入充值最少10以上的正整数。</li>"+
		"</ul></div>"+
		"<script>" +
		"$(\".recharge_amount\").click(function(){"+
			"$(\".recharge_amount\").removeClass(\"on\");"+
			"$(\".recharge_input\").removeClass(\"on\");"+
			"$(this).addClass(\"on\");"+
			"var amount = $(this).find('i').html().replace(\"¥\",\"\");"+
			"$(\"#recharge_amount\").attr('svalue',amount);"+
		"});"+
		"$(\".recharge_input\").click(function(){"+
			"$(\".recharge_amount\").removeClass(\"on\");"+
			"$(\"#recharge_amount\").attr('svalue','0');"+
			"$(this).addClass(\"on\");"+
		"});"+
		"</script>";
		var submits= function(v1,h1,f1){
			if(h1.find("#recharge_amount").attr('svalue')=='0'){
				amount = h1.find("#recharge_amount").val();
			}else{
				amount = h1.find("#recharge_amount").attr('svalue');
			};
			if(amount==""||amount=="0"||amount==null){
				$.jBox.tip("请选择充值金额！", "error", {focusId : "recharge_amount"});
				return false;
			}
			if (/^[0-9]*[0-9][0-9]*$/.test(amount)){
				if(parseInt(amount)<10){
					$.jBox.tip("请输入充值金额最少10元！", "error", {focusId : "recharge_amount"});
					return false;
				}
			}else{
				$.jBox.tip("充值金额只允许输入整数！", "error", {focusId : "recharge_amount"});
				return false;
			}
			
			$.jBox.confirm("确定批量操作充值？", "信息提示", submit);
			return true;
		}
		$.jBox(html, {title:"请选择金额", submit: submits });
	}else{
		$.jBox.confirm("确定批量操作？", "信息提示", submit);
	}
	
};

function mqif_Cancel(id,url) {
	if(id==""){
		$(".couponcancel").each(function(){
			if ($(this).is(":checked")) {
				id += $(this).attr('value')+',';
			}
		});
	}
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : {'id':id},
		dataType : "JSON",
		timeout : 30000, //超时30秒
		beforeSend:function(){
			$.jBox.tip("数据提交中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if(data.status){
				$.jBox.alert(data.message, "信息提示",{ closed: function () {mqif_GoUrl('coupon/list.action');} });
			}else{
				$.jBox.error(data.message, '信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function _issuing_coupon(){
	var strlists = $("input[name='listCheckBox']:checked").map(function() { return $(this).attr('value'); }).get();
	if(strlists.length==0){
		$.jBox.tip('请先选择单据','错误信息提示',{timeout:700});
		return false;
	}else{
		//查询是否有优惠券
		$.ajax({
			type : "POST",
			url : "vmember/coupon_json.action",
			cache : false,
			data : {
			},
			dataType : "json",
			timeout : 30000, //超时60秒
			beforeSend:function(){
				$.jBox.tip("正在读取优惠券设置信息，请稍候", 'loading');
			},
			success:function(data) {
				
				if(data.status){
					var tmpHtml = "";
					$.each(data.message, function(index,list){
						tmpHtml += ("<li dataUuid=\""+list.uuid+"\"");
						tmpHtml += (" dataAmount=\""+list.amount+"\"");
						tmpHtml += (" onclick=\"setCoupon(this);\"");
						tmpHtml += (">");
							
						tmpHtml += ("<p class=\"mj\">");
						if(list.minconamount>0&list.amount>0){
							tmpHtml += ("满<b>"+list.minconamount+"</b>元 减<b>"+list.amount+"元</b>");
						}else{
							tmpHtml += ("折扣:<b>"+list.discount+"</b>");
						}
						tmpHtml += ("</p>");
						tmpHtml += ("<p class=\"date\">有效时间："+list.startdate+" 至 "+list.enddate+"</p>");
						tmpHtml += ("</li>");
				    });
					if(tmpHtml==""){
						tmpHtml += ("<div class=\"couponSelectlist\"><input name='dataUuid' id='dataUuid' value='' class='hidden'/><input name='dataNumber' id='dataNumber' value='' class='hidden'/><ul><br><br>");
						tmpHtml += ("<h3 style=\"color:#666;text-align:center;\">没有优惠券信息!</h3>");
						tmpHtml += ("<br><br></ul></div>");
					}else{
						var str = "";
						str += ("<div class=\"couponSelectlist\" style=\"overflow-y:scroll;width:350px;height:400px;\">");
						str += ("<input name='dataUuid' id='dataUuid' value='' class='hidden'/>");
						str += ("<input name='dataAmount' id='dataAmount' value='0' class='hidden'/>");
						tmpHtml = (str+"<ul>"+tmpHtml+"</ul></div>");
					}
					$.jBox.closeTip();
					setTimeout(function () {
						var submit = function (v, h, f) {
								if (v == true){
 									var value_dataUuid = $("input[name='dataUuid']").val();
 									if(value_dataUuid==''){
 										$.jBox.error("请选择优惠券", '错误信息提示');
 										return;
 									}else{
 										$.ajax({
 											type : "POST",
 											url : "vmember/issuing_coupon.action",
 											cache : false,
 											data : {
 												"strlist":strlists.toString(),
 												"copid":value_dataUuid
 											},
 											dataType : "json",
 											timeout : 30000,
 											beforeSend:function(){
 												$.jBox.tip("正在提交数据，请稍候！","loading");
 											},
 											success:function(data2) {
 												$.jBox.closeTip();
 												setTimeout(function () {
 												if(data2.status){
 													$.jBox.alert("发放成功", "信息提示",{ closed: function () {mqif_GoUrl('vmember/list.action');} });
 												}else{
 													$.jBox.error(data2.message,'错误信息提示');
 												}
 												},510);
 											},
 											complete:function(XMLHttpRequest,status){
 												if(status!='success'){
 													$.jBox.closeTip();
 													$.jBox.error("数据加载异常！请重试！", '错误信息提示');
 												}
 											}
 										}); 
 									}
								
								}
							    return true;
							};
							$.jBox(tmpHtml, {
								title: "发放优惠券",
								submit: submit,
								buttons: { '保存': true, '取消': false}
							});
					},1000);
				}else{
					$.jBox.closeTip();
					$.jBox.alert(data.message, "信息提示");
					
				}
				
			},
			complete:function(XMLHttpRequest,status){
				if(status!='success'){
						$.jBox.closeTip();
						$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		});
	//查询优惠券结束
	}
};

function setCoupon(obj) {
	$('#dataUuid').val($(obj).attr("dataUuid"));
	$('#dataAmount').val($(obj).attr("dataAmount"));
 		$('.couponSelectlist ul li').each(function() {
		$(this).removeClass("on");
	});
 		$(obj).addClass('on');
};

function mqif_query_filter(obj) {
	var httpurl = $(obj).attr("data-mqif-url");
	var page = $(obj).attr("data-mqif-page");
	var startdateFrom = $("#startdateFrom").val();
	var enddateFrom = $("#enddateFrom").val();
	var ordstatus = $("#ordstatus").val();
	
    var start=new Date(startdateFrom.replace("-", "/").replace("-", "/"));  
    var end=new Date(enddateFrom.replace("-", "/").replace("-", "/"));  
    if(end<start){  
    	$.jBox.error("结束日期不能大于开始日期!", '错误信息提示');
        return false; 
    }  
	$.ajax({
		type : "GET",
		url : httpurl+"?p="+page+"&startdateFrom="+startdateFrom+"&enddateFrom="+enddateFrom+"&ordstatus="+ordstatus,
		cache : false,
		dataType : "html",
		timeout : 30000,
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			var tmpjson = $.parseJSON($(data).filter('#mqif_json').html());
			$(".loadAjaxData").html("");
			$("#list_table_cache tbody").html("");
			if(tmpjson.status){
				$("#list_table_cache tbody").append($(data).filter('#mqif_html').find('table#list_table_cache').find('tbody').html());
				mqif_load_table(httpurl.replace("/",""));
				if(tmpjson.issurpluspage){
					$("#loadMore").attr("data-mqif-page",tmpjson.nextpageno);
					$("#loadMore").show();
				}else{
					$("#loadMore").attr("data-mqif-page","");
					$("#loadMore").hide();
				}
				set_BgColor();
			}else{
				$("#loadMore").attr("data-mqif-page","");
				$("#loadMore").hide();
				$.jBox.error("已经没有更多数据可以加载了!", '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_DishUpload(obj){
	var _this = obj;
	$.ajax({
		type : "POST",
		url : "common/mqif_upload.action",
		cache : false,
		//data : {},
		dataType : "html",
		timeout : 30000, //超时30秒
		success:function(data) {
			$.jBox.closeTip();
			var submit = function (v, h, f) {
				if(v==true){
					if(f.upicon==""){
						$.jBox.tip("您还未选择需要上传的图片，请点击选择！");
						return false;
					}else{
						saveCDishImg(f.upicon, $(_this).prev(),$(_this).attr("data-id"));
						$(_this).parent().prev().children().attr("src",f.upicon);
//						tessssss($(_this).prev());
//						data-id
//						console.log("val:"+$(_this).prev().attr("value"));
//						saveCDishImg($(_this).attr("data-id"),);
						//$("#iconshow").attr("src",f.upicon);//修改为再次保存
						return true;
					}
				}else{
					return true;
				}
			};
			$.jBox(data, {title:"请选择:",width:"630px",height:"380px",submit: submit,buttons:{ '确定':true,'取消':false} });
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
	
}
function saveCDishImg(iconimage,inputObj,uuid){
	console.log("iconimage:"+iconimage)
	console.log("uuid:"+uuid)
	$.ajax({
		type : "POST",
		url : 'common/upicon.action',
		cache : false,
		data : {
			'iconimage':iconimage
		},
		dataType : "json",
		timeout : 180000, //超时180秒
		beforeSend:function(){
			$.jBox.tip("文件上传中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if(data.state=='SUCCESS'){
				$.jBox.tip("上传成功！");
				inputObj.attr('value',data.url);
				
				$.ajax({
					type : "POST",
					url : "cashsys/cdish_img_save.action",
					cache : false,
					data : {"id":uuid,"thumbpath":data.url},
					dataType : "json",
					timeout : 30000, //超时30秒
					beforeSend:function(){
						$.jBox.tip("正在保存...", 'loading');
					},
					success:function(datas) {
						$.jBox.closeTip();
						if(datas.status){
							$.jBox.alert(datas.message, "信息提示");
						}else{
							$.jBox.error(datas.message,'错误信息提示');
						}
						
					},
					complete:function(XMLHttpRequest,status){
						//异常处理
						if(status!='success'){
							$.jBox.closeTip();
							$.jBox.error("数据加载异常！请重试！", '错误信息提示');
						}
					}
				});
				
			}else{
				inputObj.attr('value','');
				$.jBox.error("上传失败！"+unescape(data.state), '错误信息提示');
			}
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				if(status=='timeout'){
					$.jBox.error("服务器连接超时！请重试！", '错误信息提示');
				}else{
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		}
	});
};

function mqif_cash_sysnc(posturl,relurl){
	$.ajax({
		type : "POST",
		url : posturl,
		cache : false,
		dataType : "json",
		cache : false,
		timeout : 30000, //超时30秒
		beforeSend:function(){
			$.jBox.tip("正在同步数据，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			if(data.status){
				$.jBox.alert(data.message, "信息提示",{ closed: function () {mqif_GoUrl(relurl);} });
			}else{
				$.jBox.error(data.message,'错误信息提示');
			}
			
		},
		complete:function(XMLHttpRequest,status){
			//异常处理
			if(status!='success'){
				$.jBox.closeTip();
				$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				return false;
			}
		}
	});
};