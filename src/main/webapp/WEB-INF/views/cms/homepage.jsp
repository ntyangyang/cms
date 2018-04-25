<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/popper.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	var jsonData='${jsonData}';
	var div = $("<div id='demo' class='carousel slide' data-ride='carousel'></div>");
	var ul = $("<ul class='carousel-indicators'></ul>");
	var div_inner = $("<div class='carousel-inner'>");
	var data = eval("(" + jsonData + ")");
	for ( var key in data) {
		for ( var j in data[key]) {
// 			console.log(data[key])
// 			console.log(data[key][j])
			var urls = data[key][j].data.url
// 			console.log(urls)
			for (var i in urls) {
				console.log(urls[i].img)
				var li = "";
				if (i == 0) {
					li = $("<li data-target=''#demo' data-slide-to='"+i+"' class='active'></li>");
				} else {
					li = $("<li data-target=''#demo' data-slide-to='"+i+"'></li>");
				}
				$(li).appendTo($(ul));
				
				var img = "";
				if (i == 0) {
					img = $(" <div class='carousel-item active'><img src='"+urls[i].img+"'></div>");
				} else {
					img = $(" <div class='carousel-item'><img src='"+urls[i].img+"'></div>");
				}
				$(img).appendTo($(div_inner));
			}
		}
	}
	var a = $("<a class='carousel-control-prev' href='#demo' data-slide='prev'><span class='carousel-control-prev-icon'></span></a><a class='carousel-control-next' href='#demo' data-slide='next'><span class='carousel-control-next-icon'></span></a>");
	
	$(ul).appendTo($(div));
	$(div_inner).appendTo($(div));
	$(a).appendTo($(div));
	$(div).appendTo("body");
</script>
</body>
</html>