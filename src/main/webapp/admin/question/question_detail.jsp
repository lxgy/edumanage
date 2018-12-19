<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>问题详情</title>
<%@ include file="/common/common.jsp"%>
<script src="${path }/admin/js/question/question_detail.js" type="text/javascript" charset="utf-8"></script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm" style="padding: 0 10px;">
		<table class="layui-table">
			<tr>
				<th width="100px">阶段</th>
				<td><c:if test="${question.stage_type ==1}">第一阶段</c:if>
					<c:if test="${question.stage_type ==2}">第二阶段</c:if>
					<c:if test="${question.stage_type ==3}">第三阶段</c:if>
				</td>
			</tr>
			<tr>
				<th>问题</th>
				<td>${question.question}</td>
			</tr>
			<tr>
				<th>类型</th>
				<td><c:if test="${question.type ==1}">单选题</c:if>
					<c:if test="${question.type ==2}">多选题</c:if>
					<c:if test="${question.type ==3}">问答题</c:if>
				</td>
			</tr>
		</table>
	</blockquote>
	
	<!-- 选择题 -->
	<c:if test="${question.type !=3}">
	  <form action="" method="post"  class="layui-form">
	  	<input type="hidden" name="stage_question.type" value="${question.type}"/>
	  	<input type="hidden" name="type" value="${question.type}"/>
	  	<input type="hidden" name="stage_question.id" value="${question.id}"/>
		<table class="layui-table">
			<tr><th>选项</th><th width="70px" align="center">正确/错误</th><th width="90px" align="center">操作</th></tr>
			<c:forEach items="${question.options}" var="option">
				<tr>
					<td>${option.summary}</td>
					<td>
						<c:if test="${option.is_answer==1}"><i class="fa fa-check" style="color:green;" onclick="updateAnswer(this)" option-id="${option.id}" is-right></i></c:if>
						<c:if test="${option.is_answer==undefined || option.is_answer!=1}"><i class="fa fa-times" style="color:red;" onclick="updateAnswer(this)" option-id="${option.id}" is-right></i></c:if>
					</td>
					<td>
						<button class="layui-btn layui-btn-xs layui-btn-primary" type="button" value="${option.id}" onclick="updateOption(this)">修改</button>
						<button class="layui-btn layui-btn-xs" type="button" value="${option.id}" onclick="deleteOption(this)">删除</button>
					</td>
				</tr>
			</c:forEach>
			
			
			<tr id="option_new">
				<td style="padding: 0">
					<input type="text" name="summary" lay-verify="required" placeholder="新增选项" class="layui-input"/>
				</td>
				<td>
					<input type="checkbox" name="is_answer" value="1">
				</td>
				<td>
					<button class="layui-btn layui-btn-primary layui-btn-xs" type="reset">重置</button>
					<button class="layui-btn layui-btn-xs" lay-submit lay-filter="*">新增</button>
				</td>
			</tr>
			
		</table>
	  </form>
	</c:if>
	
	<!-- 问答题 -->
	<c:if test="${question.type ==3}">
		<form class="layui-form">
		  <input type="hidden" name="stage_question.type" value="${question.type}"/>
		  <input type="hidden" name="type" value="${question.type}"/>
		  <input type="hidden" name="stage_question.id" value="${question.id }"/>
		  <c:if test="${question.options.size()>0}">
		  		<input type="hidden" name="id" value="${question.options.get(0).id}"/>
		  </c:if>
		  <div class="layui-form-item layui-form-text">
		  
		  	<fieldset class="layui-elem-field layui-field-title">
			  <legend>参考答案</legend>
			  <div class="layui-field-box">
			  	<textarea name="summary" placeholder="参考答案" class="layui-textarea"><c:if test="${question.options.size()>0}">${question.options.get(0).summary }</c:if></textarea>
			  </div>
			</fieldset>
		  </div>
		  <div class="layui-form-item">
		      <button class="layui-btn" lay-submit lay-filter="*">提交参考答案</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		  </div>
		</form>
	</c:if>
		
</body>
</html>