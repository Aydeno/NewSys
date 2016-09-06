
//商品编辑-删除规格行信息
function del_gg(obj) {
	$(obj).parent().remove();
};

//商品编辑-删除当前缩略图片
function del_thumb(obj) {
	$(obj).parent().remove();
};

//初始化缩略图上传组件
var upThumb = new UE.ui.Editor();
upThumb.render('upThumb');
upThumb.ready(function() {
	upThumb.hide();
	upThumb.addListener('beforeInsertImage',function(t, arg) {
		var j;
		$('#pro_tab_ul .tabBlock-tab').each(function(index) {
			if ($(this).hasClass('is-active')) {
				j = index;
			}
		});
		$($('#pro_tab_div .tabBlock-pane')[j]).find('li.thumblist div.clear').remove();
		var thumbStr = '';
		for (var i = 0; i < arg.length; i++) {
			var uid = $($('#pro_tab_div .tabBlock-pane')[j]).attr('uid')+'_THUMB';
			thumbStr += '<p class="thumb">';
			thumbStr += '<img src="'+arg[i].src+'">';
			thumbStr += '<i class="del_thumb fa fa-minus-circle fa-2x" onclick="del_thumb(this);" title="删除当前图片"></i>';
			thumbStr += '<input name="'+uid+'" value="'+arg[i].src+'" type="checkbox" checked="checked" style="display:none;"/>';
			thumbStr += '</p>';
		};
		thumbStr += '<div class="clear" style="clear:both;"></div>';
		$($('#pro_tab_div .tabBlock-pane')[j]).find('li.thumblist').append(thumbStr);
	});
});
function add_thumb() {
	upThumb.getDialog('insertimage').render();
	upThumb.getDialog('insertimage').open();
};
function isd(obj) {
	if($(obj).attr("checked")=="checked"||$(obj).attr("checked")==true){
		$("#"+$(obj).val()).css("display","none");
	}else{
		$("#"+$(obj).val()).css("display","");
	}
};
//JS删除颜色页签事件
function pro_tab_del() {
	var i = 0;
	var j;
	var gg_Id;
	var gg_Name;
	$('#pro_tab_ul .tabBlock-tab').each(function(index) {
		if ($(this).hasClass('is-active')) {
			j = index;
			gg_Id = $(this).attr('uid');
			gg_Name = $(this).text();
			if (index > 0) {
				i = index - 1;
			};
		}
	});
	if (gg_Name == '默认商品介绍') {
		$.jBox.error('默认商品介绍页签不允许删除！', '错误信息提示');
		return;
	};
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$('#pro_tab_div .tabBlock-pane')[j].remove();
			$('#pro_tab_ul .tabBlock-tab')[j].remove();
			$($('#pro_tab_ul .tabBlock-tab')[i]).addClass('is-active');
			$($('#pro_tab_div .tabBlock-pane')[i]).show();
			UE.getEditor(gg_Id).destroy();
			$('#' + gg_Id).remove();
		};
		return true;
	};
	$.jBox.confirm('您确定要删除吗？<br>颜色：' + gg_Name, '删除颜色', submit);
};

//JS添加颜色页签事件
function pro_tab_add() {
	$.ajax({
		type : "GET",
		url : "vpro/vprobascolorlist.action",
		cache : false,
		dataType : "json",
		timeout : 30000, //超时60秒
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			var html = "";
			$.each(data,function(n,value) {
				var j = 0;
				$('#pro_tab_div .tabBlock-pane').each(function(index) {
					if ($(this).attr('uid')==value.uuid) {
						j = 1;
					}
				});
				if(j==0){
					html += "<label><input name='tmpcolorList' value='"+value.uuid+"|"+value.name+"' type='checkbox' />" + value.name +"</label>";
				}
		    });
			if(html==""){
				$.jBox.tip("已经没有颜色可以添加了！", 'error');
				return;
			}
			html = "<div style='padding:10px;'>"+html+"</div>";
			var submit = function(v, h, f) {
				if (f.tmpcolorList == null) {
					$.jBox.tip("您还没选择颜色，请选择颜色。", 'error');
					return false;
				};
				var s = f.tmpcolorList.toString().split(',');
				$.each(s,function(n,value) {
					var vs = value.toString().split('|');
					var colorID = vs[0];
					var colorName = vs[1];
				 	$('#pro_tab_ul .is-active').removeClass("is-active");
					$('#pro_tab_div .tabBlock-pane').hide();
					$('#pro_tab_ul').append('<li class="tabBlock-tab is-active" uid="'+ colorID+ '">'+ colorName + '</li>');
					var tabhtml = '<div class="tabBlock-pane" uid="'+ colorID+ '">';
					tabhtml += '<input name="colorList" value="'+ colorID+ '" type="checkbox" checked="checked" style="display:none;"/>';
					tabhtml += '<ul class="vproSpecification">';
					tabhtml += '	<li class="title">';
					tabhtml += '		<p><a href="javascript:void(0)" onclick="add_gg(this);" class="add_gg add" uid="'+ colorID+ '">添加规格</a><a href="javascript:void(0)" class="add_thumb add" onclick="add_thumb();" uid="'+ colorID+ '">添加缩略图</a><label class="add"><input name="'+colorID+'_ISCONTENT" class="isd" onclick="isd(this);" type="checkbox" value="'+colorID+'" checked="checked"/>使用默认商品介绍</label></p>';
					tabhtml += '	</li>';
					tabhtml += '	<li class="thumblist"></li>';
					tabhtml += '</ul>';
					tabhtml += '<script type="text/plain" id="'+ colorID+ '" name="'+ colorID+ '" style="width:100%;height:240px;display:none;"><//script></div>';
					$('#pro_tab_div').append(tabhtml);
					//初始化编辑器
					var editor = new UE.ui.Editor();
					editor.render(colorID);
			    });
				return true;
			};
			$.jBox(html, {
				title : "添加颜色",
				submit : submit
			});
			
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
//商品编辑-添加规格信息行
function add_gg(obj) {
	var pdiv = $(obj);
	$.ajax({
		type : "GET",
		url : "vpro/vprobassizelist.action",
		cache : false,
		dataType : "json",
		timeout : 30000, //超时60秒
		beforeSend:function(){
			$.jBox.tip("数据装载中，请稍候！", 'loading');
		},
		success:function(data) {
			$.jBox.closeTip();
			var html = "";
			var j;
			$('#pro_tab_ul .tabBlock-tab').each(function(index) {
				if ($(this).hasClass('is-active')) {
					j = index;
				}
			});
			$.each(data,function(n,value) {
				var jj = 0;
				$($('#pro_tab_div .tabBlock-pane')[j]).find('li input').each(function(index) {
					if ($(this).val()==value.uuid) {
						jj = 1;
					}
				});
				if(jj==0){
					html += "<label><input name='tmpcolorList' value='"+value.uuid+"|"+value.name+"' type='checkbox' />" + value.name +"</label>";
				}
		    });
			if(html==""){
				$.jBox.tip("已经没有规格尺寸可以添加了！", 'error');
				return;
			}
			html = "<div style='padding:10px;'>"+html+"</div>";
			var submit = function(v, h, f) {
				if (f.tmpcolorList == null) {
					$.jBox.tip("您还没选择规格尺寸，请选择尺寸规格。", 'error');
					return false;
				};
				var s = f.tmpcolorList.toString().split(',');
				$.each(s,function(n,value) {
					var vs = value.toString().split('|');
					var sizeID = vs[0];
					var sizeName = vs[1];
					var htmlli = '<li><input name="'+pdiv.attr('uid')+'_GG" value="'+sizeID+ '" type="checkbox" checked="checked" style="display:none;"/>'
					htmlli += '规格名称:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_NAME\" class=\"GGName\" value=\"'+sizeName+'\" disabled="disabled" />';
					htmlli += '条码:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_BARCODE\" class=\"GGCode\" value=\"\" datatype=\"*1-255\" />';
					htmlli += '库存:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_LAVEQTY\" value=\"9999\" datatype=\"n1-5\" />';
					htmlli += '已售:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_SALEQTY\" value=\"0\" datatype=\"n1-8\" />';
					htmlli += '原价:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_PRICEOLD\" value=\"0\" datatype=\"*1-8\" />';
					htmlli += '现价:<input type=\"text\" name=\"'+pdiv.attr('uid')+sizeID+'_PRICE\" value=\"0\" datatype=\"*1-8\" />';
					htmlli += '<span class=\"Validform_checktip\"></span>';
					htmlli += '<a href=\"javascript:void(0)\" class=\"del_gg\" onclick=\'del_gg(this);\'>删除</a>';
					htmlli += '</li>';
					pdiv.parent().parent().after(htmlli);

			    });
				return true;
			};
			$.jBox(html, {
				title : "添加规格",
				submit : submit
			});
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

function add_TimeRange(){
	var presel = $("input[name='fpety_List']:checked").map(function() { return $(this).attr('value'); }).get();
		$.ajax({
			type : "POST",
			url :"anniversary/getTimeRange.action",
			cache : false,
			dataType : "json",
			cache : false,
			timeout : 30000, //超时30秒
			beforeSend:function(){
			},
			success:function(data) {
				
				var html = '<div style="padding:10px;">';
				$.each(data,function(i,timeRange) {
					if(presel.toString().indexOf(timeRange.uuid)<0){
						html += '<div style="display:block"><input type="checkbox" name="'+timeRange.value+'" id="'+timeRange.value+'" value="'+timeRange.value+'" class="whousechk" data-name="'+timeRange.name+'"/><label for="'+timeRange.value+'">'+timeRange.name+'</label>';
						html += '</div>'
					}else{
						html += '<div style="display:block"><input type="checkbox" name="'+timeRange.value+'" id="'+timeRange.value+'" value="'+timeRange.value+'" class="whousechk" data-name="'+timeRange.name+'" checked="checked" /><label for="'+timeRange.value+'">'+timeRange.name+'</label>';
						html += '</div>'
					}
				});
				
				html+='</div>';
				
				var submit = function (v, h, f) {
					if(v=='ok'){
						var strlists = h.find(".whousechk:checked").map(function() { return $(this).attr('value')+"_"+$(this).attr('data-name'); }).get();
						if(strlists==null||strlists==''){
							$.jBox.tip('请选择时间段','错误信息提示',{timeout:700});
							return false;
						}
						
						var ulhtm = '';
						var str = strlists.toString().split(",");
						for(var ii=0;ii<str.length;ii++){
							var iistr = str[ii].split("_");
							if(presel.toString().indexOf(iistr[0])<0){
								ulhtm+='<tr>';
								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
								ulhtm+='	<input class="sepcwidth" name="fpety_List" value="'+iistr[0]+'" type="checkbox" checked="checked" style="display:none;"/>';
								ulhtm+=iistr[1];
								ulhtm+='</td>';
								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_giftone_qty" value="" datatype="n"/>';
								ulhtm+='<span class="Validform_checktip"></span>';
								ulhtm+='</td>';
//								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
//								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_giftone_ableqty" value=""  datatype="n"/>';
//								ulhtm+='<span class="Validform_checktip"></span>';
//								ulhtm+='</td>';
								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_gifttwo_qty" value="" datatype="n" />';
								ulhtm+='<span class="Validform_checktip"></span>';
								ulhtm+='</td>';
//								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
//								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_gifttwo_ableqty" value="" datatype="n"/>';
//								ulhtm+='<span class="Validform_checktip"></span>';
//								ulhtm+='</td>';
								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_giftthree_qty" value="" datatype="n"/>';
								ulhtm+='<span class="Validform_checktip"></span>';
								ulhtm+='</td>';
//								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;">';
//								ulhtm+='<input class="sepcwidth" type="text" name="'+iistr[0]+'_giftthree_ableqty" value="" datatype="n"/>';
//								ulhtm+='<span class="Validform_checktip"></span>';
//								ulhtm+='</td>';
								ulhtm+='<td style="border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;text-align: center;"><a onclick="delEtyList(this,\''+iistr[0]+'\');">删除</a></td>';
								ulhtm+='</tr>';
							}
						}
						$("#selprobody").append(ulhtm);
					}
					return true;
					
				};
				
				
				$.jBox(html, {title:"请选择时间段", submit: submit });
				
			},
			complete:function(XMLHttpRequest,status){
				//异常处理
				if(status!='success'){
					$.jBox.error("数据加载异常！请重试！", '错误信息提示');
				}
			}
		});
};
function delEtyList(obj,uuid){
	$(obj).parent().parent().remove();
	$("#delUuid_div").append('<input type="checkbox" checked="checked" style="display:none;" value="'+uuid+'" name="del_uuid_list"/>');
};
