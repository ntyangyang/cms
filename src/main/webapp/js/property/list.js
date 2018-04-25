$(function() {
	var _dataTable = $("#property_list_table").DataTable({
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
			"url" : "/cms/property/page.json",
			"type" : "POST",
			"data" : function(d) {
				d.componentId = $("#componentId").val();
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
			"data" : "keyword"
		}, {
			"sWidth" : "10%",
			"data" : "type",
			render : function(data, type, row, meta) {
				return formatType(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "paramType",
			render : function(data, type, row, meta) {
				return formatParamType(data);
			}
		}, {
			"sWidth" : "5%",
			"data" : "lifecycle",
			render : function(data, type, row, meta) {
				return formatLifecycle(data);
			}
		}, {
			"sWidth" : "5%",
			"data" : "orderNum"
		}, {
			"sWidth" : "10%",
			"data" : "createTime",
			render : function(data, type, row, meta) {
				return formatDate(data);
			}
		}, {
			"sWidth" : "10%",
			"data" : "modifyTime",
			render : function(data, type, row, meta) {
				return formatDate(data);
			}
		}, {
			"sWidth" : "20%",
			"data" : "id",
			render : function(data, type, row, meta) {
				return drawOperate(data);
			}
		} ]
	});

	// 返回
	$(".return").on("click", function() {
		window.location.href = "/cms/component/list.htm";
	})

	// 属性类型change事件
	$("#modal-property-add .select_add_type")
			.on(
					"change",
					function() {
						if ($(this).find("option:selected").val() == 'NORMAL_INPUT') {
							$(".add_expand").hide();
						}

						if ($(this).find("option:selected").val() == 'VIDEO_SELECT'
								|| $(this).find("option:selected").val() == 'IMAGE_SELECT') {
							$(".add_expand").hide();
						}

						if ($(this).find("option:selected").val() == 'CHECK_RADIO'
								|| $(this).find("option:selected").val() == 'PULL_DOWN_SELECT') {
							$(".add_expand").show();
							$("#modal-property-add .div_param_option_add")
									.show();
							$("#modal-property-add .select_component_bind")
									.hide();
							$("#modal-property-add .cycle_index").hide();
						}

						if ($(this).find("option:selected").val() == 'QUOTE_COMPONENT_SELECT') {
							$(".add_expand").show();
							$("#modal-property-add .div_param_option_add")
									.hide();
							$("#modal-property-add .select_component_bind")
									.show();
							$("#modal-property-add .cycle_index").show();
						}
					});

	$("#modal-property-modify .select_add_type")
			.on(
					"change",
					function() {
						if ($(this).find("option:selected").val() == 'NORMAL_INPUT') {
							$(".add_expand").hide();
						}

						if ($(this).find("option:selected").val() == 'VIDEO_SELECT'
								|| $(this).find("option:selected").val() == 'IMAGE_SELECT') {
							$(".add_expand").hide();
						}

						if ($(this).find("option:selected").val() == 'CHECK_RADIO'
								|| $(this).find("option:selected").val() == 'PULL_DOWN_SELECT') {
							$(".add_expand").show();
							$("#modal-property-modify .div_param_option_add")
									.show();
							$("#modal-property-modify .select_component_bind")
									.hide();
							$("#modal-property-modify .cycle_index").hide();
						}

						if ($(this).find("option:selected").val() == 'QUOTE_COMPONENT_SELECT') {
							$(".add_expand").show();
							$("#modal-property-modify .div_param_option_add")
									.hide();
							$("#modal-property-modify .select_component_bind")
									.show();
							$("#modal-property-modify .cycle_index").show();
						}
					});

	// 下拉菜单和单选按钮选项新增删除
	$("#modal-property-add .div_param_option_add .add_option")
			.on(
					"click",
					function() {
						$(
								"#modal-property-add .div_param_option_add .ul_param_option_add li:first")
								.clone()
								.appendTo(
										$("#modal-property-add .div_param_option_add .ul_param_option_add"));
					});

	$("#modal-property-add .div_param_option_add")
			.on(
					"click",
					".delete_option",
					function() {
						if ($("#modal-property-add .ul_param_option_add li").length > 1) {
							$(this).parent("li").remove();
						}
					});

	$("#modal-property-modify .div_param_option_add .add_option")
			.on(
					"click",
					function() {
						$(
								"#modal-property-modify .div_param_option_add .ul_param_option_add li:first")
								.clone()
								.appendTo(
										$("#modal-property-modify .div_param_option_add .ul_param_option_add"));
					});

	$("#modal-property-modify .div_param_option_add")
			.on(
					"click",
					".delete_option",
					function() {
						if ($("#modal-property-modify .ul_param_option_add li").length > 1) {
							$(this).parent("li").remove();
						}
					});

	// 新增属性
	$(".addProperty")
			.on(
					"click",
					function() {
						$("#modal-property-add .input_add_name").val("");
						$("#modal-property-add .input_add_key").val("");
						$("#modal-property-add .input_add_description").val("");

						$("#modal-property-add .select_add_type").prop(
								"selectedIndex", 0).trigger("change");
						$("#modal-property-add .select_add_param_type").prop(
								"selectedIndex", 0);
						$("#modal-property-add .select_component_bind").prop(
								"selectedIndex", 0);

						$("#modal-property-add .select_add_lifecycle").prop(
								"selectedIndex", 0);
						$("#modal-property-add .input_add_order").val("");

						// 扩展参数初始化
						$("#modal-property-add .select_component_bind").prop(
								"selectedIndex", 0);
						$("#modal-property-add .select_cycle_index").prop(
								"selectedIndex", 0);
						$(
								"#modal-property-add .div_param_option_add .ul_param_option_add")
								.empty()
								.html(
										"<li><input type='text' placeholder='选项名' class='param_option_name'/><input type='text' placeholder='选项值'class='param_option_value'/><a href='javascript:void(0);' class='delete_option'>删除</a></li>")
						$("add_expand").hide();

						$("#modal-property-add").modal("show");
					})

	// 新增属性保存
	$("#modal-property-add .confirm_property")
			.on(
					"click",
					function() {
						var url = "/cms/property/save.json";
						var type = $(
								"#modal-property-add .select_add_type option:selected")
								.val();
						var paramType = $(
								"#modal-property-add .select_add_param_type option:selected")
								.val();
						var cycleIndex = 1;
						var expand = "";
						if (type == 'QUOTE_COMPONENT_SELECT') {
							expand = $(
									"#modal-property-add .select_component_bind option:selected")
									.val();
							cycleIndex = $(
									"#modal-property-add .select_cycle_index option:selected")
									.val();
						} else if (type == 'CHECK_RADIO'
								|| type == 'PULL_DOWN_SELECT') {
							var options = new Array();
							var i = 0;
							$("#modal-property-add .ul_param_option_add li")
									.each(
											function() {
												var option = new Object();
												option.name = $(this).find(
														".param_option_name")
														.val();
												option.value = $(this).find(
														".param_option_value")
														.val();
												options[i] = option;
												i++;
											});
							expand = JSON.stringify(options);
						} else {
							cycleIndex = 1;
							expand = "";
						}

						var param = $
								.param({
									componentId : $("#componentId").val(),
									name : $(
											"#modal-property-add .input_add_name")
											.val(),
									type : type,
									paramType : paramType,
									expand : expand,
									cycleIndex : cycleIndex == "" ? 1
											: parseInt(cycleIndex),
									description : $(
											"#modal-property-add .input_add_description")
											.val(),
									keyword : $(
											"#modal-property-add .input_add_key")
											.val(),
									lifecycle : $(
											"#modal-property-add .select_add_lifecycle option:selected")
											.val(),
									orderNum : $(
											"#modal-property-add .input_add_order")
											.val()
								});
						$.post(url, param, function(data) {
							if (data == true) {
								$("#modal-property-add").modal("hide");
								_dataTable.ajax.reload();
								alert("保存成功");
							} else if (data == false) {
								alert("保存失败");
							}
						});
					});
	
	// 修改属性
	$("#property_list_table").on("click", ".property_modify", function() {
		var id = $(this).attr("val");
		$.post("/cms/property/findPropertyById.json", {
			propertyId : id
		}, function(data) {
			if (data) {
				$("#modal-property-modify .input_add_id").val(data.id);
				$("#modal-property-modify .input_add_name").val(data.name);
				$("#modal-property-modify .input_add_key").val(data.keyword);
				$("#modal-property-modify .select_add_type option[value='" + data.type + "']").prop("selected", true);
				$("#modal-property-modify .input_add_description").val(data.description);
				$("#modal-property-modify .select_add_param_type option[value='" + data.paramType + "']").prop("selected", true);
				$("#modal-property-modify .select_add_lifecycle option[value='" + data.lifecycle + "']").prop("selected", true);
				$("#modal-property-modify .input_add_order").val(data.orderNum);
				
				$("#modal-property-modify .select_component_bind option:first").prop("selected", true);
				$("#modal-property-modify .select_cycle_index option:first").prop("selected", true);
				$("#modal-property-modify .div_param_option_add .ul_param_option_add").empty().html(
						"<li><input type='text' placeholder='选项名' class='param_option_name'/><input type='text' placeholder='选项值'class='param_option_value'/><a href='javascript:void(0);' class='delete_option'>删除</a></li>")
				$(".add_expand").hide();		
				
				if (data.type == 'NORMAL_INPUT') {
					$(".add_expand").hide();
				}
				
				if (data.type== 'VIDEO_SELECT' || data.type == 'IMAGE_SELECT') {
					$(".add_expand").hide();
				}

				if (data.type == 'CHECK_RADIO' || data.type == 'PULL_DOWN_SELECT') {
					$(".add_expand").show();		
					$("#modal-property-modify .div_param_option_add").show();
					$("#modal-property-modify .select_component_bind").hide();
					$("#modal-property-modify .cycle_index").hide();
					$("#modal-property-modify .div_param_option_add .ul_param_option_add").empty();
					var optionData = eval("("+data.expand+")");
					for(var i in optionData){
						$("<li><input type='text' placeholder='选项名' class='param_option_name' value='"+optionData[i].name+"'/><input type='text' placeholder='选项值'class='param_option_value' value='"+optionData[i].value+"'/><a href='javascript:void(0);' class='delete_option'>删除</a></li>").appendTo($("#modal-property-modify .div_param_option_add .ul_param_option_add"));
					}
				}

				if (data.type == 'QUOTE_COMPONENT_SELECT') {
					$(".add_expand").show();		
					$("#modal-property-modify .div_param_option_add").hide();
					$("#modal-property-modify .select_component_bind").show();
					$("#modal-property-modify .cycle_index").show();
					
					$("#modal-property-modify .select_component_bind option[value='" + data.expand + "']").prop("selected", true);
					$("#modal-property-modify .select_cycle_index option[value=" + data.cycleIndex + "]").prop("selected", true);
				}

				$("#modal-property-modify").modal("show");
			}
		});
	});

	// 修改属性保存
	$("#modal-property-modify .confirm_property")
			.on(
					"click",
					function() {
						var url = "/cms/property/save.json";
						var type = $(
								"#modal-property-modify .select_add_type option:selected")
								.val();
						var paramType = $(
								"#modal-property-modify .select_add_param_type option:selected")
								.val();
						var cycleIndex = 1;
						var expand = "";
						if (type == 'QUOTE_COMPONENT_SELECT') {
							expand = $(
									"#modal-property-modify .select_component_bind option:selected")
									.val();
							cycleIndex = $(
									"#modal-property-modify .select_cycle_index option:selected")
									.val();
						} else if (type == 'CHECK_RADIO'
								|| type == 'PULL_DOWN_SELECT') {
							var options = new Array();
							var i = 0;
							$("#modal-property-modify .ul_param_option_add li")
									.each(
											function() {
												var option = new Object();
												option.name = $(this).find(
														".param_option_name")
														.val();
												option.value = $(this).find(
														".param_option_value")
														.val();
												options[i] = option;
												i++;
											});
							expand = JSON.stringify(options);
						} else {
							cycleIndex = 1;
							expand = "";
						}

						var param = $
								.param({
									id : $(
											"#modal-property-modify .input_add_id")
											.val(),
									name : $(
											"#modal-property-modify .input_add_name")
											.val(),
									type : type,
									paramType : paramType,
									expand : expand,
									cycleIndex : cycleIndex == "" ? 1
											: parseInt(cycleIndex),
									description : $(
											"#modal-property-modify .input_add_description")
											.val(),
									keyword : $(
											"#modal-property-modify .input_add_key")
											.val(),
									lifecycle : $(
											"#modal-property-modify .select_add_lifecycle option:selected")
											.val(),
									orderNum : $(
											"#modal-property-modify .input_add_order")
											.val()
								});
						$.post(url, param, function(data) {
							if (data == true) {
								$("#modal-property-modify").modal("hide");
								_dataTable.ajax.reload();
								alert("修改成功");
							} else if (data == false) {
								alert("修改失败");
							}
						});
					});

	// 保存创建的组件
	$("#modal-component-add .confirm_component")
			.on(
					"click",
					function() {
						var url = "/cms/component/save.json";
						var param = $
								.param({
									code : $(
											"#modal-component-add .input_add_code")
											.val(),
									name : $(
											"#modal-component-add .input_add_name")
											.val(),
									description : $(
											"#modal-component-add .input_add_description")
											.val(),
									type : $(
											"#modal-component-add .input_add_type option:selected")
											.val(),
									lifecycle : $(
											"#modal-component-add .input_add_lifecycle option:selected")
											.val(),
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

	// 属性删除
	$("#property_list_table").on("click", ".property_delete", function() {
		if (!confirm("确定要删除吗?")) {
			return;
		}
		var url = "/cms/property/delete.json";
		var id = $(this).attr("val");
		$.post(url, {
			propertyId : id
		}, function(data) {
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
	return "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm property_modify'>修改</a>"
			+ "<a href='javascript:void(0);' val='" + data
			+ "' class='btn btn-outline-info btn-sm property_delete'>删除</a>";
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
	if (data == "NORMAL_INPUT") {
		return "一般文本";
	} else if (data == "VIDEO_SELECT") {
		return "视频";
	} else if (data == "CHECK_RADIO") {
		return "单选按钮";
	} else if (data == "PULL_DOWN_SELECT") {
		return "下拉菜单";
	} else if (data == "IMAGE_SELECT") {
		return "图片";
	} else if (data == "QUOTE_COMPONENT_SELECT") {
		return "引用组件";
	}
}

function formatParamType(data) {
	if (data == "STRING_NORMAL") {
		return "普通字符串";
	} else if (data == "STRING_URL") {
		return "链接字符串";
	} else if (data == "INTEGER") {
		return "整数";
	} else if (data == "TIMESTAMP") {
		return "时间戳";
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