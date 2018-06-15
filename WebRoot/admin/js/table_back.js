var nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
var pagenum = $(".pagination-page-list").val();
$(function(){
	tableback();
});
/*页面进行时渲染数据*/
function tableback(){
	$.ajax({
		url:"/Comic/qpp/comic/select/feedBack.do",
		type:"post",
		data:{
			"content" : $("#search").val(),
			"backDate" : $("#date").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : nowpage
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#gong").html(data.totalNum);
				for(var i = 0; i<data.obj.length;i++){
					var str = "<tr><td></td><td>"+data.obj[i].obj+"</td><td>"+data.obj[i].userId+"</td><td class='content'>"+data.obj[i].content+"</td><td>"+data.obj[i].backDate+"</td></tr>";
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



/*当前页数*/

function change(){
	nowpage = $(".pagination-num").val();
	pagenum = $(".pagination-page-list").val();
	tableback()
}
/*搜搜*/
function sousuo(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	tableback()
}
/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	tableback()
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	tableback()
	
}

/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	tableback()
}
/*页码的后一个*/
function rightcenter(){
	nowpage =(parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	tableback()

}
