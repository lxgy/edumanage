<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>学生阶段评价</title>
	<%@ include file="/common/common.jsp"%>
	<script src="${path }/admin/js/stage/evaluation/stage_student_evaluation_detail.js" type="text/javascript"></script>
</head>
<body class="childrenBody">
	<div class="layui-tab layui-tab-brief">
		
	  <ul class="layui-tab-title">
	    <li class="layui-this">阶段评价</li>
	    <li>阶段总评</li>
	  </ul>
	  <div class="layui-tab-content">
	  	  <div class="layui-tab-item layui-show">
			<div class="layui-form">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<select id="type">
							<option value="">所属阶段</option>
							<option value="1">第一阶段</option>
							<option value="2">第二阶段</option>
							<option value="3">第三阶段</option>
						</select>
					</div>
				</div>
					
				<div class="layui-inline">
					<a class="layui-btn search_btn stage_search" >查询</a>
				</div>
				
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" href="${path}/admin/stage/evaluation/download_student_stage/${sid}">导出</a>
				</div>
			</div>
			<table class="layui-table" id="table_stage" lay-size="sm">
				<thead>
					<tr>
						<th width="50"></th>
						<th align="center" width="150">阶段</th>
						<th align="center">内容</th>
						<th align="center" width="50">老师得分</th>
						<th align="center" width="50">自我得分</th>
						<th align="center" >自我评价</th>
						<th align="center" >老师评价</th>
						<th width="35"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="page"></div>
		  </div>
		  
      	  <div class="layui-tab-item">
			 <table class="layui-table">
			  	<thead>
			   	 	<tr>
			      		<th width="100"></th>
			      		<th >总评</th>
			      		<th >老师评价</th>
			      		<th width="40"></th>
			    	</tr> 
			  </thead>
			  <tbody>
			  		<c:forEach items="${evaluations}" var="eva">
			  			<tr>
					      <td><c:if test="${eva.type==1}">第一阶段</c:if>
					      	  <c:if test="${eva.type==2}">第二阶段</c:if>
					      	  <c:if test="${eva.type==3}">第三阶段</c:if>
					      </td>
					      <td height="120px">${eva.content}</td>
					      <td >${eva.teacher_evaluation}</td>
					      <td ><button class="layui-btn layui-btn-xs" value="${eva.id}" onclick="teacher_evaluate(this)">评价</button></td>
					    </tr>
			  		</c:forEach>
			  </tbody>
			</table>
		  </div>
	  </div>
	</div>      
	
	<script type="text/javascript">
		var sid = '${sid}';
	</script>
	
	<script type="text/html" id="stage_list">

  	{{#  layui.each(d.data, function(index, item){ }}
    	<tr >
	  		<td rowspan={{item.student_evaluation.length+1}}>{{ getTypeName(item.type)}}</td>
      		<td rowspan={{item.student_evaluation.length+1}}>{{ item.name }}</td>
      		<td colspan="5" height="0" style="padding: 0"></td>
    	</tr>
			{{#  layui.each(item.student_evaluation, function(index, item2){ }}
				<tr>
      				<td>{{ item2.stage_content.content }}</td>
					<td>{{ item2.teacher_score||"" }}</td>
					<td>{{ item2.student_score||"" }}</td>
					<td>{{ item2.evaluation||"" }}</td>
					<td>{{ item2.teacher_evaluation||"" }}</td>
					<td><button  class="layui-btn layui-btn-xs" value="{{item2.id}}" onclick="stage_evaluate(this)">评价</td>
				</tr>
			{{#  }); }}
  	{{#  }); }}
  	{{#  if( d.data == undefined || d.data.length === 0){ }}
    	无数据
  	{{#  } }} 
		
	</script>
	
	<script type="text/html" id="evaluation">
		<form class="layui-form" style="margin: 20px">
		  <input type="hidden" name="id" value="{{d.id}}">
		  <div class="layui-form-item layui-form-text">
		      <textarea name="teacher_evaluation" placeholder="老师评价" lay-verify="required" class="layui-textarea">{{d.evaluation||""}}</textarea>
		  </div>
		</form>
	</script>
</body>

</html>