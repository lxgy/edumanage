layui.use(['form', 'layer','layedit'],function() {
	var form = layui.form,
	layedit = layui.layedit,
	layer = layui.layer;

	form.on('submit(add_stage)', function(data) {
		$.ajax({
			type: "post",
			url: _path + "/admin/stage/add_stage",
			data: data.field,
			async: true,
			success: function(result) {
				if(result.code == "200"){
					parent.$(".layui-laypage-btn").click(); 
					parent.layer.closeAll();
				}
				layer.msg(result.msg);
			},
			error: function() {
				layer.msg("系统错误");
			}
		});
		return false;
	});
});