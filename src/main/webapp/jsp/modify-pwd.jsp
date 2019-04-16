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
<style type="text/css">
	.fitem {
		float : left;
		width : 300px;
		margin : 5px 30px;
		clear: both;
	}
	
	.fitem label {
		float : left;
		width : 70px;
		font-size : 14px;
		font-weight : bold;
		color : #AE0000;
	}
	
	.fitem input {
		float : right;
	}
</style>
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

	});
	
	function confirmMM() {
		$("#fmmm").form("submit", {
			url : "modifyPwd.action",
			onSubmit : function() {
				var ymm = $("#ymm").val();
				var psw = $("#psw").val();
				var confirmpsw = $("#confirmpsw").val();

				if (!ymm) {
					$.messager.show({
						title : "提示",
						msg : "原始密码不能为空"
					});
					$("#mmmsg").html("原始密码不能为空");
					return false;
				}
				
				var reg = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,20}');
				//var reg = /^[A-Za-z0-9]{6,20}$/;

				if (!reg.test(psw)) {
					$.messager.show({
						title : "提示",
						msg : "6-20位字母数字组合"
					});
					$("#mmmsg").html("6-20位字母数字组合");
					return false;
				}

				if (psw != confirmpsw) {
					$.messager.show({
						title : "提示",
						msg : "两次密码输入不一致"
					});
					$("#mmmsg").html("两次密码输入不一致");
					return false;
				}

				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval("(" + result + ")");
				if (result.success == true) {
					$.messager.show({
						title : "提示",
						msg : "密码修改成功"
					});
					$("#mmmsg").html("");
					$("#mmdlg").dialog("close");
				} else {
					$("#mmmsg").html(result.msg);
					$.messager.show({
						title : "提示",
						msg : result.msg
					}); 
				}
			}
		});

	};

	$.extend($.fn.validatebox.defaults.rules, {
		/*必须和某个字段相等*/
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '字段不匹配'
		}
	});
</script>
</head>
<body> 
	<div class="easyui-panel" title="修改密码">
		<form id="fmmm" method="post">
			<div class="fitem">
				<label>原密码：</label> <input name="ymm" class="easyui-textbox "
					type="password" required="true" id="ymm" />
			</div>
			<div class="fitem">
				<label>密码：</label> <input class="easyui-textbox " type="password"
					name="psw" required="true" id="psw" />
			</div>
			<div class="fitem">
				<label>确认密码：</label> <input class="easyui-textbox " type="password"
					name="confirmpsw" required="true" id="confirmpsw" />
			</div>
			<div class="fitem" style="float:left;color: red;margin-left: 20px;" id="mmmsg"></div>
			<div id="mm-buttons" class="fitem" style="float:right;margin-right:30px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="confirmMM()">确认</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="$('#fmmm').form('clear');">取消</a>
			</div>
		</form>
	</div>
</body>
</html>
