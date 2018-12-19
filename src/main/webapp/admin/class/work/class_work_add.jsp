<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp" %>
<title>添加班级作业</title>
</head>
<body class="childrenBody">
	<form class="layui-form  layui-form-pane" action="">
	   <div class="layui-form-item">
    		<div style="padding:6px 10px; font-weight: 500; letter-spacing: 1px; background: #e1e3e1;">
    		    日期：<span id="showDate"></span>
    		    <a class="layui-btn layui-btn-primary layui-btn-xs" href="${path}/admin/class/work/class_work_list/${class_id}" style="float: right;">返回</a>
   			</div>
	   </div>
	  <input type="hidden" name="seehope_class.id" value="${class_id}"/>
	  <input type="hidden" name="class_id" value="${class_id}"/>
	  <div class="layui-form-item">
	    <label class="layui-form-label">知识点</label>
	    <div class="layui-input-block">
	      <input type="text" name="point" lay-verify="required" placeholder="请输入知识点" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	      <textarea name="content" placeholder="作业内容" class="layui-textarea" lay-verify="required" style="min-height: 370px"></textarea>
	  </div>
	  <div class="layui-form-item">
	      <button class="layui-btn" lay-submit lay-filter="formWork">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	  </div>
	</form>
	 
	<script>
		var nowDate = new Date();
	    var myDate = nowDate.toLocaleDateString().replace(/\//g,'-');
	    /*页面加载结束显示当前日期*/
	    window.onload=function(){
	        dateShow(myDate);
	    }
	    function dateShow(date){
	        document.getElementById('showDate').innerHTML = date;
	    }
	    
		layui.use(['form'], function(){
		  var form = layui.form;
		  
		  
		  	//监听提交
			 form.on('submit(formWork)', function(data){
			    $.ajax({
		   			url: _path + '/admin/class/work/add',
		   			type: 'post',
		   			data: data.field,
		   			success: function(result){
		   				if(result.code == "200"){
		   					location.href = _path + '/admin/class/work/class_work_list/' + data.field.class_id;
		   				}
		   				parent.layer.msg(result.msg);
		   			}
		   		});
			    return false;
			  });
		});
	</script>
</body>
</html>