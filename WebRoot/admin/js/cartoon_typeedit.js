$(function(){
	edittype();
});
function edittype(){
	$.ajax({
		url : "/Comic/qpp/comic/select/cartoonType.do",
		type : "post",
		data : {
			"id" : GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$("#byname").val(data.obj.cartoonType);
			}
		}
	});
	
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function mall(){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonType.do",
		type : "post",
		data : {
			"id" : GetQueryString("id"),
			"cartoonType" : $("#byname").val()
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "table_type.html";
			}
		}
	});
}