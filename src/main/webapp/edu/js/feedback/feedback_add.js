layui.use(['form'], function(){
	form = layui.form;
	
	  //监听提交
	form.on('submit(*)', function(data){
	    $.ajax({
			url: _path + "/edu/feedback/add_feedback",
			type: 'post',
			dataType: 'json',
			data: data.field,
			success: function(result){
				if(result.code == 200){
					parent.layer.closeAll();
				}
				parent.layer.msg(result.msg);
			}
		});
	    return false;
	});
});

