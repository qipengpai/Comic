$(function(){
	mallrefsh();
});
function mall(){
	$.ajax({
		url : "/Comic/qpp/comic/update/cartoon.do",
		type:"post",
		data:{
			"firsrtType": $("#firstclass").val(),
			"cartoonName":$("#byname").val(),
			"cartoonAuthor":$("#author").val(),
			"serialState":$("#type option:selected").val(),
			"mainPhoto":$("#mainPhoto").val(),
			"introduction":$("#introduction").val(),
			"midelPhoto":$("#midelPhoto").val(),
			"smallPhoto":$("#smallPhoto").val(),
			"sharePhoto": $("#shareimg").val(),
			"id":GetQueryString('id'),
			"introduc":$("#introduc").val(),
			"cartoonAuthorPic":$("#authorPhoto").val()
	
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "table_basic.html?search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum");
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
function mallrefsh(){
	$.ajax({
		url : "/Comic/qpp/comic/select/cartoonById.do?id="+GetQueryString('id'),
		type:"post",
		success:function(data){
			if(data.error == 200){
				$("#mainPhoto").val(data.obj.mainPhoto);
				$("#introduction").val(data.obj.introduction);
				$("#midelPhoto").val(data.obj.midelPhoto);
				$("#smallPhoto").val(data.obj.smallPhoto);
				$("#smallsmall").val(data.obj.smallsmall);
				$("#authorPhoto").val(data.obj.cartoonAuthorPic);
				$(".mainPhoto").attr("src",data.obj.mainPhoto);
	       	 	$(".introduction").attr("src",data.obj.introduction);
	       	 	$(".midelPhoto").attr("src",data.obj.midelPhoto);
		       	$(".smallPhoto").attr("src",data.obj.smallPhoto);
		       	$(".smallsmall").attr("src",data.obj.sharePhoto);
		       	$(".authorPhoto").attr("src",data.obj.cartoonAuthorPic);
		       	$("#introduc").val(data.obj.introduc);
	       	 	if(data.obj.firstType = 1){
	       	 		$(".firstclass").val("普通漫画");
	       	 	}else{
	       	 		$(".firstclass").val("真人漫画");
	       	 	}
	       	 	$("#byname").val(data.obj.cartoonName);
	       	 	$("#author").val(data.obj.cartoonAuthor);
	       	 	$("#title").val(data.obj.updateTile);
	       		$("#type").val(data.obj.serialState);
	       	 	
	       	 	
			}
		}
		
	});
}

function uploadmain(formData){
	 $.ajax({    
	       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data: formData,    
	       async: false,    
	       cache: false,    
	       contentType: false,    
	       processData: false,    
	       success: function (returndata) {  
	     	  if(returndata.error==200){
	     		  $(".form-group_input .filename01").css("max-width","100%");
	     		 lujing01 = returndata.obj[0].src;
	         	 $("#mainPhoto").val(lujing01);
	         	 $(".mainPhoto").attr("src",lujing01);
	         	  
	     	  }
	     	  
	       },    
	       error: function (returndata) {    
	         $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(returndata);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	       }    
	  }); 
}
function mainphoto(){
	var formData = document.getElementById('mainph'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadmain(data.formData);
       
    });
}
function uploadintro(formData){
	 $.ajax({    
	       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data: formData,    
	       async: false,    
	       cache: false,    
	       contentType: false,    
	       processData: false,    
	       success: function (returndata) {  
	     	  if(returndata.error==200){
	     		 $(".form-group_input .filename02").css("max-width","100%");
	     		  lujing01 = returndata.obj[0].src;
	         	 $("#introduction").val(lujing01);
	         	 $(".introduction").attr("src",lujing01);
	         	  
	     	  };
	     	  
	       },
	       error: function (returndata) {    
	            $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(returndata);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	  
	       }  
	  })
}
function introduction(){
	var formData = document.getElementById('intro'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadintro(data.formData);
       
    });
}
function uploadmidelPh(formData){
	  $.ajax({    
	       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data: formData,    
	       async: false,    
	       cache: false,    
	       contentType: false,    
	       processData: false,    
	       success: function (returndata) {  
	     	  if(returndata.error==200){
	     		 $(".form-group_input .filename03").css("max-width","100%");
	     		lujing01 = returndata.obj[0].src;
	         	 $("#midelPhoto").val(lujing01);
	         	 $(".midelPhoto").attr("src",lujing01);
	         	  
	     	  };
	     	  
	       },
	       error: function (returndata) {    
	            $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(returndata);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
	       }  
	  })
}
function midelPhoto(){
	var formData = document.getElementById('midelPh'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadmidelPh(data.formData);
       
    });

}
function uploadsma(formData){
	 $.ajax({    
	       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data: formData,    
	       async: false,    
	       cache: false,    
	       contentType: false,    
	       processData: false,    
	       success: function (returndata) {  
	     	  if(returndata.error==200){
	     		 $(".form-group_input .filename04").css("max-width","100%");
	     		lujing01 = returndata.obj[0].src;
	         	 $("#smallPhoto").val(lujing01);
	         	 $(".smallPhoto").attr("src",lujing01);
	         	  
	     	  };
	     	  
	       },
	       error: function (returndata) {    
	            $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(returndata);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	 
	       }  
	  })
}
function smallPhoto(){
	var formData = document.getElementById('smallPh'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadsma(data.formData);
       
    });
 
}
function shareimg(){
//	alert(1)
	var formData = document.getElementById('smallsmall'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadsmall(data.formData);
    });
 
}
function uploadsmall(formData){
	  $.ajax({    
	       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
	       type: 'POST',    
	       data: formData,    
	       async: false,    
	       cache: false,    
	       contentType: false,    
	       processData: false,    
	       success: function (returndata) {  
	     	  if(returndata.error==200){
	     		 $(".form-group_input .filename05").css("max-width","100%");
	     		 lujing01 = returndata.obj[0].src;
	         	 $("#shareimg").val(lujing01);
	         	 $(".smallsmall").attr("src",lujing01);
	         	  
	     	  };
	     	  
	       },
	       error: function (returndata) {    
	           $('#myModal').modal('show');
					$('#myModalLabel').html('系统提示');
					$('#textcontent').html("请填写所有信息");
					$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	 
	       }  
	  })
	}
function authorPhoto(){
	var formData = document.getElementById('authorPh'); 
	lrz(formData.files[0]).then(function(data) {
        return uploadaut(data.formData);
       
    });
 
}
function uploadaut(formData){
  $.ajax({    
       url: '/Comic/app/uplod/multipartFile.do' ,  /*这是处理文件上传的servlet*/  
       type: 'POST',    
       data: formData,    
       async: false,    
       cache: false,    
       contentType: false,    
       processData: false,    
       success: function (returndata) {  
     	  if(returndata.error==200){
     		 $(".form-group_input .filename05").css("max-width","100%");
     		lujing01 = returndata.obj[0].src;
         	 $("#authorPhoto").val(lujing01);
         	 $(".authorPhoto").attr("src",lujing01);
         	  
     	  };
     	  
       },
       error: function (returndata) {    
            $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(returndata);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	  
       }  
  })
}
function back(){
	window.location.href = "table_basic.html?search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum");
}	