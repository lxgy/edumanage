<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>问卷选项</title>
		<%@ include file="/common/common.jsp"%>
		<script src="${path}/admin/js/survey/survey-option.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<!--主题内容开始-->

	<body class="childrenBody">
		<!--问卷调查内容开始-->
		<blockquote class="layui-elem-quote layui-quote-nm" style="padding: 0 10px;">
			<table class="layui-table">
				<!--调查类型开始-->
				<tr>
					<th>问卷调查类型</th>
					<td>
						<c:if test="${survey.type ==1}">单选题</c:if>
						<c:if test="${survey.type ==2}">多选题</c:if>
						<c:if test="${survey.type ==3}">问答题</c:if>
					</td>
				</tr>
				<!--调查类型结束-->
				<!--描述开始-->
				<tr>
					<th>问卷调查描述</th>
					<td>${survey.description}</td>
				</tr>
				<!--描述结束-->
				<!--备注开始-->
				<tr>
					<th>备注</th>
					<td>${survey.remarks}</td>
				</tr>
				<!--备注结束-->
			</table>
		</blockquote>
		<!--问卷调查内容结束-->

		<!--选项内容开始-->
		<!--选择题开始-->
		<c:if test="${survey.type !=3}">
			<!--表单内容开始-->
			<form action="" method="post" class="layui-form">

				<input type="hidden" name="survey.id" id="survey" value="${survey.id}" />
				<table class="layui-table">
					<!--表头开始-->
					<tr>
						<th>选项</th>
						<th width="70px" align="center">正确/错误</th>
						<th width="90px" align="center">操作</th>
					</tr>
					<!--表头结束-->
					<!--循环罗列所有的选项开始-->
					<c:forEach items="${survey.survey_options}" var="option">
						<tr>
							<td>${option.summary}</td>
							<td>
								<c:if test="${option.answer==1}"><i class="fa fa-check" style="color:green;" onclick="updateAnswer(this)" option-id="${option.id}" is-right></i></c:if>
								<c:if test="${option.answer==undefined || option.answer!=1}"><i class="fa fa-times" style="color:red;" onclick="updateAnswer(this)" option-id="${option.id}" is-right></i></c:if>
							</td>
							<td>
								<button class="layui-btn layui-btn-xs layui-btn-primary" type="button" value="${option.id}" onclick="updateOption(this)">修改</button>
								<button class="layui-btn layui-btn-xs" type="button" value="${option.id}" onclick="deleteOption(this)">删除</button>
							</td>
						</tr>
					</c:forEach>
					<!--循环罗列所有的选项结束-->
					<!--新增选项操作开始-->
					<tr id="option_new">
						<td style="padding: 0">
							<input type="text" name="summary" lay-verify="required" placeholder="请输入新增选项描述..." class="layui-input" />
						</td>
						<td>
							<input type="checkbox" name="answer" value="1">
						</td>
						<td>
							<button class="layui-btn layui-btn-primary layui-btn-xs" type="reset">重置</button>
							<button class="layui-btn layui-btn-xs" lay-submit lay-filter="*">新增</button>
						</td>
					</tr>
					<!--新增选项操作结束-->
				</table>
			</form>
			<!--表单内容结束-->
		</c:if>
		<!--选择题结束-->
		<!--问答题开始-->
		<c:if test="${survey.type ==3}">
			<!--表单内容开始-->
			<form class="layui-form">
				<input type="hidden" name="survey.id" id="survey" value="${survey.id}" />
				<c:if test="${survey.survey_options.size()>0}">
					<input type="hidden" name="id" value="${survey.survey_options.get(0).id}" />
				</c:if>
				<!--参考意见开始-->
				<div class="layui-form-item layui-form-text">
					<fieldset class="layui-elem-field layui-field-title">
						<legend>参考答案</legend>
						<div class="layui-field-box">
							<textarea name="summary" id="tsum" placeholder="请输入参考答案..." class="layui-textarea"><c:if test="${survey.survey_options.size()>0}">${survey.survey_options.get(0).summary }</c:if></textarea>
						</div>
					</fieldset>
				</div>
				<!--参考意见结束-->
				<!--操作按钮开始-->
				<div class="layui-form-item">
					<c:if test="${survey.survey_options.size()>0}">
						<button class="layui-btn" onclick="updateSummary(${survey.id});">修改参考答案</button>
					</c:if>
					<c:if test="${survey.survey_options.size()==0}">
						<button class="layui-btn" lay-submit lay-filter="*">提交参考答案</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</c:if>
				</div>
				<!--操作按钮结束-->
			</form>
			<!--表单内容结束-->
		</c:if>
		<!--问答题结束-->
		<!--选项内容结束-->
	</body>
	<!--主体内容结束-->

</html>