layui.use(['table', 'form', 'layer'], function() {

	/*定义模块化*/
	var form = layui.form,
		table = layui.table,
		layer = layui.layer;

	/*表格初始化*/
	table.render({
		elem: '#studnet_survey_table',
		method: 'post',
		url: _path + '/admin/survey/list_student_info',
		cols: [
			[{
					field: 'username',
					title: '用户姓名'
				},
				{
					field: 'phone',
					title: '电话号码'
				},
				{
					field: 'qq',
					title: 'qq号码'
				},
				{
					field: 'email',
					title: '邮件'
				},
				{
					field: 'job',
					title: '期待发展方向'
				},
				{
					field: 'remarks',
					title: '用户留言'
				},
				{
					title: '操作',
					fixed: 'right',
					width: 200,
					align: 'center',
					toolbar: '#student_survey_bar'
				}
			]
		],
		response: {
			statusCode: 200
		},
		request: {
			pageName: 'pageno',
			limitName: 'pagesize'
		},
		id: 'studentSurveyReload',
		page: true,
		limits: [10, 20, 30],
		limit: 10,
		loading: true,
		height: 'full-100'
	});

	/*多条件查询开始*/
	$('.search_survey_btn').on('click', function() {
		table.reload('studentSurveyReload', {
			where: {
				keyText: $("#keyText").val()
			}
		});
	});

	/*绑定事件操作*/
	table.on('tool(student_survey_filter)', function(obj) {
		var data = obj.data,
			layEvent = obj.event;

		if(layEvent === 'user') {
			parent.layer.open({
				title: '用户信息详情',
				maxmin: true,
				type: 2,
				area: ['450px', '530px'],
				content: _path + '/admin/survey/student_user_detail/' + data.id
			});
		} else if(layEvent === 'detail') {
			$.ajax({
				type: "post",
				url: _path + "/admin/survey/is_survey/" + data.id,
				async: true,
				success: function(result) {
					if(result.code == "200") {
						parent.layer.open({
							title: '测试信息详情',
							maxmin: true,
							type: 2,
							area: ['1000px', '600px'],
							content: _path + '/admin/survey/student_paper_detail/' + data.id
						});
					}else{
						parent.layer.msg("该用户尚未进行测试.");
					}
				}
			});

		} else if(layEvent === 'del') {
			layer.confirm('是否确定删除该用户信息', {
				title: '删除确认',
				icon: 0,
				btn: ['删除', '取消']
			}, function() {
				$.ajax({
					type: "post",
					url: _path + "/admin/survey/delete_student_info_id/" + data.id,
					async: true,
					success: function(result) {
						$(".layui-laypage-btn").click();
						parent.layer.msg(result.msg);
					}
				});
			}, function() {
				layer.msg("取消了删除操作");
			});
		}
	});
});