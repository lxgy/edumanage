layui.use(['form','layer'], function(){
 	  var form = layui.form,
 	  layer = parent.layer == undefined ? layui.layer : parent.layer;
 	  
  /**
   * 更新
   */
  form.on('submit(updateTeacher)', function(data){
	var index = layer.load();
	$.ajax({
		url : _path + '/admin/teacher/update',
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
  
  $("[name='phone']").keyup(function(){
	  $("[name='wechat']").val($(this).val());
  });

  /**
   * 电话验证
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
		if( _phone == value){
			isFlag = true;
		}
		if(!isFlag){
			return '该手机已经被占用';
		}
    }
  }); 
});  

