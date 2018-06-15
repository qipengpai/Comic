$(function(){
});
function add(){
	var addurl ="";
	var addwidth ="";
	var addheight ="";
	$("#url .img_url").each(function (i){
		addurl += $(this).val() + "," ;
		
	});
	$("#width .imgw").each(function (i){
		addwidth += $(this).val() + "," ;
		
	});
	$("#height .imgh").each(function (i){
		addheight += $(this).val() + "," ;
	});
	$.ajax({
		url : "/Comic/qpp/comic/add/cartoonPhoto.do",
		type:"post",
		data:{
			"cartoonId":GetQueryString("cartoonid"),
			"cartoonSetId":GetQueryString("id"),
			"photoUrl":addurl,
			"photoHeight" : addheight,
			"photoWidth" : addwidth
		},
		success:function(data){
			if(data.error == 200){
				window.location.href = "table_basic_updataadd.html?id="+GetQueryString("id")+"&cartoonid="+GetQueryString("cartoonid")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
			};
			
		}
	});
}
var imgArray = [];
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
function showPhoto(){
	var waterObject = $("input[name='watertype']:checked").val();
	if(waterObject != undefined){
		var count = 0;
		var flag = 1;
		var formData = document.getElementById('filename');
		var total = formData.files.length;
		yasuo(count,total,flag,formData.files,waterObject)
	}else{
		alert('请选择是否水印')
	}
	/*var count = 0;
	var flag = 1;
	var formData = document.getElementById('filename');
	var total = formData.files.length;
	yasuo(count,total,flag,formData.files)*/

}
function yasuo(count,total,flag,arr,waterObject){
	var count = count;
	var total = total;
	var flag = flag;
	var arr = arr;
	if(flag == 1 && count < total){
		flag = 0;
		lrz(arr[count]).then(function(data) {
			/*if(waterObject == 1){
				upload(data.formData,waterObject)
			}else if(waterObject == 0){
				uploadno(data.formData)
			}*/
			upload(data.formData,waterObject)
//			imgArray.push(data.formData)
			count++;
			flag = 1;
			yasuo(count,total,flag,arr,waterObject);
		})
//		console.log(imgArray)
	}else{
//		upload(imgArray);
	}
}
var int = 0;
function upload(formData,waterObject) {
	var url;
	if(waterObject == 1){
		url = '/Comic/app/uplod/multipartFile/water.do'
	}else if(waterObject == 0){
		url = '/Comic/app/uplod/multipartFile.do'
	}
	$.ajax({
		url : url,
		type:"post",
		 data: formData,    
         async: false,    
         cache: false,    
         contentType: false,    
         processData: false,    
         success: function (returndata) { 
       	  if(returndata.error==200){
       		  int ++;
       		  $("#width").val( returndata.obj[0].w);
       		  $("#height").val( returndata.obj[0].h);
       		  $(".form-group_input .filename").css("max-width","100%");
       		 var img_conent = "";
       		 var img_url = "";
       		 var imgurldata = returndata.obj[0].src;
       		img_conent += "<div class='showdiv"+int+"' ><img class='show_url show_url"+int+"' src='"+imgurldata+"'><span onclick='deleter(&quot;"+int+"&quot;)'>X</span></div>";
       		img_url +="<input type='hidden' class='img_url img_url"+int+"' value='"+imgurldata+"' >";
       		$("#showPhoto").append(img_conent);
       		$("#url").append(img_url);
       		var imgwidth = "";
       		var imgheight = "";
       		imgwidth += "<input type='hidden' class='imgw imgw"+int+"' value='"+returndata.obj[0].w+"'>";
       		imgheight += "<input type='hidden' class='imgh imgh"+int+"' value='"+returndata.obj[0].h+"'>";
       		$("#width").append(imgwidth);
       		$("#height").append(imgheight);
       	  };
       	  
         }
	});
}

/*删除*/
function deleter(idid){;
	$("#yins").val(idid);
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html('是否删除当前内容');
	$('#btngroup').html('<button type="button" class="btn btn-default" onclick="del()" id="delbtn" data-val=idid>删除</button><button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');			
	
}
function del(){
	var id = $("#yins").val();
	$(".showdiv"+id).remove();
	$(".img_url"+id).remove();
	$(".imgw"+id).remove();
	$(".imgh"+id).remove();
	$('#myModal').modal('show');
	$('#myModalLabel').html('系统提示');
	$('#textcontent').html("删除成功");
	$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="confirmbtn">关闭</button>');		
}
function primary(){
	window.location.href="table_basic_watch.html?id="+GetQueryString("cartoonid")+"&nowpagew="+GetQueryString("nowpagew")+"&pageNumw="+GetQueryString("pageNumw")+"&search="+GetQueryString("search")+"&nowpage="+GetQueryString("nowpage")+"&pageNum="+GetQueryString("pageNum")+"&back="+GetQueryString("back");
}