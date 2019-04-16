<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文档管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		//初始化表格
		$("#doc_grid").datagrid({
			url : "docList.action",
			//fitColumns : true,
			striped : true,
			toolbar : "#tb",
			rownumbers : true,
			singleSelect : true,
			collapsible : true,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			pageList : [ 10, 20, 30, 50 ],
			queryParams : params,
			onBeforeLoad : setQueryParams,
			onClickRow : function(index, row) {
				//$("#fm").form("clear").form("load",row);
			}
		});
		
	});

	var params = {
		keyText : "",
		pageNo : 1,
		pageSize : 15
	};

	function setQueryParams(params) {
		params.pageNo = $("#doc_grid").datagrid("options").pageNumber;
		params.pageSize = $("#doc_grid").datagrid("options").pageSize;
	}

	function query() {
		var opts = $("#doc_grid").datagrid("options");
		var params = opts.queryParams;
		params.keyText = $("#keyText").val();
		$("#doc_grid").datagrid("load");
	}
</script>
</head>
<body>
	<table id="doc_grid" data-options="title:'文档列表'" style="height:480px;">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'zid',align:'center',width:80">唯一zid</th>
				<th data-options="field:'lb',align:'center',width:120">类别</th>
				<th data-options="field:'mlmc',align:'center',width:100">目录名称</th>
				<th data-options="field:'wdmj',align:'center',width:60">文档密级</th>
				<th data-options="field:'wddw',align:'center',width:60">发文单位</th>
				<th data-options="field:'wdwh',align:'center',width:100">文号</th>
				<th data-options="field:'wdmc',align:'center',width:120">文档名称</th>
				<th data-options="field:'wdhz',align:'center',width:120">文档后缀</th>
				<th data-options="field:'zt',align:'center',width:150">状态</th>
				<th data-options="field:'czy',align:'center',width:150">操作员</th>
				<th data-options="field:'rqtime',align:'center',width:150">录入日期</th>
				<th data-options="field:'jhczy',align:'center',width:150">审核人</th>
				<th data-options="field:'jhtime',align:'center',width:150">审核日期</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding : 5px">
		<input class="easyui-textbox" id="keyText" style="margin-left:8px;width:300px"/>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
	</div>
</body>
</html>
