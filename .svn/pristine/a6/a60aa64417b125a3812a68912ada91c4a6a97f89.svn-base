$(function(){
	type();
});
function type(){
	$.ajax({
		url:"/Comic/crxl/qpp/admin/selectCartoonModel.do",
		type:"post",
		success:function(data){
			if(data.error == 200){
				for(var i = 0; i<data.obj.length;i++){
					var typestr ="<tr><td></td>" + 
						"<td>" +
						data.obj[i].model + 
						"</td><td><span class='edit  btn_btn' onclick='edit(&quot;"+data.obj[i].id+"&quot;)'>改</span><span class='delete btn_btn' onclick='deleter(&quot;"+data.obj[i].id+"&quot;)'>删</span></td></td>"+
						"<td><span class='add btn_btn' onclick='topp(&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].sort+"&quot;)'>上</span><span class='delete btn_btn' onclick='bottomm(&quot;"+data.obj[i].id+"&quot;,&quot;"+data.obj[i].sort+"&quot;)'>下</span></td>"+
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
/*上移*/
function topp(id,sort){
	$.ajax({
		url:"/Comic/crxl/qpp/admin/uodateCartoonModel.do",
		type:"post",
		data:{
			"sign":1,
			"sort":sort,
			"id":id,
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				type();
			}
		}
	});
}
/*下移*/
function bottomm(id,sort){
	$.ajax({
		url:"/Comic/crxl/qpp/admin/uodateCartoonModel.do",
		type:"post",
		data:{
			"sign":0,
			"sort":sort,
			"id":id,
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				type();
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
			url:"/Comic/crxl/qpp/admin/deleteCartoonModel.do",
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
	type();
}

function edit(id){
	window.location.href = "module_update.html?id="+id;
	
}