<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户详情</title>
		<%@ include file="/common/common.jsp"%>
	</head>

	<body style="margin:20px;">
		<form action="" method="post" class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label">用户姓名</label>
				<div class="layui-input-inline">
					<input type="text" name="" id="" value="${studentInfo.username}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">手机号码</label>
				<div class="layui-input-inline">
					<input type="text" name="" id="" value="${studentInfo.phone}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">qq号码</label>
				<div class="layui-input-inline">
					<input type="text" name="" id="" value="${studentInfo.qq}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱号码</label>
				<div class="layui-input-inline">
					<input type="text" name="" id="" value="${studentInfo.email}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">期待工作</label>
				<div class="layui-input-inline">
					<input type="text" name="" id="" value="${studentInfo.job}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">留言</label>
				<div class="layui-input-inline">
					<textarea name="" rows="" cols="" class="layui-textarea">${studentInfo.remarks}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-left:200px;margin-top:30px;">
				<div class="layui-input-inline">
					<button class="layui-btn layui-btn-normal close-user">关闭</button>
				</div>
			</div>
		</form>
	</body>

	<script type="text/javascript">
		layui.use(['form', 'layer'], function() {
			var form = layui.form,
				layer = layui.layer;
				
			$(".close-user").on('click',function(){
				parent.layer.closeAll();
			});
		});
	</script>

</html>