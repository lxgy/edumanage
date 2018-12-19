<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>阶段详情</title>
	<%@ include file="/common/common.jsp"%>
	<script type="text/javascript" src="${path }/admin/js/stage/stage_edit.js"></script>
</head>

<body>
	<div id="" style="margin-top: 20px;margin-right: 10px;">
		<form action="" method="post" class="layui-form" id="stageF">

			<input type="hidden" name="id" id="id" value="${stage.id}" class="layui-input" />
			
			<div class="layui-form-item">
				<label class="layui-form-label">大阶段</label>
				<div class="layui-input-block">
					<input type="text" id="type" class="layui-input" style="border: 0;background: white;color: black;" disabled="disabled"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">阶段名称</label>
				<div class="layui-input-block">
					<textarea name="name" id="name" class="layui-textarea" placeholder="阶段内容" 
						autocomplete="off" lay-verify="required">${stage.name}</textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">修改</button>
					<button class="layui-btn layui-btn-primary" onclick="cancel();">取消</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var typeName = function getTypeName() {
			switch ('${stage.type}') {
			case '1':
				return '第一阶段';
			case '2':
				return '第二阶段';
			case '3':
				return '第三阶段';
			default:
				return '';
			}
		}
		$("#type").val(typeName);
	</script>
</body>
</html>