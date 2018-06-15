$(function(){
	$("#nowpage").val(GetQueryString('nowpage'));
	$("#condition").val(GetQueryString('condition'));
	ajax();
})
function ajax(){
	$.ajax({
		url:"/Comic/crxl/qpp/get/distributor/byid.do",
		type:"post",
		data:{
			"id":GetQueryString("id")
		},
		success:function(data){
			$("#account").val(data.obj.userName),
			$("#qd").val(data.obj.qd),
			$("#nickname").val(data.obj.nickName),
			$("#email").val(data.obj.email),
			$("#phone").val(data.obj.phone),
			$("#payaccount").val(data.obj.accountNum),
			$("#payuser").val(data.obj.accountName),
			$("#paytype").val(data.obj.payType),
			$("#proportion").val(data.obj.proportion)
		}
	})
}
function cancel(){
	window.location.href = "distributor_user.html?nowpage="+$("#nowpage").val()+"&condition="+$("#condition").val();
}
function save(){
	$.ajax({
		url:"/Comic/crxl/qpp/distributor/information.do",
		type:"post",
		data:{
			"id":GetQueryString("id"),
			"userName":$("#account").val(),
			"qd":$("#qd").val(),
			"nickName":$("#nickname").val(),
			"email":$("#email").val(),
			"phone":$("#phone").val(),
			"AccountNum":$("#payaccount").val(),
			"AccountName":$("#payuser").val(),
			"payType":$("#paytype").val(),
			"proportion":$("#proportion").val()
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