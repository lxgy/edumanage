<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>阶段增加</title>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript" src="${path }/admin/js/stage/stage_add.js"></script>
</head>

<body>
	<div id="" style="margin-top: 20px; margin-right: 20px;">
		<form action="" method="post" class="layui-form" id="stageAddF">

			<div class="layui-form-item">
				<label class="layui-form-label">所属阶段</label>
				<div class="layui-input-block">
					<select name="type" class="layui-select" autocomplete="off" lay-verify="required">
						<option value="">所属阶段</option>
						<option value="1">第一阶段</option>
						<option value="2">第二阶段</option>
						<option value="3">第三阶段</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">阶段名称</label>
				<div class="layui-input-block">
					<textarea  name="name" class="layui-textarea" placeholder="阶段内容" autocomplete="off" lay-verify="required"></textarea>
				</div>
			</div>
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="add_stage">增加</button>
					<button class="layui-btn layui-btn-primary" type="reset">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>