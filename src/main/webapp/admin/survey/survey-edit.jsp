<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>问卷调查内容修改</title>
		<%@ include file="/common/common.jsp"%>
		<script src="${path }/admin/js/survey/survey-edit.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<!--主体内容开始-->
		<div id="" style="margin-top: 15px; margin-right: 50px;">
			<!--表单内容开始-->
			<form action="" method="post" class="layui-form">
				<!--id设为隐藏 开始-->
				<input type="hidden" name="id" id="id" value="${survey.id}" />
				<!--id值设为隐藏 结束-->
				<!--问卷调查类型开始-->
				<div class="layui-form-item">
					<label class="layui-form-label">问卷调查类型</label>
					<div class="layui-input-block">
						<select name="type" id="type" class="layui-select">
							<option value="1" <c:if test="${survey.type == 1}">selected="selected"</c:if>>单选题</option>
							<option value="3" <c:if test="${survey.type == 3}">selected="selected"</c:if>>问答题</option>
						</select>
					</div>
				</div>
				<!--问卷调查类型结束-->
				<!--问卷调查描述结束-->
				<div class="layui-form-item">
					<label class="layui-form-label">问卷调查描述:</label>
					<div class="layui-input-block">
						<textarea name="description" class="layui-textarea">${survey.description}</textarea>
					</div>
				</div>
				<!--问卷调查描述结束-->
				<!--问卷调查备注开始-->
				<div class="layui-form-item">
					<label class="layui-form-label">问卷调查备注:</label>
					<div class="layui-input-block">
						<textarea name="remarks" class="layui-textarea">${survey.remarks}</textarea>
					</div>
				</div>
				<!--问卷调查备注结束-->
				<!--按钮操作开始-->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="*">修改</button>
						<button class="layui-btn layui-btn-primary" type="reset">重置</button>
					</div>
					<!--按钮操作结束-->
				</div>
			</form>
			<!--表单内容结束-->
		</div>
		<!--主体内容结束-->
	</body>

</html>