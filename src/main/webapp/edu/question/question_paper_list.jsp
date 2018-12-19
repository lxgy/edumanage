<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/edu/js/question/question_paper_list.js"></script>
<title>答题记录</title>
<style type="text/css">
	.layui-table-view{
	 	background: white;
	}
	.layui-table-header table thead tr{
		background-color: white;
	}
	.layui-table-header table thead tr div span{
		font-weight: 600;
	}
</style>
</head>
<body style="background: rgb(248, 248, 248)">
	<!-- 页头 -->
	<%@ include file="/edu/courses/header.jsp" %>
	<!-- 内容主体区域 -->
	<div class="layui-container" style="padding: 10px 20px;">
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px;background: white;">
			<div id="content" >
				<table id="table_paper" lay-filter="paper"></table>
			</div>
		</blockquote>
	</div>
	
	<!-- 页尾 -->
	<%@ include file="/edu/courses/footer.jsp" %>
	
	<script type="text/html" id="toolbar">
  		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="details">详情</a>
	</script>
</body>
</html>