var piename;
var piedata=[];
var dateList;
var pagenum = "";
var nowpage = "";
var starttime = "";
var overtime = "";
$(function(){
	if(GetQueryString("pagenum")){
		pagenum = GetQueryString("pagenum")
	}else{
		pagenum = $(".pagination-page-list").val()
	}
	if(GetQueryString("nowpage")){
		nowpage = GetQueryString("nowpage")
	}else{
		nowpage = $(".pagination-num").val()
	}
	if(GetQueryString("starttime")){
		starttime = GetQueryString("starttime")
	}else{
		starttime = $("#starttime").val()
	}
	if(GetQueryString("overtime")){
		overtime = GetQueryString("overtime")
	}else{
		overtime = $("#overtime").val()
	}
	gamexi();
})
/*游戏数据分析*/
function gamexi(){
	
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		$.ajax({
			type : "post",
			data : {
				"starTime" :starttime,
				"endTime" :overtime,
				"pageNum" : pagenum,
				"nowPage" : nowpage
			},
			url : "/Comic/qpp/comic/select/cartoonKaKaMoney.do",
			success : function(data){
				$("#tbody").empty();
				$(".pagination-num").val(data.nowpage);
				$(".pagination-data").html(data.totalpage);
				$(".pagination-page-list").val(pagenum);
				$("#jilu").html(data.totalNum);
				$("#starttime").val(starttime);
				$("#overtime").val(overtime);
				if(data.error == 200){
					piename = data.msg;
					piedata = data.obj;
					dateList = piedata.map(function (item) {
					    return item["name"];
					});
					piedraw();
					for(var i = 0;i<data.obj2.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td><td><i class='fa fa-hand-pointer-o' onclick='chakan(&quot;"+data.obj2[i].userId+"&quot;)'></i></td></tr>"
						$("#tbody").append(str);
					}
					
				}else{
					$("#tbody").empty();
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
function chakan(id){
	window.location.href = "cartoonset_income.html?id="+id+"&pagenum="+$(".pagination-page-list").val()+"&nowpage="+$(".pagination-num").val()+"&starttime="+$("#starttime").val()+"&overtime="+$("#overtime").val()
}
/*游戏数据分析*/
function sousuo(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		pagenum = $(".pagination-page-list").val();
		nowpage = 1;
		starttime = $("#starttime").val();
		overtime = $("#overtime").val();
		gamexi()
	}else{
		$("#tbody").empty();
		$('#myModal').modal('show');
		$("#myModalLabel").html('系统提示');
		$("#textcontent").html('开始时间或结束时间不能为空');	
	}
}
/*页码的第一个*/
function leftbottom(){
	pagenum = $(".pagination-page-list").val();
	nowpage = 1;
	starttime = $("#starttime").val();
	overtime = $("#overtime").val();
	gamexi()
}
/*页码的上一个*/
function leftcenter(){
	pagenum = $(".pagination-page-list").val();
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	starttime = $("#starttime").val();
	overtime = $("#overtime").val();
	gamexi()
}

/*页码的最后一个*/
function rightbottom(){
	pagenum = $(".pagination-page-list").val();
	nowpage = $(".pagination-data").html();
	starttime = $("#starttime").val();
	overtime = $("#overtime").val();
	gamexi()
}
/*页码的后一个*/
function rightcenter(){
	pagenum = $(".pagination-page-list").val();
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	starttime = $("#starttime").val();
	overtime = $("#overtime").val();
	gamexi()
	
}
/*改变当前页和页码容量*/
function change(){
	pagenum = $(".pagination-page-list").val();
	nowpage = (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	starttime = $("#starttime").val();
	overtime = $("#overtime").val();
	gamexi()
}
/* 获取id */
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURIComponent(r[2]);
	return null;
}