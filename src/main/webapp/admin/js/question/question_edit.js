layui.use(['form'], function() {
	var form = layui.form;

	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/question/update_question",
			data: data.field,
			dataType: "json",
			success: function(result) {
				if(result.code == '200'){
					parent.$(".layui-laypage-btn").click();
					parent.layer.closeAll();
				}
				parent.layer.msg(result.msg);
			},
			error: function() {
				layer.msg('系统错误');
			}
		});
		return false;
	});

});
