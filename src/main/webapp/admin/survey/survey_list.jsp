<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>问卷调查</title>
		<%@ include file="/common/common.jsp"%>
		<script src="${path }/admin/js/survey/survey_list.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<!--页面主题内容开始-->

	<body class="childrenBody">
		<!--头部功能展示展示开始-->
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form">
			<!--关键词搜索开始-->
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="keyText" id="keyText" value="" placeholder="请输入关键字" class="layui-input search_input" />
				</div>
			</div>
			<!--关键词搜索结束-->
			<!--问卷调查题目类型开始-->
			<div class="layui-input-inline">
				<select id="type" name="type">
					<option value="">问卷调查类型</option>
					<option value="1">单选题</option>
					<option value="3">问答题</option>
				</select>
			</div>
			<!--问卷调查题目类型结束-->
			<!--查询、添加按钮开始-->
			<div class="layui-inline">
				<a class="layui-btn search_survey_btn">查询</a>
				<!-- <a class="layui-btn layui-btn-danger reset-btn">重置</a> -->
				<a class="layui-btn layui-btn-normal add_survey_btn">添加问卷问题</a>
			</div>
			<!--查询添加按钮结束-->
		</blockquote>
		<!--头部功能展示结束-->

		<!--表格信息列表开始-->
		<table class="layui-hide" id="survey_table" lay-filter="survey_filter" lay-size="sm"></table>
		<!--表格信息列表结束-->

		<!--绑定事件操作开始-->
		<script type="text/html" id="survey_bar">
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">选项管理</a>
			<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
		<!--绑定事件操作结束-->
	</body>
	<!--页面主题内容结束-->

</html>