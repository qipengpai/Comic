$(function(){
	tablebasic();
	
});
/*页面进行时渲染数据*/
function tablebasic(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		success:function(data){
			if(data.error == 200){
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}

				var str = "";
				str +="<tr>" +"<td>" +
					"</td>" +
					"<td>" +
					data.obj[i].cartoon.cartoonName +
					"</td>" +
					"<td>" +
					firstTypehtml +
					"</td>" +
					"<td>" +
					cartoonTypeAll + 
					"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
                    "<i class='fa fa-chevron-up'></i>" +
                    "</a>"+
					"</td>" +
					"<td>" +
					data.obj[i].cartoon.cartoonAuthor+
					"</td>" +
					"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
					"</tr>";
				$("#tbody").append(str);
					
					 
				};
            
			};
		}
	});
}
/*改变列表的个数*/
function change(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					if(data.obj[i].cartoon.updateTile == ""){
						var updateTile = "其他";
					}else{
						var updateTile = data.obj[i].cartoon.updateTile;
					}
					
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml +
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*删除完成进行渲染*/
function deletefunsh(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : $(".pagination-num").val()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					if(data.obj[i].cartoon.updateTile == ""){
						var updateTile = "其他";
					}else{
						var updateTile = data.obj[i].cartoon.updateTile;
					}
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml +
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='add btn_btn' onclick='topp(&quot;"+data.obj[i].cartoon.id+"&quot;,&quot;"+data.obj[i].cartoon.sort+"&quot;,&quot;"+data.obj[i].cartoon.state+"&quot;,1)'>上</span><span class='delete btn_btn' onclick='bottomm(&quot;"+data.obj[i].cartoon.id+"&quot;,&quot;"+data.obj[i].cartoon.sort+"&quot;,&quot;"+data.obj[i].cartoon.state+"&quot;,0)'>下</span><span class='edit btn_btn' onclick='stick(&quot;"+data.obj[i].cartoon.id+"&quot;,&quot;"+data.obj[i].cartoon.sort+"&quot;,&quot;"+data.obj[i].cartoon.state+"&quot;,2)'>置顶</span></td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>添加banner</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查看banner</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/* 删除    */
function deleter(id){
	$.ajax({
		url:"/Comic/qpp/comic/delete/cartoon.do",
		type:"post",
		data:{
			"id":id
		},
		success:function(data){
			$("#tbody").empty();
			deletefunsh();
			
		}
	
	});
}
/*页码的第一个*/
function leftbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					if(data.obj[i].cartoon.updateTile == ""){
						var updateTile = "其他";
					}else{
						var updateTile = data.obj[i].cartoon.updateTile;
					}
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml+
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*页码的上一个*/
function leftcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					if(data.obj[i].cartoon.updateTile == ""){
						var updateTile = "其他";
					}else{
						var updateTile = data.obj[i].cartoon.updateTile;
					}
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml +
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}

/*页码的最后一个*/
function rightbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : $(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml +
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*页码的后一个*/
function rightcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoon.do",
		type:"post",
		data:{
			"pageNum" : $(".pagination-page-list").val(),
			"nowpage" : ($(".pagination-num").val()<$(".pagination-data").html())?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				for(var i=0;i<data.obj.length;i++){
					if(data.obj[i].cartoon.firstType == 1){
						var firstTypehtml = "普通漫画";
					}else{
						var firstTypehtml = "真人漫画";
					}
					if(data.obj[i].cartoonTypeAll == ""){
						var cartoonTypeAll = "其他";
					}else{
						var cartoonTypeAll = data.obj[i].cartoonTypeAll;
					}
					var str = "";
					str +="<tr>" +"<td>" +
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonName +
						"</td>" +
						"<td>" +
						firstTypehtml +
						"</td>" +
						"<td>" +
						cartoonTypeAll + 
						"<a class='collapse-link' onclick='selecter(&quot;"+data.obj[i].cartoon.id+"&quot;)'> " +
	                    "<i class='fa fa-chevron-up'></i>" +
	                    "</a>"+
						"</td>" +
						"<td>" +
						data.obj[i].cartoon.cartoonAuthor+
						"</td>" +
						"<td><span class='watch btn_btn' onclick='addbannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>增</span><span class='primary btn_btn' onclick='seebannner(&quot;"+data.obj[i].cartoon.id+"&quot;)'>查</span></td>" +
						"</tr>";
					$("#tbody").append(str);
						
						 
					};
				
			}
		}
	});
}
/*修改页面*/
function edit(id){
	
	window.location.href = "table_basic_edit.html?id="+id;
}


function selecter(id){
	window.location.href = "table_basic_select.html?id="+id;
}

function stick(id,sort,state,sortNum){
	$.ajax({
		url :"/Comic/qpp/comic/update/cartoon.do",
		type:"post",
		data:{
			"state":state,
			"id":id,
			"sort":sort,
			"sortNum" : sortNum
		},
		success:function(data){
			if(data.error == 200){
				deletefunsh();
			}	
		}
	});
}
function addbannner(id){
	window.location.href = "banneradd.html?id="+id;
}
function seebannner(id){
	window.location.href = "bannerwat.html?id="+id;
}