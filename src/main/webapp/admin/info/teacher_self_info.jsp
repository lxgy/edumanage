<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/info/teacher_self_info.js"></script>
<title>个人中心</title>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote">
		个人中心
	</blockquote>
	<form action="" class="layui-form">
		<input type="hidden" name="id" value="${teacher.id}" />
		<table class="layui-table">
		  <tr>
		    <th>姓名</th>
		    <td><input type="text" name="name" lay-verify="required" autocomplete="off" 
	       	 placeholder="姓名" class="layui-input" value="${teacher.name}"/></td>
		  </tr>
		  <tr>
		    <th>类型</th>
		    <td>
		        <select name="type" lay-verify="required">
			        <option value="1" <c:if test="${teacher.type==1}">selected="selected"</c:if>>讲师</option>
			        <option value="2" <c:if test="${teacher.type==2}">selected="selected"</c:if>>教务</option>
			      </select>
		      </td>
		  </tr>
		  <tr>
		    <th>手机</th>
		    <td><input type="tel" name="phone" lay-verify="phone|checkphone" autocomplete="off" 
	           value="${teacher.phone}" placeholder="手机" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>微信</th>
		    <td><input type="text" name="wechat"  autocomplete="off" 
	           value="${teacher.wechat}" placeholder="微信" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>邮箱</th>
		    <td><input type="text" name="email" lay-verify="email" autocomplete="off" 
	        	value="${teacher.email}" placeholder="邮箱" class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>QQ</th>
		    <td><input type="text" name="qq" autocomplete="off" 
	        	value="${teacher.qq}" placeholder="QQ"  class="layui-input"></td>
		  </tr>
		  <tr>
		    <th>性别</th>
		    <td><input type="radio" name="sex" value="男" title="男" <c:if test="${teacher.sex=='男'}">checked="checked"</c:if>>
	      		<input type="radio" name="sex" value="女" title="女" <c:if test="${teacher.sex=='女'}">checked="checked"</c:if>></td>
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
		var _phone = '${teacher.phone}';
	</script>
</body>
</html>