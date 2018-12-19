layui.use(['form', 'layer'], function() {
	var form = layui.form,
		layer = layui.layer;

	form.on('submit(*)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/survey/update_survey",
			async: true,
			dataType: "json",
			data: data.field,
			success: function(result) {
				if(result.code == '200') {
					parent.$(".layui-laypage-btn").click();
					parent.layer.closeAll();
				}
				layer.msg(result.msg);
			},
			error: function() {
				layer.msg('系统错误');
			}
		});
		return false;
	});
});