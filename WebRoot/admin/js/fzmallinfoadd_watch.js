var pageNum;
var nowpage;
var nowpage01 ="";
var pageNum01 ="";
var back= "";
$(function(){
	/*判断页数*/
	if (GetQueryString("nowpage01")){
		nowpage01 = GetQueryString("nowpage01");
	}else{
		nowpage01 = $(".pagination-num").val();
	}
	/*判断页面容量*/
	if(GetQueryString("pagenum01")){
		pageNum01 = GetQueryString("pagenum01");
	}else{
		pageNum01 = $(".pagination-page-list").val();
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
			"pageNum" : pageNum01,
			"nowPage" : nowpage01,
			"back" : back,
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-page-list").val(pageNum01);
					$(".pagination-num").val(data.nowpage);
					$(".pagination-data").html(data.totalpage);
					$("#jilu").html(data.totalNum);
					for(var i=0;i<data.obj.length;i++){
						var str = "";
						str +="<tr>" +"<td>" +
							"</td>" +
							"<td>" +
							data.obj[i].titile +
							"</td>"  +
							"<td>" +
							"<i class='fa fa-hand-pointer-o' onclick='lookepisode(&quot;"+data.obj[i].id+"&quot;)'></i>" +
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
	nowpage01 =$(".pagination-num").val();
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
	mallwatch()
}
function tablebasic(){
	nowpage01 =$(".pagination-num").val();
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
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
	nowpage01 =1;
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
	mallwatch()
	
}
/*页码的上一个*/
function leftcenter(){
	nowpage01 =($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
	mallwatch()
}

/*页码的最后一个*/
function rightbottom(){
	nowpage01 =$(".pagination-data").html();
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
	mallwatch()
	
}
/*页码的后一个*/
function rightcenter(){
	nowpage01 =(parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
	mallwatch()
	
}
/*改变列表的个数*/
function change(){
	nowpage01 =(parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pageNum01 =$(".pagination-page-list").val();
	back= $("#xulie").val();
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




/*返回上一页*/
function cliback(){
	window.location.href = "cartoon_comment.html?search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pagenum="+GetQueryString("pagenum");
}
/*进入*/
function lookepisode(id){
	window.location.href="episode_details.html?cartooonid="+id +"&id="+GetQueryString("id")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pagenum="+GetQueryString("pagenum")+"&nowpage01="+$(".pagination-num").val()+"&pagenum01="+$(".pagination-page-list").val()
}