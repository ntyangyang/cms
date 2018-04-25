$(function() {
	var _dataTable = $("#instance_list_table").DataTable({
		"searching" : false,
		"info" : true,
		"autoWidth" : false,
		"serverSide" : true,
		"bSort" : false,
		"processing" : true,
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
			"url" : "/cms/instance/page.json",
			"type" : "POST",
			"data" : function(d) {
				d.url = $("#url").val();
				d.pageType = $("#pageType").val();
				d.lifecycle = $("#lifecycle option:selected").val();
			}
		},
		"columns" : [ {
			"sWidth" : "5%",
			"data" : "id"
		}, {
			"sWidth" : "5%",
			"data" : "url"
		}, {
			"sWidth" : "10%",
			"data" : "templateName"
		}, {
			"sWidth" : "5%",
			"data" : "pageType",
			render : function(data, type, row, meta) {
				return formatType(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "description"
		}, {
			"sWidth" : "10%",
			"data" : "lastModifyTime",
			render : function(data, type, row, meta) {
				return formatDate(data);
			}
		}, {
			"sWidth" : "5%",
			"data" : "lifecycle",
			render : function(data, type, row, meta) {
				return formatLifecycle(data);
			}
		}, {
			"sWidth" : "30%",
			"data" : "id",
			render : function(data, type, row, meta) {
				return drawOperate(data);
			}
		} ]
	});

	// 搜索
	$("#search").on("click", function() {
		_dataTable.ajax.reload();
	})

	// 新建页面实例
	$(".addInstance").on("click", function() {
		$("#modal-instance-add .textarea_add_description").val("");
		$("#modal-instance-add .input_add_url").val("");
		$("#modal-instance-add .select_add_template").prop("selectedIndex", 0)
		$("#modal-instance-add").modal("show");
	})

	// 保存新建的页面实例
	$("#modal-instance-add .confirm_instance")
			.on(
					"click",
					function() {
						var url = "/cms/instance/save.json";
						var param = $
								.param({
									url : $(
											"#modal-instance-add .input_add_url")
											.val(),
									templateId : $(
											"#modal-instance-add .select_add_template option:selected")
											.val(),
									description : $(
											"#modal-instance-add .textarea_add_description")
											.val(),
								});
						$.post(url, param, function(data) {
							if (data == true) {
								$("#modal-instance-add").modal("hide");
								_dataTable.ajax.reload();
								alert("新建成功");
							} else if (data == false) {
								alert("新建失败");
							}
						});
					});

	// 编辑页面实例
	$("#instance_list_table")
			.on(
					"click",
					".instance_modify",
					function() {
						var id = $(this).attr("val");
						$
								.post(
										"/cms/instance/findById.json",
										{
											instanceId : id
										},
										function(data) {
											if (data.success == true) {
														$(
																"#modal-instance-modify .input_add_id")
																.val(data.id),
														$(
																"#modal-instance-modify .textarea_add_description")
																.val(
																		data.description);
												$(
														"#modal-instance-modify .input_add_url")
														.val(data.url);
														$(
																"#modal-instance-modify .select_add_template option[value="
																		+ data.id
																		+ "]")
																.prop(
																		"selected",
																		true),
														$(
																"#modal-instance-modify")
																.modal("show");
											}
										});
					});

	// 保存修改的页面实例
	$("#modal-instance-modify .confirm_instance")
			.on(
					"click",
					function() {
						var url = "/cms/instance/save.json";
						var param = $
								.param({
									id : $(
											"#modal-instance-modify .input_add_id")
											.val(),
									url : $(
											"#modal-instance-modify .input_add_url")
											.val(),
									description : $(
											"#modal-instance-modify .textarea_add_description")
											.val(),
									templateId : $(
											"#modal-instance-modify .select_add_template option:selected")
											.val(),
								});
						$.post(url, param, function(data) {
							if (data == true) {
								$("#modal-instance-modify").modal("hide");
								_dataTable.ajax.reload();
								alert("修改成功");
							} else if (data == false) {
								alert("修改失败");
							}
						});
					});

	// 页面实例删除
	$("#instance_list_table").on("click", ".instance_delete", function() {
		if (!confirm("确定要删除吗?")) {
			return;
		}
		var url = "/cms/instance/delete.json";
		var id = $(this).attr("val");
		$.post(url, {
			instanceId : id
		}, function(data) {
			if (data == true) {
				_dataTable.ajax.reload();
				alert("删除成功");
			} else if (data == false) {
				alert("删除失败");
			}
		});
	});

	// 页面实例预览
	$("#instance_list_table").on("click", ".instance_preview", function() {
		var url = "/cms/instance/preview.json";
		var id = $(this).attr("val");
		$.post(url, {
			instanceId : id,
			type : 'preview'
		}, function(data) {
			if (data.success == true) {
				window.open(data.destination, target = 'blank');
			} else {
				alert("预览失败");
			}
		});
	});

	// 页面实例发布
	$("#instance_list_table").on(
			"click",
			".instance_publish",
			function() {
				var id = $(this).attr("val");
				$.post("/cms/instance/findById.json", {
					instanceId : id
				}, function(data) {
					if (data.success == true) {
								$("#modal-instance-publish .input_instance_id")
										.val(id), $(
										"#modal-instance-publish .input_url")
										.val(data.url);

						$("#modal-instance-publish").modal("show");
					}
				});
			});

	// 确认实例发布
	$("#modal-instance-publish .confirm_instance").on("click", function() {
		var instanceId = $("#modal-instance-publish .input_instance_id").val();
		var url = $("#modal-instance-publish .input_url").val()
		var startTime = $("#input_start_time").val();
		var endTime = $("#input_end_time").val();
		$.post("/cms/instance/publish.json", {
			instanceId : instanceId,
			url : url,
			startTime : startTime,
			endTime : endTime
		}, function(data) {
			if (data.success == true) {
				$("#modal-instance-publish").modal("hide");
				alert("发布成功");
			} else if (data == false) {
				alert("发布失败");
			}
		});
	});

	$(".form_datetime").datetimepicker({
		format : 'yyyy-mm-dd',// 显示格式
		todayHighlight : 1,// 今天高亮
		minView : "month",// 设置只显示到月份
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		autoclose : 1
	// 选择后自动关闭
	});
})

function drawOperate(data) {
	return "<a href='/cms/instance/data/edit.htm?instanceId="
			+ data
			+ "' class='btn btn-outline-info btn-sm instance_data_modify'>编辑数据</a>"
			+ "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm instance_preview'>预览</a>"
			+ "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm instance_publish'>发布</a>"
			+ "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm instance_modify'>修改</a>"
			+ "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm instance_delete'>删除</a>";
}

// 获取日期格式
function formatDate(val) {
	if (val == null || val == '') {
		return "&nbsp;";
	} else {
		var date = new Date(val);
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate();
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