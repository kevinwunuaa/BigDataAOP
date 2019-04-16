<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="favicon.ico">

<title><spring:message code="app.title" />-登录页</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="static/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="static/css/signin.css" rel="stylesheet">
<style type="text/css">
	html,body{height: 100%;width: 100%;margin:0;padding:0;}
	body{
		background:url(static/image/bigdata.png)no-repeat;
	    width:100%;
	    height:100%;
	    background-size:100% 100%;
	    position:absolute;
	    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='static/image/bigdata.png',sizingMethod='scale');
	}
	
	body .container{
		margin:10% auto;   
	    width:100%;  
	    height:50%;
	}
</style>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="static/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="static/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">
		<form class="form-signin" method="post" action="login.action">
			<h3><span class="label label-danger">${message }</span></h3>
			<h2 class="form-signin-heading"><span class="label label-primary">请登录</span></h2>
			<label for="inputUsername" class="sr-only">Email address</label> <input
				id="inputUsername" name="username"  class="form-control" placeholder="用户名|工号|证件号"
				required autofocus> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password"
				id="inputPassword" name="password" class="form-control" placeholder="密码" required>
			<!--  
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			-->
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->
	<!-- jQuery 3 -->
	<script
		src="static/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="static/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript">
		function login() {

			var username = $("#inputUsername").val();
			var password = $("#inputPassword").val();

			$.ajax({
				method : "post",
				url : "login.action",
				async : false,
				data : {
					username : username,
					password : password
				},
				dataType : "json",
				success : function(result) {
					if (result.success) {
						location.href = "index.action";
					} else {
						$.messager.show({
							title : "Error",
							msg : result.msg
						});
					}
				}
			});

		}
	</script>
</body>
</html>
