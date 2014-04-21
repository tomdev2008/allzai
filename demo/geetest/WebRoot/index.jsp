<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
body {
	background-color: #9e9;
}

.wrap {
	width: 960px;
	margin: 100px auto;
	font-size: 125%;
}

.row {
	margin: 30px 0;
}
</style>
</head>

<body>
	<div class="wrap">

		<h1>用户注册(<a href='login.jsp'>已有帐户,去登录</a>)</h1>
		<form method="post" action="http://112.124.51.168:8081/allzai_server/public/userRegister">
			<div class="row">
				<label for="name">邮箱</label> <input type="text" id="account"
					name="account" value="test@allzai.com" />
			</div>
			<div class="row">
				<label for="passwd">密码</label> <input type="password" id="password"
					name="password" value="123456" />
			</div>
			<div class="row">
				<script type="text/javascript"
					src="http://api.geetest.com/get.php?gt=de7689d1283867a3b72f3ee0da8641db"></script>
				<script>
/* 定义一个全局的变量保存验证结果 */
/* 未通过情况下不允许提交 */
/* 通过:1, 未通过:0 */
function gt_custom_ajax(result, selector) {
	if(result == 1) {
		document.getElementById("rj").disabled=false;
	}
}
				</script>
			</div>
			<div class="row">
				<input id="rj" type="submit" disabled="disabled" value="注册" />
			</div>
		</form>

	</div>
</body>
</html>
