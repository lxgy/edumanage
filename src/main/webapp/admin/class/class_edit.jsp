<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/class/class_edit.js"></script>
<title>编辑班级</title>
<style type="text/css">
  body{
    padding-top: 10px;
  }
</style>
</head>
<body class="childrenBody">
	 
	<form class="layui-form" action="" style="width:90%;">
	  <input type="hidden" name="id" value="${clazz.id}">
	  <div class="layui-form-item">
	    <label class="layui-form-label">班级名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" autocomplete="off" 
	      	value="${clazz.name}" placeholder="班级名" class="layui-input" />
	    </div>
	  </div> 
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">状态</label>
	    <div class="layui-input-block">
	      <select name="status" lay-verify="required">
	        <option value="">状态</option>
	        <option value="1" <c:if test="${clazz.status==1}">selected="selected"</c:if>>预开班</option>
	        <option value="2" <c:if test="${clazz.status==2}">selected="selected"</c:if>>已开班</option>
	        <option value="3" <c:if test="${clazz.status==3}">selected="selected"</c:if>>已毕业</option>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">阶段</label>
	    <div class="layui-input-block">
	      <input type="text" name="stage" autocomplete="off" placeholder="阶段" 
	      		value="${clazz.stage}" class="layui-input" />
	    </div>
	  </div> 
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">开班时间</label>
	      <div class="layui-input-block">
	        <input type="text" name="open_time" id="date" lay-verify="date" 
	          value="${clazz.open_time}" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">班主任</label>
	    <div class="layui-input-block">
	      <select name="charge_teacher.id">
	        <option value="">设置班主任</option>
	        <c:forEach items="${charge_teachers}" var="ct">
	        	<option value="${ct.id}" <c:if test="${ct.id==clazz.charge_teacher.id}">selected="selected"</c:if>>${ct.name}</option>
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
	        	<option value="${tt.id}" <c:if test="${tt.id==clazz.teach_teacher.id}">selected="selected"</c:if>>${tt.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="updateClass">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>


</body>
</html>