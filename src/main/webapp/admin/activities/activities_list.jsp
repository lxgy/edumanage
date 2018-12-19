<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动报名列表</title>
		<%@ include file="/common/common.jsp" %>
	</head>

	<body class="childrenBody">
		<!--头部功能展示展示开始-->
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form">
			<!--关键词搜索开始-->
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="keyText" id="keyText" value="" placeholder="请输入关键字" class="layui-input search_input" />
				</div>
			</div>
			<!--关键词搜索结束-->
			<!--查询、添加按钮开始-->
			<div class="layui-inline">
				<a class="layui-btn search_activities_btn">查询</a>
				<!-- <a class="layui-btn layui-btn-danger reset-btn">重置</a> -->
			</div>
			<!--查询添加按钮结束-->
		</blockquote>
		<!--头部功能展示结束-->

		<!--表格信息列表开始-->
		<table class="layui-hide" id="activities_table" lay-filter="activities_filter" lay-size="sm"></table>
		<!--表格信息列表结束-->

		<!--绑定事件操作开始-->
		<script type="text/html" id="survey_bar">
			<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">详情</a>
		</script>
		<!--绑定事件操作结束-->
	</body>

	<script type="text/javascript">
		layui.use(['table', 'form', 'layer'], function() {
			/*定义模块化*/
			var form = layui.form,
				table = layui.table,
				layer = layui.layer;

			/*初始化表格列表开始*/
			table.render({
				elem: '#activities_table',
				method: 'post',
				url: _path + '/admin/activities/findAllActivities',
				cols: [
					[{
							field: 'name',
							title: '姓名',
							width: '150'
						},
						{
							field: 'qq_num',
							title: 'QQ_NUM',
							width: '150'
						},

						{
							field: 'phone',
							title: '手机号码',
							width: '150'
						},
						{
							field: 'email',
							title: '专业',
							width: '300'
						},
						{
							field: 'school',
							title: '学校',
							width: '200'
						},
						{
							field: 'end_time',
							title: '毕业时间',
							width: '150'
						},{
							field: 'create_time',
							title: '报名时间',
							width: '150'
						}
						/* ,
						{
							title: '操作',
							fixed: 'right',
							width: 200,
							align: 'center',
							toolbar: '#survey_bar'
						} */
					]
				],
				response: {
					statusCode: 200
				},
				request: {
					pageName: 'pageno',
					limitName: 'pagesize'
				},
				id: 'activitiesReload',
				page: true,
				limits: [10, 20, 30],
				limit: 10,
				loading: true,
				height: 'full-100'
			});
		});
	</script>

</html>