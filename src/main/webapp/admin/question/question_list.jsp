<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>阶段问题列表</title>
<%@ include file="/common/common.jsp"%>
<script src="${path }/admin/js/question/question_list.js" type="text/javascript" charset="utf-8"></script>
</head>

<body class="childrenBody">
	<blockquote class="layui-elem-quote  layui-quote-nm layui-form">
		<!--表头操作-->
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" id="keyText" placeholder="请输入关键字" class="layui-input search_input">
			</div>
			<div class="layui-input-inline">
				<select id="stage_type">
					<option value="">阶段</option>
					<option value="1">第一阶段</option>
					<option value="2">第二阶段</option>
					<option value="3">第三阶段</option>
				</select>
			</div>
			
			<div class="layui-input-inline">
				<select id="type">
					<option value="">类型</option>
					<option value="1">单选题</option>
					<option value="2">多选题</option>
					<option value="3">问答题</option>
				</select>
			</div>
			
			<div class="layui-input-inline">
				<select id="level">
					<option value="">难度</option>
					<option value="1">简单</option>
					<option value="2">一般</option>
					<option value="3">困难</option>
				</select>
			</div>
			
		</div>

		<div class="layui-inline">
			<a class="layui-btn search_question_btn">查询</a>
			<a class="layui-btn layui-btn-normal add_question_btn">添加问题</a>
		</div>
	</blockquote>

	<!--数据表格-->
	<table class="layui-hide" id="question_table" lay-filter="question_filter" lay-size="sm"></table>

	<!--操作-->
	<script type="text/html" id="question_bar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="detail">选项管理</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	
</body>

</html>