$(function(){
	selecter();
});
function selecter(){
	$.ajax({
		url : "/Comic/crxl/qpp/admin/selectThisCartoonModel.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				$("#ckeckd").empty();
				var str="";
				for(var i=0;i<data.obj.length;i++){
					str += '<div class="modulebox"><span class="modulename">'+data.obj[i].obj+'</span><span class="moduledel" onclick="delbox(&quot;'+data.obj[i].id+'&quot;)">X</span></div>'
				}
				$("#ckeckd").append(str);
			}
		}
	});
}
function add(){
	$.ajax({
		url : "/Comic/crxl/qpp/admin/selectAddThisCartoonModel.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id")
		},
		success:function(data){
			if(data.error == 200){
				var str="";
				for(var i=0;i<data.obj.length;i++){
					str += '<div class="checkbox checkbox-inline"><input type="checkbox" id="'+data.obj[i].id+'" name="module" value="'+data.obj[i].id+'"><label for="'+data.obj[i].id+'">'+data.obj[i].model+'</label></div>'
				}
				$('#myModal').modal('show');
				$('#myModalLabel').html('新增类型');
				$('#textcontent').html(str);
				$('#btngroup').html('<button type="button" class="btn btn-primary" onclick="addmodule()">提交</button><button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');
			}
		}
	});
}
function addmodule(){
	var modulearray=new Array();
	$('input[name="module"]:checked').each(function(){  
		modulearray.push($(this).val());//向数组中添加元素  
	});  
	var str=modulearray.join(',');
	if(modulearray.length > 0){
		$.ajax({
			url : "/Comic/crxl/qpp/admin/addThisCartoonModel.do",
			type:"post",
			data:{
				"cartoonId" : GetQueryString("id"),
				"cartoonModelId":str
			},
			success:function(data){
				selecter();
				$('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');
			}
		});
	}
}
function delbox(id){
	$("#delid").val(id)
	$('#confirmbox').modal('show');
}
function confirmdel(){
	$('#confirmbox').modal('hide');
	$.ajax({
		url : "/Comic/crxl/qpp/admin/deleteThisCartoonModel.do",
		type:"post",
		data:{
			"id":$("#delid").val()
		},
		success:function(data){
			selecter();
			$('#myModal').modal('show');
			$('#myModalLabel').html('系统提示');
			$('#textcontent').html(data.msg);
			$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');
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