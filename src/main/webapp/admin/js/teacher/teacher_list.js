layui.use(['table','layer'], function(){
    var table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
    /*layer = layui.layer;*/
    
	//初始化表格
  	table.render({ 
  		elem: '#table_teacher'
  		,url: _path + '/admin/teacher/list'
  		,method: 'post'
  		,cols: [[
  		    {field:'name', title:'姓名', width:100,fixed: 'left'}
  		    ,{field:'type', title:'类型', width:80,templet:'<div>{{ d.type==1?"讲师":"教务"}}</div>'}
	        ,{field:'phone', title:'电话', width:130}
	        ,{field:'wechat', title:'微信', width:130}
	        ,{field:'email', title:'邮件', width:220}
	        ,{field:'qq', title:'QQ', width:130}
	        ,{field:'sex', title:'性别', }
  	        ,{fixed:'right',title:'操作', width:150, align:'center',fixed:'right',toolbar:'#toolbar'}
        ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,id: 'teacherReload'
  	    ,page : true
  	    ,limits: [10,20,30]
  	    ,limit: 10 //默认采用60
  	    ,height: 'full-100'
  	    ,size: 'sm'
	}); 
  	
	/**
	 * 表格重载
	 */
	function tableReload() {
		table.reload('teacherReload', {
		    where: {
		      key: $('#keyText').val(),
		      type: $('#type').val(),
		      status: $('#status').prop("checked")
		    }
		  });
	}
	$('.search_btn').on('click', function(){
		tableReload();
	});
	$('.layui-form-checkbox').click( function(){
		tableReload();
	});
	
	
	/**
	 * 添加页面
	 */
	$('.add_btn').click(function(){
		var index = layui.layer.open({
			title: '新增教师',
			type : 2,
			area : [ '650px', '500px' ],
			shadeClose: true,
			maxmin : true,
			content : _path+"/admin/teacher/teacher_add"
		});
	});
	
	/**
	 * 监听工具条
	 */
	table.on('tool(teacher)', function(obj){
	  var data = obj.data;      //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr;          //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'disable'){ //停用
	      layer.confirm('真的停用么', function(index){
	        $.ajax({
	    	   url : _path + "/admin/teacher/update_status",
	    	   dataType : 'json',
	    	   type : 'post',
	    	   data : { 'tid' : data.id,
	    		        'status' : 0},
	    	   success : function(result){
	    		  if(result.code == '200'){
	    		    $(".layui-laypage-btn").click();
	    		    layer.msg('已停用');
	    		  }
	    	   },
	    	   complete : function(){
	    		   layer.close(index);
			   }
	        });
	    });
	  }else if(layEvent === 'enable'){ //启用
		  layer.confirm('真的启用么', function(index){
		    $.ajax({
	    	   url : _path + "/admin/teacher/update_status",
	    	   dataType : 'json',
	    	   type : 'post',
	    	   data : { tid : data.id,
	    		        status : 1},
	    	   success : function(result){
	    		  if(result.code == '200'){
	    		    $(".layui-laypage-btn").click(); 
	    		    layer.msg('已启用');
	    		  }
	    	   },
	    	   complete : function(){
	    		   layer.close(index);
			   }
	        }); 
		  });
	  }else if(layEvent === 'edit'){ //编辑
		  layui.layer.open({
			  title: "修改信息",
			  type: 2,
			  area: ['650px', '500px'],
			  shadeClose: true,
			  maxmin: true,
			  content: _path + '/admin/teacher/teacher_edit/' + data.id
		  });
	  }
	});
	
});

