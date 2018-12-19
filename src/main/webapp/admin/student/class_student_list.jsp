<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/student/class_student_list.js"></script>
<title>班级学生列表</title>
</head>
<body class="childrenBody">
	<a class="layui-btn layui-btn-xs layui-btn-primary" 
		href="${path}/admin/student/download_class_student_excel/${cid}">生成学生信息表</a>
	<a class="layui-btn layui-btn-xs layui-btn-primary" 
		href="${path}/admin/student/download_class_student_book/${cid}">生成学生学生点名表</a>
	<table class="layui-hide" id="table_student" lay-filter="student"></table>
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="evaluation">阶段</a>
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="paper">答题</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="class_log">日志</a>
	</script>
	
	<script type="text/javascript">
	    var cid = '${cid}';
	</script>
</body>
</html>