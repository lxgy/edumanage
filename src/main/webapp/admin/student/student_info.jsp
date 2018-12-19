<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>学生信息</title>
</head>
<body class="childrenBody">
	<a class="layui-btn layui-btn-primary layui-btn-sm" href="${path}/admin/stage/evaluation/stage_student_evaluation_detail/${student.id}">阶段信息</a>
	<a class="layui-btn layui-btn-primary layui-btn-sm" href="${path}/admin/question/paper/student_question_paper_list/${student.id}">答题记录</a>
	<table class="layui-table" lay-size="sm"> 
	  <tr>
	    <th>姓名</th>
	    <td>${student.name}</td>
	  </tr>
	  <tr>
	    <th>渠道</th>
	    <td>${student.channel}</td>
	  </tr>
	  <tr>
	    <th>手机</th>
	    <td>${student.phone}</td>
	  </tr>
	  <tr>
	    <th>微信</th>
	    <td>${student.wechat}</td>
	  </tr>
	  <tr>
	    <th>邮箱</th>
	    <td>${student.email}</td>
	  </tr>
	  <tr>
	    <th>QQ</th>
	    <td>${student.qq}</td>
	  </tr>
	  <tr>
	    <th>性别</th>
	    <td>${student.sex}</td>
	  </tr>
	  <tr>
	    <th>学校</th>
	    <td>${student.university}</td>
	  </tr>
	  <tr>
	    <th>专业</th>
	    <td>${student.major}</td>
	  </tr>
	  <tr>
	    <th>学历</th>
	    <td>${student.education}</td>
	  </tr>
	  <tr>
	    <th>毕业时间</th>
	    <td>${student.graduation_time}</td>
	  </tr>
	  <tr>
	    <th>身份证号码</th>
	    <td>${student.IDcard}</td>
	  </tr>
	  <tr>
	    <th>班级</th>
	    <td>${student.seehope_class.name}</td>
	  </tr>
	  <tr>
	    <th>签约人</th>
	    <td>${student.contract_teacher.name}</td>
	  </tr>
	  <tr>
	    <th>签约时间</th>
	    <td>${student.contract_time}</td>
	  </tr>
	  <tr>
	    <th>毕业去向</th>
	    <td>${student.graduation_where}</td>
	  </tr>
	  <tr>
	    <th>备注</th>
	    <td>${student.remark}</td>
	  </tr>
	</table>
	
</body>
</html>