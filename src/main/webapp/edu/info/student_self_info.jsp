<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/edu/js/info/student_self_info.js"></script>
<title>个人中心</title>
</head>
<body style="background: rgb(248, 248, 248)">
	<!-- 页头 -->
	<%@ include file="/edu/courses/header.jsp" %>

	<div class="layui-container" style="margin-top: 20px; min-height: 500px;">
		<blockquote class="layui-elem-quote layui-quote-nm" style="background: white;">
			个人中心
		</blockquote>
		<form action="" class="layui-form" style="background:white; padding-bottom: 20px" >
			<table class="layui-table">
			  <tr>
			    <th>姓名</th>
			    <td>${student.name}</td>
			  </tr>
			  <tr>
			    <th>手机</th>
			    <td><input type="tel" name="phone" lay-verify="phone|checkphone" autocomplete="off" 
		           value="${student.phone}" placeholder="手机" class="layui-input"></td>
			  </tr>
			  <tr>
			    <th>手机</th>
			    <td><input type="text" name="wechat" placeholder="微信" 
	      				autocomplete="off" class="layui-input" value="${student.wechat}"/></td>
			  </tr>
			  <tr>
			    <th>邮箱</th>
			    <td><input type="text" name="email" lay-verify="email" autocomplete="off" 
		        	value="${student.email}" placeholder="邮箱" class="layui-input"></td>
			  </tr>
			  <tr>
			    <th>QQ</th>
			    <td><input type="text" name="qq" autocomplete="off" 
		        	value="${student.qq}" placeholder="QQ"  class="layui-input"></td>
			  </tr>
			  <tr>
			    <th>性别</th>
			    <td>
	      			<input type="radio" name="sex" value="男" title="男" <c:if test="${student.sex=='男'}">checked="checked"</c:if>>
	     			<input type="radio" name="sex" value="女" title="女" <c:if test="${student.sex=='女'}">checked="checked"</c:if>>
	  			</td>
			  </tr>
			  <tr>
			    <th>学校</th>
			    <td>${student.university}</td>
			  </tr>
			  <tr>
			    <th>seehope班级</th>
			    <td>${student.seehope_class.name}</td>
			  </tr>
			  <tr>
			    <th>班主任</th>
			    <td>${student.seehope_class.charge_teacher.name}</td>
			  </tr>
			  <tr>
			    <th>教师</th>
			    <td>${student.seehope_class.teach_teacher.name}</td>
			  </tr>
			</table>
			
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit="" lay-filter="userInfo">修改</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
			</div>
		</form>
    </div>
    
    <!-- 页尾 -->
	<%@ include file="/edu/courses/footer.jsp" %>
	
	<script type="text/javascript">
		var _phone = '${student.phone}';
	</script>
</body>
</html>