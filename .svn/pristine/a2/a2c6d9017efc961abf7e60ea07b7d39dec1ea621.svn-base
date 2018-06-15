$(function(){
	minit();
});
//定义一个空数组
function minit(){
	$.ajax({
		url:"/Comic/qpp/comic/Allselect/cartoonType.do",
		type:"post",
		success:function(data){
			
			for(var int=1;int<data.obj.length;int++){
				var str= "<label><input class='mytypechecked' name='Fruit' type='checkbox' value='"+data.obj[int].id+"' />"+ data.obj[int].cartoonType+"</label>";
				$("#ckeckd").append(str);
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
/*页面提交进行传参*/
function refer(){
	var boorn = true;
	if(boorn){
		var spCodesTemp ="";
		$("input[name='Fruit']:checkbox").each(function (i){
			var myvla=$(this).is(":checked");
			if(myvla==true){
					spCodesTemp += $(this).val() + "," ;
				
			}
			
		});
		
			$.ajax({
				url:"/Comic/qpp/comic/add/cartoonAllType.do",
				type:"post",
				data:{
					"cartoonId": GetQueryString("id"),
					"cartoonTypeId":spCodesTemp
				},
				success:function(data){
					if(data.error == 200){
						window.location.href="table_basic.html";
					}
				}
			});
		}else{
			$('#myModal').modal('show');
			$('#myModalLabel').html('系统提示');
			$('#textcontent').html("必须选中任意一个类型");
			$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" id="offbtn">关闭</button>');	
		}
		return false;
	
}