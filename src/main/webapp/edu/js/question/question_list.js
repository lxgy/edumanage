var laytpl,layer,form;
var num;

layui.use(['laytpl','layer','form'], function(){
	layer = layui.layer;
	laytpl = layui.laytpl;
	form = layui.form;
	
	$('.search_question_btn').click(function(){
		var stage_type= $('[name=stage_type]').val();
		
		num = list_question(stage_type);
		
	});
	
});

/**
 * 试卷试题展示
 * @param type      题目类型 1单选 2多选 3问答
 * @param stage_type    阶段类型 1 2 3
 * @returns
 */
function list_question(stage_type){
	var num;
	$.ajax({
		url: _path + "/edu/question/paper/create_paper",
		type: 'post',
		dataType: 'json',
		data: {'stage_type':stage_type,
				},
		async: false,
		success: function(result){
			if(result.code == "200"){
				num = result.data.question_students.length;
				var getTpl = document.getElementById("question_list").innerHTML;
				view = $("#content");
				laytpl(getTpl).render(result.data, function(html){
					view.html(html);
				});
				form.render();
			}
		}
	});
	return num;
}

/**
 * 提交试卷
 * @param item
 * @returns
 */
function submit_paper(item){
	
	layer.confirm("确认已做完，要提交试卷嘛", {
		title: '提交试题',
		icon: 0,
		btn: ['提交', '取消']
	}, function() {
		
		var _data = new Array();
		for(var i=0; i<num ; i++){
			var question = $('#question_'+i);
			var question_type = question.attr('question-type');
			var question_id = question.val();
			//选择题
			if( question_type != 3){
				var answer_option = '';
				var options = $("[name='answer_"+i+"']");
				options.each(function(){
					var option_value = $(this).val();
					if($(this).is(':checked')){
						if(answer_option != "") answer_option = answer_option + ",";
						answer_option = answer_option + option_value;
					}
				});
				if(answer_option == ''){
					layer.msg("请做完再提交");
					return ;
				}
				
				_data[i] = {"question" : {'id':question_id, "type":question_type}, "answer":answer_option};
				
			}else{
				var answer = $("[name='answer_"+i+"']").val();
				if(answer == ''){
					layer.msg("请做完再提交");
					return ;
				}
				_data[i] = {"question" : {'id':question_id, "type":question_type}, "answer":answer};
			}
		}
		
//		alert(JSON.stringify(_data));
		
		//提交答案
		var paper_id;
		$.ajax({
			url: _path + "/edu/question/paper/submit_paper_type",
			type: 'post',
			dataType: 'json',
			data: {'type': $("[name='type']").val(),
				   'stage.id': $("[name='stage.id']").val()
			},
			async: false,
			success: function(result){
				if(result.code == "200"){
					paper_id=result.data.id;
				}else{
					layer.msg(result.msg);
				}
			}
		});
		
		
		//提交答案
		$.ajax({
			url: _path + "/edu/question/paper/submit_paper/" + paper_id,
			type: 'post',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(_data),
			success: function(result){
				if(result.code == "200"){
					location.href = _path + "/edu/question/paper/question_paper_details/"+ result.data.id;
				}else{
					layer.msg(result.msg);
				}
			}
		});
		
	}, function() {
	});
	
	
}
