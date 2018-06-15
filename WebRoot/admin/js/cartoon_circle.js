var nowpage = "";
var pagenum = "";
var condition = "";
var start = "";
var over = "";
var state = "";
$(function(){
	if(GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage")
	}else{
		nowpage = 1
	}
	if(GetQueryString("pagenum")){
		pagenum = GetQueryString("pagenum")
	}else{
		pagenum = $(".pagination-page-list").val()
	}
	if(GetQueryString("condition")){
		condition = GetQueryString("condition")
	}else{
		condition = $("#newsid").val()
	}
	if(GetQueryString("start")){
		start = GetQueryString("start")
	}else{
		start = $("#startdata").val()
	}
	if(GetQueryString("over")){
		over = GetQueryString("over")
	}else{
		over = $("#overdata").val()
	}
	if(GetQueryString("state")){
		state = GetQueryString("state")
	}else{
		state = $("#pushed").val()
	}
	circle();
});

function circle(){
	$.ajax({
		url:"/Comic/qpp/comic/select/adminAllfriendcircle.do",
		type:"post",
		data:{
			"condition":condition,
			"starTime": start,
			"endTime" : over,
			"pageNum" : pagenum,
			"nowPage" : nowpage,
			"deleteState" : state
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-page-list").val(pagenum);
				$("#gong").html(data.totalNum);
				$("#newsid").val(condition);
				$("#startdata").val(start);
				$("#overdata").val(over);
				$("#pushed").val(state);
				for(var i=0;i<data.obj.length;i++){
				var str = "";
				if(data.obj[i][6] == 1){
					var hq ="<a onclick='hot(0,&quot;"+data.obj[i][0]+"&quot;)'><i class='fa fa-check text-navy' style='color:#D84C29'></i></a>"
				}else if(data.obj[i][6] == 0){
					var hq ="<a onclick='hot(1,&quot;"+data.obj[i][0]+"&quot;)'><i class='fa fa-check text-navy'></i></a>"
				}
				if(data.obj[i][7] !== 0){
					var hqimg ="<a onclick='hqimg(&quot;"+data.obj[i][0]+"&quot;)'>查看</a>"
				}else if(data.obj[i][7] == 0){
					var hqimg ="暂无图片"
				}
				str +="<tr>" +"<td>" +
					"</td>" +
					"<td>" +
					data.obj[i][1] +
					"</td>" +
					"<td>" + 
					data.obj[i][2] +
					"</td>" +
					"<td>"+
					data.obj[i][3] +
					"</td>" +
					"<td>"+
					data.obj[i][4] +
					"</td>" +
					"<td>"+
					data.obj[i][5] +
					"</td>" +
					"<td>"+
					hq +
					"</td>" +
					"<td>"+
					hqimg +
					"</td>" +
					"</tr>";
				$("#tbody").append(str);
					
					 
				};
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html("必须选中任意一个类型");
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
function hot(stateid,id){
	$.ajax({
		url:"/Comic/qpp/comic/update/adminAllfriendcircleState.do",
		type:"post",
		data:{
			"id": id,
			"deleteState" : stateid,
			"condition":$("#newsid").val(),
			"starTime": $("#startdata").val(),
			"endTime" : $("#overdata").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				recircle();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}

/*搜索*/
function recircle(){
	nowpage = $(".pagination-num").val();
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
}
/*搜索*/
function sousuo(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
}
 /*获取id*/ 
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
/*每页的列表数*/
function change(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
}
/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
	
}

/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
	
}
/*页码的后一个*/
function rightcenter(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
	
}
function change(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	condition =$("#newsid").val();
	start = $("#startdata").val();
	over = $("#overdata").val();
	state = $("#pushed").val();
	circle()
}
function hqimg(id){
	window.location.href="cartoon_circle_details.html?id="+id+"&pagenum="+$(".pagination-page-list").val()+"&nowpage="+$(".pagination-num").val()+"&condition="+$("#newsid").val()+"&start="+$("#startdata").val()+"&over="+$("#overdata").val()+"&state="+$("#pushed").val()
}