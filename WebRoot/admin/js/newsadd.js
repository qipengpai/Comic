$(function(){
	
});
 function newsadd(){
	 $.ajax({
			url:"/Comic/qpp/comic/add/news.do",
			type:"post",
			data:{
				"title":$("#newstitle").val(),
				"content": $("#newscontent").val(),
				"type":$("#newstype").val(),
				"sendDate": $("#newsdata").val()
			},
			success:function(data){
				if(data.error == 200){
					window.location.href = "table_news.html";
				}
			}
		})
 }