<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!-- 页面返回的JSON,ID不允许修改,必须固定ID：mqif_json -->
<div id="mqif_json">${returnInfo}</div>
<!-- 页面返回的JS代码,ID不允许修改,必须固定mqif_js -->
<script id="mqif_js">
$(document).ready(function(){
	mqif_load_table("user/list.action");
});
</script>
<!-- 页面返回的HTML代码,ID不允许修改,必须固定ID：mqif_html -->
<div id="mqif_html">
	<s:if test="returnInfo.get('status')==true">
		<div class="box-title">
			<h3>
				<i class="fa fa-list-ul"></i>平台用户管理
			</h3>
			<a class="btn" href="javascript:mqif_GoUrl('user/add.action');"><i class="fa fa-plus"></i>添加平台用户</a>
		</div>
		<table id="list_table_cache" style="display:none;">
			<tbody>
			<s:iterator value="jsonlist">
				<tr class="tbl-item">
					<td align="center" class="index"><s:property value="get('index')" /></td>
					<td align="center" class="status"><s:property value="get('status')" /></td>
					<td align="center" class="number"><s:property value="get('number')" /></td>
					<%-- <td align="center" class="lv"><s:property value="get('lv')" /></td> --%>
					<td align="center" class="realname"><s:property value="get('realname')" /></td>
					<td align="center" class="nickname"><s:property value="get('nickname')" /></td>
					<td align="center" class="mobile"><s:property value="get('mobile')" /></td>
					<td align="center" class="qq"><s:property value="get('qq')" /></td>
					<td align="left" class="email"><s:property value="get('email')" /></td>
					<td align="center">
						<a href="javascript:mqif_GoUrl('user/edit.action?id=<s:property value="get('uuid')"/>');">编辑</a> | 
						<a href="javascript:mqif_del('user/remove.action?id=<s:property value="get('uuid')"/>','删除提示','您是否要删除（<s:property value="get('name')"/>）记录？');">删除</a>
					</td>
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
							<li><span data-path=".number" data-order="asc" data-type="text">名称 A-Z</span></li>
							<li><span data-path=".number" data-order="desc" data-type="text">名称 Z-A</span></li>
							<li><span data-path=".lv" data-order="asc" data-type="datetime">等级 升序</span></li>
							<li><span data-path=".lv" data-order="desc" data-type="datetime">等级 降序</span></li> 
						</ul>
					</div>
					<div class="text-filter-box">
						<i class="fa fa-search  jplist-icon"></i>
						<input data-path=".realname" type="text" value="" placeholder="真实姓名" data-control-type="textbox" data-control-name="name-filter" data-control-action="filter" />
					</div>
					
				</div>
				<!-- data -->
				<div class="box text-shadow">
					<table class="list_table-tbl">
						<thead>
							<tr>
								<th width="30" align="center">序号</th>
								<th width="50" align="center">状态</th>
								<th width="100" align="center">用户名</th>
								<!-- <th width="80" align="center">等级</th> -->
								<th width="100" align="center">真实姓名</th>
								<th width="100" align="center">昵称</th>
								<th width="100" align="center">手机</th>
								<th width="100" align="center">QQ</th>
								<th width="100" align="center">EMAIL</th>
								<th width="70" align="center">操作</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<div class="box jplist-no-results text-shadow align-center"><p>未找到符合条件的记录!</p></div>
				<div class="jplist-ios-button"><i class="fa fa-sort"></i>&nbsp;分页 (显示/隐藏)</div>
				<div class="jplist-panel box panel-bottom">
				<!-- 	<button type="button" onclick="javascript:_saveSEQ('user/updateseq.action','user/list.action')">保存排序</button> -->
					<div class="jplist-label" data-type="{start} - {end} of {all}" data-control-type="pagination-info" data-control-name="paging" data-control-action="paging"></div>
					<div class="jplist-pagination" data-control-type="pagination" data-control-name="paging" data-control-action="paging" data-control-animate-to-bottom="true"></div>
					<s:if test="issurpluspage==true">
						<button type="button" id="loadMore" data-mqif-url="user/list.action" data-mqif-page="2" onclick="mqif_LoadMorePage($(this));">加载更多</button>
					</s:if>
				</div>
				
				
			</div>
		</form>
	</s:if>
</div>