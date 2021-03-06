<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>IPS - Sign in</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- libraries -->
<link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/signin.css" type="text/css"
	media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<!-- background switcher -->
	<!--  
	<div class="bg-switch visible-desktop">
		<div class="bgs">
			<a href="#" data-img="landscape.jpg" class="bg active"> <img
				src="img/bgs/landscape.jpg" />
			</a> <a href="#" data-img="blueish.jpg" class="bg"> <img
				src="img/bgs/blueish.jpg" />
			</a> <a href="#" data-img="7.jpg" class="bg"> <img
				src="img/bgs/7.jpg" />
			</a> <a href="#" data-img="8.jpg" class="bg"> <img
				src="img/bgs/8.jpg" />
			</a> <a href="#" data-img="9.jpg" class="bg"> <img
				src="img/bgs/9.jpg" />
			</a> <a href="#" data-img="10.jpg" class="bg"> <img
				src="img/bgs/10.jpg" />
			</a> <a href="#" data-img="11.jpg" class="bg"> <img
				src="img/bgs/11.jpg" />
			</a>
		</div>
	</div>
-->

	<div class="row-fluid login-wrapper">
		<a href="index.html"> <img class="logo" src="img/logo-white.png" />
		</a>

		<div class="span4 box">
			<div class="content-wrap">
				<h6 style="font-size: 30px; font-weight: bolder;">登 录</h6>

				<form class="theme-signin" name="loginform"
					action="/IPSServer/LoginServlet" method="post" style="overflow: hidden;">

					<input id="email" name="email" class="span12" type="text" placeholder="email" /> 
					<input id="password" name="password" class="span12" type="password" placeholder="password" /> 
					<a href="#" class="forgot">忘记密码?</a>
					<div class="remember">
						<input id="remember-me" type="checkbox" /> <label
							for="remember-me">记住密码</label>
					</div>
					<input class="btn-glow primary login" type="submit" name="submit" value="登陆" href="index.html"/>

				</form>
			</div>
		</div>

		<div class="span4 no-account">
			<p>没有账号？</p>
			<a href="signup.html">注册</a>
		</div>
	</div>

	<!-- scripts -->
	<script src="js/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/theme.js"></script>

	<script type="text/javascript">
		$("html").css("background-image", "url('img/bgs/7.jpg')");
	</script>

	<!-- pre load bg imgs -->
	<script type="text/javascript">
		$(function() {
			// bg switcher
			var $btns = $(".bg-switch .bg");
			$btns.click(function(e) {
				e.preventDefault();
				$btns.removeClass("active");
				$(this).addClass("active");
				var bg = $(this).data("img");

				$("html").css("background-image", "url('img/bgs/" + bg + "')");
			});

		});
	</script>

</body>
</html>