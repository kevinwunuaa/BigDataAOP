<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String swfPath = "upload/test.swf";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/flexpaper_flash.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/flexpaper_flash_debug.js"></script>
<script type="text/javascript"> 
	$(function(){
		var fp = new FlexPaperViewer(     
		         'FlexPaperViewer',  
		         'viewerPlaceHolder', { config : {  
		         SwfFile : escape('${swfPath}'),
				Scale : 0.6,
				ZoomTransition : 'easeOut',
				ZoomTime : 0.5,
				ZoomInterval : 0.2,
				FitPageOnLoad : true,
				FitWidthOnLoad : false,
				FullScreenAsMaxWindow : false,
				ProgressiveLoading : false,
				MinZoomSize : 0.2,
				MaxZoomSize : 5,
				SearchMatchAll : false,
				InitViewMode : 'SinglePage',
		
				ViewModeToolsVisible : true,
				ZoomToolsVisible : true,
				NavToolsVisible : true,
				CursorToolsVisible : true,
				SearchToolsVisible : true,
		
				localeChain : 'en_US'
			}
		});
	});
	
</script>
</script>
<style type="text/css" media="screen">
html,body {
	height: 100%;
}

body {
	margin: 0;
	padding: 0;
	overflow: auto;
	background:#dedede;
}

#flashContent {
	display: none;
}
</style>

<title>文档在线预览</title>
</head>
<body>
	<a id="viewerPlaceHolder" style="width:820px;height:650px;display:block;margin-left:auto;margin-right:auto"></a>
</body>
</html>
