<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>单位维护</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/easyui/themes/icon.css">
<style type="text/css">

</style>
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function() {
		//载入机构信息
		$.post('loadOrgByCode.action', {
			orgCode : top.appContext.user.organizecode
		}, function(result) {
			$("#fm").form("clear");
			$("#fm").form("load",result);
		}, 'json');
	});
	
	function save() {
		$("#fm").form("submit", {
			url : "saveOrg.action?action=update",
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval("(" + result + ")");
				if (result.success) {
					$.messager.show({
						title : "Tips",
						msg : result.msg
					});
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
	}

	//文件上传
	function upload() {
		$("#photo-dlg").dialog("open").dialog("setTitle", "文件上传");
	}

	function saveImage() {
		if ($("#myfile").val() == "") {
			alert("Please select a file to upload");
			return;
		}
		$.ajaxFileUpload({
			url : 'ajaxLogoUpload.action',
			type : 'post',
			secureuri : false,
			fileElementId : 'myfile',
			dataType : 'json',
			async : true, //是否是异步
			success : function(data, status) {
				if (data.success) {
					$("#photo").val("photo/" + data.fileName);
					/*
					$("#img_photo").attr(
							"src",
							"${pageContext.request.contextPath}/photo/"
									+ data.fileName);
					*/
				} else {
					$.messager.show({
						title : "error",
						msg : data.msg
					});
				}
				$('#myfile').val('');
				$("#photo-dlg").dialog("close");
			}
		});
	}
</script>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true,title:'机构维护'">
		<div id="photo-dlg" class="easyui-dialog"
			style="width:300px;height:150px;" closed="true" buttons="#upload-btn">
			<div id="upload-btn">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveImage()">保存图片</a>
			</div>
			<input type="file" id="myfile" name="myfile"/>
		</div>
		<form id="fm" method="post">
			<input class="easyui-textbox" name="id" hidden="true"/>
			<table width="100%">
				<tr>
					<td>单位编号:</td>
					<td><input class="easyui-textbox" name="dwbh" readonly="readonly"/></td>
					<td>单位名称:</td>
					<td><input class="easyui-textbox" name="dwmc"/></td>
					<td>邮编:</td>
					<td><input class="easyui-textbox" name="dwyb"/></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input class="easyui-textbox" name="dwdz"/></td>
					<td>办公室电话:</td>
					<td><input class="easyui-textbox" name="dwbgsdh"/></td>
					<td>急救中心电话:</td>
					<td><input class="easyui-textbox" name="dwjjzxdh"/></td>
				</tr>
				<tr>
					<td>服务中心电话:</td>
					<td><input class="easyui-textbox" name="dwfwzxdh"/></td>
					<td>级别:</td>
					<td><input class="easyui-textbox" name="dwjb"/></td>
					<td>获取时间:</td>
					<td><input class="easyui-textbox" name="dwjbsj"/></td>
				</tr>
				<tr>
					<td>采用评审标准:</td>
					<td><input class="easyui-textbox" name="dwpsbz"/></td>
					<td>法人姓名:</td>
					<td><input class="easyui-textbox" name="dwfr"/></td>
					<td>法人电话:</td>
					<td><input class="easyui-textbox" name="dwfrlxdh"/></td>
				</tr>
				<tr>
					<td>法人身份证号:</td>
					<td><input class="easyui-textbox" name="dwfrsfzh"/></td>
					<td>网址:</td>
					<td><input class="easyui-textbox" name="dwurl"/></td>
					<td>LOGO:</td>
					<td><input class="easyui-textbox" id="photo" name="dwlogo"/></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input id="hostcode" class="easyui-textbox" name="dwemail"/></td>
					<td>修改IP地址:</td>
					<td><input id="tcpip" class="easyui-textbox" name="tcpip"/></td>
					<td></td>
					<td><a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="upload()">上传logo</a></td>
				</tr>
				<tr>
					<td>修改操作员副标题:</td>
					<td><input id="czybt" class="easyui-textbox" name="czybt"/></td>
					<td>修改日期:</td>
					<td><input class="easyui-textbox" name="rqtime"/></td>
					<td>修改操作员:</td>
					<td><input class="easyui-textbox" name="czy"/></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons" style="position:relative;float:right;right:80px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
		</div>
	</div>
</body>
</html>