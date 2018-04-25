<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" charset="utf8"
	src="/js/component/list.js"></script>
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
					组件管理
					<button type="button"
						class="btn btn-outline-primary btn-sm addComponent">新建组件</button>
				</h4>
				<form class="form-inline">
					<label>组件名称</label> <input type="text" class="form-control"
						placeholder="组件名称" id="name" /> <label>组件编码</label> <input
						type="text" class="form-control" placeholder="组件编码" id="code" />
					<label>组件类型</label> <select class="form-control" id="type">
						<option value="">--</option>
						<option value="INSTANCE_COMPONENT">实例组件</option>
						<option value="QUOTE_COMPONENT">引用组件</option>
					</select> <label>状态</label> <select class="form-control" id="lifecycle">
						<option value="">--</option>
						<option value="1">可用</option>
						<option value="0">禁用</option>
					</select>
					<button type="button" id="search" class="btn btn-info btn-sm">搜索</button>
				</form>
				<br />
				<h4 class="card-title">组件列表</h4>
				<table id="component_list_table" class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>组件名称</th>
							<th>组件编码</th>
							<th>描述</th>
							<th>状态</th>
							<th>类型</th>
							<th>创建时间</th>
							<th>修改时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

		<!-- 创建组件模态框 -->
		<div class="modal fade" id="modal-component-add">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">创建组件</h4>
						<input type="hidden" class="input_add_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label>编码</label> <input class="form-control input_add_code"
									type="text" />
							</div>
							<div class="form-group">
								<label>名称</label> <input class="form-control input_add_name"
									type="text" />
							</div>
							<div class="form-group">
								<label>描述</label>
								<textarea class="form-control input_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>类型</label> <select class="form-control input_add_type">
									<option value="">--</option>
									<option value="INSTANCE_COMPONENT">实例组件</option>
									<option value="QUOTE_COMPONENT">引用组件</option>
								</select>
							</div>
							<div class="form-group">
								<label>状态</label> <select
									class="form-control input_add_lifecycle">
									<option value="1">可用</option>
									<option value="0">禁用</option>
								</select>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_component">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>

				</div>
			</div>
		</div>

		<!-- 修改组件模态框 -->
		<div class="modal fade" id="modal-component-modify">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">修改组件</h4>
						<input type="hidden" class="input_add_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label>编码</label> <input class="form-control input_add_code"
									type="text" />
							</div>
							<div class="form-group">
								<label>名称</label> <input class="form-control input_add_name"
									type="text" />
							</div>
							<div class="form-group">
								<label>描述</label>
								<textarea class="form-control input_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>类型</label> <select class="form-control input_add_type">
									<option value="">--</option>
									<option value="INSTANCE_COMPONENT">实例组件</option>
									<option value="QUOTE_COMPONENT">引用组件</option>
								</select>
							</div>
							<div class="form-group">
								<label>状态</label> <select
									class="form-control input_add_lifecycle">
									<option value="1">可用</option>
									<option value="0">禁用</option>
								</select>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_component">确认</button>
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