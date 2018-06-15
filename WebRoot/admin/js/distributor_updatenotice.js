$(function(){
	$("#nowpage").val(GetQueryString('nowpage'));
	$("#condition").val(GetQueryString('condition'));
	var editor=new Simditor({textarea:$("#editor")})
	ajax();
//	ajax();
})
function ajax(){
	$.ajax({
		url:"/Comic/crxl/qpp/select/selectDistributionNews/byid.do",
		type:"post",
		data:{
			"id":GetQueryString("id")
		},
		success:function(data){
			$("#title").val(data.obj.title);
			$("#editor").val(data.obj.content);
			alert(data.obj.content)
		}
	})
}
function cancel(){
	window.location.href = "distributor_notice.html?nowpage="+$("#nowpage").val()+"&condition="+$("#condition").val();
}
function save(){
	$.ajax({
		url:"/Comic/crxl/qpp/update/updateDistributionNews.do",
		type:"post",
		data:{
			
		},
		success:function(data){
			model(data.msg)
		}
	})
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function model(msg){
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html(msg);
	$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	/*$("#tbody").empty();*/
}