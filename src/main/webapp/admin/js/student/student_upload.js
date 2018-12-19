layui.use(['upload','layer'], function(){
  var upload = layui.upload,
  layer = layui.layer;
   
  var filename = "";
  
  //执行实例
  var uploadInst = upload.render({
    elem: '#upload_student' //绑定元素
    ,url: _path + '/upload_student_excel' //上传接口
    ,accept: 'file' //普通文件
    ,exts: 'xlsx|xls'
//    ,auto: false
    ,done: function(res){
      if(res.result){
    	  $('#upload_student p').html('<i class="fa fa-file-excel-o" style="color:green"></i> &nbsp;'+res.ori);
    	  $('#upload_student p').css("color","black")
    	  filename = res.name;
    	  layer.msg(res.msg);
      }
    }
    ,error: function(){
    	filename = "";
    }
  });
  
  $("#stu_info").click(function(){
	  $.ajax({
		  url: _path + "/admin/student/import_info",
		  type: 'post',
		  dataType: 'json',
		  data: {'filename':filename},
		  success: function(data){
			  if(data.code == "200"){
				  parent.$(".layui-laypage-btn").click(); 
				  parent.layer.closeAll();
			  }
			  parent.layer.msg(data.msg);
		  }
	  });
  });
  
});




