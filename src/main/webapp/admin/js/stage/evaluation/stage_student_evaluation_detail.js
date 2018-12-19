var laytpl,layer,laypage;

layui.use(['laytpl', 'layer','form','laypage','element'],function() {
	laytpl = layui.laytpl;
	layer = layui.layer;
	laypage = layui.laypage;
	
	//加载首页
	var count = list_stage(1,5,null,null,1);
	//加载页码
	page(count);
	
	/**
	 * 按条件查询
	 */
	$('.search_btn').on('click', function() {
		var count = list_stage(1,5,$('#type').val());
		page(count);
	});

});

/**
 * 加载页码
 * @param count
 * @returns
 */
function page(count){
	laypage.render({
	  elem: 'page'
	  ,count: count
	  ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
	  ,jump: function(obj, first){
		  if(!first){ //首次不加载
			  list_stage(obj.curr,obj.limit,$('#keyText').val(),$('#type').val(),1);
		  }
	  }
	  ,limits: [5,10,20]
	  ,limit: 5 //默认采用60
	});
}

/**
 * 查询阶段信息
 * @param pageno   页码
 * @param pagesize 每页数据
 * @param key      阶段名关键字
 * @param type	   阶段类型 1 2 3
 * @param status   状态 1正常 0删除
 * @returns
 */
function list_stage(pageno,pagesize,type){
	var count;
	$.ajax({
		url: _path + "/admin/stage/evaluation/list_stage_student_evaluation/"+sid,
		type: 'post',
		dataType: 'json',
		data: {	'pageno':pageno,
				'pagesize':pagesize,
				'type': type,
				},
		async: false,
		success: function(result){
			if(result.code == 200){
				count = result.count;
				var getTpl = document.getElementById('stage_list').innerHTML
				laytpl(getTpl).render(result, function(html){
				  $('#table_stage tbody').html(html);
				});
			}
		}
	});
	return count;
}

/**
 * 老师阶段 总评价
 * @param item
 * @returns
 */
function teacher_evaluate(item){
	$.ajax({
		url: _path + "/admin/evaluation/get_evaluation/"+item.value,
		type: 'post',
		dataType: 'json',
		success: function(data){
			if(data.code == 200){
				var evaluation = document.getElementById("evaluation").innerHTML;
				laytpl(evaluation).render(data.data,function(html){
					layer.open({
						type: 1
						,content: html
						,area: ['400px', '300px']
						,btn: ['提交评价', '取消']
						,yes: function(index, layero){
							$.ajax({
								url: _path +  "/admin/evaluation/update_teacher_evaluation",
								type: 'post',
								dataType: 'json',
								data: layero.find('form').serialize(),
								async: false,
								success: function(result){
									if(result.code == '200'){
										 layer.close(index);
										 layer.msg("评价成功");
									 }
								 },
								 complete: function(){
								 }
							  });
						  }
					  });
				});
			}
		}
	});	 
}


/**
 * 老师阶段 评价
 * @param item
 * @returns
 */
function stage_evaluate(item){
	$.ajax({
		url: _path + "/admin/stage/evaluation/get_stage_evaluation/"+item.value,
		type: 'post',
		dataType: 'json',
		success: function(data){
			if(data.code == 200){
				var evaluation = document.getElementById("evaluation").innerHTML;
				laytpl(evaluation).render(data.data,function(html){
					layer.open({
						type: 1
						,content: html
						,area: ['400px', '300px']
						,btn: ['提交评价', '取消']
						,yes: function(index, layero){
							$.ajax({
								url: _path +  "/admin/stage/evaluation/update_stage_teacher_evaluation",
								type: 'post',
								dataType: 'json',
								data: layero.find('form').serialize(),
								async: false,
								success: function(result){
									if(result.code == '200'){
										 $(".layui-laypage-btn").click()
										 layer.close(index);
										 layer.msg("评价成功");
									 }
								 },
								 complete: function(){
								 }
							  });
						  }
					  });
				});
			}
		}
	});	 
}

/* 获得阶段名称 */
function getTypeName(type){
	switch (type) {
	case 1:
		return "第一阶段";
	case 2:
		return "第二阶段";
	case 3:
		return "第三阶段";
	default:
		return "";
	}
}