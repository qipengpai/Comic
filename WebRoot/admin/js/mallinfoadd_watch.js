var pageNum = "";
var nowpage ="";
var back = "";
$(function(){
	
	/*判断页数*/
	if (GetQueryString("nowpagew")){
		nowpage = GetQueryString("nowpagew");
	}else{
		nowpage = $(".pagination-num").val();
	}
	/*判断页面容量*/
	if(GetQueryString("pageNumw")){
		pageNum = GetQueryString("pageNumw");
	}else{
		pageNum = $(".pagination-page-list").val();
	}
	if(GetQueryString("back")){
		back = GetQueryString("back");
	}else{
		back = "";
	}
	mallwatch();
	
});
/*页面刷新渲染数据*/
function mallwatch(){
	
	$.ajax({
		url : "/Comic/qpp/comic/select/cartoonSet.do",
		type : "post",
		data : {
			"cartoonId" : GetQueryString("id"),
			"pageNum" : pageNum,
			"nowPage" : nowpage,
			"back" : back,
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
					$(".pagination-page-list").val(pageNum);
					$(".pagination-num").val(data.nowpage);
					$(".pagination-data").html(data.totalpage);
					$("#jilu").html(data.totalNum);
					if(back == ""){
						$("#dx").css("background","");
						$("#dx").css("color","");
						$("#zx").css("background","");
						$("#zx").css("color","");
						$("#xulie").val();
					}else if(back == 1){
						$("#zx").css("background","#D84C29");
						$("#zx").css("color","#fff");
						$("#dx").css("background","");
						$("#dx").css("color","");
						$("#xulie").val(1);
					}else if(back == 0){
						$("#dx").css("background","#D84C29");
						$("#dx").css("color","#fff");
						$("#zx").css("background","");
						$("#zx").css("color","");
						$("#xulie").val(0);
					}
					for(var i=0;i<data.obj.length;i++){
						var str = "";
						if(data.obj[i].watchState == 1){
							var watchstate = "<a onclick='watchstate(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].cartoonId+"&quot;,&quot;"+data.obj[i].sort+"&quot;,&quot;"+data.obj[i].titile+"&quot;)' ><i class='fa fa-check text-navy' style='color:#D84C29'></i></a>";
						}else if(data.obj[i].watchState == 0){
							var watchstate = "<a onclick='watchstate(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].cartoonId+"&quot;,&quot;"+data.obj[i].sort+"&quot;,&quot;"+data.obj[i].titile+"&quot;)' ><i class='fa fa-check text-navy'></i></a>";
						}
						if(data.obj[i].moneyState == 1){
							var moneyState ="是";
							var makeurl = "<span></span>"
						}else if(data.obj[i].moneyState == 0){
							var moneyState ="否";
							var makeurl = "<input type='text' class='makeurl' id="+data.obj[i].id+"><span onclick='lianjie(&quot;"+data.obj[i].cartoonId+"&quot;,&quot;"+data.obj[i].id+"&quot;)'>生成链接</span>"
						}
						
						str +="<tr>" +"<td>" +
							"</td>" +
							"<td>" +
							data.obj[i].titile +
							"</td>" +
							"<td>" +
							"<img src='"+data.obj[i].showPhoto+"'>" +
							"</td>" +
							"<td>" +
							data.obj[i].details +
							"</td>" +
							"<td>" +
							data.obj[i].updateDate +
							"</td>" +
							"<td>" +
							data.obj[i].implDate +
							"</td>" +
							"<td>" +
							"<input type='number' class='dm dm"+i+"' value='"+data.obj[i].barrageCount+"'>" +
							"<a class='collapse-link' onclick='dm(&quot;"+i+"&quot;,&quot;"+data.obj[i].id+"&quot;)'> " +
							 "<i class='fa fa-chevron-up'></i>" +
			                    "</a>"+
							"</td>" +
							"<td>" +
							"<input type='number' class='pl pl"+i+"' value='"+data.obj[i].commentCount+"'>" +
							"<a class='collapse-link' onclick='pl(&quot;"+i+"&quot;,&quot;"+data.obj[i].id+"&quot;)'> " +
							 "<i class='fa fa-chevron-up'></i>" +
			                    "</a>"+
							"</td>" +
							"<td>" +
							"<input type='number' class='bf bf"+i+"' value='"+data.obj[i].playCount+"'>" +
							"<a class='collapse-link' onclick='bf(&quot;"+i+"&quot;,&quot;"+data.obj[i].id+"&quot;)'> " +
							 "<i class='fa fa-chevron-up'></i>" +
			                    "</a>"+
							"</td>" +
							"<td>" +
							"<input type='number' class='rs rs"+i+"' value='"+data.obj[i].okCount+"'>" +
							"<a class='collapse-link' onclick='rs(&quot;"+i+"&quot;,&quot;"+data.obj[i].id+"&quot;)'> " +
							 "<i class='fa fa-chevron-up'></i>" +
			                    "</a>"+
							"</td>" +
							"<td>" +
							data.obj[i].sort +
							"</td>" +
							"<td>" +
							data.obj[i].price +
							"</td>" +
							"<td>" +
							moneyState +
							"</td>" +
							"<td>" +
							watchstate  +
							"</td>" +
							"<td>" +
							makeurl+
							"</td>" +
							"<td><span class='watch btn_btn' onclick='watch(&quot;"+data.obj[i].id+"&quot;)'>更新</span><span class='edit btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].sort+"&quot;)'>删</span>" +
							"</td>";
						$("#tbody").append(str);
							
							 
						};
	            
				}else{
				
					$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html("请填写所有信息");
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				}
		}
	});
}
function ref(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = $(".pagination-num").val();
	mallwatch()
}
function tablebasic(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = $(".pagination-num").val();
	mallwatch()
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
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = 1;
	mallwatch()
}
/*页码的上一个*/
function leftcenter(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	mallwatch()
}

/*页码的最后一个*/
function rightbottom(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = $(".pagination-data").html();
	mallwatch()
}
/*页码的后一个*/
function rightcenter(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	mallwatch()
}
/*改变列表的个数*/
function change(){
	back = $("#xulie").val();
	pageNum = $(".pagination-page-list").val();
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	mallwatch()
	
}
/* 删除    */
function deleter(id,sort){   
	$("#cunzhi").val(id);
	$("#cunzhisort").val(sort);
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html('是否删除当前内容');
	$('#btngroup').html('<button type="button" class="btn btn-default" onclick="del()" id="delbtn">删除</button><button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');			
}
/* 删除当前 */
function del(){
	var id=$("#cunzhi").val();
	var sort=$("#cunzhisort").val();
	$.ajax({
			url:"/Comic/qpp/comic/datele/cartoonSet.do",
			type:"post",
			data:{
				"cartoonId" : GetQueryString("id"),
				"id" : id,
				"sort":sort
			},
			success:function(data){
				if(data.error == 200){
					$('#myModal').modal('show');
					$('#myModalLabel').html('系统提示');
					$('#textcontent').html(data.msg);
					$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');		
					
				}else{
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
	ref();
}
function lianjie(cartoonid,cartoonsetid){
//	console.log(cartoonid+','+cartoonsetid);
	var qd = document.getElementById(cartoonsetid).value.toUpperCase();
	$.ajax({
		url:'/Comic/qpp/comic/add/cartoon/lianjie.do',
		type:"post",
		data:{
			"qd":qd,
			"cartoonId":cartoonid,
			"cartoonSetId":cartoonsetid
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');		
				
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');		
			}
		}
	})
}

/*更新*/
function watch(id){
	/*$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet",
		type:"post",
		data:{
			
		},
		success:function(data){
			
		}
	});*/
	window.location.href = "table_basic_updataadd.html?id="+id+"&cartoonid="+GetQueryString("id")+"&nowpagew="+$(".pagination-num").val()+"&pageNumw="+$(".pagination-page-list").val()+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+$("#xulie").val();
}
/*添加*/
function addup(){
	window.location.href = "table_basic_add.html?id="+GetQueryString("id")+"&nowpagew="+$(".pagination-num").val()+"&pageNumw="+$(".pagination-page-list").val()+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+$("#xulie").val();
	
}
/*编辑*/
function edit(id){
	window.location.href = "table_basic_watchedit.html?id="+id+"&cartoonId="+GetQueryString("id")+"&nowpagew="+$(".pagination-num").val()+"&pageNumw="+$(".pagination-page-list").val()+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+$("#xulie").val();
}
/*观看状态*/
function watchstate(state,id,cartoonId,sort,titile){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"watchState": state,
			"id" : id,
			"cartoonId" : GetQueryString("id"),
			"sort" : sort,
			"titile" : titile,
			"back" : $("#xulie").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
/*弹幕次数*/
function dm(i,id){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"id":id,
			"barrageCount": $(".dm"+i).val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
/*评论次数*/
function pl(i,id){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"id":id,
			"commentCount": $(".pl"+i).val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
/*播放次数*/
function bf(i,id){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"id":id,
			"playCount": $(".bf"+i).val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
/*点赞次数*/
function rs(i,id){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"id":id,
			"okCount": $(".rs"+i).val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}
/*正序*/
function zx(){
	back = 1;
	pageNum = $(".pagination-page-list").val();
	nowpage = $(".pagination-num").val();
	mallwatch()
	
}
/*倒序*/
function dx(){
	back = 0;
	pageNum = $(".pagination-page-list").val();
	nowpage = $(".pagination-num").val();
	mallwatch()
	
}
/*返回上一页*/
function cliback(){
	window.location.href = "table_basic.html?search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum");
}
/*全部上线*/
function qbsx(){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSetAllWatchState.do",
		type:"post",
		data:{
			"cartoonId":GetQueryString("id"),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
				ref();
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}