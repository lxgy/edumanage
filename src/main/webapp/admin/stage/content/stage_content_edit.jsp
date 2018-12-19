<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>阶段修改</title>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript" src="${path }/admin/js/stage/content/stage_content_edit.js"></script>
</head>

<body>
	<div style="margin-top: 20px;margin-right:10px;">
		<form action="" method="post" class="layui-form" >

			<div class="layui-form-item">
				<label class="layui-form-label">阶段名</label>
				<div class="layui-input-block">
					<input type="text" value="${content.stage.name}" class="layui-input" style="border: 0;background: white; color:black;" disabled="disabled" />
				</div>
			</div>
			<input type="hidden" name="id" value="${content.id}"/>
			<div class="layui-form-item">
				<label class="layui-form-label">阶段内容</label>
				<div class="layui-input-block">
					<textarea name="content" id="content" class="layui-textarea" placeholder="阶段内容" 
						autocomplete="off" lay-verify="required">${content.content}</textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">修改</button>
					<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>

</html>