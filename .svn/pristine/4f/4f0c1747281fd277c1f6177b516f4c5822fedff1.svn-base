var nowpage = 1;
var totalpage;
var condition = "";
$(function(){
	if(GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage");
	}
	if(GetQueryString("condition")){
		condition = GetQueryString("condition");
	}
	ajax(nowpage);
})
function ajax(nowpage){
	$.ajax({
		url:"/Comic/crxl/qpp/getComic/distributor/selectComic.do",
		type:"post",
		data:{
			"condition" : condition,
			"nowPage" : nowpage
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#gong").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				totalpage = data.totalpage;
				nowpage = data.nowpage;
				for(var i = 0;i<data.obj.length;i++){
					if(data.obj[i].state == 1){
						var state = "<a onclick='updatestate(0,&quot;"+data.obj[i].id+"&quot;)'><i class='fa fa-check text-navy' style='color:#D84C29'></i></a>";
					}else if(data.obj[i].state == 0){
						var state = "<a onclick='updatestate(1,&quot;"+data.obj[i].id+"&quot;)'><i class='fa fa-check text-navy'></i></a>";
					}
					var str = "<tr>" +
							"<td>"+data.obj[i].cartoonName+"</td>" +
							"<td>" +data.obj[i].updateTile +"</td>" +
							"<td><i class='fa fa-hand-pointer-o' onclick='totitle(&quot;"+data.obj[i].id+"&quot;)'></i></td>" +
							"<td><i class='fa fa-hand-pointer-o' onclick='toimg(&quot;"+data.obj[i].id+"&quot;)'></i></td>" +
							"</tr>";
					$("#tbody").append(str);
				}
			}else{
				model(data.msg);
			}
		}
	})
}
function totitle(id){
	window.location.href="distributor_cartoontitle.html?id="+id+"&nowpage="+$(".pagination-num").val();
}
function toimg(id){
	window.location.href="distributor_cartoonimg.html?id="+id+"&nowpage="+$(".pagination-num").val();
}
function sousuo(){
	condition = $("#search").val();
	ajax(1);
}

function firstpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage == 1){
		return false
	}else{
		ajax(1);
	}
}
function prevpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(totalpage>1){
		ajax(nowpage-1);
	}else{
		return false;
	}
}
function lastpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage == totalpage){
		return false;
	}else{
		ajax(totalpage)
	}
}
function nextpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(totalpage > nowpage){
		ajax(nowpage+1)
	}else{
		return false
	}
}
function selectpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage >= totalpage){
		ajax(totalpage);
	}else{
		ajax(nowpage);
	}
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function model(msg){
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html(msg);
	$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	/*$("#tbody").empty();*/
}