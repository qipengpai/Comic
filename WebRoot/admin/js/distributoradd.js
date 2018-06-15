 function save(){
	var password = $("#password").val();
	var repassword = $("#rpassword").val();
	console.log(password)
	console.log(repassword)
	if(password != repassword){
		model('两次密码输入不一致');
		return false;
	}
	$.ajax({
		type:"post",
		data:{
			"userName":$("#account").val(),
			"qd":$("#qd").val(),
			"userPwd":$("#password").val(),
			"nickName":$("#nickname").val(),
			"email":$("#email").val(),
			"phone":$("#phone").val(),
			"AccountNum":$("#payaccount").val(),
			"AccountName":$("#payuser").val(),
			"payType":$("#paytype").val(),
			"proportion":$("#proportion").val()
		},
		url:"/Comic/crxl/qpp/distributor/register.do",
		success:function(data){
			model(data.msg);
		}
	})
}
function cancel(){
	window.location.href="distributor_user.html"
}
function model(msg){
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html(msg);
	$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	/*$("#tbody").empty();*/
}