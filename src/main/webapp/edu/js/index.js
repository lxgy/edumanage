var table, laytpl, layer, laypage;

layui.use(['form', 'laytpl', 'table', 'element', 'laypage'], function() {
	var form = layui.form,
		element = layui.element;

	layer = layui.layer;
	laytpl = layui.laytpl;
	table = layui.table;
	laypage = layui.laypage;

	/*判断用户是否做了问卷调查.*/
	/*$.ajax({
		type: "post",
		url: _path + "/edu/survey/is_survey",
		async: false,
		data: {
			id: _id
		},
		success: function(result) {
			if(result.code == 200) {
				element.on('tab(stage)', function(data) {
					//加载首页
					var count = list_stage(1, 1, this.value, data.index);
					//加载页码
					page(count, this.value, data.index);

					$("legend").text(this.innerHTML + "自我评价");

					//加载阶段总结
					$.ajax({
						url: _path + "/edu/evaluation/get_evaluation",
						type: 'post',
						dataType: 'json',
						data: {
							'type': this.value
						},
						success: function(result) {
							if(result.code == "200") {
								$("[name=id]").val(result.data.id);
								$("[name=content]").val(result.data.content);
							}
						}
					});

				});

				//选项卡初始
				$(".layui-tab-title li:first-child").click();

			} else {
				//弹出问卷调查页面
				parent.layer.open({
					type: 2,
					maxmin: true,
					title: '问卷调查',
					closeBtn:0,
					area: ['1000px', '600px'],
					content: _path + "/edu/survey/survey_paper/" + _id
				});
			}
		}
	});*/

	element.on('tab(stage)', function(data) {
		//加载首页
		var count = list_stage(1, 1, this.value, data.index);
		//加载页码
		page(count, this.value, data.index);
		$("legend").text(this.innerHTML + "自我评价");
		//加载阶段总结
		$.ajax({
			url: _path + "/edu/evaluation/get_evaluation",
			type: 'post',
			dataType: 'json',
			data: {
				'type': this.value
			},
			success: function(result) {
				if(result.code == "200") {
					$("[name=id]").val(result.data.id);
					$("[name=content]").val(result.data.content);
				}
			}
		});
	});
});

/**
 * 查询阶段信息
 * @param pageno    页码
 * @param pagesize  每页数
 * @param type      阶段类型 1 2 3
 * @param index     tab下标，
 * @returns
 */
function list_stage(pageno, pagesize, type, index) {
	var count;
	$.ajax({
		url: _path + "/edu/stage/student/list_self_stage_evaluation",
		type: 'post',
		dataType: 'json',
		data: {
			'pageno': pageno,
			'pagesize': pagesize,
			'type': type,
		},
		async: false,
		success: function(result) {
			$(".layui-tab-content .layui-tab-item").each(function() {
				$(this).html("");
			});
			if(result.code == 200) {
				count = result.count;
				var getTpl = document.getElementById("stage_list").innerHTML;
				view = $(".layui-tab-content .layui-tab-item:eq(" + index + ")");
				laytpl(getTpl).render(result, function(html) {
					view.html(html);
				});
			}
		}
	});
	return count;
}

/**
 * 加载页码
 * @param count  
 * @param type
 * @param index  tab下标
 * @returns
 */
function page(count, type, index) {
	laypage.render({
		elem: 'page',
		count: count,
		layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
		jump: function(obj, first) {
			if(!first) { //首次不加载
				list_stage(obj.curr, obj.limit, type, index);
			}
		},
		limits: [1, 5, 10, 20],
		limit: 1 //默认采用60
	});
}

/**
 * 评价
 * @param item
 * @returns
 */
function evaluate_stage(item) {
	$.ajax({
		url: _path + "/edu/stage/student/get_stage_evaluation/" + item.value,
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if(data.code == 200) {
				var evaluation = document.getElementById("evaluation").innerHTML;
				laytpl(evaluation).render(data.data, function(html) {
					layer.open({
						type: 1,
						title: false,
						closeBtn: 0,
						shadeClose: true,
						shade: 0.6,
						skin: 'layui-layer-rim',
						fixed: false,
						content: html,
						area: ['650px', '350px'],
						btn: ['提交评价', '取消'],
						yes: function(index, layero) {
							$.ajax({
								url: _path + "/edu/stage/student/update_stage_evaluation",
								type: 'post',
								dataType: 'json',
								data: layero.find('form').serialize(),
								async: false,
								success: function(result) {
									if(result.code == '200') {
										$(".layui-laypage-btn").click()
										layer.close(index);
										layer.msg("评价成功");
									}
								},
								complete: function() {
									//									 layer.close(index);
								}
							});
						}
					});
				});
			}
		}
	});
}

//提交评价
function evaluation(item) {
	var _item = $(item)
	if(_item.val() == "0") {
		$("textarea[name=content]").removeAttr("disabled");
		_item.val("1");
		_item.text("提交评价");
		_item.addClass("layui-btn-normal");
		$("textarea[name=content]").focus();
	} else {
		$.ajax({
			url: _path + "/edu/evaluation/evaluate",
			type: 'post',
			dataType: 'json',
			data: $(item).parents('form').serialize(),
			success: function(result) {
				if(result.code == "200") {
					layer.msg('评价成功');
					$("textarea[name=content]").attr("disabled", "");
					_item.val("0");
					_item.text("评价");
					_item.removeClass("layui-btn-normal")
				}
			}
		});
	}

}