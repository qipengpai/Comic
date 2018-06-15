var nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
var pagenum = $(".pagination-page-list").val();
$(function(){
	order();
	
});
function order(){
	$.ajax({
		url:"/Comic/qpp/comic/select/mallCartoonOrder.do",
		type:"post",
		data:{
			"cartoonId": $("#select01").val() ,
			"cartoonSetId": $("#select02").val(),
			"starTime" : $("#date01").val(),
			"endTime": $("#date02").val(),
			"pageNum": $(".pagination-page-list").val(),
			"nowPage" :  nowpage
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#gong").html(data.totalNum);
				$("#zls").html(data.spare);
				$("#cyls").html(data.msg);
				$(".pagination-data ").html(data.totalpage);
				$("#pagination-num").val(data.nowpage);
				for(var i = 0;i<data.obj.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj[i][1]+"</td><td>"+
							data.obj[i][2] +"</td><td>"+
							data.obj[i][3]+
							"</td><td>"+
							data.obj[i][4] +
							"</td><td>"+
							data.obj[i][5] +
							"</td><td>"+
							data.obj[i][6]+"</td></tr>";
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
/*搜搜*/
function change(){
	nowpage = $(".pagination-num").val();
	pagenum = $(".pagination-page-list").val();
	order()
}
/*搜搜*/
function sousuo(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	order()
}
/*页码的第一个*/
function leftbottom(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	order()
	
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	order()

}

/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	order()

}
/*页码的后一个*/
function rightcenter(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	order()
}