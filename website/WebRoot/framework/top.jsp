<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%!boolean login = false; %>
<%!String nickName = "hundun"; %>
<%
Map<String, Object> user = (Map<String, Object>)request.getSession().getAttribute("USER");
if(user != null && user.size() > 0) {
login = true;
nickName = String.valueOf(user.get("nickName"));
user = null;
}
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--=== Top ===-->
<div class="top">
	<div class="container">
		<ul class="loginbar pull-right">
			<li><i><img src="assets/img/home.png" width="10px;" height="10px;"></img>
			</i>&nbsp;<a href="#">设为首页<i class="icon-sort-up"></i>
			</a></li>
			<li class="devider">&nbsp;</li>
			<li><i><img src="assets/img/like.gif"></img>
			</i>&nbsp;<a href="#">收藏本站<i class="icon-sort-up"></i>
			</a></li>

			<%if(login) {%>
			欢迎你, <%=nickName %>
			<%} else {%>
			<li class="devider">&nbsp;</li>
			<li><a id="registBtn">注册</a>
			</li>
			<li class="devider">&nbsp;</li>
			<li><a class="login-btn" id="loginBtn">登录</a>
			</li>
			<%}%>

		</ul>
	</div>
</div>
<!--/top-->
<!--=== End Top ===-->

<!--=== Header ===-->
<div class="header">
	<div class="container">
		<!-- Logo -->
		<div class="logo">
			<a href="index.html"><img id="logo-header"
				src="assets/img/logo1-default.png" alt="Logo" />
			</a>
		</div>
		<!-- /logo -->

		<!-- Menu -->
		<div class="navbar">
			<div class="navbar-inner">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a>
				<!-- /nav-collapse -->
				<div class="nav-collapse collapse">
					<ul class="nav top-2">
						<li class="active"><a href="#" class="dropdown-toggle" data-toggle="dropdown">首页 <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="index.html">About Us</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="" class="dropdown-toggle" data-toggle="dropdown">头条
								<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="page_about.html">About Us</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">媒体 <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="demo.html" target="_blank">美图</a>
								</li>
								<li><a href="demo.html" target="_blank">音乐</a>
								</li>
								<li><a href="demo.html" target="_blank">视频</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">软件研发 <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="demo.html" target="_blank">移动应用</a>
								</li>
								<li><a href="demo.html" target="_blank">前端开发</a>
								</li>
								<li><a href="demo.html" target="_blank">后台服务</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">博客 <b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="blog.html">Blog</a>
								</li>
								<li><a href="blog_item.html">Blog Item</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">论坛<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="blog.html">Blog</a>
								</li>
								<li><a href="blog_item.html">Blog Item</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">联系我们<b class="caret"></b> 
							</a>
							<ul class="dropdown-menu">
								<li><a href="page_contact.html" target="_blank">关于我们</a>
								</li>
								<li><a target="_blank">编辑投稿 - 敬请期待</a>
								</li>
								<li><a target="_blank">广告投放 - 敬请期待</a>
								</li>
							</ul> <b class="caret-out"></b></li>
						<li><a class="search"><i class="icon-search search-btn"></i>
						</a>
						</li>
					</ul>
					<div class="search-open">
						<div class="input-append">
							<form>
								<input type="text" class="span3" placeholder="我要精彩!!!" />
								<button type="submit" class="btn-u">搜</button>
							</form>
						</div>
					</div>
				</div>
				<!-- /nav-collapse -->
			</div>
			<!-- /navbar-inner -->
		</div>
		<!-- /navbar -->
	</div>
	<!-- /container -->
</div>
<!--/header -->
<!--=== End Header ===-->
