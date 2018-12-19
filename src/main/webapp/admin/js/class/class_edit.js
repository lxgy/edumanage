layui.use(['form','laydate','layer'], function(){
 	  var form = layui.form,
 	  laydate = layui.laydate,
 	  layer = parent.layer === undefined ? layui.layer : parent.layer;
  
	//日期
    laydate.render({
	   elem: '#date'
	});
    
  //监听提交
  form.on('submit(updateClass)', function(data){
	var index = layer.load();
	$.ajax({
		url : _path + '/admin/class/update',
		dataType : 'json',
		type : 'POST',
		data : data.field,
		success : function(result) {
			if(result.code == "200"){
				parent.$(".layui-laypage-btn").click(); 
				parent.layer.closeAll();
			}
			layer.msg(result.msg);
		},
		error : function(){
			layer.msg('系统错误');
		},
		complete : function(){
			layer.close(index);
		}
	});
	return false;
  });
  

});  

