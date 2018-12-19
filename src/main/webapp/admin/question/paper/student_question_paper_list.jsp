<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/question/paper/student_question_paper_list.js"></script>
<title>学生答题记录</title>
</head>
<body class="childrenBody">
	<table class="layui-hide" id="table_paper" lay-filter="question_paper"></table>
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="detail">查看</a>
	</script>
	
	<script type="text/javascript">
	    var sid = '${sid}';
	</script>
</body>
</html>