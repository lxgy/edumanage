layui.use(['table','layer'], function(){
	var table = layui.table,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
  
	//初始化表格
  	table.render({ 
  		elem: '#table_paper'
  		,url: _path + '/admin/question/paper/find_student_paper/' + sid
  	  	,method: 'post'
  		,cols: [[
  	        {field:'name', title:'姓名', width:100,fixed: 'left',templet:'<div>{{d.student.name}}</div>'}
  	        ,{field:'type', title:'阶段', width:100}
	        ,{field:'choice_num', title:'选择题数', width:90}
  		    ,{field:'right_num', title:'答对题数', width:90}
	        ,{field:'update_time', title:'时间'}
	        ,{fixed:'right', title:'操作', width:80, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,page : true
  		,response: {
   		   statusCode: 200 //成功的状态码，默认：0
   		}
	    ,limits: [10,20,30]
	    ,limit: 30 //默认采用60
	    ,height: 'full-100'
	    ,size: "sm"
	}); 
  	
  	
    //监听工具条
	table.on('tool(question_paper)', function(obj){
	  var data = obj.data;      //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr;          //获得当前行 tr 的DOM对象
	 
	  location.href = _path + '/admin/question/paper/student_paper_details/' + data.id
	});
  	
});



