var laytpl,layer,laypage;

layui.use(['laytpl', 'layer','form','laypage'],function() {
	laytpl = layui.laytpl;
	layer = layui.layer;
	laypage = layui.laypage;
	
	//加载首页
	var count = list_stage(1,5,null,null,1);
	//加载页码
	page(count,null,null);
	
	/**
	 * 按条件查询
	 */
	$('.search_btn').on('click', function() {
		
		var count = list_stage(1,5,$('#key').val(),$('#type').val(),1);
		page(count,$('#key').val(),$('#type').val());
	});

	/**
	 * 添加阶段
	 */
	$('.stageAdd_btn').on('click', function() {
		layer.open({
			type: 2
			,title: '新增阶段'
			,area: ['500px', '300px']
			,shade: 0.6 
			,shadeClose :true
			,fixed: false
			,content: _path + '/admin/stage/stage_add'
		});
	});

});

/**
 * 加载页码
 * @param count
 * @returns
 */
function page(count,key,type){
	laypage.render({
	  elem: 'page'
	  ,count: count
	  ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
	  ,jump: function(obj, first){
		  if(!first){ //首次不加载
			  list_stage(obj.curr,obj.limit,key,type,1);
		  }
	  }
	  ,limits: [5,10,20]
	  ,limit: 5 //默认采用60
	});
}

/**
 * 查询阶段信息
 */	
function list_stage(pageno,pagesize,key,type,status){
	var count;
	$.ajax({
		url: _path + "/admin/stage/list_stage",
		type: 'post',
		dataType: 'json',
		data: {	'pageno':pageno,
				'pagesize':pagesize,
				'key': key,
				'type': type,
				'status': 1 },
		async: false,
		success: function(result){
			if(result.code == 200){
				count = result.count;
				var getTpl = document.getElementById('stage_list').innerHTML
				laytpl(getTpl).render(result, function(html){
				  $('tbody').html(html);
				});
			}
		}
	});
	return count;
}

/**
 * 编辑阶段 页面
 * @param item
 * @returns
 */
function edit_stage(item){
	layer.open({
		type: 2
		,title: '阶段编辑'
		,area: ['500px', '300px']
		,shade: 0.6 
		,shadeClose :true
		,fixed: false
		,content: _path + '/admin/stage/stage_edit/' + item.value
	});
}

/**
 * 删除阶段
 * @param item
 * @returns
 */
function delete_stage(item){
	layer.confirm("是否确认删除该信息", {
		title: "信息",
		icon: 0,
		btn: ['删除', '取消']
	}, function() {
		$.ajax({
			type: "post",
			url: _path + "/admin/stage/delete_stage/" +item.value,
			dataType: 'json',
			success: function(result) {
				if(result.code == "200"){
					$(".layui-laypage-btn").click();
				}
				layer.msg(result.msg);
			}
		});
		return false;
	}, function() {
		layer.msg("取消删除操作");
	});
}

/**
 * 根据阶段添加阶段内容 页面
 * @param item
 * @returns
 */
function add_stage_content(item){
	layer.open({
		type: 2
		,title: '阶段编辑'
		,area: ['500px', '300px']
		,shade: 0.6 
		,shadeClose :true
		,fixed: false
		,content: _path + '/admin/stage/content/stage_content_add/' + item.value
	});
}

/**
 * 编辑阶段内容 页面
 * @param item
 * @returns
 */
function edit_stage_content(item){
	layer.open({
		type: 2
		,title: '阶段编辑'
		,area: ['500px', '300px']
		,shade: 0.6 
		,shadeClose :true
		,fixed: false
		,content: _path + '/admin/stage/content/stage_content_edit/' + item.value
	});
}

/**
 * 删除阶段
 * @param item
 * @returns
 */
function delete_stage_content(item){
	layer.confirm("是否确认删除该信息", {
		title: "信息",
		icon: 0,
		btn: ['删除', '取消']
	}, function() {
		$.ajax({
			type: "post",
			url: _path + "/admin/stage/content/delete_stage_content/" +item.value,
			dataType: 'json',
			success: function(result) {
				if(result.code == "200"){
					$(".layui-laypage-btn").click();
				}
				layer.msg(result.msg);
			}
		});
		return false;
	}, function() {
		layer.msg("取消删除操作");
	});
}

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