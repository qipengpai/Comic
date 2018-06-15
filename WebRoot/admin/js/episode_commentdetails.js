$(function(){
	mallwatch();
});
function mallwatch(){
	/*判断页数*/
	if (GetQueryString("nowpage01")){
		nowpage = GetQueryString("nowpage01");
	}else{
		nowpage = $(".pagination-num").val();
	}
	/*判断页面容量*/
	if(GetQueryString("pagenum01")){
		pageNum = GetQueryString("pagenum01");
		/*$(".pagination-page-list").find("option[text='pageNum']").attr("selected",true); */
		$(".pagination-page-list").val(pageNum);
	}else{
		pageNum = $(".pagination-page-list").val();
	}
	$.ajax({
		url : "/Comic/qpp/comic/select/cartoonSet.do",
		type : "post",
		data : {
			"cartoonId" : GetQueryString("id"),
			"nowPage": nowpage,
			"pageNum" :pageNum
			
		},
		success:function(data){
			if(data.error == 200){
				if(data.error == 200){
					$("#gong").html(data.totalNum);
					$(".pagination-num").val(data.nowpage);
					$(".pagination-data").html(data.totalpage);
					for(var i=0;i<data.obj.length;i++){
						var str = "";
						str +="<tr>" +"<td>" +
							"</td>" +
							"<td>" +
							data.obj[i].titile +
							"</td>" +
							"<td>" +
							data.obj[i].details +
							"</td><td>"+
							"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].id+"&quot;)'></i>" +
							"</td></tr>";
						$("#tbody").append(str);
							
							 
						};
	            
				};
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
/*页码的第一个*/
function leftbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*页码的上一个*/
function leftcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}

/*页码的最后一个*/
function rightbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*页码的后一个*/
function rightcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*改变列表的个数*/
function change(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" :(parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
			}
		}
	});
}
/*删除*/
function deleter(id){
	$.ajax({
		url:"/Comic/qpp/comic/datele/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"cartoonId" :GetQueryString("id"),
			"id" : id
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				deletefunsh();
			}
		}
	});
}
/*删除完成进行渲染*/
function deletefunsh(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSet.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].titile +
						"</td>" +
						"<td>" +
						data.obj[i].details +
						"</td><td>"+
						"<i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoonId+"&quot;)'></i>" +
						"</td></tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}

function lookcartoon(id){
	window.location.href = "episode_details.html?id="+id+"&cartoonid="+GetQueryString("id")+"&nowpage01="+$(".pagination-num").val()+"&pagenum01="+$(".pagination-page-list").val();
}
function cliback(){
	window.location.href = "cartoon_comment.html?search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pagenum="+GetQueryString("pagenum");
}