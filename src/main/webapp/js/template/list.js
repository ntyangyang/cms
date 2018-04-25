$(function() {
	var _dataTable = $("#template_list_table").DataTable({
		"searching" : false,
		"info" : true,
		"autoWidth" : false,
		"serverSide" : true,
		"bSort" : false,
		"processing": true,
		"sPaginationType" : "full",
		"oLanguage" : {// 国际化配置
			"sLengthMenu" : "显示 _MENU_ 条",
			"sInfo" : "当前数据为第 <b>_START_ 到 _END_</b>条记录 总记录数为_TOTAL_条",
			"sSearch" : "搜索",
			"sZeroRecords" : "没有符合条件的数据",
			"sProcessing" : "正在获取数据，请稍后...",
			"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered" : "(全部记录数 _TOTAL_ 条)",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "最后一页"
			}
		},
		"ajax" : {
			"url" : "/cms/template/page.json",
			"type" : "POST",
			"data" : function(d) {
				d.name = $("#name").val();
				d.lifecycle = $("#lifecycle").val();
			}
		},
		"columns" : [ {
			"sWidth" : "5%",
			"data" : "id"
		}, {
			"sWidth" : "10%",
			"data" : "name"
		}, {
			"sWidth" : "10%",
			"data" : "description"
		}, {
			"sWidth" : "10%",
			"data" : "type",
			render: function (data, type, row, meta) {
				return formatType(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "partitionNum"
		}, {
			"sWidth" : "10%",
			"data" : "lifecycle",
			render: function (data, type, row, meta) {
				return formatLifecycle(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "createTime",
			render: function (data, type, row, meta) {
				return formatDate(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "modifyTime",
			render: function (data, type, row, meta) {
				return formatDate(data);
			}
		}, {
			"sWidth" : "20%",
			"data" : "id",
			render: function (data, type, row, meta) {
				return drawOperate(data);
			}
		} ]
	});

	// 搜索
	$("#search").on("click", function() {
		_dataTable.ajax.reload();
	})
	
	// 创建模板
	$(".addTemplate").on("click", function() {
		$("#modal-template-add .input_add_id").val("");
		$("#modal-template-add .input_add_name").val("");
		$("#modal-template-add .input_add_description").val("");
		$("#modal-template-add .input_add_partition").val("");
		$("#modal-template-add .input_add_lifecycle").prop("selectedIndex", 0);
		$("#modal-template-add .input_add_type").prop("selectedIndex", 0);
		$("#modal-template-add").modal("show");
	})
	
	// 编辑组件
	$("#template_list_table").on("click", ".template_modify", function() {
		var id = $(this).attr("val");
		$.post("/cms/template/findTemplateById.json", {templateId:id}, function(data) {
			if (data) {
				$("#modal-template-modify .input_add_id").val(data.id);
				$("#modal-template-modify .input_add_partition").val(data.partitionNum);
				$("#modal-template-modify .input_add_name").val(data.name);
				$("#modal-template-modify .input_add_description").val(data.description);
				$("#modal-template-modify .input_add_type option[value='"+data.type+"']").prop("selected", true);
				$("#modal-template-modify .input_add_lifecycle option[value='"+data.lifecycle+"']").prop("selected", true);
				$("#modal-template-modify").modal("show");
			} 
		});
	});
	
	// 保存创建的模板
	$("#modal-template-add .confirm_template").on("click", function() {
		var url = "/cms/template/save.json";
		var param = $.param({
			id : $("#dialog-template-add .input_add_id").val(),
			name : $("#modal-template-add .input_add_name").val(),
			description : $("#modal-template-add .input_add_description").val(),
			type : $("#modal-template-add .input_add_type option:selected").val(),
			lifecycle : $("#modal-template-add .input_add_lifecycle option:selected").val(),
			partitionNum : $("#modal-template-add .input_add_partition").val()
		});
		$.post(url, param, function(data) {
			if (data == true) {
				$("#modal-template-add").modal("hide");
				_dataTable.ajax.reload();
				alert("新建成功");
			} else if (data == false) {
				alert("新建失败");
			}
		});
	});
	
	// 保存修改的组件
	$("#modal-template-modify .confirm_template").on("click", function() {
		var url = "/cms/template/save.json";
		var param = $.param({
			id : $("#modal-template-modify .input_add_id").val(),
			partitionNum : $("#modal-template-modify .input_add_partition").val(),
			name : $("#modal-template-modify .input_add_name").val(),
			description : $("#modal-template-modify .input_add_description").val(),
			type : $("#modal-template-modify .input_add_type option:selected").val(),
			lifecycle : $("#modal-template-modify .input_add_lifecycle option:selected").val(),
		});
		$.post(url, param, function(data) {
			if (data == true) {
				$("#modal-template-modify").modal("hide");
				_dataTable.ajax.reload();
				alert("修改成功");
			} else if (data == false) {
				alert("修改失败");
			}
		});
	});
	
	//模板删除
	$("#template_list_table").on("click", ".template_delete", function(){
		if(!confirm("确定要删除吗?")){
			return;
		}
		var url = "/cms/template/delete.json";
		var id = $(this).attr("val");
		$.post(url, {templateId:id}, function(data) {
			if (data == true) {
				_dataTable.ajax.reload();
				alert("删除成功");
			} else if (data == false) {
				alert("删除失败");
			}
		});
	});
	
	$("#template_list_table").on("click", ".component_bind", function() {
		var templateId = $(this).attr("val");
		var componentsIds;
		$.post("/cms/template/findComponentsByTemplateId.json", {
			templateId : templateId
		}, function(data) {
			componentsIds = data;
			$("#modal-component-bind").modal("show");
			$("#template_id").val(templateId);

			$.post("/cms/component/findComponentsExcludeById.json", {}, function(data) {
				if (data) {
					var li = "";
					for ( var i in data) {
						if ($.inArray(data[i].id, componentsIds) != -1) {
							li = li + "<li><input type='checkbox' checked='checked' value='" + data[i].id + "'/>" + data[i].name + "</li>";
						} else {
							li = li + "<li><input type='checkbox' value='" + data[i].id + "'/>" + data[i].name + "</li>";
						}
					}
					$("#modal-component-bind .div_bind_component").empty().html("<ul>" + li + "</ul>");
				}
			});
		});
	});
	
	$("#modal-component-bind .div_bind_component").on("click", "input[type='checkbox']", function() {
		var componentId = $(this).val();
		var templateId = $("#template_id").val();
		$.post("/cms/template/bindComponent.json", {
			componentId : componentId,
			templateId : templateId,
			type : $(this).prop("checked")
		}, function(data) {
		});
	});
	
})

function drawOperate(data) {
	return "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm component_bind'>绑定模块</a>"
			+ "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm template_modify'>修改模板</a>"
			+ "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm template_delete'>删除模板</a>";
}

//获取日期格式
function formatDate(val) {
	if (val == null || val == '') {
		return "&nbsp;";
	} else {
		var date = new Date(val);
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
	}
}

function formatType(data) {
	if (data == "HOMEPAGE") {
		return "主页";
	} else if (data == "CATEGORY") {
		return "分类";
	} else if (data == "LOOKS") {
		return "系列";
	} else if (data == "SHOWS") {
		return "展台";
	}
}

function formatLifecycle(data) {
	if (data == 0) {
		return "禁用";
	} 
	if (data == 1) {
		return "可用";
	}
	
}