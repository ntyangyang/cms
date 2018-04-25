$(function() {
	var _dataTable = $("#component_list_table").DataTable({
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
			"url" : "/cms/component/page.json",
			"type" : "POST",
			"data" : function(d) {
				d.name = $("#name").val();
				d.code = $("#code").val();
				d.type = $("#type option:selected").val();
				d.lifecycle = $("#lifecycle option:selected").val();
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
			"data" : "code"
		}, {
			"sWidth" : "15%",
			"data" : "description"
		},{
			"sWidth" : "10%",
			"data" : "lifecycle",
			render: function (data, type, row, meta) {
				return formatLifecycle(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "type",
			render: function (data, type, row, meta) {
				return formatType(data);
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
	
	// 创建组件
	$(".addComponent").on("click", function() {
		$("#modal-component-add .input_add_id").val("");
		$("#modal-component-add .input_add_code").val("");
		$("#modal-component-add .input_add_name").val("");
		$("#modal-component-add .input_add_description").val("");
		$("#modal-component-add .input_add_type").prop("selectedIndex", 0)
		$("#modal-component-add .input_add_lifecycle").prop("selectedIndex", 0);
		$("#modal-component-add").modal("show");
	})
	
	// 编辑组件
	$("#component_list_table").on("click", ".component_modify", function() {
		var id = $(this).attr("val");
		$.post("/cms/component/findComponentById.json", {componentId:id}, function(data) {
			if (data) {
				$("#modal-component-modify .input_add_id").val(data.id);
				$("#modal-component-modify .input_add_code").val(data.code);
				$("#modal-component-modify .input_add_name").val(data.name);
				$("#modal-component-modify .input_add_description").val(data.description);
				$("#modal-component-modify .input_add_type option[value='"+data.type+"']").prop("selected", true);
				$("#modal-component-modify .input_add_lifecycle option[value='"+data.lifecycle+"']").prop("selected", true);
				$("#modal-component-modify").modal("show");
			} 
		});
	});
	
	// 保存创建的组件
	$("#modal-component-add .confirm_component").on("click", function() {
		var url = "/cms/component/save.json";
		var param = $.param({
			code : $("#modal-component-add .input_add_code").val(),
			name : $("#modal-component-add .input_add_name").val(),
			description : $("#modal-component-add .input_add_description").val(),
			type : $("#modal-component-add .input_add_type option:selected").val(),
			lifecycle : $("#modal-component-add .input_add_lifecycle option:selected").val(),
		});
		$.post(url, param, function(data) {
			if (data == true) {
				$("#modal-component-add").modal("hide");
				_dataTable.ajax.reload();
				alert("新建成功");
			} else if (data == false) {
				alert("新建失败");
			}
		});
	});
	
	// 保存修改的组件
	$("#modal-component-modify .confirm_component").on("click", function() {
		var url = "/cms/component/save.json";
		var param = $.param({
			id : $("#modal-component-modify .input_add_id").val(),
			code : $("#modal-component-modify .input_add_code").val(),
			name : $("#modal-component-modify .input_add_name").val(),
			description : $("#modal-component-modify .input_add_description").val(),
			type : $("#modal-component-modify .input_add_type option:selected").val(),
			lifecycle : $("#modal-component-modify .input_add_lifecycle option:selected").val(),
		});
		$.post(url, param, function(data) {
			if (data == true) {
				$("#modal-component-modify").modal("hide");
				_dataTable.ajax.reload();
				alert("修改成功");
			} else if (data == false) {
				alert("修改失败");
			}
		});
	});
	
	//组件删除
	$("#component_list_table").on("click", ".component_delete", function(){
		if(!confirm("确定要删除吗?")){
			return;
		}
		var url = "/cms/component/delete.json";
		var id = $(this).attr("val");
		$.post(url, {componentId:id}, function(data) {
			if (data == true) {
				_dataTable.ajax.reload();
				alert("删除成功");
			} else if (data == false) {
				alert("删除失败");
			}
		});
	});
	
})

function drawOperate(data) {
	return "<a href='/cms/property/list.htm?componentId="+ data + "' class='btn btn-outline-info btn-sm property_manage'>属性管理</a>" 
			+ "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm component_modify'>修改</a>"
			+ "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm component_delete'>删除</a>"
			+ "<a href='javascript:void(0);' val='" + data + "' class='btn btn-outline-info btn-sm component_preview'>预览</a>";
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
	if (data == "INSTANCE_COMPONENT") {
		return "实例组件";
	} else if (data == "QUOTE_COMPONENT") {
		return "引用组件";
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