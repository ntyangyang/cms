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
<script type="text/javascript" charset="utf8" src="/js/template/list.js"></script>
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
					模板管理
					<button type="button"
						class="btn btn-outline-primary btn-sm addTemplate">新建模板</button>
				</h4>
				<form class="form-inline">
					<label>模板名称</label> <input type="text" class="form-control"
						placeholder="模板名称" id="name" /> <label>状态</label> <select
						class="form-control" id="lifecycle">
						<option value="">--</option>
						<option value="1">可用</option>
						<option value="0">禁用</option>
					</select>
					<button type="button" id="search" class="btn btn-info btn-sm">搜索</button>
				</form>
				<br />
				<h4 class="card-title">模板列表</h4>
				<table id="template_list_table" class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>模板名称</th>
							<th>描述</th>
							<th>模板类型</th>
							<th>分区数目</th>
							<th>状态</th>
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

		<!-- 创建模板模态框 -->
		<div class="modal fade" id="modal-template-add">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">创建模板</h4>
						<input type="hidden" class="input_add_id" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						<form>
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
									<option value="HOMEPAGE">主页</option>
									<option value="CATEGORY">分类</option>
									<option value="LOOKS">系列</option>
									<option value="SHOWS">展台</option>
								</select>
							</div>
							<div class="form-group">
								<label>分区数目</label> <input
									class="form-control input_add_partition" type="text" />
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
						<button type="button" class="btn btn-success confirm_template">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>

				</div>
			</div>
		</div>

		<!-- 修改模板模态框 -->
		<div class="modal fade" id="modal-template-modify">
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
								<label>名称</label> <input class="form-control input_add_name"
									type="text" />
							</div>
							<div class="form-group">
								<label>描述</label>
								<textarea class="form-control input_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>类型</label> <select class="form-control input_add_type">
									<option value="HOMEPAGE">主页</option>
									<option value="CATEGORY">分类</option>
									<option value="LOOKS">系列</option>
									<option value="SHOWS">展台</option>
								</select>
							</div>
							<div class="form-group">
								<label>分区数目</label> <input
									class="form-control input_add_partition" type="text" />
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
						<button type="button" class="btn btn-success confirm_template">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>

				</div>
			</div>
		</div>

		<!-- 绑定模块模态框 -->
		<div class="modal fade" id="modal-component-bind">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">绑定模块</h4>
						<input type="hidden" id="template_id" value="" />
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- 模态框主体 -->
					<div class="modal-body">
						选择模块:
						<div class="div_bind_component "></div>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">确认</button>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>