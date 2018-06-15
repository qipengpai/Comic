$(function(){
	edittype();
});
function edittype(){
	$.ajax({
		url : "/Comic/crxl/qpp/admin/selectCartoonModelById.do",
		type : "post",
		data : {
			"id" : GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$("#byname").val(data.obj.model);
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
		url : "/Comic/crxl/qpp/admin/uodateCartoonModel.do",
		type : "post",
		data : {
			"id" : GetQueryString("id"),
			"model" : $("#byname").val()
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "cartoon_module.html";
			}
		}
	});
}