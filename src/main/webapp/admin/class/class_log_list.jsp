<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>班级日志</title>
</head>
<body class="childrenBody" style="padding-left: 20px;">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
	  <legend>班级时间线</legend>
	</fieldset> 
	<ul class="layui-timeline">
		<c:forEach items="${logs}" var="log">
			<li class="layui-timeline-item">
			    <i class="layui-icon layui-timeline-axis"></i>
			    <div class="layui-timeline-content layui-text">
			      <div class="layui-timeline-title">${log.update_time}${log.remark}</div>
			    </div>
			</li>
		</c:forEach>
	</ul>
</body>
</html>