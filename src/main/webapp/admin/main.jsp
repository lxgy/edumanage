<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/common.jsp" %>	
	<link rel="stylesheet" href="${path}/static/css/main.css" media="all" />
	<script type="text/javascript" src="${path}/static/js/main.js"></script>
	<title>首页</title>
</head>
<body class="childrenBody">
	<div class="panel_box row">
		
		<div class="panel col">
			<a href="javascript:;" data-code="01">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="fa fa-star-half-o" aria-hidden="true"></i>
				</div>
				<div class="panel_word">
					<span>我的班级</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-code="02">
				<div class="panel_icon">
					<i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i>
				</div>
				<div class="panel_word">
					<span>班级管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-code="03">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word">
					<span>教师管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-code="04">
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span>学生管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-code="05">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word">
					<span>档案管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col max_panel">
			<a href="javascript:;" data-code="06">
				<div class="panel_icon" style="background-color:#2F4056;">
					<i class="iconfont icon-text" data-icon="icon-text"></i>
				</div>
				<div class="panel_word">
					<span>问答管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
		<div class="panel col max_panel">
			<a href="javascript:;" data-code="07">
				<div class="panel_icon" style="background-color:#2F4056;">
					<i class="fa fa-paper-plane-o" aria-hidden="true"></i>
				</div>
				<div class="panel_word">
					<span>反馈管理</span>
					<cite></cite>
				</div>
			</a>
		</div>
	</div>
</body>
</html>