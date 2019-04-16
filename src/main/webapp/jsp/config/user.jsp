<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><spring:message code="app.title" />-用户管理</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/datagrid-export.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/pdfmake.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/vfs_fonts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function(){
		//初始化表格
		$("#user_grid").datagrid({
			url : "listUsers.action",
			//fitColumns : true,
			striped : true,
			toolbar : "#tb",
			rownumbers : true,
			singleSelect : true,
			collapsible : true,
			pagination : true,
			pageSize : 20,
			pageNumber : 1,
			pageList : [10,20,30,50],
			queryParams : params,
			onBeforeLoad : setQueryParams,
			onClickRow : function(index,row){
				$("#fm").form("clear").form("load",row);
			}
		});
		
		//初始化机构
		$("#tree").tree({
			data : getOrganTree(),
			onClick : function(node){
				orgCode = node.id;
				query();
			}
		});
		
		$("#gender").combobox({valueField:"id",textField:"text",data:dicItems("gender")});
		$("#organizecode").combotree("loadData",getOrganTree());
		$("#status").combobox({valueField:"id",textField:"text",data:dicItems("status")});
	});

	var orgCode = null;
	var params = {
			orgCode : "",
			keyText : "",
			pageNo : 1,
			pageSize : 15
	};
	
	function setQueryParams(params){
		params.pageNo = $("#user_grid").datagrid("options").pageNumber;
		params.pageSize = $("#user_grid").datagrid("options").pageSize;
	}
	
	function query(){
		var opts=$("#user_grid").datagrid("options");   
		var params = opts.queryParams;
		params.orgCode = orgCode;
		params.keyText = $("#keyText").val();
		$("#user_grid").datagrid("load");
	}
	
	function edit(){
		var row = $("#user_grid").datagrid("getSelected");
		if(!row){
			$.messager.show({title : "Error",msg : "请选择行！"});
			return ;
		}
		
		$("#fm").form("clear");
		$("#fm").form("load",row);
	}
	
	function fmtStatus(v,r,i){
		var result = fmtDic('status',v);
		if(v == 0){
			result = '<font style="color:green">' + result + '</font>';		
		}else{
			result = '<font style="color:red;text-decoration:line-through;">' + result + '</font>';	
		}
		
		return result;
	}
	
	function unlock(){
		var row = $("#user_grid").datagrid("getSelected");
		if(!row){
			$.messager.alert("Error","请选择行！");
			return ;
		}
		
		if(row.status == 0){
			$.messager.alert("Error","用户状态正常！");
			return ;
		}
		
		$.ajax({
			type : "get",
			url : "unlockUser.action",
			data : {userId : row.userid},
			traditional: true,//这里设置为true
			async : true,
			dataType : "json",
			timeout : 1000, // 设定超时
			cache : false, // 禁用缓存
			success : function(result) {
				if(result.success){
					$.messager.alert("Tips","操作成功！");
					$("#user_grid").datagrid("reload");
				}else{
					$.messager.alert("Error",result.msg);
				}
			}
		});
	}
	
	function importXls(){
		$("#file-dlg").dialog("open").dialog("setTitle", "导入Excel");
	}
	
	function saveFile(){
		if ($("#myfile").val() == "") {
			alert("Please select a file to upload");
			return;
		}
		$.ajaxFileUpload({
			url : 'importUsers.action',
			type : 'post',
			secureuri : false,
			fileElementId : 'myfile',
			dataType : 'json',
			async : true, //是否是异步
			success : function(result, status) {
				if (result.success) {
					$("#user_grid").datagrid("loadData",result.data);
				} else {
					$.messager.show({
						title : "error",
						msg : result.msg
					});
				}
				$('#myfile').val('');
				$("#file-dlg").dialog("close");
			}
		});
	}
	
	 function toPdf() {
	     var body = $('#user_grid').datagrid('toArray');
	     var docDefinition = {
	    	 pageSize: 'A3',
	         content: [{
	             table: {
	                 headerRows: 1,
	                 widths: ['auto', 'auto', 'auto', 'auto', 'auto', 'auto','auto', 'auto', 'auto'],
	                 body: body
	             }
	         }],
	         defaultStyle: {
	             font: 'FZYTK'
	         },
	         styles: {
	             per_info_header: {
	                 fontSize: 24,
	                 alignment: 'center'
	             },
	             per_info: {
	                 alignment: 'center'
	             }
	         }
	     };

	     pdfMake.fonts = {
	         Roboto: {
	             normal: 'Roboto-Regular.ttf',
	             bold: 'Roboto-Medium.ttf',
	             italics: 'Roboto-Italic.ttf',
	             bolditalics: 'Roboto-Italic.ttf'
	         },
	         /*
	         msyh: {
	             normal: 'msyh.ttf',
	             bold: 'msyh.ttf',
	             italics: 'msyh.ttf',
	             bolditalics: 'msyh.ttf'
	         },*/
	         FZYTK: {
	             normal: 'FZYTK.ttf',
	             bold: 'FZYTK.ttf',
	             italics: 'FZYTK.ttf',
	             bolditalics: 'FZYTK.ttf'
	         }
	     };

	     pdfMake.createPdf(docDefinition).open();
	     
	     // pdfMake.createPdf(docDefinition).download("test.pdf");
	 }	 
</script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="file-dlg" class="easyui-dialog"
			style="width:300px;height:150px;" closed="true" buttons="#upload-btn">
			<div id="upload-btn">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveFile()">保存</a>
			</div>
			<input type="file" id="myfile" name="myfile"/>
		</div>
		<div data-options="region:'west',split:true" style="width:180px"
			title="机构信息">
			<ul id="tree"></ul>
		</div>
		<div data-options="region:'center'">
			<table id="user_grid" data-options="title:'用户列表'"
				style="height:280px;">
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
							data-options="field:'status',align:'center',width:120,formatter:fmtStatus">状态</th>
						<th data-options="field:'remark',align:'center',width:150">备注</th>
					</tr>
				</thead>
			</table>
			<div id="tb" style="padding : 5px">
				<input class="easyui-textbox" id="keyText"
					style="margin-left:8px;width:300px" />&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="query()">查询</a> &nbsp;&nbsp;
				<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">新增</a> -->
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" onclick="edit()">查看</a>
				<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()">删除</a> -->
				&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="unlock()">启用/解锁</a> &nbsp;&nbsp; 
				<a href="javascript:;" class="easyui-linkbutton" onclick="importXls()">导入</a>	
				<a href="javascript:;" class="easyui-linkbutton" onclick="$('#user_grid').datagrid('toExcel','dg.xls')">导出Excel</a>
				<a href="javascript:;" class="easyui-linkbutton" onclick="toPdf()">导出PDF</a>
				<a href="javascript:;" class="easyui-linkbutton" onclick="$('#user_grid').datagrid('print','DataGrid')">打印</a>
			</div>
			<br />
			<div class="easyui-panel" title="用户信息">
				<form id="fm" method="post">
					<table style="padding:8px">
						<tr>
							<td><label>用户编码：</label></td>
							<td><input class="easyui-textbox" name="userid" /></td>
							<td><label>用户名：</label></td>
							<td><input class="easyui-textbox" name="username" /></td>
							<td><label>姓名：</label></td>
							<td><input class="easyui-textbox" name="personname" /></td>
						</tr>
						<tr>
							<td><label>性别：</label></td>
							<td><input class="easyui-combobox" name="gender" id="gender" /></td>
							<td><label>密码：</label></td>
							<td><input class="easyui-textbox" name="password"
								type="password" /></td>
							<td><label>卡号：</label></td>
							<td><input class="easyui-textbox" name="cardnum" /></td>
						</tr>
						<tr>
							<td><label>组织机构:</label></td>
							<td><input class="easyui-combotree" name="organizecode"
								id="organizecode" /></td>
							<td><label>状态</label></td>
							<td><input class="easyui-combobox" name="status" id="status" /></td>
						</tr>
						<tr>
							<td><label>备注：</label></td>
							<td colspan="5"><textarea name="remark"
									style="height:50px;width:650px"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
