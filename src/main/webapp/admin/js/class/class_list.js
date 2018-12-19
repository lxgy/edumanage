layui.use(['table','layer'], function(){
	var table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
//    layer = layui.layer;
  
	//初始化表格
  	table.render({ 
  		elem: '#table_class'
  		,url: _path + '/admin/class/list'
  		,method: 'post' //如果无需自定义HTTP类型，可不加该参数
  		,cols: [[
  	        {field:'name', title:'班级名', width:200,fixed:'left'}
	  	    ,{field:'open_time', title:'开班时间', width:120}
	  	    ,{field:'status', title:'状态', width:100, templet:'<div>{{getStatus(d.status)}}</div>'}
	  	    ,{field:'stage', title:'阶段'}
	        ,{field:'charge_teacher', title:'班主任', width:100, templet:'<div>{{d.charge_teacher==undefined?"":d.charge_teacher.name}}</div>'}
	        ,{field:'teach_teacher', title:'教师',width:100, templet:'<div>{{d.teach_teacher==undefined?"":d.teach_teacher.name}}</div>'}
	        ,{fixed:'right', width:280, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,id: 'classReload'
  	    ,page : true
  	    ,limits: [10,20,30]
  	    ,limit: 10 //默认采用60
  	    ,height: 'full-100'
  	    ,size: 'sm'
	}); 
  	
	$('.search_btn').on('click', function(){
	  table.reload('classReload', {
	    where: {
	      key: $('#keyText').val(),
          status: $('#status').val()
	    }
	  });
	});
	
	//添加
	$('.add_btn').click(function(){
		var index = layui.layer.open({
			title: '新增班级',
			type : 2,
			area : [ '650px', '450px' ],
			shadeClose : true,
			maxmin : true,
			content : _path+"/admin/class/class_add"
		});
	});
	
	//监听工具条
	table.on('tool(classes)', function(obj){ 
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'detail_stu'){ //查看
		  //var index = layui.layer.open({
		  layer.open({
			  type: 2
			  ,title: data.name+" 学生信息"
			  ,area: ['1000px', '520px']
			  ,shade: 0.6 
		      ,shadeClose :true
			  ,maxmin: true
			  ,content: _path + '/admin/student/class_student_list/' + data.id
		  });
		  //layui.layer.full(index);
	  } else if(layEvent === 'edit'){ //编辑
		  layui.layer.open({
			  type: 2
			  ,title: "编辑班级信息"
			  ,area: ['650px', '450px']
			  ,shade: 0.6 
		      ,shadeClose :true
			  ,maxmin: true
			  ,content: _path + '/admin/class/class_edit/' + data.id
		  });
	  }  else if(layEvent === 'log'){    //日志
		  layer.open({
			  title: data.name
			  ,type: 2
			  ,area: ['500px', '600px']
		  	  ,fixed: false
			  ,shade: 0.6 
		      ,shadeClose :true
			  ,content: _path + '/admin/class/class_log_list/' + data.id
		  });
	   }else if(layEvent === 'class_work'){   //查看班级作业信息
		  layer.open({
			  type: 2
			  ,title: '班级作业'
			  ,area: ['1000px', '600px']
			  ,fixed: false //不固定
			  ,shade: 0.6 
		      ,shadeClose :true
			  ,content: _path + '/admin/class/work/class_work_list/' + data.id
		  });
	  }else if(layEvent === 'del'){
		  layer.confirm('确定删除该班级',{
		  	title:'删除确认',
		  	icon:0,
		  	btn:['删除','取消']
		  },function(){
		  	$.ajax({
		  		type:"post",
		  		url: _path + "/admin/class/delete_class/" + data.id,
		  		async:true,
		  		success:function(result){
		  			$(".layui-laypage-btn").click();
		  			if(result.code == "200"){
		  				parent.layer.msg("删除成功");
		  			}else{
		  				parent.layer.msg("删除失败");
		  			}
		  		}
		  	});
		  },function(){
		  	layer.msg("取消删除操作");
		  });
	  }
	});
	
});
 

function getStatus(status){
	switch (status) {
	case 1:
		return "预开班";
	case 2:
		return "已开班";
	case 3:
		return "已毕业";
	default:
		return "";
	}
}

