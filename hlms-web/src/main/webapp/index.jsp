<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/context.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>HLMS</title>

	<link rel="shortcut icon" href="<%=basePath%>res/image/favicon.ico">
	<link href="res/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="res/css/magister.css">


</head>

<!-- use "theme-invert" class on bright backgrounds, also try "text-shadows" class -->
<body class="theme-invert">

<nav class="mainmenu">
	<div style="margin-left:2.5rem;">
		<div class="dropdown">
			<button type="button" class="navbar-toggle" data-toggle="dropdown"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
				<li><a href="#head" class="active">欢迎</a></li>
				<li><a href="#es">ES</a></li>
				<li><a href="#cms">CMS</a></li>
				<li><a href="#gis">GIS</a></li>
				
			</ul>
		</div>
		<div style="
    float: right;
    margin-right: 2.5rem;
    font-size: 12px;
    font-weight: bold;
		"><a href="login">登录</a>&nbsp;&nbsp;<a href="register" >注册</a></div>
	</div>
	
</nav>


<!-- Main (Home) section -->
<section class="section" id="head">
	<div class="container" >

		<div class="row">
			<div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">	

				<!-- Site Title, your name, HELLO msg, etc. -->
				<h1 class="title">欢迎</h1>
				<h2 class="subtitle">使用hlms管理系统</h2>

				<!-- Short introductory (optional) -->
				<h3 class="tagline">
					Potentially, the best place to tell people why they are here.<br>
					So, this is a demo page built to showcase the beauty of the template.
				</h3>
				
				<!-- Nice place to describe your site in a sentence or two -->
				<p><a href="index" class="btn btn-default btn-lg">Download template now</a></p>
	
			</div> <!-- /col -->
		</div> <!-- /row -->
	
	</div>
</section>

<!-- Second (About) section -->
<section class="section" id="es">
	<div class="container">
	
		<h2 class="text-center title">ElasticSearch 引擎</h2>
		<div class="row">
			<div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">	

				
				<!-- Nice place to describe your site in a sentence or two -->
				<p><a href="es" class="btn btn-default btn-lg">Enter</a></p>
	
			</div> <!-- /col -->

		</div>
	</div>
</section>

<!-- Third (Works) section -->
<section class="section" id="cms">
	<div class="container">
	
		<h2 class="text-center title">CMS</h2>
		<p class="lead text-center">
			Huge thank you to all people who publish<br>
			their photos at <a href="http://unsplash.com">Unsplash</a>, thank you guys!
		</p>
		<div class="row">
			<div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">	

				
				<!-- Nice place to describe your site in a sentence or two -->
				<p><a href="pages/cms/index" class="btn btn-default btn-lg">Enter</a></p>
	
			</div> <!-- /col -->

		</div>

	</div>
</section>

<!-- Fourth (Contact) section -->
<section class="section" id="gis">
	<div class="container">
	
		<h2 class="text-center title">GIS</h2>

		<div class="row">
			<div class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">	

				
				<!-- Nice place to describe your site in a sentence or two -->
				<p><a href="pages/gis/index" class="btn btn-default btn-lg">Enter</a></p>
	
			</div> <!-- /col -->

		</div>

	</div>
</section>


<script type="text/javascript">

var require = {
        urlArgs: 'v=1.123'
   };
var basePath="<%=basePath%>";

var error="${error}";

</script>
<script data-main="res/js/index" src="<%=basePath %>res/lib/require.js"></script>
</body>
</html>