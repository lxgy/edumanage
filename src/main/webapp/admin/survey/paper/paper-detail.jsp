<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户测试题目详情</title>
		<%@ include file="/common/common.jsp"%>
	</head>

	<body>
		<blockquote class="layui-elem-quote  layui-quote-nm" style="margin-top: 10px; background: white; min-height: 400px;">
			<div id="content" style="max-width: 900px; margin: auto;">
				<form class="layui-form">
					<input type="hidden" name="" id="s_len" value="${len}" />
					<c:forEach items="${surveys }" var="sur" varStatus="status">
						<c:set var="item" value="${sur.id }"></c:set>
						<c:if test="${sur.type==1 }">
							<div class="layui-form-item">
								<div class="layui-form-label" style="width: 100%; text-align: left;">
									【单】${sur.description }
								</div>
								<c:forEach items="${survey_options }" var="options">
									<c:set var="item2" value="${options.survey.id }"></c:set>
									<c:if test="${item == item2}">
										<c:forEach items="${papers }" var="paper">
											<c:if test="${paper.submit_summary == options.id.toString() }">
												<div class="layui-input-block" style="margin-left: 50px;">
													<input type="radio" checked="checked" class="layui-form-radio" name="dansewr_${status.index}" value="${options.id}_${sur.id}" title="${options.summary}">
												</div>
											</c:if>
										</c:forEach>
										<div class="layui-input-block" style="margin-left: 50px;">
											<input type="radio" class="layui-form-radio" name="dansewr_${status.index}" value="${options.id}_${sur.id}" title="${options.summary}">
										</div>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
					</c:forEach>
					<%-- <c:forEach items="${surveys }" var="sur" varStatus="status">
						<c:set var="item" value="${sur.id }"></c:set>
						<c:if test="${sur.type==2 }">
							<div class="layui-form-item">
								<div class="layui-form-label" style="width: 100%; text-align: left; float: none;">
									【多】${sur.description }
								</div>
								<c:forEach items="${survey_options }" var="options">
									<c:set var="item2" value="${options.survey.id }"></c:set>
									<c:if test="${item == item2}">
										<c:forEach items="${papers }" var="paper">
											<c:if test="${paper.submit_summary == options.id.toString() }">
												<div class="layui-input-block" style="margin-left: 50px;">
													<input type="checkbox" checked="checked" mystr="${sur.id }" name="sansewr_${status.index}" id="${options.id}" value="${options.id}" class="layui-input" title="${options.summary}" lay-verify="required">
												</div>
											</c:if>
										</c:forEach>
										<div class="layui-input-block" style="margin-left: 50px;">
											<input type="checkbox" mystr="${sur.id }" name="sansewr_${status.index}" id="${options.id}" value="${options.id}" class="layui-input" title="${options.summary}" lay-verify="required">
										</div>
									</c:if>
								</c:forEach>

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
									<c:forEach items="${papers }" var="paper">
										<c:if test="${sur.id == paper.survey.id}">
											<textarea id="wansewr_${status.index}" tstr="${sur.id}" name="wansewr_${status.index}" rows="" cols="" class="layui-textarea">${paper.submit_summary}</textarea>
										</c:if>
									</c:forEach>
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
	</body>

	<script type="text/javascript">
		layui.use(['form', 'layer'], function() {
			var form = layui.form,
				layer = layui.layer;
		});
	</script>

	<!-- <script type="text/javascript">
		window.onload = function() {
			var paperArr = new Array();
			<c:forEach items="${papers}" var = "paper" varStatus="status">
				paperArr["${status.index}"] = "${paper.submit_summary}"; 
				<c:if test="${paper.survey.type != 3}">
				  var _idt = "#" + "${paper.submit_summary}";
			      $("#${paper.submit_summary}").attr("checked", "checked"); 
			    </c:if> 
			</c:forEach>
		} 
	</script> -->

</html>