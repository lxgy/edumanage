layui.use(['table','jquery','layer'], function(){
	var $ = layui.$,
    table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
//    layer = layui.layer;
  
	//初始化表格
  	table.render({ 
  		elem: '#table_class'
  		,url: _path + '/admin/class/list_self_class'
  		,method: 'post' //如果无需自定义HTTP类型，可不加该参数
  		,cols: [[
  	        {field:'name', title:'班级名', width:200}
	  	    ,{field:'open_time', title:'开班时间', width:120}
	  	    ,{field:'status', title:'状态', width:120, templet:'<div>{{getStatus(d.status)}}</div>'}
	  	    ,{field:'stage', title:'阶段', width:200}
	  	    ,{field:'charge_teacher', title:'班主任', width:120, templet:'<div>{{d.charge_teacher==undefined?"":d.charge_teacher.name}}</div>'}
	        ,{field:'teach_teacher', title:'教师', templet:'<div>{{d.teach_teacher==undefined?"":d.teach_teacher.name}}</div>'}
	        ,{fixed:'right', width:250, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,id: 'classTable'
  	    ,page : true
  	    ,limits: [10,20,30]
  	    ,limit: 10 //默认采用60
  	    //,loading: true
  	    ,height: 'full-100'
  	    ,size: 'sm'
	}); 
  	
	$('.layui-form-checkbox').on('click', function(){
	  table.reload('classTable', {
	    where: {
	    	isGraduation: $('#status').prop("checked")
	    }
	  });
	});
	
	//监听工具条
	table.on('tool(classes)', function(obj){ 
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  //查看班级学生
	  if(layEvent === 'detail_stu'){ 
//		  var index = layui.layer.open({
		  layer.open({
			  type: 2,
			  title: data.name+" 学生信息",
			  area: ['1000px', '600px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: _path + '/admin/student/class_student_list/' + data.id
		  });
//		  layui.layer.full(index);
	  }else if(layEvent === 'class_work'){   //查看班级作业信息
		  layer.open({
			  type: 2,
			  title: '班级作业',
			  area: ['1000px', '600px'],
			  fixed: false, //不固定
			  content: _path + '/admin/class/work/class_work_list/' + data.id
		  });
	  } else if(layEvent === 'add_work'){   //查看班级作业信息
		  layer.open({
			  type: 2,
			  title: '班级作业',
			  area: ['1000px', '600px'],
			  fixed: false, //不固定
			  content: _path + '/admin/class/work/class_work_add/' + data.id
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

