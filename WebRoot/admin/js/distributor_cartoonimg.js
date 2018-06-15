var nowpage = 1;
var totalpage;
var condition = "";
$(function(){
	if(GetQueryString("nowpage")){
//		nowpage = GetQueryString("nowpage");
		$("#nowpage").val(GetQueryString("nowpage"))
	}
	if(GetQueryString("condition")){
		condition = GetQueryString("condition");
		$("#condition").val(GetQueryString("condition"))
	}
	if(GetQueryString("id")){
		$("#cartoonid").val(GetQueryString("id"))
	}
	ajax(nowpage);
})
function ajax(nowpage){
	$.ajax({
		url:"/Comic/crxl/qpp/distributorcover/selectDistributioncover.do",
		type:"post",
		data:{
			"condition" : condition,
			"nowPage" : nowpage,
			"cartoonId":$("#cartoonid").val()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$("#gong").html(data.totalNum);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-num").val(data.nowpage);
				totalpage = data.totalpage;
				nowpage = data.nowpage;
				for(var i = 0;i<data.obj.length;i++){
					var str = "<tr>" +
							"<td>"+data.obj[i].implDate+"</td>" +
							"<td><img class='imgstyle' src="+data.obj[i].modelUrl+"></td>" +
							"<td><span class='delete btn_btn' onclick='deleteimg(&quot;"+data.obj[i].id+"&quot;,&quot;"+nowpage+"&quot;)'>删</span></td>"+
							"</tr>";
					$("#tbody").append(str);
				}
			}else{
				$("#tbody").empty();
				model(data.msg);
			}
		}
	})
}
function addimg(){
	$('#addimg').modal('show');
}
function addphoto(){
	var count = 0;
	var flag = 1;
	var formData = document.getElementById('cartoonimage');
	var total = formData.files.length;
	yasuo(count,total,flag,formData.files)
}
function yasuo(count,total,flag,arr){
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
			upload(data.formData)
//			imgArray.push(data.formData)
			count++;
			flag = 1;
			yasuo(count,total,flag,arr);
		})
//		console.log(imgArray)
	}else{
//		upload(imgArray);
	}
}
var imgArray=[];
function upload(formData,waterObject) {
	$.ajax({
		url : "/Comic/app/uplod/multipartFile/water.do",
		type:"post",
		 data: formData,    
         async: false,    
         cache: false,    
         contentType: false,    
         processData: false,    
         success: function (data) { 
       	  if(data.error==200){
       		imgArray.push(data.obj[0].src);
//       		  $("#imgarray").val(data.obj[0].src);
       		
       	   }; 
         }
	});
}
function save(){
	nowpage = parseInt($(".pagination-num").val());
	var str = "";
	var length = imgArray.length;
	var mark = "|";
	for(var i=0;i<imgArray.length;i++){
		if(i == length-1){
			mark = "";
		}
		str += imgArray[i]+mark;
	}
	$.ajax({
		url:"/Comic/crxl/qpp/distributorcover/addDistributioncover.do",
		type:"post",
		data:{
			"cartoonId":$("#cartoonid").val(),
			"modelUrl":str
		},
		success:function(data){
			if(data.error == 200){
//				$("#titlecon").val('');
//				nowpage = parseInt($(".pagination-num").val());
				ajax(nowpage);
			}
			$('#addimg').modal('hide');
			model(data.msg);
		}
	})
}
function deleteimg(id){
	$("#delid").val(id);
	$("#confirmbox").modal('show');
}
function confirmdel(){
	nowpage = parseInt($(".pagination-num").val());
	$("#confirmbox").modal('hide');
	$.ajax({
		url:"/Comic/crxl/qpp/distributorcover/deleteDistributioncover.do",
		type:"post",
		data:{
			"id" : $("#delid").val(),
		},
		success:function(data){
			if(data.error == 200){
				ajax(nowpage)
			}
			model(data.msg)
		}
	})
}
function cancel(){
	window.location.href = "distributor_cartoon.html?nowpage="+$("#nowpage").val();
}
function firstpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage == 1){
		return false
	}else{
		ajax(1);
	}
}
function prevpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(totalpage>1){
		ajax(nowpage-1);
	}else{
		return false;
	}
}
function lastpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage == totalpage){
		return false;
	}else{
		ajax(totalpage)
	}
}
function nextpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(totalpage > nowpage){
		ajax(nowpage+1)
	}else{
		return false
	}
}
function selectpage(){
	totalpage = parseInt($(".pagination-data").html());
	nowpage = parseInt($(".pagination-num").val());
	if(nowpage >= totalpage){
		ajax(totalpage);
	}else{
		ajax(nowpage);
	}
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