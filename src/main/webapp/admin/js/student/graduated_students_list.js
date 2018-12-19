layui.use(['laytpl', 'table', 'layer', 'form'], function() {
	
	var table = layui.table,
		upload = layui.upload,
		laytpl = layui.laytpl,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
	
	table.render({
		elem: '#table_graduated_student',
		url: _path + '/admin/student/graduated_list',
  		method: 'post',
  		cols:[[
  		   {type:'checkbox',fixed: 'left'}
  		    ,{field:'name', title:'姓名', width:80,fixed: 'left'}
  		    ,{field:'status', title:'状态', width:70,fixed: 'left',event:'status',templet:'<div>{{getStatus(d.status)}}</div>'}
  		    ,{field:'seehope_class', title:'班级',width:160,templet:'<div>{{d.seehope_class==undefined?"":d.seehope_class.name}}</div>'}
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
  		]],
  		response: {
  		   statusCode: 200 //成功的状态码，默认：0
  		},
  		request: {
  	       pageName: 'pageno' //页码的参数名称，默认：page
  	       ,limitName: 'pagesize' //每页数据量的参数名，默认：limit
  	   },
  	   id: 'stReload',
  	   page : true,
  	   limits: [10,20,30,50],
  	   limit: 20, //默认采用60
  	   loading: true,
  	   height: 'full-148',
  	   size: "sm"
	});
	
	/*多条件查询*/
	$(".search1_btn").on('click',function(){
		table.reload('stReload',{
			where:{
				key:$("#keyText1").val()
			}
		});
	});
	
	table.on('tool(graduated_student)', function(obj){
		
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

