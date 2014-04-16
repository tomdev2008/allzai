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

<title>极验行为式验证 php 类网站安装测试页面</title>
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
		<h1>极验行为式验证 php 类网站安装测试页面</h1>
		<form method="post" action="http://112.124.51.168:8080/allzai_server/public/userLogin">
			<div class="row">
				<label for="name">邮箱</label> <input type="text" id="account"
					name="account" value="geetest@126.com" />
			</div>
			<div class="row">
				<label for="passwd">密码</label> <input type="password" id="password"
					name="password" value="gggggggg" />
			</div>
			<div class="row">
				<script type="text/javascript"
					src="http://api.geetest.com/get.php?gt=de7689d1283867a3b72f3ee0da8641db"></script>
				<script>
function check() {

String privateKey="3e81ef75d797e93c3496736b457ea3f6";
GeetestLib geetest=new GeetestLib(privateKey);
boolean result = geetest.validate(request.getParameter("geetest_challenge"),request.getParameter("geetest_validate"),request.getParameter("geetest_seccode"));
if (result){
alert('对了');
return true;
}else{
alert(错了');
  //验证失败后的操作
return false;
}

}

				</script>
			</div>
			<div class="row">
				<input type="submit" value="登录" onclick="return check()" />
			</div>
		</form>
	</div>
</body>
</html>
