$(function(){
	circle_details();
});

/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function circle_details(){
	$.ajax({
		url:"/Comic/qpp/comic/select/adminAllfriendcirclePhoto.do",
		type:"post",
		data:{
			"friendCircleId":GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#gong").html(data.totalNum);
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
					"<img src='"+data.obj[i].src+"'>" +
					"</td>" +
					"<td>" + 
					data.obj[i].implDate +
					"</td>" +
					"</tr>";
				$("#tbody").append(str);
					
					 
				};
			}else{
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
			}
		}
	});
}


function back(){
	window.location.href="cartoon_circle.html?pagenum="+GetQueryString("pagenum")+"&nowpage="+GetQueryString("nowpage")+"&condition="+GetQueryString("condition")+"&start="+GetQueryString("start")+"&over="+GetQueryString("over")+"&state="+GetQueryString("state")
}