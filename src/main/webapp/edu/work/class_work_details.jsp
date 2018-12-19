<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>档案</title>
<style type="text/css">
	.layui-table-main{
		background: white;
	}
</style>
</head>
<body style="background: rgb(248, 248, 248)">
	<!-- 页头 -->
	<%@ include file="/edu/courses/header.jsp" %>
	<!-- 内容主体区域 -->
	<div class="layui-container">
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;">
			 日期：<span id="showDate"></span>
			 <a class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:history.back()" style="float: right;">返回</a>
		</blockquote>
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;min-height: 300px">
			 
			<form class="layui-form layui-form-pane" action="">
			  <div class="layui-form-item" pane>
			    <label class="layui-form-label">知识点</label>
			    <div class="layui-input-block">
			      <input type="text" readonly="readonly" value="${class_work.point}" class="layui-input">
			    </div>
			  </div>
			  <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">作业</label>
			    <div class="layui-input-block">
			      <textarea readonly="readonly" class="layui-textarea" style="min-height: 500px">${class_work.content}</textarea>
			    </div>
			  </div>
			</form>
		</blockquote>
  		
	</div>
	
	<!-- 页尾 -->
	<%@ include file="/edu/courses/footer.jsp" %>
	
	<script type="text/html" id="toolbar">
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="edit">查看</a>
	</script>
	
	<script type="text/javascript">
	 	window.onload=function(){
	    	var time = '${class_work.create_time}'.split(" ")[0];
	        dateShow(time);
	    }
	    function dateShow(date){
	        document.getElementById('showDate').innerHTML = date;
	    }
	
		
	</script>
</body>
</html>