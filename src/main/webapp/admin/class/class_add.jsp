<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/class/class_add.js"></script>
<title>添加班级</title>
</head>
<body class="childrenBody">
	 
	<form class="layui-form" action="" style="width:90%; margin-top: 20px;">
	  <div class="layui-form-item">
	    <label class="layui-form-label">班级名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="班级名" class="layui-input" />
	    </div>
	  </div> 
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">状态</label>
	    <div class="layui-input-block">
	      <select name="status" lay-verify="required">
	        <option value="">状态</option>
	        <option value="1">预开班</option>
	        <option value="2">已开班</option>
	        <option value="3">已毕业</option>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">阶段</label>
	    <div class="layui-input-block">
	      <input type="text" name="stage" autocomplete="off" placeholder="阶段" class="layui-input" />
	    </div>
	  </div> 
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">开班时间</label>
	      <div class="layui-input-block">
	        <input type="text" name="open_time" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	  </div> 
	  
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">班主任</label>
	    <div class="layui-input-block">
	      <select name="charge_teacher.id">
	        <option value="">设置班主任</option>
	        <c:forEach items="${charge_teachers}" var="ct">
	        	<option value="${ct.id}">${ct.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">教师</label>
	    <div class="layui-input-block">
	      <select name="teach_teacher.id">
	        <option value="">设置教师</option>
	        <c:forEach items="${teach_teachers}" var="tt">
	        	<option value="${tt.id}">${tt.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="addClass">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>

</body>
</html>