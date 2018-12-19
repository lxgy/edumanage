<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/common/common.jsp" %>
		<script type="text/javascript" src="${path}/edu/js/index.js"></script>
		<title>档案</title>
		<style type="text/css">
			.layui-tab {
				background-color: white;
				min-height: 450px;
				margin-bottom: 2px;
			}
			
			.evaluation {
				margin: 10px 0;
				background: white;
			}
			
			.evaluation form {
				padding: 20px;
			}
		</style>

	</head>

	<body style="background: rgb(248, 248, 248)">
		<!-- 页头 -->
		<%@ include file="/edu/courses/header.jsp" %>
		
		<script type="text/javascript">
		   var _id = ${edu_student.id};
		</script>

		<!-- 内容主体区域 -->
		<div class="layui-container">
			<div class="layui-tab layui-tab-brief" lay-filter="stage">
				<ul class="layui-tab-title">
					<li class="layui-this" value="1">第一阶段</li>
					<li value="2">第二阶段</li>
					<li value="3">第三阶段</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show"></div>
					<div class="layui-tab-item"></div>
					<div class="layui-tab-item"></div>
				</div>
			</div>
			<div id="page" style="background: white;padding-left: 10px;"></div>
			<div class="evaluation">
				<form class="layui-form">
					<input type="hidden" name="id">
					<div class="layui-form-item layui-form-text">
						<fieldset class="layui-elem-field layui-field-title">
							<legend>阶段评价</legend>
						</fieldset>
						<textarea disabled name="content" placeholder="自我评价" lay-verify="required" class="layui-textarea"></textarea>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button type="button" class="layui-btn" value="0" onclick="evaluation(this)">评价</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- 页尾 -->
		<%@ include file="/edu/courses/footer.jsp" %>

		<script type="text/html" id="evaluation">
			<form class="layui-form layui-form-pane" style="margin: 20px">
				<input type="hidden" name="id" value="{{d.id}}">
				<div class="layui-form-item">
					<label class="layui-form-label">老师得分</label>
					<div class="layui-input-block">
						<input type="text" name="teacher_score" placeholder="老师得分" value="{{d.teacher_score||" "}}" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">自我得分</label>
					<div class="layui-input-block">
						<input type="text" name="student_score" placeholder="自我得分" value="{{d.student_score||" "}}" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">自我评价</label>
					<div class="layui-input-block">
						<textarea name="evaluation" placeholder="自我评价" lay-verify="required" class="layui-textarea">{{d.evaluation||""}}</textarea>
					</div>
				</div>
			</form>
		</script>

		<script type="text/html" id="stage_list">
			<table class="layui-table" id="table_stage" lay-size="sm">
				<thead>
					<tr>
						<th align="center" width="200">阶段</th>
						<th align="center">内容</th>
						<th align="center" width="60">老师得分</th>
						<th align="center" width="60">自我得分</th>
						<th align="center">自我评价</th>
						<th align="center">老师评价</th>
						<th align="center" width="30">操作</th>
					</tr>
				</thead>
				<tbody>
					{{# layui.each(d.data, function(index, item){ }}
					<tr>
						<td rowspan={{item.student_evaluation.length+1}}>{{ item.name }}</td>
						<td colspan="5" height="0" style="padding: 0"></td>
					</tr>
					{{# layui.each(item.student_evaluation, function(index, item2){ }}
					<tr>
						<td>{{ item2.stage_content.content }}</td>
						<td>{{ item2.teacher_score||"" }}</td>
						<td>{{ item2.student_score||"" }}</td>
						<td>{{ item2.evaluation||"" }}</td>
						<td>{{ item2.teacher_evaluation||"" }}</td>
						<td><button class="layui-btn layui-btn-xs layui-btn-primary" onclick="evaluate_stage(this)" value="{{item2.id}}">评价</button></td>
					</tr>
					{{# }); }} {{# }); }} {{# if( d.data == undefined || d.data.length === 0){ }}
					<tr>
						<td colspan="7">暂无数据</td>
					</tr>

					{{# } }}

				</tbody>
			</table>
		</script>
	</body>

</html>