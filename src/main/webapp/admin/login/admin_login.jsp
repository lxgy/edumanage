<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>后台管理系统 </title>
<link rel="stylesheet" href="${path}/static/login/admin/login.css" />
</head>
<body>
<form>
	<dl class="admin_login">
	  <dt> <strong>教务后台管理系统</strong> <em>Educational Administration System</em> </dt>
	  <dd class="user_icon">
	    <input type="text" name="loginName" placeholder="手机/邮箱" class="login_txtbx"/>
	  </dd>
	  <dd class="pwd_icon">
	    <input type="password" name="loginPwd" placeholder="密码" class="login_txtbx"/>
	  </dd>
	  <%-- <dd class="val_icon">
	    <div class="checkcode">
	      <input type="text" id="J_codetext" name="registerCode" placeholder="验证码" maxlength="4" class="login_txtbx">
	      <img class="J_codeimg" src="${path}/captcha-image.jpg" id="kaptchaImage" /> </div>
	    <input type="button" value="点击,换一张" class="ver_btn" onclick="$(this).prev().find('img').click()">
	  </dd> --%>
	  <dd>
	    <input type="button" value="立即登陆" class="submit_btn"/>
	  </dd>
	  <dd>
	    <p>© 2017 <a href="http://www.seehope.net/" target="_blank">砺锋科技</a> 版权所有</p>
	  </dd>
	</dl>
</form>
<script src="${path}/static/common/jquery/jquery-3.2.0.min.js"></script> 
<script src="${path}/static/common/layui/layui.js"></script>
<script src="${path}/static/common/particleground/js/jquery.particleground.min.js"></script> 
<script src="${path}/static/login/admin/login.js"></script>
<script> var _path = '${path}';</script>
</body>
</html>