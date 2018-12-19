$(function() {
	//粒子背景特效
	$('body').particleground({
		dotColor : '#5cbdaa',
		lineColor : '#5cbdaa'
	});
})

/**
 * 验证码更改
$(function() {
	$('#kaptchaImage').click(function() {
		$(this).attr('src', baselocation + '/captcha-image.jpg?' + Math.floor(Math.random() * 100));
	})
});
*/
var layer;
layui.use('layer', function(){
 	  layer = layui.layer
});

/**
 * 用户登录
 */
$(function() {
	$('.submit_btn').click(function() {
		var username = $("input[name='loginName']").val();
		if (username.length <= 0) {
			$("input[name='loginName']").attr("placeholder", "请输入帐号");
			return false;
		}
		var password = $("input[name='loginPwd']").val();
		if (password.length <= 0) {
			$("input[name='loginPwd']").attr("placeholder", "请输入密码");
			return false;
		}
		/*var registerCode = $("input[name='registerCode']").val();
		if (registerCode.length <= 0) {
			$("input[name='registerCode']").attr("placeholder", "输入验证码");
			return false;
		}*/
		
		var logindata = $("form").serialize();
		$.ajax({
			url : _path+"/admin/ajax_login",
			type : 'post',
			dataType : 'json',
			data : logindata,
			success : function(result) {
				console.info(result);
				if (result.code == "200") {
					window.location.href = _path+'/admin/index';
				}
				else if (result.msg == "帐号不存在") {
					$("input[name='loginName']").val("");
					$("input[name='loginName']").attr("placeholder", result.msg);
					$(".ver_btn").trigger("click");
					layer.msg(result.msg);
				} 
				else if (result.msg == "密码不正确") {
					$("input[name='loginPwd']").val("");
					$("input[name='loginPwd']").attr("placeholder", result.msg);
					$(".ver_btn").trigger("click");
					layer.msg(result.msg);
				}else{
					layer.msg(result.msg);
				} 
			}
		});
	})
});

/**
 * 回车登录实现
 */
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		$(".submit_btn").trigger("click");
	}
});