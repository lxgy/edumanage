var $,tab,skyconsWeather;
layui.config({
	base : _path + "/static/js/"
}).use(['bodyTab','form','element','layer','jquery'],function(){
	// 添加新窗口
	$(".panel_box.row .panel.col a").on("click",function(){
		var _tree = parent.$(window.parent.document).find(".layui-side .navBar .layui-nav.layui-nav-tree");
		var _code = parent.$(this).data("code");
		_tree.find(".layui-nav-item a[data-code="+_code+"]").trigger("click");
	})

})
