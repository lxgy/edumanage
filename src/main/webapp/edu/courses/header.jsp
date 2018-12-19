<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
	  <div class="layui-container">
		<a href="${path}/edu/index"><div class="layui-logo">砺锋教务</div></a>
		<ul class="layui-nav layui-layout-left">
	      <li class="layui-nav-item"><a href="${path}/edu/index">档案记录</a></li>
	      <li class="layui-nav-item"><a href="${path}/edu/work/class_work_list">作业</a></li>
	      <li class="layui-nav-item"><a href="${path}/edu/question/question_list">题库</a></li>
	    </ul>
		
		<ul class="layui-nav layui-layout-right">
		  <li class="layui-nav-item">
		    <a href="javascript:;" onclick="feedback_add()">反馈</a>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:;">
		    	<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
		    	${edu_student.name}
		    </a>
		    <dl class="layui-nav-child">
				<dd><a href="${path}/edu/student_self_info" ><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
				<dd><a href="javascript:;" onclick="updatePwd()"><i class="iconfont icon-shezhi1"></i><cite>修改密码</cite></a></dd>
				<dd><a href="${path}/edu/question/paper/question_paper_list"><i class="fa fa-calendar-minus-o" aria-hidden="true"></i></i><cite>答题记录</cite></a></dd>
				<dd><a href="${path}/edu/logout"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
			</dl>
		  </li>
		</ul>
	  </div>
	</div>
</div>

<script type="text/javascript">
	var layer;
	layui.use(['layer','element'], function(){
		layer = layui.layer;
	});
	function updatePwd(){
		layer.open({
			  type: 2,
			  title: false,
			  closeBtn: 0,
			  shadeClose: true,
			  skin: 'layui-layer-rim',
			  shade: 0.6,
			  area: ['420px', '250px'],
			  fixed: false, //不固定
			  content: _path + "/edu/modify_pwd"
		 });
	}
	//反馈
	function feedback_add(){
		layer.open({
			  type: 2,
			  title: false,
			  closeBtn: 0,
			  shadeClose: true,
			  shade: 0.6,
			  skin: 'layui-layer-rim',
			  area: ['400px', '220px'],
			  fixed: false, //不固定
			  content: _path + "/edu/feedback/feedback_add"
		 });
	}
	
	
</script>