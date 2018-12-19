<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/common/common.jsp" %>
		<script type="text/javascript" src="${path}/edu/js/question/question_list.js"></script>
		<title>答题情况</title>
		<style type="text/css">
			.question {
				padding: 5px;
				background: rgba(238, 238, 238, 0.19);
				margin: 5px;
			}
			
			.option {
				margin: 5px;
				margin-left: 30px;
			}
			
			.option input {
				margin-right: 10px;
			}
			
			textarea {
				max-width: 800px;
			}
			
			.layui-form-radio span,
			.layui-form-checkbox[lay-skin="primary"] span {
				color: black;
			}
			
			xmp {
				display: inline-block;
				margin: 0;
			}
		</style>
	</head>

	<body style="background: rgb(248, 248, 248)">
		<!-- 页头 -->
		<%@ include file="/edu/courses/header.jsp" %>
		<!-- 内容主体区域 -->
		<div class="layui-container">
			<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;">
				<div class="layui-inline">

					<div class="layui-input-inline">
						<select name="stage_type">
							<option value="">大阶段</option>
							<option value="1">第一阶段</option>
							<option value="2">第二阶段</option>
							<option value="3">第三阶段</option>
						</select>
					</div>
				</div>

				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary search_question_btn">随机题目</a>
				</div>

				<a class="layui-btn layui-btn-primary layui-btn-sm" href="${path}/edu/question/paper/question_paper_list" style="float: right;">答题记录</a>
			</blockquote>

			<blockquote class="layui-elem-quote  layui-quote-nm" style="margin-top: 10px;background: white;min-height: 400px;">
				<div id="content" style="max-width: 900px;margin: auto;">

				</div>

			</blockquote>

		</div>

		<!-- 页尾 -->
		<%@ include file="/edu/courses/footer.jsp" %>

		<script type="text/html" id="question_list">
			<form class="layui-form" action="">
				<input type="hidden" name="type" value="{{d.type||" "}}"/>
				<input type="hidden" name="stage.id" value="{{d.stage==undefined?" ":d.stage.id}}"/> {{# layui.each(d.question_students, function(index, item){ }}
				<div class="question">
					<h3>
				{{#if(item.question.type==1){ }}<span class="layui-badge-rim">单</span>{{#}}}
				{{#if(item.question.type==2){ }}<span class="layui-badge-rim">多</span>{{#}}}
				{{#if(item.question.type==3){ }}<span class="layui-badge-rim">问</span>{{#}}}
				<input type="hidden" id="question_{{index}}" value="{{item.question.id}}" question-type="{{item.question.type}}"/>
				<span>{{item.question.question}}</span>
				</h3> {{#if(item.question.type==1){ }} {{# layui.each(item.question.options, function(index1, item1){ }}
					<div class="option">
						<input type="radio" name="answer_{{index}}" value="{{item1.id}}" title="<xmp>{{item1.summary}}</xmp>" />
					</div>
					{{# }); }} {{#}}} {{#if(item.question.type==2){ }} {{# layui.each(item.question.options, function(index2, item2){ }}
					<div class="option">
						<input type="checkbox" name="answer_{{index}}" value="{{item2.id}}" title="<xmp>{{item2.summary}}</xmp>" lay-skin="primary" />
					</div>
					{{# }); }} {{#}}} {{#if(item.question.type==3){ }}
					<textarea name="answer_{{index}}" placeholder="答题" class="layui-textarea option"></textarea> {{#}}}
				</div>
				{{# }); }} {{# if( d.question_students == undefined || d.question_students.length === 0){ }} 暂时没有题目 {{# } }} {{# if( d.question_students != undefined ){ }}
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-primary" onclick="submit_paper(this)" type="button">提交</button>
					</div>
				</div>
				{{# } }}

			</form>
		</script>
	</body>

</html>