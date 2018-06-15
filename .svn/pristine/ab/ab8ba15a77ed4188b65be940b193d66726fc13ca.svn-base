var nowpage = 1;
var pageNum = 10;
var search ="";
$(function(){
	tablebasic();
	/*判断页数*/
	if (GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage");
	}else{
		nowpage = $(".pagination-num").val();
	}
	/*判断页面容量*/
	if(GetQueryString("pagenum")){
		pageNum = GetQueryString("pagenum");
	}else{
		pageNum = $(".pagination-page-list").val();
	}
	/*判断搜索条件*/
	if(GetQueryString("search")){
		search = GetQueryString("search");
	}else{
		search = $("#select").val();
	}
});
/*页面进行时渲染数据*/
function tablebasic(){
	
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"condition":search ,
			"pageNum" : pageNum ,
			"nowpage" : nowpage
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-page-list").val(pageNum);
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#gong").html(data.totalNum);
				$("#select").val(search);
				for(var i=0;i<data.obj.length;i++){
				var str = "";
				str +="<tr>" +"<td>" +
					"</td>" +
					"<td>" +
					data.obj[i].cartoon.cartoonName +
					"</td>" +
					"<td>" + 
					data.obj[i].cartoon.introduc +
					"</td>" +
					"<td><i class='fa fa-hand-pointer-o' onclick='lookcartoon(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td>" +
					"<td><i class='fa fa-hand-pointer-o' onclick='lookepisode(&quot;"+data.obj[i].cartoon.id+"&quot;)'></i></td>" +
					"</tr>";
				$("#tbody").append(str);
					
					 
				};
            
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
/*搜索*/
function sousuo(){
    nowpage = 1;
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
}
/* 获取id */
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
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
	
}
/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
	
}

/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
	
}
/*页码的后一个*/
function rightcenter(){
	nowpage =(parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pageNum = $(".pagination-page-list").val();
	search =$("#select").val();
	tablebasic()
	
}

function lookcartoon(id){
	window.location.href="cartoon_commentdetails.html?id="+id+"&search="+$("#select").val()+"&nowpage="+$(".pagination-num").val()+"&pagenum="+$(".pagination-page-list").val();
}
function lookepisode(id){
	window.location.href="episode_commentdetails.html?id="+id+"&search="+$("#select").val()+"&nowpage="+$(".pagination-num").val()+"&pagenum="+$(".pagination-page-list").val();
}

