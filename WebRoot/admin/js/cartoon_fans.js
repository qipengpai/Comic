var piename;
var piedata=[];
var dateList;
var nowpage = 1;
var pagenum = $(".pagination-page-list").val();
$(function(){
	gamexi();
})
/*游戏数据分析*/
function gamexi(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		$.ajax({
			type : "post",
			url : "/Comic/qpp/comic/select/userEntityFansArea.do",
			data:{
				"pageNum" : pagenum,
				"nowPage" : nowpage
			},
			success : function(data){
				$("#tbody").empty();
				if(data.error == 200){
					piename = data.msg;
					piedata = data.obj;
					dateList = piedata.map(function (item) {
					    return item["name"];
					});
//					piedraw();
					for(var i = 0;i<data.obj2.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj2[i].name +"</td><td>"+data.obj2[i].value +"</td></tr>"
						$("#tbody").append(str);
						$(".pagination-num").val(data.nowpage);
						$(".pagination-data").html(data.totalpage);
						$("#jilu").html(data.totalNum);
						
					}
				}else{
					$("#tbody").empty();
					$('#myModal').modal('show');
					$("#myModalLabel").html('系统提示');
					$("#textcontent").html(data.msg);
					$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');			
				}
			}
		})
	}else{
		$('#myModal').modal('show');
		$("#myModalLabel").html('系统提示');
		$("#textcontent").html('开始时间或结束时间不能为空');
		$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');			
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
	nowpage = 1;
	pagenum = $(".pagination-page-list").val();
	gamexi()
}
/*页码的上一个*/
function leftcenter(){
	nowpage = ($(".pagination-num").val()>1)?$(".pagination-num").val()-1:1;
	pagenum = $(".pagination-page-list").val();
	gamexi()
	
}

/*页码的最后一个*/
function rightbottom(){
	nowpage =  $(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	gamexi()
	
}
/*页码的后一个*/
function rightcenter(){
	nowpage =  (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())+1):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	gamexi()
}
/*改变当前页和页码容量*/
function change(){
	nowpage =  (parseInt($(".pagination-num").val())<parseInt($(".pagination-data").html()))?(parseInt($(".pagination-num").val())):$(".pagination-data").html();
	pagenum = $(".pagination-page-list").val();
	gamexi()

}