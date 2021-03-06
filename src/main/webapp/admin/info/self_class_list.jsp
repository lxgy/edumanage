<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/info/self_class_list.js"></script>
<title>我的班级列表</title>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-form">
		<input type="checkbox" id="status" value="1" title="显示毕业班级">
	</blockquote>
	
	<table class="layui-hide" id="table_class" lay-filter="classes"></table> 
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="add_work">布置作业</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="class_work">作业</a>
  		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail_stu">班级学生</a>
	</script>
	
</body>
</html>