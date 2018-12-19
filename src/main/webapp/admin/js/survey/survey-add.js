layui.use(['form', 'layer'], function() {

	var form = layui.form,
		layer = layui.layer;

	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/survey/add_survey",
			async: true,
			data: data.field,
			success: function(result) {
				if(result.code == "200") {
					parent.$(".layui-laypage-btn").click();
				}
				layer.msg(result.msg);
			},
			error: function() {
				layer.msg("添加失败.");
			}
		});
		return false;
	});
});