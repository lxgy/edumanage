layui.use(['form','laydate','layer'], function(){
 	  var form = layui.form,
 	  laydate = layui.laydate,
 	  layer = parent.layer === undefined ? layui.layer : parent.layer;
 	
  //日期
  laydate.render({
    elem: '#date1'
  });
  laydate.render({
    elem: '#date2'
  });
  
  $("[name='phone']").keyup(function(){
	  $("[name='wechat']").val($(this).val());
  });
  
  //监听提交
  form.on('submit(addStudent)', function(data){
	var index = layer.load();
	$.ajax({
		url : _path + '/admin/student/add',
		dataType : 'json',
		type : 'POST',
		data : data.field,
		success : function(result) {
			if(result.code == "200"){
				$('[type=reset]').click();
				parent.$(".layui-laypage-btn").click(); 
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
  
  //用户名验证
  form.verify({
	checkphone: function(value, item){ //value：表单的值、item：表单的DOM对象
		var isFlag = false;
		$.ajax({
		  url : _path+"/admin/student/checkphone",
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

