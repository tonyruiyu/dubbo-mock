var isquery = 0;

//延迟显示代码
//$(function(){
//  $("div.holder").jPages({
//    containerID : "itemContainer",
//    perPage : 1000,  
//    delay : 1,
//  });
//});

function showOrHideElement(id) {
    $(id).toggle();
}

function gotoPage(pageNo,queryForm) {
	if(isquery == 0){
		isquery = 1;
		var pageSize = document.getElementById("p_pageSizeSelect").value;
		document.getElementById("pageSize").value = pageSize;
		queryForm.action += "?pageNo=" + pageNo + "&pageSize=" + pageSize;
		queryForm.submit();
	}
}

function gotoPageByBtn(queryForm) {
	if(isquery == 0){
		var totalPage = document.getElementById("p_totalPage").value;
		var pageNo = document.getElementById("p_pageNo").value;
		var pageNoInt = parseInt(pageNo);
		if (pageNoInt > 0 && pageNoInt <= totalPage) {
			gotoPage(pageNoInt, queryForm);
		} else if (pageNoInt > totalPage) {
			gotoPage(totalPage, queryForm);
		} else if (pageNoInt <= 0) {
			gotoPage(1, queryForm);
		}
	}
}

function hiddenString(str, len) {
	if (str.length <= len) {
		return str;
	}
	var strlen = 0;
	var s = "";
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 128) {
			strlen = strlen + 2;
		} else {
			strlen = strlen + 1;
		}
		s = s + str.charAt(i);
		if (strlen >= len) {
			return s + "...";
		}
	}
	return s;
}
