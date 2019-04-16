<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><spring:message code="app.title"/>-角色管理</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/dic.js"></script>
<script type="text/javascript">
	$(function() {
		//初始化角色表格
		$("#role_grid").datagrid({
			url : "listRoles.action",
			fitColumns : true,
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
				curRoleId = row.roleid;
				addUserRowIds = [];
				delUserRowIds = [];
				
				//根据row.roleid加载用户列表
				queryUserByRole(row.roleid);
				//根据row.roleid加载资源数

				$("#resTree").tree({
					data : getRemoteData("getResTreeByRole.action",{roleId : row.roleid}),
					checkbox :true,
					onClick : function(node){

					}
				});

			}
		});
		
		//初始化用户表格
		$("#user_grid").datagrid({
			url : "listUsersByRole.action",
			fitColumns : true,
			striped : true,
			rownumbers : true,
			idField : "userid",
			singleSelect : true,
			collapsible : true,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			pageList : [10,20,30,50],
			queryParams : {roleId : "",pageNo : 1, pageSize : 15},
			onBeforeLoad : function(params){
				params.pageNo = $("#user_grid").datagrid("options").pageNumber;
				params.pageSize = $("#user_grid").datagrid("options").pageSize;
			}
		});
		
		//初始化选择用户表格
		$("#userselect_grid").datagrid({
			url : "listUsers.action",
			fitColumns : true,
			striped : true,
			rownumbers : true,
			idField : "userid",
			singleSelect : true,
			collapsible : true,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			pageList : [10,20,30,50],
			queryParams : {orgCode : "",keyText : "",pageNo : 1, pageSize : 15},
			onBeforeLoad : function(params){
				params.pageNo = $("#user_grid").datagrid("options").pageNumber;
				params.pageSize = $("#user_grid").datagrid("options").pageSize;
			},
			onDblClickRow : function(index,row){
				if($("#user_grid").datagrid("getRowIndex",row.userid) >= 0){
					$.messager.alert("Error","用户已存在!");
					return ;
				}
				addUserRowIds.push(row.userid);
				$("#user_grid").datagrid("appendRow",row);
				$("#userselect_dlg").dialog("close");
			}
		});
		
		$("#usg_orgCode").combotree("loadData",getOrganTree());
	});

	var params = {
		roleName : "",
		pageNo : 1,
		pageSize : 15
	};

	function setQueryParams(params) {
		params.pageNo = $("#role_grid").datagrid("options").pageNumber;
		params.pageSize = $("#role_grid").datagrid("options").pageSize;
	}

	function query() {
		var opts = $("#role_grid").datagrid("options");
		var params = opts.queryParams;
		params.roleName = $("#roleName").val();
		$("#role_grid").datagrid("load");
	}
	
	function queryUserByRole(roleId) {
		var opts = $("#user_grid").datagrid("options");
		var params = opts.queryParams;
		params.roleId = roleId;
		$("#user_grid").datagrid("load");
	}
	
	var action = "";

	function add() {
		$("#role_dlg").dialog("open").dialog("setTitle", "新增");
		$("#fm").form("clear");
		action = "add";
	}

	function edit() {
		var row = $("#role_grid").datagrid("getSelected");

		if (!row) {
			$.messager.show({
				title : "Error",
				msg : "请选择行！"
			});
			return;
		}

		action = "update";

		$("#fm").form("clear");
		$("#fm").form("load", row);
		$("#role_dlg").dialog("open").dialog("setTitle","编辑");
	}

	function remove() {
		var row = $("#role_grid").datagrid("getSelected");

		if (!row) {
			$.messager.show({
				title : "Error",
				msg : "请选择行！"
			});
			return;
		}

		$.messager.confirm("Tips", "您确定要执行操作吗？", function(data) {
			if (data) {
				$.post('removeRole.action', {
					roleid : row.roleid
				}, function(result) {
					$("#role_grid").datagrid("reload");
				}, 'json');
			}
		});
	}

	function save() {
		$("#fm").form("submit", {
			url : "saveRole.action?action=" + action,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval("(" + result + ")");
				if (result.success) {
					$("#role_dlg").dialog("close");
					$("#role_grid").datagrid("reload");
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
		$("#role_dlg").dialog("close");
	}
	
	var curRoleId = null;
	var addUserRowIds = [];
	var delUserRowIds = [];
	
	function addUser(){
		if(!curRoleId){
			$.messager.alert("Error","请选择角色！");
			return ;
		}
		$("#userselect_dlg").dialog("open").dialog("setTitle","选择用户");
	}
	
	function delUser(){
		var row = $("#user_grid").datagrid("getSelected");
		
		if(!row){
			$.messager.show({title : "Error",msg : "请选择行！"});
			return;
		}
		
		delUserRowIds.push(row.userid);
		var inx = $("#user_grid").datagrid("getRowIndex",row);
		$("#user_grid").datagrid("deleteRow",inx);
		
	}
	
	function searchUser(){
		var opts = $("#userselect_grid").datagrid("options");
		var params = opts.queryParams;
		params.orgCode = $("#usg_orgCode").combobox("getValue");
		params.keyText = $("#usg_keyText").val();
		$("#userselect_grid").datagrid("load");
	}
	
	function saveUser(){
		$.ajax({
			type : "get",
			url : "saveRoleUsers.action",
			data : {addRowIds : addUserRowIds,delRowIds : delUserRowIds,roleId : curRoleId},
			traditional: true,//这里设置为true
			async : true,
			dataType : "json",
			timeout : 1000, // 设定超时
			cache : false, // 禁用缓存
			success : function(result) {
				if(result.success){
					$.messager.alert("Tips","保存成功！");
				}else{
					$.messager.alert("Error",result.msg);
				}
			}
		});
	}
	
	function saveRoleResources(){
		if(!curRoleId){
			$.messager.alert("Error","请选择角色！");
			return ;
		}
		
		var nodes = $("#resTree").tree("getChecked",["checked","indeterminate"]);
		
		if(nodes && nodes.length > 0){
			var resids = [];
			for(var i = 0;i < nodes.length;i ++){
				var node = nodes[i];
				resids.push(node.id);
			}
			
			$.ajax({
				type : "get",
				url : "saveRoleResources.action",
				data : {resIds : resids,roleId : curRoleId},
				traditional: true,//这里设置为true
				async : true,
				dataType : "json",
				timeout : 1000, // 设定超时
				cache : false, // 禁用缓存
				success : function(result) {
					if(result.success){
						$.messager.alert("Tips","保存成功！");
					}else{
						$.messager.alert("Error",result.msg);
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'center'">
			<table id="role_grid" data-options="title:'角色列表'"
				style="height:190px;">
				<thead>
					<tr>
						<th data-options="field:'roleid',align:'center',width:200">角色编号</th>
						<th data-options="field:'rolename',align:'center',width:300">角色名称</th>
						<th data-options="field:'remark',align:'center',width:500">说明</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb">
			<label style="margin-left:10px">角色名称：</label> <input
				class="easyui-textbox" id="roleName" /> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="query()">查询</a> &nbsp;&nbsp; <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" onclick="add()">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" onclick="edit()">编辑</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" onclick="remove()">删除</a>
		</div>
		<div id="role_dlg" class="easyui-dialog" closed="true"
			style="width:350px;height:200px" buttons="#dlg_buttons">
			<form id="fm" method="post">
				<table>
					<tr>
						<td><label>角色编号：</label></td>
						<td><input class="easyui-textbox" name="roleid"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td><label>角色名称：</label></td>
						<td><input class="easyui-textbox" name="rolename" /></td>
					</tr>
					<tr>
						<td><label>说明:</label></td>
						<td><textarea name="remark" style="width:172px;height:50px"></textarea>
					</tr>
				</table>
			</form>
			<div id="dlg_buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-save" onclick="save()">保存</a> <a
					href="javaxcript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" onclick="cancel()">取消</a>
			</div>
		</div>

		<div data-options="region:'south',split:true" style="height:300px">
			<div style="width:65%;height:290px;float:left">
				<table id="user_grid" class="easyui-datagrid" data-options="title:'用户列表',toolbar:[
					{iconCls:'icon-save',handler:function(){saveUser();}},
					{iconCls:'icon-add',handler:function(){addUser();}},
					{iconCls:'icon-remove',handler:function(){delUser();}}]"
					style="height:290px;">
					<thead>
						<tr>
							<th data-options="field:'userid',align:'center',width:80">用户编码</th>
							<th data-options="field:'username',align:'center',width:120">用户名</th>
							<th data-options="field:'personname',align:'center',width:100">姓名</th>
							<th
								data-options="field:'gender',align:'center',width:60,formatter:function(v,r,i){return fmtDic('gender',v);}">性别</th>
							<th data-options="field:'password',hidden:true">密码</th>
							<th data-options="field:'cardnum',align:'center',width:100">卡号</th>
							<th
								data-options="field:'organizecode',align:'center',width:120,formatter:function(v,r,i){return dicText(getOrganTree(),v);}">组织机构</th>
							<th
								data-options="field:'status',align:'center',width:50,formatter:function(v,r,i){return fmtDic('status',v);}">状态</th>
							<th data-options="field:'remark',align:'center'">备注</th>
						</tr>
					</thead>
				</table>
				
				<div id="userselect_dlg" class="easyui-dialog" style="width:750px;height:350px" closed="true">
					<table id="userselect_grid" class="easyui-datagrid" data-options="title:'用户列表',toolbar:'#usg_tb'" style="height:310px;">
						<thead>
							<tr>
								<th data-options="field:'userid',align:'center',width:80">用户编码</th>
								<th data-options="field:'username',align:'center',width:120">用户名</th>
								<th data-options="field:'personname',align:'center',width:100">姓名</th>
								<th data-options="field:'gender',align:'center',width:60,formatter:function(v,r,i){return fmtDic('gender',v);}">性别</th>
								<th data-options="field:'password',hidden:true">密码</th>
								<th data-options="field:'cardnum',align:'center',width:100">卡号</th>
								<th data-options="field:'organizecode',align:'center',width:120,formatter:function(v,r,i){return dicText(getOrganTree(),v);}">组织机构</th>
								<th data-options="field:'status',align:'center',width:50,formatter:function(v,r,i){return fmtDic('status',v);}">状态</th>
								<th data-options="field:'remark',align:'center'">备注</th>
							</tr>
						</thead>
					</table>
					<div id="usg_tb">
						<label>组织机构：</label>
						<input class="easyui-combotree" id="usg_orgCode"/>
						<label>用户名/姓名：</label>
						<input class="easyui-textbox" id="usg_keyText"/>
						&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchUser()">查询</a>
					</div>
				</div>
				
			</div>

			<div style="width:33%;height:290px;float:right">
				<div class="easyui-panel" title="资源授权" 
					data-options="tools:[{iconCls:'icon-save',handler:function(){saveRoleResources();}}]">
					<ul id="resTree" class="easyui-tree" style="height:90%"></ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
