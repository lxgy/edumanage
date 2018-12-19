<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/edu/js/question/question_list.js"></script>
<title>答题详情</title>
<style type="text/css">
	.question{
		padding:10px;
		background: rgba(238, 238, 238, 0.19);
		margin: 5px;
	}
	.option{
		margin: 0 30px;
	}
	.layui-form-radio span,.layui-form-checkbox[lay-skin="primary"] span{
		color: #8e8b8b;
		height: 28px;
	}
	.layui-form-radioed i{
		color: black !important;
	}
	.layui-form-checked[lay-skin="primary"] i {
		border-color: black;
    	background-color: black;
	}
	.is_error{
		background: #c4bebe1a;
	}
	.is_answer div span,is_answer,.is_answer p,.is_answer .layui-form-checkbox[lay-skin="primary"] span{
		color: black;
		font-weight: 700;
	}
	xmp{
		margin: 0;
		display: inline;
	}
</style>
</head>
<body style="background: rgb(248, 248, 248)">
	<!-- 页头 -->
	<%@ include file="/edu/courses/header.jsp" %>
	<!-- 内容主体区域 -->
	<div class="layui-container" style="padding: 10px 30px;">
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white; padding-left: 20px;">
			<div style="margin: auto; max-width: 900px;" >
				<span style="letter-spacing: 1px;font-size: 20px;">${paper.student.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span style="color: gray;">
					选择题数:${paper.choice_num}&nbsp;&nbsp;
					答对题数:${paper.right_num}&nbsp;&nbsp;&nbsp;
					（选择题正确答案为加粗选项）
				</span>
				<a class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:window.history.back()" style="float: right">返回</a>
			</div>
		</blockquote>
		
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;min-height: 400px;">
		   <div style="margin: auto; max-width: 900px;margin-bottom: 30px;" >
			<c:forEach items="${paper.question_students}" var="item">
				<div class="question <c:if test="${item.is_right == 0}">is_error</c:if>">
					<h3>
						<c:if test="${item.question.type==1}"><span class="layui-badge-rim">单</span></c:if>
						<c:if test="${item.question.type==2}"><span class="layui-badge-rim">多</span></c:if>
						<c:if test="${item.question.type==3}"><span class="layui-badge-rim">问</span></c:if>
						<span>${item.question.question}</span>
					</h3>
					<c:if test="${item.is_right == 0}"><i class="fa fa-times" aria-hidden="true" style="color: red;float:right;"></i></c:if>
					<c:if test="${item.is_right == 1}"><i class="fa fa-check" aria-hidden="true" style="color: green;float: right;"></i></c:if>
					
					<c:if test="${item.question.type==1}">
						<c:forEach items="${item.question.options}" var="option1">
							<div class="option <c:if test="${option1.is_answer==1}">is_answer</c:if>">
								<input type="radio" title="<xmp>${option1.summary}</xmp>" disabled 
									<c:forEach items="${item.options}" var="option">
										<c:if test="${option1.id == option.id}">checked</c:if>
									</c:forEach>/>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${item.question.type==2}">
						<c:forEach items="${item.question.options}" var="option2">
							<div class="option <c:if test="${option2.is_answer==1}">is_answer</c:if>">
								<input type="checkbox" title="<xmp>${option2.summary}</xmp>" lay-skin="primary" disabled 
									<c:forEach items="${item.options}" var="option">
										<c:if test="${option2.id == option.id}">checked</c:if>
									</c:forEach>/>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${item.question.type==3}">
						<div class="option"><p>&nbsp;&nbsp;&nbsp;&nbsp;${item.answer}</p></div>
						<c:if test="${item.question.options!=null  && item.question.options.size()>0}">
							<div class="option is_answer">
							 	<hr>
							 	<p>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: gray;font-weight: 500">(参考答案)</span>${item.question.options.get(0).summary}</p></div>
						</c:if>
					</c:if>
				</div>
			
			</c:forEach>
		   </div>
		</blockquote>
		
	</div>
	
	<!-- 页尾 -->
	<%@ include file="/edu/courses/footer.jsp" %>
	
	<script type="text/javascript">
		layui.use('form', function(){
			var form = layui.form;
			form.render();
		});
	</script>
</body>
</html>