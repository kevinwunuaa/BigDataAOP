<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><spring:message code="app.title"/>-资源管理</title>
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
	$(function(){
		//加载资源树
		$("#resTree").tree({
			data : getRemoteData("getResTree.action"),
			onContextMenu : function(e,node){
				e.preventDefault();
				$('#treemenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			},
			onClick : function(node){
				var parent = $("#resTree").tree("getParent",node.target);
				
				if(parent){
					$("#parentid").combobox({valueField:"id",textField:"text",
						data:[{id: parent.id,text : parent.text}]});
				}
				var data = node.attributes;
				data.resid = node.id;
				data.resname = node.text;
				$("#fm").form("clear");
				$("#fm").form("load",data);
			}
		});
		
		// 新增树节点
		$("#menu_add").bind("click", function() {
			var selectedNode = $("#resTree").tree("getSelected");
			
			if(!selectedNode){
				$.messager.alert("Error","请选择父节点！");
				return;
			}
			
			if(selectedNode.attributes.reslevel >= 4){
				$.messager.alert("Error","最多支持节点深度为5！");
				return;
			}

			var node = {
					//id : "new1",
					text : "节点1",
					attributes : {
						parentid : selectedNode.id,
						resdomain : selectedNode.attributes.resdomain,
						reslevel : 1 + selectedNode.attributes.reslevel,
						status : "1"
					}
			};
			
			$("#resTree").tree("append",{
				parent : selectedNode.target,
				data : [node]
			});
		});
		
		//删除树节点
		$("#menu_remove").bind("click", function() {
			var selectedNode = $("#resTree").tree("getSelected");
			if(!$("#resTree").tree("isLeaf",selectedNode.target)){
				$.messager.alert("Error","只能删除叶子节点！");
				return ;
			}
			
			var resId = selectedNode.id;
			$("#resTree").tree("remove",selectedNode.target);
			
			$.post("removeResource.action",{resId : resId},function(result){
				if(result.success){
					$.messager.alert("Tips","操作成功！");
				}else{
					$.messager.alert("Tips",result.msg);
				}
			},"json");
		});
		
		//初始化字典
		$("#restype").combobox({valueField : "id",textField : "text",data : dicItems("restype")});
		$("#reslevel").combobox({valueField : "id",textField : "text",data : dicItems("reslevel")});
		$("#resdomain").combobox({valueField : "id",textField : "text",data : dicItems("domain")});
	});
	
	
	function saveResource() {
		$("#fm").form(
				"submit",
				{
					url : "saveResource.action",
					onSubmit : function() {
						return $(this).form("validate");
					},
					success : function(result) {
						var result = eval("(" + result + ")");
						if (result.success) {
							$("#resTree").tree("loadData", getRemoteData("getResTree.action"));
							var node = $("#resTree").tree("find",result.nodeId);
							$("#resTree").tree("select",node.target);
						} else {
							$.messager.alert("Error", result.msg);
						}
					}
				});
	}
</script>
</head>
<body>
	<!-- 上下文菜单 -->
	<div id="treemenu" class="easyui-menu" style="width:120px">
		<div id="menu_add" data-options="iconCls:'icon-add'">新增</div>
		<div id="menu_remove" data-options="iconCls:'icon-remove'">删除</div>
	</div>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'west',split:true" style="width:300px">
			<div class="easyui-panel" title="资源管理">
				<ul id="resTree" class="easyui-tree" style="height:93%"></ul>
			</div>
		</div>
		<div data-options="region:'center'">
			<div class="easyui-panel" data-options="title:'资源信息'">
				<form id="fm" method="post">
					<table style="padding:8px">
						<tr>
							<td><label>资源序号：</label></td>
							<td><input class="easyui-textbox" name="resid" readonly="readonly"/></td>
							<td><label>资源类型：</label></td>
							<td><input class="easyui-combobox" name="restype" id="restype"/></td>
							<td><label>资源名称：</label></td>
							<td><input class="easyui-textbox" name="resname" /></td>
						</tr>
						<tr>
							<td><label>上级资源：</label></td>
							<td><input class="easyui-combobox" name="parentid" id="parentid" readonly="readonly"/></td>
							<td><label>资源级别：</label></td>
							<td><input class="easyui-combobox" name="reslevel" id="reslevel"/></td>
							<td><label>资源URL：</label></td>
							<td><input class="easyui-textbox" name="resurl" /></td>
						</tr>
						<tr>
							<td><label>所属域：</label></td>
							<td><input class="easyui-combobox" name="resdomain" id="resdomain"/></td>
							<td><label>使用状态：</label></td>
							<td><input class="easyui-combobox" name="status" data-options="valueField:'id',textField:'text',data:[{id:'1',text:'正常'},{id:'0',text:'停用'}]"/></td>
							<td><label>顺序号：</label></td>
							<td><input class="easyui-numberbox" name="resorder"/></td>
						</tr>
						<tr>
							<td><label>备注：</label></td>
							<td colspan="5"><textarea name="remark" style="width:100%;height:50px"></textarea></td>
						</tr>
					</table>
					<div id="tb" style="float:right;margin-right:15px">
						<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveResource()">保存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="clear()">清除</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
