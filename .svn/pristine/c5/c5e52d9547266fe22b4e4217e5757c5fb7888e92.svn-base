function subnotice(){
	$.ajax({
		type:"post",
		data:{
			"title":$("#title").val(),
			"content":$("#editor").val()
		},
		url:"/Comic/crxl/qpp/update/addDistributionNews.do",
		success:function(data){
			model(data.msg);
		}
	})
}
function cancel(){
	window.location.href = "distributor_notice.html";
}
function model(msg){
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html(msg);
	$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	/*$("#tbody").empty();*/
}