$(function() {
	// 默认选中第一个分区
	$("#top_partition_area ul li:first a").trigger("click");
	
	// 返回
	$(".return").on("click", function(){
		history.go(-1);
	})
	
	// 模块区拖拽功能
	$(".left_component").each(function(){
		var index = $(this).attr("index");
		$("#left_component_area" + index + " .component_alert").draggable({
			appendTo : "#middle_content_area" + index,
			helper : "clone",
			connectToSortable : "#middle_content_area" + index,
			opacity : 0.8,
			start : function(event, ui) {
				$(this).attr("componentInstanceId", (new Date()).generateUniqueCode("yyyyMMddhhmmssS"));
			}
		})
		
		// 模块实例排序
		$("#middle_content_area" + index).sortable({
			forcePlaceholderSize : true,
			opacity : 0.8,
			revert : true
		});
		
		// 模块实例点击事件
		$("#middle_content_area" + index).on("click", " .component_alert", function() {
			$("#middle_content_area" + index + " .component_alert").hide();
			$(this).show();
			var componentId = $(this).attr("componentId");
			var componentInstanceId = $(this).attr("componentInstanceId");

			// 判断
			if ($(".component_instance_div[componentInstanceId='" + componentInstanceId + "']").length == 0) {
				// 生成每个组件实例的编辑div
				var componentInstanceDiv = $("<div class='component_instance_div' componentId='" + componentId + "' componentInstanceId='" + componentInstanceId + "'></div>");
				componentInstanceDiv.html($("#component_modify_template_area .component_template_div[value='" + componentId + "']").html());
				$(componentInstanceDiv).appendTo($("#right_property_area" + index));
			}

			$(".component_instance_div").hide();
			$(".component_instance_div[componentInstanceId='" + componentInstanceId + "']").show();
		});
	});
	
	// 组件实例删除事件
	$(".middle_content").on("click", "button",function(e) {
		if (confirm("确认删除此组件实例吗")) {
			var componentInstanceId = $(this).parent("div").attr("componentInstanceId");
			$(".component_instance_div[componentInstanceId='" + componentInstanceId + "']").remove();

			$(this).parent("div").remove();

			var index = $("#top_partition_area li a.active").parent("li").attr("index");
			$("#middle_content_area" + index).find("div:first").trigger("click");
			
			// 阻止事件冒泡
			e.stopPropagation();
		} else {
			return false;
		}
	});
	
	
	// 加载上传组件
	$.getScript('/js/ajaxfileupload.js');
	
	// 初始化当前页面所有可用组件的编辑模板
	$(".left_component:first div").each(function(index, data) {
		var componentId = $(this).attr("componentId");
		var url = "/cms/property/findPropertyByComponentId.json";

		$.ajax({
			type : "post",
			url : url,
			data : $.param({
				componentId : componentId
			}),
			async : false,// 取消异步
			success : function(data) {
				$(generateComponentDiv(0, componentId, data)).appendTo($("#component_modify_template_area"));
				if ($(".left_component:first div").length == (index + 1)) {
					initInstanceData();
				}

				// 处理图片上传控件回调参数问题
				$(".right_property input[type='file']").each(function() {
					$(this).attr("hName", (new Date()).generateUniqueCode("yyyyMMddhhmmssS"));
				});
			}
		});
	});
	
	// 保存全部数据
	$(".saveAllInstanceData").on("click", function() {
		saveAllInstanceData();
	});
	
	// 上传图片
	
	$(".right_property").on("click", ".color-select-line .uploadlink", function() {
		
		var id = "img" + $(this).attr("name");
		$.ajaxFileUpload
        (
            {
                url: '/image/upload.json', // 用于文件上传的服务器端请求地址
                secureuri: false, // 是否需要安全协议，一般设置为false
                fileElementId: id, // 文件上传域的ID
                dataType: 'json', // 返回值类型 一般设置为json
                success: function (data, status)  // 服务器成功响应处理函数
                {
                	$(this).parents(".color-select-line:first").children(".input_image_upload").val(data.imgUrl);
                    if (typeof (data.success) != 'undefined') {
                        if (data.error != '') {
                            alert(data.error);
                        } else {
                            alert(data.msg);
                        }
                    }
                },
                error: function (data, status, e)// 服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
        return false;
	})
})

// 初始化已编辑数据
function initInstanceData() {
	if ($("#instanceValue").attr("json") == "") {
		return;// do nothing
	}
	var data = eval("(" + $("#instanceValue").attr("json").replace(/singlequote/g,"'") + ")");
	for ( var key in data) {
		for ( var i in data[key]) {
			var generateCode = (new Date()).generateUniqueCode("yyyyMMddhhmmssS");
			$("#left_component_area" + key + " div[componentcode='" + data[key][i].code + "']").clone(false).attr("componentInstanceId", generateCode).appendTo($("#middle_content_area" + key));
			$("#middle_content_area" + key + " div[componentInstanceId='" + generateCode + "']").trigger("click");
			setInstanceDataValue(0, $("#right_property_area" + key + " .component_instance_div[componentInstanceId='" + generateCode + "']").find("table").filter(":first"), data[key][i].data);
		}
	}
}

/**
 * 设置组件的编辑域数据
 */
function setInstanceDataValue(index, table, data) {
	$(table).children("tbody").children("tr").each(function() {
		var type = $(this).attr("type");
		var cycleIndex = $(this).attr("cycleIndex");
		if (type == 'NORMAL_INPUT') {
			if (undefined != data[$(this).attr("key")]) {
				$(this).find("input").val(data[$(this).attr("key")]);
			}
		}
		if (type == 'CHECK_RADIO') {
			if (undefined != data[$(this).attr("key")]) {
				$(this).find("input[value='" + data[$(this).attr("key")] + "']").prop("checked", true);
			}
		}
		if (type == 'PULL_DOWN_SELECT') {
			if (undefined != data[$(this).attr("key")]) {
				$(this).find("select option[value='" + data[$(this).attr("key")] + "']").prop("selected", true);
			}
		}
		if (type == 'IMAGE_SELECT') {
			if (undefined != data[$(this).attr("key")]) {
				$(this).find("input.input_image_upload").val(data[$(this).attr("key")]);
			}
		}
		if (type == 'VIDEO_SELECT') {
			if (undefined != data[$(this).attr("key")]) {
				$(this).find("input").val(data[$(this).attr("key")]);
			}
		}
		if (type == 'QUOTE_COMPONENT_SELECT') {
			if (undefined != data[$(this).attr("key")]) {
				if (index <= 2) {
					var cycleData = data[$(this).attr("key")];
					if (cycleIndex > 1) {
						var componentId = $(this).attr("componentid");
						var i = 0;
						$(this).find(".component_template_div[value='" + componentId + "']").each(function(j, data) {
							if(i < cycleData.length){
								setInstanceDataValue(index + 1, $(this).find("table").filter(":first"), cycleData[i]);
							}
							i++;	
						});
					} else {
						setInstanceDataValue(index + 1, $(this).find("table").filter(":first"), cycleData);
					}
				}
			}
		}
	});
}

/**
 * 生成组件的编辑域内容
 */
function generateComponentDiv(index, componentId, data) {
	var key = componentId;
	var div = $("<div class='component_template_div' value='" + key + "'></div>");
	var table = $("<table></table>");
	for ( var i in data[key]) {
		if (data[key][i].type == 'NORMAL_INPUT') {
			$("<tr key='" + data[key][i].keyword + "' type='" + data[key][i].type + "'><td><label>" + data[key][i].name + ":</label></td><td><input type='text' /></td></tr>").appendTo($(table));
		}
		if (data[key][i].type == 'CHECK_RADIO') {
			var checkRadioDiv = "";
			var expandMap = JSON.parse(data[key][i].expand);
			for ( var expand in expandMap) {
				checkRadioDiv = checkRadioDiv + "<input type='radio' name='" + data[key][i].keyword + getRandomNum(0,9999) + "' value='" + expandMap[expand].value + "'/>" + expandMap[expand].name;
			}
			$("<tr key='" + data[key][i].keyword + "' type='" + data[key][i].type + "'><td><label>" + data[key][i].name + ":</label></td><td>" + checkRadioDiv + "</td></tr>").appendTo($(table));
		}
		if (data[key][i].type == 'PULL_DOWN_SELECT') {
			var selectDiv = "";
			var expandMap = JSON.parse(data[key][i].expand);
			for ( var expand in expandMap) {
				selectDiv = selectDiv + "<option value='" + expandMap[expand].value + "'>" + expandMap[expand].name + "</option>";
			}
			$("<tr key='" + data[key][i].keyword + "' type='" + data[key][i].type + "'><td>" + data[key][i].name + ":</td><td><select>" + selectDiv + "</select></td></tr>").appendTo($(table));
		}
		if (data[key][i].type == 'IMAGE_SELECT') {
			// to do
			$("<tr key='"+ data[key][i].keyword+ "' type='"+ data[key][i].type+ "'><td>"+ data[key][i].name+ ":</td><td><div class='color-select-line'><input name='img'  class='input_image_upload'><a name='"+data[key][i].keyword+"' class='uploadlink' href='javascript:void(0);'><span>上传</span><input type='hidden' name='templateImageUrl' value=''></a><input id='img"+data[key][i].keyword+"' callback='imgUploadCallback' class='imgUploadComponet fileupload' role='' model='C' hname='templateImageUrl' type='file' url='/image/upload.json'  value=''></div></td></tr>")
					.appendTo($(table));
		}
		if (data[key][i].type == 'VIDEO_SELECT') {
			$("<tr key='" + data[key][i].keyword + "' type='" + data[key][i].type + "'><td><label>" + data[key][i].name + ":</label></td><td><input type='text' /></td></tr>").appendTo($(table));
		}
		if (data[key][i].type == 'QUOTE_COMPONENT_SELECT') {
			var tdName = $("<td>" + data[key][i].name + ":</td>");
			var tdContent = $("<td></td>");
			var quoteTr = $("<tr key='" + data[key][i].keyword + "' type='" + data[key][i].type + "' class='quote_component_tr' cycleIndex = '" + data[key][i].cycleIndex + "' componentId='" + data[key][i].expand + "'></tr>");
			if (index <= 2) {
				for (var j = 0; j < parseInt(data[key][i].cycleIndex); j++) {
					$(generateComponentDiv(index + 1, data[key][i].expand, data)).appendTo($(tdContent));
				}
			}
			$(tdName).appendTo($(quoteTr));
			$(tdContent).appendTo($(quoteTr));
			$(quoteTr).appendTo($(table));
		}
	}
	$(table).appendTo($(div));
	return div;
}

/**
 * 保存所有实例数据
 */
function saveAllInstanceData() {
	var instanceDataMap = new Map();
	$("#top_partition_area ul li").each(function() {
		var index = $(this).attr("index");
		var partitionData = new Array();
		$("#middle_content_area" + index + " div").each(function(i, data) {
			var componentInstanceId = $(this).attr("componentInstanceId");

			var componentData = new Object();
			componentData.code = $(this).attr("componentCode");
			componentData.order = i + 1;
			componentData.data = getSingleInstanceData(0, $("#right_property_area" + index + " .component_instance_div[componentInstanceId='" + componentInstanceId + "']").find("table").filter(":first"));
			partitionData[i] = componentData;
		});
		instanceDataMap[index] = partitionData;
	});
	console.log(JSON.stringify(instanceDataMap));
	var jsonStr = JSON.stringify(instanceDataMap);
	var instanceId = $("#instanceId").val();
	var url = "/cms/instance/data/save.json";
	$.post(url, {
		instanceId : instanceId,
		jsonStr : jsonStr
	}, function(data) {
		if (data == true) {
			alert("保存成功");
		} else if (data == false) {
			alert("保存失败");
		}
	});
}

/**
 * 获取组件的编辑域数据
 */
function getSingleInstanceData(index, table) {
	// var componentInstanceId =
	// $(".right_property[display='block']").attr("componentInstanceId");
	var componentData = new Map();
	$(table).children("tbody").children("tr").each(function() {
		var type = $(this).attr("type");
		var cycleIndex = $(this).attr("cycleIndex");
		if (type == 'NORMAL_INPUT') {
			componentData[$(this).attr("key")] = $(this).find("input").val();
		}
		if (type == 'CHECK_RADIO') {
			componentData[$(this).attr("key")] = $(this).find("input:checked").val();
			if($(this).find("input:checked").val() == "true"){
				componentData[$(this).attr("key")] = true;	
			}
			if($(this).find("input:checked").val() == "false"){
				componentData[$(this).attr("key")] = false;	
			}
		}
		if (type == 'PULL_DOWN_SELECT') {
			componentData[$(this).attr("key")] = $(this).find("select option:selected").val();
		}
		if (type == 'IMAGE_SELECT') {
			componentData[$(this).attr("key")] = $(this).find("input.input_image_upload").val();
		}
		if (type == 'VIDEO_SELECT') {
			componentData[$(this).attr("key")] = $(this).find("input").val();
		}
		if (type == 'QUOTE_COMPONENT_SELECT') {
			if (index <= 2) {
				if (cycleIndex > 1) {
					var componentId = $(this).attr("componentid");
					var cycleData = new Array();
					var i = 0;
					$(this).find(".component_template_div[value='" + componentId + "']").each(function(j, data) {
						var singleCycleData = getSingleInstanceData(index + 1, $(this).find("table").filter(":first"));
						if (!isEmptyData($(this).find("table").filter(":first"))) {
							cycleData[i] = singleCycleData;
							i++;
						}
					});
					componentData[$(this).attr("key")] = cycleData;
				} else {
					componentData[$(this).attr("key")] = getSingleInstanceData(index + 1, $(this).find("table").filter(":first"));
				}
			}
		}
	});
	return componentData;
}

Date.prototype.generateUniqueCode = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));

	return fmt + getRandomNum(1000, 9999).toString();
}

function getRandomNum(Min, Max) {
	var Range = Max - Min;
	var Rand = Math.random();
	return (Min + Math.round(Rand * Range));
}


function isEmptyData(dataTable) {
	var flag = true;
	$(dataTable).find("input[type='text']:not(.imgUploadComponet)").each(function() {
		if ($(this).val().trim() != "" && $(this).val().trim() != "undefined" && $(this).val().trim() != undefined && $(this).val().trim() != null  && $(this).val().trim() != 'null') {
			flag = false;
		}
	});
	return flag;
}