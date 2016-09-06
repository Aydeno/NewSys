<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!-- 页面返回的JSON,ID不允许修改,必须固定ID：mqif_json -->
<div id="mqif_json">${returnInfo}</div>
<!-- 页面返回的JS代码,ID不允许修改,必须固定mqif_js -->
 <script id="mqif_js">
 $("#listSelectAll").click(function() {
		if ($(this).is(":checked")) {
			$(".list_table-tbl .selcon").prop("checked", true);
		} else {
			$(".list_table-tbl .selcon").prop("checked", false);
		}
	});
$(document).ready(function(){
	mqif_load_table("vmember/list.action");
});
$('.datepicker').Zebra_DatePicker();
</script> 
<!-- 页面返回的HTML代码,ID不允许修改,必须固定ID：mqif_html -->
<div id="mqif_html">
	<s:if test="returnInfo.get('status')==true">
		<div class="box-title">
			<h3>
				<i class="fa fa-list-ul"></i>APP用户管理
			</h3>
		</div>
		<table id="list_table_cache" style="display:none;">
			<tbody>
			<s:iterator value="jsonlist">
				<tr class="tbl-item">
					<td align="center"><input name="listCheckBox" value="<s:property value="get('uuid')"/>" type="checkbox" class="selcon"></td>
					<td align="center" class="index"><s:property value="get('index')" /></td> 
					<td align="center" class="username"><s:property value="get('username')" /></td>
					<td align="center" class="realname"><s:property value="get('realname')" /></td>
					<td align="center" class="sex"><s:property value="get('sex')" /></td>
					<td align="center" class="mobile"><s:property value="get('mobile')" /></td>
					<td align="center" class="createtime"><s:property value="get('createtime')" /></td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
		<form name="listForm" id="listForm">
			<div id="list_table" class="box jplist table-layout-2">
				<div class="jplist-ios-button"><i class="fa fa-sort"></i>&nbsp;过滤条件 (显示/隐藏)</div>
				<div class="jplist-panel box panel-top">
					<div class="jplist-drop-down" data-control-type="drop-down" data-control-name="paging" data-control-action="paging">
						<ul>
							<li><span data-number="10" data-default="true">10行记录/页</span></li>
							<li><span data-number="20">20行记录/页</span></li>
							<li><span data-number="50">50行记录/页</span></li>
						</ul>
					</div>
					<div class="jplist-drop-down" data-control-type="drop-down" data-control-name="sort" data-control-action="sort" data-datetime-format="{year}-{month}-{day} {hour}:{min}:{sec}">
						<ul>
							<li><span data-path=".index" data-order="asc" data-type="number" data-default="true">序号 升序</span></li>
							<li><span data-path=".index" data-order="desc" data-type="number">序号 降序</span></li>
							<li><span data-path=".createtime" data-order="asc" data-type="datetime">注册时间 升序</span></li>
							<li><span data-path=".createtime" data-order="desc" data-type="datetime">注册时间 降序</span></li> 
						</ul>
					</div>
					<div class="text-filter-box">
						<i class="fa fa-search  jplist-icon"></i>
						<input data-path=".username" type="text" value="" placeholder="用户名过滤(页内)" data-control-type="textbox" data-control-name="title-filter" data-control-action="filter" />
					</div>
					<div class="text-filter-box">
						<i class="fa fa-search  jplist-icon"></i>
						<input data-path=".realname" type="text" value="" placeholder="实名过滤(页内)" data-control-type="textbox" data-control-name="title-filter-realname" data-control-action="filter" />
					</div>
					<div class="text-filter-box">
						<i class="fa fa-search  jplist-icon"></i>
						<input data-path=".mobile" type="text" value="" placeholder="手机号码过滤(页内)" data-control-type="textbox" data-control-name="desc-filter" data-control-action="filter" />
					</div> 
					
				</div>
				<!-- data -->
				<div class="box text-shadow">
					<table class="list_table-tbl">
						<thead>
							<tr>
							<th width="20" align="center"><input type="checkbox" class="checkbox" id="listSelectAll" /></th>
								<th width="40" align="center">序号</th>
								<th width="60" align="center">用户名</th>
								<th width="60" align="center">实名</th>
								<th width="40" align="center">性别</th>
								<th width="60" align="center">手机号码</th>
								<th width="120" align="center">注册时间</th>
							</tr>
						</thead>
						<tbody class="loadAjaxData">
						
						</tbody>
					</table>
				</div>
				
				<div class="box jplist-no-results text-shadow align-center"><p>未找到符合条件的记录!</p></div>
				<div class="jplist-ios-button"><i class="fa fa-sort"></i>&nbsp;分页 (显示/隐藏)</div>
				<div class="jplist-panel box panel-bottom">
					<div class="jplist-label" data-type="{start} - {end} of {all}" data-control-type="pagination-info" data-control-name="paging" data-control-action="paging"></div>
					<div class="jplist-pagination" data-control-type="pagination" data-control-name="paging" data-control-action="paging" data-control-animate-to-bottom="true"></div>
					<s:if test="issurpluspage==true">
						<button type="button" id="loadMore" data-mqif-url="vmember/list.action" data-mqif-page="2" onclick="mqif_LoadMorePage($(this));">加载更多</button>
					</s:if>
				</div>
				
				
			</div>
		</form>
	</s:if>
</div>
