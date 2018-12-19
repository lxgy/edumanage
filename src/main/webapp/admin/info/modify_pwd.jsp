<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/info/modify_pwd.js"></script>
<title>修改密码</title>
</head>
<body class="childrenBody">

	<form class="layui-form changePwd" style="width:90%;">
		
		<div class="layui-form-item">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-inline">
		    	<input type="password" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-inline">
		    	<input type="password" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" 
		    	   name="password" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-inline">
		    	<input type="password" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input">
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="updatePwd">修改密码</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
</body>
</html>