<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>班级作业</title>
</head>
<body class="childrenBody">
	<button class="layui-btn layui-btn-xs" id="add_btn">添加作业</button>
	<table class="layui-hide" id="classWorkTable" lay-filter="classWorktable"></table>
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="edit">查看</a>
	</script>
	
	<script type="text/javascript">
		var class_id = '${class_id}';
	   	layui.use(['layer','table'],function(){
	   		var layer = layui.layer,
	   		table = layui.table;
	   		
			table.render({ 
	   	  		elem: '#classWorkTable'
	   	  		,method: 'get'
	   	  		,url: _path + '/admin/class/work/list/' + class_id
	   	  		,cols: [[
	   	  	        {field:'create_time', title:'时间', width:120,fixed:'left',templet:'<div>{{d.create_time.split(" ")[0]}}</div>'}
	   	  			,{field:'point', title:'知识点',width:200, event:'edit'}
	   	  	        ,{field:'content', title:'内容'}
	   		        ,{fixed:'right', width:80, align:'center', toolbar: '#toolbar'}
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
	   	  	    ,limit: 20 //默认采用60
	   	  	    ,height: 'full-60'
	   	  	    ,size: 'sm'
	   		}); 
 
	   		
	   		$('#add_btn').click(function(){
	   			location.href = _path + '/admin/class/work/class_work_add/' + class_id;
	   		});
	   		
			table.on('tool(classWorktable)', function(obj){ 
			  var data = obj.data; 
			  var layEvent = obj.event; 
			 
			  if(layEvent === 'edit'){ 
				location.href = _path + '/admin/class/work/class_work_edit/' + data.id;
			  }
			});
			
	   	});
	</script>
</body>
</html>