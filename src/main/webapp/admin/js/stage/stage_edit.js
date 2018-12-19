layui.use(['form', 'layer'], function() {
	var layer = layui.layer,
	form = layui.form;

	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/stage/update_stage",
			data: data.field,
			dataType: "json",
			success: function(result) {
				if(result.code == "200"){
					parent.$(".layui-laypage-btn").click();
					parent.layer.closeAll();
				}
				layer.msg(data.msg);
			},
			error: function() {
				layer.msg("系统错误");
			}
		});
		return false;
	});

});
