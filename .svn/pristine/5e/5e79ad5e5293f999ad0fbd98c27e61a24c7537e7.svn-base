var nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
var pagenum = $(".pagination-page-list").val();
$(function(){
	typey();
});
function typey(){
	$.ajax({
		url:"/Comic/qpp/comic/select/userOrder.do",
		type:"post",
		data:{
			"condition": $("#order_number").val(),
			"starTime" : $("#date01").val(),
			"endTime" :$("#date02").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : nowpage
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#zls").html(data.msg);
				$("#gong").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				for(var i = 0; i<data.obj.length;i++){
					if(data.obj[i][5] == 1){
						var sex = "男";
					}else if(data.obj[i][5] == 0){
						var sex = "女";
					}
					if(data.obj[i][6] == 101){
						var type = "咔咔豆";
					}else if(data.obj[i][6] == 102){
						var type = "会员";
					}
					var typestr ="<tr>" + 
						"<td>" +
						data.obj[i][1] + 
						"</td>"+
						"<td>" +
						data.obj[i][2] + 
						"</td>"+
						"<td>" +
						data.obj[i][5] + 
						"</td>"+
						"<td>" +
						data.obj[i][8] + 
						"</td>"+
						"<td>" +
						data.obj[i][3] + 
						"</td>"+
						"<td>" +
						data.obj[i][4] + 
						"</td>"+
						"<td>" +
						type + 
						"</td>"+
						"<td>" +
						data.obj[i][7] + 
						"</td>"+
						"</tr>";
					$("#tbody").append(typestr);
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
/*改变当前页*/
function change(){
	nowpage = $(".pagination-num").val();
	pagenum = $(".pagination-page-list").val();
	typey()
}
/*搜索*/
function sousuo(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	typey()
}
function leftbottom(){
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	typey()
	
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	typey()
}



/*页码的最后一个*/
function rightbottom(){
	nowpage = $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	typey()
}
/*页码的后一个*/
function rightcenter(){
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	typey()
}
