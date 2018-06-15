var nowpage = 1;
var pageNum =10;
var content = "";
var newdata = "";
var state = "";
$(function(){
	/*判断页数*/
	if (GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage");
	}else{
		nowpage = $(".pagination-num").val();
	}
	/*判断页面容量*/
	if(GetQueryString("pageNum")){
		pageNum = GetQueryString("pageNum");
		$(".pagination-page-list").find("option[text='pageNum']").attr("selected",true); 
	}else{
		pageNum = $(".pagination-page-list option:selected").text();
	}
	/*判断搜索条件*/
	if(GetQueryString("content")){
		content = GetQueryString("content");
	}else{
		content = $("#newsid").val();
	}
	/*判断时间条件*/
	if(GetQueryString("sendDate")){
		newdata = GetQueryString("sendDate");
	}else{
		newdata = $("#newdata").val();
	}
	/*判断状态*/
	if(GetQueryString("state")){
		state =GetQueryString("state");
	}else{
		state = $("#pushed").val();
	}
	news();
	/*$(".pagination-page-list").find("option[text='pageNum']").attr("selected",true);*/
});
function news(){
	$.ajax({
		url:"/Comic/qpp/comic/select/news.do",
		type:"post",
		data:{
			"content" : content,
			"sendDate": newdata,
			"state" :   state,
			"nowPage" : nowpage,
			"pageNum" : pageNum
			
			
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-page-list").val(pageNum);
				$("#newdata").val(newdata);
				$("#newsid").val(content);
				$("#pushed").val(state);
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#gong").html(data.totalNum);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].state == 1){
						var grounding = "<a onclick='grounding(0,&quot;"+data.obj[i].id+"&quot;)'><i class='fa fa-check text-navy' style='color:#D84C29'></i></a>";
					}else if(data.obj[i].state == 0){
						var grounding = "<a onclick='grounding(1,&quot;"+data.obj[i].id+"&quot;)'><i class='fa fa-check text-navy'></i></a>";
					}
					var str = "";
						str += "<tr><td></td><td>"+
						data.obj[i].title +
						"</td><td>" + 
						data.obj[i].content +
						"</td>" +
						"<td>" +
						data.obj[i].type +
						"</td>" +
						"<td>" +
						data.obj[i].sendDate +
						"</td><td>" + 
						grounding +
						"</td>" +
						"<td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></tr>";
						$("#tbody").append(str);
				}
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html('暂无数据');
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				$("#tbody").empty();
			}
		}
	});
}
/*搜索*/
function sousuo(){
	nowpage = 1;
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
}
/**/
function renews(){
	nowpage = $(".pagination-num").val();
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
	
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
/* 删除    */
function deleter(id){   
	$("#cunzhi").val(id);
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html('是否删除当前内容');
	$('#btngroup').html('<button type="button" class="btn btn-default" onclick="del()" id="delbtn">删除</button><button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');						
}
/* 删除当前 */
function del(){
	var id=$("#cunzhi").val();
	$.ajax({
			url:"/Comic/qpp/comic/delete/news.do",
			type:"post",
			data:{
				"id" :id
			},
			success:function(data){
				if(data.error == 200){
					$('#myModal').modal('show');
					$('#myModalLabel').html('系统提示');
					$('#textcontent').html(data.msg);
					$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');			
					
				}
			}
		});
}
function off(){
	$("#tbody").empty();
	renews();
}
function edit(id){
	window.location.href = "cartoon_newsedit.html?id="+id+"&nowpage="+$(".pagination-num").val()+"&pageNum="+$(".pagination-page-list").val()+"&content="+$("#newsid").val()+"&sendDate="+$("#newdata").val()+"&state="+$("#pushed option:selected").val();
}
function grounding(state,id){
	$.ajax({
		url :"/Comic/qpp/comic/update/news.do",
		type:"post",
		data:{
			"state":state,
			"id":id
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				renews();
			}	
		}
	});
}


/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
	
}
/*页码的后一个*/

/*改变列表的个数*/
function change(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
	
}
/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()

}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
	
}


/*页码的后一个*/
function rightcenter(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pageNum =$(".pagination-page-list").val();
	content = $("#newsid").val();
	newdata = $("#newdata").val();
	state = $("#pushed").val();
	news()
	
}