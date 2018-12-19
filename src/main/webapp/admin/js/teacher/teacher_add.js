layui.use(['form','layer'], function(){
 	  var form = layui.form,
 	  layer = parent.layer === undefined ? layui.layer : parent.layer;
 	  
  $("[name='phone']").keyup(function(){
	  $("[name='wechat']").val($(this).val());
  }); 
  
  /**
   * 添加
   */
  form.on('submit(addTeacher)', function(data){
	var index = layer.load();
	$.ajax({
		url : _path + '/admin/teacher/add',
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
  
  /**
   * 手机唯一性验证
   */
  form.verify({
	checkphone: function(value, item){ //value：表单的值、item：表单的DOM对象
		var isFlag = false;
		$.ajax({
		  url : _path+"/admin/teacher/checkphone",
		  data : {'phone':value},
		  type : 'post',
		  dataType : 'json',
		  async : false,
		  success : function(data){
	        if(data.code == '200'&& data.msg == '可以使用'){
	           isFlag = true;
	        }
	      }
		});
		if(!isFlag){
			return '该手机已经被占用';
		}
    }
  });  
  
});  

