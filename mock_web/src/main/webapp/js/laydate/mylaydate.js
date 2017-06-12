/**
 日期范围限制 js代码
 */

var start = {
	elem : '#start',
	format : 'YYYY-MM-DD hh:mm:ss',
	istime : true,
	istoday : true,
	max : $('#end').val(),
	choose : function(datas) {
		end.min = datas; //开始日选好后，重置结束日的最小日期
		end.start = datas; //将结束日的初始值设定为开始日
	}
};
var end = {
	elem : '#end',
	format : 'YYYY-MM-DD hh:mm:ss',
	min : $('#start').val(),
	//max : $('#end').val(),
	istime : true,
	istoday : true,
	choose : function(datas) {
		start.max = datas; //结束日选好后，重置开始日的最大日期
	}
};
laydate(start);
laydate(end);