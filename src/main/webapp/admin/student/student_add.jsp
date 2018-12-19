<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript" src="${path}/admin/js/student/student_add.js"></script>

<title>添加学生</title>
</head>
<body class="childrenBody">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>添加学生</legend>
	</fieldset>
	 
	<form class="layui-form" action="" style="width:90%;">
	  <div class="layui-form-item">
	    <label class="layui-form-label">姓名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" lay-verify="required" 
	      	autocomplete="off" placeholder="姓名" class="layui-input"/>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">手机</label>
	      <div class="layui-input-block">
	        <input type="tel" name="phone" lay-verify="phone|checkphone" 
	        	placeholder="手机" autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">微信</label>
	    <div class="layui-input-block">
	      <input type="text" name="wechat" placeholder="微信" 
	      	autocomplete="off" class="layui-input"/>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">邮箱</label>
	      <div class="layui-input-block">
	        <input type="text" name="email" placeholder="邮箱"
	        	lay-verify="email" autocomplete="off" class="layui-input">
	      </div>
	  </div>
	   <div class="layui-form-item">
	      <label class="layui-form-label">QQ</label>
	      <div class="layui-input-block">
	        <input type="text" name="qq" placeholder="QQ" 
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div> 
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">学校</label>
	      <div class="layui-input-block">
	        <input type="text" name="university" placeholder="学校" 
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">专业</label>
	      <div class="layui-input-block">
	        <input type="text" name="major" placeholder="专业" 
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">学历</label>
	      <div class="layui-input-block">
	        <input type="text" name="education" placeholder="学历"
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">毕业时间</label>
	      <div class="layui-input-block">
	        <input type="text" name="graduation_time" placeholder="毕业时间"
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">身份证号码</label>
	      <div class="layui-input-block">
	        <input type="text" name="IDcard" placeholder="身份证"
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">渠道</label>
	      <div class="layui-input-block">
	        <input type="text" name="channel" placeholder="渠道"
	        	autocomplete="off" class="layui-input">
	      </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
	      <input type="radio" name="sex" value="男" title="男" checked="checked">
	      <input type="radio" name="user.sex" value="女" title="女">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">班级</label>
	    <div class="layui-input-block">
	      <select name="seehope_class.id" >
	        <option value="">设置班级</option>
	        <c:forEach items="${classes}" var="c">
	        	<option value="${c.id}">${c.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">签约人</label>
	    <div class="layui-input-block">
	      <select name="contract_teacher.id" >
	        <option value="">签约人</option>
	        <c:forEach items="${teachers}" var="t">
	        	<option value="${t.id}">${t.name}</option>
	        </c:forEach>
	      </select>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	      <label class="layui-form-label">签约时间</label>
	      <div class="layui-input-block">
	        <input type="text" name="contract_time" id="date1"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	  </div> 
	  
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">备注</label>
	    <div class="layui-input-block">
	      <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
	    </div>
	  </div>
	
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="addStudent">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
	  </div>
	</form>


</body>
</html>