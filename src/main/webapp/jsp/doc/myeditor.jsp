<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Ajax &mdash; CKEditor Sample</title>
<meta content="text/html; charset=utf-8" http-equiv="content-type" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	var editor, html = '';

	function createEditor() {
		if (editor)
			return;

		var config = {};
		editor = CKEDITOR.appendTo('editor', config, html);
	}

	function removeEditor() {
		if (!editor)
			return;

		document.getElementById('editorcontents').innerHTML = html = editor
				.getData();
		document.getElementById('contents').style.display = '';

		editor.destroy();
		editor = null;
	}
</script>
</head>
<body>
	<h1 class="samples">CKEditor Sample &mdash; Create and Destroy
		Editor Instances for Ajax Applications</h1>
	<p>Click the buttons to create and remove a CKEditor instance.</p>
	<p>
		<input onclick="createEditor();" type="button" value="Create Editor" />
		<input onclick="removeEditor();" type="button" value="Remove Editor" />
	</p>
	<!-- This div will hold the editor. -->
	<div id="editor"></div>
	<div id="contents" style="display: none">
		<p>Edited Contents:</p>
		<!-- This div will be used to display the editor contents. -->
		<div id="editorcontents"></div>
	</div>
</body>
</html>
