<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/common.jsp" %>
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<link rel="stylesheet" href="${path}/static/css/main.css" media="all" />
	<script type="text/javascript" src="${path}/static/js/leftNav.js"></script>
	<script type="text/javascript" src="${path}/static/js/index.js"></script>
	<title>教务系统</title>
	<style type="text/css">
		cite{
			letter-spacing:2px;
		}
		.layui-layout-admin .layui-body {
		    top: 60px;
		    bottom: 0;
		}
	</style>
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main">
				<a href="#" class="logo">教务系统</a>
			    
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
			    	<li class="layui-nav-item" mobile>
			    		<a href="javascript:;" data-url="${path}/user/user_teacher_info"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>设置</cite></a>
			    	</li>
			    	<li class="layui-nav-item" mobile>
			    		<a href="${path}/admin/logout"><i class="iconfont icon-loginout"></i> 退出</a>
			    	</li>
					<li class="layui-nav-item" pc>
						<a href="javascript:;">
							<img src="${path}/static/images/photo.jpg" class="layui-circle" width="35" height="35">
							<cite>${admin_teacher.name}</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" data-url="${path}/admin/teacher_self_info"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
							<dd><a href="javascript:;" onclick="updatePwd()"><i class="iconfont icon-shezhi1"></i><cite>修改密码</cite></a></dd>
							<dd><a href="${path}/admin/logout"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像" ><img src="${path}/static/images/photo.jpg"></a>
				<p>你好！<span class="userName">${admin_teacher.name}</span></p>
			</div>
			<div class="navBar layui-side-scroll"></div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab marg0" lay-filter="bodyTab">
				<ul class="layui-tab-title top_tab">
					<li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe src="${path}/admin/main"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<!-- <div class="layui-footer footer">
			<p>copyright @2017 教务系统  <a href="http://www.seehope.net/" target="_blank">砺锋科技</a>
		</div> -->
	</div>

	<!-- 移动导航 -->
	<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
	<div class="site-mobile-shade"></div>
	
	<script type="text/javascript">
		function updatePwd(){
			layer.open({
				  type: 2,
				  title: "修改密码",
				  area: ['420px', '280px'],
				  shadeClose: true,
				  content: _path + "/admin/modify_pwd"
			 });
		}
	</script>
</body>
</html>