$(function(){
	tititle();
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
function backback(){
	window.location.href = "table_basic_watch.html?id="+GetQueryString("id")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
}
function tititle(){
	$.ajax({
		url : "/Comic/qpp/comic/add/cartoonSetTitile.do",
		type:"post",
		data :{
			"cartoonId":GetQueryString("id"),
		},
		success:function(data){
			if(data.error == 200){
				$("#title_img").val(data.msg);
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
function upload(formData) {
    $.ajax({    
         url: '/Comic/app/uplod/multipartFile.do',  
         type: 'POST',    
         data: formData,    
         async: false,    
         cache: false,    
         contentType: false,    
         processData: false,    
         success: function (returndata) {  
       	  if(returndata.error==200){
       		$(".form-group_input .fileimg").css("width","100%");
           	 $("#showPhoto").val(returndata.obj[0].src);
           	 $(".showPhoto").attr("src",returndata.obj[0].src);
       	  }
       	  
         },    
         error: function (returndata) {    
            $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	 
         }    
    });
}
function doUpload(){
	var objfile = document.getElementById('fileimg');
    lrz(objfile.files[0]).then(function(data) {
        return upload(data.formData);
       
    });
}
function malladd(){
	if($("#showPhoto").val() !== "" && $(".byname").val() !== ""){
		
		$.ajax({
			url : "/Comic/qpp/comic/add/cartoonSet.do",
			type:"post",
			data :{
				"showPhoto":$("#showPhoto").val(),
				"titile" : $("#title_img").val(),
				"details":$("#details_img").val(),
				"price" : $("#price_img").val(),
				"moneyState" : $(".moneystste:checked").val(),
				"watchState" : $(".watchstate:checked").val(),
				"cartoonId" : GetQueryString("id")
			},
			success:function(data){
				if(data.error == 200){
					window.location.href = "table_basic_watch.html?id="+GetQueryString("id")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
				}else{
					$('#myModal').modal('show');
					$('#myModalLabel').html('系统提示');
					$('#textcontent').html(data.msg);
					$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	 
				}
			}
			
		});
	}else{
		$('#myModal').modal('show');
		$('#myModalLabel').html('系统提示');
		$('#textcontent').html("所有内容必须全部填写");
		$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	 
	}
}
