var nowpage = 1;
var pagenum = $(".pagination-page-list").val();
$(function(){
	details();
});
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function details(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetCommentAndComment.do",
		type:"post",
		data:{
			"id":GetQueryString("plid"),
			"cartoonSetId" :GetQueryString("cartooonid"),
			"cartoonId" : GetQueryString("id"),
			"nowPage" : nowpage,
			"pageNum" : pagenum
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#gong").html(data.totalNum);
				for(var i = 0;i <data.obj.length; i ++){
					$("#hiddenval").val(data.obj[i][1]);
					if(data.obj[i][7] == 1){
						var hot_title = "<a onclick='hot(0,&quot;"+data.obj[i][0]+"&quot;)' ><i class='fa fa-check text-navy' style='color:#D84C29'></i></a>";
					}else if(data.obj[i][7] == 0){
						var hot_title = "<a onclick='hot(1,&quot;"+data.obj[i][0]+"&quot;)'><i class='fa fa-check text-navy'></i></a>";
					}
					var str = "";
					str += "<tr><td></td><td>"+
							data.obj[i][1] +
							"</td><td>" + 
							data.obj[i][2] +
							"</td><td>"+
							data.obj[i][3] +
							"</td><td>" + 
							data.obj[i][4] +
							"</td><td>" + 
							data.obj[i][5] +
							"</td><td>" +
							data.obj[i][6] +
							"</td>"+
							"<td>" +
							hot_title +
							"</td>" 
							"</tr>";
					$("#tbody").append(str);
				}
            
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				$("#tbody").empty();
			}
		}
	});
}
function hot(state,id){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSetCommentAndComment.do",
		type:"post",
		data:{
			"id":id,
			"deleteCartoonSetComment":state
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				details();
			}
		}
	});
}
function cliback(){
	window.location.href = "episode_details.html?nowpage01="+GetQueryString("nowpage01")+"&pagenum01="+GetQueryString("pagenum01")+"&id="+GetQueryString("id")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pagenum="+GetQueryString("pagenum")+"&nowpage02="+GetQueryString("nowpage02")+"&pagenum02="+GetQueryString("pagenum02")+"&cartooonid="+GetQueryString("cartooonid")
}

/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	details()
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	details()
	
}

/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	details()
	
}
/*页码的后一个*/
function rightcenter(){
	nowpage = ($(".pagination-num").val()<$(".pagination-data").html())?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	details()
	
}
/*改变列表的个数*/
function change(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	details()
}

