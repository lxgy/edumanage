<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/class/class_list.js"></script>
<title>班级列表</title>
</head>

<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm layui-form" >
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" id="keyText" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
		    <div class="layui-input-inline">
				<select id="status">
			        <option value="">状态</option>
			        <option value="1">预开班</option>
			        <option value="2">已开班</option>
			        <option value="3">已毕业</option>
			     </select>
	     	</div>
		</div>
		<div class="layui-inline">
			<a class="layui-btn search_btn">查询</a>
			<a class="layui-btn layui-btn-normal add_btn" >添加班级</a>
		</div>
	</blockquote>
	
	<table class="layui-hide" id="table_class" lay-filter="classes"></table> 
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="class_work">作业</a>
  		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail_stu">班级学生</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="log">日志</a>
	    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
	
</body>
</html>