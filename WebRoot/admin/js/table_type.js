$(function(){
	type();
	inpblur();
});
function type(){
	$.ajax({
		url:"/Comic/qpp/comic/Allselect/cartoonType.do",
		type:"post",
		success:function(data){
			if(data.error == 200){
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
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
function typere(){
	$.ajax({
		url:"/Comic/qpp/comic/Allselect/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
				
			}
		}
	});
}
/*上移*/
function topp(sortnum,id,shownum){
	$.ajax({
		url:"/Comic/qpp/comic/update/cartoonType.do",
		type:"post",
		data:{
			"sortNum":sortnum,
			"id":id,
			"showNum":shownum
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				typere();
			}
		}
	});
}
/*下移*/
function bottomm(sortnum,id,shownum){
	$.ajax({
		url:"/Comic/qpp/comic/update/cartoonType.do",
		type:"post",
		data:{
			"sortNum":sortnum,
			"id":id,
			"showNum":shownum
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				typere();
			}
		}
	});
}
/*改变列表的个数*/
function change(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
				
			}
		}
	});
}
/*页码的第一个*/
function leftbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
				
			}
		}
	});
}
/*页码的上一个*/
function leftcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
			}
		}
	});
}

/*页码的最后一个*/
function rightbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : $(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
			}
		}
	});
}
/*页码的后一个*/
function rightcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonType.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : ($(".pagination-num").val()<$(".pagination-data").html())?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].cartoonType + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
						"</tr>";
					$("#tbody").append(typestr);
				}
			}
		}
	});
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
			url:"/Comic/qpp/comic/delete/cartoonType.do",
			type:"post",
			data:{
				"id":id
			},
			success:function(data){
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');		
				
				
			}
	});
}
function off(){
	$("#tbody").empty();
	typere();
}

function edit(id){
	window.location.href = "cartoon_typeedit.html?id="+id;
	
}
function inpblur(){
	$(".pagination-num").blur(function(){
			$.ajax({
				url:"/Comic/qpp/comic/select/cartoonType.do",
				type:"post",
				data:{
					"pageNum" : $(".pagination-page-list").val(),
					"nowpage" : $(".pagination-num").val()
				},
				success:function(data){
					if(data.error == 200){
						$("#tbody").empty();
						$(".pagination-num").val(data.nowpage);
						$(".pagination-data").html(data.totalpage);
						for(var i = 0; i<data.obj.length;i++){
							var typestr ="<tr><td></td>" + 
								"<td>" +
								data.obj[i].cartoonType + 
								"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
								"<td><span class='add btn_btn' onclick='topp(1,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(0,&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].showNum+"&quot;)'>下</span></td>"+
								"</tr>";
							$("#tbody").append(typestr);
						}
					};
				}
			});
	});
}
