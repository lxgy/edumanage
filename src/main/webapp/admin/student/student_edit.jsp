<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/student/student_edit.js"></script>
<title>编辑学生</title>
</head>
<body class="childrenBody">
	 
	<form class="layui-form" action="" style="width:90%;">
	  <input type="hidden" name="id" value="${student.id}">
	  <div class="layui-form-item">
	    <label class="layui-form-label">姓名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" autocomplete="off" 
	         value="${student.name}" placeholder="姓名" class="layui-input"/>
	    </div>
	  </div>
	  
	  <input type="hidden" name="username" value="${student.phone}"/>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">手机</label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="phone|checkphone" 
	        	laceholder="手机" autocomplete="off" value="${student.phone}" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">微信</label>
	    <div class="layui-input-block">
	      <input type="text" name="wechat" placeholder="微信" 
	      	autocomplete="off" class="layui-input" value="${student.wechat}"/>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" lay-verify="email" autocomplete="off" 
	        	placeholder="邮箱" value="${student.email}" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">QQ</label>
	      <div class="layui-input-block">
	        <input type="text" name="qq" autocomplete="off" 
	        	placeholder="QQ" value="${student.qq}" class="layui-input">
	      </div>
	  </div> 
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">学校</label>
	      <div class="layui-input-block">
	        <input type="text" name="university" autocomplete="off" 
	        	placeholder="学校" value="${student.university}" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">专业</label>
	      <div class="layui-input-block">
	        <input type="text" name="major" placeholder="专业" 
	        	autocomplete="off" class="layui-input" value="${student.major}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">学历</label>
	      <div class="layui-input-block">
	        <input type="text" name="education" placeholder="学历"
	        	autocomplete="off" class="layui-input" value="${student.education}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">毕业时间</label>
	      <div class="layui-input-block">
	        <input type="text" name="graduation_time" placeholder="毕业时间"
	        	autocomplete="off" class="layui-input" value="${student.graduation_time}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">身份证号码</label>
	      <div class="layui-input-block">
	        <input type="text" name="IDcard" placeholder="身份证"
	        	autocomplete="off" class="layui-input" value="${student.IDcard}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">渠道</label>
	      <div class="layui-input-block">
	        <input type="text" name="channel" placeholder="渠道"
	        	autocomplete="off" class="layui-input" value="${student.channel}">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="男" title="男" <c:if test="${student.sex=='男'}">checked="checked"</c:if>>
	      <input type="radio" name="sex" value="女" title="女" <c:if test="${student.sex=='女'}">checked="checked"</c:if>>
	    </div>
	  </div>
	  
	  <%-- <div class="layui-form-item">
	    <label class="layui-form-label">班级</label>
	    <div class="layui-input-block">
	      <select name="seehope_class.id">
	        <option value="">设置班级</option>
	        <c:forEach items="${classes}" var="c">
	        	<option value="${c.id}" <c:if test="${student.seehope_class.id==c.id}">selected="selected"</c:if>>${c.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div> --%>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">签约人</label>
	    <div class="layui-input-block">
	      <select name="contract_teacher.id">
	        <option value="">签约人</option>
	        
	        <c:forEach items="${teachers}" var="t">
	        	<option value="${t.id}" <c:if test="${student.contract_teacher.id==t.id}">selected="selected"</c:if>>${t.name}</option>
	        </c:forEach>
	        
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">签约时间</label>
	      <div class="layui-input-inline">
	        <input type="text" name="contract_time" id="date1"
	           value="${student.contract_time}" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	  </div> 
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">毕业去向</label>
	    <div class="layui-input-block">
	      <input type="text" name="graduation_where" placeholder="毕业去向" class="layui-input"
	      		value="${student.graduation_where}">
	    </div>
	  </div>
	
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">备注</label>
	    <div class="layui-input-block">
	      <textarea name="remark" placeholder="请输入内容" class="layui-textarea">${student.remark}</textarea>
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="updateStudent">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>
	
	<script type="text/javascript">
		var _phone = '${student.phone}';
	</script>

</body>
</html>