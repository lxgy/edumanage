<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/common/common.jsp"%>
		<script type="text/javascript" src="${path}/admin/js/student/graduated_students_list.js"></script>
		<title>已毕业学生信息管理</title>
	</head>

	<body class="childrenBody">
		<!--导航头部开始-->
		<blockquote class="layui-elem-quote layui-quote-nm layui-form">
			<!--学生姓名关键字开始-->
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" id="keyText1" placeholder="请输入关键字" class="layui-input search_input">
				</div>
			</div>
			<!--学生姓名关键字结束-->
			<!--查询开始-->
			<div class="layui-inline">
				<a class="layui-btn search1_btn">查询</a>
			</div>
			<!--查询结束-->
		</blockquote>
		<!--导航头部结束-->

		<!--表格信息展示开始-->
		<table class="layui-hide" id="table_graduated_student" lay-filter="graduated_student"></table>
		<!--表格信息展示结束-->

		<!--操作按钮开始-->
		<script type="text/html" id="toolbar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="class_log">日志</a>
		</script>
		<!--操作按钮结束-->
	</body>

</html>