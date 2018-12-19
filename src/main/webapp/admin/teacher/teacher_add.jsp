<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/teacher/teacher_add.js"></script>
<title>添加教师</title>
</head>
<body class="childrenBody">
	<form class="layui-form" action="" style="width:95%; margin-top: 20px;">
	  <div class="layui-form-item">
	    <label class="layui-form-label">姓名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" autocomplete="off" 
	      	placeholder="姓名" class="layui-input"/>
	    </div>
	  </div>
	      
	  <div class="layui-form-item">
	    <label class="layui-form-label">类型</label>
	    <div class="layui-input-block">
	      <select name="type" lay-verify="required">
	        <option value="">类型</option>
	        <option value="1">讲师</option>
	        <option value="2">教务</option>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">手机</label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="phone|checkphone" 
	        	placeholder="手机" autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">微信</label>
	      <div class="layui-input-block">
	        <input type="tel" name="wechat" autocomplete="off" 
	        	placeholder="微信" class="layui-input">
	      </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" lay-verify="email" 
	        	placeholder="邮箱" autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  <div class="layui-form-item">
	      <label class="layui-form-label">QQ</label>
	      <div class="layui-input-block">
	        <input type="text" name="qq" autocomplete="off" 
	        	placeholder="QQ" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="男" title="男" checked="checked">
	      <input type="radio" name="sex" value="女" title="女">
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="addTeacher">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>

</body>
</html>