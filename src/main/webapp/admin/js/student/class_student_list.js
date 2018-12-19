layui.use(['table','layer'], function(){
	var table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
  
	$.ajax({
		url: _path + '/admin/student/list_class_student/' + cid,
		type: 'get',
		success: function(result){
			//初始化表格
		  	table.render({ 
		  		elem: '#table_student'
		  	  	,data: result.data
		  		,cols: [[
		  	        {field:'name', title:'姓名', width:80,fixed: 'left'}
		  		   // ,{field:'seehope_class', title:'班级', width:100, templet:'<div>{{d.seehope_class==undefined?"":d.seehope_class.name}}</div>'}
			        ,{field:'sex', title:'性别', width:50}
			        ,{field:'phone', title:'电话', width:110}
			        ,{field:'email', title:'邮件', width:120}
			        ,{field:'qq', title:'QQ', width:110,}
			        ,{field:'university', title:'学校', width:120,}
			        ,{field:'major', title:'专业', width:120,}
			        ,{field:'education', title:'学历', width:70,}
			        ,{field:'graduation_time', title:'毕业时间', width:80,}
			        ,{field:'contract_time', title:'签约时间', width:110,}
			        ,{field:'contract_teacher', title:'签约人',fixed: 'right',width:80,templet:'<div>{{d.contract_teacher==undefined?"":d.contract_teacher.name}}</div>'}
			        ,{field:'graduation_where', title:'毕业去向', width:100,}
			        ,{field:'remark', title:'备注', width:100,}
			        ,{fixed:'right', title:'操作', width:220, align:'center', toolbar: '#toolbar'}
		  	    ]]
		  		,page : true
			    ,limits: [10,20,30,40]
			    ,limit: 30 //默认采用60
			    ,height: 'full-60'
			    ,size: "sm"
			}); 
		}
	});
	
  	
  	
    //监听工具条
	table.on('tool(student)', function(obj){
	  var data = obj.data;      //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr;          //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'edit'){ //编辑
		  layui.layer.open({
			  title: "编辑学生信息",
			  type: 2,
			  area: ['700px', '450px'],
			  shadeClose: true,
			  maxmin: true,
			  content: _path + '/admin/student/student_edit/' + data.id
		  });
	  } else if(layEvent === 'class_log'){ // 班级情况
		  layer.open({
			  type: 2 
			  ,area: ['535px', '400px']
			  ,title: data.name
			  ,shadeClose: true
			  ,shade: 0.6 //遮罩透明度
			  ,content: _path + '/admin/student/student_class_log_list/'+data.id
		  }); 
	  } else if(layEvent === 'evaluation'){ 
		  parent.parent.layer.open({
			  type: 2 
			  ,area: ['900px', '630px']
			  ,title: data.name
			  ,shadeClose: true
			  ,shade: 0.6 //遮罩透明度
			  ,content: _path + '/admin/stage/evaluation/stage_student_evaluation_detail/'+data.id
		  }); 
	  } else if(layEvent === 'paper'){ 
		  parent.parent.layer.open({
			  type: 2 
			  ,title: data.name
			  ,shadeClose: true
			  ,type: 2
			  ,area: ['900px', '630px']
			  ,fixed: false
			  ,shade: 0.6 //遮罩透明度
			  ,content: _path + '/admin/question/paper/student_question_paper_list/'+data.id
		  }); 
	  }
	  
	});
  	
});


