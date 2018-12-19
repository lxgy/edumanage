<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学内容阶段</title>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${path }/admin/js/stage/stage_list.js"></script>
<script type="text/javascript" src="${path }/static/common/art-template/template-web.js"></script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-quote-nm layui-form">

		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" id="key" name="key" placeholder="关键字" class="layui-input search_input">
			</div>
		</div>

		<div class="layui-inline">
			<div class="layui-input-inline">
				<select id="type">
					<option value="">所属阶段</option>
					<option value="1">第一阶段</option>
					<option value="2">第二阶段</option>
					<option value="3">第三阶段</option>
				</select>
			</div>
		</div>
		
		<div class="layui-inline">
			<a class="layui-btn search_btn stage_search" >查询</a>
			<a class="layui-btn layui-btn-normal stageAdd_btn">添加阶段</a>
		</div>

	</blockquote>

	<table class="layui-table" id="table_stage" lay-size="sm">
		<thead>
			<tr>
				<th width="80"></th>
				<th align="center" width="200">阶段</th>
				<th align="center">内容</th>
				<th align="center" width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

	<div id="page"></div>
	
	<script type="text/html" id="stage_list">

  	{{#  layui.each(d.data, function(index, item){ }}
    	<tr >
	  		<td rowspan={{item.contents.length+1}}>{{ getTypeName(item.type)}}</td>
      		<td rowspan={{item.contents.length+1}}>{{ item.name }}</td>
      		<td align="left" colspan="2" style="background-color: rgba(213, 213, 213, 0.5);">
				<button class="layui-btn layui-btn-xs" onclick="add_stage_content(this)" value="{{item.id}}">添加内容</button>
      			<button class="layui-btn layui-btn-xs" onclick="edit_stage(this)" value="{{item.id}}">修改</button>
				<button class="layui-btn layui-btn-danger layui-btn-xs" onclick="delete_stage(this)" value="{{item.id}}">删除</button>
			</td>
    	</tr>
			{{#  layui.each(item.contents, function(index, item2){ }}
				<tr>
      				<td>{{ item2.content }}</td>
      				<td><button class="layui-btn layui-btn-xs" onclick="edit_stage_content(this)" value="{{item2.id}}">修改</button>
						<button class="layui-btn layui-btn-danger layui-btn-xs" onclick="delete_stage_content(this)" value="{{item2.id}}">删除</button>
					</td>
				</tr>
			{{#  }); }}
  	{{#  }); }}
  	{{#  if( d.data == undefined || d.data.length === 0){ }}
    	<tr>
      		<td colspan="4">无数据</td>
		</tr>
  	{{#  } }} 
		
	</script>
</body>

</html>