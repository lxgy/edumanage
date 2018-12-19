<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户测试详情</title>
		<%@ include file="/common/common.jsp"%>
		<script type="text/javascript" src="${path}/admin/js/survey/paper/paper-list.js" ></script>
	</head>

	<!--页面主题内容开始-->

	<body class="childrenBody">
		<!--头部多功能开始-->
		<!--关键字搜索开始-->
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" name="keyText" id="keyText" value="" placeholder="请输入用户名关键字" class="layui-input search_input" />
			</div>
		</div>
		<!--关键字搜索结束-->
		<!--查询、添加按钮开始-->
		<div class="layui-inline">
			<a class="layui-btn search_survey_btn">查询</a>
		</div>
		<!--查询按钮结束-->
		<!--头部多功能结束-->

		<!--表格信息列表开始-->
		<table class="layui-hide" id="studnet_survey_table" lay-filter="student_survey_filter" lay-size="sm"></table>
		<!--表格信息列表结束-->

		<!--绑定事件操作开始-->
		<script type="text/html" id="student_survey_bar">
			<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="user">用户详情</a>
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">查看测试</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
		<!--绑定事件操作结束-->

	</body>
	<!--页面主题内容结束-->
</html>