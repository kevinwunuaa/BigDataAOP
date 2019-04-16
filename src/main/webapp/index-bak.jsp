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
                <!-- 
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-info" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
                -->
            </div>
        </div>
    </div>
    
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
                <!-- 
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-info" data-dismiss="modal"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
                -->
            </div>
        </div>
    </div>
    <!-- 计算器 -->
    <div class="modal fade" id="myCalculatorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document" style="width:310px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">计算器</h4>
                </div>
                <div class="modal-body">
                	<div style="margin:5px 10px">
	                	<div class="row">
	                		<div style="position:relative;height:60px;width:289px;text-align:right;margin-bottom:5px;background:white;border:1px solid black">
								<div id="calResult" style="position: absolute; bottom: 5px; right: 5px;font-size:large">0</div>
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
		<!--  
		<div class="navbar-custom-menu pull-left">
			<ul class="nav navbar-nav">
				<li><a>${user.personname }&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-circle text-success"></i> 在线</a> </li>
			</ul>
		</div>
		-->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				<!--  
				<li class="dropdown messages-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-envelope-o"></i> <span class="label label-success">4</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">You have 4 messages</li>
						<li>
							
							<ul class="menu">
								<li>
									<a href="#">
										<div class="pull-left">
											<img
												src="${pageContext.request.contextPath}/static/AdminLTE/dist/img/user2-160x160.jpg"
												class="img-circle" alt="User Image">
										</div>
										<h4>
											Support Team <small><i class="fa fa-clock-o"></i> 5
												mins</small>
										</h4>
										<p>Why not buy a new awesome theme?</p>
								</a>
								</li>
								
								<li><a href="#">
										<div class="pull-left">
											<img
												src="${pageContext.request.contextPath}/static/AdminLTE/dist/img/user3-128x128.jpg"
												class="img-circle" alt="User Image">
										</div>
										<h4>
											AdminLTE Design Team <small><i class="fa fa-clock-o"></i>
												2 hours</small>
										</h4>
										<p>Why not buy a new awesome theme?</p>
								</a></li>
								<li><a href="#">
										<div class="pull-left">
											<img
												src="${pageContext.request.contextPath}/static/AdminLTE/dist/img/user4-128x128.jpg"
												class="img-circle" alt="User Image">
										</div>
										<h4>
											Developers <small><i class="fa fa-clock-o"></i> Today</small>
										</h4>
										<p>Why not buy a new awesome theme?</p>
								</a></li>
								<li><a href="#">
										<div class="pull-left">
											<img
												src="${pageContext.request.contextPath}/static/AdminLTE/dist/img/user3-128x128.jpg"
												class="img-circle" alt="User Image">
										</div>
										<h4>
											Sales Department <small><i class="fa fa-clock-o"></i>
												Yesterday</small>
										</h4>
										<p>Why not buy a new awesome theme?</p>
								</a></li>
								<li><a href="#">
										<div class="pull-left">
											<img
												src="${pageContext.request.contextPath}/static/AdminLTE/dist/img/user4-128x128.jpg"
												class="img-circle" alt="User Image">
										</div>
										<h4>
											Reviewers <small><i class="fa fa-clock-o"></i> 2 days</small>
										</h4>
										<p>Why not buy a new awesome theme?</p>
								</a></li>
							</ul>
						</li>
						<li class="footer"><a href="#">See All Messages</a></li>
					</ul></li>
				--> 
				<li class="dropdown">
		            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" onclick="addTab('home','首页','html/home.html')">
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
					<!--  
					<a href="javascript:void(0)" onclick="logout()" style="margin:3px" class="btn btn-sm btn-danger" aria-label="Left Align">
					  <span class="glyphicon glyphicon-off" aria-hidden="true">&nbsp;退出</span>
					</a>
					-->
					<a  href="javascript:;" alt="退出" onclick="logout()" class="glyphicon glyphicon-off" aria-hidden="true"></a>
				</li>	
				<!-- Notifications: style can be found in dropdown.less -->

				<!-- Tasks: style can be found in dropdown.less -->

				<!-- User Account: style can be found in dropdown.less -->
				<li>
					<!-- 
					<a href="#" data-toggle="control-sidebar" style="margin:3px" class="btn btn-sm btn-success" aria-label="Left Align">
					 <span class="glyphicon glyphicon-cog" aria-hidden="true">&nbsp;换肤</span>
					</a>
					-->
					<a href="#" style="margin:3px" alt="换肤" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
		</div>

		</nav> </header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- Sidebar user panel -->
		<!--  
		<div class="user-panel">
			<div class="pull-left image">
				<img
					src="${pageContext.request.contextPath}/static/image/doctor160x160.png"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>${user.personname }</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>
		-->
		<!-- search form --> <!--  
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				--> <!-- /.search form --> <!-- my sidebar menu -->
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
			<ul class="nav nav-tabs custom-nav-tabs" role="tablist" style="height: 34px;">
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu" id="contextMenu"> 
					<li><a href="javascript:void(0)" onclick="closeAll()">关闭所有</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="javascript:void(0)" onclick="closeLeft()">关闭左侧</a></li>
					<li><a href="javascript:void(0)" onclick="closeRight()">关闭右侧</a></li>
				</ul> 
			</ul>
			<div class="tab-content custom-tab-content" style="width:100%;"></div>
		</div>
		<!-- /.content-wrapper -->
		<!--  
		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0
		</div>
		<strong>Copyright &copy; 2018-2019 <a href="#">Big Data
				Administrative Office Platform</a>.
		</strong> All rights reserved. </footer>
		-->
		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark"> <!-- Create the tabs -->
		<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
			<!--  <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
					class="fa fa-home"></i></a></li>
			<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
					class="fa fa-gears"></i></a></li>
					-->
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
			
			var item = {
				'id' : '0',
				'name' : '首页',
				'url' : 'html/home.html',
				'closable' : false
			};
			closableTab.addTab(item);
			

			$(".sidebar-menu .treeview:first").addClass("active menu-open");

			/*
			$(".custom-nav-tabs").mousedown(
					function(e) {
						if (3 == e.which) {
							document.oncontextmenu = function() {
								return false;
							}
							$("#contextMenu").hide();
							$("#contextMenu").attr(
									"style",
									"display: block; position: fixed; top:"
											+ e.pageY + "px; left:" + e.pageX
											+ "px; width: 180px;");
							$("#contextMenu").show();
						}
					});
			$(".custom-nav-tabs").click(function(e) {
				$("#contextMenu").hide();
			});
			*/
		});
		
		function traverse(root){
			var path = root.resname;
			if(root.children && root.children.length > 0){
				for(var i = 0; i < root.children.length; i ++){
					root.children[i].resname = path + "->>" + root.children[i].resname;
					traverse(root.children[i]);
				}
			}
		}
		
		function findNode(root,id){
			if(id == root.resid){
				return root;
			}
			if(root.children && root.children.length > 0){
				for(var i = 0; i < root.children.length; i ++){
					var node = findNode(root.children[i],id);
					if(node){
						return node;
					}
				}	
			}
			
			return null;
		}
		
		function addTab(id, name, url) {
			var root = window.appContext.user.tree.root;
			var node = findNode(root,id);
			if(node){name = node.resname.replace(root.resname + "->>","");}
			$(".custom-nav-tabs > li").remove();
			$(".custom-tab-content > .tab-pane").remove();
			$(".sidebar-menu li a").removeClass("btn btn-info");
			$("#menuitem_" + id + " a").addClass("btn btn-info");
			var item = {
				'id' : id,
				'name' : name,
				'url' : url,
				'closable' : false
			};
			closableTab.addTab(item);
		}
		
		function modifyPwd() {// 密码修改
			addTab('modify-pwd','修改密码','modifyPwdPage.action');
		}

		function logout() {
			if (confirm('确认退出吗?')) {
				$.post("logout.action", {}, function(result) {
					if (result.success) {
						window.top.location = "loginPage.action";
					} else {
						alert(result.msg);
					}
				}, "json");
			}
		}
		
		function closeAll(){
			$(".custom-nav-tabs > li:gt(0)").remove();
			$(".custom-tab-content > .tab-pane:gt(0)").remove();
			
			$(".custom-nav-tabs > li:first").addClass("active");
			$(".custom-tab-content > .tab-pane:first").addClass("active");
		}
		
		function closeLeft(){
			var curtab = $(".custom-nav-tabs > li.active");
			
			var prevtab = $(curtab).prev("li");
			
			var temptab = null;
			
			while(prevtab){
				if($(prevtab).attr("id") == "tab_seed_0"){
					break;
				}
				
				temptab = prevtab;
				prevtab = $(temptab).prev("li");
				$(temptab).remove();
			}
			
			
			var curcontent = $(".custom-tab-content > .tab-pane.active");
			var prevcontent = $(curcontent).prev(".tab-pane");
			var tempcontent = null;
			
			while(prevcontent){
				if($(prevcontent).attr("id") == "tab_container_0"){
					break;
				}
				
				tempcontent = prevcontent;
				prevcontent = $(tempcontent).prev(".tab-pane");
				$(tempcontent).remove();
			}
		}
		
		function closeRight(){
			$(".custom-nav-tabs > li.active").nextAll().remove();
			$(".custom-tab-content > .tab-pane.active").nextAll().remove();
		}
		
		function switchRank(){
			$("#myModalLabel").text("切换身份");
			$('#myModal').modal();
		}
		
		function setRank(target){
			$("#myRank").html($(target).html());
			$('#myModal').modal("hide");
		}
		
		//日历显示
		function myCalendar(){
			$('#myCalendarModal').modal();
			goToday();
		}
		
		//计算器
		function myCalculator(){
			$('#myCalculatorModal').modal();
		}
		
		function inputBtn(el){
			var reg=/^\d+(\.*\d{0,2})([+*/-\\%]\d+(\.*\d{0,2}))+$/
			var expr = $("#calResult").html();
			var inStr = $(el).html();
			
			switch(inStr){
			case "AC":
				$("#calResult").html("0");
				return ;
			case "RT":
				if(expr.length > 1){
					$("#calResult").html(expr.substr(0,expr.length - 1));
				}else{
					$("#calResult").html("0");
				}
				return ;
			case "+/-":
				return ;
			case "=":
				$("#calMessage").empty();
				if(reg.test(expr)){
					eval("var result = " + expr);
					$("#calResult").html(result);
				}
				else{
					$("#calMessage").html('<span class="label label-warning">算式不符合规范，请检查！</span>');
				}
				return ;
			default:
				break;
			}
			
			var tempResult = expr + inStr;
			
			if(expr == "0"){
				tempResult = inStr;
			}
			
			$("#calResult").html(tempResult);
		}
		
		//判断闰年
		function runNian(_year) {
		    if(_year%400 === 0 || (_year%4 === 0 && _year%100 !== 0) ) {
		        return true;
		    }
		    else { return false; }
		}
		
		//判断某年某月的1号是星期几
		function getFirstDay(_year,_month) {
		    var allDay = 0, y = _year-1, i = 1;
		    allDay = y + Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400) + 1;
		    for ( ; i<_month; i++) {
		        switch (i) {
		            case 1: allDay += 31; break;
		            case 2: 
		                if(runNian(_year)) { allDay += 29; }
		                else { allDay += 28; }
		                break;
		            case 3: allDay += 31; break;
		            case 4: allDay += 30; break;
		            case 5: allDay += 31; break;
		            case 6: allDay += 30; break;
		            case 7: allDay += 31; break;
		            case 8: allDay += 31; break;
		            case 9: allDay += 30; break;
		            case 10: allDay += 31; break;
		            case 11: allDay += 30; break;
		            case 12: allDay += 31; break;
		        }
		    }
		    return allDay%7;
		}
		
		//清除内容
		function clearBody(){
			$("#myCalendar tbody").empty();
		}
		
		//显示日历
		function showCalendar(_year,_month,_day,firstDay) {
			clearBody();
		    var i = 0,
		        monthDay = 0,
		        showStr = "",
		        _classname = "",
		        today = new Date();
		        //月份的天数
		    switch(_month) {
		        case 1: monthDay = 31; break;
		        case 2:
		            if(runNian(_year)) { monthDay = 29; }
		            else { monthDay = 28; }
		            break;
		        case 3: monthDay = 31; break;
		        case 4: monthDay = 30; break;
		        case 5: monthDay = 31; break;
		        case 6: monthDay = 30; break;
		        case 7: monthDay = 31; break;
		        case 8: monthDay = 31; break;
		        case 9: monthDay = 30; break;
		        case 10: monthDay = 31; break;
		        case 11: monthDay = 30; break;
		        case 12: monthDay = 31; break;
		    }

		    //输出日历表格，这部分因结构而异
		    showStr = "<tr>";
		    //当月第一天前的空格
		    for (i=1; i<=firstDay; i++) {
		        showStr += "<td></td>";
		    }
		    //显示当前月的天数
		    for (i=1; i<=monthDay; i++) {
		    	//debugger;
 
		        //当日的日期
		        if(_year === today.getFullYear() && _month === today.getMonth()+1 && i === today.getDate()) {
		            _classname = "info"; 
		        } 
		        //当日之前的日期（这个判断是因为我有工作需求，就是要求之前的日期不能点击）
		        else if(_year < today.getFullYear() || (_year === today.getFullYear() && _month <= today.getMonth()) || (_year === today.getFullYear() && _month === today.getMonth()+1 && i < today.getDate()) ) {
		            _classname = "cld-old";
		        }
		        //其他普通的日期
		        else { _classname = "cld-day"; }
		        //其他大于当月的月份的相同日期（为了让点击下一月的时候，相同的日期增加cld-cur类）
		        if(_day === i && (_year > today.getFullYear() || _month > today.getMonth()+1)) { _classname = "cld-cur"; }
		        //把日期存在对应的value       
		        showStr += "<td class=" + _classname + " value='" + _year + "-" + _month + "-" + i + "'>" + i + "</td>";

		        firstDay = (firstDay+1)%7;
		        if(firstDay === 0 && i !== monthDay) {
		            showStr += "</tr><tr>";
		        } 
		    }
		    
		    //剩余的空格
		    if(firstDay!==0) {
		        for (i=firstDay; i<7; i++) {
		            showStr += "<td></td>";
		        }
		    }
		        
		    showStr +="</tr>";
		    //插入calendar的页面结构里
		   	$("#myCalendar tbody").html($(showStr));
		}
		
		//显示年月日
		function showDate(_year,_month,_day) {
		    var date = "",curDate = "", firstDay = getFirstDay(_year,_month,_day);
		    if(_day !== 0) {
		        date = _year + "年" + _month + "月" +_day + "日";
		        curDate = _year + "-" + _month + "-" +_day;
		    }
		    else { date = "No Choose."; }
		    $("#curDate").val(curDate);
		    $("#showDate").html(date); //日历头部显示
		    showCalendar(_year,_month,_day,firstDay);         //调用日历显示函数
		}
		
		//今天
		function goToday(){
			var today = new Date();
			var _year = today.getFullYear(),
			    _month = today.getMonth() + 1,
			    _day = today.getDate();
			
			showDate(_year,_month,_day)
		}
		
		//上一月
		function preMonth() {
			var dateArr = $("#curDate").val().split("-");
			var _year = dateArr[0] - 0,_month = dateArr[1] - 0,_day = dateArr[2] - 0;
		    if(_month == 1) { showDate(_year - 1,12,_day); }
		    else { showDate(_year,_month - 1,_day); }
		}
		//下一月
		function nextMonth() {
			var dateArr = $("#curDate").val().split("-");
			var _year = dateArr[0] - 0,_month = dateArr[1] - 0,_day = dateArr[2] - 0;
		    if(_month == 12) { showDate(_year + 1,1,_day); }
		    else { showDate(_year,_month + 1,_day); }
		}
		
	</script>
</body>
</html>
