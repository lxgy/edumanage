layui.use(['laytpl','table','layer','upload','form'], function(){
	var table = layui.table,
	upload = layui.upload,
	laytpl = layui.laytpl,
	form = layui.form,
    layer = parent.layer === undefined ? layui.layer : parent.layer;
//	layer = layui.layer;
  
	//初始化表格
  	table.render({ 
  		elem: '#table_student'
  		,url: _path + '/admin/student/list'
  		,method: 'post' 
  		,cols: [[
  			{type:'checkbox',fixed: 'left'}
  		    ,{field:'name', title:'姓名', width:80,fixed: 'left',event:'details'}
  		    ,{field:'status', title:'状态', width:70,fixed: 'left',event:'status',templet:'<div>{{getStatus(d.status)}}</div>'}
  		    ,{field:'seehope_class', title:'班级',width:160,event:'transfer',templet:'<div>{{d.seehope_class==undefined?"":d.seehope_class.name}}</div>'}
	        ,{field:'sex', title:'性别', width:50}
	        ,{field:'phone', title:'电话', width:110}
	        ,{field:'wechat', title:'微信', width:110}
	        ,{field:'email', title:'邮件', width:110}
	        ,{field:'qq', title:'QQ', width:100}
	        ,{field:'university', title:'学校', width:140,}
	        ,{field:'major', title:'专业', width:110,}
	        ,{field:'education', title:'学历', width:60,}
	        ,{field:'graduation_time', title:'毕业时间', width:80,}
	        ,{field:'IDcard', title:'身份证', width:200,}
	        ,{field:'information', title:'基本情况', width:120,}
	        ,{field:'channel', title:'渠道', width:120,}
	        ,{field:'contract_time', title:'签约时间', width:110,}
	        ,{field:'contract_teacher', title:'签约人', width:100,templet:'<div>{{d.contract_teacher==undefined?"":d.contract_teacher.name}}</div>'}
	        ,{field:'graduation_where', title:'毕业去向', width:150,}
	        ,{field:'remark', title:'备注', width:300,}
	        ,{fixed:'right', title:'操作', width:200, align:'center', toolbar: '#toolbar'}
  	    ]]
  		,response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		}
  	    ,request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	    }   
  	    ,id: 'studentReload'
  	    ,page : true
  	    ,limits: [10,20,30,50]
  	    ,limit: 20 //默认采用60
  	    ,loading: true
  	    ,height: 'full-148'
  	    ,size: "sm"
	}); 
  	
  	/**
	 * 加载班级
	 */
	$.ajax({
 	   url : _path + '/admin/class/list',
 	   dataType : 'json',
 	   type : 'post',
 	   async: false,
 	   data: {pagesize:20},
 	   success : function(result){
 		  if(result.code == '200'){
 			  var getTpl = document.getElementById('class_option').innerHTML
 			  laytpl(getTpl).render(result, function(html){
 				  $('#class_select').html(html);
 				 form.render();
	  		  });
 		  }
 	   }
     });
  	
  	
	/**
	 * 表格重载
	 */
	function tableReload() {
		table.reload('studentReload', {
		    where: {
		      key: $('#keyText').val(),
		      status: $('#status').prop("checked")
		    }
		  });
	}
	
	$('.search_btn').on('click', function(){
		tableReload();
	});
	$('.layui-form-checkbox').on('click', function(){
		tableReload();
	});
	
	//添加
	$('.add_btn').click(function(){
		var index = layui.layer.open({
			title: "添加学生",
			type : 2,
			area : [ '800px', '500px' ],
			fixed : true, // 不固定
			maxmin : true,
			content : _path+"/admin/student/student_add"
		});
		layui.layer.full(index);
	});
	
	/*查看毕业生信息*/
	$('#f-stu').on('click',function(){
		var index = layui.layer.open({
			title:'已毕业学生信息',
			type:2,
			area:['800px','500px'],
			fixed:true,
			maxmin:true,
			content:_path+"/admin/student/graduated_students"
		});
		layui.layer.full(index);
	});
	
	/**
	 * 导入学生数据表页面
	 */
	$('.upload_btn').click(function(){
		layui.layer.open({
			  type: 2,
			  title: false,
			  closeBtn: 0,
			  shade:0.6,
			  skin: 'layui-layer-rim',
			  shadeClose: true,
			  area : [ '400px', '300px' ],
			  content: _path+"/admin/student/student_upload"
		});
	});
	
	/**
	 * 监听工具条
	 */
	table.on('tool(student)', function(obj){
	  var data = obj.data;      //获得当前行数据
	  var layEvent = obj.event; //获得 lay-event 对应的值
	  var tr = obj.tr;          //获得当前行 tr 的DOM对象
	 
	  if(layEvent === 'status'){ //更改状态
		  var content = getStatusHtml(data.status);
		 
	      layui.layer.open({
	    	  content: content
	    	  ,btn: ['确定', '取消']
	    	  ,yes: function(index, layero){
	    		  var status = layero.find("input:checked").val()
	    		  $.ajax({
	   	    	   url : _path + "/admin/student/update_student_status",
	   	    	   dataType : 'json',
	   	    	   type : 'post',
	   	    	   data : { sid : data.id,
	   	    		        status : status},
	   	    	   success : function(result){
	   	    		  if(result.code == '200'){
	   	    		    $(".layui-laypage-btn").click();
	   	    		    layer.msg('修改成功');
	   	    		    $(".layui-laypage-btn").click();
	   	    		  }
	   	    	   },
	   	    	   complete : function(){
	   	    		   layer.close(index);
	   			   }
	   	        });
	    	  }
	    	  ,btn2: function(index, layero){
	    	  }
	    	  ,success: function(){
	    		  form.render('radio');
	    	  }
	    	});
	  } else if(layEvent === 'edit'){ //编辑
		  layui.layer.open({
			  title: "编辑学生"
			  ,type: 2
			  ,area: ['700px', '520px']
		      ,shade: 0.6 
	          ,shadeClose :true
		      ,fixed: false
			  ,maxmin: true
			  ,content: _path + '/admin/student/student_edit/' + data.id
		  });
	  } else if(layEvent === 'class_log'){ //班级日志
		  layer.open({
			  title: false
			  ,type: 2 
			  ,area: ['650px', '400px']
			  ,title: data.name
			  ,shade: 0.6 
		      ,shadeClose :true
			  ,fixed: false
			  ,content: _path + '/admin/student/student_class_log_list/'+data.id
			});  
	  } else if(layEvent === 'details'){    //详情   
		  layer.open({
			  title: data.name
			  ,type: 2
			  ,area: ['900px', '630px']
		  	  ,shade: 0.6 
		      ,shadeClose :true
			  ,fixed: false
			  ,content: _path + '/admin/student/student_info/' + data.id
		  });
	   } else if(layEvent === 'transfer'){
		   layer.open({
	    	  content: '<select class="layui-select">'+$('#cid').html()+'</select>'
	    	  ,btn: ['确定', '取消']
	    	  ,yes: function(index, layero){
	    		  var classId = layero.find("select").val();
	    		  
	    		  $.ajax({
	   		 	   url : _path + '/admin/student/update_class/'+data.id,
	   		 	   dataType : 'json',
	   		 	   type : 'post',
	   		 	   data : {'classId' : classId },
	   		 	   success : function(result){
	   		 		   if(result.code=='200'){
	   		 			  $(".layui-laypage-btn").click();
	   		 		   }
	   		 		  layer.msg(result.msg);
	   		 	   }
	   		     });
	   	        	
	    	  }
	    	  ,btn2: function(index, layero){
	    	  }
	    	  ,success: function(layero, index){
	    		  form.render('select');
	    	  }
	    	});
	   }else if(layEvent === 'survey_detail'){
	   	   parent.layer.open({
	   	   	   title:'学生问卷调查报告',
	   	   	   type:2,
	   	   	   maxmin:true,
	   	   	   area: ['900px', '630px'],
	   	   	   content:_path + "/admin/survey/survey_paper_list"
	   	   });
	   }
	});

	/**
	 * 批量换班
	 */
	var id;
	$('.change_btn').click(function(){
		var checkStatus = table.checkStatus('studentReload'); 
		if(checkStatus.data.length>0){
			if($('#cid').val() == undefined || $('#cid').val() == '' ){
				layer.msg('请选择班级');
				return ;
			}
			id = new Array();
			for(var i=0; i<checkStatus.data.length; i++){
				id[i] = checkStatus.data[i].id;
			}
			$.ajax({
		 	   url : _path + '/admin/student/update_students_class',
		 	   dataType : 'json',
		 	   type : 'post',
		 	   data : {'id' : JSON.stringify(id),
		 		   	   'cid' : $('#cid').val()
		 	   },
		 	   success : function(result){
		 		   if(result.code=='200'){
		 			  $(".layui-laypage-btn").click();
		 		   }
		 		  layer.msg(result.msg);
		 	   }
		     });
			
		}else{
			layer.msg('请选择学生');
		}
	});
	
});


//获得状态
function getStatus(status){
	switch (status) {
	case 0:
		return '<span class="layui-badge layui-bg-orange">停用</span>';
	case 1:
		return '<span class="layui-badge">毕业</span>';
	case 2:
		return '<span class="layui-badge layui-bg-blue">学习中</span>'
	case 3:
		return '<span class="layui-badge layui-bg-gray">暂停学习</span>';
	case 4:
		return '<span class="layui-badge layui-bg-green">转班中</span>';
	default:
		return '';
	}
}

function getStatusHtml(status){
	var html = '<div class="layui-form status-radio">';
	html = html + '<input type="radio" name="status" value="10" title="无状态"';
	if( status == 10)html = html + ' checked';
	html = html + '><input type="radio" name="status" value="4" title="转班中"';
	if( status == 4)html = html + ' checked';
	html = html + '><input type="radio" name="status" value="3" title="暂停学习"';
	if( status == 3)html = html + ' checked';
	html = html + '><input type="radio" name="status" value="2" title="学习中"';
	if( status == 2)html = html + ' checked';
	html = html + '><input type="radio" name="status" value="1" title="毕业"';
	if( status == 1)html = html + ' checked';
	html = html + '><input type="radio" name="status" value="0" title="停用"';
	if( status == 0)html = html + ' checked';
	html = html + '></div>';
	return html;
}
