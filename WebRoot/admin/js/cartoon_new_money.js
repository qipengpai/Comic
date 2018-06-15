var piename;
var piedata=[];
var dateList;
$(function(){
	gamexi();
})
/*游戏数据分析*/
function gamexi(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
		$.ajax({
			type : "post",
			data : {
				"starTime" :$("#starttime").val(),
				"endTime" :$("#overtime").val()
			},
			url : "/Comic/qpp/comic/select/cartoon/profit/new.do",
			success : function(data){
				$("#tbody").empty();
				if(data.error == 200){
					var bmark = data.spare;
					$("#bmark").html(bmark);
					piename = data.msg;
					piedata = data.obj;
					dateList = piedata.map(function (item) {
					    return item["name"];
					});
					piedraw();
					for(var i = 0;i<data.obj.length;i++){
						var str = "";
						str += "<tr><td></td><td>"+data.obj[i].name +"</td><td>"+data.obj[i].value +"</td></tr>"
						$("#tbody").append(str);
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
function exportdata(){
	if(($("#starttime").val() !== "" &&  $("#overtime").val() !== "") || ($("#starttime").val() === "" && $("#overtime").val() === "")){
//		$.ajax({
//			type : "post",
//			data : {
//				"starTime" :$("#starttime").val(),
//				"endTime" :$("#overtime").val()
//			},
//			url : "http://192.168.1.39:8080/Comic/qpp/pc/comic/add/exportExcel.do",
//			success : function(data){
//				
//			}
//		})
		top.location = '/Comic/qpp/pc/comic/add/exportExcel.do?starTime='+$("#starttime").val()+'&endTime='+$("#overtime").val()
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