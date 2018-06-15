$(function(){
	banneradd();
});
function banneradd(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonAddBanner.do",
		type:"post",
		success:function(data){
			if(data.error == 200){
				var option = "<option value=''>请选择</option>";
				for(var i = 0;i<data.obj.length;i++){
					option += "<option value='"+data.obj[i][0]+"'>"+data.obj[i][1]+"</option>";
					
				}
				$("#title").append(option);
				
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
         success: function (data) {  
       	  if(data.error==200){
       		  $(".mainPhoto").attr("src",data.obj[0].src);
       		$(".addurl").val(data.obj[0].src);
       	  };
       	  
         }
	});
}
function addbanner(){
	$.ajax({
		url:"/Comic/qpp/comic/add/banner.do",
		type:"post",
		data:{
			"cartoonId": $("#title").val(),
			"title" : $("#title").find("option:selected").text(),
			"httpImg" : $(".addurl").val()
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "cartoon_banner.html";
			}
		}
	});
}