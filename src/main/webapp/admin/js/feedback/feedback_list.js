layui.use(['table','layer'], function(){
	var table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
//    layer = layui.layer;
  
	//初始化表格
  	table.render({ 
  		elem: '#table_feedback'
  		,url: _path + '/admin/feedback/list'
  		,method: 'post' //如果无需自定义HTTP类型，可不加该参数
  		,cols: [[
  	        {field:'name', title:'学生', width:100,fixed:'left',templet:'<div>{{d.student.name}}</div>' }
  	        ,{field:'seehope_class', title:'班级', width:100, templet:'<div>{{d.student.seehope_class==undefined?"":d.student.seehope_class.name}}</div>'}
	  	    ,{field:'update_time', title:'反馈时间', width:180}
	  	    ,{field:'content', title:'内容'}
	        ,{fixed:'right', width:150, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,id: 'feedback_table'
  	    ,page : true
  	    ,limits: [10,20,30]
  	    ,limit: 10 //默认采用60
  	    ,height: 'full-100'
	}); 
  	
  	$('.layui-form-checkbox').click( function(){
	  table.reload('feedback_table', {
	    where: {
          status: $('#status').prop("checked")
	    }
	  });
	});
	
	//监听工具条
	table.on('tool(feedback)', function(obj){ 
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'edit'){ //标记已读
		  $.ajax({
	    	   url : _path + "/admin/feedback/update_status/"+data.id,
	    	   dataType : 'json',
	    	   type : 'post',
	    	   success : function(result){
	    		   if(result.code=="200"){
	    			   $(".layui-laypage-btn").click();  
	    		   }
	    		  layer.msg(result.msg);
	    	   }
	       });
	  } else if(layEvent === 'delete'){ //编辑
		  $.ajax({
	    	   url : _path + "/admin/feedback/delete/"+data.id,
	    	   dataType : 'json',
	    	   type : 'post',
	    	   success : function(result){
	    		   if(result.code=="200"){
	    			   $(".layui-laypage-btn").click();  
	    		   }
	    		  layer.msg(result.msg);
	    	   }
	       });
	  }  
	});
	
});

