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

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=basePath%>css/learunui-framework.css" rel="stylesheet" />
<link href="<%=basePath%>css/learunui-login.css" rel="stylesheet" />
<link href="<%=basePath%>css/zzsc.css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery-1.8.1.min.js"></script>
</head>

<body class="Loginbody">
	<div class="logo" style="color:white;">
		<div>
			<div style="height: 50px; text-align: center; width:380px;">
				<div>
					<span style="font-size: 24px;width:310px;">军 事 头 条 管 理 系 统</span>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="Loginform">
			<div class="form-message"></div>
			<div class="form-account">
				账户 <input id="txtaccount" type="text" />
			</div>
			<div class="form-password">
				密码 <input id="txtpassword" type="password" />
			</div>
			<div class="form-bottom">
				<div id="btlogin" class="btlogin"></div>
			</div>
		</div>
	</div>
	<script>
		$('#btlogin').click(function() {
			var username = $('#txtaccount').val();
			var password = $('#txtpassword').val();
			$.ajax({
				url : '<%=basePath%>AdminLogin',
				type : 'post',
				data : {
					username : username,
					password : password
				},
				dataType : 'json',
				success : function(data) {
					if (data.status == 1) {
						alert('登录成功')
						window.location.href = '<%=basePath%>page/AddArticle.jsp';
					} else {
						alert(data.prompt);
					}
				}
			})
		})
	</script>
</body>
</html>
