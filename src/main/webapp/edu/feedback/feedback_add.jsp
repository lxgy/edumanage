<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/edu/js/feedback/feedback_add.js"></script>
<title>反馈</title>
</head>
<body class="childrenBody" >
	<form class="layui-form layui-form-pane">
		 <div class="layui-form-item layui-form-text">
		 	<label class="layui-form-label">反馈信息</label>
		    <div class="layui-input-block">
		      <textarea name="content" placeholder="反馈内容" class="layui-textarea"></textarea>
		    </div>
		</div>

		<div class="layui-form-item">
			<button class="layui-btn" lay-submit="" lay-filter="*">提交反馈</button>
			<button class="layui-btn layui-btn-primary" type="reset">重置</button>
		</div>
	</form>
</body>
</html>