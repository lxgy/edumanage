<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/student/student_upload.js"></script>
<title>导入学生数据</title>
</head>
<body>
	<div style="margin: 30px auto; text-align: center;">
		<div class="layui-upload-drag" id="upload_student">
		  <i class="layui-icon"></i>
		  <p>点击上传，或将文件拖拽到此处</p>
		</div><br>
		
		<button id="stu_info" class="layui-btn" style="margin-top: 30px">导入信息</button>
		<a class="layui-btn layui-btn-xs layui-btn-primary" style="margin-top: 30px"
			href="${path}/admin/student/download_stu_excel">下载模板</a>
	</div>
</body>
</html>