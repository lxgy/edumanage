<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" type="text/css" href="${path}/css/login/normalize.css"/>
<link rel="stylesheet" type="text/css" href="${path}/css/login/demo.css"/>
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="${path}/css/login/component.css"/>
<!--[if IE]>
<script src="${path}/js/login/html5.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/html5.js"></script>
<![endif]-->
<title>教务系统登录</title>
<style type="text/css">
	.demo-1 .large-header {
		background-image: url("${path}/images/demo-1-bg.jpg");
	}
	.u_user{
		width: 25px;
		height: 25px;
		background: url("${path}/images/login_ico.png");
		background-position:  -125px 0;
		position: absolute;
		margin: 10px 13px;
	}
	.us_uer{
		width: 25px;
		height: 25px;
		background-image: url("${path}/images/login_ico.png");
		background-position: -125px -34px;
		position: absolute;
		margin: 10px 13px;
	}
</style>

</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<h3>教务系统 欢迎你</h3>
					<form action="#" name="f" method="post">
						<div class="input_outer">
							<span class="u_user"></span>
							<input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span>
							<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
						</div>
						<div class="mb2">
							<a class="act-but submit" href="javascript:;" style="color: #FFFFFF" onclick="login()">登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div><!-- /container -->
	<script src="${path}/js/login/TweenLite.min.js"></script>
	<script src="${path}/js/login/EasePack.min.js"></script>
	<script src="${path}/js/login/rAF.js"></script>
	<script src="${path}/js/login/demo-1.js"></script>
	<script type="text/javascript" src="${path}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/layui/layui.js"></script>
	
	<script type="text/javascript">
		var layer;
		layui.use('layer', function(){
		  layer = layui.layer;
		});              
	
		$("input[type=password]").keyup(function(e){
			if( e.which == 13 ) login();
		});
		function login() {
			if( $("input[name=username]").val() == "" ) {
				layer.msg("请输入帐号");
				return;
			}
			if( $("input[name=password]").val() == "" ) {
				layer.msg("请输入密码");
				return;
			}
			var logindata = $("form").serialize();
			$.ajax({
				url: "${path}/edu/ajax_login",
				data: logindata,
				type: 'post',
				dataType: 'json',
				success:function(data) {
					if( data.code == "200" ) {
						window.location.href = "index";
					}else {
						layer.msg(data.msg);
					}
				}
			});
		}
	</script>
</body>
</html>