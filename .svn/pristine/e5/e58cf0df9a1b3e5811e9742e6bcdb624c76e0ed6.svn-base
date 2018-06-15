$(function(){
	
});
function mall(){
	if($("#byname").val() !== ""){
		$.ajax({
			type:"post",
			url:"/Comic/qpp/comic/add/cartoonType.do",
			data:{
				"cartoonType":$("#byname").val()
			},
			success:function(data){
				if(data.error == 200){
					window.location.href = "table_type.html";
				}
			}
		});
	}
}