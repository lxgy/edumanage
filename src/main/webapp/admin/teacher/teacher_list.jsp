<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/teacher/teacher_list.js"></script>
<title>教师管理</title>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm layui-form">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" id="keyText" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
			<div class="layui-input-inline">
			    <select id="type">
		          <option value="">类型</option>
		          <option value="1">讲师</option>
		          <option value="2">教务</option>
		        </select>
	        </div>
		</div>
		<div class="layui-inline">
			<a class="layui-btn search_btn">查询</a>
			<a class="layui-btn layui-btn-normal add_btn">添加教师</a>
		</div>
		<input type="checkbox" id="status" value="1" title="显示停用">
	</blockquote>
	
	<table class="layui-hide" id="table_teacher" lay-filter="teacher"></table> 
	
	<script type="text/html" id="toolbar">
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        {{# if(d.status == 1){ }}
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="disable">停用</a>
        {{# }else{ }}
		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="enable">启用</a>
        {{# } }}
	</script>
	
</body>
</html>