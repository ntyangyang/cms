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
<script type="text/javascript" charset="utf8" src="/js/property/list.js"></script>
<title>CMS</title>
</head>
<body>
	<div class="container-fluid">
		<h2>CMS后台</h2>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link"
				href="/cms/component/list.htm">组件管理</a></li>
			<li class="nav-item"><a class="nav-link" href="/cms/template/list.htm">模板管理</a></li>
			<li class="nav-item"><a class="nav-link" href="/cms/instance/list.htm">页面实例管理</a></li>
		</ul>
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">
					模块属性管理 <input type="hidden" id="componentId"
						value="${component.id}" />
					<button type="button"
						class="btn btn-outline-primary btn-sm addProperty">添加属性</button>
					<button type="button"
						class="btn btn-outline-secondary btn-sm return">返回</button>
				</h4>
				<table class="table table-bordered table-sm">
					<thead>
						<tr>
							<td><label>ID</label></td>
							<td><label>模块名称</label></td>
							<td><label>模块编码</label></td>
							<td><label>模块描述</label></td>
							<td><label>状态</label></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span>${component.id}</span></td>
							<td><span>${component.name}</span></td>
							<td><span>${component.code}</span></td>
							<td><span>${component.description}</span></td>
							<td><c:choose>
									<c:when test="${component.lifecycle eq '1'}">
										<span>可用</span>
									</c:when>
									<c:otherwise>
										<span>禁用</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</tbody>
				</table>
				<br />
				<h4 class="card-title">模块属性列表</h4>
				<table id="property_list_table" class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>属性名称</th>
							<th>描述</th>
							<th>属性key</th>
							<th>属性类型</th>
							<th>属性值类型</th>
							<th>状态</th>
							<th>序号</th>
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

		<!-- 添加属性模态框 -->
		<div class="modal fade" id="modal-property-add">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">添加属性</h4>
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
								<label>属性key</label> <input class="form-control input_add_key"
									type="text" />
							</div>
							<div class="form-group">
								<label>描述</label>
								<textarea class="form-control input_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>属性类型</label> <select class="form-control select_add_type">
									<option value="NORMAL_INPUT">一般文本输入</option>
									<option value="VIDEO_SELECT">视频选择</option>
									<option value="CHECK_RADIO">单选按钮</option>
									<option value="PULL_DOWN_SELECT">下拉菜单</option>
									<option value="IMAGE_SELECT">图片选择</option>
									<option value="QUOTE_COMPONENT_SELECT">引用组件</option>
								</select> <label>属性值类型</label> <select
									class="form-control select_add_param_type">
									<option value="STRING_NORMAL">普通字符串</option>
									<option value="STRING_URL">链接字符串</option>
									<option value="INTEGER">整数</option>
									<option value="TIMESTAMP">时间戳</option>
								</select>
							</div>
							<div class="form-group add_expand">
								<div class="card">
									<div class="card-body">
										<label>扩展参数</label> <select class="select_component_bind"
											style="display: none;">
											<c:forEach var="component" items="${otherComponents}">
												<option value="${component.id }">${component.name}(${component.code})</option>
											</c:forEach>
										</select>
										<div class="div_param_option_add">
											<a href="javascript:void(0);" class="add_option">添加</a>
											<ul class="ul_param_option_add">
												<li><input type="text" placeholder="选项名"
													class="param_option_name" /><input type="text"
													placeholder="选项值" class="param_option_value" /> <a
													href="javascript:void(0);" class="delete_option">删除</a></li>
											</ul>
										</div>
										<div class="cycle_index">
											<label>循环次数</label> <select class="select_cycle_index"
												mandatory="true">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
												<option>10</option>
												<option>20</option>
												<option>50</option>
												<option>100</option>
												<option>200</option>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div class="form-group">
								<label>状态</label> <select
									class="form-control select_add_lifecycle">
									<option value="1">可用</option>
									<option value="0">禁用</option>
								</select>
							</div>
							<div class="form-group">
								<label>排序</label> <input class="form-control input_add_order"
									type="text" />
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_property">确认</button>
						&nbsp;
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
					</div>

				</div>
			</div>
		</div>

		<!-- 修改属性模态框 -->
		<div class="modal fade" id="modal-property-modify">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- 模态框头部 -->
					<div class="modal-header">
						<h4 class="modal-title">修改属性</h4>
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
								<label>属性key</label> <input class="form-control input_add_key"
									type="text" />
							</div>
							<div class="form-group">
								<label>描述</label>
								<textarea class="form-control input_add_description"></textarea>
							</div>
							<div class="form-group">
								<label>属性类型</label> <select class="form-control select_add_type">
									<option value="NORMAL_INPUT">一般文本输入</option>
									<option value="VIDEO_SELECT">视频选择</option>
									<option value="CHECK_RADIO">单选按钮</option>
									<option value="PULL_DOWN_SELECT">下拉菜单</option>
									<option value="IMAGE_SELECT">图片选择</option>
									<option value="QUOTE_COMPONENT_SELECT">引用组件</option>
								</select> <label>属性值类型</label> <select
									class="form-control select_add_param_type">
									<option value="STRING_NORMAL">普通字符串</option>
									<option value="STRING_URL">链接字符串</option>
									<option value="INTEGER">整数</option>
									<option value="TIMESTAMP">时间戳</option>
								</select>
							</div>
							<div class="form-group add_expand">
								<div class="card">
									<div class="card-body">
										<label>扩展参数</label> <select class="select_component_bind"
											style="display: none;">
											<c:forEach var="component" items="${otherComponents}">
												<option value="${component.id }">${component.name}(${component.code})</option>
											</c:forEach>
										</select>
										<div class="div_param_option_add">
											<a href="javascript:void(0);" class="add_option">添加</a>
											<ul class="ul_param_option_add">
												<li><input type="text" placeholder="选项名"
													class="param_option_name" /><input type="text"
													placeholder="选项值" class="param_option_value" /> <a
													href="javascript:void(0);" class="delete_option">删除</a></li>
											</ul>
										</div>
										<div class="cycle_index">
											<label>循环次数</label> <select class="select_cycle_index"
												mandatory="true">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
												<option>10</option>
												<option>20</option>
												<option>50</option>
												<option>100</option>
												<option>200</option>
											</select>
										</div>
									</div>
								</div>

							</div>
							<div class="form-group">
								<label>状态</label> <select
									class="form-control select_add_lifecycle">
									<option value="1">可用</option>
									<option value="0">禁用</option>
								</select>
							</div>
							<div class="form-group">
								<label>排序</label> <input class="form-control input_add_order"
									type="text" />
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success confirm_property">确认</button>
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