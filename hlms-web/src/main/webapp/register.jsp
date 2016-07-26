<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/context.jsp"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>HLMS</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="res/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="res/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="res/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="res/css/login.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="res/image/favicon.ico" />
	
	<script>
		var basePath="<%=basePath%>";
	</script>
	<script data-main="res/js/register" src="res/lib/require.js"></script>
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGO -->

	<div class="logo">

		<img src="res/image/logo-big.png" alt="" /> 

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		     

		

		<!-- BEGIN REGISTRATION FORM -->

		<form class="form-vertical" action="register" id="register" method="post">

			<h3 class="form-title">注 册</h3>

			<p>请输入相关信息:</p>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">登录名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="登录名" name="username"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="密码" name="password"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">确认密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-ok"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="确认密码" name="rpassword"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">Email</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">手机号</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-phone"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="手机号" name="phone"/>

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button id="btn_login" type="button" class="btn">

				<i class="m-icon-swapleft"></i>  登录

				</button>
				
				<button type="submit" id="register-submit-btn" class="btn green pull-right">

				注册 <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

		</form>

		<!-- END REGISTRATION FORM -->

	</div>

	<!-- END LOGIN -->


	<jsp:include page="common/footer.jsp"></jsp:include>


<!-- END BODY -->

</html>