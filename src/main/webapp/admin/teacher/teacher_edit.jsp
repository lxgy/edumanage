<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/teacher/teacher_edit.js"></script>
<title>修改教师信息</title>
<style type="text/css">
  body{
    padding-top: 10px;
  }
</style>
</head>
<body>
	<form class="layui-form" action="" style="width:90%;">
	  <input type="hidden" name="id" value="${teacher.id}">
	  <div class="layui-form-item">
	    <label class="layui-form-label">姓名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" autocomplete="off" 
	       	 placeholder="姓名" class="layui-input" value="${teacher.name}"/>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">手机</label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="phone|checkphone" autocomplete="off" 
	        	class="layui-input"  value="${teacher.phone}"/>
	      </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">微信</label>
	      <div class="layui-input-block">
	        <input type="tel" name="wechat"  autocomplete="off" 
	        	class="layui-input"  value="${teacher.wechat}"/>
	      </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" lay-verify="email" autocomplete="off" 
	            class="layui-input"  value="${teacher.email}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">QQ</label>
	      <div class="layui-input-block">
	        <input type="text" name="qq" autocomplete="off" 
	            class="layui-input" value="${teacher.qq}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">类型</label>
	    <div class="layui-input-block">
	      <select name="type" lay-verify="required">
	        <option value=""></option>
	        <option value="1" <c:if test="${teacher.type==1}">selected="selected"</c:if>>讲师</option>
	        <option value="2" <c:if test="${teacher.type==2}">selected="selected"</c:if>>教务</option>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="男" title="男" <c:if test="${teacher.sex=='男'}">checked="checked"</c:if>>
	      <input type="radio" name="sex" value="女" title="女" <c:if test="${teacher.sex=='女'}">checked="checked"</c:if>>
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="updateTeacher">确认修改</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
	
	<script type="text/javascript">
		var _phone = '${teacher.phone}';
	</script>
</body>
</html>