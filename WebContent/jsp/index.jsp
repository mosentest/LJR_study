<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>移动微学习系统后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<div class="dl-title"><span class="">移动微学习系统后台管理</span></div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user">root</span><a href="#" title="退出系统"
				class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">管理</div></li>
<!-- 				<li class="nav-item dl-selected"><div -->
<!-- 						class="nav-item-inner nav-order">商品管理</div></li> -->
<!-- 				<li class="nav-item dl-selected"><div -->
<!-- 						class="nav-item-inner nav-order">人事管理</div></li> -->
<!-- 				<li class="nav-item dl-selected"><div -->
<!-- 						class="nav-item-inner nav-order">系统管理</div></li> -->
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				homePage : '11',
				menu : [ {
					text : '基础数据',
					items : [ {
						id : '11',
						text : '文章管理',
						href : 'jsp/article/index.jsp'
					}, {
						id : '12',
						text : '问题管理',
						href : 'jsp/discipline/index.jsp'
					}, {
						id : '13',
						text : '问卷管理',
						href : 'jsp/questionnaire/index.jsp'
					}, {
						id : '14',
						text : '用户管理',
						href : 'jsp/user/index.jsp'
					}]
				},{
					text : '用户数据',
					items : [ {
						id : '15',
						text : '用户测试',
						href : 'jsp/userQuestionnaire/index.jsp'
					}, {
						id : '16',
						text : '用户收藏',
						href : 'jsp/userCollectDiscipline/index.jsp'
					}, {
						id : '17',
						text : '用户错题',
						href : 'jsp/userWrongDiscipline/index.jsp'
					}]
				} ]
			}];
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>