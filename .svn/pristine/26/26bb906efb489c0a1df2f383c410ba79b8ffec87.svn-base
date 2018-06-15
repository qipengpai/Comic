$(function(){
	newsedit();
});
function newsedit(){
	$.ajax({
		url:"/Comic/qpp/comic/select/news.do",
		type:"post",
		data:{
			"id" :GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$("#newstitle").val(data.obj[0].title);
				$("#newscontent").val(data.obj[0].content);
				$("#newstype").val(data.obj[0].type);
				$("#newsdata").val(data.obj[0].sendDate);
				
			}
		}
	});
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function newsadd(){
	$.ajax({
		url:"/Comic/qpp/comic/update/news.do",
		type:"post",
		data:{
			"id" :GetQueryString("id"),
			"content" : $("#newscontent").val(),
			"title" : $("#newstitle").val(),
			"type" : $("#newstype").val()
			
		},
		success:function(data){
			if(data.error == 200){
				window.location.href="table_news.html?nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&content="+GetQueryString("content")+"&sendDate="+GetQueryString("sendDate")+"&state="+GetQueryString("state");
				
			}
		}
	});
}
function back(){
	window.location.href="table_news.html?nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&content="+GetQueryString("content")+"&sendDate="+GetQueryString("sendDate")+"&state="+GetQueryString("state");
}