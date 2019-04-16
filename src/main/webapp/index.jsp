<%@ page language="java"
	import="java.util.*,com.bdaop.context.AppContext"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.bdaop.vo.*"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	User user = (User) session.getAttribute(AppContext.SESSION_USER);
	if (user != null) {

	} else {
		request.getRequestDispatcher("loginPage.action").forward(
				request, response);
		return;
	}
	ResourceTree tree = user.getResourceTree();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" href="favicon.ico">
<title><spring:message code="app.title" />-首页</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/bower_components/Ionicons/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/bower_components/jvectormap/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/AdminLTE/dist/css/skins/_all-skins.min.css">
<!-- bootstrap gallary -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-gallery/css/baguetteBox.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-gallery/css/gallery-clean.css">
	
<link href="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.css" rel="stylesheet">
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-table/bootstrap-table.min.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<style>
    .customtab{
        width: 100%;
        height: 100%;
        padding:4px;
        font-size:0
    }
    .logotitle ul {
    	line-height: normal;
    	padding-top: 6px;
    }
    
    .logotitle ul li{
    	list-style-type:none;
    }
    
    .bgtitle{
    	font-size: 12;
   	    font-family: -webkit-pictograph;
    }
    
    .smtitle{
    	font-size: initial;	
    }
    
    .menu-selected{
    	background:#666;
    	border:1px solid red;
    }
    
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- 弹出框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width:250px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">切换身份</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <a href="javascript:;" onclick="setRank(this)" class="btn btn-Warning">信息科--科长</a>
                    </div>
                    <div class="form-group">
                       <a href="javascript:;" onclick="setRank(this)" class="btn btn-Warning">部门2--职务2</a>
                    </div>
                     <div class="form-group">
                        <a href="javascript:;" onclick="setRank(this)" class="btn btn-Warning">部门3--职务3</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 日历 -->	
    <div class="modal fade" id="myCalendarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
        <div class="modal-dialog" role="document" style="width:380px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">我的日历</h4>
                </div>
                <div class="modal-body">
                    <span id="showDate" style="margin:0 8px;font-size:large;"></span>
					<input type="hidden" id="curDate"/>
					<button class="btn btn-sm btn-info" onclick="preMonth()">上一月</button>
					<button class="btn btn-sm btn-info" onclick="nextMonth()">下一月</button>
					&nbsp;&nbsp;
					<button class="btn btn-sm btn-danger" onclick="goToday()">返回今天</button>
					<div style="margin-bottom:5px"></div>
					<div class="table-responsive">
						<table id="myCalendar" class="table table-bordered table-condensed">
							<thead>
								<tr class="success">
									<th class="danger">日</th>
									<th>一</th>
									<th>二</th>
									<th>三</th>
									<th>四</th>
									<th>五</th>
									<th class="danger">六</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>
					</div>
                </div>
            </div>
        </div>
    </div>
    <!-- 计算器 -->
    <div class="modal fade" id="myCalculatorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document" style="width:321px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">计算器</h4>
                </div>
                <div class="modal-body">
                	<div style="margin:5px 10px">
	                	<div class="row">
	                		<div style="position:relative;height:60px;width:295px;text-align:right;margin-bottom:5px;background:white;border:1px solid black">
								<div id="calResult" style="position: absolute; bottom: 5px; right: 5px">0</div>
							</div>
	                	</div>
	                	<div class="row">
	                		<button class="btn btn-info" onclick="inputBtn(this)"  style="width:70px;margin-bottom:5px">AC</button>
	                		<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">RT</button>
	                		<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">+/-</button>
	                		<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">/</button>
						</div>
						<div class="row">
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">7</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">8</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">9</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">*</button>
						</div>
						<div class="row">
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">4</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">5</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">6</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">-</button>
						</div>
						<div class="row">
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">1</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">2</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">3</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">+</button>
						</div>
						<div class="row">
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">%</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">0</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">.</button>
							<button class="btn btn-info" onclick="inputBtn(this)" style="width:70px;margin-bottom:5px">=</button>
						</div>
						<div class="row" style="margin-top:8px">
							<span id="calMessage"></span>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
    
	<div class="wrapper">

		<header class="main-header"> <!-- Logo --> <a
			href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><img src="${pageContext.request.contextPath}/photo/logo.png" alt="logo" /></span>
		<!-- logo for regular state and mobile devices --> 
		<span class="logo-lg">
			<div style="float:left;">
		    	<img src="${pageContext.request.contextPath}/photo/logo.png" alt="logo"/>
		    </div>
		    <div class="logotitle">
		    	<ul>
		    		<li class="bgtitle">贵阳市第三人民医院</li>
		    		<li class="smtitle">大数据行政办公平台</li>
		    	</ul>
		    </div>
		</span>
		</a> <!-- Header Navbar: style can be found in header.less --> <nav
			class="navbar navbar-static-top"> <!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a> <!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li class="dropdown">
		            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" onclick="addTab('home','首页','home.action')">
		              	首页
		            </a>
		        </li>
				<li class="dropdown user user-menu">
		            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		              <img
						src="${pageContext.request.contextPath}/static/image/doctor160x160.png"
						class="user-image" alt="User Image">
					  <span class="hidden-xs" id="myRank">信息科--科长</span>
		            </a>
		        </li>
		        <li>
		        	<a href="javascript:void(0)" onclick="switchRank()" style="margin:3px" class="btn btn-sm btn-Success" aria-label="Left Align">
					  	<span class="glyphicon glyphicon-random" aria-hidden="true">&nbsp;切换</span>
					</a>
		        </li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenu1" aria-haspopup="true" aria-expanded="true">
		                                  个人设置
		              <span class="caret"></span>
		            </a>
					<ul class="dropdown-menu" style="position:absolute;width:100%;left:0px" aria-labelledby="dropdownMenu1">
						<li><a href="javascript:void(0)" onclick="modifyPwd()">修改密码</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" id="dropdownMenu2" aria-haspopup="true" aria-expanded="true">
		                                   常用工具                 
		              <span class="caret"></span>
		            </a>
					<ul class="dropdown-menu" style="position:absolute;width:100%;left:0px" aria-labelledby="dropdownMenu2">
						<li><a href="javascript:void(0)" onclick="myCalendar()">日历</a></li>
						<li><a href="javascript:void(0)" onclick="myCalculator()">计算器</a></li>
					</ul>
				</li>			
				<li>
					<a  href="javascript:;" alt="退出" onclick="logout()" class="glyphicon glyphicon-off" aria-hidden="true"></a>
				</li>	

				<li>
					<a href="#" style="margin:3px" alt="换肤" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
		</div>

		</nav> </header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- Sidebar user panel -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">主导航</li>

			<%
				for(ResourceNode node : tree.getRoot().getChildren()){
					out.println(tree.renderNode(node));
				}
			%>
		</ul>

		</section> <!-- /.sidebar --> </aside>

		<!-- Content Wrapper. Contains page content --> 
		<div class="content-wrapper" style="background-color:white">
			<h3 class="content-header"></h3>
			<div class="content-body"></div>
		</div>
		<!-- /.content-wrapper -->
		
		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark"> <!-- Create the tabs -->
		<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
			
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<!-- Home tab content -->
			<div class="tab-pane" id="control-sidebar-home-tab">
				
				<!-- /.control-sidebar-menu -->

				<!-- /.control-sidebar-menu -->

			</div>
			<!-- /.tab-pane -->
			<!-- Stats tab content -->
			<!-- /.tab-pane -->
			<!-- Settings tab content -->
		</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>

	</div>
	<!-- ./wrapper -->
	<!-- jQuery 3 -->
	<script src="${pageContext.request.contextPath}/static/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap 3.3.7 -->
	<script src="${pageContext.request.contextPath}/static/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath}/static/AdminLTE/dist/js/adminlte.min.js"></script>
	<!-- skin setting -->
	<script
		src="${pageContext.request.contextPath}/static/plugins/skin-settings.js"></script>
	<!-- Closable tabs -->
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-closable-tab.js"></script>
	<!-- bootstrap gallery -->
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/bootstrap-gallery/js/baguetteBox.min.js"></script>
	<!-- bootstrap treeview-->
	<script src="https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
		
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

	<!-- global js -->
    <script src="${pageContext.request.contextPath}/static/js/global.js"></script>	
    <!-- index js -->
    <script src="${pageContext.request.contextPath}/static/js/index.js"></script>
	
	<script type="text/javascript">
		$(function() {
			window.appContext = {};
			window.appContext.user =  {
				userid:'<%=user.getUserid()%>',
				username:'<%=user.getUsername()%>',
				personname:'<%=user.getPersonname()%>',
				gender:'<%=user.getGender()%>',
				password:'<%=user.getPassword()%>',
				organizecode:'<%=user.getOrganizecode()%>',
				tree:eval('(<%=tree.toJson()%>)')
			};
			window.appContext.dicMap = {};
			
			traverse(window.appContext.user.tree.root);

			$(".content-header").html('<span class="label label-primary">首页</span>');
			$.get("home.action",function(data){
				$.getScript("${pageContext.request.contextPath}/jsp/home.js?q=" + Math.random(),function(){
					$(".content-body").html(data);
					var homeObj = createClass("home");
					homeObj.init();
				});
			});

			$(".sidebar-menu .treeview:first").addClass("active menu-open");

		});

	</script>
</body>
</html>
