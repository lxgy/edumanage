<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>修改问题</title>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${path }/admin/js/question/question_edit.js"></script>
</head>
<body>
	<div id="" style="margin-top: 15px; margin-right: 50px;">
		<form action="" method="post" class="layui-form">
			<input type="hidden" name="id" id="id" value="${question.id}" />

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
				<label class="layui-form-label">题目类型</label>
				<div class="layui-input-block">
					<select name="type" id="type" class="layui-select">
						<option value="1" <c:if test="${question.type == 1}">selected="selected"</c:if>>单选题</option>
						<option value="2" <c:if test="${question.type == 2}">selected="selected"</c:if>>多选题</option>
						<option value="3" <c:if test="${question.type == 3}">selected="selected"</c:if>>问答题</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">难易程度</label>
				<div class="layui-input-block">
					<select name="level" lay-verify="required" >
						<option value="1" <c:if test="${question.level == 1}">selected="selected"</c:if>>简单</option>
						<option value="2" <c:if test="${question.level == 2}">selected="selected"</c:if>>一般</option>
						<option value="3" <c:if test="${question.level == 3}">selected="selected"</c:if>>困难</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">解题思路</label>
				<div class="layui-input-block">
					<textarea name="idea" autocomplete="off" class="layui-textarea" placeholder="解题思路">${question.idea}</textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">问题描述:</label>
				<div class="layui-input-block">
					<textarea name="question" class="layui-textarea">${question.question}</textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="*">修改</button>
					<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>
</html>