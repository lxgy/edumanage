<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/feedback/feedback_list.js"></script>
<title>学生反馈管理</title>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm layui-form">
		<input type="checkbox" id="status" value="1" title="显示已读">
	</blockquote>
	
	<table class="layui-hide" id="table_feedback" lay-filter="feedback"></table> 
	
	<script type="text/html" id="toolbar">
        {{# if(d.status == 2){ }}
  		<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">标记为已读</a>
        {{# }else{ }}
		<a class="layui-btn layui-bg-gray layui-btn-xs" lay-event="">已读</a>
        {{# } }}

		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
	</script>
	
</body>
</html>