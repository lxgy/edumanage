var layer;
layui.use(['form', 'layer'], function() {
	var form = layui.form;
	layer = layui.layer;

	form.on('submit(*)', function(data){
		$.ajax({
			type: "post",
			url: _path + "/admin/question/option/save_option",
			data: data.field,
			dataType: 'json',
			success: function(result) {
				if(result.code == "200"){
					if(data.field.type != 3){
						var html = '<tr><td>'+data.field.summary+'</td><td>';
						if(result.data.is_answer!=undefined && result.data.is_answer==1){
							html = html + '<i class="fa fa-check" style="color:green;" >';
						}else{
							html = html + '<i class="fa fa-times" style="color:red;">';
						}
						html = html + '</td><td><button class="layui-btn layui-btn-xs layui-btn-primary" type="button" value="';
						html = html + result.data.id;
						html = html + '" onclick="updateOption(this)">修改</button><button class="layui-btn layui-btn-xs" type="button" value="';
						html = html + result.data.id;
						html = html + '" onclick="deleteOption(this)">删除</button></td></tr>';
						$('#option_new').before(html);
						form.render();
						layer.msg("添加成功");
						$("[type=reset]").click();
					}else if(data.field.type == 3){
						layer.msg("修改成功");
					}
				}else if(result.code == "400"){
					layer.msg("400")
				}
			}
		});
	    return false;
	});
});

function deleteOption(item) {
	layer.confirm("是否删除该选项", {
		title: '删除确认',
		icon: 0,
		btn: ['删除', '取消']
	}, function() {
		$.ajax({
			type: "post",
			url: _path + "/admin/question/option/delete_option/" + item.value,
			async: false,
			success: function(result) {
				if(result.code == "200"){
					$(item).parents("tr").remove();
				}
				layer.msg(result.msg);
			}
		});
	},function(){
	});
	
}

function updateOption(item) {
	var _summary = $(item).parents("tr").children("td:first");
	layer.prompt(
			{title: '修改选项', 
		  	formType: 2,
		  	value: _summary.text()}, 
		function(text, index){
	  		$.ajax({
				type: "post",
				url: _path + "/admin/question/option/update_option_summary",
	//			async: false,
				dataType: 'json',
				data:{
					id:item.value,
					summary:text},
				success: function(result) {
					if(result.code == "200"){
						_summary.text(text);
					}
					layer.msg(result.msg);
				}
			});
			layer.close(index);
		});
}
	  
function updateAnswer(item) {
	layer.confirm("是否修改该选项答案", {
		title: '修改确认',
		icon: 0,
		btn: ['修改', '取消']
	}, function() {
		var $item = $(item);
		var is_answer;
		if($item.attr('is-right') == ''){
			is_answer = 0;
		}else{
			is_answer = 1;
		}
		
		$.ajax({
			type: "post",
			url: _path + "/admin/question/option/update_option_is_answer",
//			async: false,
			data:{
				id:$item.attr('option-id'),
				is_answer: is_answer
			},
			success: function(result) {
				if(result.code == "200"){
					if(is_answer == 0){
						$item.css('color','red');
						$item.removeClass('fa-check');
						$item.addClass('fa-times');
						$item.removeAttr('is-right');
					}else if(is_answer == 1){
						$item.css('color','green');
						$item.removeClass('fa-times');
						$item.addClass('fa-check');
						$item.attr('is-right','');
					}
				}
				layer.msg(result.msg);
			}
		});
	},function(){
	});  
}
