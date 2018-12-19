layui.use(['table','form', 'layer'], function() {
	var form = layui.form,
	table = layui.table,
	layer = layui.layer;

	table.render({
		elem: '#question_table',
		method: 'post',
		url: _path + '/admin/question/list_question',
		cols: [[
			 {field: 'stage',title: '阶段',width:80,templet: '<div>{{stageTypeName(d.stage_type)}}</div>'}
			,{field: 'type',title: '类型',width:70,templet: '<div>{{typeName(d.type)}}</div>'}
			,{field: 'question',title: '问题描述'}
			,{field: 'level',title: '难度',width:65,templet: '<div>{{levelName(d.level)}}</div>'}
			,{title: '操作',fixed: 'right',width:200,align: 'center',toolbar: '#question_bar'}
		]],
		response: {
			statusCode: 200
		},
		request: {
			pageName: 'pageno',
			limitName: 'pagesize'
		},
		id: 'questionReload',
		page: true,
		limits: [10, 20, 30],
		limit: 10,
		loading: true,
		height: 'full-100',
		size: 'sm'
	});

	//	多条件查询
	$('.search_question_btn').on('click', function() {
		table.reload('questionReload', {
			where: {
				keyText: $('#keyText').val(),
				type: $('#type').val(),
				stage_type: $('#stage_type').val(),
				level:$('#level').val()
			}
		});
	});

	//添加操作
	$(".add_question_btn").on('click', function() {
		layer.open({
			type: 2,
			maxmin: true,
			title: '添加问题',
			area: ['700px', '500px'],
			content: _path + "/admin/question/question_add"
		});
	});

	//	toolbar操作
	table.on('tool(question_filter)', function(obj) {
		var data = obj.data,
			layEvent = obj.event;

		if(layEvent === "detail") {
			parent.layer.open({
				type: 2,
				title: '选项管理',
				maxmin: true,
				area: ['1000px', '600px'],
				content: _path + "/admin/question/question_detail/" + data.id
			});
		} else if(layEvent === "edit") {
			layer.open({
				title: '问题修改',
				type: 2,
				maxmin: true,
				area: ['700px', '500px'],
				content: _path + "/admin/question/question_edit/" + data.id
			});
		} else if(layEvent === "del") {
			layer.confirm("是否删除该问题", {
				title: '删除确认',
				icon: 0,
				btn: ['删除', '取消']
			}, function() {
				$.ajax({
					type: "post",
					url: _path + "/admin/question/delete_question/" + data.id,
					async: true,
					success: function(data) {
						if(data == "200"){
							$(".layui-laypage-btn").click();
						}
						layer.msg(data.msg);
					}
				});
				return false;
			}, function() {
				layer.msg("取消删除操作");
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
function stageTypeName(stage_type) {
	switch(stage_type) {
		case 1:
			return "第一阶段";
		case 2:
			return "第二阶段";
		case 3:
			return "第三阶段";
		default:
			return "";
	}
}
function levelName(level){
	switch (level) {
	case 1:
		return '<i class="fa fa-star"></i>';
	case 2:
		return '<i class="fa fa-star"></i><i class="fa fa-star"></i>';
	case 3:
		return '<i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i>';
	default:
		return "";
	}
}

function getQuestion(question){
	question = question.replace(/</g,"&lt;");
	question = question.replace(/>/g,"&gt;");
	question = question.replace(/\"/g,"\\\"");
	question = question.replace(/\'/g,"\\\'");
	question = question.replace(/\n/g,"<br>");
	question = question.replace(/\r/g,"<br>");
	return question;
}