<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>员工维护</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/public.css"></link>
<style type="text/css">
</style>
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		$("#person_grid").datagrid({
			url : "listPersons.action",
			//fitColumns:true,
			striped : true,
			toolbar : "#tb",
			rownumbers : true,
			singleSelect : true,
			collapsible : true,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			pageList : [ 15, 20, 30, 50 ],
			method : "post",
			queryParams : params, //查询条件
			onBeforeLoad : setQueryParams
		});

		query();
	});

	var params = {
		orgCode : "",
		personName : "",
		pageNo : 1,
		pageSize : 15
	};

	function setQueryParams(params) {
		params.pageNo = $("#person_grid").datagrid("options").pageNumber;
		params.pageSize = $("#person_grid").datagrid("options").pageSize;
	}

	function query() {
		var opts = $("#person_grid").datagrid("options");
		var params = opts.queryParams;
		params.orgCode = window.top.appContext.user.organizecode;
		params.personName = $("#personName").val();
		$("#person_grid").datagrid("load");
	}

	var action = "";
	function add() {
		action = "add";
		$("#dlg").dialog("open").dialog("setTitle", "新增");
		$("#fm").form("clear");

		$("#organizcode").combotree("setValue", appContext.user.organizecode);
	}

	function update() {
		action = "update";
		var row = $("#person_grid").datagrid("getSelected");
		if (!row) {
			$.messager.show({
				title : "Error",
				msg : "请选择行！"
			});
			return;
		}

		$.post('loadPerson.action', {
			id : row.id
		}, function(result) {
			$("#dlg").dialog("open").dialog("setTitle", "修改");
			$("#fm").form("clear");

			$("#fm").form("load", result);
		}, 'json');
	}

	function remove() {
		var row = $("#person_grid").datagrid("getSelected");
		if (!row) {
			$.messager.show({
				title : "Error",
				msg : "请选择行！"
			});
			return;
		}

		$.messager.confirm("Tips", "您确定要执行操作吗？", function(data) {
			if (data) {
				$.post('removePerson.action', {
					id : row.id
				}, function(result) {
					$("#person_grid").datagrid("reload");
				}, 'json');
			}
		});

	}

	function save() {
		$("#fm").form("submit", {
			url : "savePerson.action?action=" + action,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval("(" + result + ")");
				if (result.success) {
					$("#dlg").dialog("close");
					$("#person_grid").datagrid("reload");
				} else {
					$.messager.show({
						title : "error",
						msg : result.msg
					});
				}
			}
		});
	}

	function cancel() {
		$("#fm").form("clear");
		$("#dlg").dialog("close");
	}
	
</script>
</head>
<body>
	<table id="person_grid" data-options="title:'员工列表'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">id</th>
				<th data-options="field:'dwbh',align:'center',width:120">单位编号</th>
				<th data-options="field:'dm',align:'center',width:180">代码</th>
				<th data-options="field:'jm',align:'center',width:180">简码</th>
				<th data-options="field:'yhm',width:150">用户名</th>
				<th data-options="field:'mc',width:100">姓名</th>
				<th data-options="field:'xmm',width:100,hidden:true">密码(MD5)</th>
				<th data-options="field:'xb',width:100">性别</th>
				<th data-options="field:'csrq',width:100">出生日期</th>
				<th data-options="field:'sfzh',width:100">身份证号码</th>
				<th data-options="field:'lxfs',width:100">联系方式</th>
				<th data-options="field:'bmmc',width:100">部门名称</th>
				<th data-options="field:'zzmc',width:100">职务名称</th>
				<th data-options="field:'zt',width:100,hidden:true">使用状态</th>
				<th data-options="field:'mmcwcs',width:100,hidden:true">密码错误次数</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<label style="margin-left:15px">员工姓名：</label> 
		<input class="easyui-textbox" id="personName" style="width:300px" />&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="">查询</a> &nbsp;&nbsp; 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">新建</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="update()">修改</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:910px;height:330px;padding:8px;float:left;top:8px"
		closed="true" buttons="#dlg-buttons" modal="true">
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
		</div>
		<form id="fm" method="post">
			<input class="easyui-textbox" name="id" hidden="true" />
			<fieldset>
				<legend><font color="red">基本信息</font></legend>
				<table width="100%">
					<tr>
						<td>单位编号:</td>
						<td><input class="easyui-textbox" name="dwbh" /></td>
						<td>代码:</td>
						<td><input class="easyui-textbox" name="dm" /></td>
						<td>简码:</td>
						<td><input class="easyui-textbox" name="jm" /></td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input id="cardtype" class="easyui-textbox" name="mc" /></td>
						<td>性别:</td>
						<td><input class="easyui-textbox" name="xb" /></td>
						<td>出生日期:</td>
						<td><input class="easyui-textbox" name="csrq" /></td>
					</tr>
					<tr>
						<td>身份证号码:</td>
						<td><input class="easyui-textbox" name="sfzh" /></td>
						<td>联系方式:</td>
						<td><input class="easyui-textbox" name="lxfs" /></td>
						<td>部门名称:</td>
						<td><input class="easyui-textbox" name="bmmc" /></td>
					</tr>
					<tr>
						<td>职务名称:</td>
						<td><input class="easyui-textbox" name="zzmc" /></td>
					</tr>
				</table>
			</fieldset>
			<br/></br>
			<fieldset>
				<legend><font color="red">登录信息</font></legend>
				<table width="100%">
					<tr>
						<td>用户名:</td>
						<td><input class="easyui-textbox" name="yhm" /></td>
						<td>密码:</td>
						<td><input class="easyui-textbox" type="password" name="xmm" /></td>
						<td>使用状态:</td>
						<td><input class="easyui-textbox" name="zt" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>
