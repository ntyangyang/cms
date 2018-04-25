<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="/css/jquery.dataTables.css">
<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="/js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css">
<script type="text/javascript" charset="utf8"
	src="/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" charset="utf8" src="/js/jquery-ui.min.js"></script>

<script type="text/javascript" charset="utf8" src="/js/instance/edit.js"></script>
<title>CMS</title>
</head>
<body>
	<div class="container-fluid">
		<h2>CMS后台</h2>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link"
				href="/cms/component/list.htm">组件管理</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/cms/template/list.htm">模板管理</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/cms/instance/list.htm">页面实例管理</a></li>
		</ul>
		<div class="card">
			<div class="card-body">
				<!-- data area -->
				<div id="instanceDataBackupDiv">
					<input type="hidden" id="instanceId" value="${instanceId}" />
					<p json='${fn:replace(instanceValue,"'
						","singlequote")}' id="instanceValue"></p>
				</div>
				<h4 class="card-title">
					实例数据编辑
					<button type="button"
						class="btn btn-outline-primary btn-sm saveAllInstanceData">保存全部</button>
					&nbsp;
					<button type="button"
						class="btn btn-outline-secondary btn-sm return">返回</button>
				</h4>
				<!-- 分区 -->
				<div id="top_partition_area">
					<ul class="nav nav-tabs" role="tablist">
						<c:forEach var="i" begin="1" end="${template.partitionNum}">
							<li class="nav-item" index="${i}"><a class="nav-link"
								data-toggle="tab" href="#menu${i}">分区${i}</a></li>
						</c:forEach>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<c:forEach var="i" begin="1" end="${template.partitionNum}">
							<div id="menu${i}" class="tab-pane">
								<table class="table table-bordered mh-100">
									<thead>
										<tr>
											<th style="width: 10%">模块区</th>
											<th style="width: 10%">模块实例区</th>
											<th style="width: 80%">模块属性编辑区</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td style="width: 10%" class="left_component"
												id="left_component_area${i}" index="${i}">
												<!-- 组件区 --> <c:forEach var="component"
													items="${componentList}">
													<div componentId="${component.id}"
														componentCode="${component.code}"
														componentInstanceId="${instanceId }"
														class="alert alert-primary alert-dismissable component_alert">
														<button type="button" class="close" >&times;</button>
														${component.name}
													</div>
												</c:forEach>
											</td>
											<td style="width: 10%" class="middle_content"
												id="middle_content_area${i}" index="${i}">
												<!-- 组件实例区 -->
											</td>
											<td style="width: 80%" class="right_property"
												id="right_property_area${i}" index="${i}">
												<!-- 组件属性编辑区 -->
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- 组件编辑模板生成区 -->
				<div style="display: none;" id="component_modify_template_area"></div>
				</button>
			</div>
		</div>
	</div>
</body>
</html>