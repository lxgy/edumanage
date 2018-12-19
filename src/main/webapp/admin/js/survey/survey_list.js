layui.use(['table', 'form', 'layer'], function() {

	/*定义模块化*/
	var form = layui.form,
		table = layui.table,
		layer = layui.layer;

	/*初始化表格列表开始*/
	table.render({
		elem: '#survey_table',
		method: 'post',
		url: _path + '/admin/survey/list_survey',
		cols: [[{field: 'type',title: '类型',width: 80,templet: '<div>{{typeName(d.type)}}</div>'},
			{field: 'description',title: '问卷问题描述'},
			{field: 'remarks',title: '备注'},
			{title: '操作',fixed: 'right',width: 200,align: 'center',toolbar: '#survey_bar'}]],
		response: {
			statusCode: 200
		},
		request: {
			pageName: 'pageno',
			limitName: 'pagesize'
		},
		id: 'surveyReload',
		page: true,
		limits: [10, 20, 30],
		limit: 10,
		loading: true,
		height: 'full-100'
	});

	/*多条件查询开始*/
	$('.search_survey_btn').on('click', function() {
		table.reload('surveyReload', {
			where: {
				keyText: $("#keyText").val(),
				type: $("#type").val()
			}
		});
	});

	/*添加问卷调查问题*/
	$('.add_survey_btn').on('click', function() {
		parent.layer.open({
			type: 2,
			title: '添加问卷调查问题',
			maxmin: true,
			area: ['1000px', '600px'],
			content: _path + "/admin/survey/survey_add"
		});
	});

	/*绑定事件操作*/
	table.on('tool(survey_filter)', function(obj) {
		var data = obj.data,
			layEvent = obj.event;

		if(layEvent === "detail") {
			parent.layer.open({
				type: 2,
				maxmin: true,
				title: '问卷调查选项',
				area: ['1000px', '600px'],
				content: _path + "/admin/survey/survey_option/" + data.id
			});
		} else if(layEvent === "edit") {
			parent.layer.open({
				type: 2,
				maxmin: true,
				area: ['1000px', '600px'],
				title: '修改问卷调查内容',
				content: _path + "/admin/survey/survey_edit?id=" + data.id
			})
		} else if(layEvent === "del") {
			layer.confirm('是否确认删除该项问卷调查', {
				title: '删除确认',
				icon: 0,
				btn: ['删除', '取消']
			}, function() {
				$.ajax({
					type: "post",
					url: _path + "/admin/survey/survey_delete",
					async: true,
					data: {
						id: data.id
					},
					success: function(message) {
						if(message.code == "200") {
							$(".layui-laypage-btn").click();
						}
						layer.msg(message.msg);
					},
					error: function() {
						layer.msg("系统错误，请重试！");
					}
				});
				return false;
			}, function() {
				layer.msg("取消删除操作")
			});
		}

	});
});

function typeName(type) {
	switch(type) {
		case 1:
			return "单选题";
		case 2:
			return "多选题";
		case 3:
			return "问答题";
		default:
			return "";
	}
}

function getDescription(description) {
	description = description.replace(/</g, "&lt;");
	description = description.replace(/>/g, "&gt;");
	description = description.replace(/\"/g, "\\\"");
	description = description.replace(/\'/g, "\\\'");
	description = description.replace(/\n/g, "<br>");
	description = description.replace(/\r/g, "<br>");
	return description;
}