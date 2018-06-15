$(function(){ 
	
});
function login(){
	$.ajax({
	   url: '/Comic/qpp/comic/login/admin.do' ,  /*这是处理文件上传的servlet*/  
       type: 'POST',
       data:{
    	   "adminName": $("#username").val(),
    	   "adminPwd":$("#password").val(),
    	   "vcode" : $("#code").val()
       },
       success:function(data){
    	   if(data.error == 200){
    		   window.location.href = "index.html";
    	   }else{
    		   $('#myModal').modal('show');
				$('#myModalLabel').html('系统提示');
				$('#textcontent').html(data.msg);
				$('#btngroup').html('<button type="button" class="btn btn-default" data-dismiss="modal" onclick="off()" id="confirmbtn">关闭</button>');			
    	   }
       }
      
	});
}
