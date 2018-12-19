<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>增加问题</title>
<%@ include file="/common/common.jsp"%>
<script src="${path }/admin/js/question/question_add.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div id="" style="margin-top: 15px;margin-right: 50px;">
		<form action="" method="post" class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">所属阶段</label>
				<div class="layui-input-block">
					<select name="stage_type" lay-verify="required" lay-search>
						<option value="1">第一阶段</option>
						<option value="2">第二阶段</option>
						<option value="3">第三阶段</option>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">所属类型</label>
				<div class="layui-input-block">
					<select name="type" lay-verify="required" >
						<option value="1">单选题</option>
						<option value="2">多选题</option>
						<option value="3">问答题</option>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">难易程度</label>
				<div class="layui-input-block">
					<select name="level" lay-verify="required" >
						<option value="1">简单</option>
						<option value="2">一般</option>
						<option value="3">困难</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">问题描述</label>
				<div class="layui-input-block">
					<textarea name="question" lay-verify="required" autocomplete="off" placeholder="问题描述" class="layui-textarea"></textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">解题思路</label>
				<div class="layui-input-block">
					<textarea name="idea" autocomplete="off" class="layui-textarea" placeholder="解题思路"></textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">增加</button>
					<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>