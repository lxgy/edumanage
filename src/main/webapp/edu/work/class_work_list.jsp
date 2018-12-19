<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>档案</title>
</head>
<body style="background: rgb(248, 248, 248)">
	<!-- 页头 -->
	<%@ include file="/edu/courses/header.jsp" %>
	<!-- 内容主体区域 -->
	<div class="layui-container">
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;min-height: 400px;">
			<table class="layui-hide" id="classWorkTable" lay-filter="classWorktable"></table>
		</blockquote>
	    
	</div>
	
	<!-- 页尾 -->
	<%@ include file="/edu/courses/footer.jsp" %>
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="details">查看</a>
	</script>
	
	<script type="text/javascript">
		layui.use(['layer','table'],function(){
	   		var layer = layui.layer,
	   		table = layui.table;
	   		
	   		$.ajax({
	   			url: _path + '/edu/work/findAll',
	   			type: 'get',
	   			success: function(result){
	   				if(result.code == "200"){
	   					table.render({ 
	   			   	  		elem: '#classWorkTable'
	   			   	  		,data: result.data
	   			   	  		,cols: [[
	   			   	  	        {field:'create_time', title:'时间', width:120,fixed:'left',templet:'<div>{{d.create_time.split(" ")[0]}}</div>'}
	   			   	  			,{field:'point', title:'知识点',width:200}
	   			   	  	        ,{field:'content', title:'内容'}
	   			   		        ,{fixed:'right', width:80, align:'center', toolbar: '#toolbar'}
	   			   	  	    ]]
	   			   	  	    ,page : true
	   			   	  	    ,limits: [10,20,30]
	   			   	  	    ,limit: 20 //默认采用60
	   			   	  	    ,height: 'full-60'
	   			   	  	    ,size: 'sm'
	   			   		}); 
	   				}else{
	   					layer.msg(result.msg);
	   				}
	   			}
	   		});
	   		
	   		table.on('tool(classWorktable)', function(obj){ 
	   		  var data = obj.data; 
	   		  var layEvent = obj.event; 
	   		 
	   		  //查看作业详情
	   		  if(layEvent === 'details'){ 
	   			location.href = _path + '/edu/work/class_work_details/' + data.id;
	   		  }
	   		});
	   	});
	</script>
</body>
</html>