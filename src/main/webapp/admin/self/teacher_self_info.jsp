<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/static/js/user/user_info.js"></script>
<title>个人中心</title>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote">
		个人中心
	</blockquote>
	<form action="" class="layui-form">
		<input type="hidden" name="username" value="${teacher.user.username}" />
		<table class="layui-table">
		  <tr>
		    <th>姓名</th>
		    <td>${teacher.name}</td>
		  </tr>
		  <tr>
		    <th>手机</th>
		    <td><input type="tel" name="phone" lay-verify="phone" autocomplete="off" 
	           value="${teacher.user.phone}" placeholder="手机" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>微信</th>
		    <td><input type="tel" name="wechat" lay-verify="phone" autocomplete="off" 
	           value="${teacher.user.wechat}" placeholder="微信" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>邮箱</th>
		    <td><input type="text" name="email" lay-verify="email" autocomplete="off" 
	        	value="${teacher.user.email}" placeholder="邮箱" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>QQ</th>
		    <td><input type="text" name="qq" autocomplete="off" 
	        	value="${teacher.user.qq}" placeholder="QQ"  class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>性别</th>
		    <td>${teacher.user.sex}</td>
		  </tr>
		  <tr>
		    <th>职位</th>
		    <td>
		        <c:if test="${teacher.job==1}">讲师</c:if>
		        <c:if test="${teacher.job==2}">销售</c:if>
		        <c:if test="${teacher.job==3}">渠道</c:if>
		        <c:if test="${teacher.job==4}">行政</c:if>
		        <c:if test="${teacher.job==5}">教务</c:if>
		        <c:if test="${teacher.job==6}">CEO</c:if>
		        <c:if test="${teacher.job==7}">市场总监</c:if>
		      </td>
		  </tr>
		  <tr>
		    <th>入职时间</th>
		    <td>${teacher.hire_date}</td>
		  </tr>
		</table>
		
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="userInfo">修改</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
    
    <script type="text/javascript">
		var user_type = '${admin_user == null ? edu_user.type : admin_user.type}';
		var _phone = '${teacher.user.phone}';
	</script>
</body>
</html>