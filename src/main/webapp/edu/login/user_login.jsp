<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<link rel="shortcut icon" href="${path}/default/ico/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${path}/static/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${path}/static/login/user/css/gloab.css" />
<link rel="stylesheet" href="${path}/static/login/user/css/index.css" />
<script> var _path = '${path}';</script>
</head>
<body class="bgf4">
<script zIndex="-1" src="${path}//static/login/user/js/canvas-nest.min.js"></script>
<div class="login-box">
  <div class="container-nav"> <a class="navbar-brand">但行好事,莫问前程.</a>
    <ul class="navbar-nav-right">
      <li><a href="http://www.seehope.net" target="_blank">砺锋科技</a></li>
      <li><a href="http://www.codeseeding.com" target="_blank">码云社</a></li>
      <li><a href="http://www.u-cto.com" target="_blank">众创空间</a></li>
    </ul>
  </div>
  <div class="main">
    <div class="reg-box-pan display-inline">
      <div class="reg-box login" id="verifyCheck" style="margin-top:20px;">
        <div class="part1">
          <form>
            <div class="item col-xs-12">
              <div class="f-fl item-ifo">
                <input type="text" name="loginName" maxlength="20" placeholder="手机/邮箱" class="txt03 f-r3 required loginPage" tabindex="1" data-valid="isNonEmpty" data-error="请输入帐号" id="adminNo" style="height: 40px;"/>
                <span class="ie8 icon-close close hide"></span>
                <label class="icon-sucessfill blank hide"></label>
                <label class="focus"><span></span></label>
                <label class="focus valid"></label>
              </div>
            </div>
            <div class="item col-xs-12">
              <div class="f-fl item-ifo">
                <input type="password" name="loginPassword" id="password" maxlength="20" placeholder="密码" class="txt03 f-r3 required loginPage" tabindex="3" style="ime-mode:disabled; height: 40px"/>
                <span class="ie8 icon-close close hide" style="right:55px"></span> <span class="showpwd" data-eye="password"></span>
                <label class="icon-sucessfill blank hide"></label>
                <label class="focus"></label>
                <label class="focus valid"></label>
                <span class="clearfix"></span>
                <label class="strength"> <span class="f-fl f-size12">安全程度：</span> <b><i>弱</i><i>中</i><i>强</i></b> </label>
              </div>
            </div>
          </form>
          <div class="item col-xs-12 message" style="height:auto">
            <label class="valid" ></label>
          </div>
          <div class="item col-xs-12">
            <div class="f-fl item-ifo"> <a href="javascript:;" class="btn btn-blue f-r3" id="btn_login">立即登录</a> </div>
          </div>
          <%-- <div class="item col-xs-12" style="height:auto">
            <label class="valid  line" ><a href="${path }/pass/forgetPassword">忘记密码？</a></label>
          </div> --%>
        </div>
      </div>
    </div>
  </div>
</div>
<footer class="footer clear">
  <div class="footer_copyright"> © 2016<a href="http://www.seehope.net/" target="_blank"> 砺锋科技 </a></div>
</footer>
<script src="${path}/static/common/jquery/jquery-2.1.4.min.js"></script> 
<script src="${path}/static/common/layui/layui.js"></script>
<script src="${path}/static/common/bootstrap/js/bootstrap.min.js"></script> 
<script src="${path}/static/common/security/security.js"></script> 
<script src="${path}/static/login/user/js/register.js"></script> 
<script src="${path}/static/login/user/js/login.js"></script> 
</body>
</html>
