
$(function(){
	gamexi();
})
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
/*游戏数据分析*/
function gamexi(){
	if(GetQueryString("condition")){
		condition = GetQueryString("condition")
	}else{
		condition = $("#select").val()
	}
	if(GetQueryString("pagenum")){
		pageNum = GetQueryString("pagenum")
	}else{
		pageNum = $(".pagination-page-list").val()
	}
	if(GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage")
	}else{
		nowpage = $(".pagination-num").val()
	}
		$.ajax({
			type : "post",
			data : {
				"condition":condition,
				"pageNum" : pageNum,
				"nowpage" : nowpage
			},
			url : "/Comic/qpp/comic/select/cartoon.do",
			success : function(data){
				if(data.error == 200){
					$("#tbody").empty();
					$("#jilu").html(data.totalNum);
					$(".pagination-data").html(data.totalpage);
					$(".pagination-num").val(data.nowpage);
					for(var i=0;i<data.obj.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
						$("#tbody").append(str);
					}
					
				}else{
					$("#tbody").empty();
					$('#myModal').modal('show');
					$("#myModalLabel").html('系统提示');
					$("#textcontent").html(data.msg);
				}
			}
		})
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function chakan(id){
	window.location.href = "cartoonset_income.html?id="+id+"&pagenum="+$(".pagination-page-list").val()+"&nowpage="+$(".pagination-num").val()+"&condition="+$("#select").val()
}
/*搜索条件时*/
function sousuo(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val() ,
			"pageNum" : $(".pagination-page-list").val() ,
			"nowpage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#jilu").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
			}
		}
	});
}
/*页码数量时*/
function change(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val() ,
			"pageNum" : $(".pagination-page-list").val() ,
			"nowpage" : (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#jilu").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
			}
		}
	});
}
/*页码的第一个*/
function leftbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#jilu").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
			}
		}
	});
}
/*页码的上一个*/
function leftcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#jilu").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
				
			}
		}
	});
}

/*页码的最后一个*/
function rightbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : $(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
				
			}
		}
	});
}
/*页码的后一个*/
function rightcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":$("#select").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#jilu").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i=0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i].cartoon.cartoonName+"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td></tr>";
					$("#tbody").append(str);
				}
				
			}
		}
	});
}