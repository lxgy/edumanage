var layer;
layui.use('layer', function(){
  layer = layui.layer;
});              
/**
 * 登陆页面按钮栏控制
 */
$(function() {
	$("#btn_login").click(function() {
		loginPassword = $("#password").val();
		loginName = $("input[name='loginName']").val();
		if(!(loginName.length > 0)) return;
		$.ajax({
			type : "POST",
			url : _path + '/edu/ajax_login',
			data : {
				"loginName" : loginName,
				"loginPwd" : loginPassword
			},
			dataType : "json",
			success : function(result) {
				if (result.code == "200") {
					//$(".message").hide();
					window.location.href = _path+'/edu/index';
				} else {
					//$(".message").show();
					//$(".message").children("label").text(result.msg);
					layer.msg(result.msg);
				}
			}
		})
	});
});
/**
 * 回车登录实现
 */
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		$("#btn_login").trigger("click");
	}
});

