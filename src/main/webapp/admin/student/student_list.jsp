<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/student/student_list.js"></script>
<title>学生管理</title>

<style type="text/css">
  .disable{
  	background-color: green;
  }
</style>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm layui-form">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" id="keyText" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		</div>
		<div class="layui-inline">
			<a class="layui-btn search_btn">查询</a>
			<a class="layui-btn layui-btn-normal add_btn">添加学生</a>
			<a class="layui-btn layui-btn-warm f-student" id="f-stu">查看已毕业学生</a>
			<input type="checkbox" id="status" value="1" title="显示停用">
			
			<a class="layui-btn layui-btn-sm layui-btn-primary upload_btn">
				<i class="layui-icon" style="color:green; font-weight: 800">&#xe60a;</i>导入
			</a>
		</div>
	</blockquote>
	
	<div class="layui-inline">
		<div class="layui-input-inline">
			<div id="class_select"></div>
		</div>
	</div>
	<div class="layui-inline">
		<a class="layui-btn layui-btn-sm layui-btn-primary change_btn">批量换班</a>
	</div>
	
	<table class="layui-hide" id="table_student" lay-filter="student"></table> 
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="survey_detail">查看问卷</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="class_log">日志</a>
	</script>
	
	<script type="text/html" id="class_option">
		<div class="layui-form">
			<div class="layui-inline">
			    <div class="layui-input-inline">
					<select id="cid" >
				        <option value="">选择班级</option>
				        {{#  layui.each(d.data, function(index, item){ }}
				        	<option value="{{item.id}}">{{item.name}}</option>
				        {{#  }); }}
				     </select>
		     	</div>
			</div>
		</div>
	 </script>
</body>
</html>