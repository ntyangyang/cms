<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" charset="utf8"
	src="/js/bootstrap-datetimepicker.min.js"></script>


<script type="text/javascript" charset="utf8" src="/js/instance/list.js"></script>
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
				<h4 class="card-title">
					页面实例管理
					<button type="button"
						class="btn btn-outline-primary btn-sm addInstance">新建页面实例</button>
				</h4>
				<form class="form-inline">
					<label>URL</label> <input type="text" class="form-control"
						placeholder="URL" id="url" /> <label>类型</label> <select
						class="form-control" id="pageType">
						<option value="">--</option>
						<option value="HOMEPAGE">主页</option>
						<option value="CATEGORY">分类</option>
						<option value="LOOKS">系列</option>
						<option value="SHOWS">展台</option>
					</select> <label>状态</label> <select class="form-control" id="lifecycle">
						<option value="">--</option>
						<option value="1">可用</option>
						<option value="0">禁用</option>
					</select>
					<button type="button" id="search" class="btn btn-info btn-sm">搜索</button>
				</form>
				<br />
				<h4 class="card-title">页面实例列表</h4>
				<table id="instance_list_table" class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>URL</th>
							<th>模板名称</th>
							<th>类型</th>
							<th>描述</th>
							<th>最后修改时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<!-- 创建页面实例模态框 -->
		<div class="modal fade" id="modal-instance-add">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">新建页面实例</h4>
						<input type="hidden" class="input_add_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label>使用模板</label><select
									class="form-control select_add_template" name="type">
									<c:forEach var="template" items="${templateList}">
										<option value="${template.id }">${template.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>实例描述</label>
								<textarea class="form-control textarea_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>URL</label> <input class="form-control input_add_url"></input>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_instance">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>

				</div>
			</div>
		</div>

		<!-- 修改页面实例模态框 -->
		<div class="modal fade" id="modal-instance-modify">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">修改页面实例</h4>
						<input type="hidden" class="input_add_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label>使用模板</label><select
									class="form-control select_add_template" name="type">
									<c:forEach var="template" items="${templateList}">
										<option value="${template.id }">${template.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>实例描述</label>
								<textarea class="form-control textarea_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>URL</label> <input class="form-control input_add_url"></input>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_instance">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>


		<!-- 发布模态框 -->
		<div class="modal fade" id="modal-instance-publish">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">发布</h4>
						<input type="hidden" class="input_instance_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label>URL</label> <input class="form-control input_url"></input>
							</div>
							<div class="form-group">
								<label>生效时间</label> <input id="input_start_time" type="text"
									class="form-control form_datetime"></input>
							</div>
							<div class="form-group">
								<label>失效时间</label> <input id="input_end_time" type="text"
									class="form-control form_datetime"></input>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_instance">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>