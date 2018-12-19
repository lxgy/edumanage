<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加问卷调查</title>
		<%@ include file="/common/common.jsp"%>
		<script src="${path }/admin/js/survey/survey-add.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<!--主题内容开始-->

	<body>
		<div id="" style="margin-top: 15px;margin-right: 50px;">
			<!--表单内容开始-->
			<form action="" method="post" class="layui-form">
				<!--所属类型开始-->
				<div class="layui-form-item">
					<label class="layui-form-label">所属类型</label>
					<div class="layui-input-block">
						<select name="type" lay-verify="required">
							<option value="1">单选题</option>
							<option value="3">问答题</option>
						</select>
					</div>
				</div>
				<!--所属类型结束-->

				<!--调查内容描述开始-->
				<div class="layui-form-item">
					<label class="layui-form-label">调查内容描述</label>
					<div class="layui-input-block">
						<textarea name="description" lay-verify="required" autocomplete="off" placeholder="请输入调查内容描述..." class="layui-textarea"></textarea>
					</div>
				</div>
				<!--调查内容描述结束-->

				<!--备注开始-->
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea name="remarks" autocomplete="off" class="layui-textarea" placeholder="请输入备注信息..."></textarea>
					</div>
				</div>
				<!--备注结束-->
				<!--按钮操作开始-->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="*">增加</button>
						<button class="layui-btn layui-btn-primary" type="reset">重置</button>
					</div>
				</div>
				<!--按钮操作结束-->
			</form>
			<!--表单内容结束-->
		</div>
	</body>
	<!--主题内容结束-->

</html>