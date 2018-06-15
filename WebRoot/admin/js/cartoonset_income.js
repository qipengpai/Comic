var piename;
var piedata=[];
var dateList;
$(function(){
	gamexi();
})
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}
/*游戏数据分析*/
function gamexi(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		$.ajax({
			type : "post",
			data : {
				"cartoonId" : GetQueryString("id"),
				"starTime" :GetQueryString("starttime"),
				"endTime" :GetQueryString("overtime"),
				"pageNum" : $(".pagination-page-list").val(),
				"nowPage" : 1
			},
			url : "/Comic/qpp/comic/select/cartoonSetKaKaMoney.do",
			success : function(data){
				$("#tbody").empty();
				if(data.error == 200){
					piename = data.msg;
					piedata = data.obj;
					dateList = piedata.map(function (item) {
					    return item["name"];
					});
					piedraw();
					$("#jilu").html(data.totalNum);
					$("#starttime").val(GetQueryString("starttime"));
					$("#overtime").val(GetQueryString("overtime"));
					$(".pagination-data").html(data.totalpage);
					for(var i = 0;i<data.obj2.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
						$("#tbody").append(str);
					}
				}else{
					$('#myModal').modal('show');
					$("#myModalLabel").html('系统提示');
					$("#textcontent").html(data.msg);
				}
			}
		})
	}else{
		$("#tbody").empty();
		$('#myModal').modal('show');
		$("#myModalLabel").html('系统提示');
		$("#textcontent").html('开始时间或结束时间不能为空');
	}
}
function sousuo(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		$.ajax({
			type : "post",
			data : {
				"cartoonId" : GetQueryString("id"),
				"starTime" :$("#starttime").val(),
				"endTime" :$("#overtime").val(),
				"pageNum" : $(".pagination-page-list").val(),
				"nowPage" : 1
			},
			url : "/Comic/qpp/comic/select/cartoonSetKaKaMoney.do",
			success : function(data){
				$("#tbody").empty();
				if(data.error == 200){
					piename = data.msg;
					piedata = data.obj;
					dateList = piedata.map(function (item) {
					    return item["name"];
					});
					piedraw();
					$("#jilu").html(data.totalNum);
					$(".pagination-data").html(data.totalpage);
					for(var i = 0;i<data.obj2.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
						$("#tbody").append(str);
					}
				}else{
					$('#myModal').modal('show');
					$("#myModalLabel").html('系统提示');
					$("#textcontent").html(data.msg);
				}
			}
		})
	}else{
		$("#tbody").empty();
		$('#myModal').modal('show');
		$("#myModalLabel").html('系统提示');
		$("#textcontent").html('开始时间或结束时间不能为空');
	}
}
/*绘制饼图*/
function piedraw(){
	var myChart = echarts.init(document.getElementById('main'));
	option = {
		    title : {
		        text: piename,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: dateList
		    },
		    /*配置pie*/
		    series : [
		        {
		            name: '收入占比',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:piedata,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		 myChart.setOption(option);
}
/*页码的第一个*/
function leftbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetKaKaMoneyPage.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"starTime" :$("#starttime").val(),
			"endTime" :$("#overtime").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : 1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#jilu").html(data.totalNum);
				for(var i = 0;i<data.obj2.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
					$("#tbody").append(str);
				}
			}
		}
	});
}
/*页码的上一个*/
function leftcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetKaKaMoneyPage.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"starTime" :$("#starttime").val(),
			"endTime" :$("#overtime").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#jilu").html(data.totalNum);
				for(var i = 0;i<data.obj2.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
					$("#tbody").append(str);
				}
				
			}
		}
	});
}

/*页码的最后一个*/
function rightbottom(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetKaKaMoneyPage.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"starTime" :$("#starttime").val(),
			"endTime" :$("#overtime").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : $(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#jilu").html(data.totalNum);
				for(var i = 0;i<data.obj2.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
					$("#tbody").append(str);
				}
				
			}
		}
	});
}
/*页码的后一个*/
function rightcenter(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetKaKaMoneyPage.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"starTime" :$("#starttime").val(),
			"endTime" :$("#overtime").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#jilu").html(data.totalNum);
				for(var i = 0;i<data.obj2.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
					$("#tbody").append(str);
				}
				
			}
		}
	});
}
/*改变当前页和页码容量*/
function change(){
	$.ajax({
		url:"/Comic/qpp/comic/select/cartoonSetKaKaMoneyPage.do",
		type:"post",
		data:{
			"cartoonId" : GetQueryString("id"),
			"starTime" :$("#starttime").val(),
			"endTime" :$("#overtime").val(),
			"pageNum" : $(".pagination-page-list").val(),
			"nowPage" : (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html()
		},
		success:function(data){
			if(data.error == 200){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$("#jilu").html(data.totalNum);
				for(var i = 0;i<data.obj2.length;i++){
					var str = "";
					str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
					$("#tbody").append(str);
				}
				
			}
		}
	});
}
function back(){
	window.location.href = "cartoon_income.html?pagenum="+GetQueryString("pagenum")+"&nowpage="+GetQueryString("nowpage")+"&starttime="+GetQueryString("starttime")+"&overtime="+GetQueryString("overtime")
}