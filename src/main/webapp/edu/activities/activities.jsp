<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>砺锋科技报名活动</title>
		<%@ include file="/common/common.jsp"%>
		<link rel="stylesheet" type="text/css" href="${path}/static/common/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${path}/static/css/layer.css" />

		<style type="text/css">
			body {
				font-family: "微软雅黑";
			}
			
			.form-group {
				text-align: left;
			}
			
			.error {
				color: red;
			}
		</style>
	</head>

	<body>
		<!--头部导航开始-->
		<div class="navbar navbar-fixed-top navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header nav-title ">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">  
                        <span class="sr-only">Toggle navigation</span>  
                        <span class="icon-bar"></span>  
                        <span class="icon-bar"></span>  
                        <span class="icon-bar"></span>  
                   </button>
					<span class="navbar-brand">愿世界聚焦于您</span>
				</div>
				<div class="collapse navbar-collapse navbar-right is-collapse">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="https://www.seehope.net/" target="_blank" class="a-sty" style="color: cadetblue;">砺锋科技</a>
						</li>
						<li>
							<a href="http://www.codeseeding.com/" target="_blank" class="a-sty" style="color: cadetblue;">码云社</a>
						</li>
						<li>
							<a href="http://www.u-cto.com/" target="_blank" class="a-sty" style="color: cadetblue;">众创空间</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--头部导航结束-->

		<!--注册form表单开始-->
		<div class="jumbotron" style="text-align: center;width: 100%;">
			<div class="container" style="width: 500px;">
				<div class="">
					<form action="" id="registF" method="post" style="margin-top: 100px;">
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="username">姓名:</label>
									<input type="text" class="form-control" id="name" name="name" placeholder="请输入您的真实姓名..." value="" required="required" rangelength=[2,10]>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="phone">手机:</label>
									<input type="text" class="form-control" id="phone" name="phone" placeholder="请输入您的手机号码..." value="" datatype="m" required="required" number=true>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="qq">QQ:</label>
									<input type="text" class="form-control" id="qq_num" name="qq_num" placeholder="请输入您的qq号码..." value="" required="required" number=true>
								</div>
							</div>
						</div>
						<!--<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="email">Email:</label>
									<input type="text" class="form-control" id="email" name="email" placeholder="请输入您的邮箱..." value="" required="required" email=true>
								</div>
							</div>
						</div>-->
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="remarks">学校:</label>
									<input type="text" class="form-control" name="school" id="school" value="" placeholder="请输入您在读学校名称..." required="required" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="remarks">专业:</label>
									<input type="text" class="form-control" name="email" id="email" value="" placeholder="请输入您的在读专业..." required="required" />
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<label for="remarks">毕业时间:</label>
									<input type="date" class="form-control" name="end_time" id="end_time" value="" placeholder="请输入您的毕业时间..." required="required" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-10">
								<div class="form-group">
									<input type="submit" class="btn btn-primary form-control" name="" id="regist" value="报名" />
									<!--<button class="btn btn-block btn-primary" id="regist">提交</button>-->
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!--注册form表单结束-->
		<footer class="footer col-sm-12" style="height: 50px;text-align: center;padding-top:15px;">
			<span class="col-sm-12" style="font-size: 16px;">由广州砺锋信息科技有限公司提供技术支持</span>
		</footer>
	</body>

	<script src="${path}/static/common/bootstrap/js/bootstrap.min.js " type="text/javascript " charset="utf-8 "></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

	<script type="text/javascript">
		layui.use(['layer'], function() {
			var layer = layui.layer;

			$("#registF").validate({
				onkeyup: true, // 是否在敲击键盘时验证  
				/* onsubmit: true, // 是否在提交是验证  
				onfocusout: true, // 是否在获取焦点时验证 */
				messages: {
					required: "这是必填字段",
					email: "请输入有效的电子邮件地址",
					number: "请输入有效的数字",
					rangelength: "请输入长度在 2 到 10 之间的字符串"
				},
				submitHandler: function(form) { //通过之后回调  
					var data = $("#registF").serialize();
					$.ajax({
						type: "post",
						url: _path + "/activities/addRegistPeople",
						async: true,
						data: data,
						dataType: 'json',
						success: function(result) {
							layer.alert("恭喜您报名成功", {
								icon: 1,
								closeBtn: 0
							}, function() {
								location.href = "https://www.seehope.net/";
							});
						}
					});
				},
				invalidHandler: function(form, validator) { //不通过回调  
					return false;
				}

			});
		});
	</script>

</html>