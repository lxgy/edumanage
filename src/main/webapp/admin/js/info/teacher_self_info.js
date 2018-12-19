layui.use(['form','layer'], function(){
 	  var form = layui.form,
 	  layer = parent.layer == undefined ? layui.layer : parent.layer;
 	  
  //监听提交
  form.on('submit(userInfo)', function(data){
	$.ajax({
		url : _path + '/admin/teacher/update',
		dataType : 'json',
		type : 'POST',
		data : data.field,
		success : function(result) {
			if(result.code == "200"){
				//location.reload();
			}
			layer.msg(result.msg);
		},
	});
	return false;
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