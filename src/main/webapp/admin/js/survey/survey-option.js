layui.use(['form', 'layer'], function() {
	/*使用form、layer模块*/
	var form = layui.form,
		layer = layui.layer;

	/*表单监听 */
	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/survey/add_survey_option",
			data: data.field,
			dataType: 'json',
			success: function(result) {
				if(result.code == "200") {
					if(data.field.type != 3) {
						var html = '<tr><td>' + data.field.summary + '</td><td>';
						if(result.data.answer != undefined && result.data.answer == 1) {
							html = html + '<i class="fa fa-check" style="color:green;" >';
						} else {
							html = html + '<i class="fa fa-times" style="color:red;">';
						}
						html = html + '</td><td><button class="layui-btn layui-btn-xs layui-btn-primary" type="button" value="';
						html = html + result.data.id;
						html = html + '" onclick="updateOption(this)">修改</button><button class="layui-btn layui-btn-xs" type="button" value="';
						html = html + result.data.id;
						html = html + '" onclick="deleteOption(this)">删除</button></td></tr>'
						$('#option_new').before(html);
						form.render();
						layer.msg("添加成功");
						$("[type=reset]").click();
					} else if(data.field.type == 3) {
						layer.msg("修改成功");
					}
				} else if(result.code == "400") {
					layer.msg(result.msg)
				}
			}
		});
		return false;
	});
});

/*删除选项函数*/
function deleteOption(item) {
	layer.confirm('是否删除改选项', {
		title: '删除确认',
		icon: 0,
		btn: ['删除', '取消']
	}, function() {
		$.ajax({
			type: "post",
			url: _path + "/admin/survey/delete_option/" + item.value,
			async: true,
			dataType: 'json',
			success: function(result) {
				if(result.code == "200") {
					layer.close();
					$(item).parents("tr").remove();
				}
				layer.msg(result.msg);
			},
			error: function() {
				layer.msg("系统错误！")
			}
		});
	}, function() {
		layer.msg("取消删除操作");
	});
}

/*修改选项描述函数*/
function updateOption(item) {
	var _summary = $(item).parents("tr").children("td:first");
	layer.prompt({
		title: '选项描述修改',
		formType: 2,
		value: _summary.text()
	}, function(text, index) {
		$.ajax({
			type: "post",
			url: _path + "/admin/survey/update_option_summary1",
			async: true,
			dataType: 'json',
			data: {
				id: item.value,
				summary: text
			},
			success: function(result) {
				if(result.code == "200") {
					_summary.text(text);
				}
				layer.msg(result.msg);
				layer.close(index);
			},
			error: function() {
				layer.msg("系统错误");
			}
		});
		return false;
	});
}

/*修改选择题答案函数*/
function updateAnswer(item) {
	layer.confirm("是否修改该选项答案", {
		title: '修改确认',
		icon: 0,
		btn: ['修改', '取消']
	}, function() {
		var $item = $(item);
		var answer;
		if($item.attr('is-right') == '') {
			answer = 0;
		} else {
			answer = 1;
		}

		$.ajax({
			type: "post",
			url: _path + "/admin/survey/update_option_answer",
			data: {
				id: $item.attr('option-id'),
				answer: answer
			},
			success: function(result) {
				if(result.code == "200") {
					if(answer == 0) {
						$item.css('color', 'red');
						$item.removeClass('fa-check');
						$item.addClass('fa-times');
						$item.removeAttr('is-right');
					} else if(answer == 1) {
						$item.css('color', 'green');
						$item.removeClass('fa-times');
						$item.addClass('fa-check');
						$item.attr('is-right', '');
					}
				}
				layer.msg(result.msg);
			}
		});
	}, function() {
		layer.msg("取消修改操作");
	});
}
/*修改问答题参考答案函数*/
function updateSummary(obj) {
	var _summary = $('#tsum').val();
	$.ajax({
		type: "post",
		url: _path + "/admin/survey/update_option_summary2",
		async: true,
		dataType: 'json',
		data: {
			id:obj,
			summary: _summary
		},
		success: function(result) {
			layer.msg(result.msg);
		},
		error: function() {
			layer.msg("系统错误");
		}
	});
}