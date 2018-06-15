$(function(){
	watchedit();
	$(".moneystste").click(function(){
		 var vipvalue = $('input:radio[name="moneystste"]:checked').val();
		 if(vipvalue == 1){
			 $(".liandong").css("display","block");
		 }else if(vipvalue == 0){
			 $(".liandong").css("display","none");
			 $("#price_img").val("0");
		 }
	});
});

function watchedit(){
	$.ajax({
		url : "/Comic/qpp/comic/selectById/cartoonSet.do",
		type:"post",
		data:{
			"id": GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$(".showPhoto").attr("src",data.obj.showPhoto);
				$("#showPhoto").val(data.obj.showPhoto);
				$("#title_img").val(data.obj.titile);
				$("#details_img").val(data.obj.details);
				$("#price_img").val(data.obj.price);
				if(data.obj.moneyState == 0){
					$(".moneystste")[1].checked = true;
					 $(".liandong").css("display","none");
					 $("#price_img").val("0");
				}else if(data.obj.moneyState == 1){
					$(".moneystste")[0].checked = true;
					 $(".liandong").css("display","block");
				};
			};
		}
	});
}
function malladd(){
	
	
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoonSet.do",
		type:"post",
		data:{
			"id": GetQueryString("id"),
			"showPhoto":$("#showPhoto").val(),
			"titile":$("#title_img").val(),
			"details":$("#details_img").val(),
			"price" :$("#price_img").val(),
			"moneyState":$(".moneystste:checked").val(),
			"cartoonId":GetQueryString("cartoonId")
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "table_basic_watch.html?id="+GetQueryString("cartoonId")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
				
			}
		}
	});
}
function returnf(){
	window.location.href = "table_basic_watch.html?id="+GetQueryString("cartoonId")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
/*上传图片*/
function showphoto(id){
	var formData = new FormData($( "#uploadForm" )[0]);
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
       		 $(".showPhoto").attr("src",returndata.obj[0].src),
       		$("#showPhoto").val(returndata.obj[0].src);
       	  };
       	  
         }
	});
}