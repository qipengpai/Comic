$(function(){
	updatabanner();
});
function updatabanner(){
	$.ajax({
		url:"/Comic/qpp/comic/select/bannerById.do",
		type:"post",
		data:{
			"id":GetQueryString("id"),
		},
		success:function(data){
			if(data.error == 200){
				$("#mallimg").val(data.obj.title);
				$(".mainPhoto").attr("src",data.obj.httpImg);
				$(".addurl").val(data.obj.httpImg);
				$(".banneredit").val(data.obj.cartoonId);
			}else{
				
			}
		}
	});
}
function addbanner(){
	$.ajax({
		url:"/Comic/qpp/comic/update/banner.do",
		type:"post",
		data:{
			"id":GetQueryString("id"),
			"titie": $("#mallimg").val(),
			"httpImg" :$(".addurl").val(),
			"cartoonId" : $(".banneredit").val()
		},
		success:function(data){
			if(data.error == 200){
				window.location.href= "cartoon_banner.html?id="+GetQueryString("id");
			}else{
				
			}
		}
	});
}
function mainphoto(){
	var formData = new FormData($(".uploadForm" )[0]);
	$.ajax({
		url : '/Comic/app/uplod/multipartFile.do',
		type:"post",
		 data: formData,    
         async: false,    
         cache: false,    
         contentType: false,    
         processData: false,    
         success: function (returndata) {  
       	  if(returndata.error==200){
       		 $(".mainPhoto").attr("src",returndata.obj[0].src);
       		 $(".addurl").val(returndata.obj[0].src);
       	  };
       	  
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