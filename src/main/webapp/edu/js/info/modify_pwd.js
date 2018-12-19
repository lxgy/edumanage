layui.use(['form','layer'], function(){
 	  var form = layui.form,
 	  layer = parent.layer == undefined ? layui.layer : parent.layer;
 	  
 	//添加验证规则
  form.verify({
	  oldPwd : function(value, item){
		  var isFlag = true;
	      $.ajax({
	    	  async: false,
	    	  url: _path+"/edu/check_oldPwd",
	    	  data: {'oldPwd':value},
	    	  dataType: 'json',
	    	  type: 'post',
	    	  success: function(data){
		    	  if(data.msg == '密码错误'){
		    		  isFlag = false;
		    	  }
	    	  }
	      });
	      if(!isFlag){
	    	  return "旧密码不正确";
	      }
	  },
	  newPwd : function(value, item){
	      if(value.length < 6){
	          return "密码长度不能小于6位";
	      }
	  },
	  confirmPwd : function(value, item){
	      if(!($("#oldPwd").val()==value)){
	      	  return "两次输入密码不一致，请重新输入！";
	      }
      }
  })
    
  //监听提交
  form.on('submit(updatePwd)', function(data){
	var index = layer.load();
	$.ajax({
		url : _path + '/edu/update_pwd',
		dataType : 'json',
		type : 'POST',
		data : data.field,
		success : function(result) {
			if(result.code == 200){
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

