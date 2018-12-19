layui.use(['table','layer'], function(){
	var table = layui.table,
	layer = layui.layer;
	
	//初始化表格
  	table.render({ 
  		elem: '#table_paper'
  		,url: _path + '/edu/question/paper/find_student_paper'
  		,method: 'post' //如果无需自定义HTTP类型，可不加该参数
  		,cols: [[
  			{field:'name', title:'姓名', width:100,fixed: 'left',templet:'<div>{{d.student.name}}</div>'}
  	        ,{field:'type', title:'阶段', width:100,templet:'<div>{{typeName(d.type)}}</div>'}
  		    ,{field:'choice_num', title:'选择题数', width:90,}
  		    ,{field:'right_num', title:'答对题数', width:90,}
	        ,{field:'update_time', title:'时间'}
	        ,{fixed:'right', title:'操作', width:80, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,page : true
  	    ,limits: [10,20,30]
  	    ,limit: 10 //默认采用60
  	    ,height: 'full-140'
//  	    ,skin: 'nob'
	}); 
	
  //监听工具条
	table.on('tool(paper)', function(obj){ 
	  var data = obj.data; //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr; //获得当前行 tr 的DOM对象
	 
	  location.href = _path + "/edu/question/paper/question_paper_details/" + data.id
	});
  	
});

function typeName(type) {
	switch(type) {
		case 1:
			return "第一阶段";
		case 2:
			return "第二阶段";
		case 3:
			return "第三阶段";
		default:
			return "";
	}
}

