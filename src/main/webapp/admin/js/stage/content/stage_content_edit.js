layui.use(['form', 'layer', 'jquery'], function() {
	var form = layui.form,
		layer = layui.layer;

	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/stage/content/update_stage_content",
			data: data.field,
			async: true,
			success: function(result) {
				if(result.code == "200"){
					parent.$(".layui-laypage-btn").click(); 
					parent.layer.closeAll();
				}
				parent.layer.msg(result.msg);
			},
			error: function() {
				layer.msg("系统错误");
			}
		});
		return false;
	});
});

