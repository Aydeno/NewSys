function trim(t){ 
	return (t||"").replace(/^\s+|\s+$/g, ""); 
};
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
};
function mqif_layer_load(msg,timeout){
	var str = '数据加载中';
	if(msg!=null&&""!=msg){
		str = msg;
	}
	var timeoutN = 500;
	var isout = false;
	if(null!=timeout){
		if(timeout>0){
			isout = true;
			timeoutN = timeout;
		}
	}
	if(isout){
		setTimeout(function () {
			layer.open({
			    type: 2,
			    shadeClose: false,
			    content: str
			});
		},timeoutN);
	}else{
		layer.open({
		    type: 2,
		    shadeClose: false,
		    content: str
		});
	}
};
function mqif_layer_closeAll(timeout){
	var timeoutN = 500;
	var isout = true;
	if(null!=timeout){
		if(timeout==0){
			isout = false;
		}else{
			timeoutN = timeout;
		}
	}
	if(isout){
		setTimeout(function () {
			layer.closeAll();
		},timeoutN);
	}else{
		layer.closeAll();
	}
};
function mqif_layer_msg(msg,timeout){
	var timeoutN = 500;
	var isout = true;
	if(null!=timeout){
		if(timeout==0){
			isout = false;
		}else{
			timeoutN = timeout;
		}
	}
	if(isout){
		setTimeout(function () {
			layer.open({
			    type: 0,
			    time: 2,
			    content: msg,
			    style: 'text-align:center;'
			});
		},timeoutN);
	}else{
		layer.open({
		    type: 0,
		    time: 2,
		    content: msg,
		    style: 'text-align:center;'
		});
	}
};

function tzHref(htmlurl){
	window.location.href=htmlurl;
};
