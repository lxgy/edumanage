<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>创客笔试题</title>
		<%@ include file="/common/common.jsp"%>
	</head>
	<!--页面主题内容开始-->

	<body>
		<!--头部温馨提示开始-->
		<blockquote class="layui-elem-quote  layui-quote-nm layui-form" style="margin-top: 10px; background: white;">
			<table class="layui-table">
				<tr>
					<th style="text-align: center; color: red;font-weight: 900;">请先完成此所有问卷调查问题，才能正常使用教务系统！</th>
				</tr>
			</table>
		</blockquote>
		<!--头部温馨提示结束-->

		<!--问卷内容开始-->
		<blockquote class="layui-elem-quote  layui-quote-nm" style="margin-top: 10px; background: white; min-height: 400px;">
			<div id="content" style="max-width: 900px; margin: auto;">
				<form class="layui-form">
					<input type="hidden" name="" id="s_len" value="${len}" /> 
					<input type="hidden" name="" id="s_id" value="${sid}" />
					
					<c:forEach items="${surveys }" var="sur" varStatus="status">
						<c:set var="item" value="${sur.id }"></c:set>
						<c:if test="${sur.type==1 }">
							<div class="layui-form-item">
								<div class="layui-form-label" style="width: 100%; text-align: left;">
									【单】${sur.description }</div>
								<div class="layui-input-block" style="margin-left: 50px;">
									<c:forEach items="${survey_options }" var="options">
										<c:set var="item2" value="${options.survey.id }"></c:set>
										<c:if test="${item == item2}">
											<input type="radio" class="layui-form-radio" name="dansewr_${status.index}" value="${options.id}_${sur.id}" title="${options.summary}">
											<br />
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<%-- <c:forEach items="${surveys }" var="sur" varStatus="status">
						<c:set var="item" value="${sur.id }"></c:set>
						<c:if test="${sur.type==2 }">
							<div class="layui-form-item">
								<div class="layui-form-label" style="width: 100%; text-align: left; float: none;">
									【多】${sur.description }</div>
								<div class="layui-input-block" style="margin-left: 50px;">
									<c:forEach items="${survey_options }" var="options"  varStatus="sta">
										<c:set var="item2" value="${options.survey.id }"></c:set>
										<c:if test="${item == item2}">
											<input type="checkbox" mystr="${sur.id }" name="sansewr_${status.index}" id="stid_${sta.index}" value="${options.id}" class="layui-input" title="${options.summary}" lay-verify="required">
											</br>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</c:forEach> --%>
					<c:forEach items="${surveys }" var="sur" varStatus="status">
						<c:set var="item" value="${sur.id }"></c:set>
						<c:if test="${sur.type==3 }">
							<div class="layui-form-item">
								<div class="layui-form-label" style="width: 100%; text-align: left; float: none;">
									【问】${sur.description } </br>
								</div>
								<div class="layui-input-block" style="margin-left: 50px;">
									<textarea id="wansewr_${status.index}" tstr="${sur.id}" name="wansewr_${status.index}" rows="" cols="" class="layui-textarea"></textarea>
									</br>
								</div>
							</div>
						</c:if>
					</c:forEach>

					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="*">提交</button>
						</div>
					</div>
				</form>
			</div>
		</blockquote>
		<!--问卷内容结束-->
	</body>
	<!--页面主题内容结束-->

	<script type="text/javascript">
		layui.use(['form', 'layer'], function() {
			var form = layui.form,
				layer = layui.layer;

			var len = $("#s_len").val();
			var ssid = $("#s_id").val();

			var danswers = new Array();
			//var sanswers = new Array();
			var wanswers = new Array();

			form.on('submit(*)', function(data) {
				
				//为单选题赋值
				for(var i = 0; i < len; i++) {
					danswers[i] = getRadio(i);
					//多选题
					/* if(getSelected(i) != null) {
						sanswers[i] = getSelected(i);
					} */
					//问答题
					if(getTextarea(i) != null) {
						var test = "wansewr_" + i;
						var t_id = document.getElementById(test).getAttribute('tstr');
						wanswers[i] = getTextarea(i) + "_" + t_id;
					}
				}

				$.ajax({
					type: "post",
					url: _path + "/user/survey/student_survey",
					async: true,
					dataType: 'json',
					traditional: true,
					data: {
						"danswer": danswers,
						"wanswer": wanswers,
						"id": ssid
					},
					success: function(result) {
						parent.layer.closeAll();
						parent.layer.msg("感谢您的提交，我们工作人员将会在3个工作日内联系您！");
					},
					error:function(){
						parent.layer.closeAll();
						parent.layer.msg("感谢您的提交，我们工作人员将会在3个工作日内联系您！");
					}
				});
			});

			//获取radio选中的值
			function getRadio(i) {
				var r_name = "dansewr_" + i;
				var radios = document.getElementsByName(r_name);
				var value;
				for(var j = 0; j < radios.length; j++) {
					if(radios[j].checked) {
						value = radios[j].value;
						break;
					}
				}
				return value;
			}

			//获取checkbox的值.
			function getSelected(i) {
				var c_name = "sansewr_" + i;
				var checkboxs = document.getElementsByName(c_name);
				var ch = new Array();
				var str = null;
				var index = -1;

				for(var j = 0; j < checkboxs.length; j++) {
					if(checkboxs[j].checked) {
						ch[j] = checkboxs[j].value;
						index = j;
					}
				}
				if(index >= 0) {
					var test = "stid_" + i;
					var c_id = document.getElementById(test).getAttribute('mystr');
					index = index + 1;
					ch[index] = c_id;
					str = ch.join("_");
				}
				return str;
			}

			//获取textarea的值.
			function getTextarea(i) {
				var w_name = "#wansewr_" + i;
				var t_anw = null;
				t_anw = $(w_name).val();
				return t_anw;
			}
		});
	</script>

</html>