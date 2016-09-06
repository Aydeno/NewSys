
	function mqif_pay_starts(type,oid,url){
		$.ajax({
			type : "POST",
			url : "../api/wechat/getpaystatus.html",
			cache : false,
			data : {"id":oid,"type":type},
			dataType : "json",
			timeout : 300000, //超时60秒
			beforeSend:function(){
				$.jBox.tip("正在查询支付信息！", 'loading');
			},
			success:function(data) {
				$.jBox.closeTip();
				if(data.status){
					$.jBox.tip('支付信息生成失败!'+data.message, 'error', {
						opacity : 0.9,
						timeout : 3000
					});
					//location.replace("order.html?status=1");
					if(url!=''){
						location.replace(url);
					}else{
						location.replace("user.html");
					}
				}else{
					$.jBox.tip('支付信息生成失败!'+data.message, 'error', {
						opacity : 0.9,
						timeout : 3000
					});
				}
			},
			complete:function(XMLHttpRequest,status){
				if(status!='success'){
					$.jBox.closeTip();
					if(status=='timeout'){
						$.jBox.alert("服务器连接超时！请重试！", '错误信息提示');
					}else{
						$.jBox.alert("数据加载异常！请重试！", '错误信息提示');
					}
				}
			}
		});
	};
	function mqif_pay(pay,type,oid,url){
		if(pay=='wx'){
			//微信支付
			$.ajax({
				type : "POST",
				url : "../api/wechat/getpayinfo.html",
				cache : false,
				data : {"id":oid,"type":type},
				dataType : "json",
				timeout : 300000, //超时60秒
				beforeSend:function(){
					$.jBox.tip("正在获取支付信息！", 'loading');
				},
				success:function(data) {
					$.jBox.closeTip();
					if(data.status){
						WeixinJSBridge.invoke(
						    'getBrandWCPayRequest', {
						        "appId" : data.otoappID,
						        "timeStamp":data.timeStamp,
						        "nonceStr" :data.nonceStr,
						        "package":data.package_str,
						        "signType":data.signType,
						        "paySign":data.paySign
						    },
						    function(res){
						    	var strtmp = "";
						        if(res.err_msg=="get_brand_wcpay_request:ok"){
						        	strtmp="支付成功！";
						        }else if(res.err_msg=="get_brand_wcpay_request:cancel"){
						        	strtmp="用户取消支付！";
						        }else{
						        	strtmp="支付失败！";
						        }
								$.jBox.tip(strtmp,'error', {
									opacity : 0.9,
									timeout : 3000
								});
								mqif_pay_starts(type,oid,url);
						    }
						); 
						if (typeof WeixinJSBridge == "undefined"){
						   if( document.addEventListener ){
						       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
						   }else if (document.attachEvent){
						       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
						       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
						   }
						}else{
						   onBridgeReady();
						}
					}else{
						$.jBox.tip('支付信息生成失败!'+data.message, 'error', {
							opacity : 0.9,
							timeout : 3000
						});
					}
				},
				complete:function(XMLHttpRequest,status){
					if(status!='success'){
						$.jBox.closeTip();
						if(status=='timeout'){
							$.jBox.alert("服务器连接超时！请重试！", '错误信息提示');
						}else{
							$.jBox.alert("数据加载异常！请重试！", '错误信息提示');
						}
					}
				}
			});
		}else{
			//余额支付
			$.ajax({
				type : "POST",
				url : "../api/wechat/getpayinfo.html",
				cache : false,
				data : {"id":oid,"type":type},
				dataType : "json",
				timeout : 300000, //超时60秒
				beforeSend:function(){
					$.jBox.tip("正在获取支付信息！", 'loading');
				},
				success:function(data) {
					$.jBox.closeTip();
					if(data.status){
						
					}else{
						$.jBox.tip('支付信息生成失败!'+data.message, 'error', {
							opacity : 0.9,
							timeout : 3000
						});
					}
				},
				complete:function(XMLHttpRequest,status){
					if(status!='success'){
						$.jBox.closeTip();
						if(status=='timeout'){
							$.jBox.alert("服务器连接超时！请重试！", '错误信息提示');
						}else{
							$.jBox.alert("数据加载异常！请重试！", '错误信息提示');
						}
					}
				}
			});
		}
	};
	
	function _addCode2(name,uuid,url) {
		var murl = encodeURIComponent(url+"/wx/buycat.html?id="+uuid);
		var turl = url+"/api/qrcode.action?url="+murl;
		$(".pupupEWM").css('display','block');
		$(".ewmPic").attr("src",turl);
	};
	function _noneCode2() {
		$(".pupupEWM").css('display','none');
	};
	