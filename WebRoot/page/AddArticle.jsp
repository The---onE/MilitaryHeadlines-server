<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加文章</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="<%=basePath%>jquery-1.8.1.min.js" />

</head>

<body>
	标题:
	<input id="title" type="text" />
	<br /> 作者:
	<input id="author" type="text" />
	<br /> 类别:
	<input id="category" type="text" />
	<br /> 内容:
	<textarea id="content"></textarea>
	<br />
	<input id="confirm" type="button" value="提交" />
	<input id="cancel" type="button" value="取消" />
</body>

<script>
	$('#confirm').click(function() {
		var title = $('#title').val();
		var author = $('#author').val();
		var category = $('#category').val();
		var content = $('#content').val();
		$.ajax({
			url : '<%=basePath%>AddArticle',
			type : 'post',
			data : {
				title : title,
				author : author,
				category : category,
				content : content
			},
			dataType : 'json',
			success : function(data) {
				if (data.status == 1) {
					alert('添加成功')
					$('#title').val('');
					$('#author').val('');
					$('#category').val('');
					$('#content').val('');
				} else {
					alert(data.prompt);
				}
			}
		})
	})
	$('#cancel').click(function() {
		$('#title').val('');
		$('#author').val('');
		$('#category').val('');
		$('#content').val('');
	})
</script>
</html>
